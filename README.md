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
Azért választottuk ezt a szakmát, mert régóta érdeklődünk szoftverfejlesztéssel kapcsolatos témák iránt. Vannak olyan kihívások, amik felkeltik a figyelmünket például összetettebb rendszerek készítése és működése. Később szeretnénk ebben a szakmában elhelyezkedni, tevékenykedni vagy továbbtanulni.
Az utóbbi években kis városunkban megnőtt a turizmus és ennek köszönhetően megnőtt a gépjármű bérlésre az igény.
A mi projektünk célja, hogy a hétköznapi emberek számára könnyebbé és egyszerűbbé tegye a gépjármű bérlés lehetőségét, a cégek számára könnyebbé és átláthatóbbá tegye a gépjárművek nyilvántartását és a bérlések kezelését. 
Azért választottuk ezt a témát mivel érdeklődünk a személy-gépkocsik iránt. Korábbi autóbérlésünk során találkoztunk néhány nehézséggel, így szeretnénk megkönnyíteni a városba érkező turisták vagy helyiek, vagy környékbeliek közlekedési problémáit, egyúttal egy vállalkozás munkáját, ami bérbeadással foglalkozik. 
A projekt elkészítése során a következő programozási nyelveket használtuk: Java, PHP, MySQL, HTML, CSS, JavaScript.
 
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
•	XAMPP  v3.3.0
•	PHP v8.1.6
•	Java Runtime Enviroment(JDK) 11.0.14
 
Telepítés menete
Adatbázis hozzáadása
Indítsa el a xampp-ot, ha nincs feltelepítve a számítógépre, akkor telepítse a v3.3.0 verzió számú vagy újabb programot, amely 8.1.6 vagy újabb php-val rendelkezik. A telepítésének folyamata a telepitő állomány megnyitásával kezdődik, majd ha végzett a telepítés indítsa el az Apache és a MySQL szervert. Ez az adatbázis FONTOS a weboldal és a java alkalmazás futtatásához is!
 
0 1. XAMPP CONTROL PANEL V3.3.0
Nyisson meg egy böngészőt (pl.: Google Chrome) és lépjen be a phpMyadmin szerverére a http://localhost/phpmyadmin/ címmel. Hozza létre a következő adatbázist ’gkkdb’ néven, a karakter kódolása utf8_hungarian_ci legyen. 
 
0 2. ADATBÁZIS LÉTREHOZÁSA
Nyissa meg a létrehozott adatbázist (gkkdb) és kattintson az import fülre. Kattintson a fájl kiválasztására, majd válassza ki a GKKAPP/adatbázis mappából a gkkdb.sql fájlt és kattintson az indítás gombra.
 
0 3. ADATBÁZIS IMPORTÁLÁSA
Weboldal telepítés menete:
A weboldal feltelepítése előtt keresse meg a xampp/htdocs nevű mappát, majd törölje annak minden tartalmát, ha ez megtörtént helyezze a GKK WEB mappa tartalmát a törölt fájlok helyére. Írja be a keresőmezőbe a localhost címet és élvezze weboldalunk nyújtotta szolgáltatásokat. 
Java alkalmazás telepítése:
Windows indítás:
Töltse le a git reposytort (https://github.com/Florex001/GKKApp). Majd csomagolja ki egy tetszőleges helyre. Indítsa el a „GKKApp_win.exe” fájlt és próbáljon meg bejelentkezni. Ellenőrizze, hogy elérhető-e az adatbázis, ha nem akkor hajtsa végre az „Adatbázis hozzáadása” című fejezetben leírtak szerint az adatbázis létrehozását. Ha nincs, létrehozva az adatbázis ezt a hibaüzenetet fogja látni:
 
0 4. HIBA ÜZENET JAVA
Arch Linux  indítása:
Töltse le a git reposytort(https://github.com/Florex001/GKKApp). Majd csomagolja ki egy tetszőleges helyre. Indítsa el a „Gkkapp_linux.jar” fájlt és próbáljon meg bejelentkezni. Ellenőrizze, hogy elérhető-e az adatbázis, ha nem akkor hajtsa végre az „Adatbázis hozzáadása” című fejezetben leírtak szerint az adatbázis létrehozását. Ha nincs, létrehozva az adatbázis ezt a hibaüzenetet fogja látni:
 
0 5. HIBA ÜZENET JAVA
Debian Linux indítása:
Töltse le a git reposytort(https://github.com/Florex001/GKKApp). Majd csomagolja ki egy tetszőleges helyre. Majd a projekt mappájában nyisson meg egy terminált. A terminálban írja be a következő parancsot: „java –jar Gkkapp_linux.jar”. Nyomjon egy entert és már el is indul a program, Próbáljon meg bejelentkezni. Ellenőrizze, hogy elérhető-e az adatbázis, ha nem akkor hajtsa végre az „Adatbázis hozzáadása” című fejezetben leírtak szerint az adatbázis létrehozását. Ha nincs, létrehozva az adatbázis ezt a hibaüzenetet fogja látni: 
 
6. HIBA ÜZENET JAVA

Szoftver használata
Web alkalmazás használata
 
0 7. KEZDŐLAP
Általános információk
A weboldalunk olyan szolgáltatást nyújt, amelyen keresztül személygépjárműveket lehet bérelni. Az oldal fejlesztéséhez Google Chrome böngészőt használtunk, de más böngészőkkel is működik. A gépjárművekhez általános leírás is tartozik valamint a bérlés napi díjáról is kap tájékoztatás a felhasználó. A gépjárművek között lehet keresni, erre szolgál a weboldalba beépített keresőmotor. Ezen felül a felhasználó tudja rendezni a gépjárműveket „Ár szerint növekvő” és „Ár szerint csökkenő” sorrendbe a szűrő legördülő menü segítségével. A bérlés megkezdéséhez felhasználói fiókra van szükség. 
Gépjárművek elrendezése
A gépjárművek kártyákban vannak elhelyezve a kezdőlapon. Ezek elrendezése négyzetrács szerűen van megoldva. A kártyák tartalmaznak egy képet a gépjárműről, egy címet, ami a gépjármű neve, egy leírást a gépjárműről és a gépjármű napi árát. Ezek mellett megtalálható egy foglalás nevű gomb is, amely átnavigálja a felhasználót a gépjármű foglalás oldalra, amelyhez bejelentkezés szükséges.
0 8. GÉPJÁRMŰ KÁRTYA
Regisztráció és Belépés
 
0 9. REGISZTRÁCIÓ
Ahhoz hogy a felhasználó regisztrálni tudjon, ki kell választania az egyik gépjárművet majd a foglalás gombra kattintani (Regisztráció vagy Bejelentkezés esetén nem kerül foglalásra az adott gépjármű!). Majd át navigálja a weboldal a felhasználót hogy „A tartalom megtekintéséhez bejelentkezés szükséges”. Ha rendelkezik felhasználói fiókkal jelentkezzen be. Ha nincs még felhasználói fiókja, akkor kattintson a „Nincs még felhasználói fiókod? Regisztráció” Regisztráció linkre. Ha rákattintott, akkor az oldal automatikusan átnavigálja a regisztráció oldalra, ahol minden adat megadása kötelező.
 
0 10. BEJELENTKEZÉS
A bejelentkezésnél Felhasználónév és Jelszó megadása kötelező. Ha sikeres a bejelentkezés egy sütit fogunk eltárolni a böngészőjében. Ha sikeres a bejelentkezés, akkor a kiválasztott gépjárműnek a foglalás oldala fog megjelenni. Illetve a navigációs sávban megjelenik a kijelentkezés gomb, amire ha rá kattintunk, akkor törlődik az eltárolt süti és új bejelentkezésre lesz szüksége és még megjelenik a foglalásaim fül is ahol a felhasználó meg tudja tekinteni a gépjármű foglalásait. Profil oldal is elérhetővé vállik onnan ismerhető fel, hogy a felhasználónév lesz a neve annak a menünek.
Gépjármű foglalása
 
0 11. GÉPJÁRMŰ FOGLALÁS
Ha sikeresen bejelentkezett, akkor foglalás oldal fog megjelenni a kiválasztott autójával. Ha biztos benne hogy azt az autót szeretné lefoglalni, akkor töltse ki az alábbi mezőket valós adatokkal:
 
0 12. ADATOK MEGADÁSA FOGLALÁSHOZ
Ha biztos benne hogy arra az időpontra szeretné kibérelni az adott gépjárművet és a vezetői engedélyszáma valós, akkor kattintson a Foglalás gombra. Ha sikeres a foglalás, akkor át navigálja a foglalásaim oldalra. Ha nem megfelelő a foglalás, akkor hiba üzenetet fog kapni: Például.: 

Foglalásaim oldal
 
0 14. FOGLALÁSAIM MENÜPONT
A foglalásaim menüpontra kattintva, vagy ha éppen foglalt egy gépjárművet és átnavigálásra került, akkor a saját foglalásait fogja látni. A foglalását csak a gépjármű átadásának idejéig fogja látni. Ha már a gépjárművet használja, amit bérel, abban az esetben már nem fogja látni foglalását. Fizetés gépjármű átadása előtt történik a telephelyen készpénzzel vagy bankkártyával. Amennyiben módosítani akarja foglalását akkor a navigációs menüben kattintson az Elérhetőségek menüpontra. Azt fogja tapasztalni, hogy az oldal aljára, fogja navigálni ahol, megjelennek a céggel kapcsolatos elérhetőségek. Módosításhoz hívja fel az ott található telefonszámot vagy írjon emailt. Foglalás lemondására az oldalon van lehetőség. 
 
0 15.FOGLALÁS MEGJELENÉSE
A saját foglalása a 0-15.Foglalás megjelenése ábrának megfelelően fog megjelenni. Ebben látni fogja a foglalás azonosítóját, amit jegyezzen meg, mert fontos a gépjármű bérlése során. Ezen kívül megjelenik még a gépjármű neve, Foglalás kezdete, Foglalás vége illetve a teljes ár. A teljes ár a két megadott dátum közötti napok száma szorozva napi árral. A Foglalás lemondása gomb is itt jelenik meg, ha le akarja mondani a foglalását nincs más dolga csak rá kattintani.
Profil oldal
 
0 16. PROFIL OLDAL
A profil oldalon megjelenik egy profilkép, a felhasználónév, a felhasználó vezetékneve és keresztneve, az e-mail címe illetve a regisztráció időpontja. Láthatunk egy módosítás gombot is amely arra a célra szolgál, hogy ha módosítani szeretnénk az adatokat, akkor erre ad lehetőséget. 
 
0 17. ADATOK MÓDOSÍTÁSA
Az adatok csak külön-külön változtathatók meg. A felhasználónév, az e-mail cím, a telefonszám és a jelszó módosítható. A jelszót csak a régi jelszó tudatában tudja módosítani és az új jelszónak és az újra mezőnek egyeznie kell. Ha a vezetékneve és keresztneve változott vegye fel velünk a kapcsolatot e-mail címen.
Java alkalmazás használata
Általános Információk
A Java alkalmazásban a dolgozók a foglalásokkal és a gépjárművekkel kapcsolatos információkat tudják kezelni. Ha a felhasználó jogosultsága admin akkor megtekinthető számára az admin felület is. Ezen felül a dolgozó és az admin számára is megtekinthető az céginformációk, üzenet küldés és a saját profiljának adatai.
Bejelentkezés
A bejelentkezéshez már egy létező felhasználó szükséges aminek „worker” vagy „admin” jogosultsága van. Írja be a felhasználónevét és jelszavát majd kattintson a Bejelentkezés gombra. Ha nincs megfelelő jogosultsága kérjen meg egy dolgozót, hogy vegye fel a kapcsolatot az adminisztrátorral. 
 
18. BEJELENTKEZÉS
Főoldal
A főoldalon megtalálható a felhasználónév, Foglalások, Gépjárművek, Cégről, Üzenet küldés, Profil és egy Kijelentkezés gomb. 
 
19. FŐOLDAL
 
Foglalások
A foglalások menüpontban megtalálható az összes foglalás, a foglalásokról az összes adat, egy Vissza a főoldalra egy gomb. Ezek között megfigyelhető egy táblázat is ahol megjelenik az összes foglalás. Illetve a foglalások között tud keresni is, továbbá tud foglalást törölni, módosítani és hozzáadni. Tudja a kiválasztott foglalás státuszát is módosítani a Gépjármű átadva gombbal.
 
20. FOGLALÁSOK OLDAL
A státuszát akkor kell módosítani, hogyha átadásra került a bérlő számára a gépjármű. Ezek után az elvitt menüpontba át fog kerülni a kiválasztott foglalás. A Sikeres foglalás gombra akkor kattintson, ha az adott gépjármű foglalása végbe ment. 
 
21. ELVITT MENÜPONT
 
A Teljesített menüponton megtalálható az összes végbement foglalás és annak összes adata. 
 
22. TELJESÍTETT MENÜPONT
Gépjárművek
A gépjárművek oldalon megtalálható egy táblázatban az összes gépjármű amelyek között lehet keresni és a kiválasztott gépjárművet lehet módosítani vagy törölni. Módosításnál csak a gépjármű nevét, információját, és napi árát lehet módosítani.
 
23. GÉPJÁRMŰVEK OLDAL
Ahhoz hogy gépjárművet tudjon hozzáadni, írja be először a gépjármű nevét, információját és a napi árát a beviteli mezőkbe majd kattintson a Hozzáadás gombra. Ezután egy fájlkezelő ablak fog megnyílni ahol kiválaszthatja, a gépjárműről a képet majd kattintson a Megnyitás gomra. Fog kapni egy felugró üzenet, ha sikeres a hozzáadás.
 
24. FÁJLKEZELŐ
Cégről
A Cégről gombra kattintva egy felugró ablak jelenik meg, amelyben megtalálható a cégről az összes információ, mint például Regisztrációs szám, Cégnév, Székhely és Adószám.
 
25. CÉGINFORMÁCIÓK
Üzenet küldés
Az üzenet küldés oldalon írhat üzenetet az adminisztrátornak, hogyha valamelyik felhasználónak jogosultságot kell adni vagy elvenni illetve különböző profilmódosításokat lehet kérvényezni. Ha megírta az üzenetét akkor kattintson a Küldés gomra. Ezeket az adminisztrátor el tudja fogadni vagy el tudja utasítani az üzentet. Az elfogadásról vagy az elutasításról nem fog értesítést kapni.
 
26. ÜZENET KÜLDÉS ABLAK
Profil
A profil oldalon meg tudja tekinteni az összes felhasználói adatát, mint például felhasználónév, e-mail cím, telefonszám, nevét, regisztráció dátumát illetve jogosultságát. Ha rákattint a Jelszó megváltoztatása gombra megjelenik egy beviteli mező, amelybe beírhatja új jelszavát, ha beírásra került kattintson az OK! gombra. 
 
27. PROFIL OLDAL
Abban az esetben, ha jogosultsága admin akkor az oldalon a Kijelentkezés gomb alatt megjelenik egy Admin gomb, amely átirányít az alkalmazás adminisztrátori felületére. 
 
Az adminisztrátori felület hasonlít a Főoldalhoz . Itt megtekinthetőek az Értesítések, 
Felhasználók és egy Kilépés gomb. 
 
28. ÉRTESÍTÉSEK
Az Értesítések fülön megtekinthetők azok az üzenetek amiket a dolgozók írnak. Az Értesítéseknél olyan üzeneteket fogadhatnak el amelyek felhasználó módosítással kapcsolatosak(Jelszót az adminisztrátor se tud változtatni). Amely kérés nem felel meg ennek azt az adminisztrátornak el kell utasítania. Az Üzenetek fülön kívül megtalálható egy Elfogadva fül ahol az elfogadott üzenetek találhatóak és egy Elutasítva fül ahol az elutasított üzenetek találhatóak.
 
29. FELHASZNÁLÓK
A Felhasználók fülön egy táblázatban fel van sorolva az összes felhasználó. A táblázat alatt  egy kereső mező amelyben kereshet felhasználónév, név vagy email alapján a felhasználók között .A kereső mező alatt vannak beviteli mezők. Itt tudja beírni a kívánt adatokat. A beviteli mezők alatt található egy Törlés gomb, amely a táblázatban kiválasztott felhasználót tudja törölni, egy Módosítás gomb, amivel a táblázatban kiválasztott felhasználó adatait tudja módosítani kivéve az azonosítót, a jelszót és a regisztrációs idejét. Felhasználót is lehet létrehozni , ezt a Hozzáadás gombbal tudja megvalósítani miután az összes beviteli mező kitöltésre került kivéve az azonosítót és a regisztrációs időt ugyanis ezeket a rendszer automatikusan tölti ki. A Kilépés menüpont vissza lépteti a Főoldara. Kijelentkezni a felhasználójából a Főoldalon a Kijelentkezés menüpont segítségével tud kilépni a fiókjából vagy Profil menüpontban a jobb felső sarokban a Kijelentkezés gombra kattint. Ez vissza irányítja a bejelentkezés felületre.
 
Fejlesztői dokumentáció
Az alkalmazott fejlesztői eszközök
Trello projektkezelő
A csapatunk a Trello nevű projektkezelőt használta. A Trello megkönnyíti a projektek és feladatok kezelését a csapatok számára. A Trello olyan vizuális eszköz, amely segítette a csapatunk projektjének munkafolyamatát és segített a feladatkövetés kezelésében. 
 
30.TRELLO IKON
Git verziókezelő
A Git egy ingyenes és nyílt forráskódú elosztott verziókezelő rendszer, amely a kicsitől, a nagyon nagy projektig mindent gyorsan és hatékonyan kezel. A csapatunk a Github rendszerét választotta. Alapvetően is ezt a verziókezelőt használtuk volna. Saját funkcióin felül a Git elosztott verziókövetését és forráskódkezelését (SCM) teszi elérhetővé. Hozzáférés-kezelést és számos együttműködési funkciót nyújt, mint például bug követés, szolgáltatáslekérés, feladatkezelés, valamint wikiket minden projekthez.
 
31. GITHUB
XAMPP
Azért a XAMPP adatbázis kezelőt választottuk, mert a PHP weboldalunknál nagy segítséget nyújtott ennek a használata. Ez egy szabad és nyílt forrású platformfüggetlen webszerver-szoftvercsomag, amelynek legfőbb alkotóelemei az Apache webszerver, valamint a PHP programozási nyelv értelmezője.
 
32. XAMPP
Microsoft Visual Studio Code
Azért a Visual Studio Code-ot használtuk, mert ezt találtuk a legalkalmasabbnak mivel, sokféle programnyelvet és a Git verzió kezelését is támogatja. Ezen kívül számtalan hasznos bővítmény elérhető hozzá. A Visual Studio Code ingyenes, nyílt forráskódú kódszerkesztő, melyet a Microsoft fejleszt Windows, Linux és OS X operációs rendszerekhez. Támogatja a hibakeresőket, továbbá képes az intelligens kódkiegészítésre az IntelliSense segítségével.
 
33. MICROSOFT VISUAL STUDIO CODE
 
IntelliJ IDEA
Azért az IntelliJ IDEA-t használtuk, mert ez nyújtja a legnagyobb segítséget a JAVA alkalmazás fejlesztése közben. Az alkalmazást 11.0.14-es verzió számú JDK-val fejlesztettük. Az IntelliJ IDEA egy intelligens, a Java programkód írására alkalmas IDE a Java és más JVM-nyelvek, például a Kotlin, a Scala és a Groovy használatához mindenféle alkalmazáson. Az IntelliJ IDEA bővíthető a JetBrains által fejlesztett ingyenes bővítményekkel, amelyek lehetővé teszik más programozási nyelvekkel való együttműködést. Az IDE (Integrált fejlesztői környezet), olyan szoftver alkalmazás, amely átfogó létesítményeket biztosít a számítógépes programozók számára a szoftverfejlesztéshez.
 
34. INTELLIJ IDEA
Microsoft Office Word
A dokumentáció megírásához a Microsoft Office csomagban található Microsoft Word-öt használtuk. A megbízható Word alkalmazás segítségével gyorsan és egyszerűen létrehozhatja, szerkesztheti, megtekintheti és megoszthatja fájljait másokkal.
 
35. MICROSOFT OFFICE WORD
