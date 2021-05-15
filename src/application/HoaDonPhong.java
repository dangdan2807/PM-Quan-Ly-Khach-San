package application;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class HoaDonPhong extends JFrame {

    private ImageIcon icon_pay = new ImageIcon("data/images/purse.png");
    private ImageIcon icon_in = new ImageIcon("data/images/printer.png");
    public JPanel pMain;

    public HoaDonPhong(){
        pMain = renderGUI();
    }

    public JPanel renderGUI() {
        JPanel pMain1 = new JPanel();
        
        JLabel lbHeader = new JLabel("Hoa don thanh toan");
        lbHeader.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        lbHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
        lbHeader.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        pMain1.add(lbHeader);
        // pMain1.setLayout(new BoxLayout(pMain1, BoxLayout.X_AXIS));
        JPanel pMain = new JPanel();
        pMain.setLayout(new BoxLayout(pMain, BoxLayout.X_AXIS));
        pMain1.add(pMain);
        // thông tin hóa đơn

        JPanel p_left = new JPanel(new FlowLayout());
        pMain.add(p_left);

        JPanel p_sec_info = new JPanel();
        p_sec_info.setLayout(new BoxLayout(p_sec_info, BoxLayout.Y_AXIS));
        p_sec_info.setBorder(BorderFactory.createEmptyBorder(0, 30, 10, 10));
        p_left.add(p_sec_info);
        
        // nội dung hóa đơn 
        JPanel p_sec_hd = new JPanel();
        p_sec_info.add(p_sec_hd);
        p_sec_hd.setLayout(new BoxLayout(p_sec_hd, BoxLayout.X_AXIS));
        String[] cols = {"Thong tin", "Noi dung"};
        DefaultTableModel modelHD = new DefaultTableModel(cols, 0);
        JTable tblHD = new JTable(modelHD);
        
        JScrollPane scroll1 = new JScrollPane(tblHD);
        // scroll1.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        p_sec_hd.add(scroll1);
        // p_sec_hd.add(tblHD);
        tblHD.setRowHeight(30);
        tblHD.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
        tblHD.getTableHeader().setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        // tblHD.getColumn

        modelHD.addRow(new Object[]{"Ma hoa don", "#123"});
        modelHD.addRow(new Object[]{"Ma khach hang", "1"});
        modelHD.addRow(new Object[]{"Ten khach hang", "Tran Van Nhan"});
        modelHD.addRow(new Object[]{"So CMND", "123456789"});
        modelHD.addRow(new Object[]{"Ngay het han", "12/03/1999"});
        modelHD.addRow(new Object[]{"Quoc tich", "Viet Nam"});
        modelHD.addRow(new Object[]{"So phong", "102"});
        modelHD.addRow(new Object[]{"Ngay den", "10/02/2000"});
        modelHD.addRow(new Object[]{"Ngay di", "11/02/2000"});
        modelHD.addRow(new Object[]{"So ngay o", "1"});
        modelHD.addRow(new Object[]{"So tien", "100.000"});

        // nội dung dịch vụ
        JPanel p_sec_dv = new JPanel();
        p_sec_info.add(p_sec_dv);
        p_sec_dv.setLayout(new BoxLayout(p_sec_dv, BoxLayout.X_AXIS));
        String[] cols2 = {"Dich vu", "So luong", "So tien"};
        DefaultTableModel modelDV = new DefaultTableModel(cols2, 0);
        JTable tblDV = new JTable(modelDV);
        JScrollPane scroll2 = new JScrollPane(tblDV);
        // scroll2.setBorder(BorderFactory.createEmptyBorder());
        p_sec_dv.add(scroll2);
        tblDV.setRowHeight(25);
        tblDV.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
        modelDV.addRow(new Object[]{"Xe dua don", "1", "200.000"});
        modelDV.addRow(new Object[]{"Nuoc ngot", "2", "30.000"});
        
        // tổng tiền
        JPanel p_right = new JPanel();
        pMain.add(p_right);
        JPanel p_sec_total = new JPanel();
        p_sec_total.setLayout(new BoxLayout(p_sec_total, BoxLayout.Y_AXIS));
        p_sec_total.setBorder(BorderFactory.createEmptyBorder(0, 20, 30, 30));
        
        p_right.add(p_sec_total);

        JPanel p_sec_t = new JPanel();
        p_sec_total.add(p_sec_t);
        p_sec_t.setBorder(BorderFactory.createTitledBorder("Tong tien"));

        // JLabel lbThanhToan = new JLabel("Tong tien thanh toan:");
        // p_sec_t.add(lbThanhToan);

        GridLayout grid = new GridLayout(3, 2);
        grid.setHgap(30);
        grid.setHgap(20);
        JPanel pGeneral = new JPanel(grid);
        p_sec_t.add(pGeneral);
        pGeneral.add(new JLabel("Tien phong: "));
        JLabel lbTienPhong = new JLabel("100.000đ");
        lbTienPhong.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
        pGeneral.add(lbTienPhong);
        

        pGeneral.add(new JLabel("Tien dich vu: "));
        JLabel lbTienDV = new JLabel("230.000đ");
        lbTienDV.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
        pGeneral.add(lbTienDV);

        pGeneral.add(new JLabel("Tong tien: "));
        JLabel lbTongTien = new JLabel("330.000đ");
        lbTongTien.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
        pGeneral.add(lbTongTien);

        JPanel p_sec_action = new JPanel();
        p_sec_total.add(p_sec_action);
        p_sec_action.setBorder(BorderFactory.createTitledBorder("Hanh dong"));

        GridLayout grid2 = new GridLayout(1, 2);
        grid2.setHgap(30);
        grid2.setHgap(20);
        JPanel pBtn = new JPanel(grid2);
        p_sec_action.add(pBtn);
        JButton btnThanhToan = new JButton("Thanh toan", icon_pay);
        JButton btnIn = new JButton("In hoa don", icon_in);
        pBtn.add(btnThanhToan);
        pBtn.add(btnIn);

        return pMain1;
    }
    
}
