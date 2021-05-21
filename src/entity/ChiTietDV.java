package entity;

import java.sql.ResultSet;
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
        this(rs.getInt("SoLuong"), rs.getDate("NgayGioDat"),
                new HoaDonDV(rs.getInt("MaHDDV"), rs.getDate("ngayGioLap"), rs.getInt("tinhTrang"),
                        new KhachHang(rs.getInt("MaKH"), rs.getString("TenKH"), rs.getString("CMND"),
                                rs.getDate("NgayHetHan"), rs.getString("LoaiKH"), rs.getInt("SoLanDatPhong"))),
                new DichVu(rs.getInt("MaDV"), rs.getString("tenDV"), rs.getDouble("donGia")));
    }

}
