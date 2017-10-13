<?php

	/* 
	 * get staff list from the staff.xml file
	 */
	function getStaffList(){
		$xml=simplexml_load_file("staff.xml") or die ("Error: Cannot create object");
		foreach($xml->children() as $staffs){
			echo $staffs['id'] . "    " . $staffs->name . "<br>";
		}
	}
	
?>