package QuanLyKhachSan;

public class Phong {
    private String maPhong;
    private int sucChua;
    private int soGiuong;
    private String viTri;
    private Boolean tinhTrang;

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public int getSucChua() {
        return sucChua;
    }

    public void setSucChua(int sucChua) {
        this.sucChua = sucChua;
    }

    public int getSoGiuong() {
        return soGiuong;
    }

    public void setSoGiuong(int soGiuong) {
        this.soGiuong = soGiuong;
    }

    public String getViTri() {
        return viTri;
    }

    public void setViTri(String viTri) {
        this.viTri = viTri;
    }

    public Boolean getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(Boolean tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public Phong(String maPhong, int sucChua, int soGiuong, String viTri, Boolean tinhTrang) {
        this.maPhong = maPhong;
        this.sucChua = sucChua;
        this.soGiuong = soGiuong;
        this.viTri = viTri;
        this.tinhTrang = tinhTrang;
    }

    public Phong() {
        this("P00", 1, 1, "chưa rõ", false);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((maPhong == null) ? 0 : maPhong.hashCode());
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
        Phong other = (Phong) obj;
        if (maPhong == null) {
            if (other.maPhong != null)
                return false;
        } else if (!maPhong.equals(other.maPhong))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "[maPhong=" + maPhong + ", soGiuong=" + soGiuong + ", sucChua=" + sucChua + ", tinhTrang=" + tinhTrang
                + ", viTri=" + viTri + "]";
    }

}
