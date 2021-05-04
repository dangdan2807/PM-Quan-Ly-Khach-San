package entity;

import java.util.ArrayList;

public class QuanLyNhanVien {
    private ArrayList<Phong> dsPhong;

    public boolean themPhong(Phong p) {
        if (dsPhong.contains(p)) {
            return false;
        }
        dsPhong.add(p);
        return true;
    }

    public boolean xoaPhong(String maPhong) {
        return false;
    }
}
