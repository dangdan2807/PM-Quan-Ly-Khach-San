package entity;

import java.sql.*;

public class HoaDonPhong {
    private int maHD;
    private Date ngayGioNhan;
    private Date ngayGioTra;

    private Phong phong;
    private KhachHang khachHang;
    private NhanVien nhanVien;

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public Date getNgayGioNhan() {
        return ngayGioNhan;
    }

    public void setNgayGioNhan(Date ngayGioNhan) {
        this.ngayGioNhan = ngayGioNhan;
    }

    public Date getNgayGioTra() {
        return ngayGioTra;
    }

    public void setNgayGioTra(Date ngayGioTra) {
        this.ngayGioTra = ngayGioTra;
    }

    public Phong getPhong() {
        return phong;
    }

    public void setPhong(Phong phong) {
        this.phong = phong;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public HoaDonPhong(int maHD, Date ngayGioNhan, Date ngayGioTra, Phong phong, KhachHang khachHang,
            NhanVien nhanVien) {
        this.maHD = maHD;
        this.ngayGioNhan = ngayGioNhan;
        this.ngayGioTra = ngayGioTra;
        this.phong = phong;
        this.khachHang = khachHang;
        this.nhanVien = nhanVien;
    }

    public HoaDonPhong(ResultSet rs) throws SQLException {
        this(rs.getInt("MaHD"), rs.getDate("NgayGioNhan"), rs.getDate("NgayGioTra"),
                new Phong(rs.getInt("MaPhong"),
                        new LoaiPhong(rs.getInt("MaLoaiPhong"), rs.getString("TenLoaiPhong"), rs.getDouble("DonGia"))),
                new KhachHang(rs.getInt("MaKH"), rs.getString("TenKH")),
                new NhanVien(rs.getInt("MaNV"), rs.getString("TenNV")));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + maHD;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        HoaDonPhong other = (HoaDonPhong) obj;
        if (maHD != other.maHD)
            return false;
        return true;
    }

}
