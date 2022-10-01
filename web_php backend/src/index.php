<?php

$method = $_SERVER["REQUEST_METHOD"];
$parsed = parse_url($_SERVER['REQUEST_URI']);
$path = $parsed['path'];

$routes = [
    'GET' => [
        "/" => 'homePage',
        "/autok" => 'carsPage'
    ],
    'POST' => [
        "/register" => 'registrationhandler',
        "/login" => 'loginhandler',
        "/logout" => 'logoutHandler'
    ],
];

$handlerFunction = $routes[$method][$path] ?? "NotFoundHandler";

$handlerFunction();

function getPathWhitId($url)
{
    $parsed = parse_url($url);
    if (!isset($parsed['query'])) {
        return $url;
    }
    $queryParams = [];
    parse_str($parsed['query'], $queryParams);
    return $parsed['path'] . "?id=" . $queryParams['id'];
}

function logoutHandler()
{

    session_start();

    $params = session_get_cookie_params();

    setcookie(session_name(), '', 0, $params['path'], $params['domain'], $params['secure'], isset($params['httponly']));

    session_destroy();

    header('Location: ' . getPathWhitId($_SERVER['HTTP_REFERER']));
}

function carsPage()
{

    if (!isLoggedIn()) {
        echo compileTemplate("wrapper.phtml", [
            'content' => compileTemplate('subscriptionFrom.phtml', [
                'info' => $_GET['info'] ?? '',
                'isRegistration' => isset($_GET['isRegistration']),
                'url' => getPathWhitId($_SERVER['REQUEST_URI']),
            ]),
            'bejelentkezve' => false
        ]);

        return;
    }

    $carid = $_GET['id'] ?? '';
    $pdo = getConnection();
    $statement = $pdo->prepare('SELECT * FROM vehicles WHERE id = ?');
    $statement->execute([$carid]);
    $car = $statement->fetch(PDO::FETCH_ASSOC);

    echo compileTemplate('wrapper.phtml', [
        'content' => compileTemplate('carsPage.phtml', [
            'car' => $car
        ]),
        'bejelentkezve' => true,
    ]);
}

function registrationhandler()
{
    $rank = "user";
    $pdo = getConnection();

    $statement = $pdo->prepare("SELECT username FROM user WHERE username = ?");
    $statement->execute([$_POST["username"]]);
    $user = $statement->fetch(PDO::FETCH_ASSOC);

    if ($user) {
        header('Location: ' . getPathWhitId($_SERVER['HTTP_REFERER']) . '&info=regisztracioSikertelen');
        return;
    }else if($_POST["username"] === "" ||  $_POST["keresztnev"] === "" || $_POST["vezeteknev"]==="" || $_POST["jelszo"]==="" ||$_POST["email"] ==="" || $_POST["telefonszam"]===""){
        header('Location: ' . getPathWhitId($_SERVER['HTTP_REFERER']) . '&info=regisztracioSikertelen');
        return;
    }else{
        $statement = $pdo->prepare("INSERT INTO `user` (`id`, `username`, `first_name`, `last_name`, `password`, `email`, `phone_number`, `registration_date`, `rank`)
        VALUES (NULL, ? , ? , ? , ? , ? , ? , ? , ?)");

        $statement->execute([
            $_POST["username"],
            $_POST["keresztnev"],
            $_POST["vezeteknev"],
            $_POST["jelszo"],
            $_POST["email"],
            $_POST["telefonszam"],
            date("Y-m-d"),
            $rank
        ]);

        header('Location: ' . getPathWhitId($_SERVER['HTTP_REFERER']) . '&info=regisztracioSikeres');
    }
}

function loginhandler()
{
    $pdo = getConnection();
    $statement = $pdo->prepare("SELECT * FROM user WHERE username = ? ");
    $statement->execute([$_POST["username"]]);
    $user = $statement->fetch(PDO::FETCH_ASSOC);

    if (!$user) {
        header('Location: ' . getPathWhitId($_SERVER['HTTP_REFERER']) . '&info=hibasadatok');
        return;
    }


    if (!($_POST['password'] === $user["password"])) {
        header('Location: ' . getPathWhitId($_SERVER['HTTP_REFERER']) . '&info=hibasadatok');
        return;
    }

    session_start();
    $_SESSION['userID'] = $user['id'];

    header('Location: ' . getPathWhitId($_SERVER['HTTP_REFERER']));
}

function homePage()
{
    $pdo = getConnection();

    $statement = $pdo->prepare('SELECT * FROM `vehicles`');
    $statement->execute();
    $cars = $statement->fetchAll(PDO::FETCH_ASSOC);


    echo compileTemplate("wrapper.phtml", [
        'content' => compileTemplate('cars.phtml', [
            'cars' => $cars
        ]),
        'bejelentkezve' => isLoggedIn()
    ]);
}



function getConnection()
{
    return new pdo(
        'mysql:host=' . $_SERVER['DB_HOST'] . ';dbname=' . $_SERVER['DB_NAME'],
        $_SERVER['DB_USER'],
        $_SERVER['DB_PASSWORD']
    );
}

function isLoggedIn(): bool
{
    if (!isset($_COOKIE[session_name()])) {
        return false;
    }

    session_start();

    if (!isset($_SESSION['userID'])) {
        return false;
    }

    return true;
}

function compileTemplate($filePath, $params = []): string
{
    ob_start();
    require __DIR__ . "/views/" . $filePath;
    return ob_get_clean();
}

function NotFoundHandler()
{
    echo "oldal nem található";
}
