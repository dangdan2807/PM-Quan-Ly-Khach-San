package baiTapCuoiKy;

public class KHVietNam extends KhachHang{
	private String cMND;

	public String getcMND() {
		return cMND;
	}

	public void setcMND(String cMND) {
		this.cMND = cMND;
	}

	public KHVietNam(String maKH, String tenKH, int soLanDatPhong, String cMND) {
		super(maKH, tenKH, soLanDatPhong);
		this.cMND = cMND;
	}




}
