package entity;

import java.sql.*;

public class ChiTietDV {
    private DichVu dichVu;
    private HoaDonDV hoaDonDV;
    private int soLuong;
    private Date ngayGioDat;

    public Date getNgayGioDat() {
        return ngayGioDat;
    }

    public void setNgayGioDat(Date ngayGioDat) {
        this.ngayGioDat = ngayGioDat;
    }

    public ChiTietDV(int soLuong, HoaDonDV hoaDonDV, DichVu dichVu) {
        setSoLuong(soLuong);
        this.hoaDonDV = hoaDonDV;
        this.dichVu = dichVu;
    }

    public ChiTietDV(DichVu dichVu, int soLuong) {
        
        
        
        this.dichVu = dichVu;
        setSoLuong(soLuong);
        
    }

    public DichVu getDichVu() {
        return dichVu;
    }

    public void setDichVu(DichVu dichVu) {
        this.dichVu = dichVu;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        if (soLuong < 0)
            soLuong = 0;
        this.soLuong = soLuong;
    }

    public HoaDonDV getHoaDonDV() {
        return hoaDonDV;
    }

    public void setHoaDonDV(HoaDonDV hoaDonDV) {
        this.hoaDonDV = hoaDonDV;
    }

    public ChiTietDV(int soLuong, Date ngayGioDat, HoaDonDV hoaDonDV, DichVu dichVu) {
        setSoLuong(soLuong);
        this.ngayGioDat = ngayGioDat;
        this.hoaDonDV = hoaDonDV;
        this.dichVu = dichVu;
    }

    public ChiTietDV(ResultSet rs) throws SQLException {
        KhachHang kh = new KhachHang(rs.getInt("MaKH"), rs.getString("TenKH"), rs.getString("CMND"),
                rs.getDate("NgayHetHan"), rs.getString("LoaiKH"), rs.getInt("SoLanDatPhong"));
        HoaDonDV hdDV = new HoaDonDV(rs.getInt("MaHDDV"), kh, rs.getDate("ngayGioLap"), rs.getInt("tinhTrang"));
        DichVu dv = new DichVu(rs.getInt("MaDV"), rs.getString("tenDV"), rs.getDouble("donGia"));

        setSoLuong(rs.getInt("SoLuong"));
        this.ngayGioDat = rs.getDate("NgayGioDat");
        this.hoaDonDV = hdDV;
        this.dichVu = dv;
    }

}
