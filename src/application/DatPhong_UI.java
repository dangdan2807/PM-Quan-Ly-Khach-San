package application;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;

import connectDB.ConnectDB;
import DAO.PhongDAO;
import DAO.LoaiPhongDAO;
import DAO.HoaDonPhongDAO;
import entity.*;

public class DatPhong_UI extends JFrame implements ActionListener{
    private PhongDAO phong_dao;
    private LoaiPhongDAO loaiPhong_dao;
    private HoaDonPhongDAO hoaDonPhong_dao;
    public ArrayList<Phong> dsp, dsp_avail;
    public ArrayList<LoaiPhong> dslp;
    public ArrayList<HoaDonPhong> dshdp;
    
    public JPanel pnMain;
    public int maPhong = 0;
    private ImageIcon icon_add = new ImageIcon("data/images/add.png");
    private ImageIcon icon_refresh = new ImageIcon("data/images/refresh.png");
    private ImageIcon icon_trash = new ImageIcon("data/images/trash.png");
    private ImageIcon icon_edit = new ImageIcon("data/images/edit.png");
    private ImageIcon icon_search = new ImageIcon("data/images/magnifying-glass.png");
    private JTextField txtMaKH;
    private JTextField txtTenKH;
    private DefaultComboBoxModel<String> modelMaPhong;
    private JComboBox<String> cboMaPhong;
    private JTextField txtNgayDen;
    private JTextField txtNgayDi;
    private JTextField txtGhiChu;
    private JButton btnDatPhong;
    private JButton btnSua;
    private JButton btnHuy;
    private JButton btnClear;
    private DefaultTableModel modelAvail;
    private JTable tblAvail;
    private DefaultTableModel modelDatPhong;
    private JTable tblDatPhong;

    public DatPhong_UI(){
        // khởi tạo
        try{
            ConnectDB.getInstance().connect();
        }catch(SQLException e){
            e.printStackTrace();
        }
        phong_dao = new PhongDAO();
        loaiPhong_dao = new LoaiPhongDAO();
        hoaDonPhong_dao = new HoaDonPhongDAO();

        dsp = phong_dao.getAllPhong();
        dsp_avail = phong_dao.getPhongAvail();
        dslp = loaiPhong_dao.getAllLoaiPhong();
        dshdp = hoaDonPhong_dao.getAllHDPhong();
        pnMain = renderGUI();
        renderHoaDon();
    }

    public JPanel renderGUI() {
        JPanel pnMain = new JPanel();
        pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));
        pnMain.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JPanel pTop = new JPanel();
        pTop.setPreferredSize(new Dimension(1000, 400));
        pTop.setLayout(new BoxLayout(pTop, BoxLayout.X_AXIS));
        pnMain.add(pTop);
        
        // Fields
        JPanel p_sec_Fields = new JPanel();
        p_sec_Fields.setLayout(new BoxLayout(p_sec_Fields, BoxLayout.Y_AXIS));
        pTop.add(p_sec_Fields);
        pTop.add(space(20, 20));

        JPanel p_sec_f_top = new JPanel();
        p_sec_f_top.setLayout(new BoxLayout(p_sec_f_top, BoxLayout.X_AXIS));
        p_sec_Fields.add(p_sec_f_top);
        

        Box p_l = Box.createVerticalBox();
        Box p_r = Box.createVerticalBox();
        p_l.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
        // p_r.setLayout(new BoxLayout(p_r, BoxLayout.Y_AXIS));
        p_sec_f_top.add(p_l);
        p_sec_f_top.add(p_r);

        JLabel lbMaKH = new JLabel("Mã khách hàng");
        lbMaKH.setFont(fontSize(20));
        JLabel lbTenKH = new JLabel("Tên khách hàng");
        lbTenKH.setFont(fontSize(20));
        JLabel lbMaPhong = new JLabel("Mã phòng");
        lbMaPhong.setFont(fontSize(20));
        JLabel lbNgayDen = new JLabel("Ngày đến");
        lbNgayDen.setFont(fontSize(20));
        JLabel lbNgayDi = new JLabel("Ngày đi");
        lbNgayDi.setFont(fontSize(20));
        JLabel lbGhiChu = new JLabel("Ghi chú");
        lbGhiChu.setFont(fontSize(20));
        
        p_l.add(lbMaKH);
        p_l.add(Box.createVerticalStrut(10));
        p_l.add(lbTenKH);
        p_l.add(Box.createVerticalStrut(10));
        p_l.add(lbMaPhong);
        p_l.add(Box.createVerticalStrut(10));
        p_l.add(lbNgayDen);
        p_l.add(Box.createVerticalStrut(10));
        p_l.add(lbNgayDi);
        p_l.add(Box.createVerticalStrut(10));
        p_l.add(lbGhiChu);

        txtMaKH = new JTextField(10);
        txtTenKH = new JTextField(10);

        modelMaPhong = new DefaultComboBoxModel<String>();
        cboMaPhong = new JComboBox<String>(modelMaPhong);


        txtNgayDen = new JTextField(10);
        txtNgayDi = new JTextField(10);
        txtGhiChu = new JTextField(10);
        p_r.add(Box.createVerticalStrut(20));
        p_r.add(txtMaKH);
        p_r.add(Box.createVerticalStrut(10));
        p_r.add(txtTenKH);
        p_r.add(Box.createVerticalStrut(10));
        p_r.add(cboMaPhong);
        p_r.add(Box.createVerticalStrut(10));
        p_r.add(txtNgayDen);
        p_r.add(Box.createVerticalStrut(10));
        p_r.add(txtNgayDi);
        p_r.add(Box.createVerticalStrut(10));
        p_r.add(txtGhiChu);
        p_r.add(Box.createVerticalStrut(10));

        // check box
        JPanel p_sec_f_center = new JPanel();
        p_sec_Fields.add(p_sec_f_center);
        // lbNhanPhong.setFont(fontSize(20));
        JCheckBox chkIsNhanPhong = new JCheckBox("Nhận phòng ngay");
        p_sec_f_center.add(chkIsNhanPhong);

        // action
        JPanel p_sec_f_bottom = new JPanel();
        GridLayout grid = new GridLayout(2, 2);
        grid.setHgap(10);
        grid.setVgap(10);
        p_sec_f_bottom.setLayout(grid);
        p_sec_Fields.add(p_sec_f_bottom);

        btnDatPhong = new JButton("Đặt phòng", icon_add);
        btnSua = new JButton("Sửa", icon_edit);
        btnHuy = new JButton("Hủy", icon_trash);
        btnClear = new JButton("Làm lại", icon_refresh);
        p_sec_f_bottom.add(btnDatPhong);
        p_sec_f_bottom.add(btnSua);
        p_sec_f_bottom.add(btnHuy);
        p_sec_f_bottom.add(btnClear);
        btnDatPhong.addActionListener(this);
        btnSua.addActionListener(this);
        btnHuy.addActionListener(this);
        btnClear.addActionListener(this);

        // Danh sách phòng trống
        JPanel p_sec_DS = new JPanel();
        p_sec_DS.setLayout(new BoxLayout(p_sec_DS, BoxLayout.Y_AXIS));
        
        pTop.add(p_sec_DS);
        JLabel lbAvail = new JLabel("Danh sach phong trong");
        lbAvail.setAlignmentX(Component.CENTER_ALIGNMENT);
        p_sec_DS.add(lbAvail);
        String[] cols_avail = {"Ma phong", "Loai phong", "Suc chua", "So giuong", "Vi tri", "Gia phong"};
        modelAvail = new DefaultTableModel(cols_avail, 0);
        tblAvail = new JTable(modelAvail);
        // tblAvail.setLayout(new FlowLayout());
        // tblAvail.setPreferredSize(new Dimension(2000, 150));
        p_sec_DS.add(new JScrollPane(tblAvail));

        // modelAvail.addRow(new Object[]{"1", "vip", "2", "1", "Lau 1", "120.000"});
        // modelAvail.addRow(new Object[]{"2", "vip", "2", "1", "Lau 2", "120.000"});
        // modelAvail.addRow(new Object[]{"3", "vip", "2", "1", "Lau 3", "120.000"});


        // danh sách đặt phòng
        JPanel p_sec_table = new JPanel();
        pnMain.add(p_sec_table);

        JPanel pTimKiem = new JPanel();
        p_sec_table.add(pTimKiem);
        pTimKiem.add(new JLabel("Tim kiem: "));
        JTextField txtTim = new JTextField(20);
        pTimKiem.add(txtTim);
        JButton btnTim = new JButton("Tim kiem", icon_search);
        pTimKiem.add(btnTim);

        JPanel pTable = new JPanel();
        pTable.setLayout(new BoxLayout(pTable, BoxLayout.X_AXIS));
        pnMain.add(pTable);

        String[] cols_datphong = {"Mã hóa đơn", "Mã khách hàng", "Tên khách hàng", "Mã phòng", "Loại phòng", "Ngày đến", "Ngày đi", "Nhân viên", "Tình trạng"};
        modelDatPhong = new DefaultTableModel(cols_datphong, 0);
        tblDatPhong = new JTable(modelDatPhong);
        pTable.add(new JScrollPane(tblDatPhong));

        // modelDatPhong.addRow(new Object[]{"1", "1", "Tran Van Nhan", "1", "01-01-2001", "01-01-2001", ""});
        // modelDatPhong.addRow(new Object[]{"2", "1", "Tran Van Nhan", "1", "01-01-2001", "01-01-2001", ""});
        // modelDatPhong.addRow(new Object[]{"3", "1", "Tran Van Nhan", "1", "01-01-2001", "01-01-2001", ""});
        // modelDatPhong.addRow(new Object[]{"4", "1", "Tran Van Nhan", "1", "01-01-2001", "01-01-2001", ""});
        

        return pnMain;
    }

    public void renderDSPhongAvail(){
        // clear
        modelAvail.getDataVector().removeAllElements();
        modelMaPhong.removeAllElements();
        
        for(int i=0; i<dsp_avail.size(); i++){
            Phong phong = dsp_avail.get(i);
            int maPhong = phong.getMaPhong();
            String tenLoaiPhong = phong.getLoaiPhong().getTenLoaiPhong();
            int sucChua = phong.getSucChua();
            int soGiuong = phong.getSoGiuong();
            String viTri = phong.getViTri();
            Double donGia = phong.getLoaiPhong().getDonGia();
            // render data
            modelAvail.addRow(new Object[]{maPhong, tenLoaiPhong, sucChua, soGiuong, viTri, donGia});
            modelMaPhong.addElement(String.valueOf(maPhong));
            if(this.maPhong == maPhong){
                cboMaPhong.setSelectedIndex(i);
                tblAvail.addRowSelectionInterval(i, i);
            }
        }
    }

    public void renderHoaDon(){
        modelDatPhong.getDataVector().removeAllElements();
        for(int i=0; i<dshdp.size(); i++){
            int maHD = dshdp.get(i).getMaHD();
            Date ngayGioNhan = dshdp.get(i).getNgayGioNhan();
            Date ngayGioTra = dshdp.get(i).getNgayGioTra();
            Phong phong = dshdp.get(i).getPhong();
            int maKhachHang = dshdp.get(i).getKhachHang().getMaKH();
            String tenKhachHang = dshdp.get(i).getKhachHang().getTenKH();
            NhanVien nhanVien = dshdp.get(i).getNhanVien();
            modelDatPhong.addRow(
                new Object[]{maHD, maKhachHang, tenKhachHang, 
                    phong.getMaPhong(), phong.getLoaiPhong().getTenLoaiPhong(), 
                    ngayGioNhan, ngayGioTra, nhanVien.getTenNV(), "Đã đặt phòng"});
        }
    }

    public JLabel space(int w, int h){
        JLabel space = new JLabel("");
        space.setBorder(BorderFactory.createEmptyBorder(h/2, w/2, h/2, w/2));
        return space;
    }

    public Font fontSize(int size){
        return new Font(Font.DIALOG, Font.PLAIN, size);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if(obj == btnDatPhong){
            System.out.println("Dat phong");
            if(!txtMaKH.getText().matches("^[a-zA-Z0-9]+$")){
                renderError(txtMaKH, "Mã khách hàng chỉ được chứa chữ cái, chữ số và không được để trống");
                return;
            }

            if(!txtTenKH.getText().matches("^[a-zA-Z ]+$")){
                renderError(txtTenKH, "Tên khách hàng chỉ được chứa chữ cái, khoảng trắng và không được để trống");
                return;
            }

            // if(!txtGhiChu.getText().matches("^[a-zA-Z0-9,. ]*$")){
            //     renderError(txtGhiChu, "Ghi chú không hợp lệ");
            //     return;
            // }

            // String maKh = txtMaKH.getText();
            // String maKh = txtMaKH.getText();
            // String maKh = txtMaKH.getText();
            // String maKh = txtMaKH.getText();
            // String maKh = txtMaKH.getText();
        }
    }

    public void renderError(JTextField a, String message){
        a.requestFocus();
        a.selectAll();
        JOptionPane.showMessageDialog(pnMain, message);
    }
}
