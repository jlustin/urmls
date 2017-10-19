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
	
	session_start();
	
	$c = new Controller();
	
	switch($_GET['action']){
		case "9/10":
			$c->getStaffList();
			break;
		case "10/10":
			try {
			$c->addStaff($_GET['newstaffname']); 
			} catch (Exception $e){
				echo $e->getMessage() . "<br>";
				echo "<a href= \"../index.php\">Back</a>" . "<br>";
			}
			break;
	}
		
class Controller {
	
	public function __construct(){}
	
	/*
	 * get list of staff from urlms
	 */
	function getStaffList(){
		// Load data
		$persistence = new Persistence();
		$urlms = $persistence->loadDataFromStore();
		
		$members = $urlms->getStaffManager()->getStaffMembers();
		for ($i = 0; $i < sizeof($members); $i++){
			echo $members{$i}->getId() . " " . $members{$i}->getName() . "<br>";
		} 
		?>
		<HTML>
			<a href="../index.php">Back</a>
		</HTML><?php
		//Can use echo "<a href= \"../index.php\">Back</a>" . "<br>"; as alternative
	}
	
	/*
	 * add new staff to urlms
	 */
	function addStaff($name){
		
		if($name == null || strlen($name) == 0){
			throw new Exception ("Please enter a name.");
		} else {
			// Load data
			$persistence = new Persistence();
			$urlms = $persistence->loadDataFromStore();
			
			//add the new member to the staff manager
			$newStaff = new StaffMember($name, rand(0,1000), $urlms->getStaffManager());
			$urlms->getStaffManager()->addStaffMember($newStaff);
			
			// Write data
			$persistence->writeDataToStore($urlms);
			
			?>
			<HTML>
				<p>New staff member successfully added!</p>
				<a href="../index.php">Back</a>
			</HTML><?php
		}
	}
	
}
?>