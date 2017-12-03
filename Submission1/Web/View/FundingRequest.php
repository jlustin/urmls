<?php

$my_dir = dirname(__FILE__);
require_once $my_dir . '/../persistence/persistence.php';
require_once $my_dir . '/../controller/FundingController.php';

$persistence = new Persistence();
$urlms = $persistence->loadDataFromStore();

$c = new FundingController($urlms);
// Check which button was clicked by user
// Run appropriate controller method with respect to user request
switch($_GET['action']){
	case "1/10":
		$c->addAccount($_GET['type'], $_GET['balance']);
		break;
	case "2/10":
		$c->viewNetBalance($_GET['type']);
		break;
	case "3/10":
		$c->addTransaction($_GET['amount'], $_GET['type']);
		break;
}