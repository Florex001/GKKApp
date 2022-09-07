-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2022. Sze 07. 20:41
-- Kiszolgáló verziója: 10.4.14-MariaDB
-- PHP verzió: 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Adatbázis: `reservations`
--

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `vehicles`
--

CREATE TABLE `vehicles` (
  `id` int(255) NOT NULL,
  `car` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL,
  `category` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL,
  `info` text COLLATE utf8mb4_hungarian_ci NOT NULL,
  `daily_price` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_hungarian_ci;

--
-- A tábla adatainak kiíratása `vehicles`
--

INSERT INTO `vehicles` (`id`, `car`, `category`, `info`, `daily_price`) VALUES
(2, 'Honda Civic', 'sz.gepjarmu', '1.6 tuti valami csicska szívó\r\n5 ajtós\r\n450 literes csomagtartó\r\ndigit klimax\r\nnavi\r\nblutusz fejegység\r\nkájhacső', 5000),
(3, 'Fiat dugo', 'lako.auto', 'nagy tágas \r\nbudival ellátott\r\nklimax\r\ncsend és alvás\r\nstb', 30000),
(4, 'Ford F-Max', 'teher.auto', '160000 ccm v28 \r\n1120 le 500nm\r\náru szállításra \r\ntágas\r\nszabvány méret\r\n\r\n', 600000);

--
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `vehicles`
--
ALTER TABLE `vehicles`
  ADD PRIMARY KEY (`id`);

--
-- A kiírt táblák AUTO_INCREMENT értéke
--

--
-- AUTO_INCREMENT a táblához `vehicles`
--
ALTER TABLE `vehicles`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
