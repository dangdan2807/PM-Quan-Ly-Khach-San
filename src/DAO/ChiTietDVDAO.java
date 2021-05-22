package DAO;

import java.sql.*;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.ChiTietDV;
import entity.DichVu;

public class ChiTietDVDAO {
    private static ChiTietDVDAO instance = new ChiTietDVDAO();

    public static ChiTietDVDAO getInstance() {
        return instance;
    }

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

    public ArrayList<ChiTietDV> getChiTietDVByMaHDDV(int maHDDV){
        ArrayList<ChiTietDV> dataList = new ArrayList<ChiTietDV>();
        ConnectDB.getInstance();
        PreparedStatement stmt = null;
        try {
            Connection con = ConnectDB.getConnection();
            String sql = "select * from ChiTietDV join DichVu on chiTietDV.maDV = DichVu.maDV where maHDDV = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, maHDDV);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                
                int soLuong = rs.getInt("SoLuong");
                
                int maDV = rs.getInt("maDV");
                String tenDV = rs.getString("tenDV");
                double donGia = rs.getDouble("DonGia");
                DichVu dv = new DichVu(maDV, tenDV, donGia);
                ChiTietDV ctdv = new ChiTietDV(dv, soLuong);
                
                dataList.add(ctdv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataList;
    }
}
