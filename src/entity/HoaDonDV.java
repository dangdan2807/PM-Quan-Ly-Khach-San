package entity;

import java.sql.*;

public class HoaDonDV {
	private int maHDDV;
	private Date ngayGioDat;

	private NhanVien nhanVien;
	private KhachHang khachHang;

	public int getMaHDDV() {
		return maHDDV;
	}

	public void setMaHDDV(int maHDDV) {
		this.maHDDV = maHDDV;
	}

	public Date getNgayGioDat() {
		return ngayGioDat;
	}

	public void setNgayGioDat(Date ngayGioDat) {
		this.ngayGioDat = ngayGioDat;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public HoaDonDV(int maHDDV, Date ngayGioDat, NhanVien nhanVien, KhachHang khachHang) {
		this.maHDDV = maHDDV;
		this.ngayGioDat = ngayGioDat;
		this.nhanVien = nhanVien;
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

}
