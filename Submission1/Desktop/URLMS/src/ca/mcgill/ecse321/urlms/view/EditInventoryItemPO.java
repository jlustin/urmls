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
import javax.swing.JLabel;

public class EditInventoryItemPO extends JFrame {

	private JPanel contentPane;
	private JTextField txtTargetId;
	private JTextField txtNewName;
	private JTextField txtNewCost;
	private JTextField txtNewQuantity;
	private JButton btnEditItemDetails;
	public static InventoryController controller = new InventoryController();
	private JButton btnDeleteTargetedItem;
	private JLabel lblTargetItemId;
	private JLabel lblNewItemName;
	private JLabel lblNewCost;
	private JLabel lblNewQuantity;

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
		txtTargetId.setColumns(10);
		
		txtNewName = new JTextField();
		txtNewName.setColumns(10);
		
		txtNewCost = new JTextField();
		txtNewCost.setColumns(10);
		
		txtNewQuantity = new JTextField();
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
		
		lblTargetItemId = new JLabel("Target Item ID");
		
		lblNewItemName = new JLabel("New Item Name Desired");
		
		lblNewCost = new JLabel("New Cost");
		
		lblNewQuantity = new JLabel("New Quantity");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(89, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnEditItemDetails)
						.addComponent(lblNewQuantity))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(46)
							.addComponent(btnDeleteTargetedItem))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(txtNewQuantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(61)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewCost)
							.addGap(18)
							.addComponent(txtNewCost, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewItemName)
							.addGap(18)
							.addComponent(txtNewName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblTargetItemId)
							.addGap(18)
							.addComponent(txtTargetId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(80, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(23)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtTargetId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTargetItemId))
					.addGap(12)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtNewName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewItemName))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtNewCost, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewCost))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewQuantity)
						.addComponent(txtNewQuantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEditItemDetails)
						.addComponent(btnDeleteTargetedItem))
					.addGap(29))
		);
		contentPane.setLayout(gl_contentPane);
	}

}
