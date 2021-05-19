package application;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.*;

import DAO.*;
import connectDB.ConnectDB;
import entity.*;

public class QLPhong_UI extends JFrame implements ActionListener, MouseListener {
    public JPanel pnMain;
    private JTextField txtMaLPhong, txtTenLPhong, txtDonGia, txtMaPhong, txtViTri, txtTimLP, txtTimPhong;
    private DefaultTableModel modelTableLP, modelTableP;
    private JTable tableLP, tableP;
    private JLabel lbShowMessagesDSP, lbShowMessagesDSLP, lbShowMessagesP, lbShowMessagesLP;
    private JButton btnThemLP, btnSuaLP, btnXoaLP, btnLamLaiLP, btnThemP, btnSuaP, btnXoaP, btnLamLaiP, btnTimLP,
            btnTimP;
    private SpinnerNumberModel modelSpinSC, modelSpinSG;
    private JSpinner spinSoGiuong, spinSucChua;
    private final int SUCCESS = 1, ERROR = 0;
    private JComboBox<String> cboTinhTrang, cboLoaiPhong;
    ImageIcon blueAddIcon = new ImageIcon("data/images/blueAdd_16.png");
    ImageIcon editIcon = new ImageIcon("data/images/edit2_16.png");
    ImageIcon deleteIcon = new ImageIcon("data/images/trash2_16.png");
    ImageIcon refreshIcon = new ImageIcon("data/images/refresh_16.png");
    ImageIcon searchIcon = new ImageIcon("data/images/search_16.png");
    ImageIcon checkIcon = new ImageIcon("data/images/check2_16.png");
    ImageIcon errorIcon = new ImageIcon("data/images/cancel_16.png");
    LoaiPhongDAO LPhongDAO = new LoaiPhongDAO();
    PhongDAO phongDAO = new PhongDAO();
    private ArrayList<LoaiPhong> dsLoaiPhong;

    public QLPhong_UI() {
        try {
            ConnectDB.getInstance().connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

        txtTenLPhong = new JTextField();
        txtTenLPhong.setBounds(115, 47, 210, 20);
        pnTL.add(txtTenLPhong);
        txtTenLPhong.setColumns(10);

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

        btnSuaLP = new JButton("Sửa", editIcon);
        btnSuaLP.setBounds(225, 133, 98, 26);
        pnTL.add(btnSuaLP);

        btnXoaLP = new JButton("Xóa", deleteIcon);
        btnXoaLP.setBounds(115, 133, 98, 26);
        pnTL.add(btnXoaLP);

        btnLamLaiLP = new JButton("Làm lại", refreshIcon);
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

        modelSpinSC = new SpinnerNumberModel(1, 1, null, 1);
        modelSpinSG = new SpinnerNumberModel(1, 1, null, 1);

        spinSucChua = new JSpinner(modelSpinSC);
        spinSucChua.setBounds(89, 47, 236, 20);
        pnBL.add(spinSucChua);

        JLabel lbSoGiuong = new JLabel("Số giường: ");
        lbSoGiuong.setBounds(10, 77, 79, 16);
        pnBL.add(lbSoGiuong);

        spinSoGiuong = new JSpinner(modelSpinSG);
        spinSoGiuong.setBounds(90, 75, 235, 20);
        pnBL.add(spinSoGiuong);

        JLabel lbTinhTrang = new JLabel("Tình trạng: ");
        lbTinhTrang.setBounds(10, 133, 79, 16);
        pnBL.add(lbTinhTrang);

        cboTinhTrang = new JComboBox<String>();
        cboTinhTrang.setBounds(90, 131, 235, 20);
        pnBL.add(cboTinhTrang);
        cboTinhTrang.addItem("Trống");
        cboTinhTrang.addItem("Đã được đặt");
        cboTinhTrang.addItem("Đã có người");

        JLabel lbViTri = new JLabel("Vị trí: ");
        lbViTri.setBounds(10, 105, 55, 16);
        pnBL.add(lbViTri);

        txtViTri = new JTextField();
        txtViTri.setBounds(90, 103, 235, 20);
        pnBL.add(txtViTri);
        txtViTri.setColumns(10);

        lbShowMessagesP = new JLabel("");
        lbShowMessagesP.setBounds(10, 189, 315, 16);
        pnBL.add(lbShowMessagesP);

        btnThemP = new JButton("Thêm", blueAddIcon);
        btnThemP.setBounds(7, 217, 98, 26);
        pnBL.add(btnThemP);

        btnXoaP = new JButton("Xóa", deleteIcon);
        btnXoaP.setBounds(117, 217, 98, 26);
        pnBL.add(btnXoaP);

        btnSuaP = new JButton("Sửa", editIcon);
        btnSuaP.setBounds(227, 217, 98, 26);
        pnBL.add(btnSuaP);

        btnLamLaiP = new JButton("Làm lại", refreshIcon);
        btnLamLaiP.setBounds(117, 255, 98, 26);
        pnBL.add(btnLamLaiP);

        JLabel lbLoaiPhong = new JLabel("Loại phòng: ");
        lbLoaiPhong.setBounds(10, 161, 79, 16);
        pnBL.add(lbLoaiPhong);

        cboLoaiPhong = new JComboBox<String>();
        cboLoaiPhong.setBounds(90, 159, 235, 20);
        pnBL.add(cboLoaiPhong);

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
        modelTableLP = new DefaultTableModel(colsLP, 0) {
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };
        tableLP = new JTable(modelTableLP);
        JScrollPane scpTableLP = new JScrollPane(tableLP, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pnTableLP.add(scpTableLP, BorderLayout.CENTER);

        lbShowMessagesDSLP = new JLabel("");
        lbShowMessagesDSLP.setBounds(370, 21, 263, 16);
        pnTR.add(lbShowMessagesDSLP);

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

        btnTimP = new JButton("Tìm", searchIcon);
        btnTimP.setBounds(240, 16, 98, 26);
        pnBR.add(btnTimP);

        JPanel pnTableP = new JPanel();
        pnTableP.setBounds(12, 49, 620, 236);
        pnBR.add(pnTableP);

        String[] colsP = { "Mã phòng", "Sức chứa", "Số Giường", "Vị trí", "Tình Trạng", "Loại phòng" };
        modelTableP = new DefaultTableModel(colsP, 0) {
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };
        pnTableP.setLayout(new BorderLayout(0, 0));
        tableP = new JTable(modelTableP);
        JScrollPane scpTableP = new JScrollPane(tableP, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pnTableP.add(scpTableP);

        lbShowMessagesDSP = new JLabel("");
        lbShowMessagesDSP.setBounds(345, 21, 287, 16);
        pnBR.add(lbShowMessagesDSP);

        btnThemLP.addActionListener(this);
        btnThemP.addActionListener(this);
        btnXoaLP.addActionListener(this);
        btnXoaP.addActionListener(this);
        btnSuaLP.addActionListener(this);
        btnSuaP.addActionListener(this);
        btnLamLaiLP.addActionListener(this);
        btnLamLaiP.addActionListener(this);
        btnTimLP.addActionListener(this);
        btnTimP.addActionListener(this);

        tableLP.addMouseListener(this);
        tableP.addMouseListener(this);
        loadCboLoaiPhong();
        DocDuLieuVaoTableLPhong(dsLoaiPhong);
        DocDuLieuVaoTablePhong(phongDAO.getAllListLoaiPhong());
    }

    public static void main(String[] args) {
        new QLPhong_UI().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnThemLP)) {
            if (validDataLoaiPhong()) {
                LoaiPhong loaiPhong = null;
                loaiPhong = getDataInFormLPhong();
                try {
                    boolean result = LPhongDAO.create(loaiPhong);
                    int maLPhong = LPhongDAO.getLatestID();
                    if (result == true) {
                        txtMaLPhong.setText(String.valueOf(maLPhong));
                        modelTableLP
                                .addRow(new Object[] { maLPhong, loaiPhong.getTenLoaiPhong(), loaiPhong.getDonGia() });
                        cboLoaiPhong.addItem(loaiPhong.getTenLoaiPhong());
                        showMessage("Thêm thành công", SUCCESS, lbShowMessagesLP);
                    } else {
                        showMessage("Lỗi: Thêm thất bại", ERROR, lbShowMessagesLP);
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        } else if (o.equals(btnThemP)) {
            if (validDataPhong()) {
                Phong phong = null;
                phong = getDataInFormPhong();
                try {
                    boolean result = phongDAO.create(phong);
                    if (result == true) {
                        String tinhTrang = "";
                        if (phong.getTinhTrang() == 1)
                            tinhTrang = "Trống";
                        else if (phong.getTinhTrang() == 2)
                            tinhTrang = "Đã được đặt";
                        else if (phong.getTinhTrang() == 3)
                            tinhTrang = "Đã có người";
                        int maLPhong = phong.getLoaiPhong().getMaLoaiPhong();
                        String tenLPhong = "";
                        for (LoaiPhong i : dsLoaiPhong) {
                            if (i.getMaLoaiPhong() == maLPhong) {
                                tenLPhong = i.getTenLoaiPhong();
                                break;
                            }
                        }
                        modelTableP.addRow(new Object[] { phong.getMaPhong(), phong.getSucChua(), phong.getSoGiuong(),
                                phong.getViTri(), tinhTrang, tenLPhong });
                        showMessage("Thêm thành công", SUCCESS, lbShowMessagesP);
                    } else {
                        showMessage("Lỗi: Thêm thất bại", ERROR, lbShowMessagesP);
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        } else if (o.equals(btnXoaLP)) {
            LoaiPhong loaiPhong = null;
            loaiPhong = getDataInFormLPhong();
            int row = tableLP.getSelectedRow();
            try {
                if (row == -1) {
                    showMessage("Lỗi: Bạn cần chọn dòng cần xóa", ERROR, lbShowMessagesLP);
                } else {
                    int select;
                    select = JOptionPane.showConfirmDialog(this, "Bạn có muốn xoá dòng đã chọn ?", "Cảnh báo",
                            JOptionPane.YES_NO_OPTION);
                    if (select == JOptionPane.YES_OPTION) {
                        LPhongDAO.delete(loaiPhong.getMaLoaiPhong());
                        modelTableLP.removeRow(row);
                        showMessage("Xóa thành công", SUCCESS, lbShowMessagesLP);
                    }
                }
            } catch (Exception e3) {
                showMessage("Xóa thất bại", ERROR, lbShowMessagesLP);
            }
        } else if (o.equals(btnXoaP)) {
            Phong phong = null;
            phong = getDataInFormPhong();
            int row = tableP.getSelectedRow();
            try {
                if (row == -1) {
                    showMessage("Lỗi: Bạn cần chọn dòng cần xóa", ERROR, lbShowMessagesP);
                } else {
                    int select;
                    select = JOptionPane.showConfirmDialog(this, "Bạn có muốn xoá dòng đã chọn ?", "Cảnh báo",
                            JOptionPane.YES_NO_OPTION);
                    if (select == JOptionPane.YES_OPTION) {
                        phongDAO.delete(phong.getMaPhong());
                        modelTableP.removeRow(row);
                        showMessage("Xóa thành công", SUCCESS, lbShowMessagesP);
                    }
                }
            } catch (Exception e3) {
                showMessage("Xóa thất bại", ERROR, lbShowMessagesP);
            }
        } else if (o.equals(btnSuaLP)) {
            if (validDataLoaiPhong()) {
                LoaiPhong loaiPhong = null;
                loaiPhong = getDataInFormLPhong();
                int row = tableLP.getSelectedRow();
                try {
                    boolean result = LPhongDAO.update(loaiPhong);
                    if (result == true) {
                        modelTableLP.setValueAt(loaiPhong.getTenLoaiPhong(), row, 1);
                        modelTableLP.setValueAt(loaiPhong.getDonGia(), row, 2);
                        showMessage("Cập nhật thành công", SUCCESS, lbShowMessagesLP);
                    } else {
                        showMessage("Lỗi: Cập nhật thất bại", ERROR, lbShowMessagesLP);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        } else if (o.equals(btnSuaP)) {
            if (validDataPhong()) {
                Phong phong = null;
                phong = getDataInFormPhong();
                int row = tableP.getSelectedRow();
                try {
                    boolean result = phongDAO.update(phong);
                    if (result == true) {
                        String tinhTrang = "";
                        if (phong.getTinhTrang() == 1)
                            tinhTrang = "Trống";
                        else if (phong.getTinhTrang() == 2)
                            tinhTrang = "Đã được đặt";
                        else if (phong.getTinhTrang() == 3)
                            tinhTrang = "Đã có người";
                        int maLPhong = phong.getLoaiPhong().getMaLoaiPhong();
                        String tenLPhong = "";
                        for (LoaiPhong i : dsLoaiPhong) {
                            if (i.getMaLoaiPhong() == maLPhong) {
                                tenLPhong = i.getTenLoaiPhong();
                                break;
                            }
                        }
                        modelTableP.setValueAt(phong.getSucChua(), row, 1);
                        modelTableP.setValueAt(phong.getSoGiuong(), row, 2);
                        modelTableP.setValueAt(phong.getViTri(), row, 3);
                        modelTableP.setValueAt(tinhTrang, row, 4);
                        modelTableP.setValueAt(tenLPhong, row, 5);

                        showMessage("Cập nhật thành công", SUCCESS, lbShowMessagesP);
                    } else {
                        showMessage("Lỗi: Cập nhật thất bại", ERROR, lbShowMessagesP);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        } else if (o.equals(btnLamLaiP)) {
            txtMaPhong.setText("");
            spinSucChua.setValue(1);
            spinSoGiuong.setValue(1);
            txtViTri.setText("");
            cboTinhTrang.setSelectedIndex(0);
        } else if (o.equals(btnLamLaiLP)) {
            txtMaLPhong.setText("");
            txtTenLPhong.setText("");
            txtDonGia.setText("");
        } else if (o.equals(btnTimLP)) {
            String tenLP = txtTimLP.getText().trim();
            if (tenLP.isEmpty()) {
                modelTableLP.getDataVector().removeAllElements();
                ArrayList<LoaiPhong> ds = LPhongDAO.getAllListLoaiPhong();
                DocDuLieuVaoTableLPhong(ds);
            } else {
                try {
                    modelTableLP.getDataVector().removeAllElements();
                    ArrayList<LoaiPhong> ds = LPhongDAO.getListLoaiPhongByName(tenLP);
                    if (ds.size() <= 0) {
                        showMessage("Không tìm thấy", ERROR, lbShowMessagesDSLP);
                    } else
                        DocDuLieuVaoTableLPhong(ds);
                } catch (Exception e4) {
                    showMessage("Không tìm thấy", ERROR, lbShowMessagesDSLP);
                }
            }
        } else if (o.equals(btnTimP)) {
            String maPhong = txtTimPhong.getText().trim();
            if (maPhong.isEmpty()) {
                modelTableP.getDataVector().removeAllElements();
                ArrayList<Phong> ds = phongDAO.getAllListLoaiPhong();
                DocDuLieuVaoTablePhong(ds);
            } else {
                try {
                    modelTableP.getDataVector().removeAllElements();
                    ArrayList<Phong> ds = phongDAO.getListLoaiPhongByID(maPhong);
                    if (ds.size() <= 0) {
                        showMessage("Không tìm thấy", ERROR, lbShowMessagesDSP);
                    } else
                        DocDuLieuVaoTablePhong(ds);
                } catch (Exception e4) {
                    showMessage("Không tìm thấy", ERROR, lbShowMessagesDSP);
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object o = e.getSource();
        if (o.equals(tableP)) {
            int row = tableP.getSelectedRow();
            txtMaPhong.setText(modelTableP.getValueAt(row, 0).toString());
            spinSucChua.setValue(Integer.parseInt(modelTableP.getValueAt(row, 1).toString()));
            spinSoGiuong.setValue(Integer.parseInt(modelTableP.getValueAt(row, 2).toString()));
            txtViTri.setText(modelTableP.getValueAt(row, 3).toString());
            cboTinhTrang.setSelectedItem(modelTableP.getValueAt(row, 4).toString());
            cboLoaiPhong.setSelectedItem(modelTableP.getValueAt(row, 5).toString());
        } else if (o.equals(tableLP)) {
            int row = tableLP.getSelectedRow();
            txtMaLPhong.setText(modelTableLP.getValueAt(row, 0).toString());
            txtTenLPhong.setText(modelTableLP.getValueAt(row, 1).toString());
            txtDonGia.setText(modelTableLP.getValueAt(row, 2).toString());
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

    private void showMessage(String message, JTextField txt, JLabel lbl) {
        lbl.setForeground(Color.RED);
        txt.requestFocus();
        txt.selectAll();
        lbl.setText(message);
        lbl.setIcon(errorIcon);
    }

    private void showMessage(String message, int type, JLabel lbl) {
        if (type == SUCCESS) {
            lbl.setForeground(Color.GREEN);
            lbl.setIcon(checkIcon);
        } else {
            lbl.setForeground(Color.RED);
            lbl.setIcon(errorIcon);
        }
        lbl.setText(message);
    }

    private boolean validDataLoaiPhong() {
        String tenLP = txtTenLPhong.getText().trim();
        String donGia = txtDonGia.getText().trim();

        if (!(tenLP.length() > 0)) {
            showMessage("Lỗi: Tên không được để trống", txtTenLPhong, lbShowMessagesLP);
            return false;
        } else if (tenLP.matches("\\d+")) {
            showMessage("Lỗi: Tên không được chứa số", txtTenLPhong, lbShowMessagesLP);
            return false;
        }
        if (donGia.length() > 0) {
            try {
                Double x = Double.parseDouble(donGia);
                if (x < 0) {
                    showMessage("Lỗi: Đơn giá >= 0", txtDonGia, lbShowMessagesLP);
                    return false;
                }
            } catch (Exception e) {
                showMessage("Lỗi: Đơn giá phải là số", txtDonGia, lbShowMessagesLP);
                return false;
            }
        }
        return true;
    }

    private boolean validDataPhong() {
        String maPhong = txtMaPhong.getText().trim();
        String viTri = txtViTri.getText().trim();
        if (!(maPhong.length() > 0)) {
            showMessage("Lỗi: Mã phòng không được để trống", txtMaPhong, lbShowMessagesP);
            return false;
        }
        if (!(viTri.length() > 0)) {
            showMessage("Lỗi: Mã phòng không được để trống", txtMaPhong, lbShowMessagesP);
            return false;
        }
        return true;
    }

    public LoaiPhong getDataInFormLPhong() {
        int maLPhong = Integer.parseInt(txtMaLPhong.getText().trim());
        String tenLPhong = txtTenLPhong.getText().trim();
        Double donGia = Double.parseDouble(txtDonGia.getText().trim());
        LoaiPhong loaiPhong = new LoaiPhong(maLPhong, tenLPhong, donGia);
        return loaiPhong;
    }

    public Phong getDataInFormPhong() {
        String maPhong = txtMaPhong.getText().trim();
        int sucChua = Integer.parseInt(spinSucChua.getValue().toString());
        int soGiuong = Integer.parseInt(spinSoGiuong.getValue().toString());
        String viTri = txtViTri.getText().trim();
        int tinhTrang = cboTinhTrang.getSelectedIndex() + 1;
        String tenLPhong = cboLoaiPhong.getSelectedItem().toString();
        LoaiPhong loaiPhong = null;
        System.out.println(tenLPhong);
        for (LoaiPhong item : dsLoaiPhong) {
            System.out.println(item.getTenLoaiPhong());
            if (item.getTenLoaiPhong().contains(tenLPhong)) {
                loaiPhong = item;
                break;
            }
        }
        Phong phong = new Phong(maPhong, sucChua, soGiuong, viTri, tinhTrang, loaiPhong);
        return phong;
    }

    private void DocDuLieuVaoTablePhong(ArrayList<Phong> dataList) {
        for (Phong item : dataList) {
            String tinhTrang = "";
            if (item.getTinhTrang() == 1)
                tinhTrang = "Trống";
            else if (item.getTinhTrang() == 2)
                tinhTrang = "Đã được đặt";
            else if (item.getTinhTrang() == 3)
                tinhTrang = "Đã có người";
            int maLPhong = item.getLoaiPhong().getMaLoaiPhong();
            String tenLPhong = "";
            for (LoaiPhong i : dsLoaiPhong) {
                if (i.getMaLoaiPhong() == maLPhong) {
                    tenLPhong = i.getTenLoaiPhong();
                    break;
                }
            }
            modelTableP.addRow(new Object[] { item.getMaPhong(), item.getSucChua(), item.getSoGiuong(), item.getViTri(),
                    tinhTrang, tenLPhong });
        }
    }

    private void DocDuLieuVaoTableLPhong(ArrayList<LoaiPhong> dataList) {
        for (LoaiPhong item : dataList) {
            modelTableLP.addRow(new Object[] { item.getMaLoaiPhong(), item.getTenLoaiPhong(), item.getDonGia() });
        }
    }

    private void loadCboLoaiPhong() {
        dsLoaiPhong = LPhongDAO.getAllListLoaiPhong();
        for (LoaiPhong item : dsLoaiPhong) {
            cboLoaiPhong.addItem(item.getTenLoaiPhong());
        }
    }
}