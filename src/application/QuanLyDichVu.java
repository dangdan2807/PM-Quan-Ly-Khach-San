package application;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class QuanLyDichVu extends JFrame implements ActionListener {

    private JPanel pnMain;
    private JTextField txtTim;
    private JTable tableDV;
    private DefaultTableModel modelTableDV;
    private JTextField txtMaDV;
    private JTextField txtTenDV;
    private JTextField txtDonGia;

    public QuanLyDichVu() {
        setTitle("Danh Mục Dịch Vụ");
        setSize(630, 420);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        pnMain = new JPanel();
        pnMain.setLayout(null);
        pnMain.setBounds(0, 0, 630, 420);

        getContentPane().add(pnMain);

        JLabel lbTitle = new JLabel("Danh Mục Dịch Vụ");
        lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbTitle.setFont(new Font("Dialog", Font.BOLD, 20));
        lbTitle.setBounds(10, 11, 604, 30);
        pnMain.add(lbTitle);

        JPanel pnInfoDV = new JPanel();
        pnInfoDV.setBounds(10, 52, 273, 328);
        pnMain.add(pnInfoDV);
        pnInfoDV.setLayout(null);

        JLabel lbMaDV = new JLabel("Mã DV: ");
        lbMaDV.setBounds(0, 0, 55, 20);
        pnInfoDV.add(lbMaDV);

        txtMaDV = new JTextField();
        txtMaDV.setBounds(59, 0, 214, 20);
        pnInfoDV.add(txtMaDV);
        txtMaDV.setColumns(10);

        JLabel lbTenDV = new JLabel("Tên DV:");
        lbTenDV.setBounds(0, 32, 55, 20);
        pnInfoDV.add(lbTenDV);

        txtTenDV = new JTextField();
        txtTenDV.setBounds(59, 32, 214, 20);
        pnInfoDV.add(txtTenDV);
        txtTenDV.setColumns(10);

        JLabel lbDonGia = new JLabel("Đơn giá:");
        lbDonGia.setBounds(0, 64, 55, 16);
        pnInfoDV.add(lbDonGia);

        txtDonGia = new JTextField();
        txtDonGia.setBounds(59, 62, 214, 20);
        pnInfoDV.add(txtDonGia);
        txtDonGia.setColumns(10);

        JButton btnThem = new JButton("Thêm");
        btnThem.setBounds(0, 106, 120, 30);
        pnInfoDV.add(btnThem);

        JButton btnSua = new JButton("Sửa");
        btnSua.setBounds(153, 106, 120, 30);
        pnInfoDV.add(btnSua);

        JButton btnHuy = new JButton("Hủy");
        btnHuy.setBounds(0, 148, 120, 30);
        pnInfoDV.add(btnHuy);

        JButton btnLamLai = new JButton("Làm lại");
        btnLamLai.setBounds(153, 148, 120, 30);
        pnInfoDV.add(btnLamLai);

        JLabel lbTimKiem = new JLabel("Tìm kiếm:");
        lbTimKiem.setBounds(293, 52, 69, 20);
        pnMain.add(lbTimKiem);

        txtTim = new JTextField();
        txtTim.setBounds(364, 52, 171, 20);
        pnMain.add(txtTim);
        txtTim.setColumns(10);

        JButton btnTim = new JButton("Tìm");
        btnTim.setBounds(545, 52, 69, 20);
        pnMain.add(btnTim);

        JPanel pnTableDV = new JPanel();
        pnTableDV.setBounds(293, 81, 321, 299);
        pnMain.add(pnTableDV);
        pnTableDV.setLayout(new BorderLayout(0, 0));

        String[] cols = { "Mã DV", "Tên DV", "Đơn Giá" };
        modelTableDV = new DefaultTableModel(cols, 0);
        tableDV = new JTable(modelTableDV);
        JScrollPane scpTableDV = new JScrollPane(tableDV, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        pnTableDV.add(scpTableDV, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        new QuanLyDichVu().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
