package ca.mcgill.ecse321.urlms.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse321.urlms.application.URLMSApplication;
import ca.mcgill.ecse321.urlms.controller.Controller;
import ca.mcgill.ecse321.urlms.controller.FundingController;
import ca.mcgill.ecse321.urlms.controller.InvalidInputException;
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
	public AddTransactionPO atpo = new AddTransactionPO();
	JPanel panel = new JPanel();
	public static FundingController controller = new FundingController();
	private JTextField txtAccountName;
	JButton btnAddTransaction = new JButton("Add Transaction");
	JButton btnViewExpenses = new JButton("View Expenses");
	private String error;
	private JButton btnClose;

	/**
	 * Create the frame.
	 */
	public TransactionPagePO() {
		setAlwaysOnTop(true);
		initComponents();
	}

	private void initComponents() {
		setTitle("Transactions");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 640, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		contentPane.add(panel, BorderLayout.SOUTH);

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

		panel.add(btnViewExpenses);
		btnViewExpenses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				error = "";
				String previousText;

				try {
					expenseListLabel.setText("");
					String accountName = txtAccountName.getText();
					List<Expense> expenses = controller.viewFundingAccountExpenses(accountName);
					String name;
					String date;
					double amount;
					expenseListLabel.setText("<html>");
					for (Expense aExpense : expenses) {
						previousText = expenseListLabel.getText();
						name = aExpense.getType();
						date = aExpense.getDate();
						amount = aExpense.getAmount();
						expenseListLabel.setText(previousText + "Account Type: " + name + "&nbsp &nbsp &nbsp "
								+ "Date: " + date + "&nbsp &nbsp &nbsp " + "Amount: " + amount + " <br/>");

					}
					previousText = expenseListLabel.getText();
					expenseListLabel.setText(previousText + "</html>");
					if (expenses.isEmpty()) {
						expenseListLabel.setText("There are currently no expenses for the account you have selected.");
					}
				} catch (InvalidInputException e1) {
					error += e1.getMessage();
				}

				refreshData();
			}
		});

		btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		panel.add(btnClose);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		expenseListLabel = new JLabel("");
		expenseListLabel.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setViewportView(expenseListLabel);

		feelFreeLabel = new JLabel("Feel free to try adding some transactions. You can also view expenses.");
		feelFreeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setColumnHeaderView(feelFreeLabel);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		welcomeToStaffLabel = new JLabel("Welcome to Transactions. There's a lot of transactions.");
		panel_1.add(welcomeToStaffLabel);
	}

	private void refreshData() {
		if (error.length() > 0) {
			feelFreeLabel.setText(error);
		} else {
			feelFreeLabel.setText("Here you go, the list of transactions for the account you chose. ");
		}
	}

	public void close() {
		this.setVisible(false);
		this.dispose();
	}
}
