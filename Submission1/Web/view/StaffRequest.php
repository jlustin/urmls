<?php

$my_dir = dirname(__FILE__);
require_once $my_dir . '/../persistence/persistence.php';
require_once $my_dir . '/../controller/StaffController.php';

$persistence = new Persistence();
$urlms = $persistence->loadDataFromStore();

$invC = new StaffController($urlms);

// Check which button was clicked by user
// Run appropriate controller method with respect to user request
if(!empty($_GET['action'])){
	switch($_GET['action']){
		case "9/10":
			$invC->getStaffList();
			break;
		case "10/10":
			try {
				$invC->addStaff($_GET['newstaffname'],$_GET['newstaffsalary']);
			} catch (Exception $e){
				echo $e->getMessage() . "<br>";
				echo "<a href= \"StaffView.php\">Back</a>" . "<br>";
			}
			break;
		case "11/10":
			try {
				$invC->removeStaff($_GET['oldstaffname'], $_GET['oldstaffid']);
			} catch (Exception $e){
				echo $e->getMessage() . "<br>";
				echo "<a href= \"StaffView.php\">Back</a>" . "<br>";
			}
			break;
		case "12/10":
			try {
				$invC->viewMemberRecord($_GET['staffname'], $_GET['staffid']);
			} catch (Exception $e){
				echo $e->getMessage() . "<br>";
				echo "<a href= \"StaffView.php\">Back</a>" . "<br>";
			}
	}
}