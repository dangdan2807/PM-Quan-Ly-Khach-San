package application;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.*;

import connectDB.ConnectDB;
import DAO.PhongDAO;
import DAO.LoaiPhongDAO;
import entity.*;

public class TrangChu_UI extends JFrame implements ActionListener{
    private PhongDAO phong_dao;
    private LoaiPhongDAO loaiPhong_dao;
    public ArrayList<Phong> dsp;
    public ArrayList<LoaiPhong> dslp;

    private int countAvail = 0;
    private int countBooking = 0;
    private int countUsing = 0;

    private JPanel pnPhongTrong;
    public ImageIcon icon_green_check = new ImageIcon("data/images/check.png", "check");
    public ImageIcon icon_red_close = new ImageIcon("data/images/close.png", "close");
    public ImageIcon icon_question = new ImageIcon("data/images/question_16.png");
    public ImageIcon icon_pay = new ImageIcon("data/images/purse.png");
    public ImageIcon icon_order = new ImageIcon("data/images/booking.png");
    public ImageIcon icon_add = new ImageIcon("data/images/plus.png");
    public ImageIcon icon_checkin = new ImageIcon("data/images/add.png");
    

    public JPanel pnMain;
    private DefaultComboBoxModel<String> modelLP;
    public JComboBox<String> cboLP;
    private JPanel pn_sec_available;
    private JLabel lbAvail;
    private JLabel lbUsing;

    public JButton[] btnPhong;
    public JButton[] btn_ThanhToan;
    public JButton[] btn_DatPhong;
    public JButton[] btn_NhanPhong;
    public JButton[] btn_SuDungDV;
    public JButton[] btn_XemLichDat;
    public JFrame popup = new JFrame();
    private JLabel lbBooking;

    public TrangChu_UI(){
        // khởi tạo
        try{
            ConnectDB.getInstance().connect();
        }catch(SQLException e){
            e.printStackTrace();
        }
        phong_dao = new PhongDAO();
        loaiPhong_dao = new LoaiPhongDAO();
        dsp = phong_dao.getListPhong();
        dslp = loaiPhong_dao.getAllLoaiPhong();
        
        // renderData();
    }

    public void start(){
        pnMain = renderGUI();
        renderDSPhong();
        renderLoaiPhong();
    }

    public JPanel renderGUI() {
        // nội dung page trang chủ ở đây
        pnMain = new JPanel();
        // pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));
        
        // action
        JPanel pn_interact = new JPanel();
        pn_interact.setLayout(new BoxLayout(pn_interact, BoxLayout.Y_AXIS));
        pnMain.add(pn_interact);

        JPanel pnFields = new JPanel();
        pn_interact.add(pnFields);
        JLabel lbLoaiPhong = new JLabel("Loại phòng: ");
        modelLP = new DefaultComboBoxModel<String>();
        cboLP = new JComboBox<String>(modelLP);
        cboLP.addActionListener(this);

        pnFields.add(lbLoaiPhong);
        pnFields.add(cboLP);


        JPanel pnThongKe = new JPanel();
        pnThongKe.setLayout(new BoxLayout(pnThongKe, BoxLayout.X_AXIS));
        pn_interact.add(pnThongKe);

        // so phong trong
        JPanel lb_sec_avail = new JPanel();
        lb_sec_avail.setBorder(BorderFactory.createEtchedBorder());
        pnThongKe.add(lb_sec_avail);
        lbAvail = new JLabel("Phòng trống (20)", icon_green_check, JLabel.CENTER);
        lb_sec_avail.add(lbAvail);
        lbAvail.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        pnThongKe.add(space(5, 5));

        // so phong đã được đặt
        JPanel lb_sec_booking = new JPanel();
        lb_sec_booking.setBorder(BorderFactory.createEtchedBorder());
        pnThongKe.add(lb_sec_booking);
        lbBooking = new JLabel("Đã đặt (20)", icon_question, JLabel.CENTER);
        lb_sec_booking.add(lbBooking);
        lbBooking.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // so phong dang dung
        JPanel lb_sec_using = new JPanel();
        lb_sec_using.setBorder(BorderFactory.createEtchedBorder());
        pnThongKe.add(lb_sec_using);
        lbUsing = new JLabel("Đang ở (20)", icon_red_close, JLabel.CENTER);
        lb_sec_using.add(lbUsing);
        lbUsing.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));


        // hiển thị các phòng trống
        pn_sec_available = new JPanel();
        pn_sec_available.setLayout(new BoxLayout(pn_sec_available, BoxLayout.Y_AXIS));
        // pn_sec_available.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnMain.add(pn_sec_available);

        JLabel lbDSP = new JLabel("Tình trạng phòng");
        lbDSP.setAlignmentX(Component.CENTER_ALIGNMENT);
        lbDSP.setFont(fontSize(25));
        pn_sec_available.add(lbDSP);
        pn_sec_available.add(space(10, 10));

        pnPhongTrong = new JPanel();
        // JScrollPane scroll = new JScrollPane(pnPhongTrong);
        // scroll.setBorder(BorderFactory.createEmptyBorder());
        pn_sec_available.add(pnPhongTrong);
        // pnPhongTrong.setLayout(new BoxLayout(pnPhongTrong, BoxLayout.X_AXIS));
        
        GridLayout grid_Phong = new GridLayout(0, 5);
        grid_Phong.setHgap(10);
        grid_Phong.setVgap(10);
        pnPhongTrong.setLayout(grid_Phong);
        
        return pnMain;
    }

    public void renderDSPhong(){
        pn_sec_available.remove(pnPhongTrong);
        pn_sec_available.revalidate();
        pn_sec_available.repaint();

        pnPhongTrong = new JPanel();
        GridLayout grid_Phong = new GridLayout(0, 5);
        grid_Phong.setHgap(10);
        grid_Phong.setVgap(10);
        pnPhongTrong.setLayout(grid_Phong);
        btnPhong = new JButton[dsp.size()];
        btn_ThanhToan = new JButton[dsp.size()];
        btn_DatPhong = new JButton[dsp.size()];
        btn_NhanPhong = new JButton[dsp.size()];
        btn_SuDungDV = new JButton[dsp.size()];
        btn_XemLichDat = new JButton[dsp.size()];
        for(int i=0; i<dsp.size(); i++){
            Phong phong = dsp.get(i);
            int j = i;
            // JPanel pnPhong = new JPanel();
            btnPhong[i] = new JButton();
            pnPhongTrong.add(btnPhong[i]);
            btnPhong[i].setLayout(new BoxLayout(btnPhong[i], BoxLayout.Y_AXIS));
            btnPhong[i].setAlignmentX(CENTER_ALIGNMENT);
            btnPhong[i].setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
            
            JLabel lbMaPhong = new JLabel(String.valueOf(phong.getMaPhong()));
            lbMaPhong.setAlignmentX(Component.CENTER_ALIGNMENT);
            lbMaPhong.setFont(fontSize(20));
            lbMaPhong.setForeground(Color.WHITE);

            JLabel lbLoai = new JLabel(phong.getLoaiPhong().getTenLoaiPhong());
            lbLoai.setFont(fontSize(20));
            lbLoai.setAlignmentX(Component.CENTER_ALIGNMENT);
            lbLoai.setForeground(Color.WHITE);

            JLabel lbIcon;
            if(phong.getTinhTrang() == 2){
                btnPhong[i].setBackground(Color.red);
                lbIcon = new JLabel(icon_red_close);
            }else if(phong.getTinhTrang() == 1){
                btnPhong[i].setBackground(Color.orange);
                lbIcon = new JLabel(icon_question);
            }else{
                btnPhong[i].setBackground(Color.green);
                lbIcon = new JLabel(icon_green_check);
                
            }
            lbIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
            lbIcon.setFont(fontSize(20));
            lbIcon.setForeground(Color.WHITE);

            btnPhong[i].add(lbMaPhong);
            btnPhong[i].add(lbLoai);
            btnPhong[i].add(space(0, 5));
            btnPhong[i].add(lbIcon);

            
            btn_DatPhong[j] = new JButton("Đặt phòng này", icon_order);
            btn_NhanPhong[j] = new JButton("Nhận phòng", icon_checkin);
            btn_ThanhToan[j] = new JButton("Trả phòng", icon_pay);
            btn_SuDungDV[j] = new JButton("Sử dụng dịch vụ", icon_add);
            btn_XemLichDat[j] = new JButton("Xem lịch đặt", icon_order);
            // sự kiện bấm vào phòng
            btnPhong[i].addActionListener(new ActionListener(){
                

                public void actionPerformed(ActionEvent e) {
                    // pnMain.setFocusable(false);
                    // pnMain.setVisible(false);
                    popup.dispose();
                    popup = new JFrame();
                    popup.setTitle("Thông tin phòng");
                    popup.setSize(400, 250);
                    popup.setResizable(false);
                    popup.setLocationRelativeTo(pnMain);
                    popup.setAlwaysOnTop(true);
                    // popup.setAutoRequestFocus(true);
                    

                    JPanel pn_p_main = new JPanel();
                    popup.add(pn_p_main);
                    pn_p_main.setLayout(new BoxLayout(pn_p_main, BoxLayout.Y_AXIS));

                    JPanel pn_p_top = new JPanel();
                    pn_p_main.add(pn_p_top);
                    pn_p_top.setLayout(new GridLayout(3, 2));
                    // pn_p_top.setLayout(new BoxLayout(pn_p_top, BoxLayout.Y_AXIS));
                    pn_p_top.setBorder(BorderFactory.createTitledBorder("Chi tiết phòng"));

                    String tinhTrang = "Có thể sử dụng";
                    if(phong.getTinhTrang() == 1)
                        tinhTrang = "Đã có người đặt";
                    else if(phong.getTinhTrang() == 2)
                        tinhTrang = "Đang ở";
                        
                    String gia = new QuanLyKhachSan_UI().currencyFormat(phong.getLoaiPhong().getDonGia());
                    pn_p_top.add(new JLabel("<html><p style='padding-left: 10px;'>Mã phòng: "+ phong.getMaPhong() +"</p></html>"));
                    pn_p_top.add(new JLabel("<html><p style='padding-left: 10px;'>Vị trí: "+ phong.getViTri() +"</p></html>"));
                    pn_p_top.add(new JLabel("<html><p style='padding-left: 10px;'>Số giường: "+ phong.getSoGiuong() +"</p></html>"));
                    pn_p_top.add(new JLabel("<html><p style='padding-left: 10px;'>Tình trạng: "+ tinhTrang +"</p></html>"));
                    pn_p_top.add(new JLabel("<html><p style='padding-left: 10px;'>Loại phòng: "+ phong.getLoaiPhong().getTenLoaiPhong() +"</p></html>"));
                    pn_p_top.add(new JLabel("<html><p style='padding-left: 10px;'>Đơn giá: "+ gia +"</p></html>"));

                    JPanel pn_p_c = new JPanel();
                    pn_p_main.add(pn_p_c);
                    JPanel pn_p_bottom = new JPanel();
                    pn_p_c.add(pn_p_bottom);
                    
                    if(phong.getTinhTrang() == 2){ // đang ở
                        pn_p_bottom.setLayout(new GridBagLayout());
                        GridBagConstraints c = new GridBagConstraints();
                        c.fill = GridBagConstraints.HORIZONTAL;
                        c.gridx = 0;
                        c.gridy = 0;
                        c.insets = new Insets(5, 5, 5, 5);

                        pn_p_bottom.add(btn_ThanhToan[j], c);
                        c.fill = GridBagConstraints.HORIZONTAL;
                        c.gridx = 1;
                        c.gridy = 0;
                        c.insets = new Insets(5, 5, 5, 5);
                        pn_p_bottom.add(btn_DatPhong[j], c);
                        c.fill = GridBagConstraints.HORIZONTAL;
                        c.gridx = 0;
                        c.gridy = 1;
                        c.gridwidth = 2;
                        c.insets = new Insets(5, 5, 5, 5);
                        pn_p_bottom.add(btn_XemLichDat[j], c);
                        
                    }else if(phong.getTinhTrang() == 1){ // đã đặt
                        pn_p_bottom.setLayout(new GridBagLayout());
                        GridBagConstraints c = new GridBagConstraints();
                        c.fill = GridBagConstraints.HORIZONTAL;
                        c.gridx = 0;
                        c.gridy = 0;
                        c.insets = new Insets(5, 5, 5, 5);
                        pn_p_bottom.add(btn_DatPhong[j], c);
                        c.fill = GridBagConstraints.HORIZONTAL;
                        c.gridx = 1;
                        c.gridy = 0;
                        c.insets = new Insets(5, 5, 5, 5);
                        pn_p_bottom.add(btn_NhanPhong[j], c);
                        c.fill = GridBagConstraints.HORIZONTAL;
                        c.gridx = 0;
                        c.gridy = 1;
                        c.gridwidth = 2;
                        c.insets = new Insets(5, 5, 5, 5);
                        pn_p_bottom.add(btn_XemLichDat[j], c);
                        
                    }else{// trống
                        pn_p_bottom.add(btn_DatPhong[j]);
                    }
                    popup.setVisible(true);

                    btn_NhanPhong[j].addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e) {
                            // hiển thị thông tin khách hàng để đối chiếu
                            popup.dispose();
                            HoaDonPhong hdp = phong.getHDPByMaPhongAndDate();
                            if(hdp == null){
                                JOptionPane.showMessageDialog(pnMain, "Chưa đến hạn nhận phòng");
                                return;
                            }
                            KhachHang kh = hdp.getKhachHang();
                            

                            popup = new JFrame();
                            
                            popup.setTitle("Thông tin hóa đơn");
                            popup.setSize(400, 350);
                            popup.setResizable(false);
                            popup.setLocationRelativeTo(pnMain);
                            popup.setAlwaysOnTop(true);

                            JPanel pn_p_main = new JPanel();
                            popup.add(pn_p_main);
                            pn_p_main.setLayout(new BoxLayout(pn_p_main, BoxLayout.Y_AXIS));

                            JPanel pn_p_top = new JPanel();
                            pn_p_main.add(pn_p_top);
                            pn_p_top.setLayout(new GridLayout(0, 2));
                            // pn_p_top.setLayout(new BoxLayout(pn_p_top, BoxLayout.Y_AXIS));
                            pn_p_top.setBorder(BorderFactory.createTitledBorder("Thông tin hóa đơn"));
                            pn_p_top.add(new JLabel("<html><p style='padding-left: 10px;'>Mã hóa đơn: "+hdp.getMaHD()+"</p></html>"));
                            pn_p_top.add(new JLabel("<html><p style='padding-left: 10px;'>Mã phòng: "+phong.getMaPhong()+"</p></html>"));
                            pn_p_top.add(new JLabel("<html><p style='padding-left: 10px;'>Ngày đến: "+hdp.getNgayGioNhan()+"</p></html>"));
                            pn_p_top.add(new JLabel("<html><p style='padding-left: 10px;'>Ngày đi: "+hdp.getNgayGioTra()+"</p></html>"));

                            pn_p_top.add(new JLabel("<html><p style='padding-left: 10px;'>Mã khách hàng: "+kh.getMaKH()+"</p></html>"));
                            pn_p_top.add(new JLabel("<html><p style='padding-left: 10px;'>Tên khách hàng: "+kh.getTenKH()+"</p></html>"));
                            pn_p_top.add(new JLabel("<html><p style='padding-left: 10px;'>Số CMND: "+kh.getCmnd()+"</p></html>"));
                            pn_p_top.add(new JLabel("<html><p style='padding-left: 10px;'>Ngày hết hạn: "+kh.getNgayHetHan()+"</p></html>"));
                            pn_p_top.add(new JLabel("<html><p style='padding-left: 10px;'>Loại khách hàng: "+kh.getLoaiKH()+"</p></html>"));
                            

                            JPanel pn_p_bottom = new JPanel();
                            pn_p_main.add(pn_p_bottom);
                            
                            JButton btn_XacNhan = new JButton("Xác nhận nhận phòng", icon_checkin);
                            pn_p_bottom.add(btn_XacNhan);
                            
                            popup.setVisible(true);
                            
                            btn_XacNhan.addActionListener(new ActionListener(){
                                public void actionPerformed(ActionEvent e) {
                                    popup.dispose();
                                    
                                    // cập nhật thành đã nhận phòng
                                    if(hdp.updateTinhTrang(1)){
                                        hdp.setTinhTrang(1);
                                        // cập nhật tình trạng phòng đang có người ở
                                        Phong phong = hdp.getPhong();
                                        phong.updateTinhTrang(2);
                                        btnPhong[j].setBackground(Color.red);
                                        JOptionPane.showMessageDialog(pnMain, "Đã nhận phòng");

                                        // cập nhật tình trạng
                                        dsp.get(j).setTinhTrang(2);
                                        
                                    }else{
                                        JOptionPane.showMessageDialog(pnMain, "Có lỗi xảy ra");
                                    }
                                }
                            });
                        }
                        
                    });

                    btn_XemLichDat[j].addActionListener(new ActionListener(){

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            popup.setAlwaysOnTop(false);
                            DialogLichDatPhong form = new DialogLichDatPhong();
                            form.setMaPhong(phong.getMaPhong());
                            form.setModal(true);
                            form.setVisible(true);
                        }
                        
                    });
                }
            });
        }
        pn_sec_available.add(pnPhongTrong);
        pn_sec_available.revalidate();
        pn_sec_available.repaint();

        getCount();
        lbAvail.setText("Phòng trống (" + (countAvail+countBooking) + ")");
        lbBooking.setText("Đã đặt (" + countBooking + ")");
        lbUsing.setText("Đang ở (" + countUsing + ")");
    }

    public void renderLoaiPhong(){
        modelLP.removeAllElements();
        modelLP.addElement("Tất cả");
        // System.out.println(dslp.size());
        for(int i=0; i<dslp.size(); i++){
            modelLP.addElement(dslp.get(i).getTenLoaiPhong());
        }
    }

    public void getCount(){
        countAvail = 0;
        countBooking = 0;
        countUsing = 0;
        for(int i = 0; i<dsp.size(); i++){
            if(dsp.get(i).getTinhTrang() == 0){
                countAvail++;
            }else if(dsp.get(i).getTinhTrang() == 1){
                countBooking++;
            }else{
                countUsing++;
            }
        }
    }

    public Font fontSize(int size){
        return new Font(Font.DIALOG, Font.PLAIN, size);
    }

    public JLabel space(int w, int h){
        JLabel space = new JLabel("");
        space.setBorder(BorderFactory.createEmptyBorder(h/2, w/2, h/2, w/2));
        return space;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if(obj == cboLP){
            int choose = cboLP.getSelectedIndex() - 1;
            if(choose == -1){
                dsp = phong_dao.getListPhong();
                renderDSPhong();
            }else{
                dsp = phong_dao.getPhongByMaLoaiPhong(dslp.get(choose).getMaLoaiPhong());
                renderDSPhong();
            }
        }
        // else if(obj == btnPhong){
        //     System.out.println("detail more");
        // }
    }

}
