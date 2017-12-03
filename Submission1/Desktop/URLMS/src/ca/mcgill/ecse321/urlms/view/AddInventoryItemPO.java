package ca.mcgill.ecse321.urlms.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse321.urlms.controller.InventoryController;
import ca.mcgill.ecse321.urlms.controller.StaffController;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddInventoryItemPO extends JFrame {

	private JPanel contentPane;
	private JTextField txtItemName;
	private JTextField txtItemCost;
	public static InventoryController controller = new InventoryController();
	private JButton btnAddAsEquipment;
	private JTextField txtQuantity;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddInventoryItemPO frame = new AddInventoryItemPO();
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
	public AddInventoryItemPO() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		txtItemName = new JTextField();
		txtItemName.setText("Item Name");
		txtItemName.setColumns(10);
		
		txtItemCost = new JTextField();
		txtItemCost.setText("Item Cost");
		txtItemCost.setColumns(10);
		
		JButton AddButton = new JButton("Add as Supply Item");
		AddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name = txtItemName.getText();
				double cost = Double.valueOf(txtItemCost.getText());
				int quantity = Integer.valueOf(txtQuantity.getText());
				controller.addSupplyItem(name, cost, quantity);
				
			}
		});
		
		btnAddAsEquipment = new JButton("Add as Equipment Item");
		btnAddAsEquipment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name = txtItemName.getText();
				double cost = Double.valueOf(txtItemCost.getText());
				controller.addEquipmentItem(name, cost);
			}
		});
		
		txtQuantity = new JTextField();
		txtQuantity.setText("Quantity");
		txtQuantity.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(47)
					.addComponent(AddButton)
					.addGap(26)
					.addComponent(btnAddAsEquipment)
					.addContainerGap(12, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(157, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(txtQuantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtItemCost, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtItemName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(153))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(43)
					.addComponent(txtItemName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(txtItemCost, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(txtQuantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(32)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(AddButton)
						.addComponent(btnAddAsEquipment))
					.addContainerGap(50, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

}
