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
import java.awt.Window.Type;

public class MainPage extends JFrame {

	private JPanel contentPane;
	JLabel staffMemberListLabel;
	JLabel welcomeToUrlmsLabel;
	
	public static Controller controller = new Controller();
	public static StaffController staffController = new StaffController();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage frame = new MainPage();
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
	public MainPage() {
		setTitle("Jun2yu");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 462, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton btnStaff = new JButton("Staff");
		btnStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StaffPagePO sppo = new StaffPagePO();
				sppo.setVisible(true);
			}
		});
		panel.add(btnStaff);
		
		JButton btnInventory = new JButton("Inventory");
		btnInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InventoryPagePO ippo = new InventoryPagePO();
				ippo.setVisible(true);
			}
		});
		panel.add(btnInventory);
		
		JButton btnFunding = new JButton("Funding");
		btnFunding.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FundingPagePO fppo = new FundingPagePO();
				fppo.setVisible(true);
			}
		});
		panel.add(btnFunding);
		
		JButton btnSave = new JButton("Save");
		panel.add(btnSave);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
			
			staffMemberListLabel = new JLabel("");
			staffMemberListLabel.setHorizontalAlignment(SwingConstants.CENTER);
			scrollPane.setViewportView(staffMemberListLabel);
			
			JLabel lblFeelFreeTo = new JLabel("Feel free to check out the various amazing functionalites.");
			lblFeelFreeTo.setHorizontalAlignment(SwingConstants.CENTER);
			scrollPane.setColumnHeaderView(lblFeelFreeTo);
			
			JPanel panel_1 = new JPanel();
			contentPane.add(panel_1, BorderLayout.NORTH);
			
			welcomeToUrlmsLabel = new JLabel("Welcome to URLMS.");
			panel_1.add(welcomeToUrlmsLabel);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.save();
			}
		});
	}
	
	private void refreshData(){
		
	}
}
