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
	
	
	
?>