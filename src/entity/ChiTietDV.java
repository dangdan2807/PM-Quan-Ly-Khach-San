package entity;

public class ChiTietDV {
    private int SoLuong;

    private HoaDonDV maHDDV;
    private DichVu maDV;

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
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
        SoLuong = soLuong;
        this.maHDDV = maHDDV;
        this.maDV = maDV;
    }
}
