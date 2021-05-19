package entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ChiTietDV {
    private int soLuong;

    private HoaDonDV hoaDonDV;
    private DichVu dichVu;

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
        if(soLuong < 0)
            soLuong = 0;
        this.soLuong = soLuong;
    }

    public HoaDonDV getHoaDonDV() {
        return hoaDonDV;
    }

    public void setHoaDonDV(HoaDonDV hoaDonDV) {
        this.hoaDonDV = hoaDonDV;
    }

    public ChiTietDV(int soLuong, HoaDonDV hoaDonDV, DichVu dichVu) {
        setSoLuong(soLuong);
        this.hoaDonDV = hoaDonDV;
        this.dichVu = dichVu;
    }

    public ChiTietDV(ResultSet rs) throws SQLException {
        this(rs.getInt("SoLuong"),
                new HoaDonDV(rs.getInt("MaHDDV"), rs.getDate("ngayGioDat"),
                        new KhachHang(rs.getInt("MaKH"), rs.getString("TenKH"))),
                new DichVu(rs.getInt("MaDV"), rs.getString("tenDV"), rs.getDouble("donGia")));
    }

}
