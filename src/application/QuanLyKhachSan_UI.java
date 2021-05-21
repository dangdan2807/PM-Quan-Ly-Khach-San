package application;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.prefs.NodeChangeEvent;

import DAO.*;
import entity.*;

public class QuanLyKhachSan_UI extends JFrame implements ActionListener, ListSelectionListener{
    // thêm các page vô đây cho dễ nhớ

    private String[] nav = new String[] { "Trang chu", "Dat phong", "Quan ly hoa don phong", "Quan ly hoa don dich vu",
            "Quan ly phong", "Quan ly dich vu", "Quan ly nhan vien", "Quan ly khach hang" };
    // index ở đây tương ứng với mảng trên
    public int indx_nav = -1;

    // khai báo các lớp giao diện ở đây
    private TrangChu_UI pageTrangChu = new TrangChu_UI();
    private DatPhong_UI pageDatPhong = new DatPhong_UI();
    private ThanhToan_UI pageThanhToan = new ThanhToan_UI();
    private QuanLyDichVu_UI pageQLDichVu = new QuanLyDichVu_UI();
    private QuanLyKhachHang_UI pageQLKhachHang = new QuanLyKhachHang_UI();
    private ThongKeDichVu_UI pageTKeDichVu = new ThongKeDichVu_UI();
    private ThongKeKhachHang_UI pageTKeKhachHang = new ThongKeKhachHang_UI();
    private HoaDonDichVu_UI pageHDDichVu = new HoaDonDichVu_UI();
    private QLPhong_UI pageQLPhong = new QLPhong_UI();
    private MauDangNhap_UI pageLogin = new MauDangNhap_UI();
    // private QuanLy pageQuanLy = new QuanLy();

    
    ArrayList<HoaDonDV> dshddv = new ArrayList<HoaDonDV>();

    private JPanel pnMain = new JPanel();
    private JFrame popup = new JFrame();
    // components
    private JMenuBar menuBar;
    private JMenu menuTrangChu, menuDatPhong, menuQLHoaDon, menuQLDichVu, menuQLKhachHang, menuQLNhanVien, menuThongKe;
    private JMenuItem itemQLHDPhong, itemQLHDDichVu, itemQLPhong, itemQLDichVu;
    private JMenuItem itemTrangChu, itemDatPhong, itemQLKhachHang, itemQLNhanVien, itemThongKeDV, itemThongKeKH;

    // private JPanel pnContainer;
    private ImageIcon icon_quest = new ImageIcon("data/images/question.png");
    public QuanLyKhachSan_UI() {
        setTitle("Quản Lý Khách Sạn");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        createMenuGUI();
        createGUI();

    }

    public QuanLyKhachSan_UI(int index) {
        setTitle("Quản Lý Khách Sạn");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // setResizable(false);

        createMenuGUI();
        createGUI();
        indx_nav = index;

    }

    public void createGUI() {
        // xóa hết vẽ lại
        this.remove(pnMain);
        this.revalidate();
        this.repaint();
        popup.dispose();
        // hiển thị các page ở đây
        if(indx_nav == -1){ // login
            menuBar.setVisible(false);
            this.setSize(450, 350);
            setLocationRelativeTo(null);
            pnMain = pageLogin.pnMain;
            this.add(pnMain, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
            handleEventBtnLogin();
            return;
        }
        setSize(1000, 700);
        if (indx_nav == 0) {// trang chủ
            pageTrangChu.start();
            pnMain = pageTrangChu.pnMain;
            handleEventTrangChu();
            handleEventThayDoiLoaiPhong();
        } else if (indx_nav == 1) { // trang đặt phòng
            pageDatPhong.start();
            pnMain = pageDatPhong.pnMain;
            // pageDatPhong.renderDSPhong();
            handleEventTraPhong();
            // pageDatPhong.renderHoaDon();
        } else if (indx_nav == 2) { // hóa đơn phòng
            pageThanhToan.start();
            pnMain = pageThanhToan.pnMain;

        } else if (indx_nav == 3) { //
            pnMain = pageQLDichVu.pnMain;

        } else if (indx_nav == 4) { //
            pnMain = pageQLKhachHang.pnMain;
        } else if (indx_nav == 5) { //
            pnMain = pageTKeDichVu.pnMain;
        } else if (indx_nav == 6) { //
            pnMain = pageTKeKhachHang.pnMain;
        } else if (indx_nav == 7) {
            pnMain = pageHDDichVu.pnMain;
        } else if (indx_nav == 8) {
            pnMain = pageQLPhong.pnMain;
        }
        
        this.add(pnMain, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();

    }

    

    public void createMenuGUI() {
        menuBar = new JMenuBar();
        // menuBar.setLayout(new BoxLayout(menuBar, BoxLayout.X_AXIS));
        this.setJMenuBar(menuBar);
        // trang chu
        menuTrangChu = new JMenu("Trang chủ");
        menuBar.add(menuTrangChu);
        itemTrangChu = new JMenuItem("Trang chủ");
        menuTrangChu.add(itemTrangChu);

        // trang chu
        menuDatPhong = new JMenu("Đặt phòng");
        menuBar.add(menuDatPhong);
        itemDatPhong = new JMenuItem("Đặt phòng");
        menuDatPhong.add(itemDatPhong);

        // quản lý hóa đơn
        menuQLHoaDon = new JMenu("Quản lý Hóa Đơn");
        menuBar.add(menuQLHoaDon);
        itemQLHDPhong = new JMenuItem("Quản lý hóa đơn phòng");
        itemQLHDDichVu = new JMenuItem("Quản lý hóa đơn dịch vụ");
        menuQLHoaDon.add(itemQLHDPhong);
        // menuQLHoaDon.add(itemQLHDDichVu);

        // quản lý dịch vụ
        menuQLDichVu = new JMenu("Quản lý dịch vụ");
        menuBar.add(menuQLDichVu);
        itemQLPhong = new JMenuItem("Quản lý phòng");
        itemQLDichVu = new JMenuItem("Quản lý dịch vụ");
        menuQLDichVu.add(itemQLPhong);
        menuQLDichVu.add(itemQLDichVu);

        // quản lý khách hàng
        menuQLKhachHang = new JMenu("Quản lý khách hàng");
        menuBar.add(menuQLKhachHang);
        itemQLKhachHang = new JMenuItem("Quản lý khách hàng");
        menuQLKhachHang.add(itemQLKhachHang);

        // báo cáo
        menuThongKe = new JMenu("Thống kê");
        menuBar.add(menuThongKe);
        itemThongKeDV = new JMenuItem("Thống kê dịch vụ");
        itemThongKeKH = new JMenuItem("Thống kê khách hàng");
        menuThongKe.add(itemThongKeDV);
        menuThongKe.add(itemThongKeKH);

        // thêm sự kiện click
        itemTrangChu.addActionListener(this);
        itemDatPhong.addActionListener(this);
        itemQLHDPhong.addActionListener(this);
        itemQLHDDichVu.addActionListener(this);
        itemQLPhong.addActionListener(this);
        itemQLDichVu.addActionListener(this);
        itemQLKhachHang.addActionListener(this);
        itemThongKeDV.addActionListener(this);
        itemThongKeKH.addActionListener(this);

    }

    public JLabel space(int w, int h) {
        JLabel space = new JLabel("");
        space.setBorder(BorderFactory.createEmptyBorder(h / 2, w / 2, h / 2, w / 2));
        return space;
    }

    public void addMenu(JPanel pMenu, JButton btn) {
        btn.setBackground(Color.lightGray);
        btn.setBorder(BorderFactory.createEmptyBorder());
        pMenu.add(btn);
        pMenu.add(space(10, 6));
    }

    public static void main(String[] args) throws Exception {
        System.out.println("start!");
        new QuanLyKhachSan_UI().setVisible(true);
    }

    private void handleEventThayDoiLoaiPhong() {
        pageTrangChu.cboLP.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        handleEventTrangChu();
                    }
                }, 1000L); // 300 is the delay in millis
                
            }
        });
    }


    public void handleEventTrangChu(){
        // print(String.valueOf(pageTrangChu.dsp.size()));
        for(int i=0; i<pageTrangChu.dsp.size(); i++){
            int j = i;
            // System.out.println(pageTrangChu.btn_ThanhToan[i]);
            pageTrangChu.btn_ThanhToan[j].addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
        
                    Phong phong = pageTrangChu.dsp.get(j);
                    
                    HoaDonPhong hdp = phong.getHoaDonPhong();
                    // KhachHang kh = phong.getKHDangSuDungPhong();
                    // return;
                    System.out.println(hdp.getKhachHang().getTenKH());
                    dshddv = new HoaDonDV().getHDDVByMaKH(hdp.getKhachHang().getMaKH());
                    
                    chonHDDV(hdp);
                    
                }
            });

            pageTrangChu.btn_DatPhong[j].addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    pageTrangChu.popup.dispose();
                    System.out.println("-> Dat phong");
                    indx_nav = 1;
                    pageDatPhong.maPhong = pageTrangChu.dsp.get(j).getMaPhong();
                    pageTrangChu.popup.dispose();
                    createGUI();
                }
            });
        }
    }

    private void handleEventTraPhong() {
        pageDatPhong.tblDatPhong.getSelectionModel().addListSelectionListener(
            new ListSelectionListener(){
                public void valueChanged(ListSelectionEvent e) {
                    pageDatPhong.btn_TraPhong.addActionListener(new ActionListener(){
                        
                        public void actionPerformed(ActionEvent e) {
                            
                            int idx = pageDatPhong.tblDatPhong.getSelectedRow();
                            HoaDonPhong hdp = pageDatPhong.dshdp.get(idx);
                            // Phong phong = hdp.getPhong();
                            dshddv = new HoaDonDV().getHDDVByMaKH(hdp.getKhachHang().getMaKH());
                            // System.out.println(x);
                            chonHDDV(hdp);
                        }
                    });
                }
            }
        );
    }

    public void chonHDDV(HoaDonPhong hdp){
        popup.dispose();
        popup = new JFrame();
        popup.setTitle("Chọn hóa đơn dịch vụ");
        popup.setSize(500, 400);
        popup.setResizable(false);
        popup.setLocationRelativeTo(pnMain);
        popup.setAlwaysOnTop(true);
        JPanel pn_p_main = new JPanel();
        popup.add(pn_p_main);
        pn_p_main.setLayout(new BoxLayout(pn_p_main, BoxLayout.Y_AXIS));

        JPanel pn_p_top = new JPanel();
        pn_p_main.add(pn_p_top);
        JLabel lbMaHddv = new JLabel("Mã hóa đơn dịch vụ");
        JTextField txtMaHddv = new JTextField(10);
        JButton btnTimHDDV = new JButton("Tìm kiếm");
        pn_p_top.add(lbMaHddv);
        pn_p_top.add(txtMaHddv);
        pn_p_top.add(btnTimHDDV);

        JPanel pn_p_center = new JPanel();
        pn_p_main.add(pn_p_center);
        String[] cols = {"Mã hóa đơn", "Tên khách hàng", "Ngày giờ đặt"};
        DefaultTableModel modelHDDV = new DefaultTableModel(cols, 0);
        JTable tblHDDV = new JTable(modelHDDV);
        pn_p_center.add(new JScrollPane(tblHDDV));

        JPanel pn_p_bottom = new JPanel();
        pn_p_main.add(pn_p_bottom);
        JButton btnOke = new JButton("Đồng ý");
        pn_p_bottom.add(btnOke);
        JButton btnSkip = new JButton("Bỏ qua");
        pn_p_bottom.add(btnSkip);
        // render data
        
        for(int i=0; i<dshddv.size(); i++){
            modelHDDV.addRow(new Object[]{dshddv.get(i).getMaHDDV()
                , dshddv.get(i).getKhachHang().getTenKH(), dshddv.get(i).getNgayGioDat()});
        }
        popup.setVisible(true);

        btnOke.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                
                pageThanhToan.hdp = hdp;
                int idx = tblHDDV.getSelectedRow();
                if(idx != -1)
                    pageThanhToan.hddv = dshddv.get(idx);
                
                pageTrangChu.popup.dispose();
                popup.dispose();

                System.out.println("-> Thanh toán");
                indx_nav = 2;
                createGUI();
                
            }
        });

        btnSkip.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                pageThanhToan.hdp = hdp;
                pageTrangChu.popup.dispose();
                popup.dispose();

                System.out.println("-> Thanh toán");
                indx_nav = 2;
                createGUI();
                
            }
        });
    }

    private void handleEventBtnLogin() {
        pageLogin.btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(pageLogin.txtPassword.getPassword());
                if(pageLogin.txtUsername.getText().equals("admin") 
                && pageLogin.txtPassword.getText().equals("admin")){
                    setSize(1000, 700);
                    setLocationRelativeTo(null);
                    menuBar.setVisible(true);
                    indx_nav = 0;
                    createGUI();
                }else{
                    JOptionPane.showMessageDialog(pnMain, "Sai tài khoản hoặc mật khẩu");;
                    pageLogin.txtUsername.requestFocus();
                }
            }
        });

        pageLogin.btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj == itemTrangChu) { // trang chủ
            System.out.println("-> Trang chu");
            indx_nav = 0;
            createGUI();
            
            
        } else if (obj == itemDatPhong) {// Đặt phòng
            System.out.println("-> Dat phong");
            indx_nav = 1;
            pageDatPhong.maPhong = "0";
            createGUI();
        } else if (obj == itemQLHDPhong) {// hóa đơn phòng
            System.out.println("-> Hoa don");
            indx_nav = 2;
            createGUI();

        } else if (obj == itemQLDichVu) {// quan ly dich vu
            System.out.println("-> Dich vu");
            indx_nav = 3;
            createGUI();
        } else if (obj == itemQLKhachHang) {// quan ly khach hang
            System.out.println("-> Khach hang");
            indx_nav = 4;
            createGUI();
        } else if (obj == itemThongKeDV) {// Thong ke dich vu
            System.out.println("-> Thong ke dich vu");
            indx_nav = 5;
            createGUI();
        } else if (obj == itemThongKeKH) {// Thong ke dich vu
            System.out.println("-> Thong ke khach hang");
            indx_nav = 6;
            createGUI();
        } else if (obj == itemQLHDDichVu) {
            System.out.println("Hoa don dich vu");
            indx_nav = 7;
            createGUI();
        } else if (obj == itemQLPhong) {
            System.out.println("Quan ly phong va loai phong");
            indx_nav = 8;
            createGUI();
        }
        // thêm tương tự như phía trên, indx_nav tương ứng với mảng nav trên đầu
    }

    public void print(String msg){
        System.out.println(msg);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        // TODO Auto-generated method stub
        
    }

    // public void check

}
