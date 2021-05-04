use KhachSan;

create table KhachHang (
	MaKH varchar(50) primary key,
	TenKH varchar(50),
	soLanDatPhong int check(soLanDatPhong >= 0)
)

create table KHVietNam(
	MaKH varchar(50) references KhachHang(MaKH),
	cmnd varchar(15)
)

create table KHNuocNgoai(
	MaKH varchar(50) references KhachHang(MaKH),
	Passport varchar(15),
	NgayHetHan date
)

create table DichVu(
	MaDV varchar(50) primary key,
	TenDV varchar(50),
	soLuong int check(soLuong >= 0),
	DonGia decimal check(DonGia >= 0)
)

create table TaiKhoan(
	MaTK varchar(50) primary key,
	tenDN varchar(50),
	MatKhau varchar(50)
)

create table NhanVien(
	MaNV varchar(50) primary key,
	TenNV varchar(50),
	MaTK varchar(50) references TaiKhoan(MaTK)
)

create table LoaiPhong(
	MaLoaiPhong varchar(50) primary key,
	TenLoaiPhong varchar(50),
	DonGia decimal check(DonGia >= 0)
)

create table Phong(
	MaPhong varchar(50) primary key,
	MaLoaiPhong varchar(50) references LoaiPhong(MaLoaiPhong),
	SucChua int check(SucChua > 0),
	SoGiuong int check(SoGiuong > 0),
	ViTri varchar(50),
	TinhTrang bit
)

create table HoaDonDV(
	MaHD varchar(50) primary key,
	MaKH varchar(50) references KhachHang(MaKH),
	MaNV varchar(50) references NhanVien(MaNV),
	ThoiGian datetime,
	TongTien decimal check(TongTien >= 0)
)

create table ChiTietDV(
	MaHD varchar(50) references HoaDonDV(MaHD),
	MaDV varchar(50) references DichVu(MaDV),
	SoLuong int check(Soluong >= 0)
)


create table HoaDonPhong(
	MaHD varchar(50) primary key,
	MaKH varchar(50) references KhachHang(MaKH),
	MaNV varchar(50) references NhanVien(MaNV),
	NgayGioNhan datetime,
	NgayGioTra datetime,
	TongTien decimal check(TongTien >= 0)
)

create table ChiTietHD(
	MaHD varchar(50) references HoaDonPhong(MaHD),
	MaPhong varchar(50) references Phong(MaPhong),
	SoLuong int check(Soluong >= 0)
)