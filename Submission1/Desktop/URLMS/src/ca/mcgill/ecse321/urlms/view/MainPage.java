package ca.mcgill.ecse321.urlms.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse321.urlms.application.URLMSApplication;
import ca.mcgill.ecse321.urlms.controller.Controller;
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

public class MainPage extends JFrame {

	private JPanel contentPane;
	JLabel staffMemberListLabel;
	JLabel welcomeToUrlmsLabel;
	JLabel feelFreeLabel;
	
	public static Controller controller = new Controller();

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 462, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton btnAddSampleStaff = new JButton("Add Sample Staff");
		btnAddSampleStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.addSampleMembers();
			}
		});
		panel.add(btnAddSampleStaff);
		
		JButton btnViewStaffList = new JButton("View Staff List");
		panel.add(btnViewStaffList);
		btnViewStaffList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				staffMemberListLabel.setText("");
				List<StaffMember> staffList = controller.viewStaffList();
				String name;
				int id;
				staffMemberListLabel.setText("<html>");
				for (StaffMember aMember : staffList) {
					String previousText = staffMemberListLabel.getText();
					name = aMember.getName();
					id = aMember.getId();
					staffMemberListLabel.setText(previousText + name + " " + id + " <br/>");
				}
				String previousText = staffMemberListLabel.getText();
				staffMemberListLabel.setText(previousText + "</html>");
			}
		});
		
		JButton btnSave = new JButton("Save");
		panel.add(btnSave);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
			
			staffMemberListLabel = new JLabel("");
			staffMemberListLabel.setHorizontalAlignment(SwingConstants.CENTER);
			scrollPane.setViewportView(staffMemberListLabel);
			
			feelFreeLabel = new JLabel("Feel free to try adding sample staff, and then viewing them.");
			scrollPane.setColumnHeaderView(feelFreeLabel);
			
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
