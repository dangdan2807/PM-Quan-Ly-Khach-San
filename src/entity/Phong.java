package entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import DAO.*;

public class Phong {
    private String maPhong;
    private int sucChua;
    private int soGiuong;
    private String viTri;
    private int tinhTrang;
    private LoaiPhong loaiPhong;

    public Phong(String maPhong, int sucChua, int soGiuong, String viTri, int tinhTrang, LoaiPhong loaiPhong) {
        this.maPhong = maPhong;
        setSucChua(sucChua);
        setSoGiuong(soGiuong);
        this.viTri = viTri;
        this.tinhTrang = tinhTrang;
        this.loaiPhong = loaiPhong;
    }

    public Phong(String maPhong) {
        PhongDAO phong_dao = new PhongDAO();
        Phong phong = phong_dao.getPhongByMaPhong(maPhong);
        this.maPhong = phong.getMaPhong();
        this.loaiPhong = phong.getLoaiPhong();
        this.sucChua = phong.getSucChua();
        this.soGiuong = phong.getSoGiuong();
        this.viTri = phong.getViTri();
        this.tinhTrang = phong.getTinhTrang();
    }

    public Phong(String maPhong, LoaiPhong loaiPhong) {
        this.maPhong = maPhong;
        this.loaiPhong = loaiPhong;
    }

    public Phong(ResultSet rs) throws SQLException {
        this(rs.getString("MaPhong"), rs.getInt("SucChua"), rs.getInt("SoGiuong"), rs.getString("ViTri"),
                rs.getInt("TinhTrang"),
                new LoaiPhong(rs.getInt("MaLoaiPhong"), rs.getString("TenLoaiPhong"), rs.getDouble("DonGia")));
    }

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

    public int getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(int tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public LoaiPhong getLoaiPhong() {
        return loaiPhong;
    }

    public void setLoaiPhong(LoaiPhong loaiPhong) {
        this.loaiPhong = loaiPhong;
    }

}
