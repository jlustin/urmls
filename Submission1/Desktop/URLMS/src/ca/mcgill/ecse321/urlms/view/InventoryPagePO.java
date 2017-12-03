package ca.mcgill.ecse321.urlms.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse321.urlms.application.URLMSApplication;
import ca.mcgill.ecse321.urlms.controller.Controller;
import ca.mcgill.ecse321.urlms.controller.InventoryController;
import ca.mcgill.ecse321.urlms.controller.StaffController;
import ca.mcgill.ecse321.urlms.model.InventoryItem;
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

public class InventoryPagePO extends JFrame {

	private JPanel contentPane;
	JLabel inventoryItemListLabel;
	JLabel welcomeToInventoryLabel;
	JLabel feelFreeLabel;
	
	public static InventoryController controller = new InventoryController();
	public AddInventoryItemPO aiipo = new AddInventoryItemPO();
	public EditInventoryItemPO eiipo = new EditInventoryItemPO();
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InventoryPagePO frame = new InventoryPagePO();
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
	public InventoryPagePO() {
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
		
		JButton btnViewItemList = new JButton("View Item List");
		btnViewItemList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				inventoryItemListLabel.setText("");
				List<InventoryItem> inventoryList = controller.viewInventoryList();
				String name;
				int id;
				inventoryItemListLabel.setText("<html>");
				id = 0;
				
			//	inventoryItemListLabel.setText(previousText + "");
				for (InventoryItem aItem : inventoryList) {
					String previousText = inventoryItemListLabel.getText();
					name = aItem.getName();
					String category = aItem.getCategory();
					inventoryItemListLabel.setText(previousText + "Item Name: " + name + "&nbsp &nbsp &nbsp " +
					"Item ID: " + id + "&nbsp &nbsp &nbsp " + "Item Category " + category + " <br/>");
					id ++;
				}
				String previousText = inventoryItemListLabel.getText();
				inventoryItemListLabel.setText(previousText + "</html>");
			}
		});
		panel.add(btnViewItemList);
		
		JButton btnAddInventoryItem = new JButton("Add Item");
		btnAddInventoryItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				aiipo.setVisible(true);
			}
		});
		panel.add(btnAddInventoryItem);
		
		JButton btnRemoveItem = new JButton("Edit Item");
		btnRemoveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				eiipo.setVisible(true);
			}
		});
		panel.add(btnRemoveItem);
		
		JButton btnSave = new JButton("Save");
		panel.add(btnSave);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
			
			inventoryItemListLabel = new JLabel("");
			inventoryItemListLabel.setHorizontalAlignment(SwingConstants.CENTER);
			scrollPane.setViewportView(inventoryItemListLabel);
			
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
