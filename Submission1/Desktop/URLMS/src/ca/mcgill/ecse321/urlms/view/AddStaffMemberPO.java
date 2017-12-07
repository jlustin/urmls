package ca.mcgill.ecse321.urlms.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse321.urlms.controller.StaffController;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddStaffMemberPO extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	public static StaffController controller = new StaffController();
	
	private JCheckBox ResearchAssistantBox = new JCheckBox("Research Assistant");
	
	private JCheckBox ResearchAssociateBox = new JCheckBox("Research Associate");
	private JTextField txtSalary;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddStaffMemberPO frame = new AddStaffMemberPO();
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
	public AddStaffMemberPO() {
		setTitle("Add Staff Member");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		txtName = new JTextField();
		txtName.setText("Name");
		txtName.setColumns(10);
		

		
		JButton AddButton = new JButton("Add to List");
		AddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String staffMemberName = txtName.getText();
				boolean box1 = ResearchAssistantBox.isSelected();
				boolean box2 = ResearchAssociateBox.isSelected();
				
				String stringSalary = txtSalary.getText();
				double doubleSalary = Double.valueOf(stringSalary);
				
				
				controller.addStaffMember(staffMemberName, box1, box2, doubleSalary);
			}
		});
		
		txtSalary = new JTextField();
		txtSalary.setText("Salary");
		txtSalary.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(152)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(txtSalary, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(AddButton)
						.addComponent(ResearchAssociateBox)
						.addComponent(ResearchAssistantBox)
						.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(135, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(41)
					.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(ResearchAssistantBox)
					.addGap(18)
					.addComponent(ResearchAssociateBox)
					.addGap(18)
					.addComponent(txtSalary, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
					.addComponent(AddButton)
					.addGap(35))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
