package application;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

public class QuanLyKhachHang extends JFrame implements ActionListener {

    private JPanel pnMain;
    private JTextField txtMaKH;
    private JTextField txtTenKH;
    private JTextField txtCMND;
    private JTextField txtSoLanDat;
    private kDatePicker dpNgayHetHan;
    private JTextField textField;
    private JButton btnTim;
    private JTable tableShowKH;
    private DefaultTableModel modelTable;
    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    private JButton btnLamLai;

    public QuanLyKhachHang() {
        setTitle("Quản Lý Khách Hàng");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        pnMain = new JPanel();
        pnMain.setLayout(null);
        pnMain.setBounds(0, 0, 630, 420);

        JLabel lbTitle = new JLabel("Quản Lý Khách Hàng");
        lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbTitle.setFont(new Font("Dialog", Font.BOLD, 20));
        lbTitle.setBounds(0, 0, 624, 30);
        pnMain.add(lbTitle);

        getContentPane().add(pnMain);

        JPanel pnThongTinKH = new JPanel();
        pnThongTinKH.setBorder(
                new TitledBorder(null, "Thông tin khách hàng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pnThongTinKH.setBounds(0, 41, 359, 618);
        pnMain.add(pnThongTinKH);
        pnThongTinKH.setLayout(null);

        JLabel lbMaKH = new JLabel("Mã khách hàng:");
        lbMaKH.setBounds(10, 21, 100, 14);
        pnThongTinKH.add(lbMaKH);

        txtMaKH = new JTextField();
        txtMaKH.setEditable(false);
        txtMaKH.setBounds(125, 18, 225, 20);
        pnThongTinKH.add(txtMaKH);
        txtMaKH.setColumns(10);

        JLabel lbTenKH = new JLabel("Tên khách hàng:");
        lbTenKH.setBounds(10, 46, 100, 14);
        pnThongTinKH.add(lbTenKH);

        txtTenKH = new JTextField();
        txtTenKH.setBounds(125, 43, 225, 20);
        pnThongTinKH.add(txtTenKH);
        txtTenKH.setColumns(10);

        JLabel lbLoaiKhach = new JLabel("Loại khách hàng:");
        lbLoaiKhach.setBounds(10, 124, 105, 14);
        pnThongTinKH.add(lbLoaiKhach);

        JComboBox<String> cboLoaiKhach = new JComboBox<String>();
        cboLoaiKhach.setBounds(125, 121, 225, 20);
        cboLoaiKhach.addItem("Việt Nam");
        cboLoaiKhach.addItem("Nước ngoài");
        pnThongTinKH.add(cboLoaiKhach);

        JLabel lbCMND = new JLabel("CMND:");
        lbCMND.setBounds(10, 71, 80, 14);
        pnThongTinKH.add(lbCMND);

        JLabel lbNgayHetHan = new JLabel("Ngày hết hạn CMND:");
        lbNgayHetHan.setBounds(10, 95, 123, 16);
        pnThongTinKH.add(lbNgayHetHan);

        txtCMND = new JTextField();
        txtCMND.setBounds(125, 68, 225, 20);
        pnThongTinKH.add(txtCMND);
        txtCMND.setColumns(10);

        dpNgayHetHan = new kDatePicker(225);
        dpNgayHetHan.setBounds(125, 93, 225, 20);
        pnThongTinKH.add(dpNgayHetHan);

        JLabel lbSoLanDat = new JLabel("Số lần đặt phòng:");
        lbSoLanDat.setBounds(10, 150, 112, 16);
        pnThongTinKH.add(lbSoLanDat);

        txtSoLanDat = new JTextField();
        txtSoLanDat.setText("0");
        txtSoLanDat.setBounds(125, 148, 225, 20);
        pnThongTinKH.add(txtSoLanDat);
        txtSoLanDat.setColumns(10);

        btnThem = new JButton("Thêm");
        btnThem.setBounds(10, 178, 98, 26);
        pnThongTinKH.add(btnThem);

        btnSua = new JButton("Sửa");
        btnSua.setBounds(125, 178, 98, 26);
        pnThongTinKH.add(btnSua);

        btnXoa = new JButton("Xóa");
        btnXoa.setBounds(235, 178, 98, 26);
        pnThongTinKH.add(btnXoa);

        btnLamLai = new JButton("Làm lại");
        btnLamLai.setBounds(12, 216, 98, 26);
        pnThongTinKH.add(btnLamLai);

        JPanel pbTableKH = new JPanel();
        pbTableKH.setBorder(
                new TitledBorder(null, "Danh sách khách hàng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pbTableKH.setBounds(360, 41, 622, 618);
        pnMain.add(pbTableKH);
        pbTableKH.setLayout(null);

        JLabel lbTimTenKH = new JLabel("Tên kh:");
        lbTimTenKH.setBounds(12, 23, 55, 16);
        pbTableKH.add(lbTimTenKH);

        textField = new JTextField();
        textField.setBounds(75, 21, 200, 20);
        pbTableKH.add(textField);
        textField.setColumns(10);

        btnTim = new JButton("Tìm");
        btnTim.setBounds(287, 21, 98, 20);
        pbTableKH.add(btnTim);

        JPanel pnShowTableKH = new JPanel();
        pnShowTableKH.setBounds(12, 49, 598, 569);
        pbTableKH.add(pnShowTableKH);
        pnShowTableKH.setLayout(new BorderLayout(0, 0));

        String[] cols = { "Mã KH", "Tên KH", "CMND", "Ngày hết hạn", "Loại KH", "Số lần đặt phòng" };
        modelTable = new DefaultTableModel(cols, 0) {
            // khóa sửa dữ liệu trực tiếp trên table
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };
        tableShowKH = new JTable(modelTable);
        JScrollPane scpShowTableKH = new JScrollPane(tableShowKH, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pnShowTableKH.add(scpShowTableKH, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        new QuanLyKhachHang().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private boolean validData() {
        return true;
    }
}
