# Bevezetés

Azért választottuk ezt a szakmát, mert régóta érdeklődünk szoftverfejlesztéssel kapcsolatos témák iránt. Vannak olyan kihívások, amik felkeltik a figyelmünket például összetettebb rendszerek készítése és működése. Később szeretnénk ebben a szakmában elhelyezkedni, tevékenykedni vagy továbbtanulni.

Az utóbbi években kis városunkban megnőtt a turizmus és ennek köszönhetően megnőtt a gépjármű bérlésre az igény.

A mi projektünk célja, hogy a hétköznapi emberek számára könnyebbé és egyszerűbbé tegye a gépjármű bérlés lehetőségét, a cégek számára könnyebbé és átláthatóbbá tegye a gépjárművek nyilvántartását és a bérlések kezelését.

Azért választottuk ezt a témát mivel érdeklődünk a személy-gépkocsik iránt. Korábbi autóbérlésünk során találkoztunk néhány nehézséggel, így szeretnénk megkönnyíteni a városba érkező turisták vagy helyiek, vagy környékbeliek közlekedési problémáit, egyúttal egy vállalkozás munkáját, ami bérbeadással foglalkozik.

A projekt elkészítése során a következő programozási nyelveket használtuk: Java, PHP, MySQL, HTML, CSS, JavaScript.

  

# Felhasználói dokumentáció

## Rendszerkövetelmény

### Hardver/követelmények:

Minimális rendszerkövetelmény:

            **Operációs rendszer:** Windows 10/Linux/MacOS

            **Processzor:** 1 GHz vagy gyorsabb

            **RAM:** 1 GB

            **Merevlemez**\-**terület:** 1 GB

            **Videókártya:** A DirectX 9-es vagy újabb verziójával kompatibilis.

            **Kijelző:** 800x600

Ajánlott rendszerkövetelmény:

            **Operációs rendszer:** Windows 10/Linux/MacOS

            **Processzor:** 2 GHz vagy gyorsabb

            **RAM:** 2 GB

            **Merevlemez-terület:** 1 GB

            **Videókártya:** A DirectX 9-es vagy újabb verziójával kompatibilis.

            **Kijelző:** 1280x1024

### Szoftver/követelmények

·         XAMPP  v3.3.0

·         PHP v8.1.6

·         Java Runtime Enviroment(JDK) 11.0.14

  

## Telepítés menete

### Adatbázis hozzáadása

Indítsa el a xampp-ot, ha nincs feltelepítve a számítógépre, akkor telepítse a v3.3.0 verzió számú vagy újabb programot, amely 8.1.6 vagy újabb php-val rendelkezik. A telepítésének folyamata a telepitő állomány megnyitásával kezdődik, majd ha végzett a telepítés indítsa el az Apache és a MySQL szervert. Ez az adatbázis FONTOS a weboldal és a java alkalmazás futtatásához is!

![](vizsgaremekDokumentáció_files/image002.jpg)

0‑1. XAMPP Control Panel v3.3.0

Nyisson meg egy böngészőt (pl.: Google Chrome) és lépjen be a phpMyadmin szerverére a [http://localhost/phpmyadmin/](http://localhost/phpmyadmin/) címmel. Hozza létre a következő adatbázist ’**_gkkdb_**’ néven, a karakter kódolása utf8\_hungarian\_ci legyen.

![](vizsgaremekDokumentáció_files/image003.png)

0‑2. Adatbázis létrehozása

Nyissa meg a létrehozott adatbázist (gkkdb) és kattintson az **_import_** fülre. Kattintson a fájl kiválasztására, majd válassza ki a _GKKAPP/adatbázis_ mappából a _gkkdb.sql_ fájlt és kattintson az **indítás** gombra.

![](vizsgaremekDokumentáció_files/image004.jpg)

0‑3. Adatbázis importálása

### Weboldal telepítés menete:

A weboldal feltelepítése előtt keresse meg a _xampp/htdocs_ nevű mappát, majd törölje annak minden tartalmát, ha ez megtörtént helyezze a _GKK WEB_ mappa tartalmát a törölt fájlok helyére. Írja be a keresőmezőbe a _localhost_ címet és élvezze weboldalunk nyújtotta szolgáltatásokat.

### Java alkalmazás telepítése:

#### Windows indítás:

Töltse le a git reposytort ([https://github.com/Florex001/GKKApp](https://github.com/Florex001/GKKApp)). Majd csomagolja ki egy tetszőleges helyre. Indítsa el a „**_GKKApp\_win.exe_**” fájlt és próbáljon meg bejelentkezni. Ellenőrizze, hogy elérhető-e az adatbázis, ha nem akkor hajtsa végre az „Adatbázis hozzáadása” című fejezetben leírtak szerint az adatbázis létrehozását. Ha nincs, létrehozva az adatbázis ezt a hibaüzenetet fogja látni:

![](vizsgaremekDokumentáció_files/image005.png)

0‑4. Hiba üzenet Java

#### Arch Linux  indítása:

Töltse le a git reposytort([https://github.com/Florex001/GKKApp](https://github.com/Florex001/GKKApp)). Majd csomagolja ki egy tetszőleges helyre. Indítsa el a „**_Gkkapp\_linux.jar_**” fájlt és próbáljon meg bejelentkezni. Ellenőrizze, hogy elérhető-e az adatbázis, ha nem akkor hajtsa végre az „Adatbázis hozzáadása” című fejezetben leírtak szerint az adatbázis létrehozását. Ha nincs, létrehozva az adatbázis ezt a hibaüzenetet fogja látni:

![](vizsgaremekDokumentáció_files/image005.png)

0‑5. Hiba üzenet Java

#### Debian Linux indítása:

Töltse le a git reposytort([https://github.com/Florex001/GKKApp](https://github.com/Florex001/GKKApp)). Majd csomagolja ki egy tetszőleges helyre. Majd a projekt mappájában nyisson meg egy terminált. A terminálban írja be a következő parancsot: „_java –jar Gkkapp\_linux.jar”_. Nyomjon egy entert és már el is indul a program, Próbáljon meg bejelentkezni. Ellenőrizze, hogy elérhető-e az adatbázis, ha nem akkor hajtsa végre az „Adatbázis hozzáadása” című fejezetben leírtak szerint az adatbázis létrehozását. Ha nincs, létrehozva az adatbázis ezt a hibaüzenetet fogja látni:

![](vizsgaremekDokumentáció_files/image005.png)

6. Hiba üzenet Java
