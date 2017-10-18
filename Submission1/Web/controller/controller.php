<?php
	$my_dir = dirname(__FILE__);
	require_once $my_dir . '/../persistence/persistence.php';
	require_once $my_dir . '/../model/URLMS.php';
	require_once $my_dir . '/../model/StaffManager.php';
	require_once $my_dir . '/../model/StaffMember.php';
	require_once $my_dir . '/../model/InventoryManager.php';
	require_once $my_dir . '/../model/FundingManager.php';
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

// 	include '../persistence/persistence.php';
	
// 	$persistence = new Persistence();
// 	$urlms = $persistence->loadData("../staff.xml"); //TODO dont make it load everytime we call controller
	
	session_start();
	
	$c = new Controller();
	
	switch($_GET['action']){
		case "9/10":
			$c->getStaffList();
			break;
		case "10/10":
			$c->addStaff($_GET['newstaffname']); ?>
			
			<!DOCTYPE html>
				<html>
					<head>
						<meta http-equiv="refresh" content="0; url=/index.php/" />
					</head>
				</html>
				
			<?php
			break;
	}
		
class Controller {
	
	public function __construct(){}
	
	function getStaffList(){
		// Load data
		$persistence = new Persistence();
		$urlms = $persistence->loadDataFromStore();
		
		$members = $urlms->getStaffManager()->getStaffMembers();
		for ($i = 0; $i < sizeof($members); $i++){
			echo $members{$i}->getId() . " " . $members{$i}->getName() . "<br>";
		} 
	}
	
	/*
	 * add new staff to the staff.xml file
	 */
	function addStaff($newstaffname){
		
		$name = $newstaffname;
		if($name == null || strlen($name) == 0){
			throw new Exception ("Please enter a name.");
		} else {
			// Load data
			$persistence = new Persistence();
			$urlms = $persistence->loadDataFromStore();
			
			//add the new member to the staff manager
			$newStaff = new StaffMember($name, 00003, $urlms->getStaffManager());
			$urlms->getStaffManager()->addStaffMember($newStaff);
			
			// Write data
			$persistence->writeDataToStore($urlms);
		}
	}
	
}
	/*
	 * get staff list from the staff.xml file
	 */
// 	function getStaffList(){
// 		global $urlms;
// 		$members = $urlms->getStaffManager()->getStaffMembers();
		
// 		for($i = 0; $i < sizeOf($members); $i++){  
// 			echo $members{$i}->getId() . " " . $members{$i}->getName() . "<br>";
// 		}
// 		?>
<!-- 	<HTML> -->
<!-- 	<a href="../index.php">back</a> -->
<!-- 	</HTML> -->
		<?php 
// 	}

?>