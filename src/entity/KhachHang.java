package entity;

import java.sql.*;

public class KhachHang {
	private int maKH;
	private String tenKH;
	private String cmnd;
	private Date ngayHetHan;
	private String loaiKH;
	private int soLanDatPhong = 0;

	public int getMaKH() {
		return maKH;
	}

	public void setMaKH(int maKH) {
		this.maKH = maKH;
	}

	public String getTenKH() {
		return tenKH;
	}

	public void setTenKH(String tenKH) {
		if (tenKH.equals(""))
			tenKH = "Chưa cập nhật";
		this.tenKH = tenKH;
	}

	public int getSoLanDatPhong() {
		return soLanDatPhong;
	}

	public void setSoLanDatPhong(int soLanDatPhong) {
		if (soLanDatPhong <= 0)
			soLanDatPhong = 0;
		this.soLanDatPhong = soLanDatPhong;
	}

	public String getCmnd() {
		return cmnd;
	}

	public void setCmnd(String cmnd) {
		if (cmnd.equals(""))
			cmnd = "Chưa cập nhật";
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
		if (!(loaiKH.equals("Việt Nam")) || !(loaiKH.equals("Nước ngoài")))
			loaiKH = "Việt Nam";
		this.loaiKH = loaiKH;
	}

	public KhachHang(int maKH, String tenKH, String cmnd, Date ngayHetHan, String loaiKH, int soLanDatPhong) {
		setMaKH(maKH);
		setTenKH(tenKH);
		setCmnd(cmnd);
		this.ngayHetHan = ngayHetHan;
		// setLoaiKH(loaiKH);
		this.loaiKH = loaiKH;
		setSoLanDatPhong(soLanDatPhong);
	}

	public KhachHang(int maKH) {
		this(maKH, "Chưa cập nhật", "Chưa cập nhật", null, "VN", 0);
	}

	public KhachHang(int maKH, String tenKH) {
		this(maKH, tenKH, "Chưa cập nhật", null, "VN", 0);
	}

	public KhachHang(ResultSet rs) throws SQLException {
		this(rs.getInt("MaKH"), rs.getString("TenKH"), rs.getString("cmnd"), rs.getDate("ngayHetHan"),
				rs.getString("LoaiKH"), rs.getInt("soLanDatPhong"));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + maKH;
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
		if (maKH != other.maKH)
			return false;
		return true;
	}

}
