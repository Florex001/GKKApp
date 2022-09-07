-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2022. Sze 07. 19:19
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
-- Tábla szerkezet ehhez a táblához `bookings`
--

CREATE TABLE `bookings` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `borrowed_vehicle_id` int(11) NOT NULL,
  `borrow_start` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL,
  `borrow_end` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL,
  `indenty_card_number` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL,
  `price` int(11) NOT NULL,
  `status` varchar(255) COLLATE utf8mb4_hungarian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_hungarian_ci;

--
-- A tábla adatainak kiíratása `bookings`
--

INSERT INTO `bookings` (`id`, `user_id`, `borrowed_vehicle_id`, `borrow_start`, `borrow_end`, `indenty_card_number`, `price`, `status`) VALUES
(1, 1, 1, '20220901', '20220907', '123456ci', 60000, 'Foglalva'),
(3, 2, 2, '20220811', '20220904', '14456ci', 100000, 'tartos_berlet'),
(4, 3, 3, '20220522', '20220529', '21456ci', 40000, 'ceges_berlet');

--
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `bookings`
--
ALTER TABLE `bookings`
  ADD PRIMARY KEY (`id`);

--
-- A kiírt táblák AUTO_INCREMENT értéke
--

--
-- AUTO_INCREMENT a táblához `bookings`
--
ALTER TABLE `bookings`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
