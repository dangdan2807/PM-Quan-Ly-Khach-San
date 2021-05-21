package entity;

import java.sql.*;
import java.util.ArrayList;
import DAO.*;

public class HoaDonDV {
	private int maHDDV;
	private Date ngayGioDat;

	private KhachHang khachHang;


	public HoaDonDV(int maHDDV, Date ngayGioDat, KhachHang khachHang) {
		this.maHDDV = maHDDV;
		this.ngayGioDat = ngayGioDat;
		this.khachHang = khachHang;
	}

	public HoaDonDV() {
	
	}

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

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
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

	public ArrayList<HoaDonDV> getHDDVByMaKH(int maKH){
		HoaDonDVDAO hoaDonDV_dao = new HoaDonDVDAO();
		return hoaDonDV_dao.getHDDVByMaKH(maKH);
	}

	public ArrayList<ChiTietDV> getChiTietDV(){
		ChiTietDVDAO chiTietDV_dao = new ChiTietDVDAO();
        return chiTietDV_dao.getChiTietDVByMaHDDV(this.maHDDV);
	}

	public double tinhTong(){
		ArrayList<ChiTietDV> dsctdv = getChiTietDV();
		double tong = 0;
		for(int i=0; i<dsctdv.size(); i++){
			DichVu dv = dsctdv.get(i).getDichVu();
			tong += dv.getDonGia()*dsctdv.get(i).getSoLuong();
		}
		return tong;
	}

}
