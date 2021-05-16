package application;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class BaoCaoKhachHang extends JFrame implements ActionListener {
    private JPanel pnMain;
    private JTextField txtMaKH, txtTenKH;
    private kDatePicker dpTuNgay, dpDenNgay;
    private JLabel lbTenKH;
    private JLabel lbDenNgay;
    private DefaultTableModel modelTableBCKH;

    ImageIcon analyticsIcon = new ImageIcon("data/images/analytics_16.png");
    private JPanel pnThongKe;
    private JLabel lbTongDoanhThu;
    private JTextField txtThanhTien;
    private JLabel lbVND;
    private JButton btnThongKe;
    private JPanel pnTableBCDV;
    private JTable tableBCKH;

    public BaoCaoKhachHang() {
        setTitle("Báo Cáo Tổng Hợp Khách Hàng");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        pnMain = new JPanel();
        pnMain.setLayout(null);
        pnMain.setBounds(0, 0, 630, 420);

        getContentPane().add(pnMain);

        JLabel lbTitle = new JLabel("Báo Cáo Tổng Hợp Khách Hàng");
        lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbTitle.setFont(new Font("Dialog", Font.BOLD, 20));
        lbTitle.setBounds(10, 11, 972, 30);
        pnMain.add(lbTitle);

        JLabel lbMaKH = new JLabel("Mã KH:");
        lbMaKH.setBounds(10, 52, 55, 16);
        pnMain.add(lbMaKH);

        txtMaKH = new JTextField();
        txtMaKH.setBounds(80, 50, 170, 20);
        pnMain.add(txtMaKH);
        txtMaKH.setColumns(10);

        JLabel lbTuNgay = new JLabel("Từ ngày");
        lbTuNgay.setBounds(10, 80, 55, 16);

        dpTuNgay = new kDatePicker(170);
        dpTuNgay.setBounds(80, 80, 170, 20);
        pnMain.add(dpTuNgay);

        pnMain.add(lbTuNgay);

        lbTenKH = new JLabel("Tên KH:");
        lbTenKH.setBounds(348, 52, 55, 16);
        pnMain.add(lbTenKH);

        lbDenNgay = new JLabel("Đến ngày:");
        lbDenNgay.setBounds(348, 80, 70, 16);
        pnMain.add(lbDenNgay);

        dpDenNgay = new kDatePicker(170);
        dpDenNgay.setBounds(421, 80, 170, 20);
        pnMain.add(dpDenNgay);

        txtTenKH = new JTextField();
        txtTenKH.setBounds(421, 50, 170, 20);
        pnMain.add(txtTenKH);
        txtTenKH.setColumns(10);
        
        pnTableBCDV = new JPanel();
        pnTableBCDV.setBounds(10, 108, 972, 509);
        pnMain.add(pnTableBCDV);
        pnTableBCDV.setLayout(new BorderLayout(0, 0));

        // mã hóa đơn phòng
        String[] cols = { "Mã HD", "Phòng", "Giá phòng", "Ngày đến", "Ngày Trả", "Mã DV", "Tên DV", "Đơn giá", "Mã KH",
                "Mã NV" };
        modelTableBCKH = new DefaultTableModel(cols, 0);
        tableBCKH = new JTable(modelTableBCKH);
        JScrollPane scpTableBCDV = new JScrollPane(tableBCKH, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        pnTableBCDV.add(scpTableBCDV, BorderLayout.CENTER);
        
        pnThongKe = new JPanel();
        pnThongKe.setBounds(10, 619, 972, 40);
        pnMain.add(pnThongKe);
        pnThongKe.setLayout(null);
        
        lbTongDoanhThu = new JLabel("Tổng doanh thu:");
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
        lbVND = new JLabel("VND");
        lbVND.setBounds(309, 12, 35, 16);
        pnThongKe.add(lbVND);
        
        btnThongKe = new JButton("Thống kê", analyticsIcon);
        btnThongKe.setBounds(793, 0, 179, 40);
        pnThongKe.add(btnThongKe);
        
    }

    public static void main(String[] args) {
        new BaoCaoKhachHang().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
