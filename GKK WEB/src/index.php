<?php

$method = $_SERVER["REQUEST_METHOD"];
$parsed = parse_url($_SERVER['REQUEST_URI']);
$path = $parsed['path'];

$routes = [
    'GET' => [
        "/" => 'homePage',
        "/autok" => 'carsPage',
        "/foglalasaim" => 'bookingPage',
        "/profil" => 'profilePage'
    ],
    'POST' => [
        "/register" => 'registrationhandler',
        "/login" => 'loginhandler',
        "/logout" => 'logoutHandler',
        "/booking" => 'bookingcarHandler',
        "/updatepassword" => 'updatepassHandler',
        "/updateusername" => 'updateusernameHandler',
        "/updateemail" => 'updateemailHandler',
        "/updatephonenumber" => 'updatephonenumHandler',
        "/foglalasaim" => 'deletebookingHandler'
    ],
];

session_start();

$handlerFunction = $routes[$method][$path] ?? "NotFoundHandler";

$handlerFunction();

function logoutHandler()
{

    session_start();

    $params = session_get_cookie_params();

    setcookie(session_name(), '', 0, $params['path'], $params['domain'], $params['secure'], isset($params['httponly']));

    session_destroy();

    header('Location: ' . getPathWhitId($_SERVER['HTTP_REFERER']));
}//kijelentkezés gombra kattintva eldobja a sütiket és nem lesznek elérhetőek azok az oldalak amik bejelentkezve elérhetőek voltak

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

    $_SESSION['userID'] = $user['id'];
    $_SESSION['username'] = $user['username'];

    header('Location: ' . getPathWhitId($_SERVER['HTTP_REFERER']));
}//bejelentkezés megvizsgálja hogy létezik e olyan felhasználó és ha igen összehasonlítja a tárolt jelszót és a megadott jelszót

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
}//regisztrációnál megvizsgálja a bevitt adatokat és ha megegyeznek és még nem létezik olyan felhasználónévvel user akkor feltölti az adatbázist és mehet tovább a bejelentkezésre

function profilePage()
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

    $usrid = $_SESSION['userID'];
    $pdo = getConnection();
    $statement = $pdo->prepare('SELECT * FROM `user` WHERE id = ?');
    $statement->execute([$usrid]);
    $usr = $statement->fetch(PDO::FETCH_ASSOC);

    echo compileTemplate('wrapper.phtml', [
        'content' => compileTemplate('profilePage.phtml', [
            'info' => $_GET['info'] ?? '',
            'usr' => $usr
        ]),
        'bejelentkezve' => true,
    ]);
}//megjelennek az adott profil adatai és azokat lehet módosítani

function updatepassHandler(){
    $oldpassword = $_POST["oldpass"];
    $newpass1 = $_POST["passupdate"]; 
    $newpass2 = $_POST["passupdate2"];
    $usrid = $_SESSION['userID'];

    $pdo = getConnection();

    $statement = $pdo->prepare("SELECT password FROM user WHERE id = ?");
    $statement->execute([$usrid]);
    $pass = $statement->fetch(PDO::FETCH_ASSOC);

    if($oldpassword === "" || $newpass1 === "" || $newpass2 == ""){
        header('Location: ' . getPathWhitId($_SERVER['HTTP_REFERER']) . '&info=emptydata');
        return;
    }else{
        if(implode($pass) === $oldpassword){
            if($newpass1 === $newpass2){
                $statement = $pdo->prepare("UPDATE user SET password = ? WHERE id = ?");
                $statement->execute([$newpass1, $usrid]);
                header('Location: ' . getPathWhitId($_SERVER['HTTP_REFERER']) . '&info=sikeresJelszovaltoztatas');
            }else{
                header('Location: ' . getPathWhitId($_SERVER['HTTP_REFERER']) . '&info=badnewpassword');
                return;
            }
        }else{
            header('Location: ' . getPathWhitId($_SERVER['HTTP_REFERER']) . '&info=badpassword');
            return;
        }
    }
}//a profil oldalon jelszót lehet módosítani

function updateusernameHandler(){
    $newusername = $_POST["usernameupdate"];
    $usrid = $_SESSION['userID'];

    $pdo = getConnection();

    $statement = $pdo->prepare("SELECT username FROM user WHERE username = ?");
    $statement->execute([$newusername]);
    $uname = $statement->fetch(PDO::FETCH_ASSOC);

    if($newusername === ""){
        header('Location: ' . getPathWhitId($_SERVER['HTTP_REFERER']) . '&info=emptydata');
        return;
    }else{
        if($uname){
            header('Location: ' . getPathWhitId($_SERVER['HTTP_REFERER']) . '&info=baddata');
            return;
        }else{
            $statement = $pdo->prepare("UPDATE user SET username = ? WHERE id = ?");
            $statement->execute([$newusername, $usrid]);
            header('Location: ' . getPathWhitId($_SERVER['HTTP_REFERER']) . '&info=sikeresFelhasznalonevvaltoztatas');
        }
    }
}//profil oldalon felhasználónév változtatás

function updateemailHandler(){
    $newemail = $_POST["emailupdate"];
    $usrid = $_SESSION['userID'];

    $pdo = getConnection();

    $statement = $pdo->prepare("SELECT email FROM user WHERE email = ?");
    $statement->execute([$newemail]);
    $email = $statement->fetch(PDO::FETCH_ASSOC);

    if($newemail === ""){
        header('Location: ' . getPathWhitId($_SERVER['HTTP_REFERER']) . '&info=emptydata');
        return;
    }else{
        if($email){
            header('Location: ' . getPathWhitId($_SERVER['HTTP_REFERER']) . '&info=bademail');
            return;
        }else{
            $statement = $pdo->prepare("UPDATE user SET email = ? WHERE id = ?");
            $statement->execute([$newemail, $usrid]);
            header('Location: ' . getPathWhitId($_SERVER['HTTP_REFERER']) . '&info=sikeresEmailvaltoztatas');
        }
    }
}//profil oldalon email változtatás

function updatephonenumHandler(){
    $newphonenum = $_POST["phoneupdate"];
    $usrid = $_SESSION['userID'];

    $pdo = getConnection();

    if($newphonenum === ""){
        header('Location: ' . getPathWhitId($_SERVER['HTTP_REFERER']) . '&info=emptydata');
        return;
    }else{
            $statement = $pdo->prepare("UPDATE user SET phone_number = ? WHERE id = ?");
            $statement->execute([$newphonenum, $usrid]);
            header('Location: ' . getPathWhitId($_SERVER['HTTP_REFERER']) . '&info=sikeresTelefonszamvaltoztatas');
    }
}//telefonszám változtatás

function homePage()
{
    $elerheto = 'elerheto';
    $nemelerheto = 'nem_elerheto';
    $pdo = getConnection();

    $statement = $pdo->prepare('SELECT * FROM `vehicles` WHERE status = ?');
    $statement->execute([$elerheto]);
    $cars = $statement->fetchAll(PDO::FETCH_ASSOC);

    $statement = $pdo->prepare('SELECT * FROM `vehicles` WHERE status = ?');
    $statement->execute([$nemelerheto]);
    $nocars = $statement->fetchAll(PDO::FETCH_ASSOC);


    echo compileTemplate("wrapper.phtml", [
        'content' => compileTemplate('cars.phtml', [
            'cars' => $cars,
            'nocars' => $nocars
        ]),
        'bejelentkezve' => isLoggedIn()
    ]);
}//a kezdő oldalon kilistázásra kerül az összes tárolt gépjármű

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
            'info' => $_GET['info'] ?? '',
            'car' => $car
        ]),
        'bejelentkezve' => true,
    ]);
}//a kezdő oldalon kiválasztott gépjármű itt jelenik meg

function bookingPage()
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

    //SELECT * FROM `bookings` WHERE user_id = 7
    $useridbooking = $_SESSION['userID'];
    $status = 'foglalva';
    $pdo = getConnection();
    $statement = $pdo->prepare('SELECT * FROM `bookings` WHERE user_id = ? AND status = ?');
    $statement->execute([$useridbooking,
                         $status]);
    $mybookings = $statement->fetchAll(PDO::FETCH_ASSOC);
    
    echo compileTemplate('wrapper.phtml', [
        'content' => compileTemplate('bookingPage.phtml', [
            'mybookings' => $mybookings
        ]),
        'bejelentkezve' => true
    ]);

}//az user megtudja nézni az életben lévő foglalásait majd ha már a bérelt autót átadásra került az ügyfél részére akkor eltünik

function bookingcarHandler()
{
    $user_id = $_SESSION['userID'];
    $singlecarid = $_GET['id'] ?? '';
    $borrowed_start = $_POST['start_booking'];
    $borrowed_stop = $_POST['end_booking'];
    $_driverlicense = $_POST['v_eng_szam'];
    $status = "foglalva";
    $localdate = date("Y-m-d");

    $pdo = getConnection();
    $statement = $pdo->prepare('SELECT * FROM vehicles WHERE id = ?');
    $statement->execute([$singlecarid]);
    $car = $statement->fetch(PDO::FETCH_ASSOC);
    $daily_price = $car['daily_price'];

    $_start = new DateTime($_POST['start_booking']); 
    $_stop = new DateTime($_POST['end_booking']); 
    $interval = $_start->diff($_stop);
    $kulonbseg = $interval->format('%a');
    $napszam = $kulonbseg + 1;


    
    $allprice = $napszam * $daily_price;
 
    if($borrowed_start < $localdate || $borrowed_start === ""){
        header('Location: ' . getPathWhitId($_SERVER['HTTP_REFERER']) . '&info=invaliddate');//nem lehet hamarabb foglalni mint a mai dátum
        return;
    }

    if($borrowed_start > $borrowed_stop || $borrowed_stop === ""){
        header('Location: ' . getPathWhitId($_SERVER['HTTP_REFERER']) . '&info=invaliddate1');//nem lehet - napra foglalni
        return;
    }

    if($_driverlicense === ""){
        header('Location: ' . getPathWhitId($_SERVER['HTTP_REFERER']) . '&info=invaliddriverlicense');//muszáj megadni a vez eng számot
        return;
    }    

    $pdo = getConnection();
    $statement = $pdo->prepare("INSERT INTO `bookings` (`id`, `user_id`, `borrowed_vehicle_id`, `borrow_start`, `borrow_end`, `driver_license_number`, `price`, `status`) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?);");
    
    $statement->execute([
        $user_id,
        $singlecarid,
        $borrowed_start,
        $borrowed_stop,
        $_driverlicense,
        $allprice,
        $status
    ]);

    $statement =  $pdo->prepare("UPDATE `vehicles` SET `status` = 'nem_elerheto' WHERE `vehicles`.`id` = ?");
    $statement->execute([
        $singlecarid
    ]);

    header('Location: ' . '/foglalasaim');//sikeres foglalás
    
}//megvizsgálja az összes bevitelimezőt ami kell a foglaláshoz, és ha teljesülnek a feltételek akkor feltöltésre kerül a foglalás

function deletebookingHandler(){
        $bookingid = $_GET['id'];
        $elerheto = 'elerheto';
        $torolve = 'torolve';

        $pdo = getConnection();
        $statement = $pdo->prepare('SELECT * FROM `bookings` WHERE id = ?');
        $statement->execute([$bookingid]);
        $booking = $statement->fetch(PDO::FETCH_ASSOC);
        $car = $booking['borrowed_vehicle_id'];

        if($booking){
            $statement = $pdo->prepare('UPDATE `vehicles` SET `status` = ? WHERE `vehicles`.`id` = ?');
            $statement->execute([$elerheto, $car]);

            $statement = $pdo->prepare('UPDATE `bookings` SET `status` = ? WHERE `bookings`.`id` = ?');
            $statement->execute([$torolve, $bookingid]);
        }

    


}





function getConnection()
{
    return new pdo(
        'mysql:host=' . $_SERVER['DB_HOST'] . ';dbname=' . $_SERVER['DB_NAME'],
        $_SERVER['DB_USER'],
        $_SERVER['DB_PASSWORD']
    );
}//létre jön a kapcsolat az adatbázissal egy .htacces fileon keresztül

function isLoggedIn(): bool
{
    if (!isset($_COOKIE[session_name()])) {
        return false;
    }

    if (!isset($_SESSION['userID'])) {
        return false;
    }

    return true;
}//vizsgálja hogy a böngésző tárol e sütit 

function compileTemplate($filePath, $params = []): string
{
    ob_start();
    require __DIR__ . "/views/" . $filePath;
    return ob_get_clean();
}//a views mappábol kikéri a wrapper.phtml t és azon megjeleníti az aktuális tartalmat

function getPathWhitId($url)
{
    $parsed = parse_url($url);
    if (!isset($parsed['query'])) {
        return $url;
    }
    $queryParams = [];
    parse_str($parsed['query'], $queryParams);
    return $parsed['path'] . "?id=" . $queryParams['id'];
}//url ből lekéri az adatokat

function NotFoundHandler()
{
    
    echo compileTemplate("wrapper.phtml", [
        'content' => compileTemplate('notfoundPage.phtml'),
        'bejelentkezve' => isLoggedIn()
    ]);
}//ha hibás az url akkor erre az oldalra dob át

