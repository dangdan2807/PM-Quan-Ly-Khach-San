package DAO;

import java.sql.*;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.HoaDonPhong;

public class HoaDonPhongDAO {
    private static HoaDonPhongDAO instance = new HoaDonPhongDAO();

    public static HoaDonPhongDAO getInstance() {
        return instance;
    }

    public ArrayList<HoaDonPhong> getListHDPhongByDate(Date tuNgay, Date denNgay) {
        ArrayList<HoaDonPhong> dataList = new ArrayList<HoaDonPhong>();
        ConnectDB.getInstance();
        PreparedStatement stmt = null;
        Connection con = ConnectDB.getConnection();
        try {
            String sql = "EXEC UDP_SearchHDPhongByDate ? , ? ";
            stmt = con.prepareStatement(sql);
            stmt.setDate(1, tuNgay);
            stmt.setDate(2, denNgay);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                HoaDonPhong ctdv = new HoaDonPhong(rs);
                dataList.add(ctdv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    public ArrayList<HoaDonPhong> getListHDPhongByMaKH(int maKH, Date tuNgay, Date denNgay) {
        ArrayList<HoaDonPhong> dataList = new ArrayList<HoaDonPhong>();
        ConnectDB.getInstance();
        PreparedStatement stmt = null;
        try {
            Connection con = ConnectDB.getConnection();
            String sql = "EXEC UDP_SearchHDPhongByMaKH ? , ? , ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, maKH);
            stmt.setDate(2, tuNgay);
            stmt.setDate(3, denNgay);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                HoaDonPhong ctdv = new HoaDonPhong(rs);
                dataList.add(ctdv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    public ArrayList<HoaDonPhong> getListHDPhongByTenKH(String tenKH, Date tuNgay, Date denNgay) {
        ArrayList<HoaDonPhong> dataList = new ArrayList<HoaDonPhong>();
        ConnectDB.getInstance();
        PreparedStatement stmt = null;
        try {
            Connection con = ConnectDB.getConnection();
            String sql = "EXEC UDP_SearchHDPhongByTenKH ? , ? , ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, tenKH);
            stmt.setDate(2, tuNgay);
            stmt.setDate(3, denNgay);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                HoaDonPhong ctdv = new HoaDonPhong(rs);
                dataList.add(ctdv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    public ArrayList<HoaDonPhong> getListHDPhongByMaKHAndTenKH(int maKH, String tenKH, Date tuNgay, Date denNgay) {
        ArrayList<HoaDonPhong> dataList = new ArrayList<HoaDonPhong>();
        ConnectDB.getInstance();
        PreparedStatement stmt = null;
        try {
            Connection con = ConnectDB.getConnection();
            String sql = "EXEC UDP_SearchHDPhongByMaKHAndTenKH ? , ? , ?, ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, maKH);
            stmt.setString(2, tenKH);
            stmt.setDate(3, tuNgay);
            stmt.setDate(4, denNgay);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                HoaDonPhong ctdv = new HoaDonPhong(rs);
                dataList.add(ctdv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataList;
    }
}
