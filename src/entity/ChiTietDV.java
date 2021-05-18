package entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ChiTietDV {
    private int soLuong;

    private HoaDonDV maHDDV;
    private DichVu maDV;

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        if (soLuong <= 0)
            soLuong = 1;
        this.soLuong = soLuong;
    }

    public HoaDonDV getMaHDDV() {
        return maHDDV;
    }

    public void setMaHDDV(HoaDonDV maHDDV) {
        this.maHDDV = maHDDV;
    }

    public DichVu getMaDV() {
        return maDV;
    }

    public void setMaDV(DichVu maDV) {
        this.maDV = maDV;
    }

    public ChiTietDV(int soLuong, HoaDonDV maHDDV, DichVu maDV) {
        setSoLuong(soLuong);
        this.maHDDV = maHDDV;
        this.maDV = maDV;
    }

    public ChiTietDV(ResultSet rs) throws SQLException {
        this(rs.getInt("SoLuong"),
                new HoaDonDV(rs.getInt("MaHDDV"), rs.getDate("ngayGioDat"),
                        new NhanVien(rs.getInt("MaNV"), rs.getString("TenNV")),
                        new KhachHang(rs.getInt("MaKH"), rs.getString("TenKH"))),
                new DichVu(rs.getInt("MaDV"), rs.getString("tenDV"), rs.getDouble("donGia")));
    }

}
