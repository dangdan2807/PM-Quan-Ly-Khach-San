package application;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class BaoCaoDichVu extends JFrame implements ActionListener {

    private JPanel pnMain;
    private JTextField txtMaKH, txtTenKH;
    private kDatePicker dpTuNgay, dpDenNgay;
    private JLabel lbTenKH, lbDenNgay, lbTongDoanhThu, lbVND;
    private JTable tableBCDV;
    private DefaultTableModel modelTableBCDV;
    private JTextField txtThanhTien;
    ImageIcon analyticsIcon = new ImageIcon("data/images/analytics_16.png");
    private JPanel pnThongKe;

    public BaoCaoDichVu() {
        setTitle("Báo Cáo Dịch Vụ");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        pnMain = new JPanel();
        pnMain.setLayout(null);
        pnMain.setBounds(0, 0, 630, 420);

        getContentPane().add(pnMain);

        JLabel lbTitle = new JLabel("Báo Cáo Dịch Vụ");
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

        lbTenKH = new JLabel("Tên Khách hàng:");
        lbTenKH.setBounds(348, 52, 100, 16);
        pnMain.add(lbTenKH);

        lbDenNgay = new JLabel("Đến ngày:");
        lbDenNgay.setBounds(348, 80, 70, 16);
        pnMain.add(lbDenNgay);

        dpDenNgay = new kDatePicker(170);
        dpDenNgay.setBounds(466, 76, 170, 20);
        pnMain.add(dpDenNgay);

        txtTenKH = new JTextField();
        txtTenKH.setBounds(466, 50, 170, 20);
        pnMain.add(txtTenKH);
        txtTenKH.setColumns(10);

        JPanel pnTableBCDV = new JPanel();
        pnTableBCDV.setBounds(10, 108, 972, 509);
        pnMain.add(pnTableBCDV);
        pnTableBCDV.setLayout(new BorderLayout(0, 0));
        // mã HDDV
        String[] cols = { "Mã HD", "Mã DV", "Tên DV", "Số lượng", "Đơn giá", "Ngày Đặt", "Mã KH", "Mã NV" };
        modelTableBCDV = new DefaultTableModel(cols, 0);
        tableBCDV = new JTable(modelTableBCDV);
        JScrollPane scpTableBCDV = new JScrollPane(tableBCDV, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
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
        
        JButton btnThongKe = new JButton("Thống kê", analyticsIcon);
        btnThongKe.setBounds(793, 0, 179, 40);
        pnThongKe.add(btnThongKe);
    }

    public static void main(String[] args) {
        new BaoCaoDichVu().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
