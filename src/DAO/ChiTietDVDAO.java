package DAO;

import java.sql.*;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.ChiTietDV;
import entity.DichVu;
import entity.HoaDonDV;
import entity.KhachHang;

public class ChiTietDVDAO {
	private static ChiTietDVDAO instance = new ChiTietDVDAO();

	public static ChiTietDVDAO getInstance() {
		return instance;
	}

//    public ArrayList<ChiTietDV> getAllCTDV() {
//        ArrayList<ChiTietDV> dataList = new ArrayList<ChiTietDV>();
//        ConnectDB.getInstance();
//        PreparedStatement stmt = null;
//        Connection con = ConnectDB.getConnection();
//        try {
//            String sql = "select * from ChiTietDV";
//            stmt = con.prepareStatement(sql);
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                HoaDonDV maHD = new HoaDonDV(rs.getInt("MaHDDV"));
//                DichVu maDV = new DichVu(rs.getInt("MaDV"));
//                int soLuong = rs.getInt("SoLuong");
//                Date ngayGioDat = rs.getDate("NgayGioDat");
//                // HoaDonPhong ctdv = new HoaDonPhong(rs);
//                ChiTietDV ctdv = new ChiTietDV(soLuong, maHD, maDV);
//                dataList.add(ctdv);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return dataList;
//    }
	// vua them getlist
	public ArrayList<ChiTietDV> getListChiTietDVByDate(Date tuNgay, Date denNgay) {
		ArrayList<ChiTietDV> dataList = new ArrayList<ChiTietDV>();
		ConnectDB.getInstance();
		PreparedStatement stmt = null;
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "EXEC UDP_SearchCTHDbyDate ? , ? ";
			stmt = con.prepareStatement(sql);
			stmt.setDate(1, tuNgay);
			stmt.setDate(2, denNgay);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ChiTietDV ctdv = new ChiTietDV(rs);
				dataList.add(ctdv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataList;
	}

	public ArrayList<ChiTietDV> getListChiTietDVByMaKH(int maKH, Date tuNgay, Date denNgay) {
		ArrayList<ChiTietDV> dataList = new ArrayList<ChiTietDV>();
		ConnectDB.getInstance();
		PreparedStatement stmt = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "EXEC UDP_SearchCTHDByMaHK ? , ? , ?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, maKH);
			stmt.setDate(2, tuNgay);
			stmt.setDate(3, denNgay);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ChiTietDV ctdv = new ChiTietDV(rs);
				dataList.add(ctdv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataList;
	}

	public ArrayList<ChiTietDV> getListChiTietDVByTenKH(String tenKH, Date tuNgay, Date denNgay) {
		ArrayList<ChiTietDV> dataList = new ArrayList<ChiTietDV>();
		ConnectDB.getInstance();
		PreparedStatement stmt = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "EXEC UDP_SearchCTHDByTenKH ? , ? , ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, tenKH);
			stmt.setDate(2, tuNgay);
			stmt.setDate(3, denNgay);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ChiTietDV ctdv = new ChiTietDV(rs);
				dataList.add(ctdv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataList;
	}

	public ArrayList<ChiTietDV> getListChiTietDVByMaKHAndTenKH(int maKH, String tenKH, Date tuNgay, Date denNgay) {
		ArrayList<ChiTietDV> dataList = new ArrayList<ChiTietDV>();
		ConnectDB.getInstance();
		PreparedStatement stmt = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "EXEC UDP_SearchCTHDByMaKHAndTenKH ? , ? , ?, ?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, maKH);
			stmt.setString(2, tenKH);
			stmt.setDate(3, tuNgay);
			stmt.setDate(4, denNgay);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ChiTietDV ctdv = new ChiTietDV(rs);
				dataList.add(ctdv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataList;
	}

	public ArrayList<ChiTietDV> getChiTietDVByMaHDDV(int maHDDV) {
		ArrayList<ChiTietDV> dataList = new ArrayList<ChiTietDV>();
		ConnectDB.getInstance();
		PreparedStatement stmt = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql ="EXEC  UDP_SearchHDDVByID ? ";
					//"select * from ChiTietDV join DichVu on chiTietDV.maDV = DichVu.maDV where maHDDV = ?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, maHDDV);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int soLuong = rs.getInt("SoLuong");
				int maDV = rs.getInt("maDV");
				String tenDV = rs.getString("tenDV");
				double donGia = rs.getDouble("DonGia");
				DichVu dv = new DichVu(maDV, tenDV, donGia);
				Date date = rs.getDate("NgayGioDat");
				int maHD = rs.getInt("MaHDct");
				HoaDonDV hddv = new HoaDonDV(maHD);
				ChiTietDV ctdv = new ChiTietDV(soLuong, date, hddv ,dv);
				dataList.add(ctdv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataList;
	}

	public boolean create(ChiTietDV ctdv) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			String sql = "insert into dbo.ChiTietDV (MaDV,SoLuong)" + " values (?, ?)";
			stmt = con.prepareStatement(sql);
			//stmt.setInt(1, ctdv.getHoaDonDV().getMaHDDV());
			stmt.setInt(1, ctdv.getDichVu().getMaDV());
			stmt.setInt(2, ctdv.getSoLuong());
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
	public boolean delete(int id) {
        int n = 0;
        PreparedStatement stmt = null;
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        String query = "delete from dbo.ChiTietDV where maDV = ?";
        try {
            stmt = con.prepareStatement(query);
            stmt.setInt(1, id);

            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return n > 0;
    }
	public boolean updateByID(int id) {
        PreparedStatement stmt = null;
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        int n = 0;
        try {
            String sql = "update dbo.ChiTietDV set maHDDV = ? where maHDDV is null";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
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
	public boolean update(ChiTietDV ctdv) {
        PreparedStatement stmt = null;
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        int n = 0;
        try {
            String sql = "update dbo.ChiTietDV set maDV = ?, SoLuong = ?, NgayGioDat = ?"+ "where maHDDV = ? ";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, ctdv.getDichVu().getMaDV());
            stmt.setInt(2, ctdv.getSoLuong() );
            stmt.setDate(3, ctdv.getNgayGioDat());
            stmt.setInt(4, ctdv.getHoaDonDV().getMaHDDV());
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
