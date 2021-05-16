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
    ImageIcon blueAddIcon = new ImageIcon("data/images/blueAdd_16.png");

    public QuanLyDichVu() {
        setTitle("Danh Mục Dịch Vụ");
        setSize(1000, 700);
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
        lbTitle.setBounds(10, 11, 972, 30);
        pnMain.add(lbTitle);

        JPanel pnInfoDV = new JPanel();
        pnInfoDV.setBounds(10, 46, 391, 613);
        pnMain.add(pnInfoDV);
        pnInfoDV.setLayout(null);

        JLabel lbMaDV = new JLabel("Mã dịch vụ: ");
        lbMaDV.setBounds(0, 12, 80, 20);
        pnInfoDV.add(lbMaDV);

        txtMaDV = new JTextField();
        txtMaDV.setBounds(81, 12, 310, 20);
        pnInfoDV.add(txtMaDV);
        txtMaDV.setColumns(10);

        JLabel lbTenDV = new JLabel("Tên dịch vụ:");
        lbTenDV.setBounds(0, 44, 80, 20);
        pnInfoDV.add(lbTenDV);

        txtTenDV = new JTextField();
        txtTenDV.setBounds(81, 44, 310, 20);
        pnInfoDV.add(txtTenDV);
        txtTenDV.setColumns(10);

        JLabel lbDonGia = new JLabel("Đơn giá:");
        lbDonGia.setBounds(0, 76, 80, 16);
        pnInfoDV.add(lbDonGia);

        txtDonGia = new JTextField();
        txtDonGia.setBounds(81, 74, 310, 20);
        pnInfoDV.add(txtDonGia);
        txtDonGia.setColumns(10);

        JButton btnThem = new JButton("Thêm");
        btnThem.setIcon(blueAddIcon);
        btnThem.setBounds(0, 124, 120, 30);
        pnInfoDV.add(btnThem);

        JButton btnSua = new JButton("Sửa");
        btnSua.setBounds(153, 124, 120, 30);
        pnInfoDV.add(btnSua);

        JButton btnHuy = new JButton("Xóa");
        btnHuy.setBounds(0, 166, 120, 30);
        pnInfoDV.add(btnHuy);

        JButton btnLamLai = new JButton("Làm lại");
        btnLamLai.setBounds(153, 166, 120, 30);
        pnInfoDV.add(btnLamLai);

        String[] cols = { "Mã DV", "Tên DV", "Đơn Giá" };
        modelTableDV = new DefaultTableModel(cols, 0);

        JPanel pnShowDV = new JPanel();
        pnShowDV.setBounds(413, 46, 569, 613);
        pnMain.add(pnShowDV);
        pnShowDV.setLayout(null);

        JLabel lbTimKiem = new JLabel("Tên dịch vụ:");
        lbTimKiem.setBounds(0, 12, 75, 16);
        pnShowDV.add(lbTimKiem);

        txtTim = new JTextField();
        txtTim.setBounds(74, 10, 225, 20);
        pnShowDV.add(txtTim);
        txtTim.setColumns(10);

        JButton btnTim = new JButton("Tìm");
        btnTim.setBounds(311, 10, 69, 20);
        pnShowDV.add(btnTim);

        JPanel pnTableDV = new JPanel();
        pnTableDV.setBounds(0, 35, 569, 578);
        pnShowDV.add(pnTableDV);
        pnTableDV.setLayout(new BorderLayout(0, 0));
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
