package entity;

public class ChiTietDV {
    private String maHDDV;
    private String maDV;
    private int SoLuong;

    public String getMaHDDV() {
        return maHDDV;
    }

    public void setMaHDDV(String maHDDV) {
        this.maHDDV = maHDDV;
    }

    public String getMaDV() {
        return maDV;
    }

    public void setMaDV(String maDV) {
        this.maDV = maDV;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    public ChiTietDV(String maHDDV, String maDV, int soLuong) {
        this.maHDDV = maHDDV;
        this.maDV = maDV;
        SoLuong = soLuong;
    }

    public ChiTietDV(String maHDDV) {
        this.maHDDV = maHDDV;
        this.maDV = "DV00";
        SoLuong = 1;
    }

    public ChiTietDV() {
        this("HDDV00", "DV00", 1);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((maHDDV == null) ? 0 : maHDDV.hashCode());
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
        ChiTietDV other = (ChiTietDV) obj;
        if (maHDDV == null) {
            if (other.maHDDV != null)
                return false;
        } else if (!maHDDV.equals(other.maHDDV))
            return false;
        return true;
    }

}
