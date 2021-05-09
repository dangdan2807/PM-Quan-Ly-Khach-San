package entity;

import java.sql.Date;

public class HoaDonPhong {
    private String maHD;
    private Date ngayGioNhan;
    private Date ngayGioTra;

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public Date getNgayGioNhan() {
        return ngayGioNhan;
    }

    public void setNgayGioNhan(Date ngayGioNhan) {
        this.ngayGioNhan = ngayGioNhan;
    }

    public Date getNgayGioTra() {
        return ngayGioTra;
    }

    public void setNgayGioTra(Date ngayGioTra) {
        this.ngayGioTra = ngayGioTra;
    }

    public HoaDonPhong(String maHD, Date ngayGioNhan, Date ngayGioTra) {
        this.maHD = maHD;
        this.ngayGioNhan = ngayGioNhan;
        this.ngayGioTra = ngayGioTra;
    }

    public HoaDonPhong(String maHD, Date ngayGioNhan) {
        this.maHD = maHD;
        this.ngayGioNhan = ngayGioNhan;
        this.ngayGioTra = null;
    }

    public HoaDonPhong(String maHD) {
        this.maHD = maHD;
        this.ngayGioNhan = new Date(System.currentTimeMillis());
        this.ngayGioTra = null;
    }

    public HoaDonPhong() {
        this("HD00", new Date(System.currentTimeMillis()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((maHD == null) ? 0 : maHD.hashCode());
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
        HoaDonPhong other = (HoaDonPhong) obj;
        if (maHD == null) {
            if (other.maHD != null)
                return false;
        } else if (!maHD.equals(other.maHD))
            return false;
        return true;
    }
}
