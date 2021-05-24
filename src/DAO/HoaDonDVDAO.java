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

    public HoaDonDV getListHDDVbyID(int maHDDV) {
        HoaDonDV dataList = null;
        ConnectDB.getInstance();
        PreparedStatement stmt = null;
        try {
            Connection con = ConnectDB.getConnection();
            String sql = "EXEC  UDP_SearchHDDVByID ? ";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, maHDDV);

            ResultSet rs = stmt.executeQuery();
            if(!rs.next())
            	return null;
            dataList = new HoaDonDV(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataList;
    }
    public ArrayList<HoaDonDV> getListHDDVbyMaHD(int maHDDV) {
        ArrayList<HoaDonDV> dataList = new ArrayList<HoaDonDV>();
        ConnectDB.getInstance();
        PreparedStatement stmt = null;
        try {
            Connection con = ConnectDB.getConnection();
            String sql = "EXEC  UDP_SearchHDDVByID ? ";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, maHDDV);

            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
            	HoaDonDV hd = new HoaDonDV(rs);
            	dataList.add(hd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    public boolean insert(HoaDonDV hd) {
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
            String sql = "update dbo.HoaDonDV set MaKH = ?, NgayGioLap = ?, TinhTrang = ?" + "where maHDDV = ?";
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

    public boolean delete(int id) {
        int n = 0;
        PreparedStatement stmt = null;
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        String query = "delete from dbo.HoaDonDV where MaHDDV = ?";
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

    public ArrayList<HoaDonDV> getAllHDDV() {
        ArrayList<HoaDonDV> dataList = new ArrayList<HoaDonDV>();
        ConnectDB.getInstance();
        PreparedStatement stmt = null;
        Connection con = ConnectDB.getConnection();
        try {
            String sql = "select * from HoaDonDV";
            stmt = con.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int MaHDDV = rs.getInt("MaHDDV");
                Date ngayGioDat = rs.getDate("NgayGioLap");
                KhachHang maKhachHang = new KhachHang(rs.getInt("MaKH"));
                int tt = rs.getInt("TinhTrang");
                // HoaDonPhong ctdv = new HoaDonPhong(rs);
                HoaDonDV hddv = new HoaDonDV(MaHDDV, maKhachHang, ngayGioDat, tt);
                dataList.add(hddv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    public ArrayList<HoaDonDV> getHDDVByMaKH(int maKH) {
        ArrayList<HoaDonDV> dataList = new ArrayList<HoaDonDV>();
        ConnectDB.getInstance();
        PreparedStatement stmt = null;
        Connection con = ConnectDB.getConnection();
        try {
            String sql = "select * from HoaDonDV where maKH = ? order by MaHDDV DESC";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, maKH);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int MaHDDV = rs.getInt("MaHDDV");
                Date ngayGioDat = rs.getDate("NgayGioLap");
                KhachHang khachHang = new KhachHang(rs.getInt("MaKH"));
                HoaDonDV hddv = new HoaDonDV(MaHDDV, ngayGioDat, khachHang);
                dataList.add(hddv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    public ArrayList<HoaDonDV> getHDDVByMaKHAndDate(int MaKH, Date tuNgay, Date denNgay) {
        ArrayList<HoaDonDV> dataList = new ArrayList<HoaDonDV>();
        // tuNgay.setTime(tuNgay.getTime()-86400000);
        // denNgay.setTime(denNgay.getTime()+86400000);
        try {
            ConnectDB.getInstance();
            Connection conn = ConnectDB.getConnection();

            String sql = "Select * from HoaDonDV where maKH = ? and NgayGioLap >= ? and NgayGioLap <= ? and TinhTrang = 0 order by MaHDDV DESC";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, MaKH);
            statement.setDate(2, tuNgay);
            statement.setDate(3, denNgay);
            System.out.println(MaKH);
            System.out.println(tuNgay);
            System.out.println(denNgay);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int MaHDDV = rs.getInt("MaHDDV");
                Date ngayGioDat = rs.getDate("NgayGioLap");
                KhachHang khachHang = new KhachHang(rs.getInt("MaKH"));
                HoaDonDV hddv = new HoaDonDV(MaHDDV, ngayGioDat, khachHang);
                dataList.add(hddv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    public boolean thanhToan(int maHDDV) {
        int n = 0;
        PreparedStatement stmt = null;
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        String query = "update dbo.HoaDonDV set tinhTrang = 1 Where MaHDDV = ?";
        try {
            stmt = con.prepareStatement(query);
            stmt.setInt(1, maHDDV);
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

    public boolean updateTinhTrang(int maHDDV, int tinhTrang){
        
        int n = 0;
        PreparedStatement stmt = null;
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        String query = "update dbo.HoaDonDV set tinhTrang = ? Where maHDDV = ?";
        try {
            stmt = con.prepareStatement(query);
            stmt.setInt(1, tinhTrang);
            stmt.setInt(2, maHDDV);
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

    public int getLatestID() {
        int id = 0;
        ConnectDB.getInstance();
        Statement stmt = null;
        try {
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT * FROM dbo.HoaDonDV";
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = stmt.executeQuery(sql);
            rs.last();
            id = rs.getInt("MaHDDV");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return id;
    }

}
