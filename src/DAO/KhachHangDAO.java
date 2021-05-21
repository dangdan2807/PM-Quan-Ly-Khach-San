package DAO;

import java.util.*;

import connectDB.ConnectDB;

import java.sql.*;

import entity.KhachHang;

public class KhachHangDAO {
    private static KhachHangDAO instance = new KhachHangDAO();

    public static KhachHangDAO getInstance() {
        return instance;
    }

    public ArrayList<KhachHang> getListKhachHang() {
        ArrayList<KhachHang> dataList = new ArrayList<KhachHang>();
        ConnectDB.getInstance();
        Statement stmt = null;
        try {
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT * FROM dbo.KhachHang";
            stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                KhachHang dv = new KhachHang(rs);
                dataList.add(dv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dataList;
    }

    public int getLatestID() {
        int id = 0;
        ConnectDB.getInstance();
        Statement stmt = null;
        try {
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT * FROM dbo.KhachHang";
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = stmt.executeQuery(sql);
            rs.last();
            id = rs.getInt("MaKH");
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

    public ArrayList<KhachHang> getListKhachHangByName(String name) {
        ArrayList<KhachHang> dataList = new ArrayList<KhachHang>();
        ConnectDB.getInstance();
        PreparedStatement stmt = null;
        try {
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT * FROM dbo.KhachHang dv where dv.TenKH like % ? %";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "%" + name + "%");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                KhachHang dv = new KhachHang(rs);
                dataList.add(dv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dataList;
    }

    public KhachHang getKhachHangByMaKH(int maKH) {
        KhachHang khachHang = null;
        ConnectDB.getInstance();
        PreparedStatement stmt = null;
        try {
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT * FROM dbo.KhachHang dv where maKH = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, maKH);

            ResultSet rs = stmt.executeQuery();
            if(!rs.next())
                return null;
            
            khachHang = new KhachHang(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return khachHang;
    }

    public boolean create(KhachHang kh) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            String sql = "insert into dbo.KhachHang (TenKH, CMND, NgayHetHan, LoaiKH, soLanDatPhong)"
                    + " values (?, ?, ?, ?, ?)";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, kh.getTenKH());
            stmt.setString(2, kh.getCmnd());
            stmt.setDate(3, kh.getNgayHetHan());
            stmt.setString(4, kh.getLoaiKH());
            stmt.setInt(5, kh.getSoLanDatPhong());
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

    public boolean update(KhachHang dv) {
        PreparedStatement stmt = null;
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        int n = 0;
        try {
            String sql = "update dbo.KhachHang "
                    + " set tenKH = ?, CMND = ?, NgayHetHan = ?, LoaiKH = ?, soLanDatPhong = ? " + " where maKH = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, dv.getTenKH());
            stmt.setString(2, dv.getCmnd());
            stmt.setDate(3, dv.getNgayHetHan());
            stmt.setString(4, dv.getLoaiKH());
            stmt.setInt(5, dv.getSoLanDatPhong());
            stmt.setInt(6, dv.getMaKH());
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

    public boolean delete(KhachHang dv) {
        PreparedStatement stmt = null;
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        int n = 0;
        try {
            String sql = "delete from dbo.KhachHang " + "where maKH = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, dv.getMaKH());
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
