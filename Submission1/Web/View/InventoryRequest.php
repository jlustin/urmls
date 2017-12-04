<?php

$my_dir = dirname(__FILE__);
require_once $my_dir . '/../persistence/persistence.php';
require_once $my_dir . '/../controller/InventoryController.php';
require_once $my_dir . '/../controller/FundingController.php';


$persistence = new Persistence();
$urlms = $persistence->loadDataFromStore();

$invC = new InventoryController($urlms);
$fundC = new FundingController($urlms);

// Check which button was clicked by user
// Run appropriate controller method with respect to user request
switch($_GET['action']){
	case "9/10":
		$invC->getInventoryList();
		break;
	case "10/10":
		try {
			$invC->addInventory($_GET['newInventoryName'], $_GET['category'], $_GET['type'],$_GET['cost'],$_GET['quantity']);
			if($_GET['type'] == "Equipment"){
				$fundC->addTransaction("Equipment Funding",$_GET['newInventoryName']." bought",$_GET['cost'],"expense","03/12/2017"); 
			}else{//TODO CHANGE DATE ARGUMENT
				$fundC->addTransaction("Supply Funding", $_GET['newInventoryName']." bought", $_GET['quantity']*$_GET['cost'], "expense", "03/12/2017");
			}
			
		} catch (Exception $e){
			echo $e->getMessage() . "<br>";
			echo "<a href= \"../index.php\">Back</a>" . "<br>";
		}
		break;
	case "11/10":
		try {
			$invC->removeInventory($_GET['oldInventoryName']);
		} catch (Exception $e){
			echo $e->getMessage() . "<br>";
			echo "<a href= \"../index.php\">Back</a>" . "<br>";
		}
		break;
	case "12/10":
		try {
			$invC->viewInventoryItem($_GET['inventoryName']);
		} catch (Exception $e){
			echo $e->getMessage() . "<br>";
			echo "<a href= \"../index.php\">Back</a>" . "<br>";
		}
		break;
}