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
			case "12/10":
				try {
					$c->viewMemberRecord($_GET['staffname'], $_GET['staffid']);
				} catch (Exception $e){
					echo $e->getMessage() . "<br>";
					echo "<a href= \"../index.php\">Back</a>" . "<br>";
				}
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
		// Get staff members from urlms
		$members = $this->urlms->getLab_index(0)->getStaff()->getStaffMembers();
		for ($i = 0; $i < sizeof($members); $i++){
			// display each staff member represented by their ID and name
			echo $members{$i}->getId() . " " . $members{$i}->getName() . "<br>";
		} 
		echo "<a href= \"../index.php\">Back</a>" . "<br>";
	}
	
	/*
	 * add new staff to urlms
	 */
	function addStaff($name){
		if($name == null || strlen($name) == 0){
			throw new Exception ("Please enter a name.");
		} else {
			$urlms = $this->urlms;
			
			//add the new member to the staff manager
			$newStaffMember = new StaffMember($name, rand(0,1000), $urlms->getLab_index(0)->getStaff());
			$urlms->getLab_index(0)->getStaff()->addStaffMember($newStaffMember);
			
			//Save
			$persistence = new Persistence();
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
		$urlms = $this->urlms;
		$staffMember = $this->findMember($name, $id);
		
		//Remove staff member
// 		$urlms->getLab_index(0)->getStaff()->removeStaffMember($staffMember);
		$staffMember->delete();
		
		//Save
		$persistence = new Persistence();
		$persistence->writeDataToStore($urlms);
		
		?>
		<!-- Add back button to page -->
		<HTML>
			<p>Staff member removed succesfully</p>
			<a href="../index.php">Back</a>
		</HTML><?php		
	}
	
	function viewMemberRecord($name, $id){
		$urlms = $this->urlms;
		$staffMember = $this->findMember($name, $id);
		$_SESSION['staffmember'] = $staffMember;
		$_SESSION['urlms'] = $urlms;
		
		//Display member info
		echo "ID: " . $staffMember->getId() . "<br>";
		echo "Name: " . $staffMember->getName() . "<br>";
		echo "Role(s):";
		if(!$staffMember->hasResearchRoles()){
			echo " None";
		}
		for($i = 0; $i < $staffMember->numberOfResearchRoles(); $i++){
			echo " " . $staffMember->get_class(getResearchRole_index($i));
		}
		echo "<br>";
		echo "Progress Updates:";
		if(!$staffMember->hasProgressUpdates()){
			echo " None";
		}
		for($i = 0; $i < $staffMember->numberOfProgressUpdates(); $i++){
			//TODO Update domain model to add text in progress update
			//echo " " . $staffMember->getProgressUpdate_index($i)->getDescription();
		}
		echo "<br>";
		?>
		<HTML>
			<form action="InfoUpdater.php" method="get">
			<br>
			<h3>Edit Staff Member</h3>
			<input type="hidden" name="action" value="editStaffMember" />
			New Name: <input type="text" name="editedstaffname" value="<?php echo $staffMember->getName();?>"/>
			New ID: <input type="text" name="editedstaffid" value="<?php echo $staffMember->getId();?>"/>
			
			<?php
			for ($i = 0; $i < $staffMember->numberOfResearchRoles(); $i++) {
			?>
			    <input type="radio" name="num<?php echo $i; ?>" value="<?php echo $staffMember->get_class(getResearchRole_index($i));?>"><br>
			<?php
			}
			?>
					
			<?php
// 			for ($i = 1; $i <= 3; $i++) {
// 			    echo $_POST['num' . $i];
// 			}
// 			?>
 			<input type="submit" value="Edit staff!" />
 			<br>
		</form>
		</HTML>
		<?php 
		
		echo "<a href= \"../index.php\">Back</a>" . "<br>";
	}
	
	function editMemberRecord($name, $id){
		
		$staffMember = $this->findMember($name, $id);

		//TODO Insert some html here to create form maybe 
		//OR redirect to editMember.html or something
	}
	
	function findMember($name,$id){
		if($name == null || strlen($name) == 0){
			throw new Exception ("Please enter a name.");
		} else{
			//Find the member
			$members = $this->urlms->getLab_index(0)->getStaff()->getStaffMembers();
			for ($i = 0; $i < sizeof($members); $i++){
				if($name == $members{$i}->getName() && $id == $members{$i}->getID()){
					$staffMember = $members{$i};
				}
			}
			if($staffMember == null){
				throw new Exception ("Staff Member not found.");
			}
		}
		return $staffMember;
	}
}
?>