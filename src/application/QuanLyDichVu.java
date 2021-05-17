package application;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

import javax.swing.*;
import javax.swing.table.*;

import DAO.DichVuDAO;
import connectDB.ConnectDB;
import entity.DichVu;

public class QuanLyDichVu extends JPanel implements ActionListener, MouseListener, KeyListener {

    public JPanel pnMain;
    private JTable tableDV;
    private DefaultTableModel modelTableDV;
    private JTextField txtTim, txtMaDV, txtTenDV, txtDonGia;
    private JButton btnTim, btnThem, btnSua, btnXoa, btnLamLai;
    private JLabel lbShowMessages;
    private final int SUCCESS = 1, ERROR = 0;
    ImageIcon blueAddIcon = new ImageIcon("data/images/blueAdd_16.png");
    DichVuDAO dvDAO = new DichVuDAO();

    public QuanLyDichVu() {
        try {
            ConnectDB.getInstance().connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // setSize(1000, 700);
        // setLocationRelativeTo(null);
        // setDefaultCloseOperation(EXIT_ON_CLOSE);

        pnMain = new JPanel();
        pnMain.setLayout(null);
        pnMain.setBounds(0, 0, 1000, 700);

        this.add(pnMain);

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
        txtMaDV.setEditable(false);
        txtMaDV.setColumns(10);
        pnInfoDV.add(txtMaDV);

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
        txtDonGia.setText("0.0");
        txtDonGia.setBounds(81, 74, 310, 20);
        pnInfoDV.add(txtDonGia);
        txtDonGia.setColumns(10);

        btnThem = new JButton("Thêm");
        btnThem.setIcon(blueAddIcon);
        btnThem.setBounds(0, 132, 120, 30);
        pnInfoDV.add(btnThem);

        btnSua = new JButton("Sửa");
        btnSua.setBounds(139, 132, 120, 30);
        pnInfoDV.add(btnSua);

        btnXoa = new JButton("Xóa");
        btnXoa.setBounds(271, 132, 120, 30);
        pnInfoDV.add(btnXoa);

        btnLamLai = new JButton("Làm lại");
        btnLamLai.setBounds(139, 174, 120, 30);
        pnInfoDV.add(btnLamLai);

        lbShowMessages = new JLabel("");
        lbShowMessages.setBounds(0, 104, 391, 16);
        lbShowMessages.setForeground(Color.RED);
        pnInfoDV.add(lbShowMessages);

        String[] cols = { "Mã DV", "Tên DV", "Đơn Giá" };
        modelTableDV = new DefaultTableModel(cols, 0);

        JPanel pnShowDV = new JPanel();
        pnShowDV.setBounds(413, 46, 569, 613);
        pnShowDV.setLayout(null);
        pnMain.add(pnShowDV);

        JLabel lbTimKiem = new JLabel("Tên dịch vụ:");
        lbTimKiem.setBounds(0, 12, 75, 16);
        pnShowDV.add(lbTimKiem);

        txtTim = new JTextField();
        txtTim.setBounds(74, 10, 225, 20);
        pnShowDV.add(txtTim);
        txtTim.setColumns(10);

        btnTim = new JButton("Tìm");
        btnTim.setBounds(311, 10, 69, 20);
        pnShowDV.add(btnTim);

        JPanel pnTableDV = new JPanel();
        pnTableDV.setBounds(0, 35, 569, 578);
        pnShowDV.add(pnTableDV);
        pnTableDV.setLayout(new BorderLayout(0, 0));

        tableDV = new JTable(modelTableDV) {
            // khóa sửa dữ liệu trực tiếp trên table
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };
        JScrollPane scpTableDV = new JScrollPane(tableDV, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        pnTableDV.add(scpTableDV, BorderLayout.CENTER);

        // Sự kiện Action
        btnLamLai.addActionListener(this);
        btnThem.addActionListener(this);
        btnSua.addActionListener(this);
        btnXoa.addActionListener(this);
        btnTim.addActionListener(this);
        // sự kiện chuột
        tableDV.addMouseListener(this);
        txtTenDV.addMouseListener(this);
        txtDonGia.addMouseListener(this);
        // sự kiện phím enter
        txtTenDV.addKeyListener(this);
        txtDonGia.addKeyListener(this);
        txtTim.addKeyListener(this);
        DocDuLieuVaoTable(dvDAO.getListDichVu());
    }

    public static void main(String[] args) {
        new QuanLyDichVu().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnLamLai)) {
            txtMaDV.setText("");
            txtTenDV.setText("");
            txtDonGia.setText("0.0");
        } else if (o.equals(btnThem)) {
            if (validData()) {
                DichVu dv = getSelectedDataTable();
                try {
                    boolean result = dvDAO.create(dv);
                    int maDV = dvDAO.getLatestID();
                    if (result == true) {
                        txtMaDV.setText(String.valueOf(maDV));
                        modelTableDV.addRow(new Object[] { maDV, dv.getTenDV(), dv.getDonGia() });
                        showMessage("Thêm dịch vụ thành công", SUCCESS);
                    } else {
                        showMessage("Lỗi: Thêm dịch vụ thất bại", ERROR);
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        } else if (o.equals(btnSua)) {
            if (validData()) {
                DichVu dv = getSelectedDataTable();
                int row = tableDV.getSelectedRow();
                try {
                    boolean result = dvDAO.update(dv);
                    if (result == true) {
                        modelTableDV.setValueAt(dv.getTenDV(), row, 1);
                        modelTableDV.setValueAt(dv.getDonGia(), row, 2);
                        showMessage("Cập nhật dịch vụ thành công", SUCCESS);
                    } else {
                        showMessage("Lỗi: Cập nhật dịch vụ thất bại", ERROR);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        } else if (o.equals(btnXoa)) {
            DichVu dv = getSelectedDataTable();
            int row = tableDV.getSelectedRow();
            try {
                if (row == -1) {
                    showMessage("Lỗi: Bạn cần chọn dòng cần xóa", ERROR);
                } else {
                    int select;
                    select = JOptionPane.showConfirmDialog(this, "Bạn có muốn xoá dòng đã chọn ?", "Cảnh báo",
                            JOptionPane.YES_NO_OPTION);
                    if (select == JOptionPane.YES_OPTION) {
                        dvDAO.delete(dv);
                        modelTableDV.removeRow(row);
                        showMessage("Xóa thành công", SUCCESS);
                    }
                }
            } catch (Exception e3) {
                showMessage("Xóa thất bại", ERROR);
            }
        } else if (o.equals(btnTim)) {
            if (validDataTim()) {
                String tenDV = txtTim.getText().trim();
                if (tenDV.isEmpty()) {
                    modelTableDV.getDataVector().removeAllElements();
                    ArrayList<DichVu> ds = dvDAO.getListDichVu();
                    DocDuLieuVaoTable(ds);
                } else {
                    try {
                        modelTableDV.getDataVector().removeAllElements();
                        ArrayList<DichVu> ds = dvDAO.getListDichVuByName(tenDV);
                        if (ds.size() <= 0) {
                            showMessage("Không tìm thấy dịch vụ", ERROR);
                        } else
                            DocDuLieuVaoTable(ds);
                    } catch (Exception e4) {
                        showMessage("Không tìm thấy dịch vụ", ERROR);
                    }
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object o = e.getSource();
        if (o.equals(tableDV)) {
            int row = tableDV.getSelectedRow();
            txtMaDV.setText(modelTableDV.getValueAt(row, 0).toString());
            txtTenDV.setText(modelTableDV.getValueAt(row, 1).toString());
            txtDonGia.setText(modelTableDV.getValueAt(row, 2).toString());
        } else if (o.equals(txtTenDV)) {
            txtTenDV.selectAll();
        } else if (o.equals(txtDonGia)) {
            txtDonGia.selectAll();
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

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        Object o = e.getSource();
        Object key = e.getKeyCode();
        // bắt sự kiện nhấn phím enter tự nhấn btnLogin
        if (o.equals(txtTenDV) || o.equals(txtDonGia)) {
            if (key.equals(KeyEvent.VK_ENTER)) {
                btnThem.doClick();
            }
        } else if (o.equals(txtTim)) {
            if (key.equals(KeyEvent.VK_ENTER)) {
                btnTim.doClick();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private void showMessage(String message, JTextField txt) {
        lbShowMessages.setForeground(Color.RED);
        txt.requestFocus();
        txt.selectAll();
        lbShowMessages.setText(message);
    }

    private void showMessage(String message, int type) {
        if (type == SUCCESS)
            lbShowMessages.setForeground(Color.GREEN);
        else
            lbShowMessages.setForeground(Color.RED);
        lbShowMessages.setText(message);
    }

    private boolean validData() {
        String tenDV = txtTenDV.getText().trim();
        String donGia = txtDonGia.getText().trim();
        if (!(tenDV.length() > 0)) {
            showMessage("Lỗi: Tên không được để trống", txtTenDV);
            return false;
        } else if (donGia.length() > 0) {
            try {
                Double x = Double.parseDouble(donGia);
                if (x < 0) {
                    showMessage("Lỗi: Đơn giá >= 0", txtDonGia);
                    return false;
                }
            } catch (NumberFormatException ex) {
                showMessage("Lỗi: Đơn giá phải nhập số.", txtDonGia);
                return false;
            }
        }
        return true;
    }

    private boolean validDataTim() {
        String tenDV = txtTim.getText().trim();
        if (!(tenDV.length() > 0)) {
            showMessage("Lỗi: Tên không được để trống", txtTim);
            return false;
        }
        return true;
    }

    private DichVu getSelectedDataTable() {
        String ma = txtMaDV.getText().trim().equals("") ? "0" : txtMaDV.getText().trim();
        int maDV = Integer.parseInt(ma);
        String tenDV = txtTenDV.getText().trim();
        Double donGia = Double.parseDouble(txtDonGia.getText().trim());

        DichVu dv = new DichVu(maDV, tenDV, donGia);
        return dv;
    }

    private void DocDuLieuVaoTable(ArrayList<DichVu> dataList) {
        for (DichVu item : dataList) {
            modelTableDV.addRow(new Object[] { item.getMaDV(), item.getTenDV(), item.getDonGia() });
        }
    }
}
