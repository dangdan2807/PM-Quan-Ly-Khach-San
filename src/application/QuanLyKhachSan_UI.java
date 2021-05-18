package application;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public class QuanLyKhachSan_UI extends JFrame implements ActionListener {
    // thêm các page vô đây cho dễ nhớ

    private String[] nav = new String[] { "Trang chu", "Dat phong", "Quan ly hoa don phong", "Quan ly hoa don dich vu",
            "Quan ly phong", "Quan ly dich vu", "Quan ly nhan vien", "Quan ly khach hang" };
    // index ở đây tương ứng với mảng trên
    public int indx_nav = 0;

    // khai báo các lớp giao diện ở đây
    private TrangChu_UI pageTrangChu = new TrangChu_UI();
    private DatPhong_UI pageDatPhong = new DatPhong_UI();
    private HoaDonPhong_UI pageHDPhong = new HoaDonPhong_UI();
    private QuanLyDichVu_UI pageQLDichVu = new QuanLyDichVu_UI();
    private QuanLyKhachHang_UI pageQLKhachHang = new QuanLyKhachHang_UI();
    private ThongKeDichVu_UI pageTKeDichVu = new ThongKeDichVu_UI();
    private ThongKeKhachHang_UI pageTKeKhachHang = new ThongKeKhachHang_UI();
    // private QuanLy pageQuanLy = new QuanLy();

    private JPanel pnMain = pageTrangChu.pnMain;

    // components
    private JMenuBar menuBar;
    private JMenu menuTrangChu, menuDatPhong, menuQLHoaDon, menuQLDichVu, menuQLKhachHang, menuQLNhanVien, menuThongKe;
    private JMenuItem itemQLHDPhong, itemQLHDDichVu, itemQLPhong, itemQLDichVu;
    private JMenuItem itemTrangChu, itemDatPhong, itemQLKhachHang, itemQLNhanVien, itemThongKeDV, itemThongKeKH;
    // private JPanel pnContainer;

    public QuanLyKhachSan_UI() {
        setTitle("Quan Ly Khach San");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // setResizable(false);

        createMenuGUI();
        createGUI();

    }

    public QuanLyKhachSan_UI(int index) {
        setTitle("Quan Ly Khach San");
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

        // hiển thị các page ở đây
        if (indx_nav == 0) {// trang chủ
            pnMain = pageTrangChu.pnMain;
            handleEventTrangChu();
            handleEventThayDoiLoaiPhong();
        } else if (indx_nav == 1) { // trang đặt phòng
            pnMain = pageDatPhong.pnMain;
            pageDatPhong.renderDSPhongAvail();
            // pageDatPhong.renderHoaDon();
        } else if (indx_nav == 2) { // hóa đơn phòng
            pnMain = pageHDPhong.pnMain;

        } else if (indx_nav == 3) { //
            pnMain = pageQLDichVu.pnMain;

        } else if (indx_nav == 4) { //
            pnMain = pageQLKhachHang.pnMain;
        } else if (indx_nav == 5) { //
            pnMain = pageTKeDichVu.pnMain;
        } else if (indx_nav == 6) { //
            pnMain = pageTKeKhachHang.pnMain;
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
        menuTrangChu = new JMenu("Trang chu");
        menuBar.add(menuTrangChu);
        itemTrangChu = new JMenuItem("Trang chu");
        menuTrangChu.add(itemTrangChu);

        // trang chu
        menuDatPhong = new JMenu("Dat phong");
        menuBar.add(menuDatPhong);
        itemDatPhong = new JMenuItem("Dat phong");
        menuDatPhong.add(itemDatPhong);

        // quản lý hóa đơn
        menuQLHoaDon = new JMenu("Quan ly Hoa Don");
        menuBar.add(menuQLHoaDon);
        itemQLHDPhong = new JMenuItem("Quan ly hoa don phong");
        itemQLHDDichVu = new JMenuItem("Quan ly hoa don dich vu");
        menuQLHoaDon.add(itemQLHDPhong);
        menuQLHoaDon.add(itemQLHDDichVu);

        // quản lý dịch vụ
        menuQLDichVu = new JMenu("Quan ly dich vu");
        menuBar.add(menuQLDichVu);
        itemQLPhong = new JMenuItem("Quan ly phong");
        itemQLDichVu = new JMenuItem("Quan ly dich vu");
        menuQLDichVu.add(itemQLPhong);
        menuQLDichVu.add(itemQLDichVu);

        // quản lý khách hàng
        menuQLKhachHang = new JMenu("Quan ly khach hang");
        menuBar.add(menuQLKhachHang);
        itemQLKhachHang = new JMenuItem("Quan ly khach hang");
        menuQLKhachHang.add(itemQLKhachHang);

        // quản lý dịch vụ
        menuQLNhanVien = new JMenu("Quan ly nhan vien");
        menuBar.add(menuQLNhanVien);
        itemQLNhanVien = new JMenuItem("Quan ly nhan vien");
        menuQLNhanVien.add(itemQLNhanVien);

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
        itemQLNhanVien.addActionListener(this);
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
                    System.out.println("-> Hoa don");
                    indx_nav = 2;
                    createGUI();
                    pageTrangChu.popup.dispose();
                }
            });

            pageTrangChu.btn_DatPhong[j].addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    System.out.println("-> Dat phong");
                    indx_nav = 1;
                    pageDatPhong.maPhong = pageTrangChu.dsp.get(j).getMaPhong();
                    pageTrangChu.popup.dispose();
                    createGUI();
                }
            });
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        System.out.println("chuyen trang");

        if (obj == itemTrangChu) { // trang chủ
            System.out.println("-> Trang chu");
            indx_nav = 0;
            createGUI();
            
            
        } else if (obj == itemDatPhong) {// Đặt phòng
            System.out.println("-> Dat phong");
            indx_nav = 1;
            pageDatPhong.maPhong = 0;
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
        }else if (obj == itemThongKeKH) {// Thong ke dich vu
            System.out.println("-> Thong ke khach hang");
            indx_nav = 6;
            createGUI();
        }
        // thêm tương tự như phía trên, indx_nav tương ứng với mảng nav trên đầu
    }

    public void print(String msg){
        System.out.println(msg);
    }

}
