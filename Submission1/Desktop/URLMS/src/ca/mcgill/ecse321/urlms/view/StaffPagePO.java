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

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

public class StaffPagePO extends JFrame {

	private JPanel contentPane;
	JLabel welcomeToStaffLabel;
	
	public static StaffController controller = new StaffController();
	private JTextField txtName;
	private AbstractButton staffMemberListLabel;
	
	private JCheckBox ResearchAssistantBox = new JCheckBox("ResearchAssistant");
	
	private JCheckBox ResearchAssociateBox = new JCheckBox("ResearchAssociate");
	

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
	public StaffPagePO() {
		setTitle("Staff");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 535, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		
		JButton btnAddStaff = new JButton("Add Staff");
		btnAddStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String staffMemberName = txtName.getText();
				boolean box1 = ResearchAssistantBox.isSelected();
				boolean box2 = ResearchAssociateBox.isSelected();
				
				controller.addStaffMember(staffMemberName, box1, box2);
			}
		});
		panel.add(btnAddStaff);
		
		JButton btnEditStaffMember = new JButton("Edit Staff Member");
		panel.add(btnEditStaffMember);
		
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
			
			JPanel panel_1 = new JPanel();
			
			welcomeToStaffLabel = new JLabel("Welcome to Staff. There's a lot of stuff.");
			panel_1.add(welcomeToStaffLabel);
			
			txtName = new JTextField();
			txtName.setText("Name");
			txtName.setColumns(10);
			
			
			GroupLayout gl_contentPane = new GroupLayout(contentPane);
			gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 525, GroupLayout.PREFERRED_SIZE)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 525, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(30)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(ResearchAssistantBox)
									.addComponent(ResearchAssociateBox))))
						.addGap(19))
			);
			gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(34)
						.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(ResearchAssistantBox)
						.addGap(18)
						.addComponent(ResearchAssociateBox)
						.addGap(61)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
			);
			contentPane.setLayout(gl_contentPane);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.save();
			}
		});
	}
	
	private void refreshData(){
		
	}
}