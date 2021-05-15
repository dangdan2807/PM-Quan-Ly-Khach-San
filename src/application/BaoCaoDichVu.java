package application;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class BaoCaoDichVu extends JFrame implements ActionListener {

    private JPanel pnMain;
    private JTextField txtMaKH, txtTenKH;
    private kDatePicker dpTuNgay, dpDenNgay;
    private JLabel lbTenKH;
    private JLabel lbDenNgay;
    private JTable tableBCDV;
    private DefaultTableModel modelTableBCDV;
    private JLabel lbTongDoanhThu;
    private JTextField txtThanhTien;
    private JLabel lbVND;

    public BaoCaoDichVu() {
        setTitle("Báo Cáo Dịch Vụ");
        setSize(630, 420);
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
        lbTitle.setBounds(10, 11, 604, 30);
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

        JPanel pnTableBCDV = new JPanel();
        pnTableBCDV.setBounds(10, 108, 604, 245);
        pnMain.add(pnTableBCDV);
        pnTableBCDV.setLayout(new BorderLayout(0, 0));

        String[] cols = { "Mã HD", "Mã DV", "Tên DV", "Số lượng", "Đơn giá", "Ngày Đặt", "Mã KH", "Mã NV" };
        modelTableBCDV = new DefaultTableModel(cols, 0);
        tableBCDV = new JTable(modelTableBCDV);
        JScrollPane scpTableBCDV = new JScrollPane(tableBCDV, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        pnTableBCDV.add(scpTableBCDV, BorderLayout.CENTER);
        
        lbTongDoanhThu = new JLabel("Tổng doanh thu:");
        lbTongDoanhThu.setBounds(10, 361, 105, 16);
        pnMain.add(lbTongDoanhThu);
        
        txtThanhTien = new JTextField();
        txtThanhTien.setHorizontalAlignment(SwingConstants.RIGHT);
        txtThanhTien.setText("0.0");
        txtThanhTien.setEditable(false);
        txtThanhTien.setBounds(114, 359, 205, 20);
        pnMain.add(txtThanhTien);
        txtThanhTien.setColumns(10);
        
        lbVND = new JLabel("VND");
        lbVND.setBounds(322, 361, 43, 16);
        pnMain.add(lbVND);
        
        JButton btnThongKe = new JButton("Thống kê");
        btnThongKe.setBounds(484, 356, 128, 26);
        pnMain.add(btnThongKe);
    }

    public static void main(String[] args) {
        new BaoCaoKhachHang().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
