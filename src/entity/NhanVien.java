package entity;

public class NhanVien {
    private int maNV;
    private String tenNV;

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public NhanVien(int maNV, String tenNV) {
        this.maNV = maNV;
        this.tenNV = tenNV;
    }

    public NhanVien(int maNV) {
        this(maNV, "Chưa cập nhật");
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + maNV;
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
        NhanVien other = (NhanVien) obj;
        if (maNV != other.maNV)
            return false;
        return true;
    }

}
