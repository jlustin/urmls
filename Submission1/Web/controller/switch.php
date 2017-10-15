<?php
	include '/controller/controller.php';

	$controller = new Controller();
	
	switch($_GET['action']){
		case "9/10":
			$controller->getStaffList();
			break;
		case "10/10":
			$controller->addStaff($_GET['newstaffname']);
			break;
	}
?>