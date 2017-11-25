<?php
	$my_dir = dirname(__FILE__);
	require_once $my_dir . '/../persistence/persistence.php';
	require_once $my_dir . '/../model/URLMS.php';
	require_once $my_dir . '/../model/Lab.php';
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
		case "editInventoryItem":
			$iu->updateInventory($_GET['editedinventoryname'],$_GET['editedinventorycost'], $_GET['editedinventorycat']);
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
			$urlms = $_SESSION['urlms'];
			$inventoryItem = $_SESSION['inventoryitem'];
			
			$inventoryItem->setName($name);
			$inventoryItem->setCost($cost);
			$inventoryItem->setCategory($category);
			
			$persistence = new Persistence();
			$persistence->writeDataToStore($urlms);
			
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
			
			echo "Staff member updated succesfully! <br>";
			echo "<a href= \"../view/StaffView.php\">Back</a>" . "<br>";	
		}
}