# GKKApp

SZENT ISTVÁN KATOLIKUS TECHNIKUM ÉS GIMNÁZIUM
SÁTORALJAÚJHELY
Szakma megnevezése: Szoftverfejlesztő és - tesztelő
Szakma azonosító száma: 5 0613 12 03
SZOFTVERFEJLESZTÉS ÉS –TESZTELÉS VIZSGAREMEK
Személygépjármű bérlés
Balogh Dávid
Füredel Valentin Szilveszter
Tarnóczi Levente Tibor
2/14C
Sátoraljaújhely, 2023

TARTALOMJEGYZÉK
  Tartalom
  Bevezetés	3
  Felhasználói Dokumentáció	4
    Rendszerkövetelmény	4
    Hardver követelmények:	4
    Szoftver követelmények	4
    Telepítés menete	5
    Adatbázis hozzáadása	5
    Weboldal telepítés menete:	6
    Java alkalmazás telepítése:	7
    Szoftver használata	8
    Web alkalmazás használata	8
    Java alkalmazás használata	14



Bevezetés
    Azért választottuk ezt a szakmát, mert régóta érdeklődünk szoftverfejlesztéssel kapcsolatos témák iránt. Vannak olyan kihívások, amik felkeltik a         figyelmünket például összetettebb rendszerek készítése és működése. Később szeretnénk ebben a szakmában elhelyezkedni, tevékenykedni vagy                 továbbtanulni.
    Az utóbbi években kis városunkban megnőtt a turizmus és ennek köszönhetően megnőtt a gépjármű bérlésre az igény.
    A mi projektünk célja, hogy a hétköznapi emberek számára könnyebbé és egyszerűbbé tegye a gépjármű bérlés lehetőségét, a cégek számára könnyebbé és       átláthatóbbá tegye a gépjárművek nyilvántartását és a bérlések kezelését. 
    Azért választottuk ezt a témát mivel érdeklődünk a személy-gépkocsik iránt. Korábbi autóbérlésünk során találkoztunk néhány nehézséggel, így             szeretnénk megkönnyíteni a városba érkező turisták vagy helyiek, vagy környékbeliek közlekedési problémáit, egyúttal egy vállalkozás munkáját, ami       bérbeadással foglalkozik. 
    A projekt elkészítése során a következő programozási nyelveket használtuk : Java , PHP , MySQL , HTML, CSS, JavaScript.

Felhasználói Dokumentáció
  Rendszerkövetelmény
    Hardver követelmények:
      Minimális rendszerkövetelmény:
        Operációs rendszer: Windows 10/Linux/MacOS
        Processzor: 1 GHz vagy gyorsabb
        RAM: 1 GB 
        Merevlemez-terület: 1 GB
        Videókártya: A DirectX 9-es vagy újabb verziójával kompatibilis.
        Kijelző: 800x600
      Ajánlott rendszerkövetelmény:
        Operációs rendszer: Windows 10/Linux/MacOS
        Processzor: 2 GHz vagy gyorsabb
        RAM: 2 GB 
        Merevlemez-terület: 1 GB
        Videókártya: A DirectX 9-es vagy újabb verziójával kompatibilis.
        Kijelző: 1280x1024
    Szoftver követelmények
        • XAMPP  v3.3.0
        • PHP v8.1.6
        • Java Runtime Enviroment(JDK) 11.0.14

  Telepítés menete
  Adatbázis hozzáadása
  Indítsa el a xampp-ot, ha nincs feltelepítve a számítógépre, akkor telepítse a v3.3.0 verzió számú vagy újabb programot, amely 8.1.6 vagy újabb php-val rendelkezik. A telepítésének folyamata a telepitő állomány megnyitásával kezdődik, majd ha végzett a telepítés indítsa el az Apache és a MySQL szervert. Ez az adatbázis FONTOS a java alkalmazás futtatásához is!

  0‑1. XAMPP Control Panel v3.3.0
  Nyisson meg egy böngészőt (pl.: Google Chrome) és lépjen be a phpMyadmin szerverére a http://localhost/phpmyadmin/ címmel. Hozza létre a következő adatbázist ’gkkdb’ néven, a karakter kódolása utf8_hungarian_ci legyen. 

  0‑2. Adatbázis létrehozása
  Nyissa meg a létrehozott adatbázist (gkkdb) és kattintson az import fülre. Kattintson a fájl kiválasztására, majd válassza ki a GKKAPP/adatbázis mappából a gkkdb.sql fájlt és kattintson az indítás gombra.

  0‑3. Adatbázis importálása
  Írja be a keresőmezőbe a localhost címet és élvezze weboldalunk nyújtotta szolgáltatásokat. 
  Weboldal telepítés menete:
  A weboldal feltelepítése előtt keresse meg a xampp/htdocs nevű mappát, majd törölje annak minden tartalmát, ha ez megtörtént helyezze a GKK WEB/src mappát a törölt fájlok helyére, majd hozzon létre egy .htaccess fájlt. A htaccess fájl egy hatékony webhelyfájl, amely szabályozza a webhely magas szintű konfigurációját. Nyissa meg a létrehozott fájlt, majd illessze be a következő kódot:
  <IfModule mod_rewrite.c>
  RewriteEngine On

  # Környezeti változók beállítása
  SetEnv DB_HOST localhost
  SetEnv DB_NAME gkkdb
  SetEnv DB_USER root
  SetEnv DB_PASSWORD

  # Ha a public mappában lévő fájlra érkezett a kérés, szolgáld ki a fájlt
  RewriteCond %{REQUEST_URI} ^.*public/(.*)
  RewriteRule ^(.*)$ src/public/%1 [END]

  # Minden más esetben az index.php-t futtasd le
  RewriteRule (.*)  src/index.php [QSA]
  </IfModule>

  Java alkalmazás telepítése:
  Windows indítás:
  Töltse le a git reposytort(https://github.com/Florex001/GKKApp). Majd csomagolja ki egy tetszőleges helyre. Indítsa el a „GKKApp_win.exe” fájlt és próbáljon meg bejelentkezni. Ellenőrizze hogy elérhető e az adatbázis, ha nem akkor hajtsa végre az „Adatbázis hozzáadása” című fejezetben leírtak szerint az adatbázis létrehozását. Ha nincs létrehozva az adatbázis ezt a hibaüzenetet fogja látni:

  0‑4. Hiba üzenet Java
  Arch Linux  indítása:
  Töltse le a git reposytort(https://github.com/Florex001/GKKApp). Majd csomagolja ki egy tetszőleges helyre. Indítsa el a „Gkkapp_linux.jar” fájlt és próbáljon meg bejelentkezni. Ellenőrizze hogy elérhető e az adatbázis, ha nem akkor hajtsa végre az „Adatbázis hozzáadása” című fejezetben leírtak szerint az adatbázis létrehozását. Ha nincs létrehozva az adatbázis ezt a hibaüzenetet fogja látni:

  0‑5. Hiba üzenet Java
  Szoftver használata
  Web alkalmazás használata

  0‑6. Kezdőlap
  Általános információk
  A weboldalunk olyan szolgáltatást nyújt, amelyen keresztül személygépjárműveket lehet bérelni. Az oldal fejlesztéséhez Google Chrome böngészőt használtunk, de más böngészőkkel is működik. A gépjárművekhez általános leírás is tartozik valamint a bérlés napi díjáról is kap tájékoztatás a felhasználó. A bérlés megkezdéséhez felhasználói fiókra van szükség.

  0‑7. Bejelentkezés
  Gépjárművek elrendezése
  A gépjárművek kártyákban vannak elhelyezve a kezdőlapon. Ezek elrendezése négyzetrács szerűen van megoldva. A kártyák tartalmaznak egy képet a gépjárműről, egy címet, ami a gépjármű neve, egy leírást a gépjárműről és a gépjármű napi árát. Ezek mellett megtalálható egy foglalás nevű gomb is, amely átnavigálja a felhasználót a gépjármű foglalás oldalra, amelyhez bejelentkezés szükséges.
  0‑8. Gépjármű kártya
  Regisztráció és Belépés

  0‑9. Regisztráció
  Ahhoz hogy a felhasználó regisztrálni tudjon, ki kell választania az egyik gépjárművet majd a foglalás gombra kattintani (Regisztráció vagy Bejelentkezés esetén nem kerül foglalásra az adott gépjármű!). Majd át navigálja a weboldal a felhasználót hogy „A tartalom megtekintéséhez bejelentkezés szükséges”. Ha rendelkezik felhasználói fiókkal jelentkezzen be. Ha nincs még felhasználói fiókja, akkor kattintson a „Nincs még felhasználói fiókod? Regisztráció” Regisztráció linkre. Ha rákattintott, akkor az oldal automatikusan átnavigálja a regisztráció oldalra, ahol minden adat megadása kötelező.

  0‑10. Bejelentkezés
  A bejelentkezésnél Felhasználónév és Jelszó megadása kötelező. Ha sikeres a bejelentkezés egy sütit fogunk eltárolni a böngészőjében. Ha sikeres a bejelentkezés, akkor a kiválasztott gépjárműnek a foglalás oldala fog megjelenni. Illetve a navigációs sávban megjelenik a kijelentkezés gomb, amire ha rá kattintunk, akkor törlődik az eltárolt süti és új bejelentkezésre lesz szüksége és még megjelenik a foglalásaim fül is ahol a felhasználó meg tudja tekinteni a gépjármű foglalásait. Profil oldal is elérhetővé vállik onnan ismerhető fel, hogy a felhasználónév lesz a neve annak a menünek.
  Gépjármű foglalása

  0‑11. Gépjármű foglalás
  Ha sikeresen bejelentkezett, akkor foglalás oldal fog megjelenni a kiválasztott autójával. Ha biztos benne hogy azt az autót szeretné lefoglalni, akkor töltse ki az alábbi mezőket valós adatokkal:

  0‑12. Adatok megadása foglaláshoz
  Ha biztos benne hogy arra az időpontra szeretné kibérelni az adott gépjárművet és a vezetői engedélyszáma valós, akkor kattintson a Foglalás gombra. Ha sikeres a foglalás, akkor át navigálja a foglalásaim oldalra. Ha nem megfelelő a foglalás, akkor hiba üzenetet fog kapni: Például.:

  0‑13.Hiba üzenet
  Foglalásaim oldal

  0‑14. Foglalásaim menüpont
  A foglalásaim menüpontra kattintva, vagy ha éppen foglalt egy gépjárművet és átnavigálásra került, akkor a saját foglalásait fogja látni. A foglalását csak a gépjármű átadásának idejéig fogja látni. Ha már a gépjárművet használja, amit bérel, abban az esetben már nem fogja látni foglalását. Fizetés gépjármű átadása előtt történik a telephelyen készpénzzel vagy bankkártyával. Amennyiben módosítani akarja foglalását akkor a navigációs menüben kattintson az Elérhetőségek menüpontra. Azt fogja tapasztalni, hogy az oldal aljára, fogja navigálni ahol, megjelennek a céggel kapcsolatos elérhetőségek. Módosításhoz hívja fel az ott található telefonszámot vagy írjon emailt. Foglalás lemondására az oldalon van lehetőség. 

  0‑15.Foglalás megjelenése
  A saját foglalása a 0-15.Foglalás megjelenése ábrának megfelelően fog megjelenni. Ebben látni fogja a foglalás azonosítóját, amit jegyezzen meg, mert fontos a gépjármű bérlése során. Ezen kívül megjelenik még a gépjármű neve, Foglalás kezdete, Foglalás vége illetve a teljes ár. A teljes ár a két megadott dátum közötti napok száma szorozva napi árral. A Foglalás lemondása gomb is itt jelenik meg, ha le akarja mondani a foglalását nincs más dolga csak rá kattintani.
  Profil oldal

  0‑16. Profil oldal
  A profil oldalon megjelenik egy profilkép, a felhasználónév, a felhasználó neve, az e-mail címe illetve a regisztráció időpontja. Láthatunk egy módosítás gombot is amely arra a célra szolgál, hogy ha módosítani szeretnénk az adatokat, akkor erre ad lehetőséget. 

  0‑17. Adatok Módosítása
  Az adatok csak külön-külön változtathatók meg. A felhasználónév, az e-mail cím, a telefonszám és a jelszó módosítható. A jelszót csak a régi jelszó tudatában tudja módosítani és az új jelszónak és az újra mezőnek egyeznie kell.
  Java alkalmazás használata

