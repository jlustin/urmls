<?php

$my_dir = dirname(__FILE__);
require_once $my_dir . '/../persistence/persistence.php';
require_once $my_dir . '/../controller/InventoryController.php';

$persistence = new Persistence();
$urlms = $persistence->loadDataFromStore();

$c = new InventoryController($urlms);

// Check which button was clicked by user
// Run appropriate controller method with respect to user request
switch($_GET['action']){
	case "9/10":
		$c->getInventoryList();
		break;
	case "10/10":
		try {
			if(!isset($_GET['type'])){
				echo "Please choose a type!";
				echo "<br>";
				echo "<a href= \"../index.php\">Back</a>" . "<br>";
			}else{ 
				$c->addInventory($_GET['newInventoryName'], $_GET['category'], $_GET['type'],$_GET['cost'],$_GET['quantity']);
			}
		} catch (Exception $e){
			echo $e->getMessage() . "<br>";
			echo "<a href= \"../index.php\">Back</a>" . "<br>";
		}
		break;
	case "11/10":
		try {
			$c->removeInventory($_GET['oldInventoryName']);
		} catch (Exception $e){
			echo $e->getMessage() . "<br>";
			echo "<a href= \"../index.php\">Back</a>" . "<br>";
		}
		break;
	case "12/10":
		try {
			$c->viewInventoryItem($_GET['inventoryName']);
		} catch (Exception $e){
			echo $e->getMessage() . "<br>";
			echo "<a href= \"../index.php\">Back</a>" . "<br>";
		}
		break;
}