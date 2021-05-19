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

    private JPanel pnPhongTrong;
    ImageIcon icon_green_check = new ImageIcon("data/images/check.png", "check");
    ImageIcon icon_red_close = new ImageIcon("data/images/close.png", "close");
    private ImageIcon icon_pay = new ImageIcon("data/images/purse.png");
    private ImageIcon icon_order = new ImageIcon("data/images/booking.png");

    public JPanel pnMain;
    private DefaultComboBoxModel<String> modelLP;
    public JComboBox<String> cboLP;
    private JPanel pn_sec_available;
    private JLabel lbAvail;
    private JLabel lbUsing;

    public JButton[] btnPhong;
    public JButton[] btn_ThanhToan;
    public JButton[] btn_DatPhong;
    public JFrame popup;

    public TrangChu_UI(){
        // khởi tạo
        try{
            ConnectDB.getInstance().connect();
        }catch(SQLException e){
            e.printStackTrace();
        }
        phong_dao = new PhongDAO();
        loaiPhong_dao = new LoaiPhongDAO();
        dsp = phong_dao.getAllPhong();
        dslp = loaiPhong_dao.getAllLoaiPhong();
        pnMain = renderGUI();
        // renderData();
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
        renderDSPhong();
        renderLoaiPhong();
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
            if(phong.getTinhTrang()){
                btnPhong[i].setBackground(Color.red);
                lbIcon = new JLabel(icon_red_close);
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

            btn_ThanhToan[j] = new JButton("Thanh toán", icon_pay);
            btn_DatPhong[j] = new JButton("Đặt phòng này", icon_order);
            // sự kiện bấm vào phòng
            btnPhong[i].addActionListener(new ActionListener(){
                

                public void actionPerformed(ActionEvent e) {
                    // pnMain.setFocusable(false);
                    // pnMain.setVisible(false);
                    popup = new JFrame();
                    popup.setTitle("Thông tin phòng");
                    popup.setSize(400, 200);
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

                    String tinhTrang = phong.getTinhTrang() ? "Đang ở" : "Có thể sử dụng";
                    pn_p_top.add(new JLabel("<html><p style='padding-left: 10px;'>Mã phòng: "+ phong.getMaPhong() +"</p></html>"));
                    pn_p_top.add(new JLabel("<html><p style='padding-left: 10px;'>Vị trí: "+ phong.getViTri() +"</p></html>"));
                    pn_p_top.add(new JLabel("<html><p style='padding-left: 10px;'>Số giường: "+ phong.getSoGiuong() +"</p></html>"));
                    pn_p_top.add(new JLabel("<html><p style='padding-left: 10px;'>Tình trạng: "+ tinhTrang +"</p></html>"));
                    pn_p_top.add(new JLabel("<html><p style='padding-left: 10px;'>Loại phòng: "+ phong.getLoaiPhong().getTenLoaiPhong() +"</p></html>"));
                    pn_p_top.add(new JLabel("<html><p style='padding-left: 10px;'>Đơn giá: "+ phong.getLoaiPhong().getDonGia() +"</p></html>"));

                    JPanel pn_p_bottom = new JPanel();
                    pn_p_main.add(pn_p_bottom);
                    
                    if(phong.getTinhTrang()){
                        pn_p_bottom.add(btn_ThanhToan[j]);
                        
                    }else{
                        pn_p_bottom.add(btn_DatPhong[j]);
                    }
                    popup.setVisible(true);
                }
            });
        }
        pn_sec_available.add(pnPhongTrong);
        pn_sec_available.revalidate();
        pn_sec_available.repaint();

        int countAvail = this.getCountAvailable();
        lbAvail.setText("Phòng trống (" + countAvail + ")");
        lbUsing.setText("Đang ở (" + (dsp.size() - countAvail) + ")");
    }

    public void renderLoaiPhong(){
        modelLP.removeAllElements();
        modelLP.addElement("Tất cả");
        // System.out.println(dslp.size());
        for(int i=0; i<dslp.size(); i++){
            modelLP.addElement(dslp.get(i).getTenLoaiPhong());
        }
    }

    public int getCountAvailable(){
        int c = 0;
        for(int i = 0; i<dsp.size(); i++){
            if(!dsp.get(i).getTinhTrang()){
                c++;
            }
        }
        return c;
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
        // TODO Auto-generated method stub
        Object obj = e.getSource();
        if(obj == cboLP){
            int choose = cboLP.getSelectedIndex() - 1;
            if(choose == -1){
                dsp = phong_dao.getAllPhong();
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
