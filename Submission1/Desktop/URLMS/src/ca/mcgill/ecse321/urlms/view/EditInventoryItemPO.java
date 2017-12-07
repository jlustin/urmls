package ca.mcgill.ecse321.urlms.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse321.urlms.controller.Controller;
import ca.mcgill.ecse321.urlms.controller.InventoryController;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditInventoryItemPO extends JFrame {

	private JPanel contentPane;
	private JTextField txtTargetId;
	private JTextField txtNewName;
	private JTextField txtNewCost;
	private JTextField txtNewQuantity;
	private JButton btnEditItemDetails;
	public static InventoryController controller = new InventoryController();
	private JButton btnDeleteTargetedItem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditInventoryItemPO frame = new EditInventoryItemPO();
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
	public EditInventoryItemPO() {
		setTitle("Edit Inventory Item");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		txtTargetId = new JTextField();
		txtTargetId.setText("Target ID");
		txtTargetId.setColumns(10);
		
		txtNewName = new JTextField();
		txtNewName.setText("New Name");
		txtNewName.setColumns(10);
		
		txtNewCost = new JTextField();
		txtNewCost.setText("New Cost");
		txtNewCost.setColumns(10);
		
		txtNewQuantity = new JTextField();
		txtNewQuantity.setText("New Quantity");
		txtNewQuantity.setColumns(10);
		
		btnEditItemDetails = new JButton("Edit Item Details");
		btnEditItemDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String newName = txtNewName.getText();
				int id = Integer.valueOf(txtTargetId.getText());
				double cost = Double.valueOf(txtNewCost.getText());
				int quantity = Integer.valueOf(txtNewQuantity.getText());
				
				controller.editInventoryItemDetails(id, newName, cost, quantity);
			}
		});
		
		btnDeleteTargetedItem = new JButton("Delete Targeted Item");
		btnDeleteTargetedItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int itemID = Integer.valueOf(txtTargetId.getText());
				
				controller.removeInventoryItem(itemID);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(123, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnEditItemDetails)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(txtNewQuantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(txtNewCost, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(txtNewName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(txtTargetId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(46)
					.addComponent(btnDeleteTargetedItem)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(23)
					.addComponent(txtTargetId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(txtNewName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(txtNewCost, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(txtNewQuantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEditItemDetails)
						.addComponent(btnDeleteTargetedItem))
					.addGap(29))
		);
		contentPane.setLayout(gl_contentPane);
	}

}
