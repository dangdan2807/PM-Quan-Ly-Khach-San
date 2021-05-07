package application;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
// import application.TrangChu;

public class QuanLyKhachSan extends JFrame implements ActionListener  {
    // thêm các page vô đây cho dễ nhớ
    
    private String[] nav = new String[]{
                                "Trang chu", 
                                "Quan ly hoa don phong",
                                "Quan ly hoa don dich vu",
                                "Quan ly phong",
                                "Quan ly dich vu",
                                "Quan ly nhan vien",
                                "Quan ly khach hang"
                            };
    // index ở đây tương ứng với mảng trên
    private int indx_nav = 0;

    // khai báo các lớp giao diện ở đây
    private TrangChu pageTrangChu = new TrangChu();
    // private QuanLy pageQuanLy = new QuanLy();

    // components
    private JPanel pMain;
    private JMenuBar menuBar;
    private JMenu menuTrangChu;
    private JMenu menuQLHoaDon;
    private JMenuItem itemQLHDPhong;
    private JMenuItem itemQLHDDichVu;
    private JMenu menuQLDichVu;
    private JMenuItem itemQLPhong;
    private JMenuItem itemQLDichVu;
    private JMenu menuQLKhachHang;
    private JMenu menuQLNhanVien;
    

    public QuanLyKhachSan(){
        setTitle("Quan Ly Khach San");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        createGUI();
    }

    public void createGUI(){
        // xóa hết vẽ lại
        getContentPane().removeAll();
        getContentPane().repaint();

        // render menu
        createMenuGUI();

        // hiển thị các page ở đây
        if(indx_nav == 0){// trang chủ
            this.add(pageTrangChu.renderGUI(), BorderLayout.CENTER);
        }
        // else if(indx_nav == 1){ // trang quản lý hóa đơn phòng
        //     this.add(pageQuanLy.renderGUI(), BorderLayout.CENTER);
        // }
        
    }


    public void createMenuGUI(){
        menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        // trang chu
        menuTrangChu = new JMenu("Trang chu");
        menuBar.add(menuTrangChu);

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

        // quản lý dịch vụ
        menuQLNhanVien = new JMenu("Quan ly nhan vien");
        menuBar.add(menuQLNhanVien);

        // thêm sự kiện click
        menuTrangChu.addActionListener(this);
        itemQLHDPhong.addActionListener(this);
        itemQLHDDichVu.addActionListener(this);
        itemQLPhong.addActionListener(this);
        itemQLDichVu.addActionListener(this);
        menuQLKhachHang.addActionListener(this);
        menuQLNhanVien.addActionListener(this);


    }

    public JLabel space(int width, int height){
        JLabel lbSpace = new JLabel();
        lbSpace.setBorder(BorderFactory.createEmptyBorder(height/2, width/2, height/2, width/2));
        return lbSpace;
    }

    public void addMenu(JPanel pMenu, JButton btn){
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

        if(obj == menuTrangChu){ // trang chủ
            System.out.println("Trang chu");
            indx_nav = 0;
            createGUI();
            
        }else if(obj == itemQLHDPhong){// quản lý hóa đơn phòng
            System.out.println("Quan ly hoa don phong");
            indx_nav = 1;
            createGUI();
        }
        // thêm tương tự như phía trên, indx_nav tương ứng với mảng nav trên đầu
    }

}
