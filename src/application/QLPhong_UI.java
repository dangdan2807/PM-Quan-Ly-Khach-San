package application;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.*;

public class QLPhong_UI extends JFrame implements ActionListener {
    public JPanel pnMain;
    private JTextField txtMaLPhong, txtLPhong, txtDonGia, txtMaPhong, txtViTri, txtTimLP, txtTimPhong;
    private DefaultTableModel modelTableLP;
    private JTable tableLP;
    private JLabel lbShowMessageDSP, lbShowMessageDSLP, lbShowMessageP, lbShowMessagesLP;
    private JButton btnThemLP, btnSuaLP, btnXoaLP, btnLamLaiLP, btnThemP, btnSuaP, btnXoaP, btnLamLaiP, btnTimLP, btnTimPhong;
    ImageIcon blueAddIcon = new ImageIcon("data/images/blueAdd_16.png");
    ImageIcon editIcon = new ImageIcon("data/images/edit2_16.png");
    ImageIcon deleteIcon = new ImageIcon("data/images/trash2_16.png");
    ImageIcon refreshIcon = new ImageIcon("data/images/refresh_16.png");
    ImageIcon searchIcon = new ImageIcon("data/images/search_16.png");
    ImageIcon checkIcon = new ImageIcon("data/images/check2_16.png");
    ImageIcon errorIcon = new ImageIcon("data/images/cancel_16.png");

    public QLPhong_UI() {
        setSize(1000, 670);
        setTitle("Quản Lý Phòng Và Loại Phòng");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        pnMain = new JPanel();
        pnMain.setLayout(null);
        getContentPane().add(pnMain, BorderLayout.CENTER);

        JLabel lbTitle = new JLabel("Quản Lý Khách Hàng và Loại Phòng");
        lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbTitle.setFont(new Font("Dialog", Font.BOLD, 20));
        lbTitle.setBounds(0, 0, 984, 30);
        pnMain.add(lbTitle);

        JPanel pnTL = new JPanel();
        pnTL.setBorder(
                new TitledBorder(null, "Thông tin loại phòng ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pnTL.setBounds(0, 33, 340, 297);
        pnMain.add(pnTL);
        pnTL.setLayout(null);

        JLabel lbMaLPhong = new JLabel("Mã loại phòng: ");
        lbMaLPhong.setBounds(10, 21, 100, 16);
        pnTL.add(lbMaLPhong);

        txtMaLPhong = new JTextField();
        txtMaLPhong.setEditable(false);
        txtMaLPhong.setBounds(115, 19, 210, 20);
        pnTL.add(txtMaLPhong);
        txtMaLPhong.setColumns(10);

        JLabel lbTenLPhong = new JLabel("Tên loại phòng: ");
        lbTenLPhong.setBounds(10, 49, 100, 16);
        pnTL.add(lbTenLPhong);

        txtLPhong = new JTextField();
        txtLPhong.setBounds(115, 47, 210, 20);
        pnTL.add(txtLPhong);
        txtLPhong.setColumns(10);

        JLabel lbDonGia = new JLabel("Đơn giá: ");
        lbDonGia.setBounds(12, 77, 98, 16);
        pnTL.add(lbDonGia);

        txtDonGia = new JTextField();
        txtDonGia.setBounds(115, 75, 210, 20);
        pnTL.add(txtDonGia);
        txtDonGia.setColumns(10);

        lbShowMessagesLP = new JLabel("");
        lbShowMessagesLP.setBounds(10, 105, 315, 16);
        pnTL.add(lbShowMessagesLP);

        btnThemLP = new JButton("Thêm", blueAddIcon);
        btnThemLP.setBounds(10, 133, 98, 26);
        pnTL.add(btnThemLP);

        btnSuaLP = new JButton("Xóa", editIcon);
        btnSuaLP.setBounds(225, 133, 98, 26);
        pnTL.add(btnSuaLP);

        btnXoaLP = new JButton("Sửa", deleteIcon);
        btnXoaLP.setBounds(115, 133, 98, 26);
        pnTL.add(btnXoaLP);

        btnLamLaiLP = new JButton("Sửa", refreshIcon);
        btnLamLaiLP.setBounds(115, 171, 98, 26);
        pnTL.add(btnLamLaiLP);

        JPanel pnBL = new JPanel();
        pnBL.setBorder(new TitledBorder(null, "Thông tin phòng ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pnBL.setBounds(0, 333, 340, 297);
        pnMain.add(pnBL);
        pnBL.setLayout(null);

        JLabel lbMaPhong = new JLabel("Mã phòng: ");
        lbMaPhong.setBounds(10, 21, 79, 16);
        pnBL.add(lbMaPhong);

        txtMaPhong = new JTextField();
        txtMaPhong.setBounds(90, 19, 235, 20);
        pnBL.add(txtMaPhong);
        txtMaPhong.setColumns(10);

        JLabel lbSucChua = new JLabel("Sức chứa");
        lbSucChua.setBounds(10, 49, 79, 16);
        pnBL.add(lbSucChua);

        JSpinner spinSucChua = new JSpinner();
        spinSucChua.setBounds(89, 47, 236, 20);
        pnBL.add(spinSucChua);

        JLabel lbSoGiuong = new JLabel("Số giường: ");
        lbSoGiuong.setBounds(10, 77, 79, 16);
        pnBL.add(lbSoGiuong);

        JSpinner spinSoGiuong = new JSpinner();
        spinSoGiuong.setBounds(90, 75, 235, 20);
        pnBL.add(spinSoGiuong);

        JLabel lbTinhTrang = new JLabel("Tình trạng: ");
        lbTinhTrang.setBounds(10, 133, 79, 16);
        pnBL.add(lbTinhTrang);

        JComboBox<String> cboTinhTrang = new JComboBox<String>();
        cboTinhTrang.setBounds(90, 131, 235, 20);
        pnBL.add(cboTinhTrang);

        JLabel lbViTri = new JLabel("Vị trí: ");
        lbViTri.setBounds(10, 105, 55, 16);
        pnBL.add(lbViTri);

        txtViTri = new JTextField();
        txtViTri.setBounds(90, 103, 235, 20);
        pnBL.add(txtViTri);
        txtViTri.setColumns(10);

        lbShowMessageP = new JLabel("");
        lbShowMessageP.setBounds(10, 160, 315, 16);
        pnBL.add(lbShowMessageP);

        btnThemP = new JButton("Thêm", blueAddIcon);
        btnThemP.setBounds(12, 188, 98, 26);
        pnBL.add(btnThemP);

        btnSuaP = new JButton("Sửa", deleteIcon);
        btnSuaP.setBounds(122, 188, 98, 26);
        pnBL.add(btnSuaP);

        btnXoaP = new JButton("Xóa", editIcon);
        btnXoaP.setBounds(232, 188, 98, 26);
        pnBL.add(btnXoaP);

        btnLamLaiP = new JButton("Sửa", refreshIcon);
        btnLamLaiP.setBounds(122, 226, 98, 26);
        pnBL.add(btnLamLaiP);

        JPanel pnTR = new JPanel();
        pnTR.setBorder(
                new TitledBorder(null, "Danh sách loại phòng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pnTR.setBounds(340, 33, 645, 297);
        pnMain.add(pnTR);
        pnTR.setLayout(null);

        JLabel lbTimLP = new JLabel("Tên loại phòng: ");
        lbTimLP.setBounds(12, 21, 100, 16);
        pnTR.add(lbTimLP);

        txtTimLP = new JTextField();
        txtTimLP.setBounds(110, 19, 150, 20);
        pnTR.add(txtTimLP);
        txtTimLP.setColumns(10);

        btnTimLP = new JButton("Tìm", searchIcon);
        btnTimLP.setBounds(265, 16, 98, 26);
        pnTR.add(btnTimLP);

        JPanel pnTableLP = new JPanel();
        pnTableLP.setBounds(12, 49, 621, 242);
        pnTR.add(pnTableLP);
        pnTableLP.setLayout(new BorderLayout(0, 0));

        String[] colsLP = { "Mã loại phòng", "Tên loại phòng", "Đơn giá" };
        modelTableLP = new DefaultTableModel(colsLP, 0);
        tableLP = new JTable(modelTableLP);
        JScrollPane scpTableLP = new JScrollPane(tableLP, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pnTableLP.add(scpTableLP, BorderLayout.CENTER);
        
        lbShowMessageDSLP = new JLabel("");
        lbShowMessageDSLP.setBounds(370, 21, 263, 16);
        pnTR.add(lbShowMessageDSLP);

        JPanel pnBR = new JPanel();
        pnBR.setBorder(new TitledBorder(null, "Danh sách phòng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pnBR.setBounds(340, 333, 644, 297);
        pnMain.add(pnBR);
        pnBR.setLayout(null);

        JLabel lbTimPhong = new JLabel("Mã phòng: ");
        lbTimPhong.setBounds(12, 21, 75, 16);
        pnBR.add(lbTimPhong);

        txtTimPhong = new JTextField();
        txtTimPhong.setBounds(85, 19, 150, 20);
        pnBR.add(txtTimPhong);
        txtTimPhong.setColumns(10);

        btnTimPhong = new JButton("Tìm", searchIcon);
        btnTimPhong.setBounds(240, 16, 98, 26);
        pnBR.add(btnTimPhong);

        JPanel pnTableP = new JPanel();
        pnTableP.setBounds(12, 49, 620, 236);
        pnBR.add(pnTableP);

        String[] colsP = { "Mã phòng", "Sức chứa", "Số Giường", "Vị trí", "Tình Trạng" };
        modelTableLP = new DefaultTableModel(colsP, 0);
        pnTableP.setLayout(new BorderLayout(0, 0));
        tableLP = new JTable(modelTableLP);
        JScrollPane scpTableP = new JScrollPane(tableLP, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pnTableP.add(scpTableP);
        
        lbShowMessageDSP = new JLabel("");
        lbShowMessageDSP.setBounds(345, 21, 287, 16);
        pnBR.add(lbShowMessageDSP);
    }

    public static void main(String[] args) {
        new QLPhong_UI().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
