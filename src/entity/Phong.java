package entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import DAO.*;

public class Phong {
    private int maPhong;
    private LoaiPhong loaiPhong;
    private int sucChua;
    private int soGiuong;
    private String viTri;
    private Boolean tinhTrang;

    public Phong(int maPhong, int sucChua, int soGiuong, String viTri, Boolean tinhTrang, LoaiPhong loaiPhong) {
        this.maPhong = maPhong;
        setSucChua(sucChua);
        setSoGiuong(soGiuong);
        this.viTri = viTri;
        this.tinhTrang = tinhTrang;
        this.loaiPhong = loaiPhong;
    }

    public Phong(int maPhong) {
        PhongDAO phong_dao = new PhongDAO();
        Phong phong = phong_dao.getPhongByMaPhong(maPhong);
        this.maPhong = phong.getMaPhong();
        this.loaiPhong = phong.getLoaiPhong();
        this.sucChua = phong.getSucChua();
        this.soGiuong = phong.getSoGiuong();
        this.viTri = phong.getViTri();
        this.tinhTrang = phong.getTinhTrang();
    }

    public Phong(int maPhong, LoaiPhong loaiPhong) {
        this.maPhong = maPhong;
        this.loaiPhong = loaiPhong;
    }

    public Phong(ResultSet rs) throws SQLException {
        this(rs.getInt("maPhong"), rs.getInt("SucChua"),  rs.getInt("SoGiuong"), rs.getString("ViTri"), rs.getBoolean("tinhTrang"), new LoaiPhong(rs.getInt("MaLoaiPhong")));
    }

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
