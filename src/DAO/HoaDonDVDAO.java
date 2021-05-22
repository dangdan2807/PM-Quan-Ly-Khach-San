package DAO;

import java.sql.*;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.*;

public class HoaDonDVDAO {
	private static HoaDonDVDAO instance = new HoaDonDVDAO();

	public static HoaDonDVDAO getInstance() {
		return instance;
	}

	public ArrayList<HoaDonDV> getListHDDV() {
		ArrayList<HoaDonDV> dataList = new ArrayList<HoaDonDV>();
		ConnectDB.getInstance();
		PreparedStatement stmt = null;
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from HoaDonDV";
			stmt = con.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int maHD = rs.getInt("MaHDDV");
				KhachHang khachHang = new KhachHang(rs.getInt("MaKH"));
				int tinhTrang = rs.getInt("TinhTrang");
				Date ngayGioNhan = rs.getDate("NgayGioLap");

				HoaDonDV hddv = new HoaDonDV(maHD, khachHang, ngayGioNhan, tinhTrang);
				dataList.add(hddv);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataList;
	}

	public ArrayList<HoaDonDV> getListHDDVbyID(int maHDDV) {
		ArrayList<HoaDonDV> dataList = new ArrayList<HoaDonDV>();
		ConnectDB.getInstance();
		PreparedStatement stmt = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "EXEC  UDP_SearchHDDVByID ? ";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, maHDDV);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				HoaDonDV hdDV = new HoaDonDV(rs);
				dataList.add(hdDV);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataList;
	}

	public boolean create(HoaDonDV hd) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			String sql = "insert into dbo.HoaDonDV (MaKH,NgayGioLap,TinhTrang)" + " values (?, ?, ?)";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, hd.getKhachHang().getMaKH());
			stmt.setDate(2, hd.getNgayGioDat());
			stmt.setInt(3, hd.getTinhTrang());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n > 0;
	}
	public boolean update(HoaDonDV hd) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			String sql = "update dbo.HoaDonDV set MaKH = ?, NgayGioLap = ?, TinhTrang = ?"
					+ "where maHDDV = ?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, hd.getKhachHang().getMaKH());
			stmt.setDate(2, hd.getNgayGioDat());
			stmt.setInt(3, hd.getTinhTrang());
			stmt.setInt(4, hd.getMaHDDV());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n > 0;
	}
	public boolean delete(HoaDonDV hd) {
		PreparedStatement stmt = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		int n = 0;
		try {
			String sql = "delete from dbo.HoaDonV " + "where maMaHDDV = ?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, hd.getMaHDDV());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n > 0;
	}
}
