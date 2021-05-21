package application;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import entity.*;


public class ThanhToan_UI extends JFrame {

    private ImageIcon icon_pay = new ImageIcon("data/images/purse.png");
    private ImageIcon icon_in = new ImageIcon("data/images/printer.png");
    public JPanel pnMain;
    // public int maHD = 0;
    // public int maHDDV = 0;
    public HoaDonPhong hdp = null;
    public HoaDonDV hddv = null;
    private DefaultTableModel modelHD;
    private DefaultTableModel modelDV;
    private JLabel lbTienPhong;
    private JLabel lbTienDV;
    private JLabel lbTongTien;
    private JButton btnThanhToan;
    private JButton btnIn;

    public ThanhToan_UI(){
        
    }

    public void start(){
        
        if(hdp != null){
            pnMain = renderGUI();
            renderData();
        }else{
            // pnMain
            pnMain.add(new JLabel("Vui lòng chòn hóa đơn để thanh toán !"));
        }
        

    }

    public JPanel renderGUI() {
        JPanel pnMain1 = new JPanel();
        
        JLabel lbHeader = new JLabel("Hóa đơn thanh toán");
        lbHeader.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        lbHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
        lbHeader.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        pnMain1.add(lbHeader);
        // pnMain1.setLayout(new BoxLayout(pnMain1, BoxLayout.X_AXIS));
        JPanel pnMain = new JPanel();
        pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.X_AXIS));
        pnMain1.add(pnMain);
        // thông tin hóa đơn

        JPanel p_left = new JPanel(new FlowLayout());
        pnMain.add(p_left);

        JPanel p_sec_info = new JPanel();
        
        p_sec_info.setLayout(new BoxLayout(p_sec_info, BoxLayout.Y_AXIS));
        p_sec_info.setBorder(BorderFactory.createEmptyBorder(0, 30, 10, 10));
        p_left.add(p_sec_info);
        
        // nội dung hóa đơn 
        JPanel p_sec_hd = new JPanel();
        p_sec_info.add(p_sec_hd);
        p_sec_hd.setLayout(new BoxLayout(p_sec_hd, BoxLayout.X_AXIS));
        String[] cols = {"Thông tin", "Nội dung"};
        modelHD = new DefaultTableModel(cols, 0);
        JTable tblHD = new JTable(modelHD);
        
        JScrollPane scroll1 = new JScrollPane(tblHD);
        // scroll1.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        scroll1.setPreferredSize(new Dimension(500, 392));
        p_sec_hd.add(scroll1);
        // p_sec_hd.add(tblHD);
        tblHD.setRowHeight(30);
        tblHD.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
        tblHD.getTableHeader().setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        // tblHD.getColumn

        

        // nội dung dịch vụ
        JPanel p_sec_dv = new JPanel();
        p_sec_info.add(p_sec_dv);
        p_sec_dv.setLayout(new BoxLayout(p_sec_dv, BoxLayout.X_AXIS));
        String[] cols2 = {"Dịch vụ", "Số lượng", "Số tiền"};
        modelDV = new DefaultTableModel(cols2, 0);
        JTable tblDV = new JTable(modelDV);
        JScrollPane scroll2 = new JScrollPane(tblDV);
        // scroll2.setBorder(BorderFactory.createEmptyBorder());
        scroll2.setPreferredSize(new Dimension(500, 150));
        p_sec_dv.add(scroll2);
        tblDV.setRowHeight(25);
        tblDV.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
        
        // p_sec_info.add(space(10, 10));
        
        // tổng tiền
        JPanel p_right = new JPanel();
        pnMain.add(p_right);
        JPanel p_sec_total = new JPanel();
        p_sec_total.setLayout(new BoxLayout(p_sec_total, BoxLayout.Y_AXIS));
        p_sec_total.setBorder(BorderFactory.createEmptyBorder(0, 20, 30, 30));
        
        p_right.add(p_sec_total);

        JPanel p_sec_t = new JPanel();
        p_sec_total.add(p_sec_t);
        p_sec_t.setBorder(BorderFactory.createTitledBorder("Tổng tiền"));

        // JLabel lbThanhToan = new JLabel("Tong tien thanh toan:");
        // p_sec_t.add(lbThanhToan);

        GridLayout grid = new GridLayout(3, 2);
        grid.setHgap(30);
        grid.setHgap(20);
        JPanel pGeneral = new JPanel(grid);
        p_sec_t.add(pGeneral);
        pGeneral.add(new JLabel("Tiền phòng: "));
        lbTienPhong = new JLabel("100.000đ");
        lbTienPhong.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
        pGeneral.add(lbTienPhong);
        

        pGeneral.add(new JLabel("Tiền dịch vụ: "));
        lbTienDV = new JLabel("230.000đ");
        lbTienDV.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
        pGeneral.add(lbTienDV);

        pGeneral.add(new JLabel("Tổng tiền: "));
        lbTongTien = new JLabel("330.000đ");
        lbTongTien.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
        pGeneral.add(lbTongTien);

        JPanel p_sec_action = new JPanel();
        p_sec_total.add(p_sec_action);
        p_sec_action.setBorder(BorderFactory.createTitledBorder("Hành động"));

        GridLayout grid2 = new GridLayout(1, 2);
        grid2.setHgap(30);
        grid2.setHgap(20);
        JPanel pBtn = new JPanel(grid2);
        p_sec_action.add(pBtn);
        btnThanhToan = new JButton("Thanh toán", icon_pay);
        btnIn = new JButton("In hóa đơn", icon_in);
        pBtn.add(btnThanhToan);
        pBtn.add(btnIn);

        return pnMain1;
    }

    public void renderData(){

        // System.out.println(maHD);
        // System.out.println(maHDDV);
        KhachHang kh = hdp.getKhachHang();
        Phong phong = hdp.getPhong();
        double tienphong = hdp.tinhTongTien();
        modelHD.addRow(new Object[]{"Mã hóa đơn", hdp.getMaHD()});
        modelHD.addRow(new Object[]{"Mã khách hàng", kh.getMaKH()});
        modelHD.addRow(new Object[]{"Tên khách hàng", kh.getTenKH()});
        modelHD.addRow(new Object[]{"Số CMND", kh.getCmnd()});
        modelHD.addRow(new Object[]{"Ngày hết hạn", kh.getNgayHetHan()});
        modelHD.addRow(new Object[]{"Loại khách hàng", kh.getLoaiKH()});
        modelHD.addRow(new Object[]{"Số phòng", phong.getMaPhong()});
        modelHD.addRow(new Object[]{"Ngày đến", hdp.getNgayGioNhan()});
        modelHD.addRow(new Object[]{"Ngày đi", hdp.getNgayGioTra()});
        modelHD.addRow(new Object[]{"Số ngày ở", hdp.tinhSoNgay()});
        modelHD.addRow(new Object[]{"Đơn giá", phong.getLoaiPhong().getDonGia()});
        modelHD.addRow(new Object[]{"Số tiền", tienphong});
        lbTienPhong.setText(String.valueOf(tienphong));

        // for(int i=0; i<)
        if(hddv != null){
            ArrayList<ChiTietDV> dsctdv = hddv.getChiTietDV();
            double tienDv = hddv.tinhTong();
            lbTienDV.setText(String.valueOf(tienDv));
            lbTongTien.setText(String.valueOf(tienphong+tienDv));
            for(int i=0; i<dsctdv.size(); i++){
                DichVu dv = dsctdv.get(i).getDichVu();
                
                modelDV.addRow(new Object[]{dv.getTenDV(), dsctdv.get(i).getSoLuong(), dv.getDonGia()});
            }
        }else{
            lbTienDV.setText("0");
            lbTongTien.setText(String.valueOf(tienphong));
        }
        


        
    }   

    public JLabel space(int w, int h){
        JLabel space = new JLabel("");
        space.setBorder(BorderFactory.createEmptyBorder(h/2, w/2, h/2, w/2));
        return space;
    }
    
}

