<?php
	$my_dir = dirname(__FILE__);
	require_once $my_dir . '/../persistence/persistence.php';
	require_once $my_dir . '/../model/URLMS.php';
	require_once $my_dir . '/../model/Lab.php';
	require_once $my_dir . '/../model/StaffMember.php';
	require_once $my_dir . '/../model/InventoryItem.php';
	require_once $my_dir . '/../model/SupplyType.php';
	require_once $my_dir . '/../model/ResearchRole.php';
	require_once $my_dir . '/../model/ResearchAssociate.php';
	require_once $my_dir . '/../model/ResearchAssistant.php';
	require_once $my_dir . '/../model/FinancialReport.php';
	require_once $my_dir . '/../model/ProgressUpdate.php';
	require_once $my_dir . '/../model/Expense.php';
	require_once $my_dir . '/../model/Equipment.php';
	require_once $my_dir . '/../model/FundingAccount.php';
	
	require('InventoryController.php');
	require('FundingController.php');
	
	// start session
 	session_start();
	
	$persistence = new Persistence();
	$urlms = $persistence->loadDataFromStore();
	
	$iu = new InfoUpdater($urlms);
	
	// Check which button was clicked by user
	// Run appropriate controller method with respect to user request
	switch($_GET['action']){
		case "editInventoryItem":
			try{
				$iu->updateInventory($_GET['editedinventoryname'],$_GET['editedinventorycost'], $_GET['editedinventorycat'], $_GET['isdamaged'], $_GET['editedsupplyquantity']);		
			} catch (Exception $e){
				echo $e->getMessage() . "<br>";
				echo "<a href= \"InventoryView.php\">Back</a>" . "<br>";
			}
			break;
		case "editStaffMember":
			try{
				$iu->updateStaffMember($_GET['editedstaffname'],$_GET['editedstaffid'], $_GET['editedstaffsalary']);
			
				if(!empty($_GET['role'])){
					$iu->updateRoles($_GET['role']);
				}
				else{
					$iu->updateRoles([]);
				}
				if(!empty($_GET['newProgressUpdate'])){
					$iu->addProgressUpdate($_GET['newProgressUpdate'],$_GET['date']);
				}
			} catch (Exception $e){
				echo $e->getMessage() . "<br>";
				echo "<a href= \"StaffView.php\">Back</a>" . "<br>";
			}
			break;
		case "editAccount":
			try {$iu->updateAccount($_GET['editedaccountname']);
			}catch (Exception $e){
				echo $e->getMessage() . "<br>";
				echo "<a href= \"FundingView.php\">Back</a>" . "<br>";
			}
			break;
		case "editExpense":
			try {$iu->updateExpense($_GET['expensename'], $_GET['newexpensename'],$_GET['newexpenseamount'], $_GET['newexpensedate']);
			}catch (Exception $e){
				echo $e->getMessage() . "<br>";
				echo "<a href= \"FundingView.php\">Back</a>" . "<br>";
			}
			break;
	}
	
	class InfoUpdater {
		
		protected $urlms;
		/*
		 * Constructor
		 */
		public function __construct($urlms){
			$this->urlms = $urlms;
		}
		
		function updateInventory($name, $cost, $category, $isDamaged, $quantity){
			$urlms = $_SESSION['urlms'];
			$inventoryItem = $_SESSION['inventoryitem'];
			
			$inventoryItem->setName($name);
			$inventoryItem->setCost($cost);
			$inventoryItem->setCategory($category);

			if($isDamaged == "damaged"){
				$inventoryItem->setIsDamaged(true);
			}
			else if($isDamaged == "notdamaged"){
				$inventoryItem->setIsDamaged(false);
			}
			else {
				if($quantity != null){
					$inventoryItem->setQuantity($inventoryItem->getQuantity() + $quantity);
					if($quantity>0){
						date_default_timezone_set('America/New_York');
						$date = date('m/d/Y', time());
						$fundC = new FundingController($urlms);
						$fundC->addTransaction("Supply Funding", $inventoryItem->getName() . " bought", $quantity * $inventoryItem->getCost(), "expense", $date);
					}
				}
			}
			
			$persistence = new Persistence();
			$persistence->writeDataToStore($urlms);
			
			echo "Inventory item updated succesfully! <br>";
			echo "<a href= \"../view/InventoryView.php\">Back</a>" . "<br>";
		}
		
		function updateStaffMember($name, $id, $salary){
			$urlms = $_SESSION['urlms'];
			$staffMember = $_SESSION['staffmember'];
			
			$staffMember->setName($name);
			$staffMember->setId($id);
			$staffMember->setWeeklySalary($salary);
			
			$persistence = new Persistence();
			$persistence->writeDataToStore($urlms);
			
			echo "Staff member updated succesfully! <br>";
			echo "<a href= \"../view/StaffView.php\">Back</a>" . "<br>";	
		}
		
		function updateRoles($roles){
			$urlms = $_SESSION['urlms'];
			$staffMember = $_SESSION['staffmember'];
			
			
			foreach ($staffMember->getResearchRoles() as $r){
				$r->delete();
			}
			
			foreach ($roles as $r){
				switch ($r){
					case "ResearchAssociate":
						$staffMember->addResearchRole(new ResearchAssociate("", $staffMember));
						break;
					case "ResearchAssistant":
						$staffMember->addResearchRole(new ResearchAssistant("", $staffMember));
						break;
				}
			}	
			
			$persistence = new Persistence();
			$persistence->writeDataToStore($urlms);
		}
		
		function addProgressUpdate($desc, $date){
			$urlms = $_SESSION['urlms'];
			$staffMember = $_SESSION['staffmember'];
			
			$staffMember->addProgressUpdate(new ProgressUpdate($date, $desc,$staffMember));
			
			$persistence = new Persistence();
			$persistence->writeDataToStore($urlms);
		}
		
		function updateAccount($newType){
			$fundingAccount = $_SESSION['fundingaccount'];
			$urlms = $_SESSION['urlms'];
			
			$fundingAccount->setType($newType);
			
			$persistence = new Persistence();
			$persistence->writeDataToStore($urlms);
			
			echo "Account updated successfully! <br>";
			echo "<a href= \"../view/FundingView.html\">Back</a>" . "<br>";	
		}
		
		function updateExpense($expenseType, $newExpenseType, $newAmount, $newDate){
			$fundingAccount = $_SESSION['fundingAccount'];
			$urlms = $_SESSION['urlms'];
			$expense = null;
			
			$fundingAccountBalance = $fundingAccount->getBalance();
			
			$expenses = $fundingAccount->getExpenses();
			foreach ($expenses as $e){
				if($expenseType == $e->getType()){
					$expense = $e;
				}
			}
			if($expense == null){
				throw new Exception ("Please enter a valid expense type.");
			}
			
			$oldExpenseAmount = $expense->getAmount();
			
			$expense->setType($newExpenseType);
			$expense->setAmount($newAmount);
// 			$expense->setDate($newDate);

			
			$expenseAmountDiff = $expense->getAmount() - $oldExpenseAmount;			
			$fundingAccount->setBalance($fundingAccountBalance + $expenseAmountDiff);
			
			$persistence = new Persistence();
			$persistence->writeDataToStore($urlms);
			
			echo "Expense updated successfully! <br>";
			echo "<a href= \"../view/FundingView.html\">Back</a>" . "<br>";	
		}
		
}