package application;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.*;

import DAO.ChiTietDVDAO;
import connectDB.ConnectDB;
import entity.*;

public class ThongKeDichVu_UI extends JFrame implements ActionListener, KeyListener {

    public JPanel pnMain;
    private JTextField txtMaKH, txtTenKH, txtThanhTien;
    private kDatePicker dpTuNgay, dpDenNgay;
    private JTable table;
    private DefaultTableModel modelTable;
    private JButton btnThongKe;
    private JLabel lbShowMessages;
    private final int SUCCESS = 1, ERROR = 0;
    ImageIcon analyticsIcon = new ImageIcon("data/images/analytics_16.png");
    ImageIcon checkIcon = new ImageIcon("data/images/check2_16.png");
    ImageIcon errorIcon = new ImageIcon("data/images/cancel_16.png");
    ChiTietDVDAO chiTietDVDAO = new ChiTietDVDAO();

    public ThongKeDichVu_UI() {
        try {
            ConnectDB.getInstance().connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        setSize(1000, 670);
        setTitle("Báo Cáo Dịch Vụ");
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        pnMain = new JPanel();
        pnMain.setLayout(null);
        pnMain.setBounds(0, 0, 1000, 670);

        getContentPane().add(pnMain);

        JLabel lbTitle = new JLabel("Thống Kê Dịch Vụ");
        lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbTitle.setFont(new Font("Dialog", Font.BOLD, 20));
        lbTitle.setBounds(10, 11, 972, 30);
        pnMain.add(lbTitle);

        JLabel lbMaKH = new JLabel("Mã khách hàng:");
        lbMaKH.setBounds(10, 52, 100, 16);
        pnMain.add(lbMaKH);

        txtMaKH = new JTextField();
        txtMaKH.setBounds(114, 50, 170, 20);
        pnMain.add(txtMaKH);
        txtMaKH.setColumns(10);

        JLabel lbTuNgay = new JLabel("Từ ngày:");
        lbTuNgay.setBounds(10, 80, 100, 16);

        dpTuNgay = new kDatePicker(170);
        dpTuNgay.setBounds(114, 76, 170, 20);
        pnMain.add(dpTuNgay);

        pnMain.add(lbTuNgay);

        JLabel lbTenKH = new JLabel("Tên Khách hàng:");
        lbTenKH.setBounds(348, 52, 100, 16);
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
        // mã HDDV
        String[] cols = { "Mã HD", "Mã DV", "Tên DV", "Số lượng", "Đơn giá", "Thành Tiền", "Ngày Đặt", "Mã KH",
                "Tên KH" };
        modelTable = new DefaultTableModel(cols, 0) {
            // khóa sửa dữ liệu trực tiếp trên table
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };
        table = new JTable(modelTable);
        JScrollPane scpTable = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        pnTable.add(scpTable, BorderLayout.CENTER);

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
        txtThanhTien.setBackground(new Color(127, 255, 212));

        // JLabel lbA = new JLabel("345678", blueAddIcon, JLabel.LEFT);
        JLabel lbVND = new JLabel("VND");
        lbVND.setBounds(309, 12, 35, 16);
        pnThongKe.add(lbVND);

        btnThongKe = new JButton("Thống kê");
        btnThongKe.setIcon(analyticsIcon);
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
        new ThongKeDichVu_UI().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnThongKe)) {
            String maKH = txtMaKH.getText().trim();
            String tenKH = txtTenKH.getText().trim();
            ArrayList<ChiTietDV> dataList = null;
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

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        Object o = e.getSource();
        Object key = e.getKeyCode();
        // bắt sự kiện nhấn phím enter tự nhấn btnLogin
        if (o.equals(txtMaKH) | o.equals(txtTenKH)) {
            if (key.equals(KeyEvent.VK_ENTER)) {
                btnThongKe.doClick();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private ArrayList<ChiTietDV> getListSearchByDate() throws ParseException {
        Date tuNgay = dpTuNgay.getFullDate();
        Date denNgay = dpDenNgay.getFullDate();
        ArrayList<ChiTietDV> dataList = chiTietDVDAO.getListChiTietDVDate(tuNgay, denNgay);
        return dataList;
    }

    private ArrayList<ChiTietDV> getListSearchByMaKH() throws ParseException {
        Date tuNgay = dpTuNgay.getFullDate();
        Date denNgay = dpDenNgay.getFullDate();
        int maKH = Integer.parseInt(txtMaKH.getText().trim());
        ArrayList<ChiTietDV> dataList = chiTietDVDAO.getListChiTietDVByMaKH(maKH, tuNgay, denNgay);
        return dataList;
    }

    private ArrayList<ChiTietDV> getListSearchByTenKH() throws ParseException {
        Date tuNgay = dpTuNgay.getFullDate();
        Date denNgay = dpDenNgay.getFullDate();
        String tenKH = txtTenKH.getText().trim();
        ArrayList<ChiTietDV> dataList = chiTietDVDAO.getListChiTietDVByTenKH(tenKH, tuNgay, denNgay);
        return dataList;
    }

    private ArrayList<ChiTietDV> getListSearchByMaKHAndTenKH() throws ParseException {
        Date tuNgay = dpTuNgay.getFullDate();
        Date denNgay = dpDenNgay.getFullDate();
        int maKH = Integer.parseInt(txtMaKH.getText().trim());
        String tenKH = txtTenKH.getText().trim();
        ArrayList<ChiTietDV> dataList = chiTietDVDAO.getListChiTietDVByMaKHAndTenKH(maKH, tenKH, tuNgay, denNgay);
        return dataList;
    }

    private void DocDuLieuVaoTable(ArrayList<ChiTietDV> dataList) {
        Double sum = 0.0;
        for (ChiTietDV item : dataList) {
            HoaDonDV hoaDonDv = item.getHoaDonDV();
            DichVu dv = item.getDichVu();
            KhachHang kh = item.getHoaDonDV().getKhachHang();
            String date = formatDate(hoaDonDv.getNgayGioDat());
            Double thanhTien = item.getSoLuong() * item.getDichVu().getDonGia();
            sum += thanhTien;
            modelTable.addRow(new Object[] { hoaDonDv.getMaHDDV(), dv.getMaDV(), dv.getTenDV(), item.getSoLuong(),
                    dv.getDonGia(), thanhTien, date, kh.getMaKH(), kh.getTenKH() });
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
}
