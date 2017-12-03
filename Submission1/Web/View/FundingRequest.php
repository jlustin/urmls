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
		$c->addAccount($_GET['addtype'], $_GET['addbalance']);
		break;
	case "2/10":
		$c->viewAccount($_GET['viewtype']);
		break;
	case "3/10":
		$c->addTransaction($_GET['amount'], $_GET['type']);
		break;
	case "4/10":
		$c->removeAccount($_GET['removetype']);
		break;
	case "9/10":
		$c->getAccounts();
		break;
	case "9/10":
		$c->getNetBalance();
		break;
}