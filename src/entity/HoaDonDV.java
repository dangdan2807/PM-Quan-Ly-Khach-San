package entity;

import java.sql.*;
import java.util.ArrayList;
import DAO.*;

public class HoaDonDV {
	private int maHDDV;
	private int tinhTrang;
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

	public int getTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(int tinhTrang) {
		this.tinhTrang = tinhTrang;
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

	public HoaDonDV(int maHDDV, KhachHang khachHang, Date ngayGioDat, int tinhTrang) {
		this.maHDDV = maHDDV;
		this.ngayGioDat = ngayGioDat;
		this.tinhTrang = tinhTrang;
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

	public HoaDonDV(ResultSet rs) throws SQLException {
		this(rs.getInt("MaHDDV"), new KhachHang(rs.getInt("MaKH")), rs.getDate("NgayGioLap"), rs.getInt("TinhTrang"));
	}

	public HoaDonDV(int maHDDV) {
		HoaDonDVDAO hddvDAO = new HoaDonDVDAO();
		HoaDonDV hddv = hddvDAO.getListHDDVbyID(maHDDV);
		this.maHDDV = maHDDV;
		this.ngayGioDat = hddv.getNgayGioDat();
		this.tinhTrang = hddv.getTinhTrang();
		this.khachHang = hddv.getKhachHang();
	}



	public ArrayList<HoaDonDV> getHDDVByMaKH(int maKH) {
		HoaDonDVDAO hoaDonDV_dao = new HoaDonDVDAO();
		return hoaDonDV_dao.getHDDVByMaKH(maKH);
	}

	public ArrayList<HoaDonDV> getHDDVByMaKHAndDate(int maKH, Date tuNgay, Date denNgay) {
		HoaDonDVDAO hoaDonDV_dao = new HoaDonDVDAO();
		return hoaDonDV_dao.getHDDVByMaKHAndDate(maKH, tuNgay, denNgay);
	}

	public ArrayList<ChiTietDV> getChiTietDV() {
		ChiTietDVDAO chiTietDV_dao = new ChiTietDVDAO();
		return chiTietDV_dao.getChiTietDVByMaHDDV(this.maHDDV);
	}

	public double tinhTong() {
		ArrayList<ChiTietDV> dsctdv = getChiTietDV();
		double tong = 0;
		for (int i = 0; i < dsctdv.size(); i++) {
			DichVu dv = dsctdv.get(i).getDichVu();
			tong += dv.getDonGia() * dsctdv.get(i).getSoLuong();
		}
		return tong;
	}

	public boolean updateTinhTrang(int tinhTrang){
		this.tinhTrang = tinhTrang;
		HoaDonDVDAO hoaDonDV_dao = new HoaDonDVDAO();
		return hoaDonDV_dao.updateTinhTrang(this.maHDDV, tinhTrang);
	}
	
}
