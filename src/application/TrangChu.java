package application;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class TrangChu extends JFrame {

    public static JPanel renderGUI() {
        // nội dung page trang chủ ở đây
        JPanel pMain = new JPanel();
        JLabel lbl = new JLabel("trang chu");
        pMain.add(lbl);
        return pMain;

    }

}
