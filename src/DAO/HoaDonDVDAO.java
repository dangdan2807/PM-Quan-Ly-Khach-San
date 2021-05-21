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
                Date ngayGioDat = rs.getDate("NgayGioDat");
                KhachHang khachHang = new KhachHang(rs.getInt("MaKH"));

                // HoaDonPhong ctdv = new HoaDonPhong(rs);
                HoaDonDV hddv = new HoaDonDV(MaHDDV, ngayGioDat, khachHang);
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
                Date ngayGioDat = rs.getDate("NgayGioDat");
                KhachHang khachHang = new KhachHang(rs.getInt("MaKH"));

                // HoaDonPhong ctdv = new HoaDonPhong(rs);
                HoaDonDV hddv = new HoaDonDV(MaHDDV, ngayGioDat, khachHang);
                dataList.add(hddv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    // public HoaDonPhong getHDDVByMaKHAndDate(int MaKH, Date tuNgay, Date denNgay){
        
    //     long ml=System.currentTimeMillis(); 
    //     ml = ml/86400000*86400000;
    //     Date now = new Date(ml);
    //     HoaDonPhong hdp = null;
    //     try{
    //         ConnectDB.getInstance();
    //         Connection conn = ConnectDB.getConnection();

    //         String sql = "Select * from HoaDonDV where maKH = ? and NgayGioNhan <= ? and NgayGioTra >= ? and TinhTrang = 1 order by MaHDDV DESC";
    //         PreparedStatement statement = conn.prepareStatement(sql);
    //         statement.setString(1, MaKH);
    //         statement.setDate(2, now);
    //         statement.setDate(3, now);
    //         ResultSet rs = statement.executeQuery();

    //         if(!rs.next())
    //             return null;
            
    //         int maHD = rs.getInt("MaHD");
    //         int tinhTrang = rs.getInt("TinhTrang");
    //         Date ngayGioNhan = rs.getDate("NgayGioNhan");
    //         Date ngayGioTra = rs.getDate("NgayGioTra");
    //         Phong phong = new Phong(rs.getString("MaPhong"));
    //         KhachHang khachHang = new KhachHang(rs.getInt("MaKH"));

    //         // HoaDonPhong ctdv = new HoaDonPhong(rs);
    //         hdp = new HoaDonPhong(maHD, tinhTrang, ngayGioNhan, ngayGioTra, phong, khachHang);
    //     }catch(SQLException e){
    //         e.printStackTrace();
    //     }
    //     return hdp;
        
    // }

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
