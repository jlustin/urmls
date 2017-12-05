<?php

$my_dir = dirname(__FILE__);
require_once $my_dir . '/../persistence/persistence.php';
require_once $my_dir . '/../controller/FundingController.php';

$persistence = new Persistence();
$urlms = $persistence->loadDataFromStore();

$invC = new FundingController($urlms);
// Check which button was clicked by user
// Run appropriate controller method with respect to user request
switch($_GET['action']){
	case "1/10":
		try{
			$invC->addAccount($_GET['addtype'], $_GET['addbalance']);
		} catch (Exception $e){
			echo $e->getMessage() . "<br>";
			echo "<a href= \"FundingView.php\">Back</a>" . "<br>";
		}
		break;
	case "2/10":
		try{
			$invC->viewAccount($_GET['viewtype']);
		} catch (Exception $e){
			echo $e->getMessage() . "<br>";
			echo "<a href= \"FundingView.php\">Back</a>" . "<br>";
		}
		break;
	case "3/10":
		try{
			if(!isset($_GET['type'])){
				echo "Please chose a type of transaction<br>";
				echo"<a href= \"FundingView.php\">Back</a><br>";
			}else $invC->addTransaction($_GET['account'], $_GET['expensetype'], $_GET['amount'], $_GET['type'], $_GET['date']);
		} catch (Exception $e){
			echo $e->getMessage() . "<br>";
			echo "<a href= \"FundingView.php\">Back</a>" . "<br>";
		}
		break;
	case "4/10":
		try{
			$invC->removeAccount($_GET['removetype']);
		} catch (Exception $e){
			echo $e->getMessage() . "<br>";
			echo "<a href= \"FundingView.php\">Back</a>" . "<br>";
		}
		break;
	case "9/10":
		$invC->getAccounts();
		break;
	case "10/10":
		$invC->getNetBalance();
		break;
	case "11/10":
		try{
			$invC->generateFinancialReport($_GET['accounttype']);
		} catch (Exception $e){
			echo $e->getMessage() . "<br>";
			echo "<a href= \"FundingView.php\">Back</a>" . "<br>";
		}
		break;
	case "5/10":
		$invC->payDay();
		break;
}