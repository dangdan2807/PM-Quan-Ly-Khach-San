package entity;

public class DichVu {
	private String maDV, tenDV;
	private double donGia;

	public String getMaDV() {
		return maDV;
	}

	public void setMaDV(String maDV) {
		this.maDV = maDV;
	}

	public String getTenDV() {
		return tenDV;
	}

	public void setTenDV(String tenDV) {
		this.tenDV = tenDV;
	}

	public double getDonGia() {
		return donGia;
	}

	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}

	public DichVu(String maDV, String tenDV, double donGia) {
		this.maDV = maDV;
		this.tenDV = tenDV;
		this.donGia = donGia;
	}

	public DichVu(String maDV) {
		this(maDV, "", 0.0);
	}

	public DichVu() {
		this("DV00", "Chưa cập nhật", 0.0);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maDV == null) ? 0 : maDV.hashCode());
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
		if (maDV == null) {
			if (other.maDV != null)
				return false;
		} else if (!maDV.equals(other.maDV))
			return false;
		return true;
	}
}
