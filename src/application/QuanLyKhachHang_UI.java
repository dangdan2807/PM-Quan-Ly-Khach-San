package application;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.sql.*;
import java.text.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import DAO.KhachHangDAO;
import connectDB.ConnectDB;
import entity.KhachHang;

public class QuanLyKhachHang_UI extends JFrame implements ActionListener, MouseListener, KeyListener {

    public JPanel pnMain;
    private DefaultTableModel modelTable;
    private JTable tableShowInfo;
    private kDatePicker dpNgayHetHan;
    private JTextField txtMaKH, txtTenKH, txtCMND, txtSoLanDat, txtTim;
    private JButton btnTim, btnThem, btnSua, btnXoa, btnLamLai;
    private JComboBox<String> cboLoaiKhach;
    private JLabel lbShowMessages;
    private final int SUCCESS = 1, ERROR = 0;
    ImageIcon blueAddIcon = new ImageIcon("data/images/blueAdd_16.png");
    ImageIcon editIcon = new ImageIcon("data/images/edit2_16.png");
    ImageIcon deleteIcon = new ImageIcon("data/images/trash2_16.png");
    ImageIcon refreshIcon = new ImageIcon("data/images/refresh_16.png");
    ImageIcon searchIcon = new ImageIcon("data/images/search_16.png");
    ImageIcon checkIcon = new ImageIcon("data/images/check2_16.png");
    ImageIcon errorIcon = new ImageIcon("data/images/cancel_16.png");
    KhachHangDAO khDAO = new KhachHangDAO();

    public QuanLyKhachHang_UI() {
        try {
            ConnectDB.getInstance().connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        setSize(1000, 670);
        setTitle("Quản Lý Khách Hàng");
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        pnMain = new JPanel();
        pnMain.setLayout(null);
        pnMain.setBounds(0, 0, 1000, 670);

        JLabel lbTitle = new JLabel("Quản Lý Khách Hàng");
        lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbTitle.setFont(new Font("Dialog", Font.BOLD, 20));
        lbTitle.setBounds(0, 0, 984, 30);
        pnMain.add(lbTitle);

        getContentPane().add(pnMain);

        JPanel pnThongTinKH = new JPanel();
        pnThongTinKH.setBorder(
                new TitledBorder(null, "Thông tin khách hàng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pnThongTinKH.setBounds(0, 41, 359, 588);
        pnMain.add(pnThongTinKH);
        pnThongTinKH.setLayout(null);

        JLabel lbMaKH = new JLabel("Mã khách hàng:");
        lbMaKH.setBounds(10, 21, 100, 14);
        pnThongTinKH.add(lbMaKH);

        txtMaKH = new JTextField();
        txtMaKH.setEditable(false);
        txtMaKH.setBounds(145, 18, 205, 20);
        pnThongTinKH.add(txtMaKH);
        txtMaKH.setColumns(10);

        JLabel lbTenKH = new JLabel("Tên khách hàng:");
        lbTenKH.setBounds(10, 46, 100, 14);
        pnThongTinKH.add(lbTenKH);

        txtTenKH = new JTextField();
        txtTenKH.setBounds(145, 43, 205, 20);
        pnThongTinKH.add(txtTenKH);
        txtTenKH.setColumns(10);

        JLabel lbLoaiKhach = new JLabel("Loại khách hàng:");
        lbLoaiKhach.setBounds(10, 124, 105, 14);
        pnThongTinKH.add(lbLoaiKhach);

        cboLoaiKhach = new JComboBox<String>();
        cboLoaiKhach.setBounds(145, 121, 205, 20);
        cboLoaiKhach.addItem("Việt Nam");
        cboLoaiKhach.addItem("Nước ngoài");
        pnThongTinKH.add(cboLoaiKhach);

        JLabel lbCMND = new JLabel("CMND/CCCD/Hộ chiếu:");
        lbCMND.setBounds(10, 71, 136, 14);
        pnThongTinKH.add(lbCMND);

        JLabel lbNgayHetHan = new JLabel("Ngày hết hạn hộ chiếu:");
        lbNgayHetHan.setBounds(10, 95, 136, 16);
        pnThongTinKH.add(lbNgayHetHan);

        txtCMND = new JTextField();
        txtCMND.setBounds(145, 68, 205, 20);
        pnThongTinKH.add(txtCMND);
        txtCMND.setColumns(10);

        dpNgayHetHan = new kDatePicker(205);
        dpNgayHetHan.setBounds(145, 93, 205, 20);
        pnThongTinKH.add(dpNgayHetHan);

        JLabel lbSoLanDat = new JLabel("Số lần đặt phòng:");
        lbSoLanDat.setBounds(10, 150, 112, 16);
        pnThongTinKH.add(lbSoLanDat);

        txtSoLanDat = new JTextField();
        txtSoLanDat.setText("0");
        txtSoLanDat.setBounds(145, 148, 205, 20);
        pnThongTinKH.add(txtSoLanDat);
        txtSoLanDat.setColumns(10);

        btnThem = new JButton("Thêm", blueAddIcon);
        btnThem.setBounds(10, 207, 98, 26);
        pnThongTinKH.add(btnThem);

        btnSua = new JButton("Sửa", editIcon);
        btnSua.setBounds(125, 207, 98, 26);
        pnThongTinKH.add(btnSua);

        btnXoa = new JButton("Xóa", deleteIcon);
        btnXoa.setBounds(235, 207, 98, 26);
        pnThongTinKH.add(btnXoa);

        btnLamLai = new JButton("Làm lại", refreshIcon);
        btnLamLai.setBounds(125, 245, 98, 26);
        pnThongTinKH.add(btnLamLai);

        lbShowMessages = new JLabel("");
        lbShowMessages.setBounds(10, 179, 340, 16);
        pnThongTinKH.add(lbShowMessages);

        JPanel pbTableKH = new JPanel();
        pbTableKH.setBorder(
                new TitledBorder(null, "Danh sách khách hàng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pbTableKH.setBounds(360, 41, 624, 588);
        pnMain.add(pbTableKH);
        pbTableKH.setLayout(null);

        JLabel lbTimTenKH = new JLabel("Tên khách hàng:");
        lbTimTenKH.setBounds(12, 23, 106, 16);
        pbTableKH.add(lbTimTenKH);

        txtTim = new JTextField();
        txtTim.setBounds(118, 21, 200, 20);
        pbTableKH.add(txtTim);
        txtTim.setColumns(10);

        btnTim = new JButton("Tìm", searchIcon);
        btnTim.setBounds(330, 21, 80, 20);
        pbTableKH.add(btnTim);

        JPanel pnShowTableKH = new JPanel();
        pnShowTableKH.setBounds(12, 49, 600, 527);
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
        tableShowInfo = new JTable(modelTable);
        JScrollPane scpShowTableKH = new JScrollPane(tableShowInfo, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pnShowTableKH.add(scpShowTableKH, BorderLayout.CENTER);

        // Sự kiện Action
        btnLamLai.addActionListener(this);
        btnThem.addActionListener(this);
        btnSua.addActionListener(this);
        btnXoa.addActionListener(this);
        btnTim.addActionListener(this);
        // sự kiện chuột
        tableShowInfo.addMouseListener(this);
        // sự kiện phím enter
        txtTim.addKeyListener(this);

        DocDuLieuVaoTable(khDAO.getListKhachHang());
    }

    public static void main(String[] args) {
        new QuanLyKhachHang_UI().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnLamLai)) {
            txtMaKH.setText("");
            txtTenKH.setText("");
            txtCMND.setText("");
            cboLoaiKhach.setSelectedIndex(0);
            dpNgayHetHan.setValueToDay();
            cboLoaiKhach.setSelectedIndex(0);
            txtSoLanDat.setText("0");
            lbShowMessages.setText("");
        } else if (o.equals(btnThem)) {
            if (validData()) {
                KhachHang kh = null;
                try {
                    kh = getSelectedDataTable();
                } catch (ParseException e2) {
                    e2.printStackTrace();
                }
                try {
                    boolean result = khDAO.create(kh);
                    int maKH = khDAO.getLatestID();
                    if (result == true) {
                        txtMaKH.setText(String.valueOf(maKH));
                        String date = formatDate(kh.getNgayHetHan());
                        modelTable.addRow(new Object[] { maKH, kh.getTenKH(), kh.getCmnd(), date, kh.getLoaiKH(),
                                kh.getSoLanDatPhong() });
                        showMessage("Thêm khách hàng thành công", SUCCESS);
                    } else {
                        showMessage("Lỗi: Thêm khách hàng thất bại", ERROR);
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        } else if (o.equals(btnSua)) {
            if (validData()) {
                KhachHang kh = null;
                try {
                    kh = getSelectedDataTable();
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
                int row = tableShowInfo.getSelectedRow();
                try {
                    boolean result = khDAO.update(kh);
                    if (result == true) {
                        String date = formatDate(kh.getNgayHetHan());
                        modelTable.setValueAt(kh.getTenKH(), row, 1);
                        modelTable.setValueAt(kh.getCmnd(), row, 2);
                        modelTable.setValueAt(date, row, 3);
                        modelTable.setValueAt(kh.getLoaiKH(), row, 4);
                        modelTable.setValueAt(kh.getSoLanDatPhong(), row, 5);
                        showMessage("Cập nhật khách hàng thành công", SUCCESS);
                    } else {
                        showMessage("Lỗi: Cập nhật khách hàng thất bại", ERROR);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        } else if (o.equals(btnXoa)) {
            KhachHang kh = null;
            try {
                kh = getSelectedDataTable();
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
            int row = tableShowInfo.getSelectedRow();
            try {
                if (row == -1) {
                    showMessage("Lỗi: Bạn cần chọn dòng cần xóa", ERROR);
                } else {
                    int select;
                    select = JOptionPane.showConfirmDialog(this, "Bạn có muốn xoá dòng đã chọn ?", "Cảnh báo",
                            JOptionPane.YES_NO_OPTION);
                    if (select == JOptionPane.YES_OPTION) {
                        khDAO.delete(kh);
                        modelTable.removeRow(row);
                        showMessage("Xóa thành công", SUCCESS);
                    }
                }
            } catch (Exception e3) {
                showMessage("Xóa thất bại", ERROR);
            }
        } else if (o.equals(btnTim)) {
            if (validDataTim()) {
                String tenKH = txtTim.getText().trim();
                if (tenKH.isEmpty()) {
                    modelTable.getDataVector().removeAllElements();
                    ArrayList<KhachHang> ds = khDAO.getListKhachHang();
                    DocDuLieuVaoTable(ds);
                } else {
                    try {
                        modelTable.getDataVector().removeAllElements();
                        ArrayList<KhachHang> ds = khDAO.getListKhachHangByName(tenKH);
                        if (ds.size() <= 0) {
                            showMessage("Không tìm thấy khách hàng", ERROR);
                        } else
                            DocDuLieuVaoTable(ds);
                    } catch (Exception e4) {
                        showMessage("Không tìm thấy khách hàng", ERROR);
                    }
                }
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
        if (o.equals(txtTim)) {
            if (key.equals(KeyEvent.VK_ENTER)) {
                btnTim.doClick();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object o = e.getSource();
        if (o.equals(tableShowInfo)) {
            int row = tableShowInfo.getSelectedRow();
            txtMaKH.setText(modelTable.getValueAt(row, 0).toString());
            txtTenKH.setText(modelTable.getValueAt(row, 1).toString());
            txtCMND.setText(modelTable.getValueAt(row, 2).toString());
            try {
                dpNgayHetHan.setValue(modelTable.getValueAt(row, 3).toString());
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
            cboLoaiKhach.setSelectedItem(modelTable.getValueAt(row, 4).toString());
            txtSoLanDat.setText(modelTable.getValueAt(row, 5).toString());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private void showMessage(String message, JTextField txt) {
        lbShowMessages.setForeground(Color.RED);
        txt.requestFocus();
        txt.selectAll();
        lbShowMessages.setText(message);
        lbShowMessages.setIcon(errorIcon);
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

    private boolean validData() {
        String tenKH = txtTenKH.getText().trim();
        String cmnd = txtCMND.getText().trim();
        String soLanDat = txtSoLanDat.getText().trim();
        if (!(tenKH.length() > 0)) {
            showMessage("Lỗi: Tên không được để trống", txtTenKH);
            return false;
        } else if (tenKH.matches("\\d+")) {
            showMessage("Lỗi: Tên không được có số", txtTenKH);
            return false;
        }
        if (!(cmnd.length() > 0 && cmnd.matches("^(\\d{9}|\\d{12})$"))) {
            showMessage("Lỗi: CMND phải có 9 số hoặc CCCD phải có 12 số", txtCMND);
            return false;
        }
        if (soLanDat.length() > 0) {
            try {
                Integer x = Integer.parseInt(soLanDat);
                if (x < 0) {
                    showMessage("Lỗi: Số lần đặt phòng >= 0", txtSoLanDat);
                    return false;
                }
            } catch (NumberFormatException ex) {
                showMessage("Lỗi: Số lần đặt phòng phải nhập số.", txtSoLanDat);
                return false;
            }
        }
        return true;
    }

    private boolean validDataTim() {
        String TenKH = txtTim.getText().trim();
        if (!(TenKH.length() > 0)) {
            showMessage("Lỗi: Tên không được để trống", txtTim);
            return false;
        }
        return true;
    }

    private KhachHang getSelectedDataTable() throws ParseException {
        String ma = txtMaKH.getText().trim().equals("") ? "0" : txtMaKH.getText().trim();
        int maKH = Integer.parseInt(ma);
        String tenKH = txtTenKH.getText().trim();
        String cmnd = txtCMND.getText().trim();
        Date ngayHetHan = dpNgayHetHan.getFullDate();
        String loaiKH = cboLoaiKhach.getSelectedItem().toString().trim();
        int soLanDatPhong = Integer.parseInt(txtSoLanDat.getText().trim());
        KhachHang kh = new KhachHang(maKH, tenKH, cmnd, ngayHetHan, loaiKH, soLanDatPhong);
        return kh;
    }

    private void DocDuLieuVaoTable(ArrayList<KhachHang> dataList) {
        for (KhachHang item : dataList) {
            String date = formatDate(item.getNgayHetHan());
            modelTable.addRow(new Object[] { item.getMaKH(), item.getTenKH(), item.getCmnd(), date, item.getLoaiKH(),
                    item.getSoLanDatPhong() });
        }
    }

    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy");
        return sdf.format(date);
    }
}
