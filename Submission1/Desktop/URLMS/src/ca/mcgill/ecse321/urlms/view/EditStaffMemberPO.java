package ca.mcgill.ecse321.urlms.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse321.urlms.application.URLMSApplication;
import ca.mcgill.ecse321.urlms.controller.StaffController;
import ca.mcgill.ecse321.urlms.model.URLMS;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditStaffMemberPO extends JFrame {

	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtNewName;
	private JTextField txtNewId;
	private JTextField txtNewSalary;
	public static StaffController controller = new StaffController();
	private JCheckBox ResearchAssistantBox = new JCheckBox("Research Assistant");
	
	private JCheckBox ResearchAssociateBox = new JCheckBox("Research Associate");
	private JButton btnDelete;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditStaffMemberPO frame = new EditStaffMemberPO();
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
	public EditStaffMemberPO() {
		setTitle("Edit Staff Member");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		txtID = new JTextField();
		txtID.setText("ID of target");
		txtID.setColumns(10);
		
		txtNewName = new JTextField();
		txtNewName.setText("New Name");
		txtNewName.setColumns(10);
		
		txtNewId = new JTextField();
		txtNewId.setText("New ID");
		txtNewId.setColumns(10);
		

		
		JButton btnEditMember = new JButton("Edit Member");
		btnEditMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String desiredName = txtNewName.getText();
				int newID = Integer.valueOf(txtNewId.getText());
				boolean box1 = ResearchAssistantBox.isSelected();
				boolean box2 = ResearchAssociateBox.isSelected();
				int targetID = Integer.valueOf(txtID.getText());
				double weeklySalary = Double.valueOf(txtNewSalary.getText());
				
				controller.editStaffmemberRecordByID(targetID, newID, desiredName, box1, box2, weeklySalary);
			}
		});
		
		txtNewSalary = new JTextField();
		txtNewSalary.setText("New Salary");
		txtNewSalary.setColumns(10);
		
		btnDelete = new JButton("Delete Member");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.valueOf(txtID.getText());
				
				controller.removeStaffMemberByID(id);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(159, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(txtNewSalary, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(ResearchAssociateBox)
						.addComponent(ResearchAssistantBox)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnEditMember)
							.addGap(42)
							.addComponent(btnDelete))
						.addComponent(txtNewId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtNewName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(txtNewName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(txtNewId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(ResearchAssistantBox)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(ResearchAssociateBox)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(txtNewSalary, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEditMember)
						.addComponent(btnDelete))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}

}
