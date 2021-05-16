// Author : Pham Dang Dan
// Date   : April 23, 2021

package application;

import javax.swing.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;

public class kDatePicker extends JPanel implements ActionListener {
    private JTextField txt;
    private JButton btn;
    private int widthDefault = 150;
    DialogDatePicker f = new DialogDatePicker();
    ImageIcon calenderIcon = new ImageIcon("data/images/calender_16.png");

    public kDatePicker() {
        setLayout(null);

        createGUI();
    }

    public kDatePicker(int width) {
        setLayout(null);
        setBounds(0, 0, width, 20);
        widthDefault = width;
        createGUI();
    }

    private void createGUI() {
        txt = new JTextField();
        txt.setBounds(0, 0, widthDefault - 30, 20);
        txt.setEditable(false);
        txt.setText(DialogDatePicker.getToDay());

        btn = new JButton(calenderIcon);
        btn.setBounds(widthDefault - 30, 0, 29, 20);

        this.add(txt);
        this.add(btn);
        btn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btn)) {
            f.setModal(true);
            f.setVisible(true);
            String date = f.getPickedDate();
            txt.setText(date);
            if (txt.getText().equals("")) {
                txt.setText(DialogDatePicker.getToDay());
            }
        }
    }

    public String getValue() {
        return txt.getText();
    }

    public Date getValueDate() {
        return f.getDate();
    }

    public int getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        String date = sdf.format(txt.getText());
        return Integer.parseInt(date);
    }

    public int getMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        String date = sdf.format(txt.getText());
        return Integer.parseInt(date);
    }

    public int getYear() {
        SimpleDateFormat sdf = new SimpleDateFormat("yy");
        String date = sdf.format(txt.getText());
        return Integer.parseInt(date);
    }
}
