package ca.mcgill.ecse321.urlms.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse321.urlms.application.URLMSApplication;
import ca.mcgill.ecse321.urlms.controller.Controller;
import ca.mcgill.ecse321.urlms.controller.StaffController;
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
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class FundingPagePO extends JFrame {

	private JPanel contentPane;
	JLabel staffMemberListLabel;
	JLabel welcomeToInventoryLabel;
	JLabel feelFreeLabel;
	
	public static StaffController controller = new StaffController();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FundingPagePO frame = new FundingPagePO();
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
	public FundingPagePO() {
		setTitle("Inventory");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 535, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton btnAddInventoryItem = new JButton("Add Item");
		panel.add(btnAddInventoryItem);
		
		JButton btnRemoveItem = new JButton("Remove Item");
		panel.add(btnRemoveItem);
		
		JButton btnSave = new JButton("Save");
		panel.add(btnSave);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
			
			staffMemberListLabel = new JLabel("");
			staffMemberListLabel.setHorizontalAlignment(SwingConstants.CENTER);
			scrollPane.setViewportView(staffMemberListLabel);
			
			feelFreeLabel = new JLabel("Feel free to do stuff with the inventory.");
			feelFreeLabel.setHorizontalAlignment(SwingConstants.CENTER);
			scrollPane.setColumnHeaderView(feelFreeLabel);
			
			JPanel panel_1 = new JPanel();
			contentPane.add(panel_1, BorderLayout.NORTH);
			
			welcomeToInventoryLabel = new JLabel("Welcome to Inventory. There's a lot of inventory.");
			panel_1.add(welcomeToInventoryLabel);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.save();
			}
		});
	}
	
	private void refreshData(){
		
	}
}
