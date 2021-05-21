package application;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.w3c.dom.events.MouseEvent;
import javax.swing.border.EtchedBorder;

public class HoaDonDichVu_UI extends JFrame implements ActionListener, MouseListener{
	private DefaultTableModel model1;
	String[] cols1 = { "Mã hoá đơn dịch vụ", "Mã khách hàng", "Thời gian đặt"};
	String[] cols2 = { "Mã dịch vụ", "Tên dịch vụ", "Đơn giá","Số lượng","Tổng tiền"};
	public JPanel pnMain;
	private JTable tableHDDV;
	private TableModel model2;
	private JTable tableDV;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JTextField txtSoLuong;
	private JButton btnThem,btnXoa,btnXacNhan;
	private JComboBox cboMaKH;
	private JLabel lbShowGia;
	private JComboBox cboDV;
	private JPanel panel;
	private JPanel panel_1;
	private JTextField txtTimTenDV;
	private JTextField txtTimMaHDDV;

	public HoaDonDichVu_UI() {
		setTitle("Hoá đơn dịch vụ");
		setSize(1000,670);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		
		pnMain = new JPanel();
		pnMain.setBounds(0, 0, 584, 411);
		getContentPane().add(pnMain);
		
		JLabel lbTitle = new JLabel("Hoá đơn thanh toán dịch vụ");
		lbTitle.setBounds(335, 11, 348, 30);
		lbTitle.setForeground(Color.BLUE);
		lbTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
		pnMain.add(lbTitle);
		
		model1 = new DefaultTableModel(cols1, 0) {
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
		
		model2 = new DefaultTableModel(cols2, 0) {
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
		pnMain.setLayout(null);
		
		JPanel pn = new JPanel();
		pn.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "\u0110\u1EB7t d\u1ECBch v\u1EE5", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pn.setBounds(10, 65, 342, 251);
		pnMain.add(pn);
		pn.setLayout(null);
		
		JLabel lbMaKH = new JLabel("Mã khách hàng:");
		lbMaKH.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbMaKH.setBounds(10, 25, 93, 22);
		pn.add(lbMaKH);
		
		cboMaKH = new JComboBox();
		cboMaKH.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cboMaKH.setBounds(122, 25, 205, 22);
		pn.add(cboMaKH);
		
		JLabel lbDV = new JLabel("Dịch vụ:");
		lbDV.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbDV.setBounds(10, 58, 93, 22);
		pn.add(lbDV);
		
		cboDV = new JComboBox();
		cboDV.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cboDV.setBounds(122, 58, 205, 22);
		pn.add(cboDV);
		
		JLabel lbGia = new JLabel("Đơn giá:");
		lbGia.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbGia.setBounds(10, 124, 93, 22);
		pn.add(lbGia);
		
		lbShowGia = new JLabel("");
		lbShowGia.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbShowGia.setBounds(122, 124, 195, 22);
		pn.add(lbShowGia);
		
		
		JLabel lbSoLuong = new JLabel("Số lượng:");
		lbSoLuong.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbSoLuong.setBounds(10, 91, 93, 22);
		pn.add(lbSoLuong);
		
		txtSoLuong = new JTextField();
		txtSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtSoLuong.setBounds(122, 91, 205, 22);
		pn.add(txtSoLuong);
		txtSoLuong.setColumns(10);
		
		btnXoa = new JButton("Xoá");
		btnXoa.setIcon(new ImageIcon("data\\images\\trash2_16.png"));
		btnXoa.setBounds(111, 175, 95, 33);
		pn.add(btnXoa);
		
		btnThem = new JButton("Thêm");
		btnThem.setIcon(new ImageIcon("data\\images\\blueAdd_16.png"));
		btnThem.setBounds(10, 175, 93, 33);
		pn.add(btnThem);
		
		btnXacNhan = new JButton("Xác nhận");
		btnXacNhan.setIcon(new ImageIcon("data\\images\\check.png"));
		btnXacNhan.setBounds(216, 175, 111, 33);
		pn.add(btnXacNhan);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "D\u1ECBch v\u1EE5 kh\u00E1ch h\u00E0ng \u0111\u00E3 \u0111\u1EB7t", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(362, 64, 614, 251);
		pnMain.add(panel);
		panel.setLayout(null);
		
		tableDV = new JTable(model2);
		JScrollPane scrollPane2 = new JScrollPane(tableDV, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane2.setBounds(10, 54, 594, 186);
		panel.add(scrollPane2);
		
		JLabel lbTimTenDV = new JLabel("Tên dịch vụ: ");
		lbTimTenDV.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbTimTenDV.setBounds(10, 29, 78, 14);
		panel.add(lbTimTenDV);
		
		txtTimTenDV = new JTextField();
		txtTimTenDV.setBounds(98, 26, 125, 20);
		panel.add(txtTimTenDV);
		txtTimTenDV.setColumns(10);
		
		JButton btnTimTenDV = new JButton("Tìm");
		btnTimTenDV.setIcon(new ImageIcon("data\\images\\search_16.png"));
		btnTimTenDV.setBounds(233, 25, 89, 23);
		panel.add(btnTimTenDV);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Danh s\u00E1ch ho\u00E1 \u0111\u01A1n d\u1ECBch v\u1EE5", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(362, 326, 614, 307);
		pnMain.add(panel_1);
		panel_1.setLayout(null);
		
		tableHDDV = new JTable(model1);
		JScrollPane scrollPane1 = new JScrollPane(tableHDDV, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane1.setBounds(10, 57, 594, 239);
		panel_1.add(scrollPane1);
		
		JLabel lbTimMaHDDV = new JLabel("Mã hoá đơn dịch vụ:");
		lbTimMaHDDV.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbTimMaHDDV.setBounds(10, 32, 120, 14);
		panel_1.add(lbTimMaHDDV);
		
		txtTimMaHDDV = new JTextField();
		txtTimMaHDDV.setBounds(140, 29, 120, 20);
		panel_1.add(txtTimMaHDDV);
		txtTimMaHDDV.setColumns(10);
		
		JButton btnTimMaHDDV = new JButton("Tìm");
		btnTimMaHDDV.setIcon(new ImageIcon("data\\images\\search_16.png"));
		btnTimMaHDDV.setBounds(270, 28, 89, 23);
		panel_1.add(btnTimMaHDDV);
		
		
		
	}

	public static void main(String[] args) {
		new HoaDonDichVu_UI().setVisible(true);
	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
