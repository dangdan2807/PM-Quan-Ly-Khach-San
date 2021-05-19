package DAO;

import java.sql.*;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.Phong;

public class PhongDAO {
    private static PhongDAO instance = new PhongDAO();

    public static PhongDAO getInstance() {
        return instance;
    }

    public ArrayList<Phong> getAllListLoaiPhong() {
        ArrayList<Phong> dataList = new ArrayList<Phong>();
        ConnectDB.getInstance();
        Statement stmt = null;
        ResultSet rs = null;
        String query = "SELECT * FROM dbo.Phong";
        Connection con = ConnectDB.getConnection();
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Phong phong = new Phong(rs);
                dataList.add(phong);
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

    public ArrayList<Phong> getListLoaiPhongByID(String ID) {
        ArrayList<Phong> dataList = new ArrayList<Phong>();
        PreparedStatement stmt = null;
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        String query = "Select * from dbo.Phong where MaPhong like ?";
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, "%" + ID + "%");

            rs = stmt.executeQuery();
            while (rs.next()) {
                Phong loaiPhong = new Phong(rs);
                dataList.add(loaiPhong);
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

    public boolean create(Phong phong) {
        int n = 0;
        ConnectDB.getInstance();
        PreparedStatement stmt = null;
        Connection con = ConnectDB.getConnection();
        String query = "insert into dbo.Phong (MaPhong, SucChua, SoGiuong, ViTri, TinhTrang, MaLoaiPhong) "
                + " values (?, ?, ?, ?, ?, ?)";
        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, phong.getMaPhong());
            stmt.setInt(2, phong.getSucChua());
            stmt.setInt(3, phong.getSoGiuong());
            stmt.setString(4, phong.getViTri());
            stmt.setInt(5, phong.getTinhTrang());
            stmt.setInt(6, phong.getLoaiPhong().getMaLoaiPhong());

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

    public boolean delete(String id) {
        int n = 0;
        PreparedStatement stmt = null;
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        String query = "delete from dbo.Phong where MaPhong = ?";
        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, id);

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

    public boolean update(Phong phong) {
        int n = 0;
        PreparedStatement stmt = null;
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        String query = "update dbo.Phong set SucChua = ?, SoGiuong = ?, ViTri = ?, TinhTrang = ?, MaLoaiPhong = ?"
                + " Where MaPhong = ?";
        try {
            stmt = con.prepareStatement(query);
            stmt.setInt(1, phong.getSucChua());
            stmt.setInt(2, phong.getSoGiuong());
            stmt.setString(3, phong.getViTri());
            stmt.setInt(4, phong.getTinhTrang());
            stmt.setInt(5, phong.getLoaiPhong().getMaLoaiPhong());
            stmt.setString(6, phong.getMaPhong());

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
}
