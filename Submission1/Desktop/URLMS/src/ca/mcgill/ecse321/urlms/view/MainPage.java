package ca.mcgill.ecse321.urlms.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse321.urlms.controller.Controller;
import ca.mcgill.ecse321.urlms.controller.StaffController;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class MainPage extends JFrame {

	private JPanel contentPane;
	JLabel staffMemberListLabel;
	JLabel welcomeToUrlmsLabel;
	
	public static Controller controller = new Controller();
	public static StaffController staffController = new StaffController();
	private JButton btnStaff;
	private JButton btnFunding;
	private JButton btnSave;
	private JLabel lblFeelFreeTo;

	/**
	 * Create the frame.
	 */
	public MainPage() {
//		pack();
//		setLocationRelativeTo(null);
		centerWindow(this);
		initComponents();
	}
	
	private void initComponents(){
		setTitle("URLMS Main Page");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 462, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		btnStaff = new JButton("Staff");
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
		
		btnFunding = new JButton("Funding");
		btnFunding.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FundingPagePO fppo = new FundingPagePO();
				fppo.setVisible(true);
			}
		});
		panel.add(btnFunding);
		
		btnSave = new JButton("Save");
		panel.add(btnSave);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
			
			staffMemberListLabel = new JLabel("");
			staffMemberListLabel.setHorizontalAlignment(SwingConstants.CENTER);
			scrollPane.setViewportView(staffMemberListLabel);
			
			lblFeelFreeTo = new JLabel("Feel free to check out the various amazing functionalites.");
			lblFeelFreeTo.setHorizontalAlignment(SwingConstants.CENTER);
			scrollPane.setColumnHeaderView(lblFeelFreeTo);
			
			JPanel panel_1 = new JPanel();
			contentPane.add(panel_1, BorderLayout.NORTH);
			
			welcomeToUrlmsLabel = new JLabel("Welcome to URLMS.");
			panel_1.add(welcomeToUrlmsLabel);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.save();
				SavePO spo = new SavePO();
				spo.setVisible(true);
			}
		});
	}
	
	private void refreshData(){
		
	}
	
	public static void centerWindow(Window frame) {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
	}
}
