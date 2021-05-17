package application;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MauDangNhap_UI extends JDialog {
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MauDangNhap_UI dialog = new MauDangNhap_UI();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MauDangNhap_UI() {
		setTitle("Đăng nhập");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			{
				JPanel contentPanel = new JPanel();
				contentPanel.setLayout(null);
				contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
				contentPanel.setBounds(0, 0, 434, 183);
				panel.add(contentPanel);
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
					textField = new JTextField();
					textField.setColumns(10);
					textField.setBounds(188, 85, 185, 29);
					contentPanel.add(textField);
				}
				{
					JLabel lblNewLabel_2 = new JLabel("Mật khẩu:");
					lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
					lblNewLabel_2.setBounds(66, 122, 102, 34);
					contentPanel.add(lblNewLabel_2);
				}
				{
					textField_1 = new JTextField();
					textField_1.setColumns(10);
					textField_1.setBounds(188, 127, 185, 28);
					contentPanel.add(textField_1);
				}
			}
			{
				JPanel buttonPane = new JPanel();
				buttonPane.setBounds(0, 184, 434, 77);
				panel.add(buttonPane);
				buttonPane.setLayout(null);
				{
					JButton okButton = new JButton("Đăng nhập");
					okButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
						}
					});
					okButton.setActionCommand("OK");
					okButton.setBounds(102, 11, 100, 39);
					buttonPane.add(okButton);
				}
				{
					JButton cancelButton = new JButton("Huỷ");
					cancelButton.setActionCommand("Cancel");
					cancelButton.setBounds(245, 11, 100, 39);
					buttonPane.add(cancelButton);
				}
			}
		}
	}

}
