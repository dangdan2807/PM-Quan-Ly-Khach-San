package entity;
import DAO.LoaiPhongDAO;

public class LoaiPhong {
    private int maLoaiPhong;
    private String tenLoaiPhong;
    private Double donGia;

    public LoaiPhong(int maLoaiPhong, String tenLoaiPhong, Double donGia) {
        this.maLoaiPhong = maLoaiPhong;
        this.tenLoaiPhong = tenLoaiPhong;
        setDonGia(donGia);
    }

    public LoaiPhong(int maLoaiPhong) {
        LoaiPhongDAO loaiPhong_dao = new LoaiPhongDAO();
        LoaiPhong loaiPhong = loaiPhong_dao.getLoaiPhongByMa(maLoaiPhong);
        this.maLoaiPhong = loaiPhong.getMaLoaiPhong();
        this.tenLoaiPhong = loaiPhong.getTenLoaiPhong();
        this.donGia = loaiPhong.getDonGia();
    }

    public int getMaLoaiPhong() {
        return maLoaiPhong;
    }

    public void setMaLoaiPhong(int maLoaiPhong) {
        
        this.maLoaiPhong = maLoaiPhong;
    }

    public String getTenLoaiPhong() {
        return tenLoaiPhong;
    }

    public void setTenLoaiPhong(String tenLoaiPhong) {
        this.tenLoaiPhong = tenLoaiPhong;
    }

    public Double getDonGia() {
        return donGia;
    }

    public void setDonGia(Double donGia) {
        if (donGia < 0)
            donGia = 0.0;
        this.donGia = donGia;
    }

    

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + maLoaiPhong;
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
        LoaiPhong other = (LoaiPhong) obj;
        if (maLoaiPhong != other.maLoaiPhong)
            return false;
        return true;
    }
    
}
