package ca.mcgill.ecse321.urlms.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse321.urlms.application.URLMSApplication;
import ca.mcgill.ecse321.urlms.controller.Controller;
import ca.mcgill.ecse321.urlms.controller.FundingController;
import ca.mcgill.ecse321.urlms.controller.StaffController;
import ca.mcgill.ecse321.urlms.model.Expense;
import ca.mcgill.ecse321.urlms.model.StaffMember;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

public class TransactionPagePO extends JFrame {
	JLabel expenseListLabel;
	JLabel feelFreeLabel;
	private JPanel contentPane;
	JLabel welcomeToStaffLabel;
	public AddStaffMemberPO asmpo = new AddStaffMemberPO();
	public EditStaffMemberPO esmpo = new EditStaffMemberPO();
	public AddTransactionPO atpo = new AddTransactionPO();
	
	public static FundingController controller = new FundingController();
	private JTextField txtAccountName;

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StaffPagePO frame = new StaffPagePO();
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
	public TransactionPagePO() {
		setTitle("Transactions");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 535, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton btnAddTransaction = new JButton("Add Transaction");
		btnAddTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				atpo.setVisible(true);
			}
		});
		panel.add(btnAddTransaction);
		
		txtAccountName = new JTextField();
		txtAccountName.setText("Account Name");
		panel.add(txtAccountName);
		txtAccountName.setColumns(10);
		
		JButton btnViewStaffList = new JButton("View Expenses");
		panel.add(btnViewStaffList);
		btnViewStaffList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				expenseListLabel.setText("");
				String accountName = txtAccountName.getText();
				List<Expense> expenses = controller.viewFundingAccountExpenses(accountName);
				String name;
				int id;
				String date;
				double amount;
				expenseListLabel.setText("<html>");
				for (Expense aExpense : expenses) {
					String previousText = expenseListLabel.getText();
					name = aExpense.getType();
					date = aExpense.getDate();
					amount = aExpense.getAmount();
					expenseListLabel.setText(previousText + "Account Type: " + name + "&nbsp &nbsp &nbsp " + 
					"Date: " + date + "&nbsp &nbsp &nbsp " + "Amount: " + amount + " <br/>");

				}
				String previousText = expenseListLabel.getText();
				expenseListLabel.setText(previousText + "</html>");
			}
		});
		
		JButton btnSave = new JButton("Save");
		panel.add(btnSave);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
			
			expenseListLabel = new JLabel("");
			expenseListLabel.setHorizontalAlignment(SwingConstants.CENTER);
			scrollPane.setViewportView(expenseListLabel);
			
			feelFreeLabel = new JLabel("Feel free to try adding some staff, and then viewing them.");
			feelFreeLabel.setHorizontalAlignment(SwingConstants.CENTER);
			scrollPane.setColumnHeaderView(feelFreeLabel);
			
			JPanel panel_1 = new JPanel();
			contentPane.add(panel_1, BorderLayout.NORTH);
			welcomeToStaffLabel = new JLabel("Welcome to Staff. There's a lot of stuff.");
			panel_1.add(welcomeToStaffLabel);
			
//			txtName = new JTextField();
//			txtName.setText("Name");
//			txtName.setColumns(10);
			
			
//			GroupLayout gl_contentPane = new GroupLayout(contentPane);
//			gl_contentPane.setHorizontalGroup(
//				gl_contentPane.createParallelGroup(Alignment.LEADING)
//					.addGroup(gl_contentPane.createSequentialGroup()
//						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
//							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 525, GroupLayout.PREFERRED_SIZE)
//							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 525, GroupLayout.PREFERRED_SIZE)
//							.addGroup(gl_contentPane.createSequentialGroup()
//								.addGap(30)
//								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
//									.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
//									.addComponent(ResearchAssistantBox)
//									.addComponent(ResearchAssociateBox))))
//						.addGap(19))
//			);
//			gl_contentPane.setVerticalGroup(
//				gl_contentPane.createParallelGroup(Alignment.LEADING)
//					.addGroup(gl_contentPane.createSequentialGroup()
//						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
//						.addGap(34)
//						.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
//						.addGap(18)
//						.addComponent(ResearchAssistantBox)
//						.addGap(18)
//						.addComponent(ResearchAssociateBox)
//						.addGap(61)
//						.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
//			);
//			contentPane.setLayout(gl_contentPane);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.save();
			}
		});
	}
	
	private void refreshData(){
		
	}
}
