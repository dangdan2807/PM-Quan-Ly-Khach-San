package entity;

import java.sql.*;

public class HoaDonPhong {
    private int maHD;
    private int tinhTrang;
    private Date ngayGioNhan;
    private Date ngayGioTra;

    private Phong phong;
    private KhachHang khachHang;

    public HoaDonPhong(int maHD, int tinhTrang, Date ngayGioNhan, Date ngayGioTra, Phong phong, KhachHang khachHang) {
        this.maHD = maHD;
        this.tinhTrang = tinhTrang;
        this.ngayGioNhan = ngayGioNhan;
        this.ngayGioTra = ngayGioTra;
        this.phong = phong;
        this.khachHang = khachHang;
    }

    public int getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(int tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public HoaDonPhong(ResultSet rs) throws SQLException {
        this(rs.getInt("MaHD"), rs.getInt("TinhTrang"), rs.getDate("NgayGioNhan"), rs.getDate("NgayGioTra"),
                new Phong(rs.getString("MaPhong"),
                        new LoaiPhong(rs.getInt("MaLoaiPhong"), rs.getString("TenLoaiPhong"), rs.getDouble("DonGia"))),
                new KhachHang(rs.getInt("MaKH"), rs.getString("TenKH")));
    }

    public HoaDonPhong(ResultSet rs, int type) throws SQLException {
        this(rs.getInt("MaHD"), rs.getInt("TinhTrangHD"), rs.getDate("NgayGioNhan"), rs.getDate("NgayGioTra"),
                new Phong(rs.getString("MaPhong"), rs.getInt("SucChua"), rs.getInt("SoGiuong"), rs.getString("ViTri"),
                        rs.getInt("TinhTrangP"),
                        new LoaiPhong(rs.getInt("MaLoaiPhong"), rs.getString("TenLoaiPhong"), rs.getDouble("DonGia"))),
                new KhachHang(rs.getInt("MaKH"), rs.getString("TenKH")));
    }

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

    public double tinhTongTien(){
        double tongTien = phong.getLoaiPhong().getDonGia()*tinhSoNgay();
        System.out.println(tongTien);
        tongTien = tongTien - khachHang.getSoLanDatPhong()*3*(tongTien/100);
        return tongTien;
    }

    public int tinhSoNgay(){
        // System.out.print(ngayGioTra.getTime());
        // System.out.print(ngayGioNhan.getTime());
        long d = ngayGioTra.getTime() - ngayGioNhan.getTime();
        System.out.println(ngayGioTra.compareTo(ngayGioNhan));
        
        return (int)d/86400000 + 1;
    }
    
}
