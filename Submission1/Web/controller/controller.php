<?php
	include '../persistence/persistence.php';
	
	$persistence = new Persistence();
	$urlms = $persistence->loadData("../staff.xml"); //TODO dont make it load everytime we call controller
	
	switch($_GET['action']){
		case "9/10":
			getStaffList();
			break;
		case "10/10":
			addStaff($_GET['newstaffname']);
			break;
	}
		
	/*
	 * get staff list from the staff.xml file
	 */
	function getStaffList(){
		global $urlms;
		$members = $urlms->getStaffManager()->getStaffMembers();
		
		for($i = 0; $i < sizeOf($members); $i++){
			echo $members{$i}->getId() . " " . $members{$i}->getName() . "<br>";
		}
		?>
	<HTML>
	<a href="../index.php">back</a>
	</HTML>
		<?php 
	}
	
	/*
	 * add new staff to the staff.xml file
	 */
	function addStaff($newstaffname){
	
// 		$data = loadData("staff.xml");
// 		$staff = $data->addChild("staff");
// 		$staff->addChild("name", $newstaffname);
// 		saveData($staff, "staff.xml");
	}
	
?>