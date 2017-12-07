package ca.mcgill.ecse321.urlms.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse321.urlms.controller.FundingController;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditFundingAccountPO extends JFrame {

	private JPanel contentPane;
	private JTextField txtNewAccountType;
	private JTextField txtTargetAccountID;
	public static FundingController controller = new FundingController();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditFundingAccountPO frame = new EditFundingAccountPO();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EditFundingAccountPO() {
		setTitle("Edit Funding Account");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		txtNewAccountType = new JTextField();
		txtNewAccountType.setText("New Account Type");
		txtNewAccountType.setColumns(10);
		
		txtTargetAccountID = new JTextField();
		txtTargetAccountID.setText("Target Account Type Name");
		txtTargetAccountID.setColumns(10);
		
		JButton btnEditType = new JButton("Edit Type");
		btnEditType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String targetAccount = txtTargetAccountID.getText();
				String newType = txtNewAccountType.getText();
				
				controller.editFinancialAccount(targetAccount, newType);
			}
		});
		
		JButton btnDeleteAccount = new JButton("Delete Account");
		btnDeleteAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String index = txtTargetAccountID.getText();
				
				controller.removeFundingAccount(index);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(91)
					.addComponent(btnEditType)
					.addPreferredGap(ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
					.addComponent(btnDeleteAccount)
					.addGap(62))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(123, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(txtNewAccountType, Alignment.TRAILING)
						.addComponent(txtTargetAccountID, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE))
					.addGap(110))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(30)
					.addComponent(txtTargetAccountID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addComponent(txtNewAccountType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(49)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEditType)
						.addComponent(btnDeleteAccount))
					.addContainerGap(82, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

}
