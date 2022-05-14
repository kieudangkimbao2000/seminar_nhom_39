-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th5 14, 2022 lúc 04:51 PM
-- Phiên bản máy phục vụ: 10.4.24-MariaDB
-- Phiên bản PHP: 7.4.29

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
-- Cấu trúc bảng cho bảng `botri`
--

CREATE TABLE `botri` (
  `MaLH` char(20) NOT NULL,
  `MaPH` char(20) NOT NULL,
  `MaTH` int(11) NOT NULL,
  `Ngay` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `botri`
--

INSERT INTO `botri` (`MaLH`, `MaPH`, `MaTH`, `Ngay`) VALUES
('SCD1', 'C.C107', 6, '2022-05-14'),
('SCD1', 'C.C107', 7, '2022-05-14');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `diemdanh`
--

CREATE TABLE `diemdanh` (
  `MaSV` char(50) NOT NULL,
  `MaLH` char(20) NOT NULL,
  `GioVao` datetime NOT NULL,
  `GioRa` datetime NOT NULL,
  `VaoTre` tinyint(1) NOT NULL,
  `VeSom` tinyint(1) NOT NULL,
  `Vang` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hoc`
--

CREATE TABLE `hoc` (
  `MaSV` char(50) NOT NULL,
  `MaLH` char(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `hoc`
--

INSERT INTO `hoc` (`MaSV`, `MaLH`) VALUES
('00B0 7A14 2C2B 2848 0800 0166', 'SCD1'),
('300F 4F57 3AD0 01C0 8369 A241', 'SCD1'),
('300F 4F57 3AD0 01C0 8369 A245', 'SCD1'),
('E280 1160 6000 0209 58CD F86E', 'SCD1');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `lophoc`
--

CREATE TABLE `lophoc` (
  `MaLH` char(20) NOT NULL,
  `MaMH` char(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `lophoc`
--

INSERT INTO `lophoc` (`MaLH`, `MaMH`) VALUES
('CCNLTHD1', '841072'),
('CCNLTHD2', '841072'),
('CCNLTHD3', '841072'),
('CCNLTHD4', '841072'),
('CCNLTHD5', '841072'),
('SCD1', '841073'),
('SCD2', '841073'),
('SCD3', '841073'),
('SCD4', '841073'),
('SCD5', '841073');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `monhoc`
--

CREATE TABLE `monhoc` (
  `MaMH` char(20) NOT NULL,
  `TenMH` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `monhoc`
--

INSERT INTO `monhoc` (`MaMH`, `TenMH`) VALUES
('841072', 'Các công nghệ lập trình hiện đại'),
('841073', 'Seminar chuyên đề');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phonghoc`
--

CREATE TABLE `phonghoc` (
  `MaPH` char(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `phonghoc`
--

INSERT INTO `phonghoc` (`MaPH`) VALUES
('C.A503'),
('C.B004'),
('C.C107');

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
  `DChi` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `sinhvien`
--

INSERT INTO `sinhvien` (`MaSV`, `HoTen`, `GT`, `NgSinh`, `SDT`, `DChi`) VALUES
('00B0 7A14 2C2B 2848 0800 0166', 'Nguyen Thi Ban', 'Nu', '2022-04-07', NULL, NULL),
('300F 4F57 3AD0 01C0 8369 A241', 'Huynh Tu Ba', 'Nam', '2022-04-06', NULL, NULL),
('300F 4F57 3AD0 01C0 8369 A245', 'Nguyen Tri Thuc', 'Nu', '2022-05-05', NULL, NULL),
('E280 1160 6000 0209 58CD F86E', 'Nguyen Van A', 'Nam', '2022-04-05', NULL, NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tiethoc`
--

CREATE TABLE `tiethoc` (
  `MaTH` int(11) NOT NULL,
  `Buoi` varchar(10) NOT NULL,
  `TGBD` time NOT NULL,
  `TGKT` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `tiethoc`
--

INSERT INTO `tiethoc` (`MaTH`, `Buoi`, `TGBD`, `TGKT`) VALUES
(1, 'Sang', '07:00:00', '07:50:00'),
(2, 'Sang', '07:50:00', '08:40:00'),
(3, 'Sang', '09:00:00', '09:50:00'),
(4, 'Sang', '09:50:00', '10:40:00'),
(5, 'Sang', '10:40:00', '11:30:00'),
(6, 'Chieu', '13:00:00', '13:50:00'),
(7, 'Chieu', '13:50:00', '14:40:00'),
(8, 'Chieu', '15:00:00', '15:50:00'),
(9, 'Chieu', '15:50:00', '16:40:00'),
(10, 'Chieu', '16:40:00', '17:30:00');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `botri`
--
ALTER TABLE `botri`
  ADD PRIMARY KEY (`MaLH`,`MaPH`,`MaTH`),
  ADD KEY `botri_Fk_MaPH_MaPH` (`MaPH`),
  ADD KEY `botri_MaTH_MaTH` (`MaTH`);

--
-- Chỉ mục cho bảng `diemdanh`
--
ALTER TABLE `diemdanh`
  ADD PRIMARY KEY (`MaSV`,`MaLH`,`GioVao`);

--
-- Chỉ mục cho bảng `hoc`
--
ALTER TABLE `hoc`
  ADD PRIMARY KEY (`MaSV`,`MaLH`),
  ADD KEY `hoc_Fk_MaLH_MaLH` (`MaLH`);

--
-- Chỉ mục cho bảng `lophoc`
--
ALTER TABLE `lophoc`
  ADD PRIMARY KEY (`MaLH`),
  ADD KEY `lophoc_Fk_MaMH_MaMH` (`MaMH`);

--
-- Chỉ mục cho bảng `monhoc`
--
ALTER TABLE `monhoc`
  ADD PRIMARY KEY (`MaMH`);

--
-- Chỉ mục cho bảng `phonghoc`
--
ALTER TABLE `phonghoc`
  ADD PRIMARY KEY (`MaPH`);

--
-- Chỉ mục cho bảng `sinhvien`
--
ALTER TABLE `sinhvien`
  ADD PRIMARY KEY (`MaSV`);

--
-- Chỉ mục cho bảng `tiethoc`
--
ALTER TABLE `tiethoc`
  ADD PRIMARY KEY (`MaTH`);

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `botri`
--
ALTER TABLE `botri`
  ADD CONSTRAINT `botri_Fk_MaLH_MaLH` FOREIGN KEY (`MaLH`) REFERENCES `lophoc` (`MaLH`),
  ADD CONSTRAINT `botri_Fk_MaPH_MaPH` FOREIGN KEY (`MaPH`) REFERENCES `phonghoc` (`MaPH`),
  ADD CONSTRAINT `botri_MaTH_MaTH` FOREIGN KEY (`MaTH`) REFERENCES `tiethoc` (`MaTH`);

--
-- Các ràng buộc cho bảng `diemdanh`
--
ALTER TABLE `diemdanh`
  ADD CONSTRAINT `diemdanh_Fk_MaSV_MaLH_MaSV_MaLH` FOREIGN KEY (`MaSV`,`MaLH`) REFERENCES `hoc` (`MaSV`, `MaLH`);

--
-- Các ràng buộc cho bảng `hoc`
--
ALTER TABLE `hoc`
  ADD CONSTRAINT `hoc_Fk_MaLH_MaLH` FOREIGN KEY (`MaLH`) REFERENCES `lophoc` (`MaLH`),
  ADD CONSTRAINT `hoc_Fk_MaSV_MaSV` FOREIGN KEY (`MaSV`) REFERENCES `sinhvien` (`MaSV`);

--
-- Các ràng buộc cho bảng `lophoc`
--
ALTER TABLE `lophoc`
  ADD CONSTRAINT `lophoc_Fk_MaMH_MaMH` FOREIGN KEY (`MaMH`) REFERENCES `monhoc` (`MaMH`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
