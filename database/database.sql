USE master
GO

CREATE DATABASE KhachSan
GO

USE KhachSan
GO

CREATE TABLE KhachHang
(
	MaKH int identity PRIMARY KEY,
	TenKH NVARCHAR(100) NOT NULL,
	cmnd VARCHAR(20),
	ngayHetHan DATETIME,
	LoaiKH NVARCHAR(100),
	soLanDatPhong INT CHECK(soLanDatPhong >= 0)
)
GO

CREATE TABLE DichVu
(
	MaDV int identity PRIMARY KEY,
	TenDV NVARCHAR(100) NOT NULL,
	DonGia DECIMAL CHECK(DonGia >= 0)
)
GO

CREATE TABLE NhanVien
(
	MaNV int identity PRIMARY KEY,
	TenNV NVARCHAR(100),
)
GO

CREATE TABLE LoaiPhong
(
	MaLoaiPhong int identity PRIMARY KEY,
	TenLoaiPhong NVARCHAR(50),
	DonGia DECIMAL CHECK(DonGia >= 0)
)
GO

CREATE TABLE Phong
(
	MaPhong int identity PRIMARY KEY,
	MaLoaiPhong int REFERENCES LoaiPhong(MaLoaiPhong),
	SucChua INT CHECK(SucChua > 0),
	SoGiuong INT CHECK(SoGiuong > 0),
	ViTri NVARCHAR(50),
	TinhTrang BIT
)
GO

CREATE TABLE HoaDonDV
(
	MaHDDV int identity PRIMARY KEY,
	MaKH int REFERENCES KhachHang(MaKH),
	MaNV int REFERENCES NhanVien(MaNV),
	ngayGioDat DATETIME,
)
GO

CREATE TABLE ChiTietDV
(
	MaHDDV int REFERENCES HoaDonDV(MaHDDV),
	MaDV int REFERENCES DichVu(MaDV),
	SoLuong INT CHECK(Soluong >= 0)
)
GO

CREATE TABLE HoaDonPhong
(
	MaHD int PRIMARY KEY,
	MaKH int REFERENCES KhachHang(MaKH),
	MaNV int REFERENCES NhanVien(MaNV),
	MaPhong int REFERENCES Phong(MaPhong),
	NgayGioNhan DATETIME,
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

INSERT INTO dbo.NhanVien
	(TenNV)
VALUES
	(N'Thor'),
	(N'Cậu Vàng'),
	(N'Chị Dậu')
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
	(maKH, MaNV, ngayGioDat)
VALUES
	(1, 1, '2021-05-16'),
	(2, 2, '2021-04-02'),
	(3, 3, '2021-05-16')

INSERT INTO dbo.ChiTietDV
	(MaHDDV, MaDV, SoLuong)
VALUES
	(1, 1, 1),
	(1, 2, 2),
	(1, 4, 2),
	(2, 4, 1),
	(2, 5, 1),
	(2, 6, 2),
	(3, 8, 2),
	(3, 10, 2)
GO
-- drop database KhachSan

CREATE PROC UDP_SearchCTHDByDate
	@tuNgay DATETIME,
	@denNgay DATETIME
AS
BEGIN
	SELECT hd.MaHDDV, hd.MaKH, hd.MaNV, hd.ngayGioDat, ct.MaDV, dv.TenDV, ct.SoLuong, dv.DonGia, kh.TenKH, nv.TenNV
	FROM dbo.HoaDonDV hd JOIN dbo.ChiTietDV ct
		ON hd.MaHDDV = ct.MaHDDV
		JOIN dbo.DichVu dv ON ct.MaDV = dv.MaDV
		JOIN dbo.KhachHang kh ON hd.MaKH = kh.MaKH
		JOIN dbo.NhanVien nv ON nv.MaNV = hd.MaNV
	WHERE hd.ngayGioDat BETWEEN @tuNgay AND @denNgay
END
GO


create PROC UDP_SearchCTHDByMaHK
	@MaKH INT,
	@tuNgay DATETIME,
	@denNgay DATETIME
AS
BEGIN
	SELECT hd.MaHDDV, hd.MaKH, hd.MaNV, hd.ngayGioDat, ct.MaDV, dv.TenDV, ct.SoLuong, dv.DonGia, kh.TenKH, nv.TenNV
	FROM dbo.HoaDonDV hd JOIN dbo.ChiTietDV ct
		ON hd.MaHDDV = ct.MaHDDV
		JOIN dbo.DichVu dv ON ct.MaDV = dv.MaDV
		JOIN dbo.KhachHang kh ON hd.MaKH = kh.MaKH
		JOIN dbo.NhanVien nv ON nv.MaNV = hd.MaNV
	WHERE hd.MaKH = @MaKH AND hd.ngayGioDat BETWEEN @tuNgay AND @denNgay
END
GO

create PROC UDP_SearchCTHDByTenKH
	@TenKH NVARCHAR(100),
	@tuNgay DATETIME,
	@denNgay DATETIME
AS
BEGIN

	SET @TenKH = '%' + @TenKH +'%'
	SELECT hd.MaHDDV, hd.MaKH, hd.MaNV, hd.ngayGioDat, ct.MaDV, dv.TenDV, ct.SoLuong, dv.DonGia, kh.TenKH, nv.TenNV
	FROM dbo.HoaDonDV hd JOIN dbo.ChiTietDV ct
		ON hd.MaHDDV = ct.MaHDDV
		JOIN dbo.DichVu dv ON ct.MaDV = dv.MaDV
		JOIN dbo.KhachHang kh ON hd.MaKH = kh.MaKH
		JOIN dbo.NhanVien nv ON nv.MaNV = hd.MaNV
	WHERE kh.TenKH like @TenKH AND hd.ngayGioDat BETWEEN @tuNgay AND @denNgay
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
	SELECT hd.MaHDDV, hd.MaKH, hd.MaNV, hd.ngayGioDat, ct.MaDV, dv.TenDV, ct.SoLuong, dv.DonGia, kh.TenKH, nv.TenNV
	FROM dbo.HoaDonDV hd JOIN dbo.ChiTietDV ct
		ON hd.MaHDDV = ct.MaHDDV
		JOIN dbo.DichVu dv ON ct.MaDV = dv.MaDV
		JOIN dbo.KhachHang kh ON hd.MaKH = kh.MaKH
		JOIN dbo.NhanVien nv ON nv.MaNV = hd.MaNV
	WHERE hd.MaKH = @MaKH AND kh.tenKH like @TenKH AND hd.ngayGioDat BETWEEN @tuNgay AND @denNgay
END
GO