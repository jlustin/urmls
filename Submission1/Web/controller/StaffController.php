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
		$members = $this->urlms->getLab_index(0)->getStaffMembers();
		for ($i = 0; $i < sizeof($members); $i++){
			// display each staff member represented by their ID and name
			echo $members{$i}->getId() . " " . $members{$i}->getName() . "<br>";
		} 
		foreach ($members as $m){
			echo $m->getId() . " " . $m->getName() . "<br>";
		}
		
		echo "<a href= \"../View/StaffView.php\">Back</a>" . "<br>";
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
			$newStaffMember = new StaffMember($name, rand(0,1000), $urlms->getLab_index(0));
			$urlms->getLab_index(0)->addStaffMember($newStaffMember);
			
			//Save
			$persistence = new Persistence();
			$persistence->writeDataToStore($urlms);
			
			?>
			<!-- Add back button to page -->
			<HTML>
				<p>New staff member successfully added!</p>
				<a href="../View/StaffView.php">Back</a>
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
		$staffMember->delete();
		
		//Save
		$persistence = new Persistence();
		$persistence->writeDataToStore($urlms);
		
		?>
		<!-- Add back button to page -->
		<HTML>
			<p>Staff member removed succesfully</p>
			<a href="../View/StaffView.php">Back</a>
		</HTML><?php		
	}
	
	function viewMemberRecord($name, $id){
		$urlms = $this->urlms;
		$staffMember = $this->findMember($name, $id);
		session_start();
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
			<form action="../Controller/InfoUpdater.php" method="get">
			<br>
			<h3>Edit Staff Member</h3>
			<input type="hidden" name="action" value="editStaffMember" />
			New Name: <input type="text" name="editedstaffname" value="<?php echo $staffMember->getName();?>"/>
			New ID: <input type="text" name="editedstaffid" value="<?php echo $staffMember->getId();?>"/>
			<br>
			
			Roles:<br>
			
			<?php 
			
			$isRoleA = false; $isRoleB = false;
			
			foreach ($staffMember->getResearchRoles() as $r){
				if(get_class($r) == "ResearchAssistant"){
					$isRoleA = true;
				}
				elseif (get_class($r)== "ResearchAssociate"){
					$isRoleB = true;
				}
			}
			
			if($isRoleA){
				echo "<input type=\"checkbox\" name=\"roleA checked \"> Research Associate <br>";
			}else{
				echo "<input type=\"checkbox\" name=\"roleA\"> Research Associate <br>";
			}
			if($isRoleB){
				echo "<input type=\"checkbox\" name=\"roleB checked \"> Research Assistant <br>";
			}else{
				echo "<input type=\"checkbox\" name=\"roleB\"> Research Assistant <br>";
			}
			?>
 			
 			<input type="submit" value="Edit staff!" />
 			<br>
		</form>
		</HTML>
		<?php 
		
		echo "<a href= \"../View/StaffView.php\">Back</a>" . "<br>";
	}
		
	function findMember($name,$id){
		if($name == null || strlen($name) == 0){
			throw new Exception ("Please enter a name.");
		} else{
			//Find the member
			$members = $this->urlms->getLab_index(0)->getStaffMembers();
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