package application;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.*;

import DAO.HoaDonPhongDAO;
import connectDB.ConnectDB;
import entity.*;

public class DialogLichDatPhong extends JDialog implements ActionListener {
    private DefaultTableModel modelTable;
    private JTable table;
    private kDatePicker dpTuNgay, dpDenNgay;
    private JButton btnXem;
    HoaDonPhongDAO dhPhongDAO = new HoaDonPhongDAO();
    ArrayList<HoaDonPhong> hdPhong = new ArrayList<HoaDonPhong>();
    String maPhong = "";
    private JLabel lbTitle;

    public DialogLichDatPhong() {
        try {
            ConnectDB.getInstance().connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        setTitle("Lịch Đặt Phòng ");
        setSize(950, 450);
        setLocationRelativeTo(null);

        JPanel pnMain = new JPanel();
        this.add(pnMain, BorderLayout.CENTER);
        pnMain.setLayout(new BorderLayout(0, 0));

        JPanel pnTitle = new JPanel();
        pnMain.add(pnTitle, BorderLayout.NORTH);

        lbTitle = new JLabel("Lịch Đặt Phòng ");
        lbTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
        pnTitle.add(lbTitle);

        JPanel pnView = new JPanel();
        pnMain.add(pnView, BorderLayout.CENTER);
        pnView.setLayout(new BorderLayout(0, 0));

        JPanel pnInput = new JPanel();
        pnView.add(pnInput, BorderLayout.NORTH);
        pnInput.setLayout(null);
        pnInput.setPreferredSize(new Dimension(pnView.getWidth(), 30));

        JLabel lbTuNgay = new JLabel("Từ ngày: ");
        lbTuNgay.setBounds(12, 6, 70, 20);
        pnInput.add(lbTuNgay);

        dpTuNgay = new kDatePicker();
        dpTuNgay.setBounds(80, 6, 150, 20);
        pnInput.add(dpTuNgay);

        JLabel lbDenNgay = new JLabel("Đến ngày: ");
        lbDenNgay.setBounds(248, 6, 70, 20);
        pnInput.add(lbDenNgay);

        dpDenNgay = new kDatePicker();
        dpDenNgay.setBounds(320, 6, 150, 20);
        pnInput.add(dpDenNgay);

        btnXem = new JButton("Xem");
        btnXem.setBounds(482, 3, 92, 26);
        pnInput.add(btnXem);

        JPanel pnTable = new JPanel();
        pnView.add(pnTable, BorderLayout.CENTER);
        pnTable.setLayout(new BorderLayout(0, 0));

        String[] cols = { "Mã phòng", "Sức chứa", "Số Giường", "Vị trí", "Tình Trạng", "Loại phòng", "Đơn giá",
                "Ngày đặt", "Ngày trả", "Mã KH", "Tên KH" };
        modelTable = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };
        table = new JTable(modelTable);
        JScrollPane scpTable = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pnTable.add(scpTable, BorderLayout.CENTER);

        btnXem.addActionListener(this);
        try {
            hdPhong = getListHDPhongDatTruoc();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DocDuLieuVaoTable();
    }

    public static void main(final String[] args) {
        new DialogLichDatPhong().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnXem)) {
            modelTable.getDataVector().removeAllElements();
            try {
                Date tuNgay = dpTuNgay.getFullDate();
                Date denNgay = dpDenNgay.getFullDate();
                Date toDay = dpTuNgay.getValueToDay();
                if (validData()) {
                    try {
                        hdPhong = getListHDPhongDatTruocGioiHan();
                        if (hdPhong.size() <= 0) {
                            JOptionPane.showMessageDialog(this, "Không tìm thấy danh sách đặt phòng trước", "Thông báo",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (Exception e4) {
                        e4.printStackTrace();
                    }
                    DocDuLieuVaoTable();
                } else {
                    if (tuNgay.before(toDay)) {
                        dpTuNgay.setValueToDay();
                        JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải lớn hơn hoặc bằng ngày hiện tại",
                                "Cảnh báo", JOptionPane.ERROR_MESSAGE);
                    } else if (denNgay.before(toDay)) {
                        dpDenNgay.setValueToDay();
                        JOptionPane.showMessageDialog(this, "Ngày kết thúc phải lớn hơn hoặc bằng ngày hiện tại",
                                "Cảnh báo", JOptionPane.ERROR_MESSAGE);
                    } else if (denNgay.before(tuNgay)) {
                        dpDenNgay.setValueToDay();
                        JOptionPane.showMessageDialog(this, "Ngày kết thúc phải lớn hơn hoặc bằng ngày bắt đầu",
                                "Cảnh báo", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (HeadlessException | ParseException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
        setTitle("Lịch Đặt Phòng " + maPhong);
        lbTitle.setText("Lịch Đặt Phòng " + maPhong);
        try {
            hdPhong = getListHDPhongDatTruoc();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DocDuLieuVaoTable();
    }

    private ArrayList<HoaDonPhong> getListHDPhongDatTruocGioiHan() throws ParseException {
        Date tuNgay = dpTuNgay.getFullDate();
        Date denNgay = dpDenNgay.getFullDate();
        ArrayList<HoaDonPhong> dataList = dhPhongDAO.getListHDPhongReservationLimit(maPhong, tuNgay, denNgay);
        return dataList;
    }

    private ArrayList<HoaDonPhong> getListHDPhongDatTruoc() throws ParseException {
        Date tuNgay = dpTuNgay.getFullDate();
        ArrayList<HoaDonPhong> dataList = dhPhongDAO.getListHDPhongReservation(maPhong, tuNgay);
        return dataList;
    }

    private void DocDuLieuVaoTable() {
        for (HoaDonPhong item : hdPhong) {
            String tinhTrangP = "";
            Phong phong = item.getPhong();
            if (phong.getTinhTrang() == 0)
                tinhTrangP = "Trống";
            else if (phong.getTinhTrang() == 1)
                tinhTrangP = "Đã được đặt";
            else if (phong.getTinhTrang() == 2)
                tinhTrangP = "Đã cho thuê";
            LoaiPhong loaiPhong = phong.getLoaiPhong();
            KhachHang kh = item.getKhachHang();
            String ngayGioNhan = formatDate(item.getNgayGioNhan());
            String ngayGioTra = formatDate(item.getNgayGioTra());
            modelTable.addRow(new Object[] { phong.getMaPhong(), phong.getSucChua(), phong.getSoGiuong(),
                    phong.getViTri(), tinhTrangP, loaiPhong.getTenLoaiPhong(), loaiPhong.getDonGia(), ngayGioNhan,
                    ngayGioTra, kh.getMaKH(), kh.getTenKH() });
        }
    }

    private String formatDate(Date date) {
        if (date == null)
            return "Chưa cập nhật";
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return sdf.format(date);
    }

    public boolean validData() throws ParseException {
        Date tuNgay = dpTuNgay.getFullDate();
        Date denNgay = dpDenNgay.getFullDate();
        Date toDay = dpTuNgay.getValueToDay();
        if (tuNgay.before(toDay)) {
            return false;
        }
        if (denNgay.before(toDay) || denNgay.before(tuNgay)) {
            return false;
        }
        return true;
    }
}
