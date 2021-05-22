USE master
GO

CREATE DATABASE KhachSan
GO

-- Drop DATABASE KhachSan

USE KhachSan
GO

CREATE TABLE KhachHang
(
	MaKH INT IDENTITY PRIMARY KEY,
	TenKH NVARCHAR(100) NOT NULL,
	CMND VARCHAR(50) NOT NULL,
	NgayHetHan DATETIME NOT NULL,
	LoaiKH NVARCHAR(100),
	SoLanDatPhong INT CHECK(SoLanDatPhong >= 0) DEFAULT(0)
)
GO

CREATE TABLE DichVu
(
	MaDV int identity PRIMARY KEY,
	TenDV NVARCHAR(100) NOT NULL,
	DonGia DECIMAL CHECK(DonGia >= 0) DEFAULT(0)
)
GO

CREATE TABLE LoaiPhong
(
	MaLoaiPhong int identity PRIMARY KEY,
	TenLoaiPhong NVARCHAR(100) NOT NULL,
	DonGia DECIMAL CHECK(DonGia >= 0) DEFAULT(0)
)
GO

CREATE TABLE Phong
(
	MaPhong NVARCHAR(100) PRIMARY KEY,
	SucChua INT CHECK(SucChua > 0) DEFAULT(1),
	SoGiuong INT CHECK(SoGiuong > 0) DEFAULT(1),
	ViTri NVARCHAR(100),
	-- 0. trống | 1. đã đặt | 2.có người ở
	TinhTrang INT,
	MaLoaiPhong INT REFERENCES LoaiPhong(MaLoaiPhong)
)
GO

CREATE TABLE HoaDonDV
(
	MaHDDV INT IDENTITY PRIMARY KEY,
	MaKH INT NOT NULL REFERENCES KhachHang(MaKH),
	-- 0. chưa thanh toán | 1. đã thanh toán
	TinhTrang INT,
	NgayGioLap DATETIME DEFAULT(GETDATE())
)
GO


CREATE TABLE ChiTietDV
(
	MaHDDV int REFERENCES HoaDonDV(MaHDDV),
	MaDV int REFERENCES DichVu(MaDV),
	SoLuong INT CHECK(Soluong >= 1) DEFAULT(1),
	NgayGioDat DATETIME DEFAULT(GETDATE())
)
GO

CREATE TABLE HoaDonPhong
(
	MaHD int identity PRIMARY KEY,
	MaKH int REFERENCES KhachHang(MaKH),
	MaPhong Nvarchar(100) REFERENCES Phong(MaPhong),
	-- 0. đã đặt | 1. đã nhận | 2. đã trả
	TinhTrang int,
	NgayGioNhan DATETIME NOT NULL DEFAULT(GETDATE()),
	NgayGioTra DATETIME
)
GO

INSERT INTO dbo.KhachHang
	(tenKH, cmnd, ngayHetHan, loaiKH, soLanDatPhong)
VALUES
	(N'Chí Phèo', N'123123123', '20230101', N'Việt Nam', 0),
	(N'Xuân Tóc Đỏ', N'123123123', '20220501', N'Việt Nam', 0),
	(N'Lão Hạc', N'123123123', '20230106', N'Việt Nam', 1),
	(N'John Wick', N'123123123', '20240513', N'Nước ngoài', 0),
	(N'Tony Stark', N'123123123', '20240206', N'Nước ngoài', 1)
GO

-- Insert rows into table 'dbo.DichVu'
INSERT INTO dbo.DichVu
	(tenDV, donGia)
VALUES
	(N'Gửi xe', 5000),
	(N'Rửa xe', 30000),
	(N'Thức ăn tại phòng', 30000),
	(N'Giặt, ủi là', 20000),
	(N'Xe đưa đón sân bay', 100000),
	(N'Cho thuê xe tự lái', 120000),
	(N'Trông trẻ', 30000),
	(N'Chăm sóc thú cưng', 50000),
	(N'Spa', 300000),
	(N'Đánh golf, tennis', 200000)
GO

INSERT INTO dbo.HoaDonDV
	(maKH, ngayGioLap, TinhTrang)
VALUES
	(1, '2021-05-19', 1),
	(2, '2021-05-15', 1),
	(3, '2021-05-19', 0)

INSERT INTO dbo.ChiTietDV
	(MaHDDV, MaDV, SoLuong, NgayGioDat)
VALUES
	(1, 1, 1, '2021-05-16'),
	(1, 2, 2, '2021-05-16'),
	(1, 4, 2, '2021-05-16'),
	(2, 4, 1, '2021-05-02'),
	(2, 5, 1, '2021-05-03'),
	(2, 6, 2, '2021-05-05'),
	(3, 8, 2, '2021-05-18'),
	(3, 10, 2, '2021-05-19')
GO

INSERT INTO dbo.LoaiPhong
	(TenLoaiPhong, donGia)
VALUES
	(N'Phòng đơn', 20000),
	(N'Phòng đôi', 50000)
GO

INSERT INTO dbo.Phong
	(MaPhong, SoGiuong, SucChua, TinhTrang, ViTri, MaLoaiPhong)
VALUES
	(N'P101',1, 2, 0, N'Tầng 1', 1),
	(N'P102',1, 2, 0, N'Tầng 1', 1),
	(N'P103',2, 4, 0, N'Tầng 1', 2),
	(N'P201',2, 4, 1, N'Tầng 2', 2),
	(N'P202',2, 2, 2, N'Tầng 2', 2),
	(N'P203',1, 1, 2, N'Tầng 2', 1)
GO

INSERT INTO dbo.HoaDonPhong
	(MaKH, MaPhong, tinhTrang, NgayGioNhan, NgayGioTra)
VALUES
	(1, N'P101', 1, '2021-05-16', '2021-05-16'),
	(2, N'P102', 1, '2021-05-02', '2021-05-07'),
	(3, N'P103', 2, '2021-05-16', null),
	(5, N'P201', 0, '2021-05-30', '2021-06-10'),
	(4, N'P201', 0, '2021-06-15', null)
GO

-- drop database KhachSan

-- Chi tiết hóa đơn DV
CREATE PROC UDP_SearchCTHDByDate
	@tuNgay DATETIME,
	@denNgay DATETIME
AS
BEGIN
	SELECT hd.MaHDDV, hd.NgayGioLap , hd.TinhTrang ,
		kh.MaKH ,kh.TenKH, kh.CMND, kh.NgayHetHan, kh.LoaiKH, kh.SoLanDatPhong,
		ct.SoLuong, ct.NgayGioDat,
		dv.MaDV, dv.TenDV, dv.DonGia
	FROM dbo.HoaDonDV hd
		JOIN dbo.ChiTietDV ct ON hd.MaHDDV = ct.MaHDDV
		JOIN dbo.DichVu dv ON ct.MaDV = dv.MaDV
		JOIN dbo.KhachHang kh ON hd.MaKH = kh.MaKH
	WHERE ct.NgayGioDat BETWEEN @tuNgay AND @denNgay
		AND hd.TinhTrang = 1
END
GO

-- exeC UDP_SearchCTHDByDate '2021-05-01', '2021-05-01'

CREATE PROC UDP_SearchCTHDByMaHK
	@MaKH INT,
	@tuNgay DATETIME,
	@denNgay DATETIME
AS
BEGIN
	SELECT hd.MaHDDV, hd.NgayGioLap , hd.TinhTrang ,
		kh.MaKH ,kh.TenKH, kh.CMND, kh.NgayHetHan, kh.LoaiKH, kh.SoLanDatPhong,
		ct.SoLuong, ct.NgayGioDat,
		dv.MaDV, dv.TenDV, dv.DonGia
	FROM dbo.HoaDonDV hd
		JOIN dbo.ChiTietDV ct ON hd.MaHDDV = ct.MaHDDV
		JOIN dbo.DichVu dv ON ct.MaDV = dv.MaDV
		JOIN dbo.KhachHang kh ON hd.MaKH = kh.MaKH
	WHERE hd.MaKH = @MaKH 
		AND hd.ngayGioLap BETWEEN @tuNgay AND @denNgay
		AND hd.TinhTrang = 1
END
GO

create PROC UDP_SearchCTHDByTenKH
	@TenKH NVARCHAR(100),
	@tuNgay DATETIME,
	@denNgay DATETIME
AS
BEGIN

	SET @TenKH = '%' + @TenKH +'%'
	SELECT hd.MaHDDV, hd.NgayGioLap , hd.TinhTrang ,
		kh.MaKH ,kh.TenKH, kh.CMND, kh.NgayHetHan, kh.LoaiKH, kh.SoLanDatPhong,
		ct.SoLuong, ct.NgayGioDat,
		dv.MaDV, dv.TenDV, dv.DonGia
	FROM dbo.HoaDonDV hd 
		JOIN dbo.ChiTietDV ct ON hd.MaHDDV = ct.MaHDDV
		JOIN dbo.DichVu dv ON ct.MaDV = dv.MaDV
		JOIN dbo.KhachHang kh ON hd.MaKH = kh.MaKH
	WHERE kh.TenKH like @TenKH 
		AND hd.ngayGioLap BETWEEN @tuNgay AND @denNgay
		AND hd.TinhTrang = 1
END
GO

CREATE PROC UDP_SearchCTHDByMaKHAndTenKH
	@MaKH INT,
	@TenKH NVARCHAR(100),
	@tuNgay DATETIME,
	@denNgay DATETIME
AS
BEGIN
	SET @TenKH = '%' + @TenKH +'%'
	SELECT hd.MaHDDV, hd.NgayGioLap , hd.TinhTrang ,
		kh.MaKH ,kh.TenKH, kh.CMND, kh.NgayHetHan, kh.LoaiKH, kh.SoLanDatPhong,
		ct.SoLuong, ct.NgayGioDat,
		dv.MaDV, dv.TenDV, dv.DonGia
	FROM dbo.HoaDonDV hd
		JOIN dbo.ChiTietDV ct ON hd.MaHDDV = ct.MaHDDV
		JOIN dbo.DichVu dv ON ct.MaDV = dv.MaDV
		JOIN dbo.KhachHang kh ON hd.MaKH = kh.MaKH
	WHERE hd.MaKH = @MaKH 
		AND kh.tenKH LIKE @TenKH 
		AND hd.ngayGioLap BETWEEN @tuNgay AND @denNgay
		AND hd.TinhTrang = 1
END
GO

-- Hóa đơn phòng
CREATE PROC UDP_SearchHDPhongByDate
	@tuNgay DATETIME,
	@denNgay DATETIME
AS
BEGIN
	SELECT hd.MaHD, hd.TinhTrang AS TinhTrangHD, hd.NgayGioNhan, hd.NgayGioTra,
		p.MaPhong, p.SucChua, p.SoGiuong, p.ViTri, p.TinhTrang AS TinhTrangP,
		lp.MaLoaiPhong, lp.TenLoaiPhong, lp.DonGia,
		kh.MaKH, kh.TenKH, kh.CMND, kh.NgayHetHan, kh.LoaiKH, kh.SoLanDatPhong
	FROM dbo.HoaDonPhong hd
		JOIN dbo.Phong p ON hd.MaPhong = p.MaPhong
		JOIN dbo.LoaiPhong lp ON lp.MaLoaiPhong = p.MaLoaiPhong
		JOIN dbo.KhachHang kh ON kh.MaKH = hd.MaKH
	WHERE (hd.NgayGioNhan BETWEEN @tuNgay AND @denNgay) 
		AND (hd.TinhTrang BETWEEN 1 AND 2)
END
GO

CREATE PROC UDP_SearchHDPhongByMaKH
	@MaKH INT,
	@tuNgay DATETIME,
	@denNgay DATETIME
AS
BEGIN
	SELECT hd.MaHD, hd.TinhTrang AS TinhTrangHD, hd.NgayGioNhan, hd.NgayGioTra,
		p.MaPhong, p.SucChua, p.SoGiuong, p.ViTri, p.TinhTrang AS TinhTrangP,
		lp.MaLoaiPhong, lp.TenLoaiPhong, lp.DonGia,
		kh.MaKH, kh.TenKH, kh.CMND, kh.NgayHetHan, kh.LoaiKH, kh.SoLanDatPhong
	FROM dbo.HoaDonPhong hd
		JOIN dbo.Phong p ON hd.MaPhong = p.MaPhong
		JOIN dbo.LoaiPhong lp ON lp.MaLoaiPhong = p.MaLoaiPhong
		JOIN dbo.KhachHang kh ON kh.MaKH = hd.MaKH
	WHERE hd.MaKH = @MaKH
		AND (hd.NgayGioNhan BETWEEN @tuNgay AND @denNgay)
		AND (hd.TinhTrang BETWEEN 1 AND 2)
END
GO

CREATE PROC UDP_SearchHDPhongByTenKH
	@TenKH NVARCHAR(100),
	@tuNgay DATETIME,
	@denNgay DATETIME
AS
BEGIN
	SET @TenKH = '%' + @TenKH +'%'
	SELECT hd.MaHD, hd.TinhTrang as TinhTrangHD, hd.NgayGioNhan, hd.NgayGioTra,
		p.MaPhong, p.SucChua, p.SoGiuong, p.ViTri, p.TinhTrang as TinhTrangP,
		lp.MaLoaiPhong, lp.TenLoaiPhong, lp.DonGia, 
		kh.MaKH, kh.TenKH, kh.CMND, kh.NgayHetHan, kh.LoaiKH, kh.SoLanDatPhong
	FROM dbo.HoaDonPhong hd
		JOIN dbo.Phong p ON hd.MaPhong = p.MaPhong
		JOIN dbo.LoaiPhong lp ON lp.MaLoaiPhong = p.MaLoaiPhong
		JOIN dbo.KhachHang kh ON kh.MaKH = hd.MaKH
	WHERE kh.TenKH LIKE @TenKH
		AND (hd.NgayGioNhan BETWEEN @tuNgay AND @denNgay)
		AND (hd.TinhTrang BETWEEN 1 AND 2)
END
GO

CREATE PROC UDP_SearchHDPhongByMaKHAndTenKH
	@MaKH INT,
	@TenKH NVARCHAR(100),
	@tuNgay DATETIME,
	@denNgay DATETIME
AS
BEGIN
	SET @TenKH = '%' + @TenKH +'%'
	SELECT hd.MaHD, hd.TinhTrang as TinhTrangHD, hd.NgayGioNhan, hd.NgayGioTra,
		p.MaPhong, p.SucChua, p.SoGiuong, p.ViTri, p.TinhTrang as TinhTrangP,
		lp.MaLoaiPhong, lp.TenLoaiPhong, lp.DonGia, 
		kh.MaKH, kh.TenKH, kh.CMND, kh.NgayHetHan, kh.LoaiKH, kh.SoLanDatPhong
	FROM dbo.HoaDonPhong hd
		JOIN dbo.Phong p ON hd.MaPhong = p.MaPhong
		JOIN dbo.LoaiPhong lp ON lp.MaLoaiPhong = p.MaLoaiPhong
		JOIN dbo.KhachHang kh ON kh.MaKH = hd.MaKH
	WHERE hd.MaKH = @MaKH 
		AND kh.tenKH LIKE @TenKH 
		AND (hd.NgayGioNhan BETWEEN @tuNgay AND @denNgay)
		AND (hd.TinhTrang BETWEEN 1 AND 2)
END
GO

CREATE PROC UDP_GetDateTimeHDPhongByMaHD
	@MaHD INT
AS
BEGIN
	SELECT hd.NgayGioNhan, hd.NgayGioTra
	FROM dbo.HoaDonPhong hd
	WHERE hd.MaHD = @MaHD
END
GO

CREATE PROC UDP_GetListHDPhongReservation
	@MaPhong NVARCHAR(100),
	@tuNgay DATETIME
AS
BEGIN
	SELECT hd.MaHD, hd.TinhTrang AS TinhTrangHD, hd.NgayGioNhan, hd.NgayGioTra,
		p.MaPhong, p.SucChua, p.SoGiuong, p.ViTri, p.TinhTrang AS TinhTrangP,
		lp.MaLoaiPhong, lp.TenLoaiPhong, lp.DonGia,
		kh.MaKH, kh.TenKH, kh.CMND, kh.NgayHetHan, kh.LoaiKH, kh.SoLanDatPhong
	FROM dbo.HoaDonPhong hd
		JOIN dbo.Phong p ON hd.MaPhong = p.MaPhong
		JOIN dbo.LoaiPhong lp ON p.MaLoaiPhong = lp.MaLoaiPhong
		JOIN dbo.KhachHang kh ON kh.MaKH = hd.MaKH
	WHERE p.MaPhong = @MaPhong 
		AND hd.tinhTrang = 0 
		AND hd.NgayGioNhan > @tuNgay
END
GO

CREATE PROC UDP_GetListHDPhongReservationLimit
	@MaPhong NVARCHAR(100),
	@tuNgay DATETIME,
	@denNgay DATETIME
AS
BEGIN
	SELECT hd.MaHD, hd.TinhTrang AS TinhTrangHD, hd.NgayGioNhan, hd.NgayGioTra,
		p.MaPhong, p.SucChua, p.SoGiuong, p.ViTri, p.TinhTrang AS TinhTrangP,
		lp.MaLoaiPhong, lp.TenLoaiPhong, lp.DonGia,
		kh.MaKH, kh.TenKH, kh.CMND, kh.NgayHetHan, kh.LoaiKH, kh.SoLanDatPhong
	FROM dbo.HoaDonPhong hd
		JOIN dbo.Phong p ON hd.MaPhong = p.MaPhong
		JOIN dbo.LoaiPhong lp ON p.MaLoaiPhong = lp.MaLoaiPhong
		JOIN dbo.KhachHang kh ON kh.MaKH = hd.MaKH
	WHERE p.MaPhong = @MaPhong 
		AND hd.tinhTrang = 0 
		AND hd.NgayGioNhan BETWEEN @tuNgay AND @denNgay
END
GO