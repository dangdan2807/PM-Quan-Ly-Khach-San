package application;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import DAO.ChiTietDVDAO;
import DAO.DichVuDAO;
import DAO.HoaDonDVDAO;
import DAO.KhachHangDAO;
import connectDB.ConnectDB;
import entity.ChiTietDV;
import entity.DichVu;
import entity.HoaDonDV;
import entity.KhachHang;

import javax.swing.border.EtchedBorder;

public class HoaDonDichVu_UI extends JFrame implements ActionListener, MouseListener {
	private DefaultTableModel modelHD, modelDV;
	String[] colsHD = { "Mã hoá đơn", "Mã khách hàng", "Thời gian đặt", "Tổng tiền", "Tình trạng" };
	String[] colsDV = { "Mã dịch vụ", "Tên dịch vụ", "Số lượng", "Đơn giá", "Thời gian đặt", "Mã hoá đơn" };
	public JPanel pnMain;
	private JTable tableHDDV;
	private JTable tableDV;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JTextField txtSoLuong;
	private JButton btnThem, btnXoa, btnXacNhan;
	private JComboBox<String> cboMaKH;
	private JComboBox<String> cboDV;
	private JPanel panel;
	private JPanel panel_1;
	private JTextField txtTimMaHDDV;

	private ArrayList<KhachHang> dsKH;
	private ArrayList<DichVu> dsDV;
	private ArrayList<ChiTietDV> dsChiTietDV;
	private ArrayList<HoaDonDV> dsHDDV;
	ChiTietDVDAO ctDVDAO = new ChiTietDVDAO();
	DichVuDAO dvDAO = new DichVuDAO();
	KhachHangDAO khDAO = new KhachHangDAO();
	HoaDonDVDAO hdDVDAO = new HoaDonDVDAO();
	private JTextField txtGia;
	private JTextField txtTen;
	private KhachHangDAO khachHang_dao;
	private AbstractButton btnSua;
	private JButton btnTimMaHDDV;
	private JButton btnXem;

	public HoaDonDichVu_UI() {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		setTitle("Hoá đơn dịch vụ");
		setSize(1000, 670);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		
	}

	public void start(){
		pnMain = renderGUI();
		loadListHDDV();
		loadCboMaKH();
		loadCboTenDV();

		docDuLieuVaoTableHDDV();
	}

	public JPanel renderGUI(){
		pnMain = new JPanel();
		pnMain.setBounds(0, 0, 584, 411);
		getContentPane().add(pnMain);

		JLabel lbTitle = new JLabel("Hoá Đơn Thanh Toán Dịch Vụ");
		lbTitle.setBounds(335, 11, 348, 30);
		lbTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		pnMain.add(lbTitle);

		modelHD = new DefaultTableModel(colsHD, 0) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int i, int i1) {
				return false;
				// Không cho chỉnh sửa trên table
			}
		};

		modelDV = new DefaultTableModel(colsDV, 0) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int i, int i1) {
				return false;
				// Không cho chỉnh sửa trên table
			}
		};
		pnMain.setLayout(null);

		JPanel pn = new JPanel();
		pn.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"\u0110\u1EB7t d\u1ECBch v\u1EE5", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pn.setBounds(10, 65, 342, 286);
		pnMain.add(pn);
		pn.setLayout(null);

		JLabel lbMaKH = new JLabel("Mã khách hàng:");
		lbMaKH.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbMaKH.setBounds(10, 22, 93, 22);
		pn.add(lbMaKH);

		cboMaKH = new JComboBox<String>();
		cboMaKH.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cboMaKH.setBounds(122, 22, 205, 22);
		cboMaKH.addItem("");
		pn.add(cboMaKH);

		JLabel lbDV = new JLabel("Dịch vụ:");
		lbDV.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbDV.setBounds(10, 88, 93, 22);
		pn.add(lbDV);

		cboDV = new JComboBox<String>();
		cboDV.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cboDV.setBounds(122, 88, 205, 22);
		cboDV.addItem("");
		pn.add(cboDV);

		JLabel lbGia = new JLabel("Đơn giá:");
		lbGia.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbGia.setBounds(10, 154, 93, 22);
		pn.add(lbGia);

		txtGia = new JTextField();
		txtGia.setEditable(false);
		txtGia.setBounds(122, 154, 205, 22);
		pn.add(txtGia);
		txtGia.setColumns(10);

		JLabel lbSoLuong = new JLabel("Số lượng:");
		lbSoLuong.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbSoLuong.setBounds(10, 121, 93, 22);
		pn.add(lbSoLuong);

		txtSoLuong = new JTextField();
		txtSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtSoLuong.setBounds(122, 121, 205, 22);
		pn.add(txtSoLuong);
		txtSoLuong.setColumns(10);

		btnXoa = new JButton("Xoá");
		btnXoa.setIcon(new ImageIcon("data\\images\\trash2_16.png"));
		btnXoa.setBounds(122, 198, 99, 33);
		pn.add(btnXoa);

		btnThem = new JButton("Thêm");
		btnThem.setIcon(new ImageIcon("data\\images\\blueAdd_16.png"));
		btnThem.setBounds(10, 198, 93, 33);
		pn.add(btnThem);

		btnSua = new JButton("Sửa");
		btnSua.setIcon(new ImageIcon("data\\images\\edit2_16.png"));
		btnSua.setBounds(238, 198, 89, 33);
		pn.add(btnSua);

		btnXacNhan = new JButton("Xác nhận");
		btnXacNhan.setBounds(103, 242, 137, 33);
		pn.add(btnXacNhan);
		btnXacNhan.setIcon(new ImageIcon("data\\images\\check.png"));

		JLabel lbTen = new JLabel("Tên khách hàng");
		lbTen.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbTen.setBounds(10, 55, 93, 22);
		pn.add(lbTen);

		txtTen = new JTextField();
		txtTen.setEditable(false);
		txtTen.setColumns(10);
		txtTen.setBounds(122, 55, 205, 22);
		pn.add(txtTen);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"D\u1ECBch v\u1EE5 kh\u00E1ch h\u00E0ng \u0111\u00E3 \u0111\u1EB7t", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(362, 64, 614, 251);
		pnMain.add(panel);
		panel.setLayout(null);

		tableDV = new JTable(modelDV);
		JScrollPane scDV = new JScrollPane(tableDV, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scDV.setBounds(10, 29, 594, 211);
		tableDV.getColumnModel().getColumn(1).setPreferredWidth(105);
		panel.add(scDV);

		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Danh s\u00E1ch ho\u00E1 \u0111\u01A1n d\u1ECBch v\u1EE5", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panel_1.setBounds(362, 326, 614, 307);
		pnMain.add(panel_1);
		panel_1.setLayout(null);

		tableHDDV = new JTable(modelHD);
		JScrollPane scHD = new JScrollPane(tableHDDV, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scHD.setBounds(10, 57, 594, 239);
		panel_1.add(scHD);

		JLabel lbTimMaHDDV = new JLabel("Mã hoá đơn dịch vụ:");
		lbTimMaHDDV.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbTimMaHDDV.setBounds(10, 32, 120, 14);
		panel_1.add(lbTimMaHDDV);

		txtTimMaHDDV = new JTextField();
		txtTimMaHDDV.setBounds(140, 29, 120, 20);
		panel_1.add(txtTimMaHDDV);
		txtTimMaHDDV.setColumns(10);

		btnTimMaHDDV = new JButton("Tìm");
		btnTimMaHDDV.setIcon(new ImageIcon("data\\images\\search_16.png"));
		btnTimMaHDDV.setBounds(270, 28, 89, 23);
		panel_1.add(btnTimMaHDDV);
		
		btnXem = new JButton("Xem tất cả");
		btnXem.setIcon(null);
		btnXem.setBounds(366, 28, 106, 23);
		panel_1.add(btnXem);

		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXacNhan.addActionListener(this);// khi bam xac nhan se xoa cac dong` trong table dv;
		btnTimMaHDDV.addActionListener(this);
		btnXem.addActionListener(this);
		cboDV.addActionListener(this);
		cboMaKH.addActionListener(this);
		tableHDDV.addMouseListener(this);
		tableDV.addMouseListener(this);
		pnMain.addMouseListener(this);

		return pnMain;
		
	}

	public static void main(String[] args) {
		new HoaDonDichVu_UI().setVisible(true);
	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(tableDV)) {
			int row1 = tableDV.getSelectedRow();
			int row2 = tableHDDV.getSelectedRow();
			cboMaKH.setSelectedItem(modelHD.getValueAt(row2, 1).toString());
			cboDV.setSelectedItem(modelDV.getValueAt(row1, 1).toString());
			txtSoLuong.setText(modelDV.getValueAt(row1, 2).toString());
			txtGia.setText(modelDV.getValueAt(row1, 3).toString());

		} else if (o.equals(tableHDDV)) {
			int row = tableHDDV.getSelectedRow();
			int maHD = Integer.parseInt(modelHD.getValueAt(row, 0).toString());
			dsChiTietDV = ctDVDAO.getChiTietDVByMaHDDV(maHD);
			docDuLieuVaoTableDV();
		}else if(o.equals(pnMain)) {
			modelDV.getDataVector().removeAllElements();
			modelDV.fireTableDataChanged();
		}

	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {

	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {

	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {

	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		long millis = System.currentTimeMillis();
		Date date = new Date(millis);
		if (o.equals(btnThem)) {
			if (validDataSo()) {
				int row = modelDV.getRowCount();
				for (int i = row - 1; i >= 0; i--) {
					modelDV.removeRow(i);
				}
				ChiTietDV ctdv = null;
				ctdv = getDataIntoFormDV();
				try {
					boolean result = ctDVDAO.create(ctdv);
					if (result) {
						int maDV = 0;
						String tenDV = ctdv.getDichVu().getTenDV();
						for (DichVu item : dsDV) {
							if (item.getTenDV().equals(tenDV)) {
								maDV = item.getMaDV();
								break;
							}
						}
						modelDV.addRow(new Object[] { maDV, tenDV, ctdv.getSoLuong(), ctdv.getDichVu().getDonGia(),
								date, "" });
						JOptionPane.showMessageDialog(this, "Đã thêm dịch vụ");
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(this, "Lỗi! Thêm thất bại");
				}
			}

		} else if (o.equals(btnXoa)) {
			ChiTietDV ctDV = null;
			ctDV = getDataIntoFormDV();

			int row = tableDV.getSelectedRow();
			try {
				if (row == -1)
					JOptionPane.showMessageDialog(this, "Chưa chọn dịch vụ cần xoá");
				else {
					int select;
					select = JOptionPane.showConfirmDialog(this, "Bạn có muốn xoá dòng đã chọn ?", "Cảnh báo",
							JOptionPane.YES_NO_OPTION);
					if (select == JOptionPane.YES_OPTION) {
						ctDVDAO.delete(ctDV.getDichVu().getMaDV());
						modelDV.removeRow(row);
						JOptionPane.showMessageDialog(this, "Xoá thành công");
					}
				}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, "Xoá thất bại");
			}
		} else if (o.equals(btnXacNhan)) {
			HoaDonDV hd = null;
			hd = getDataIntoFormHDDV();
			int row = modelDV.getRowCount();
			for (int i = row - 1; i >= 0; i--) {
				modelDV.removeRow(i);
			}

			try {
				if (hdDVDAO.insert(hd)) {
					hd.setMaHDDV(hdDVDAO.getLatestID());
					ctDVDAO.updateByID(hdDVDAO.getLatestID());

				}
				String tt = convertTinhTrang(hd.getTinhTrang());
				modelHD.addRow(

						new Object[] { hd.getMaHDDV(), hd.getKhachHang().getMaKH(), hd.getNgayGioDat(), hd.tinhTong(),
								tt });
				JOptionPane.showMessageDialog(this, "Đã thêm hoá đơn");

			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, "Lỗi! Thêm thất bại");
			}
		} else if (o.equals(btnSua)) {
			if (validDataSo()) {
				ChiTietDV ctdv = null;
				ctdv = getDataIntoFormDV();

				HoaDonDV hd = null;
				hd = getDataIntoFormHDDV();

				ctdv.setHoaDonDV(hd);
				int row = tableDV.getSelectedRow();

				try {
					boolean result = ctDVDAO.update(ctdv);
					if (result) {
						int maDV = 0;
						String tenDV = ctdv.getDichVu().getTenDV();
						for (DichVu item : dsDV) {
							if (item.getTenDV().contains(tenDV)) {
								maDV = item.getMaDV();
								break;
							}
						}
						modelDV.setValueAt(maDV, row, 0);
						modelDV.setValueAt(tenDV, row, 1);
						modelDV.setValueAt(ctdv.getSoLuong(), row, 2);
						modelDV.setValueAt(ctdv.getDichVu().getDonGia(), row, 3);
						modelDV.setValueAt(ctdv.getNgayGioDat(), row, 4);
						JOptionPane.showMessageDialog(this, "Cập nhật thành công");
					} else
						JOptionPane.showMessageDialog(this, "Cập nhật thất bại");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		} else if (o.equals(btnTimMaHDDV)) {
			int maHD = Integer.parseInt(txtTimMaHDDV.getText().trim());
			modelHD.getDataVector().removeAllElements();
			modelHD.fireTableDataChanged();
			dsHDDV = hdDVDAO.getListHDDVbyMaHD(maHD);
			if(dsHDDV.size()<=0)
				JOptionPane.showMessageDialog(this, "Không tìm thấy hoá đơn");
			docDuLieuVaoTableHDDV();
			
		}else if(o.equals(btnXem)) {
			modelHD.getDataVector().removeAllElements();
			modelHD.fireTableDataChanged();
			dsHDDV = hdDVDAO.getAllHDDV();
			docDuLieuVaoTableHDDV();
		}
		else if (o.equals(cboDV))

		{
			int indx = cboDV.getSelectedIndex() - 1;
			if (indx == -1) {
				txtGia.setText("");
			} else {
				String gia = String.valueOf(dsDV.get(indx).getDonGia());
				txtGia.setText(gia);
			}
		} else if (o.equals(cboMaKH)) {
			int indx = cboMaKH.getSelectedIndex() - 1;
			if (indx == -1) {
				txtTen.setText("");
			} else {
				String ten = String.valueOf(dsKH.get(indx).getTenKH());
				txtTen.setText(ten);
			}
		}
	}

	public ChiTietDV getDataIntoFormDV() {

		int indx = cboDV.getSelectedIndex() - 1;

		int maDV = dsDV.get(indx).getMaDV();
		String tenDV = String.valueOf(cboDV.getSelectedItem());
		int soLuong = Integer.parseInt(txtSoLuong.getText().trim());
		double gia = dsDV.get(indx).getDonGia();
		DichVu dv = new DichVu(maDV, tenDV, gia);
		long millis = System.currentTimeMillis();
		Date date = new Date(millis);

		HoaDonDV hd = new HoaDonDV(0, date, null);

		ChiTietDV ctDV = new ChiTietDV(soLuong, date, hd, dv);
		return ctDV;
	}

	public HoaDonDV getDataIntoFormHDDV() {
		long millis = System.currentTimeMillis();
		Date date = new Date(millis);
		KhachHang khachHang = new KhachHang(0, "", "", date, "", 0);
		int maKH = Integer.parseInt((String) cboMaKH.getSelectedItem());
		khachHang.setMaKH(maKH);
		int tt = 0;
		HoaDonDV hd = new HoaDonDV(0, khachHang, date, tt);
		hd.setMaHDDV(hdDVDAO.getLatestID());
		return hd;
	}

	private void docDuLieuVaoTableDV() {
		// phai co su kien click vao table HDDV
		modelDV.setRowCount(0);
		for (ChiTietDV item : dsChiTietDV) {
			int maDV = item.getDichVu().getMaDV();
			String tenDV = item.getDichVu().getTenDV();
			int soLuong = item.getSoLuong();
			double donGia = item.getDichVu().getDonGia();
			Date ngayDat = item.getNgayGioDat();
			int maHD = item.getHoaDonDV().getMaHDDV();
			modelDV.addRow(new Object[] { maDV, tenDV, soLuong, donGia, ngayDat, maHD });
		}
	}

	private void docDuLieuVaoTableHDDV() {
		for (HoaDonDV item : dsHDDV) {
			String tt = convertTinhTrang(item.getTinhTrang());
			double tongTien = item.tinhTong();
			modelHD.addRow(new Object[] { item.getMaHDDV(), item.getKhachHang().getMaKH(), item.getNgayGioDat(),
					tongTien, tt });
		}

	}

	private void loadCboMaKH() {
		dsKH = khDAO.getListKhachHang();
		for (KhachHang kh : dsKH) {
			cboMaKH.addItem(String.valueOf(kh.getMaKH()));
		}
	}

	private void loadCboTenDV() {
		dsDV = dvDAO.getListDichVu();
		for (DichVu dv : dsDV)
			cboDV.addItem(dv.getTenDV());
	}

	private String convertTinhTrang(int tinhTrang) {
		String result = "";
		if (tinhTrang == 0)
			result = "Chưa thanh toán";
		else if (tinhTrang == 1)
			result = "Đã thanh toán";
		return result;
	}

	private void loadListHDDV() {
		dsHDDV = hdDVDAO.getAllHDDV();
	}

	boolean validDataSo() {
		String soLuong = txtSoLuong.getText().trim();
		if (soLuong.length() > 0) {
			try {
				Integer x = Integer.parseInt(soLuong);
				if (x <= 0) {
					JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0");
					return false;
				}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, "Số lượng phải là số!");
				return false;
			}
		}
		return true;
	}
}
