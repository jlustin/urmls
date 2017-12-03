<?php

$my_dir = dirname(__FILE__);
require_once $my_dir . '/../persistence/persistence.php';
require_once $my_dir . '/../controller/FundingController.php';

// start session
session_start();

$c = new FundingController();
// Check which button was clicked by user
// Run appropriate controller method with respect to user request
switch($_GET['action']){
	case "9/10":

		break;
	case "10/10":

		break;
	case "11/10":

		break;
}
