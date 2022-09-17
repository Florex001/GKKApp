-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2022. Sze 17. 16:18
-- Kiszolgáló verziója: 10.4.24-MariaDB
-- PHP verzió: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Adatbázis: `gkkdb`
--

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `bookings`
--

CREATE TABLE `bookings` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `borrowed_vehicle_id` int(11) NOT NULL,
  `borrow_start` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL,
  `borrow_end` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL,
  `indenty_card_number` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL,
  `price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_hungarian_ci;

--
-- A tábla adatainak kiíratása `bookings`
--

INSERT INTO `bookings` (`id`, `user_id`, `borrowed_vehicle_id`, `borrow_start`, `borrow_end`, `indenty_card_number`, `price`) VALUES
(8, 4, 2, '20221208', '20221208', 'sadfdsdf', 231111),
(9, 4, 3, '202218706', '202218706', '202218706', 2323131);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `user`
--

CREATE TABLE `user` (
  `id` int(255) NOT NULL,
  `username` varchar(255) COLLATE utf8_hungarian_ci NOT NULL,
  `first_name` varchar(255) COLLATE utf8_hungarian_ci NOT NULL,
  `last_name` varchar(255) COLLATE utf8_hungarian_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_hungarian_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_hungarian_ci NOT NULL,
  `phone_number` varchar(15) COLLATE utf8_hungarian_ci NOT NULL,
  `registration_date` varchar(11) COLLATE utf8_hungarian_ci NOT NULL,
  `rank` varchar(255) COLLATE utf8_hungarian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `user`
--

INSERT INTO `user` (`id`, `username`, `first_name`, `last_name`, `password`, `email`, `phone_number`, `registration_date`, `rank`) VALUES
(1, 'admin', 'admin', 'admin', 'admin', 'admin@gkk.app.hu', '+36709312755', '2022-09-17', 'admin'),
(2, 'worker', 'worker', 'worker', 'worker', 'worker@gkk.app.hu', '+36709312755', '2022-09-17', 'worker'),
(3, 'user', 'user', 'user', 'user', 'user@gkk.app.hu', '+36709312755', '2022-09-17', 'user'),
(4, 'alkalmazasbol', 'alkalmazasbol', 'alkalmazasbol', 'alkalmazasbol', 'alkalmazasbol@gkk.app.hu', '+36709312755', '2022-09-17', 'alkalmazasbol');

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
-- A tábla indexei `bookings`
--
ALTER TABLE `bookings`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `vehicles`
--
ALTER TABLE `vehicles`
  ADD PRIMARY KEY (`id`);

--
-- A kiírt táblák AUTO_INCREMENT értéke
--

--
-- AUTO_INCREMENT a táblához `bookings`
--
ALTER TABLE `bookings`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT a táblához `user`
--
ALTER TABLE `user`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT a táblához `vehicles`
--
ALTER TABLE `vehicles`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
