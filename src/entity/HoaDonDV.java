package entity;

import java.sql.*;

public class HoaDonDV {
	private int maHDDV;
	private int tinhTrang;
	private Date ngayGioLap;
	private KhachHang khachHang;

	public int getMaHDDV() {
		return maHDDV;
	}

	public int getTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(int tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

	public void setMaHDDV(int maHDDV) {
		this.maHDDV = maHDDV;
	}

	public Date getNgayGioDat() {
		return ngayGioLap;
	}

	public void setNgayGioDat(Date ngayGioDat) {
		this.ngayGioLap = ngayGioDat;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public HoaDonDV(int maHDDV, KhachHang khachHang,Date ngayGioDat, int tinhTrang ) {
		this.maHDDV = maHDDV;
		this.ngayGioLap = ngayGioDat;
		this.tinhTrang = tinhTrang;
		this.khachHang = khachHang;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + maHDDV;
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
		HoaDonDV other = (HoaDonDV) obj;
		if (maHDDV != other.maHDDV)
			return false;
		return true;
	}
	public HoaDonDV(ResultSet rs) throws SQLException {
        this(rs.getInt("MaHDDV"), new KhachHang(rs.getInt("MaKH")), rs.getDate("NgayGioLap"), rs.getInt("TinhTrang"));
    }
}
