package DAO;

import java.sql.*;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.KhachHang;
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

    public Phong getPhongByMaPhong(String maPhong){
        Phong phong = null;
        try{
            ConnectDB.getInstance();
            Connection conn = ConnectDB.getConnection();

            String sql = "Select * from Phong where MaPhong = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, maPhong);
            ResultSet rs = statement.executeQuery();

            if(!rs.next())
                return null;
            
            phong = new Phong(rs);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return phong;
    }

    public ArrayList<Phong> getListPhong() {
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

    public ArrayList<Phong> getListPhongByID(String ID) {
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

    public boolean insert(Phong phong) {
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

    public KhachHang getKHDangSuDungPhong(String maPhong){
        long ml=System.currentTimeMillis(); 
        ml = ml/86400000*86400000;
        Date now = new Date(ml);
        KhachHang khachHang = null;
        try{
            ConnectDB.getInstance();
            Connection conn = ConnectDB.getConnection();

            String sql = "Select * from HoaDonPhong where MaPhong = ? and NgayGioNhan <= ? and NgayGioTra >= ? and TinhTrang = 1";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, maPhong);
            statement.setDate(2, now);
            statement.setDate(3, now);
            System.out.println(statement);
            ResultSet rs = statement.executeQuery();

            if(!rs.next())
                return null;
            
            khachHang = new KhachHang(rs.getInt("MaKH"));
        }catch(SQLException e){
            e.printStackTrace();
        }
        return khachHang;
    }

    public KhachHang getKHDaDatPhong(String maPhong){
        long ml=System.currentTimeMillis(); 
        ml = ml/86400000*86400000;
        Date now = new Date(ml);
        KhachHang khachHang = null;
        try{
            ConnectDB.getInstance();
            Connection conn = ConnectDB.getConnection();

            String sql = "Select * from HoaDonPhong where MaPhong = ? and NgayGioNhan <= ? and NgayGioTra >= ? and TinhTrang = 0";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, maPhong);
            statement.setDate(2, now);
            statement.setDate(3, now);
            
            ResultSet rs = statement.executeQuery();

            if(!rs.next())
                return null;
            
            khachHang = new KhachHang(rs.getInt("MaKH"));
        }catch(SQLException e){
            e.printStackTrace();
        }
        return khachHang;
    }

    public boolean updateTinhTrang(String maPhong, int tinhTrang) {
        int n = 0;
        PreparedStatement stmt = null;
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        String query = "update dbo.Phong set tinhTrang = ? Where maPhong = ?";
        try {
            stmt = con.prepareStatement(query);
            stmt.setInt(1, tinhTrang);
            stmt.setString(2, maPhong);
            System.out.println(maPhong);
            System.out.println(tinhTrang);
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

    public boolean check_status_avail(String maPhong) {
        int n = 0;
        PreparedStatement stmt = null;
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        String query = "select * from dbo.HoaDonPhong Where maPhong = ? and tinhTrang = 0";
        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, maPhong);
            ResultSet rs = stmt.executeQuery();

            if(!rs.next()){ // hết hóa đơn đặt phòng này
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
