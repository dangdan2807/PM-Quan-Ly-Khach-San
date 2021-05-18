package entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DichVu {
	private int maDV;
	private String tenDV;
	private double donGia;

	public int getMaDV() {
		return maDV;
	}

	public void setMaDV(int maDV) {
		this.maDV = maDV;
	}

	public String getTenDV() {
		return tenDV;
	}

	public void setTenDV(String tenDV) {
		if (tenDV.equals(""))
			tenDV = "Chưa cập nhật";
		this.tenDV = tenDV;
	}

	public double getDonGia() {
		return donGia;
	}

	public void setDonGia(double donGia) {
		if (donGia < 0)
			donGia = 0.0;
		this.donGia = donGia;
	}

	public DichVu(int maDV, String tenDV, double donGia) {
		setMaDV(maDV);
		setTenDV(tenDV);
		setDonGia(donGia);
	}
	public DichVu(String tenDV, double donGia) {
		setDonGia(donGia);
		setTenDV(tenDV);
	}

	public DichVu(int maDV) {
		this(maDV, "Chưa cập nhật", 0.0);
	}

	public DichVu(ResultSet rs) throws SQLException {
		this(rs.getInt("MaDV"), rs.getString("TenDV"), rs.getDouble("donGia"));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + maDV;
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
		DichVu other = (DichVu) obj;
		if (maDV != other.maDV)
			return false;
		return true;
	}

}
