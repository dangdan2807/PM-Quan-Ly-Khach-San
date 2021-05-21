package DAO;

import java.sql.*;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.*;

public class LoaiPhongDAO {
    private static LoaiPhongDAO instance = new LoaiPhongDAO();

    public static LoaiPhongDAO getInstance() {
        return instance;
    }

    public ArrayList<LoaiPhong> getAllLoaiPhong(){
        ArrayList<LoaiPhong> dslp = new ArrayList<LoaiPhong>();
        try{
            ConnectDB.getInstance();
            Connection conn = ConnectDB.getConnection();

            String sql = "Select * from LoaiPhong";
            Statement statement = conn.createStatement();

            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()){
                int maLoaiPhong = rs.getInt(1);
                String tenLoaiPhong = rs.getString(2);
                Double donGia = rs.getDouble(3);
                
                LoaiPhong lp = new LoaiPhong(maLoaiPhong, tenLoaiPhong, donGia);
                dslp.add(lp);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return dslp;
    }

    public LoaiPhong getLoaiPhongByMa(int maLoaiPhong){
        LoaiPhong lp = null;
        try{
            ConnectDB.getInstance();
            Connection conn = ConnectDB.getConnection();

            String sql = "Select * from LoaiPhong where MaLoaiPhong = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, maLoaiPhong);
            ResultSet rs = statement.executeQuery();

            if(!rs.next())
                return null;
            String tenLoaiPhong = rs.getString(2);
            Double donGia = rs.getDouble(3);
            lp = new LoaiPhong(maLoaiPhong, tenLoaiPhong, donGia);

        }catch(SQLException e){
            e.printStackTrace();
        }
        return lp;
    }

    
    public ArrayList<LoaiPhong> getAllListLoaiPhong() {
        ArrayList<LoaiPhong> dataList = new ArrayList<LoaiPhong>();
        Statement stmt = null;
        ResultSet rs = null;
        String query = "SELECT * FROM dbo.LoaiPhong";
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        try {
            stmt = con.createStatement();

            rs = stmt.executeQuery(query);
            while (rs.next()) {
                LoaiPhong loaiPhong = new LoaiPhong(rs);
                dataList.add(loaiPhong);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    public ArrayList<LoaiPhong> getListLoaiPhongByName(String name) {
        ArrayList<LoaiPhong> dataList = new ArrayList<LoaiPhong>();
        PreparedStatement stmt = null;
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        String query = "Select * from dbo.LoaiPhong where TenLoaiPhong like ?";
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, "%" + name + "%");

            rs = stmt.executeQuery();
            while (rs.next()) {
                LoaiPhong loaiPhong = new LoaiPhong(rs);
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

    public int getLatestID() {
        int id = 0;
        ConnectDB.getInstance();
        Statement stmt = null;
        Connection con = ConnectDB.getConnection();
        String query = "Select * from dbo.LoaiPhong";
        ResultSet rs = null;
        try {
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            rs = stmt.executeQuery(query);
            rs.last();
            id = rs.getInt("MaLoaiPhong");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public boolean insert(LoaiPhong loaiPhong) {
        int n = 0;
        ConnectDB.getInstance();
        PreparedStatement stmt = null;
        Connection con = ConnectDB.getConnection();
        String query = "insert into dbo.LoaiPhong (TenLoaiPhong, DonGia) values (?, ?)";
        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, loaiPhong.getTenLoaiPhong());
            stmt.setDouble(2, loaiPhong.getDonGia());

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
        String query = "delete from dbo.LoaiPhong where maLoaiPhong = ?";
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

    public boolean update(LoaiPhong loaiPhong) {
        int n = 0;
        PreparedStatement stmt = null;
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        String query = "update dbo.LoaiPhong set TenLoaiPhong = ?, donGia = ?" + " Where maLoaiPhong = ?";
        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, loaiPhong.getTenLoaiPhong());
            stmt.setDouble(2, loaiPhong.getDonGia());
            stmt.setInt(3, loaiPhong.getMaLoaiPhong());

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
