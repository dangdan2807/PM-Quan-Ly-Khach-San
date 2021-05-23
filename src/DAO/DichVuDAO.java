package DAO;

import java.util.*;

import connectDB.ConnectDB;

import java.sql.*;

import entity.DichVu;

public class DichVuDAO {
    private static DichVuDAO instance = new DichVuDAO();

    public static DichVuDAO getInstance() {
        return instance;
    }

    public ArrayList<DichVu> getListDichVu() {
        ArrayList<DichVu> dataList = new ArrayList<DichVu>();
        ConnectDB.getInstance();
        Statement stmt = null;
        try {
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT * FROM dbo.DichVu";
            stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                DichVu dv = new DichVu(rs);
                dataList.add(dv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    public int getLatestID() {
        int id = 0;
        ConnectDB.getInstance();
        Statement stmt = null;
        try {
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT * FROM dbo.DichVu";
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = stmt.executeQuery(sql);
            rs.last();
            id = rs.getInt("MaDV");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public ArrayList<DichVu> getListDichVuByName(String name) {
        ArrayList<DichVu> dataList = new ArrayList<DichVu>();
        ConnectDB.getInstance();
        PreparedStatement stmt = null;
        try {
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT * FROM dbo.DichVu dv where dv.TenDV like ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "%" + name + "%");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                DichVu dv = new DichVu(rs);
                dataList.add(dv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    public boolean insert(DichVu dv) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            String sql = "insert into dbo.DichVu (TenDV, DonGia) values(?, ?)";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, dv.getTenDV());
            stmt.setDouble(2, dv.getDonGia());
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

    public boolean update(DichVu dv) {
        PreparedStatement stmt = null;
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        int n = 0;
        try {
            String sql = "update dbo.DichVu set tenDV = ?, donGia = ? where maDV = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, dv.getTenDV());
            stmt.setDouble(2, dv.getDonGia());
            stmt.setInt(3, dv.getMaDV());
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

    public boolean delete(DichVu dv) {
        PreparedStatement stmt = null;
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        int n = 0;
        try {
            String sql = "delete from dbo.DichVu " + "where maDV = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, dv.getMaDV());
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
//    public DichVu getListDVbyIDHD(int maHDDV) {
//    	DichVu dataList = null;
//        ConnectDB.getInstance();
//        PreparedStatement stmt = null;
//        try {
//            Connection con = ConnectDB.getConnection();
//            String sql = "EXEC  UDP_SearchHDDVByID ?";
//            stmt = con.prepareStatement(sql);
//            stmt.setInt(1, );
// //in list DV khi ấn vào bảng HDDV           
//            ResultSet rs = stmt.executeQuery();
//            if(!rs.next())
//            	return null;
//            dataList = new DichVu(rs);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return dataList;
//    }
}
