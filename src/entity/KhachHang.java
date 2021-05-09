package entity;

import java.sql.Date;

public class KhachHang {
	private String maKH;
	private String tenKH;
	private int soLanDatPhong = 0;
	private String cmnd;
	private Date ngayHetHan;
	private String loaiKH;

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public String getTenKH() {
		return tenKH;
	}

	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}

	public int getSoLanDatPhong() {
		return soLanDatPhong;
	}

	public void setSoLanDatPhong(int soLanDatPhong) {
		this.soLanDatPhong = soLanDatPhong;
	}

	public String getCmnd() {
		return cmnd;
	}

	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}

	public Date getNgayHetHan() {
		return ngayHetHan;
	}

	public void setNgayHetHan(Date ngayHetHan) {
		this.ngayHetHan = ngayHetHan;
	}

	public String getLoaiKH() {
		return loaiKH;
	}

	public void setLoaiKH(String loaiKH) {
		this.loaiKH = loaiKH;
	}

	public KhachHang(String maKH, String tenKH, int soLanDatPhong, String cmnd, Date ngayHetHan, String loaiKH) {
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.soLanDatPhong = soLanDatPhong;
		this.cmnd = cmnd;
		this.ngayHetHan = ngayHetHan;
		this.loaiKH = loaiKH;
	}

	public KhachHang(String maKH) {
		this(maKH, "Chưa cập nhật", 0, "Chưa cập nhật", null, "VN");
	}

	public KhachHang() {
		this("KH00", "Chưa cập nhật", 0, "Chưa cập nhật", null, "VN");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maKH == null) ? 0 : maKH.hashCode());
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
		KhachHang other = (KhachHang) obj;
		if (maKH == null) {
			if (other.maKH != null)
				return false;
		} else if (!maKH.equals(other.maKH))
			return false;
		return true;
	}

	
}
