<?php
	include 'persistence/persistence.php';
	
	/*
	 * get staff list from the staff.xml file
	 */
	function getStaffList(){
		$data = loadData("staff.xml");
		foreach($data->children() as $staffs){
			echo $staffs['id'] . "    " . $staffs->name . "<br>";
		}
	}
	
	/*
	 * add new staff to the staff.xml file
	 */
	function addStaff($newstaffname){
		$data = loadData("staff.xml");
		$staff = $data->addChild("staff");
		$staff->addChild("name", $newstaffname);
		saveData($staff, "staff.xml");
	}
?>