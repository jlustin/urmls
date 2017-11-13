<?php
	$my_dir = dirname(__FILE__);
	require_once $my_dir . '/../persistence/persistence.php';
	require_once $my_dir . '/../model/URLMS.php';
	require_once $my_dir . '/../model/Lab.php';
	require_once $my_dir . '/../model/Staff.php';
	require_once $my_dir . '/../model/Funding.php';
	require_once $my_dir . '/../model/Inventory.php';
	require_once $my_dir . '/../model/StaffMember.php';
	require_once $my_dir . '/../model/InventoryItem.php';
	require_once $my_dir . '/../model/SupplyType.php';
	require_once $my_dir . '/../model/ResearchRole.php';
	require_once $my_dir . '/../model/ResearchAssociate.php';
	require_once $my_dir . '/../model/ResearchAssistant.php';
	require_once $my_dir . '/../model/Report.php';
	require_once $my_dir . '/../model/ProgressUpdate.php';
	require_once $my_dir . '/../model/Expense.php';
	require_once $my_dir . '/../model/Equipment.php';
	require_once $my_dir . '/../model/FundingAccount.php';
	
	require('InventoryController.php');
	
	// start session
// 	session_start();
	
	$persistence = new Persistence();
	$urlms = $persistence->loadDataFromStore();
	
	$iu = new InfoUpdater($urlms);
	
	// Check which button was clicked by user
	// Run appropriate controller method with respect to user request
	switch($_GET['action']){
		case "editInvetoryItem":
			$iu->updateInventory($_GET['editedInventoryName'],$_GET['editedInventoryCost'], $_GET['editedInventoryCat']);
			break;
		case "editStaffMember":
			$iu->updateStaffMember($_GET['editedstaffname'],$_GET['editedstaffid']);
			break;
			
	}
	
	class InfoUpdater {
		
		protected $urlms;
		/*
		 * Constructor
		 */
		public function __construct($urlms){
			$this->urlms = $urlms;
		}
		
		function updateInventory($name, $cost, $category){
			$name = $_SESSION['inventoryItem'];
			$inventoryItem = findInventoryItem($name);
			
			$inventoryItem->setName($name);
			$inventoryItem->setName($cost);
			$inventoryItem->setName($category);
			
			echo "Inventory item updated succesfully! <br>";
			echo "<a href= \"../view/InventoryView.html\">Back</a>" . "<br>";
		}
		
		function updateStaffMember($name, $id){
			$urlms = $_SESSION['urlms'];
			$staffMember = $_SESSION['staffmember'];
			
			$staffMember->setName($name);
			$staffMember->setId($id);
			
			$persistence = new Persistence();
			$persistence->writeDataToStore($urlms);
			
			?>
			<!-- Add back button to page -->
			<HTML>
				<p>Staff member edited succesfully</p>
				<a href="../index.php">Back</a>
			</HTML><?php		
		}
		
		function findInventoryItem($name){
			if($name == null || strlen($name) == 0){
				throw new Exception ("Please enter an inventory item name.");
			} else{
				//Find the member
				$items = $this->urlms->getLab_index(0)->getInventory()->getInventoryItems();
				for ($i = 0; $i < sizeof($items); $i++){
					if($name == $items{$i}->getName()){
						$inventoryItem = $items{$i};
					}
				}
				if($inventoryItem == null){
					throw new Exception ("Inventory item not found.");
				}
			}
			return $inventoryItem;
		}
}