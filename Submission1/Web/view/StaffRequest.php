<?php

$my_dir = dirname(__FILE__);
require_once $my_dir . '/../persistence/persistence.php';
require_once $my_dir . '/../controller/StaffController.php';

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
				$c->addStaff($_GET['newstaffname'],$_GET['newstaffsalary']);
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