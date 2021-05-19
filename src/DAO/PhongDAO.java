package DAO;

import java.sql.*;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.LoaiPhong;
import entity.Phong;

public class PhongDAO {
    private static PhongDAO instance = new PhongDAO();

    public static PhongDAO getInstance() {
        return instance;
    }

    public ArrayList<Phong> getAllPhong(){
        ArrayList<Phong> dsp = new ArrayList<Phong>();
        try{
            ConnectDB.getInstance();
            Connection conn = ConnectDB.getConnection();

            String sql = "Select * from Phong";
            Statement statement = conn.createStatement();

            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()){
                Phong phong = new Phong(rs);
                dsp.add(phong);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return dsp;
    }

    public ArrayList<Phong> getPhongAvail(){
        ArrayList<Phong> dsp = new ArrayList<Phong>();
        try{
            ConnectDB.getInstance();
            Connection conn = ConnectDB.getConnection();

            String sql = "Select * from Phong where TinhTrang = 0";
            Statement statement = conn.createStatement();

            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()){
                Phong phong = new Phong(rs);
                dsp.add(phong);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return dsp;
    }

    public ArrayList<Phong> getPhongByMaLoaiPhong(int maLoaiPhong){
        ArrayList<Phong> dsp = new ArrayList<Phong>();
        try{
            ConnectDB.getInstance();
            Connection conn = ConnectDB.getConnection();

            String sql = "Select * from Phong where MaLoaiPhong = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, maLoaiPhong);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                Phong phong = new Phong(rs);
                dsp.add(phong);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return dsp;
    }

    public Phong getPhongByMaPhong(int maPhong){
        Phong phong = null;
        try{
            ConnectDB.getInstance();
            Connection conn = ConnectDB.getConnection();

            String sql = "Select * from Phong where MaPhong = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, maPhong);
            ResultSet rs = statement.executeQuery();

            if(!rs.next())
                return null;
            
            phong = new Phong(rs);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return phong;
    }

    public boolean insert(Phong phong){
        int n = 0;
        try{
            ConnectDB.getInstance();
            Connection conn = ConnectDB.getConnection();

            String sql = "insert into Phong values(?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, phong.getLoaiPhong().getMaLoaiPhong());
            statement.setInt(2, phong.getSucChua());
            statement.setInt(3, phong.getSoGiuong());
            statement.setString(4, phong.getViTri());
            statement.setBoolean(5, phong.getTinhTrang());
            n = statement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return n > 0;
    }
}
