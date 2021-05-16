package application;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class DatPhong extends JFrame {

    public JPanel pMain;
    private ImageIcon icon_add = new ImageIcon("data/images/add.png");
    private ImageIcon icon_refresh = new ImageIcon("data/images/refresh.png");
    private ImageIcon icon_trash = new ImageIcon("data/images/trash.png");
    private ImageIcon icon_edit = new ImageIcon("data/images/edit.png");
    private ImageIcon icon_search = new ImageIcon("data/images/magnifying-glass.png");

    public DatPhong(){
        pMain = renderGUI();
    }

    public JPanel renderGUI() {
        JPanel pMain = new JPanel();
        pMain.setLayout(new BoxLayout(pMain, BoxLayout.Y_AXIS));
        pMain.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JPanel pTop = new JPanel();
        pTop.setPreferredSize(new Dimension(1000, 400));
        pTop.setLayout(new BoxLayout(pTop, BoxLayout.X_AXIS));
        pMain.add(pTop);
        
        // Fields
        JPanel p_sec_Fields = new JPanel();
        p_sec_Fields.setLayout(new BoxLayout(p_sec_Fields, BoxLayout.Y_AXIS));
        pTop.add(p_sec_Fields);
        pTop.add(space(20, 20));

        JPanel p_sec_f_top = new JPanel();
        p_sec_f_top.setLayout(new BoxLayout(p_sec_f_top, BoxLayout.X_AXIS));
        p_sec_Fields.add(p_sec_f_top);
        JPanel p_sec_f_bottom = new JPanel();
        GridLayout grid = new GridLayout(2, 2);
        grid.setHgap(10);
        grid.setVgap(10);
        p_sec_f_bottom.setLayout(grid);
        p_sec_Fields.add(p_sec_f_bottom);

        Box p_l = Box.createVerticalBox();
        Box p_r = Box.createVerticalBox();
        p_l.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
        // p_r.setLayout(new BoxLayout(p_r, BoxLayout.Y_AXIS));
        p_sec_f_top.add(p_l);
        p_sec_f_top.add(p_r);

        JLabel lbMaKH = new JLabel("Ma KH");
        lbMaKH.setFont(fontSize(20));
        JLabel lbTenKH = new JLabel("Ten KH");
        lbTenKH.setFont(fontSize(20));
        JLabel lbMaPhong = new JLabel("Ma Phong");
        lbMaPhong.setFont(fontSize(20));
        JLabel lbNgayDen = new JLabel("Ngay den");
        lbNgayDen.setFont(fontSize(20));
        JLabel lbNgayDi = new JLabel("Ngay di");
        lbNgayDi.setFont(fontSize(20));
        JLabel lbGhiChu = new JLabel("Ghi chu");
        lbGhiChu.setFont(fontSize(20));
        
        p_l.add(lbMaKH);
        p_l.add(Box.createVerticalStrut(20));
        p_l.add(lbTenKH);
        p_l.add(Box.createVerticalStrut(20));
        p_l.add(lbMaPhong);
        p_l.add(Box.createVerticalStrut(20));
        p_l.add(lbNgayDen);
        p_l.add(Box.createVerticalStrut(20));
        p_l.add(lbNgayDi);
        p_l.add(Box.createVerticalStrut(20));
        p_l.add(lbGhiChu);

        JTextField txtMaKH = new JTextField(10);
        JTextField txtTenKH = new JTextField(10);

        DefaultComboBoxModel modelMaPhong = new DefaultComboBoxModel();
        JComboBox cboMaPhong = new JComboBox(modelMaPhong);
        // JTextField txtMaPhong = new JTextField(10);


        JTextField txtNgayDen = new JTextField(10);
        JTextField txtNgayDi = new JTextField(10);
        JTextField txtGhiChu = new JTextField(10);
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

        // action
        JButton btnDatPhong = new JButton("Dat phong", icon_add);
        JButton btnSua = new JButton("Sua", icon_edit);
        JButton btnHuy = new JButton("Huy", icon_trash);
        JButton btnClear = new JButton("Lam lai", icon_refresh);
        p_sec_f_bottom.add(btnDatPhong);
        p_sec_f_bottom.add(btnSua);
        p_sec_f_bottom.add(btnHuy);
        p_sec_f_bottom.add(btnClear);


        // Danh sách phòng trống
        JPanel p_sec_DS = new JPanel();
        p_sec_DS.setLayout(new BoxLayout(p_sec_DS, BoxLayout.Y_AXIS));
        
        pTop.add(p_sec_DS);
        JLabel lbAvail = new JLabel("Danh sach phong trong");
        lbAvail.setAlignmentX(Component.CENTER_ALIGNMENT);
        p_sec_DS.add(lbAvail);
        String[] cols_avail = {"Ma phong", "Loai phong", "Suc chua", "So giuong", "Vi tri", "Gia phong"};
        DefaultTableModel modelAvail = new DefaultTableModel(cols_avail, 0);
        JTable tblAvail = new JTable(modelAvail);
        // tblAvail.setLayout(new FlowLayout());
        // tblAvail.setPreferredSize(new Dimension(2000, 150));
        p_sec_DS.add(new JScrollPane(tblAvail));

        modelAvail.addRow(new Object[]{"1", "vip", "2", "1", "Lau 1", "120.000"});
        modelAvail.addRow(new Object[]{"2", "vip", "2", "1", "Lau 2", "120.000"});
        modelAvail.addRow(new Object[]{"3", "vip", "2", "1", "Lau 3", "120.000"});


        // danh sách đặt phòng
        JPanel p_sec_table = new JPanel();
        pMain.add(p_sec_table);

        JPanel pTimKiem = new JPanel();
        p_sec_table.add(pTimKiem);
        pTimKiem.add(new JLabel("Tim kiem: "));
        JTextField txtTim = new JTextField(20);
        pTimKiem.add(txtTim);
        JButton btnTim = new JButton("Tim kiem", icon_search);
        pTimKiem.add(btnTim);

        JPanel pTable = new JPanel();
        pTable.setLayout(new BoxLayout(pTable, BoxLayout.X_AXIS));
        pMain.add(pTable);

        String[] cols_datphong = {"Ma hoa don", "Ma KH", "Ten KH", "Ma Phong", "Ngay den", "Ngay di", "Ghi chu"};
        DefaultTableModel modelDatPhong = new DefaultTableModel(cols_datphong, 0);
        JTable tblDatPhong = new JTable(modelDatPhong);
        pTable.add(new JScrollPane(tblDatPhong));

        modelDatPhong.addRow(new Object[]{"1", "1", "Tran Van Nhan", "1", "01-01-2001", "01-01-2001", ""});
        modelDatPhong.addRow(new Object[]{"2", "1", "Tran Van Nhan", "1", "01-01-2001", "01-01-2001", ""});
        modelDatPhong.addRow(new Object[]{"3", "1", "Tran Van Nhan", "1", "01-01-2001", "01-01-2001", ""});
        modelDatPhong.addRow(new Object[]{"4", "1", "Tran Van Nhan", "1", "01-01-2001", "01-01-2001", ""});
        

        return pMain;
    }

    public JLabel space(int w, int h){
        JLabel space = new JLabel("");
        space.setBorder(BorderFactory.createEmptyBorder(h/2, w/2, h/2, w/2));
        return space;
    }

    public Font fontSize(int size){
        return new Font(Font.DIALOG, Font.PLAIN, size);
    }
}
