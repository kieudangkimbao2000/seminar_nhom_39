-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th4 13, 2022 lúc 08:41 AM
-- Phiên bản máy phục vụ: 10.4.22-MariaDB
-- Phiên bản PHP: 8.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `rfid_nhom_39`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `diemdanh`
--

CREATE TABLE `diemdanh` (
  `MaSV` char(50) NOT NULL,
  `GioVao` datetime NOT NULL,
  `GioRa` datetime DEFAULT NULL,
  `VaoTre` tinyint(1) NOT NULL,
  `VeSom` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `diemdanh`
--

INSERT INTO `diemdanh` (`MaSV`, `GioVao`, `GioRa`, `VaoTre`, `VeSom`) VALUES
('SV1', '2022-04-11 00:00:00', '2022-04-12 00:00:00', 0, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sinhvien`
--

CREATE TABLE `sinhvien` (
  `MaSV` char(50) NOT NULL,
  `HoTen` varchar(50) NOT NULL,
  `GT` varchar(4) NOT NULL,
  `NgSinh` date NOT NULL,
  `SDT` char(11) DEFAULT NULL,
  `DChi` varchar(200) DEFAULT NULL,
  `Lop` char(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `sinhvien`
--

INSERT INTO `sinhvien` (`MaSV`, `HoTen`, `GT`, `NgSinh`, `SDT`, `DChi`, `Lop`) VALUES
('00B07A142C2B284808000166', 'Nguyen Tri Thong', 'Nu', '2022-04-07', '0342617367', '456 LMN', 'DCT1181'),
('E28011606000020958CDF86E', 'Ly Cay Bong', 'Nam', '2022-04-05', '0342617365', '123 GMH', 'DCT1185'),
('SV1', 'Nguyen Van A', 'Nam', '2022-04-05', '0342617369', '123 abc', 'DCT1183'),
('SV2', 'Nguyen Thi Ban', 'Nu', '2022-04-07', '0342617368', '123 xyz', 'DCT1184');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `diemdanh`
--
ALTER TABLE `diemdanh`
  ADD PRIMARY KEY (`MaSV`,`GioVao`);

--
-- Chỉ mục cho bảng `sinhvien`
--
ALTER TABLE `sinhvien`
  ADD PRIMARY KEY (`MaSV`),
  ADD UNIQUE KEY `SDT` (`SDT`);

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `diemdanh`
--
ALTER TABLE `diemdanh`
  ADD CONSTRAINT `diemdanh_ibfk_1` FOREIGN KEY (`MaSV`) REFERENCES `sinhvien` (`MaSV`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
