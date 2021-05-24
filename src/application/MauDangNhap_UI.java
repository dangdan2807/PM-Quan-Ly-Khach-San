package application;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class MauDangNhap_UI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JTextField txtUsername;
	public JPasswordField txtPassword;
	public JPanel pnMain;
	public JButton btnLogin;
	public JButton btnCancel;

	public MauDangNhap_UI(){
		this.pnMain = renderGUI();
	}
	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// 	// MauDangNhap_UI dialog = new MauDangNhap_UI();
	// 	// dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	// 	// dialog.setVisible(true);
	// }

	/**
	 * Create the dialog.
	 */
	public JPanel renderGUI() {
		// MauDangNhap_UI dialog = new MauDangNhap_UI();
		// dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		// dialog.setVisible(true);

		setTitle("Đăng nhập");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		{
			pnMain = new JPanel(new FlowLayout(FlowLayout.CENTER));
			// getContentPane().add(panel, BorderLayout.CENTER);
			// pnMain.setAlignmentX(Component.CENTER_ALIGNMENT);
			// pnMain.setAlignmentY(Component.CENTER_ALIGNMENT);
			
			pnMain.setLayout(null);
			{
				JPanel contentPanel = new JPanel();
				contentPanel.setLayout(null);
				contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
				contentPanel.setBounds(0, 0, 434, 183);
				pnMain.add(contentPanel);
				{
					JLabel lblNewLabel = new JLabel("Đăng Nhập");
					lblNewLabel.setForeground(Color.BLUE);
					lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 27));
					lblNewLabel.setBounds(131, 11, 155, 51);
					contentPanel.add(lblNewLabel);
				}
				{
					JLabel lblNewLabel_1 = new JLabel("Tên đăng nhập:");
					lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
					lblNewLabel_1.setBounds(66, 83, 102, 28);
					contentPanel.add(lblNewLabel_1);
				}
				{
					txtUsername = new JTextField();
					txtUsername.setColumns(10);
					txtUsername.setBounds(188, 85, 185, 29);
					contentPanel.add(txtUsername);
				}
				{
					JLabel lblNewLabel_2 = new JLabel("Mật khẩu:");
					lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
					lblNewLabel_2.setBounds(66, 122, 102, 34);
					contentPanel.add(lblNewLabel_2);
				}
				{
					txtPassword = new JPasswordField();
					txtPassword.setColumns(10);
					txtPassword.setBounds(188, 127, 185, 28);
					contentPanel.add(txtPassword);
				}
			}
			{
				JPanel buttonPane = new JPanel();
				buttonPane.setBounds(0, 184, 434, 77);
				pnMain.add(buttonPane);
				buttonPane.setLayout(null);
				{
					btnLogin = new JButton("Đăng nhập");
					btnLogin.setActionCommand("OK");
					btnLogin.setBounds(102, 11, 100, 39);
					buttonPane.add(btnLogin);
				}
				{
					btnCancel = new JButton("Huỷ");
					btnCancel.setActionCommand("Cancel");
					btnCancel.setBounds(245, 11, 100, 39);
					buttonPane.add(btnCancel);
				}
			}
		}
		return pnMain;
	}

}
