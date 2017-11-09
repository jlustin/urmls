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
	
	// start session
	session_start();
	
	$persistence = new Persistence();
	$urlms = $persistence->loadDataFromStore();
	
	$c = new StaffController($urlms);
	
	
	// Check which button was clicked by user
	// Run appropriate controller method with respect to user request
	if(!empty($_GET['action'])){
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
			case "11/10":
				try {
					$c->removeStaff($_GET['oldstaffname'], $_GET['oldstaffid']);
				} catch (Exception $e){
					echo $e->getMessage() . "<br>";
					echo "<a href= \"../index.php\">Back</a>" . "<br>";
				}
				break;
		}
	}
		
class StaffController {
	
	protected $urlms;
	/*
	 * Constructor
	 */
	public function __construct($urlms){
		$this->urlms = $urlms;
	}
	
	/*
	 * get list of staff from urlms
	 */
	function getStaffList(){
		// Load data
		//$persistence = new Persistence();
		$urlms = $this->urlms;//$persistence->loadDataFromStore();
		// Get staff members from urlms
		$members = $urlms->getLab_index(0)->getStaff()->getStaffMembers();
		for ($i = 0; $i < sizeof($members); $i++){
			// display each staff member represented by their ID and name
			echo $members{$i}->getId() . " " . $members{$i}->getName() . "<br>";
		} 
		?>
		<!-- Add back button to page -->
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
			$urlms = $this->urlms;//$persistence->loadDataFromStore();
			
			//add the new member to the staff manager
			$newStaffMember = new StaffMember($name, rand(0,1000), $urlms->getLab_index(0)->getStaff());
			$urlms->getLab_index(0)->getStaff()->addStaffMember($newStaffMember);
			
			// Write data
			$persistence->writeDataToStore($urlms);
			
			?>
			<!-- Add back button to page -->
			<HTML>
				<p>New staff member successfully added!</p>
				<a href="../index.php">Back</a>
			</HTML><?php
		}
	}
	
	/*
	 * remove a staff member from urlms
	 */
	function removeStaff($name, $id){
		if($name == null || strlen($name) == 0){
			throw new Exception ("Please enter a name.");
		} else {
			// Load data
			$persistence = new Persistence();
			$urlms = $this->urlms;//$persistence->loadDataFromStore();
			
			//Find the member to remove
			$members = $urlms->getLab_index(0)->getStaff()->getStaffMembers();
			for ($i = 0; $i < sizeof($members); $i++){
				if($name == $members{$i}->getName() && $id == $members{$i}->getID()){
					$staffMember = $members{$i};
				}
			}
			
			if($staffMember == null){
				throw new Exception ("Staff Member not found.");
			}
			
			$result = $urlms->getLab_index(0)->getStaff()->removeStaffMember($staffMember);
			
			// Write data
			$persistence->writeDataToStore($urlms);
			
			?>
			<!-- Add back button to page -->
			<HTML>
				<p>Staff member removed succesfully</p>
				<a href="../index.php">Back</a>
			</HTML><?php
		}		
	}
}
?>