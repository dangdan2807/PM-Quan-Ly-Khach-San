package baiTapCuoiKy;

import java.sql.Date;


public class KHNuocNgoai extends KhachHang{
	private String passPort;
	private Date ngayHetHan;
	public String getPassPort() {
		return passPort;
	}
	public void setPassPort(String passPort) {
		this.passPort = passPort;
	}
	public Date getNgayHetHan() {
		return ngayHetHan;
	}
	public void setNgayHetHan(Date ngayHetHan) {
		this.ngayHetHan = ngayHetHan;
	}
	public KHNuocNgoai(String maKH, String tenKH, int soLanDatPhong, String passPort, Date ngayHetHan) {
		super(maKH, tenKH, soLanDatPhong);
		this.passPort = passPort;
		this.ngayHetHan = ngayHetHan;
	}
	public KHNuocNgoai(String maKH, String tenKH, int soLanDatPhong) {
		super(maKH, tenKH, soLanDatPhong);
	}

	

}
