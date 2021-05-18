package DAO;

import java.sql.*;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.LoaiPhong;
import entity.Phong;

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

    
}
