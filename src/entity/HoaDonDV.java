package entity;

import java.sql.*;

public class HoaDonDV {
	private String maHDDV;
	private Date ngayGioDat;

	public String getMaHDDV() {
		return maHDDV;
	}

	public void setMaHDDV(String maHDDV) {
		this.maHDDV = maHDDV;
	}

	public Date getNgayGioDat() {
		return ngayGioDat;
	}

	public void setNgayGioDat(Date ngayGioDat) {
		this.ngayGioDat = ngayGioDat;
	}

	public HoaDonDV(String maHDDV, Date ngayGioDat) {
		this.maHDDV = maHDDV;
		this.ngayGioDat = ngayGioDat;
	}

	public HoaDonDV(String maHDDV) {
		this(maHDDV, new Date(System.currentTimeMillis()));
	}

	public HoaDonDV() {
		this("", new Date(System.currentTimeMillis()));
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
		HoaDonDV other = (HoaDonDV) obj;
		if (maHDDV == null) {
			if (other.maHDDV != null)
				return false;
		} else if (!maHDDV.equals(other.maHDDV))
			return false;
		return true;
	}

}
