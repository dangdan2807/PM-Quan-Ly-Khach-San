package application;

import java.awt.*;
import javax.swing.*;

public class TrangChu extends JFrame {
    
    private JPanel pnPhongTrong;
    private int So_Phong = 30;
    ImageIcon icon_green_check = new ImageIcon("data/images/check.png", "check");
    ImageIcon icon_red_close = new ImageIcon("data/images/close.png", "close");
    public JPanel pnMain;

    public TrangChu(){
        pnMain = renderGUI();
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
        JLabel lbLoaiPhong = new JLabel("Loai phong: ");
        DefaultComboBoxModel<String> modelLP = new DefaultComboBoxModel<String>();
        JComboBox<String> cboLP = new JComboBox<String>(modelLP);
        modelLP.addElement("Tat ca");
        modelLP.addElement("Vip");
        modelLP.addElement("Thuong");

        pnFields.add(lbLoaiPhong);
        pnFields.add(cboLP);


        JPanel pnThongKe = new JPanel();
        pnThongKe.setLayout(new BoxLayout(pnThongKe, BoxLayout.X_AXIS));
        pn_interact.add(pnThongKe);

        // so phong trong
        JPanel lb_sec_avail = new JPanel();
        lb_sec_avail.setBorder(BorderFactory.createEtchedBorder());
        pnThongKe.add(lb_sec_avail);
        JLabel lbAvail = new JLabel("Phong trong (20)", icon_green_check, JLabel.CENTER);
        lb_sec_avail.add(lbAvail);
        lbAvail.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        pnThongKe.add(space(5, 5));

        // so phong dang dung
        JPanel lb_sec_using = new JPanel();
        lb_sec_using.setBorder(BorderFactory.createEtchedBorder());
        pnThongKe.add(lb_sec_using);
        JLabel lbUsing = new JLabel("Dang o (20)", icon_red_close, JLabel.CENTER);
        lb_sec_using.add(lbUsing);
        lbUsing.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));


        // hiển thị các phòng trống
        JPanel pn_sec_available = new JPanel();
        pn_sec_available.setLayout(new BoxLayout(pn_sec_available, BoxLayout.Y_AXIS));
        // pn_sec_available.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnMain.add(pn_sec_available);

        JLabel lbDSP = new JLabel("Tinh trang phong");
        lbDSP.setAlignmentX(Component.CENTER_ALIGNMENT);
        lbDSP.setFont(fontSize(25));
        pn_sec_available.add(lbDSP);
        pn_sec_available.add(space(10, 10));

        pnPhongTrong = new JPanel();
        JScrollPane scroll = new JScrollPane(pnPhongTrong);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        pn_sec_available.add(scroll);
        // pnPhongTrong.setLayout(new BoxLayout(pnPhongTrong, BoxLayout.X_AXIS));
        
        GridLayout grid_Phong = new GridLayout((So_Phong+7)/8, 8);
        grid_Phong.setHgap(10);
        grid_Phong.setVgap(10);
        pnPhongTrong.setLayout(grid_Phong);
        renderDSPhong();

        return pnMain;
    }

    public void renderDSPhong(){
        for(int i=1; i<=So_Phong; i++){
            JPanel pnPhong = new JPanel();
            pnPhongTrong.add(pnPhong);
            pnPhong.setLayout(new BoxLayout(pnPhong, BoxLayout.Y_AXIS));
            pnPhong.setAlignmentX(CENTER_ALIGNMENT);
            pnPhong.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

            
            JLabel lbMaPhong = new JLabel(String.valueOf(i));
            lbMaPhong.setAlignmentX(Component.CENTER_ALIGNMENT);
            lbMaPhong.setFont(fontSize(25));
            lbMaPhong.setForeground(Color.WHITE);

            JLabel lbLoai = new JLabel("Vip");
            lbLoai.setFont(fontSize(25));
            lbLoai.setAlignmentX(Component.CENTER_ALIGNMENT);
            lbLoai.setForeground(Color.WHITE);
            // lbLoai.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));

            
            
            
            // System.out.println(icon_green_check.getIconWidth());
            JLabel lbIcon;
            if(i % 3 == 0){
                pnPhong.setBackground(Color.red);
                lbIcon = new JLabel(icon_red_close);
            }else{
                pnPhong.setBackground(Color.green);
                lbIcon = new JLabel(icon_green_check);
                
            }
            lbIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
            lbIcon.setFont(fontSize(25));
            lbIcon.setForeground(Color.WHITE);

            pnPhong.add(lbMaPhong);
            pnPhong.add(lbLoai);
            pnPhong.add(space(0, 5));
            pnPhong.add(lbIcon);
            

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

}
