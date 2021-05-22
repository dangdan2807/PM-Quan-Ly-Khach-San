package application;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.sql.*;

import connectDB.ConnectDB;
import DAO.PhongDAO;
import DAO.LoaiPhongDAO;
import DAO.HoaDonPhongDAO;
import DAO.KhachHangDAO;
import entity.*;

public class DatPhong_UI extends JFrame implements ActionListener, ListSelectionListener{
    private PhongDAO phong_dao;
    private LoaiPhongDAO loaiPhong_dao;
    private HoaDonPhongDAO hoaDonPhong_dao;
    private KhachHangDAO khachHang_dao;
    public ArrayList<Phong> dsp, dsp_avail;
    public ArrayList<LoaiPhong> dslp;
    public ArrayList<HoaDonPhong> dshdp;
    public ArrayList<KhachHang> dskh;
    private int maHD = 0;
    
    public JPanel pnMain;
    public String maPhong = "0";
    private ImageIcon icon_add = new ImageIcon("data/images/add.png");
    private ImageIcon icon_refresh = new ImageIcon("data/images/refresh.png");
    private ImageIcon icon_trash = new ImageIcon("data/images/trash.png");
    private ImageIcon icon_edit = new ImageIcon("data/images/edit.png");
    private ImageIcon icon_search = new ImageIcon("data/images/magnifying-glass.png");
    private JTextField txtMaKH;
    private JTextField txtTenKH;
    private DefaultComboBoxModel<String> modelMaPhong;
    private JComboBox<String> cboMaPhong;
    private kDatePicker dpTuNgay, dpDenNgay;
    private JTextField txtGhiChu;
    private JButton btnDatPhong;
    private JButton btnSua;
    private JButton btnHuy;
    private JButton btnClear;
    private DefaultTableModel modelAvail;
    private JTable tblAvail;
    public DefaultTableModel modelDatPhong;
    public JTable tblDatPhong;
    private DefaultComboBoxModel modelMaKH;
    private JComboBox cboMaKH;
    private JCheckBox chkIsNotKH;
    private JCheckBox chkIsNhanPhong;
    private JTextField txtCMND;
    private JTextField txtNgayHetHan;
    private kDatePicker dpNgayHetHan;
    private DefaultComboBoxModel<String> modelLoaiKH;
    private JComboBox<String> cboLoaiKH;
    private JFrame popup = new JFrame();;
    private JButton btn_NhanPhong;
    private JButton btn_HuyPhong;
    public JButton btn_TraPhong = new JButton("Trả phòng");
    private JTextField txtTim;
    private JButton btnTim;
    private JComboBox cboTinhTrang;

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
        khachHang_dao = new KhachHangDAO();

        
    }

    public void start(){
        dsp = phong_dao.getAllPhong();
        dsp_avail = phong_dao.getPhongAvail();
        dslp = loaiPhong_dao.getAllLoaiPhong();
        dshdp = hoaDonPhong_dao.getListHDPhong();
        dskh = khachHang_dao.getListKhachHang();
        
        pnMain = renderGUI();
        renderHoaDon();
        renderKhachHang();
        renderDSPhong();
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
        JLabel lbCMND = new JLabel("Cmnd");
        lbCMND.setFont(fontSize(20));
        JLabel lbNgayHetHan = new JLabel("Ngày hết hạn");
        lbNgayHetHan.setFont(fontSize(20));
        JLabel lbLoaiKH = new JLabel("Loại khách hàng");
        lbLoaiKH.setFont(fontSize(20));
        JLabel lbMaPhong = new JLabel("Mã phòng");
        lbMaPhong.setFont(fontSize(20));
        JLabel lbNgayDen = new JLabel("Ngày đến");
        lbNgayDen.setFont(fontSize(20));
        JLabel lbNgayDi = new JLabel("Ngày đi");
        lbNgayDi.setFont(fontSize(20));
        
        p_l.add(lbMaKH);
        p_l.add(Box.createVerticalStrut(7));
        p_l.add(lbTenKH);
        p_l.add(Box.createVerticalStrut(7));
        p_l.add(lbCMND);
        p_l.add(Box.createVerticalStrut(7));
        p_l.add(lbNgayHetHan);
        p_l.add(Box.createVerticalStrut(7));
        p_l.add(lbLoaiKH);
        p_l.add(Box.createVerticalStrut(7));
        p_l.add(lbMaPhong);
        p_l.add(Box.createVerticalStrut(7));
        p_l.add(lbNgayDen);
        p_l.add(Box.createVerticalStrut(7));
        p_l.add(lbNgayDi);
        p_l.add(Box.createVerticalStrut(10));

        // txtMaKH = new JTextField(10);
        modelMaKH = new DefaultComboBoxModel();
        cboMaKH = new JComboBox(modelMaKH);
        
        cboMaKH.addActionListener(this);

        txtTenKH = new JTextField(10);
        txtTenKH.setEnabled(false);
        txtCMND = new JTextField(10);
        txtCMND.setEnabled(false);

        dpNgayHetHan = new kDatePicker(200);
        dpNgayHetHan.setPreferredSize(new Dimension(200, 40));
        dpNgayHetHan.setEnabled(false);
        
       
        modelLoaiKH = new DefaultComboBoxModel<String>();
        modelLoaiKH.addElement("Việt nam");
        modelLoaiKH.addElement("Nước ngoài");
        cboLoaiKH = new JComboBox<String>(modelLoaiKH);
        cboLoaiKH.setEnabled(false);

        modelMaPhong = new DefaultComboBoxModel<String>();
        cboMaPhong = new JComboBox<String>(modelMaPhong);

        dpTuNgay = new kDatePicker(200);
        dpTuNgay.setPreferredSize(new Dimension(200, 40));
        dpDenNgay = new kDatePicker(200);
        dpDenNgay.setPreferredSize(new Dimension(200, 40));

        txtGhiChu = new JTextField(10);
        p_r.add(Box.createVerticalStrut(15));
        p_r.add(cboMaKH);
        p_r.add(Box.createVerticalStrut(10));
        p_r.add(txtTenKH);
        p_r.add(Box.createVerticalStrut(10));
        p_r.add(txtCMND);
        p_r.add(Box.createVerticalStrut(10));
        p_r.add(dpNgayHetHan);
        p_r.add(Box.createVerticalStrut(0));
        p_r.add(cboLoaiKH);
        p_r.add(Box.createVerticalStrut(10));
        p_r.add(cboMaPhong);
        p_r.add(Box.createVerticalStrut(10));
        p_r.add(dpTuNgay);
        p_r.add(Box.createVerticalStrut(5));
        p_r.add(dpDenNgay);
        // p_r.add(Box.createVerticalStrut(0));

        // check box
        JPanel p_sec_f_center = new JPanel();
        p_sec_Fields.add(p_sec_f_center);
        // lbNhanPhong.setFont(fontSize(20));
        chkIsNotKH = new JCheckBox("Chưa là khách hàng");
        p_sec_f_center.add(chkIsNotKH);
        // chkIsNhanPhong = new JCheckBox("Nhận phòng ngay");
        // p_sec_f_center.add(chkIsNhanPhong);
        chkIsNotKH.addActionListener(this);
        // chkIsNhanPhong.addActionListener(this);
        // action
        JPanel p_sec_f_bottom = new JPanel();
        GridLayout grid = new GridLayout(0, 2);
        grid.setHgap(10);
        grid.setVgap(10);
        p_sec_f_bottom.setLayout(grid);
        p_sec_Fields.add(p_sec_f_bottom);

        btnDatPhong = new JButton("Đặt phòng", icon_add);
        btnSua = new JButton("Sửa", icon_edit);
        btnHuy = new JButton("Hủy", icon_trash);
        btnClear = new JButton("Làm lại", icon_refresh);
        p_sec_f_bottom.add(btnDatPhong);
        // p_sec_f_bottom.add(btnSua);
        // p_sec_f_bottom.add(btnHuy);
        p_sec_f_bottom.add(btnClear);
        btnDatPhong.addActionListener(this);
        btnSua.addActionListener(this);
        btnHuy.addActionListener(this);
        btnClear.addActionListener(this);

        // Danh sách phòng trống
        JPanel p_sec_DS = new JPanel();
        p_sec_DS.setLayout(new BoxLayout(p_sec_DS, BoxLayout.Y_AXIS));
        
        pTop.add(p_sec_DS);
        JLabel lbAvail = new JLabel("Danh sách phòng");
        lbAvail.setAlignmentX(Component.CENTER_ALIGNMENT);
        p_sec_DS.add(lbAvail);
        String[] cols_avail = {"Mã phòng", "Loại phòng", "Sức chứa", "Số giường", "Vị trí", "Giá phòng"};
        modelAvail = new DefaultTableModel(cols_avail, 0);
        tblAvail = new JTable(modelAvail);


        tblAvail.getSelectionModel().addListSelectionListener(new ListSelectionListener(){

            @Override
            public void valueChanged(ListSelectionEvent e) {
                int idx = tblAvail.getSelectedRow();


                DialogLichDatPhong form = new DialogLichDatPhong();
                form.setMaPhong(dsp.get(idx).getMaPhong());
                form.setModal(true);
                form.setVisible(true);
            }
        });
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
        pTimKiem.add(new JLabel("Mã hóa đơn: "));
        txtTim = new JTextField(20);
        pTimKiem.add(txtTim);
        btnTim = new JButton("Tim kiếm", icon_search);
        btnTim.addActionListener(this);
        pTimKiem.add(btnTim);

        pTimKiem.add(new JLabel("Lọc: "));
        DefaultComboBoxModel<String> modelTinhTrang = new DefaultComboBoxModel<String>();
        cboTinhTrang = new JComboBox(modelTinhTrang);
        modelTinhTrang.addElement("Tất cả");
        modelTinhTrang.addElement("Đã đặt phòng");
        modelTinhTrang.addElement("Đã nhận phòng");
        modelTinhTrang.addElement("Đã trả phòng");
        pTimKiem.add(cboTinhTrang);

        cboTinhTrang.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int idx = cboTinhTrang.getSelectedIndex();
                if(idx <= 0){
                    dshdp = hoaDonPhong_dao.getListHDPhong();
                }else{
                    dshdp = hoaDonPhong_dao.getListHDPhongByTinhTrang(idx-1);
                }
                renderHoaDon();
                
            }
            
        });

        JPanel pTable = new JPanel();
        pTable.setLayout(new BoxLayout(pTable, BoxLayout.X_AXIS));
        pnMain.add(pTable);

        String[] cols_datphong = {"Mã hóa đơn", "Mã khách hàng", "Tên khách hàng", "Mã phòng", "Loại phòng", "Ngày đến", "Ngày đi", "Tình trạng"};
        modelDatPhong = new DefaultTableModel(cols_datphong, 0);
        tblDatPhong = new JTable(modelDatPhong);
        pTable.add(new JScrollPane(tblDatPhong));
        tblDatPhong.getSelectionModel().addListSelectionListener(this);
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
            String maPhong = phong.getMaPhong();
            String tenLoaiPhong = phong.getLoaiPhong().getTenLoaiPhong();
            int sucChua = phong.getSucChua();
            int soGiuong = phong.getSoGiuong();
            String viTri = phong.getViTri();
            Double donGia = phong.getLoaiPhong().getDonGia();
            String gia = new QuanLyKhachSan_UI().currencyFormat(donGia);
            System.out.println(gia);
            // render data
            modelAvail.addRow(new Object[]{maPhong, tenLoaiPhong, sucChua, soGiuong, viTri, gia});
            modelMaPhong.addElement(String.valueOf(maPhong));
            if(this.maPhong.equals(maPhong)){
                cboMaPhong.setSelectedIndex(i);
                tblAvail.addRowSelectionInterval(i, i);
            }
        }
    }

    public void renderDSPhong(){
        // clear
        modelAvail.getDataVector().removeAllElements();
        modelMaPhong.removeAllElements();
        
        for(int i=0; i<dsp.size(); i++){
            Phong phong = dsp.get(i);
            String maPhong = phong.getMaPhong();
            String tenLoaiPhong = phong.getLoaiPhong().getTenLoaiPhong();
            int sucChua = phong.getSucChua();
            int soGiuong = phong.getSoGiuong();
            String viTri = phong.getViTri();
            Double donGia = phong.getLoaiPhong().getDonGia();
            String gia = new QuanLyKhachSan_UI().currencyFormat(donGia);
            // render data
            modelAvail.addRow(new Object[]{maPhong, tenLoaiPhong, sucChua, soGiuong, viTri, gia});
            modelMaPhong.addElement(String.valueOf(maPhong));
            if(this.maPhong.equals(maPhong)){
                cboMaPhong.setSelectedIndex(i);
            }
        }
    }

    public void renderHoaDon(){
        // tblDatPhong.removeRowSelectionInterval(0);
        // modelDatPhong.getDataVector().removeAllElements();
        modelDatPhong.setRowCount(0);
        for(int i=0; i<dshdp.size(); i++){
            int maHD = dshdp.get(i).getMaHD();
            Date ngayGioNhan = dshdp.get(i).getNgayGioNhan();
            Date ngayGioTra = dshdp.get(i).getNgayGioTra();
            Phong phong = dshdp.get(i).getPhong();
            int maKhachHang = dshdp.get(i).getKhachHang().getMaKH();
            String tenKhachHang = dshdp.get(i).getKhachHang().getTenKH();
            String tinhTrang = "Đã đặt phòng";
            
            if(dshdp.get(i).getTinhTrang() == 1)
                tinhTrang = "Đã nhận phòng";
            else if(dshdp.get(i).getTinhTrang() == 2)
                tinhTrang = "Đã thanh toán";
            modelDatPhong.addRow(
                new Object[]{maHD, maKhachHang, tenKhachHang, 
                    phong.getMaPhong(), phong.getLoaiPhong().getTenLoaiPhong(), 
                    ngayGioNhan, ngayGioTra, tinhTrang});

            if(this.maHD == maHD){
                tblDatPhong.addRowSelectionInterval(i, i);
            }
        }
    }

    public void renderKhachHang(){
        modelMaKH.removeAllElements();
        modelMaKH.addElement("");
        System.out.println(dskh.size());
        for(int i=0; i<dskh.size(); i++){
            int maKH = dskh.get(i).getMaKH();
	        String tenKH = dskh.get(i).getTenKH();

            // render data
            modelMaKH.addElement(String.valueOf(maKH + " - " + tenKH));
            
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
        long ml=System.currentTimeMillis(); 
        ml = ml/86400000*86400000;
        Date now = new Date(ml);
        if(obj == btnDatPhong){
            System.out.println("Dat phong");
            if(chkIsNotKH.isSelected()){
                
                if(txtTenKH.getText().trim().equals("")){
                    renderError(txtTenKH, "Tên khách hàng không được để trống");
                    return;
                }

                if(!txtTenKH.getText().matches("^[^0-9]{2,25}$")){
                    renderError(txtTenKH, "Tên khách hàng không được chứa chữ số, ít nhất là 2 ký tự");
                    return;
                }

                if(txtCMND.getText().trim().equals("")){
                    renderError(txtCMND, "Cmnd không được để trống");
                    return;
                }

                if(!txtCMND.getText().matches("^\\d{9,15}$")){
                    renderError(txtTenKH, "Cmnd chỉ được chứa chữ số, từ 9 đến 15 ký tự");
                    return;
                }
                
            }else{
                
                if(cboMaKH.getSelectedIndex() == 0){
                    JOptionPane.showMessageDialog(cboMaKH, "Mã khách hàng không được để trống");
                    return;
                }
            }

            Date ngayHetHan = new Date(ml);
            try {
                ngayHetHan = dpNgayHetHan.getFullDate();
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
            
            if(!ngayHetHan.toString().equals(now.toString()) && ngayHetHan.before(now)){
                JOptionPane.showMessageDialog(pnMain, "Giấy tờ đã hết hạn, không thể đặt phòng");
                return;
            }

            Date tuNgay = new Date(ml), denNgay = new Date(ml);
            
            
            try {
                tuNgay = dpTuNgay.getFullDate();
                denNgay = dpDenNgay.getFullDate(); 
            } catch (ParseException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            
            if(!tuNgay.toString().equals(now.toString()) && tuNgay.before(now)){
                JOptionPane.showMessageDialog(pnMain, "Ngày đến phải sau hoặc giống ngày hiện tại");
                return;
            }
            if(!tuNgay.toString().equals(denNgay.toString()) && denNgay.before(tuNgay)){
                JOptionPane.showMessageDialog(pnMain, "Ngày đi phải sau hoặc giống ngày đến");
                return;
            }
            KhachHang khachHang = null;
            if(chkIsNotKH.isSelected()){ // insert KH
            
                String tenKH = txtTenKH.getText();
                String cmnd = txtCMND.getText();
                
                String loaiKH = (String)cboLoaiKH.getSelectedItem();
                int soLanDatPhong = 0;
                khachHang = new KhachHang(0, tenKH, cmnd, ngayHetHan, loaiKH, soLanDatPhong);
                if(!khachHang_dao.insert(khachHang)){
                    JOptionPane.showMessageDialog(pnMain, "Không thể thêm khách hàng!");
                    return;
                }
                khachHang.setMaKH(khachHang_dao.getLatestID());
            }else{
                khachHang = dskh.get(cboMaKH.getSelectedIndex()-1);
            }
            
            // insert hóa đơn phòng

            Phong phong = dsp.get(cboMaPhong.getSelectedIndex());
            int tinhTrang = 0;
            HoaDonPhong hdp = new HoaDonPhong(0, tinhTrang, tuNgay, denNgay, phong, khachHang);
            if(hoaDonPhong_dao.insert(hdp)){
                JOptionPane.showMessageDialog(pnMain, "Đặt phòng thành công!");
                hdp.setMaHD(hoaDonPhong_dao.getLatestID());
                // dshdp.add(hdp);
                // maHD = hdp.getMaHD();
                cboTinhTrang.setSelectedIndex(0);
                
                
                // modelDatPhong.addRow(
                // new Object[]{hdp.getMaHD(), khachHang.getMaKH(), khachHang.getTenKH(), 
                //     phong.getMaPhong(), phong.getLoaiPhong().getTenLoaiPhong(), 
                //     tuNgay, denNgay, "Đã đặt phòng"});
            }else{
                JOptionPane.showMessageDialog(pnMain, "Không thể đặt phòng do trùng giờ đặt");
            }
            
        }else if(obj == cboMaKH){
            int indx = cboMaKH.getSelectedIndex() - 1;
            if(indx == -1){
                txtTenKH.setText("");
                txtCMND.setText("");
                dpNgayHetHan.setValue(now);
                cboLoaiKH.setSelectedIndex(0);
            }else{
                txtTenKH.setText(dskh.get(indx).getTenKH());
                txtCMND.setText(dskh.get(indx).getCmnd());
                dpNgayHetHan.setValue(dskh.get(indx).getNgayHetHan());
                cboLoaiKH.setSelectedItem(dskh.get(indx).getLoaiKH());
            }
            
        }else if(obj == chkIsNotKH){
            if(chkIsNotKH.isSelected()){
                txtTenKH.setEnabled(true);
                txtCMND.setEnabled(true);
                dpNgayHetHan.setEnabled(true);
                cboLoaiKH.setEnabled(true);
                txtTenKH.requestFocus();

                cboMaKH.setEnabled(false);
            }else{
                txtTenKH.setEnabled(false);
                txtCMND.setEnabled(false);
                dpNgayHetHan.setEnabled(false);
                cboLoaiKH.setEnabled(false);

                cboMaKH.setEnabled(true);
                cboMaKH.requestFocus();
            }
        }else if(obj == btn_NhanPhong){
            popup.dispose();
            int idx = tblDatPhong.getSelectedRow();
            HoaDonPhong hdp = dshdp.get(idx);
            // cập nhật thành đã nhận phòng
            if(hdp.updateTinhTrang(1)){
                // cập nhật tình trạng phòng đang có người ở
                Phong phong = hdp.getPhong();
                phong.updateTinhTrang(2);

                modelDatPhong.setValueAt("Đã nhận phòng", idx, 7);
                JOptionPane.showMessageDialog(pnMain, "Đã nhận phòng");
            }else{
                JOptionPane.showMessageDialog(pnMain, "Chưa tới thời hạn nhận phòng");
            }
            
        }else if(obj == btn_HuyPhong){
            popup.dispose();
            int idx = tblDatPhong.getSelectedRow();
            HoaDonPhong hdp = dshdp.get(idx);
            if(hoaDonPhong_dao.delete(hdp.getMaHD())){
                System.out.println("cap nhat tinh trang");
                hdp.getPhong().updateTinhTrang(0);
                modelDatPhong.removeRow(idx);
                dshdp.remove(idx);
                JOptionPane.showMessageDialog(pnMain, "Hủy thành công");
            }else{
                JOptionPane.showMessageDialog(pnMain, "Có lỗi xảy ra");
            }
        }else if(obj == btn_TraPhong){
            popup.dispose();
            // int idx = tblDatPhong.getSelectedRow();
            // HoaDonPhong hdp = dshdp.get(idx);
            // if(hoaDonPhong_dao.traPhong(hdp.getMaHD())){
            //     hdp.setTinhTrang(2);
            //     modelDatPhong.setValueAt("Đã trả phòng", idx, 7);
            //     JOptionPane.showMessageDialog(pnMain, "Đã trả phòng");
            // }else{
            //     JOptionPane.showMessageDialog(pnMain, "Có lỗi xảy ra");
            // }
        }else if(obj == btnClear){
            cboMaKH.setSelectedIndex(0);
            txtTenKH.setText("");
            txtCMND.setText("");
            dpNgayHetHan.setValue(now);
            cboMaPhong.setSelectedIndex(0);
            dpTuNgay.setValue(now);
            dpDenNgay.setValue(now);
        }else if(obj == btnTim){
            try{
                int MaHD = Integer.parseInt(txtTim.getText());
                tblDatPhong.clearSelection();
                for(int i =0; i<dshdp.size(); i++){
                    if(MaHD == dshdp.get(i).getMaHD()){
                        tblDatPhong.addRowSelectionInterval(i, i);
                        return;
                    }
                }
            }catch(Exception e1){
                JOptionPane.showMessageDialog(pnMain, "Mã hóa đơn phải là số");
            }
            

        }
    }

    public void renderError(JTextField a, String message){
        a.requestFocus();
        a.selectAll();
        JOptionPane.showMessageDialog(pnMain, message);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        // TODO Auto-generated method stub
        // System.out.println(e.getValueIsAdjusting());
        if(e.getValueIsAdjusting()){
            int idx = tblDatPhong.getSelectedRow();
            HoaDonPhong hdp = dshdp.get(idx);
            if(hdp.getTinhTrang() == 2)
                return;

            Object obj = e.getSource();

            popup.dispose();
            popup = new JFrame();
            popup.setTitle("Hành động");
            popup.setSize(300, 150);
            popup.setResizable(false);
            popup.setLocationRelativeTo(pnMain);
            popup.setAlwaysOnTop(true);

            JPanel pn_p_main = new JPanel();
            popup.add(pn_p_main);
            // pn_p_main.setLayout(new BoxLayout(pn_p_main, BoxLayout.X_AXIS));

            JPanel pn_p_top = new JPanel();
            pn_p_main.add(pn_p_top);
            GridLayout grd = new GridLayout(0, 2);
            grd.setHgap(10);
            
            pn_p_top.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

            btn_NhanPhong = new JButton("Nhận phòng");
            btn_HuyPhong = new JButton("Hủy phòng");
            btn_TraPhong = new JButton("Trả phòng");
            btn_NhanPhong.addActionListener(this);
            btn_HuyPhong.addActionListener(this);
            btn_TraPhong.addActionListener(this);

            
            if(hdp.getTinhTrang() == 0){
                pn_p_top.setLayout(grd);
                pn_p_top.add(btn_NhanPhong);
                pn_p_top.add(btn_HuyPhong);
            }else if(hdp.getTinhTrang() == 1){
                // pn_p_top.setLayout();
                pn_p_top.add(btn_TraPhong);
            }
            
            popup.setVisible(true);
            // }
        }
    }
}
