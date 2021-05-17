package entity;

public class Phong {
    private int maPhong;
    private int sucChua;
    private int soGiuong;
    private String viTri;
    private Boolean tinhTrang;

    private LoaiPhong loaiPhong;

    public int getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(int maPhong) {
        this.maPhong = maPhong;
    }

    public int getSucChua() {
        return sucChua;
    }

    public void setSucChua(int sucChua) {
        if (sucChua <= 0)
            sucChua = 1;
        this.sucChua = sucChua;
    }

    public int getSoGiuong() {
        return soGiuong;
    }

    public void setSoGiuong(int soGiuong) {
        if (soGiuong <= 0)
            soGiuong = 1;
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

    public LoaiPhong getLoaiPhong() {
        return loaiPhong;
    }

    public void setLoaiPhong(LoaiPhong loaiPhong) {
        this.loaiPhong = loaiPhong;
    }

    public Phong(int maPhong, int sucChua, int soGiuong, String viTri, Boolean tinhTrang, LoaiPhong loaiPhong) {
        this.maPhong = maPhong;
        setSucChua(sucChua);
        setSoGiuong(soGiuong);
        this.viTri = viTri;
        this.tinhTrang = tinhTrang;
        this.loaiPhong = loaiPhong;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + maPhong;
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
        if (maPhong != other.maPhong)
            return false;
        return true;
    }
}
