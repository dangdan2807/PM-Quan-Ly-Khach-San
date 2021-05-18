package application;

import javax.swing.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;

public class testChonNgay extends JFrame implements ActionListener {

    private JTextField txt;
    private JButton btn;

    public testChonNgay() {
        setTitle("Test chọn ngày");
        setSize(270, 200);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel pnMain = new JPanel();
        pnMain.setBounds(0, 0, 300, 300);
        pnMain.setLayout(null);

        // txt = new JTextField();
        // txt.setText(DialogDatePicker.getToDay());
        // btn = new JButton("...");
        // btn.setBounds(212, 10, 30, 20);
        kDatePicker txt = new kDatePicker(190);
        txt.setBounds(10, 10, 190, 20);

        pnMain.add(txt);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println(sdf.format(txt.getValue()));
        // pnMain.add(btn);

        getContentPane().add(pnMain);
        
        // txt.addActionListener(this);
        // btn.addActionListener(this);
        System.out.println(txt.getValue());
    }

    public static void main(String[] args) {
        new testChonNgay().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btn)) {
            DialogDatePicker f = new DialogDatePicker();
            f.setModal(true);
            f.setVisible(true);
            String date = f.getPickedDate();
            txt.setText(date);
        }
    }
}
