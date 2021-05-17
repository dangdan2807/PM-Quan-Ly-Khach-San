package application;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class HoaDonDichVu extends JFrame {
	private JTextField txtMa;
	private JTable table;
	private DefaultTableModel model;
	String[] cols = { "Mã dịch vụ", "Tên dịch vụ", "Đơn giá"};

	public HoaDonDichVu() {
		setTitle("Hoá đơn dịch vụ");
		setSize(600, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		getContentPane().setLayout(null);
		
		JPanel pnMain = new JPanel();
		pnMain.setBounds(0, 0, 584, 411);
		getContentPane().add(pnMain);
		pnMain.setLayout(null);
		
		JLabel lbTitle = new JLabel("Hoá đơn thanh toán dịch vụ");
		lbTitle.setBounds(191, 11, 229, 14);
		lbTitle.setForeground(Color.BLUE);
		lbTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		pnMain.add(lbTitle);
		
		JLabel lbMaHdDv = new JLabel("Mã hoá đơn dịch vụ:");
		lbMaHdDv.setBounds(10, 52, 120, 14);
		pnMain.add(lbMaHdDv);
		
		txtMa = new JTextField();
		txtMa.setBounds(140, 49, 80, 20);
		pnMain.add(txtMa);
		txtMa.setColumns(10);
		
		JButton btnTim = new JButton("Tìm");
		btnTim.setBounds(230, 48, 65, 23);
		pnMain.add(btnTim);
		
		JLabel lbMaKH = new JLabel("Mã khách hàng:");
		lbMaKH.setBounds(10, 102, 94, 14);
		pnMain.add(lbMaKH);
		
		JLabel lbMaDV = new JLabel("Mã dịch vụ:");
		lbMaDV.setBounds(10, 134, 96, 14);
		pnMain.add(lbMaDV);
		
		JLabel lblNewLabel = new JLabel("Thông tin khách hàng");
		lblNewLabel.setBounds(333, 52, 105, 14);
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 11));
		pnMain.add(lblNewLabel);
		
		JLabel lbTen = new JLabel("Tên khách:");
		lbTen.setBounds(333, 87, 87, 14);
		pnMain.add(lbTen);
		
		JLabel lbNs = new JLabel("Ngày sinh:");
		lbNs.setBounds(333, 112, 87, 14);
		pnMain.add(lbNs);
		
		JLabel lbShowMaKH = new JLabel("");
		lbShowMaKH.setBounds(116, 102, 86, 14);
		pnMain.add(lbShowMaKH);
		
		JLabel lbShowMaDV = new JLabel("");
		lbShowMaDV.setBounds(116, 134, 86, 14);
		pnMain.add(lbShowMaDV);
		
		JLabel lbShowTen = new JLabel("");
		lbShowTen.setBounds(433, 87, 129, 14);
		pnMain.add(lbShowTen);
		
		JLabel lbShowNs = new JLabel("");
		lbShowNs.setBounds(433, 112, 129, 14);
		pnMain.add(lbShowNs);
		
		JLabel lbSDT = new JLabel("SĐT:");
		lbSDT.setBounds(333, 134, 80, 14);
		pnMain.add(lbSDT);
		
		JLabel lbCMND = new JLabel("CMND:");
		lbCMND.setBounds(333, 159, 74, 14);
		pnMain.add(lbCMND);
		
		JLabel lbShowSDT = new JLabel("");
		lbShowSDT.setBounds(433, 134, 129, 14);
		pnMain.add(lbShowSDT);
		
		JLabel lbShowCMND = new JLabel("");
		lbShowCMND.setBounds(433, 159, 129, 14);
		pnMain.add(lbShowCMND);
		
		JLabel lbShowLoaiKhach = new JLabel("");
		lbShowLoaiKhach.setBounds(433, 184, 129, 14);
		pnMain.add(lbShowLoaiKhach);
		
		JLabel lbLoaiKhach = new JLabel("Loại khách:");
		lbLoaiKhach.setBounds(333, 184, 87, 14);
		pnMain.add(lbLoaiKhach);
		

		
		JPanel panel = new JPanel();
		panel.setBounds(305, 209, 257, 157);
		pnMain.add(panel);
		panel.setLayout(null);
		
		JPanel pnTien = new JPanel();
		pnTien.setBorder(new TitledBorder(new LineBorder(new Color(0, 255, 255)), "Tổng tiền", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		pnTien.setBounds(10, 11, 237, 78);
		panel.add(pnTien);
		pnTien.setLayout(null);
		
		JLabel lbTien = new JLabel("Số tiền:");
		lbTien.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbTien.setBounds(10, 36, 63, 14);
		pnTien.add(lbTien);
		
		JLabel lbShowTien = new JLabel("100000usd");
		lbShowTien.setBounds(79, 38, 128, 14);
		pnTien.add(lbShowTien);
		
		JPanel pnButton = new JPanel();
		pnButton.setBorder(new LineBorder(Color.CYAN));
		pnButton.setBounds(10, 100, 237, 46);
		panel.add(pnButton);
		pnButton.setLayout(null);
		
		JButton btnThanhToan = new JButton("Thanh toán");
		btnThanhToan.setBounds(11, 11, 101, 23);
		pnButton.add(btnThanhToan);
		
		JButton btnIn = new JButton("In hoá đơn");
		btnIn.setBounds(122, 11, 105, 23);
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
		scrollPane.setBounds(10, 184, 285, 216);
		
		
		pnMain.add(scrollPane);
		
		
	}

	public static void main(String[] args) {
		new HoaDonDichVu().setVisible(true);
	}
}
