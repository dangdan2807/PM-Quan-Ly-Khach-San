package application;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class QuanLyKhachSan extends JFrame implements ActionListener {
    // thêm các page vô đây cho dễ nhớ

    private String[] nav = new String[] { "Trang chu", "Dat phong", "Quan ly hoa don phong", "Quan ly hoa don dich vu",
            "Quan ly phong", "Quan ly dich vu", "Quan ly nhan vien", "Quan ly khach hang" };
    // index ở đây tương ứng với mảng trên
    private int indx_nav = 2;

    // khai báo các lớp giao diện ở đây
    private TrangChu pageTrangChu = new TrangChu();
    private DatPhong pageDatPhong = new DatPhong();
    private HoaDonPhong pageHDPhong = new HoaDonPhong();
    private QuanLyDichVu pageQLDichVu = new QuanLyDichVu();
    private QuanLyKhachHang pageQLKhachHang = new QuanLyKhachHang();
    // private QuanLy pageQuanLy = new QuanLy();

    // components
    private JMenuBar menuBar;
    private JMenu menuTrangChu, menuDatPhong, menuQLHoaDon, menuQLDichVu, menuQLKhachHang, menuQLNhanVien;
    private JMenuItem itemQLHDPhong, itemQLHDDichVu, itemQLPhong, itemQLDichVu;
    private JMenuItem itemTrangChu, itemDatPhong, itemQLKhachHang, itemQLNhanVien;
    private JPanel pnContainer;

    public QuanLyKhachSan() {
        setTitle("Quan Ly Khach San");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // setResizable(false);

        createMenuGUI();
        pnContainer = new JPanel();
        pnContainer.setLayout(new BoxLayout(pnContainer, BoxLayout.Y_AXIS));
        this.add(pnContainer);
        createGUI();

    }

    public void createGUI() {
        // xóa hết vẽ lại
        pnContainer.removeAll();
        pnContainer.invalidate();
        // hiển thị các page ở đây
        if (indx_nav == 0) {// trang chủ
            // System.out.println(pageTrangChu.pMain);

            pnContainer.add(pageTrangChu.pMain, BorderLayout.CENTER);
            // this.add(pageTrangChu.pMain, BorderLayout.CENTER);
        } else if (indx_nav == 1) { // trang đặt phòng
            // System.out.println(pageDatPhong.pMain);
            pnContainer.add(pageDatPhong.pMain, BorderLayout.CENTER);
            // this.add(pageDatPhong.pMain, BorderLayout.CENTER);
        } else if (indx_nav == 2) { // hóa đơn phòng
            pnContainer.add(pageHDPhong.pMain, BorderLayout.CENTER);
        } else if (indx_nav == 3) { //
            pnContainer.add(pageQLDichVu.pnMain, BorderLayout.CENTER);
        } else if (indx_nav == 4) { //
            pnContainer.add(pageQLKhachHang.pnMain, BorderLayout.CENTER);
        }

        pnContainer.revalidate();
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

        // thêm sự kiện click
        itemTrangChu.addActionListener(this);
        itemDatPhong.addActionListener(this);
        itemQLHDPhong.addActionListener(this);
        itemQLHDDichVu.addActionListener(this);
        itemQLPhong.addActionListener(this);
        itemQLDichVu.addActionListener(this);
        itemQLKhachHang.addActionListener(this);
        itemQLNhanVien.addActionListener(this);

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
        new QuanLyKhachSan().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        System.out.println("chuyen trang");

        if (obj == itemTrangChu) { // trang chủ
            System.out.println("Trang chu");
            indx_nav = 0;
            createGUI();

        } else if (obj == itemDatPhong) {// Đặt phòng
            System.out.println("Dat phong");
            indx_nav = 1;
            createGUI();
        } else if (obj == itemQLHDPhong) {// hóa đơn phòng
            System.out.println("Hoa don");
            indx_nav = 2;
            createGUI();
        } else if (obj == itemQLDichVu) {// quan ly dich vu
            System.out.println("Dich vu");
            indx_nav = 3;
            createGUI();
        } else if (obj == itemQLKhachHang) {// quan ly khach hang
            System.out.println("Khach hang");
            indx_nav = 4;
            createGUI();
        }
        // thêm tương tự như phía trên, indx_nav tương ứng với mảng nav trên đầu
    }

}
