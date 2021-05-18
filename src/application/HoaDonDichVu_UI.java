package application;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class HoaDonDichVu_UI extends JFrame {
	private JTable table;
	private DefaultTableModel model;
	String[] cols = { "Mã dịch vụ", "Tên dịch vụ", "Đơn giá"};
	public JPanel pnMain;
	private JTextField textField;

	public HoaDonDichVu_UI() {
		setTitle("Hoá đơn dịch vụ");
		setSize(1000,700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		
		pnMain = new JPanel();
		pnMain.setBounds(0, 0, 584, 411);
		pnMain.setLayout(null);
		getContentPane().add(pnMain);
		
		JLabel lbTitle = new JLabel("Hoá đơn thanh toán dịch vụ");
		lbTitle.setBounds(377, 11, 348, 30);
		lbTitle.setForeground(Color.BLUE);
		lbTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
		pnMain.add(lbTitle);
		
		JLabel lblNewLabel = new JLabel("Thông tin khách hàng");
		lblNewLabel.setBounds(548, 101, 229, 24);
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 16));
		pnMain.add(lblNewLabel);
		

		
		JPanel panel = new JPanel();
		panel.setBounds(548, 411, 404, 218);
		pnMain.add(panel);
		panel.setLayout(null);
		
		JPanel pnTien = new JPanel();
		pnTien.setBorder(new TitledBorder(new LineBorder(new Color(0, 255, 255)), "Tổng tiền", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		pnTien.setBounds(10, 11, 384, 117);
		panel.add(pnTien);
		pnTien.setLayout(null);
		
		JLabel lbTien = new JLabel("Số tiền:");
		lbTien.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbTien.setBounds(30, 41, 117, 40);
		pnTien.add(lbTien);
		
		JLabel lbShowTien = new JLabel("100000usd");
		lbShowTien.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbShowTien.setBounds(173, 41, 159, 40);
		pnTien.add(lbShowTien);
		
		JPanel pnButton = new JPanel();
		pnButton.setBorder(new LineBorder(Color.CYAN));
		pnButton.setBounds(10, 149, 384, 58);
		panel.add(pnButton);
		pnButton.setLayout(null);
		
		JButton btnThanhToan = new JButton("Thanh toán");
		btnThanhToan.setIcon(new ImageIcon("data\\images\\purse.png"));
		btnThanhToan.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnThanhToan.setBounds(10, 11, 168, 36);
		pnButton.add(btnThanhToan);
		
		JButton btnIn = new JButton("In hoá đơn");
		btnIn.setIcon(new ImageIcon("data\\images\\printer.png"));
		btnIn.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnIn.setBounds(208, 11, 166, 36);
		pnButton.add(btnIn);
		
		model = new DefaultTableModel(cols, 0) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int i, int i1) {
				return false;
				//Không cho chỉnh sửa trên table
			}
		};
		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(10, 316, 507, 313);
		
		
		pnMain.add(scrollPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 87, 507, 218);
		pnMain.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lbMaHdDv = new JLabel("Mã hoá đơn dịch vụ:");
		lbMaHdDv.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbMaHdDv.setBounds(10, 11, 171, 33);
		panel_1.add(lbMaHdDv);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(191, 13, 198, 33);
		panel_1.add(textField);
		
		JButton btnTim = new JButton("");
		btnTim.setIcon(new ImageIcon("T:\\TAILIEU\\nam2\\HK2\\THUCHANH\\PM-Quan-Ly-Khach-San\\PM-Quan-Ly-Khach-San-main\\data\\images\\magnifying-glass.png"));
		btnTim.setBounds(411, 12, 86, 35);
		panel_1.add(btnTim);
		
		JLabel lbMaKH = new JLabel("Mã khách hàng:");
		lbMaKH.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbMaKH.setBounds(10, 91, 171, 29);
		panel_1.add(lbMaKH);
		
		JLabel lbMaDV = new JLabel("Mã dịch vụ:");
		lbMaDV.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbMaDV.setBounds(10, 131, 171, 29);
		panel_1.add(lbMaDV);
		
		JLabel lbShowMaKH = new JLabel("");
		lbShowMaKH.setBounds(191, 91, 186, 29);
		panel_1.add(lbShowMaKH);
		
		JLabel lbShowMaDV = new JLabel("");
		lbShowMaDV.setBounds(191, 131, 186, 29);
		panel_1.add(lbShowMaDV);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(548, 136, 421, 246);
		pnMain.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lbTen = new JLabel("Tên khách:");
		lbTen.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lbTen.setBounds(10, 26, 87, 32);
		panel_2.add(lbTen);
		
		JLabel lbNs = new JLabel("Ngày sinh:");
		lbNs.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lbNs.setBounds(10, 69, 87, 32);
		panel_2.add(lbNs);
		
		JLabel lbSDT = new JLabel("SĐT:");
		lbSDT.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lbSDT.setBounds(10, 112, 80, 32);
		panel_2.add(lbSDT);
		
		JLabel lbCMND = new JLabel("CMND:");
		lbCMND.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lbCMND.setBounds(10, 155, 74, 32);
		panel_2.add(lbCMND);
		
		JLabel lbLoaiKhach = new JLabel("Loại khách:");
		lbLoaiKhach.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lbLoaiKhach.setBounds(10, 198, 87, 32);
		panel_2.add(lbLoaiKhach);
		
		JLabel lbShowTen = new JLabel("");
		lbShowTen.setBounds(146, 26, 248, 32);
		panel_2.add(lbShowTen);
		
		JLabel lbShowNs = new JLabel("");
		lbShowNs.setBounds(146, 69, 248, 32);
		panel_2.add(lbShowNs);
		
		JLabel lbShowSDT = new JLabel("");
		lbShowSDT.setBounds(146, 112, 248, 32);
		panel_2.add(lbShowSDT);
		
		JLabel lbShowCMND = new JLabel("");
		lbShowCMND.setBounds(146, 155, 248, 32);
		panel_2.add(lbShowCMND);
		
		JLabel lbShowLoaiKhach = new JLabel("");
		lbShowLoaiKhach.setBounds(146, 202, 248, 28);
		panel_2.add(lbShowLoaiKhach);
		
		
	}

	public static void main(String[] args) {
		new HoaDonDichVu_UI().setVisible(true);
	}
}
