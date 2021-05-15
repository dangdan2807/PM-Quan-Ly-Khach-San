USE master
GO

CREATE DATABASE KhachSan
GO

USE KhachSan
GO

CREATE TABLE KhachHang
(
	MaKH int identity PRIMARY KEY,
	TenKH NVARCHAR(50) NOT NULL,
	soLanDatPhong INT CHECK(soLanDatPhong >= 0),
	cmnd VARCHAR(20),
	LoaiKH NVARCHAR(100),
	ngayHetHan DATETIME,
)
GO

CREATE TABLE DichVu
(
	MaDV int identity PRIMARY KEY,
	TenDV NVARCHAR(50) NOT NULL,
	DonGia DECIMAL CHECK(DonGia >= 0)
)
GO

CREATE TABLE NhanVien
(
	MaNV int identity PRIMARY KEY,
	TenNV NVARCHAR(50),
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
	MaHDDV int identity REFERENCES HoaDonDV(MaHDDV),
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

-- drop database KhachSan