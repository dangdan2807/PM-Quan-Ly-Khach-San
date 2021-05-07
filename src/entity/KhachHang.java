package baiTapCuoiKy;

public class KhachHang {
	private String maKH;
	private String tenKH;
	private int soLanDatPhong=0;
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
	public KhachHang(String maKH, String tenKH, int soLanDatPhong) {
		super();
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.soLanDatPhong = soLanDatPhong;
	}
	public KhachHang(String maKH) {
		this(maKH,"",0);
	}
	@Override
	public String toString() {
		return String.format("%s %s %d", maKH, tenKH,soLanDatPhong);
	}
	
	public boolean isKhachQuen() {
		if(soLanDatPhong>=10)
			return true;
		return false;
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
	
//	public void capNhatSoLanDatPhong(){
//		if()
//			soLanDatPhong++;
//	}

	
}
