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
 	session_start();
	
	$persistence = new Persistence();
	$urlms = $persistence->loadDataFromStore();
	
	$iu = new InfoUpdater($urlms);
	
	// Check which button was clicked by user
	// Run appropriate controller method with respect to user request
	switch($_GET['action']){
		case "editInventoryItem":
			$iu->updateInventory($_GET['editedinventoryname'],$_GET['editedinventorycost'], $_GET['editedinventorycat'], $_GET['isdamaged'], $_GET['editedsupplyquantity']);
			break;
		case "editStaffMember":
			$iu->updateStaffMember($_GET['editedstaffname'],$_GET['editedstaffid']);
			if(!empty($_GET['role'])){
				$iu->updateRoles($_GET['role']);
			}
			else{
				$iu->updateRoles([]);
			}
			if(!empty($_GET['newProgressUpdate'])){
				$iu->addProgressUpdate($_GET['newProgressUpdate'],$_GET['date']);
			}
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
		
		function updateInventory($name, $cost, $category, $isDamaged, $quantity){
			$urlms = $_SESSION['urlms'];
			$inventoryItem = $_SESSION['inventoryitem'];
			
			$inventoryItem->setName($name);
			$inventoryItem->setCost($cost);
			$inventoryItem->setCategory($category);

			if($isDamaged == "damaged"){
				$inventoryItem->setIsDamaged(true);
			}
			else if($isDamaged == "notdamaged"){
				$inventoryItem->setIsDamaged(false);
			}
			else {
				if($quantity > 0){
					$inventoryItem->setQuantity($quantity);
				}
			}
						
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
		
		function updateRoles($roles){
			$urlms = $_SESSION['urlms'];
			$staffMember = $_SESSION['staffmember'];
			
			
			foreach ($staffMember->getResearchRoles() as $r){
				$r->delete();
			}
			
			foreach ($roles as $r){
				switch ($r){
					case "ResearchAssociate":
						$staffMember->addResearchRole(new ResearchAssociate("", $staffMember));
						break;
					case "ResearchAssistant":
						$staffMember->addResearchRole(new ResearchAssistant("", $staffMember));
						break;
				}
			}	
			
			$persistence = new Persistence();
			$persistence->writeDataToStore($urlms);
		}
		
		function addProgressUpdate($desc, $date){
			$urlms = $_SESSION['urlms'];
			$staffMember = $_SESSION['staffmember'];
			
			$staffMember->addProgressUpdate(new ProgressUpdate($date, $desc,$staffMember));
			
			$persistence = new Persistence();
			$persistence->writeDataToStore($urlms);
		}
}