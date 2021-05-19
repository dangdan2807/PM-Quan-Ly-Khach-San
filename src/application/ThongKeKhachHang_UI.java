package application;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.*;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.table.*;

import DAO.HoaDonPhongDAO;
import connectDB.ConnectDB;
import entity.*;
import entity.HoaDonPhong;

public class ThongKeKhachHang_UI extends JFrame implements ActionListener {
    JPanel pnMain;
    private JTextField txtMaKH, txtTenKH, txtThanhTien;
    private kDatePicker dpTuNgay, dpDenNgay;
    private DefaultTableModel modelTable;
    private JButton btnThongKe;
    private JTable table;
    private JLabel lbShowMessages;
    private final int SUCCESS = 1, ERROR = 0;
    ImageIcon analyticsIcon = new ImageIcon("data/images/analytics_16.png");
    ImageIcon checkIcon = new ImageIcon("data/images/check2_16.png");
    ImageIcon errorIcon = new ImageIcon("data/images/cancel_16.png");
    private HoaDonPhongDAO hdPhongDAO = new HoaDonPhongDAO();

    public ThongKeKhachHang_UI() {
        try {
            ConnectDB.getInstance().connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        setTitle("Thống Kê Tổng Hợp Khách Hàng");
        setSize(1000, 670);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        pnMain = new JPanel();
        pnMain.setLayout(null);
        pnMain.setBounds(0, 0, 1000, 670);

        getContentPane().add(pnMain);

        JLabel lbTitle = new JLabel("Báo Cáo Tổng Hợp Khách Hàng");
        lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbTitle.setFont(new Font("Dialog", Font.BOLD, 20));
        lbTitle.setBounds(10, 11, 972, 30);
        pnMain.add(lbTitle);

        JLabel lbMaKH = new JLabel("Mã khách hàng: ");
        lbMaKH.setBounds(10, 52, 110, 16);
        pnMain.add(lbMaKH);

        txtMaKH = new JTextField();
        txtMaKH.setBounds(120, 50, 170, 20);
        pnMain.add(txtMaKH);
        txtMaKH.setColumns(10);

        JLabel lbTuNgay = new JLabel("Từ ngày");
        lbTuNgay.setBounds(10, 80, 55, 16);

        dpTuNgay = new kDatePicker(170);
        dpTuNgay.setBounds(120, 76, 170, 20);
        pnMain.add(dpTuNgay);

        pnMain.add(lbTuNgay);

        JLabel lbTenKH = new JLabel("Tên Khách Hàng:");
        lbTenKH.setBounds(348, 52, 104, 16);
        pnMain.add(lbTenKH);

        JLabel lbDenNgay = new JLabel("Đến ngày:");
        lbDenNgay.setBounds(348, 80, 70, 16);
        pnMain.add(lbDenNgay);

        dpDenNgay = new kDatePicker(170);
        dpDenNgay.setBounds(466, 76, 170, 20);
        pnMain.add(dpDenNgay);

        txtTenKH = new JTextField();
        txtTenKH.setBounds(466, 50, 170, 20);
        pnMain.add(txtTenKH);
        txtTenKH.setColumns(10);

        JPanel pnTable = new JPanel();
        pnTable.setBounds(10, 128, 972, 458);
        pnMain.add(pnTable);
        pnTable.setLayout(new BorderLayout(0, 0));

        // mã hóa đơn phòng
        String[] cols = { "Mã HD", "Mã phòng", "Loại phòng", "Giá phòng", "Ngày đến", "Ngày Trả", "Số Ngày",
                "Thành tiền", "Mã KH", "Tên KH" };
        modelTable = new DefaultTableModel(cols, 0) {
            // khóa sửa dữ liệu trực tiếp trên table
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };

        table = new JTable(modelTable);
        JScrollPane scpTableBCDV = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        pnTable.add(scpTableBCDV, BorderLayout.CENTER);

        JPanel pnThongKe = new JPanel();
        pnThongKe.setBounds(10, 589, 972, 40);
        pnMain.add(pnThongKe);
        pnThongKe.setLayout(null);

        JLabel lbTongDoanhThu = new JLabel("Tổng doanh thu:");
        lbTongDoanhThu.setBounds(0, 12, 105, 16);
        pnThongKe.add(lbTongDoanhThu);

        txtThanhTien = new JTextField();
        txtThanhTien.setBounds(100, 10, 205, 20);
        pnThongKe.add(txtThanhTien);
        txtThanhTien.setHorizontalAlignment(SwingConstants.RIGHT);
        txtThanhTien.setText("0.0");
        txtThanhTien.setEditable(false);
        txtThanhTien.setColumns(10);

        // JLabel lbA = new JLabel("345678", blueAddIcon, JLabel.LEFT);
        JLabel lbVND = new JLabel("VND");
        lbVND.setBounds(309, 12, 35, 16);
        pnThongKe.add(lbVND);

        btnThongKe = new JButton("Thống kê", analyticsIcon);
        btnThongKe.setBounds(793, 0, 179, 40);
        pnThongKe.add(btnThongKe);

        lbShowMessages = new JLabel("");
        lbShowMessages.setBounds(10, 107, 626, 14);
        pnMain.add(lbShowMessages);

        btnThongKe.addActionListener(this);
        try {
            getListSearchByDate();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ThongKeKhachHang_UI().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnThongKe)) {
            String maKH = txtMaKH.getText().trim();
            String tenKH = txtTenKH.getText().trim();
            ArrayList<HoaDonPhong> dataList = null;
            try {
                if (!maKH.isEmpty() && tenKH.isEmpty()) {
                    dataList = getListSearchByMaKH();
                } else if (!tenKH.isEmpty() && maKH.isEmpty()) {
                    dataList = getListSearchByTenKH();
                } else if (maKH.isEmpty() && tenKH.isEmpty()) {
                    dataList = getListSearchByDate();
                } else if (!(maKH.isEmpty() && tenKH.isEmpty())) {
                    dataList = getListSearchByMaKHAndTenKH();
                }
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
            if (dataList.size() > 0) {
                modelTable.getDataVector().removeAllElements();
                DocDuLieuVaoTable(dataList);
            } else {
                showMessage("Không tìm thấy danh sách thống kê theo yêu cầu", ERROR);
            }
        }
    }

    private ArrayList<HoaDonPhong> getListSearchByDate() throws ParseException {
        Date tuNgay = dpTuNgay.getFullDate();
        Date denNgay = dpDenNgay.getFullDate();
        ArrayList<HoaDonPhong> dataList = hdPhongDAO.getListHDPhongByDate(tuNgay, denNgay);
        return dataList;
    }

    private ArrayList<HoaDonPhong> getListSearchByMaKH() throws ParseException {
        Date tuNgay = dpTuNgay.getFullDate();
        Date denNgay = dpDenNgay.getFullDate();
        int maKH = Integer.parseInt(txtMaKH.getText().trim());
        ArrayList<HoaDonPhong> dataList = hdPhongDAO.getListHDPhongByMaKH(maKH, tuNgay, denNgay);
        return dataList;
    }

    private ArrayList<HoaDonPhong> getListSearchByTenKH() throws ParseException {
        Date tuNgay = dpTuNgay.getFullDate();
        Date denNgay = dpDenNgay.getFullDate();
        String tenKH = txtTenKH.getText().trim();
        ArrayList<HoaDonPhong> dataList = hdPhongDAO.getListHDPhongByTenKH(tenKH, tuNgay, denNgay);
        return dataList;
    }

    private ArrayList<HoaDonPhong> getListSearchByMaKHAndTenKH() throws ParseException {
        Date tuNgay = dpTuNgay.getFullDate();
        Date denNgay = dpDenNgay.getFullDate();
        int maKH = Integer.parseInt(txtMaKH.getText().trim());
        String tenKH = txtTenKH.getText().trim();
        ArrayList<HoaDonPhong> dataList = hdPhongDAO.getListHDPhongByMaKHAndTenKH(maKH, tenKH, tuNgay, denNgay);
        return dataList;
    }

    private void DocDuLieuVaoTable(ArrayList<HoaDonPhong> dataList) {
        Double sum = 0.0;
        for (HoaDonPhong item : dataList) {
            Phong phong = item.getPhong();
            LoaiPhong lPhong = item.getPhong().getLoaiPhong();
            KhachHang kh = item.getKhachHang();
            String ngayGioNhan = formatDate(item.getNgayGioNhan());
            String ngayGioTra = formatDate(item.getNgayGioTra());
            int soNgay = (int) tinhSoNgay(item.getNgayGioNhan(), item.getNgayGioTra());
            Double thanhTien = lPhong.getDonGia() * soNgay;
            sum += thanhTien;
            modelTable.addRow(new Object[] { item.getMaHD(), phong.getMaPhong(), lPhong.getTenLoaiPhong(),
                    lPhong.getDonGia(), ngayGioNhan, ngayGioTra, soNgay, thanhTien, kh.getMaKH(), kh.getTenKH() });
        }
        txtThanhTien.setText(sum.toString());
    }

    private String formatDate(Date date) {
        if (date == null)
            return "Chưa cập nhật";
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return sdf.format(date);
    }

    private void showMessage(String message, int type) {
        if (type == SUCCESS) {
            lbShowMessages.setForeground(Color.GREEN);
            lbShowMessages.setIcon(checkIcon);
        } else {
            lbShowMessages.setForeground(Color.RED);
            lbShowMessages.setIcon(errorIcon);
        }
        lbShowMessages.setText(message);
    }

    private long tinhSoNgay(Date tuNgay, Date denNgay) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        long millis = System.currentTimeMillis();
        if (tuNgay == null) {
            tuNgay = new Date(millis);
        }
        if (denNgay == null) {
            denNgay = new Date(millis);
        }
        cal1.setTime(tuNgay);
        cal2.setTime(denNgay);
        long result = (cal2.getTime().getTime() - cal1.getTime().getTime()) / (24 * 3600 * 1000);
        return result <= 0 ? 1 : result;
    }
}
