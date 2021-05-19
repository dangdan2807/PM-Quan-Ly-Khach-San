package DAO;

import java.sql.*;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.*;

public class HoaDonPhongDAO {
    private static HoaDonPhongDAO instance = new HoaDonPhongDAO();

    public static HoaDonPhongDAO getInstance() {
        return instance;
    }

    public ArrayList<HoaDonPhong> getAllHDPhong() {
        ArrayList<HoaDonPhong> dataList = new ArrayList<HoaDonPhong>();
        ConnectDB.getInstance();
        PreparedStatement stmt = null;
        Connection con = ConnectDB.getConnection();
        try {
            String sql = "select * from HoaDonPhong";
            stmt = con.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int maHD = rs.getInt("MaHD");
                int tinhTrang = rs.getInt("TinhTrang");
                Date ngayGioNhan = rs.getDate("NgayGioNhan");
                Date ngayGioTra = rs.getDate("NgayGioTra");
                Phong phong = new Phong(rs.getInt("MaPhong"));
                KhachHang khachHang = new KhachHang(rs.getInt("MaKH"));

                // HoaDonPhong ctdv = new HoaDonPhong(rs);
                HoaDonPhong hdp = new HoaDonPhong(maHD, tinhTrang,ngayGioNhan, ngayGioTra, phong, khachHang);
                dataList.add(hdp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataList;
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
                HoaDonPhong hdP = new HoaDonPhong(rs);
                dataList.add(hdP);
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
                HoaDonPhong hdP = new HoaDonPhong(rs);
                dataList.add(hdP);
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

    public boolean insert(HoaDonPhong hdp){
        int n = 0;
        try{
            ConnectDB.getInstance();
            Connection conn = ConnectDB.getConnection();

            String sql = "insert into HoaDonPhong values(?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, hdp.getKhachHang().getMaKH());
            statement.setInt(2, 1);
            statement.setString(3, hdp.getPhong().getMaPhong());
            statement.setDate(4, hdp.getNgayGioNhan());
            statement.setDate(5, hdp.getNgayGioTra());
            n = statement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return n > 0;
    }

    public ArrayList<Timestamp> getDateTimeHDPhongByMaHD(int maHD) {
        ArrayList<Timestamp> dataList = new ArrayList<Timestamp>();
        ConnectDB.getInstance();
        PreparedStatement stmt = null;
        try {
            Connection con = ConnectDB.getConnection();
            String sql = "EXEC UDP_GetDateTimeHDPhongByMaHD ? ";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, maHD);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                dataList.add(rs.getTimestamp("NgayGioNhan"));
                dataList.add(rs.getTimestamp("NgayGioTra"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataList;
    }
}
