package entity;

import java.sql.*;
import DAO.*;

public class KhachHang {
	private int maKH;
	private String tenKH;
	private String cmnd;
	private Date ngayHetHan;
	private String loaiKH;
	private int soLanDatPhong = 0;

	public KhachHang(int maKH, String tenKH, String cmnd, Date ngayHetHan, String loaiKH, int soLanDatPhong) {
		setMaKH(maKH);
		setTenKH(tenKH);
		setCmnd(cmnd);
		this.ngayHetHan = ngayHetHan;
		this.loaiKH = loaiKH;
		setSoLanDatPhong(soLanDatPhong);
	}

	public KhachHang(int maKH) {
		KhachHangDAO khachHang_dao = new KhachHangDAO();
		KhachHang kh = khachHang_dao.getKhachHangByMaKH(maKH);
		this.maKH = maKH;
		this.tenKH = kh.getTenKH();
		this.cmnd = kh.getCmnd();
		this.ngayHetHan = kh.getNgayHetHan();
		this.loaiKH = kh.getLoaiKH();
		this. soLanDatPhong = kh.getSoLanDatPhong();
	}

	public KhachHang(int maKH, String tenKH) {
		this(maKH, tenKH, "Chưa cập nhật", null, "VN", 0);
	}

	public KhachHang(ResultSet rs) throws SQLException {
		this(rs.getInt("MaKH"), rs.getString("TenKH"), rs.getString("CMND"), rs.getDate("NgayHetHan"),
				rs.getString("LoaiKH"), rs.getInt("SoLanDatPhong"));
	}


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
		if (!(loaiKH.equalsIgnoreCase("Việt Nam")) || !(loaiKH.equalsIgnoreCase("Nước ngoài")))
			loaiKH = "Việt Nam";
		this.loaiKH = loaiKH;
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
