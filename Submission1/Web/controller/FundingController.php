<?php
	$my_dir = dirname(__FILE__);
	require_once $my_dir . '/../persistence/persistence.php';
	require_once $my_dir . '/../controller/Controller.php';
	require_once $my_dir . '/../model/URLMS.php';
	require_once $my_dir . '/../model/Lab.php';
	require_once $my_dir . '/../model/StaffMember.php';
	require_once $my_dir . '/../model/InventoryItem.php';
	require_once $my_dir . '/../model/SupplyType.php';
	require_once $my_dir . '/../model/FinancialReport.php';
	require_once $my_dir . '/../model/Expense.php';
	require_once $my_dir . '/../model/Equipment.php';
	require_once $my_dir . '/../model/FundingAccount.php';
 	if(!isset($_SESSION)) 
    { 
        session_start(); 
    } 
	?>	
	<html>
		<head>
			<title>URLMS</title>
			<!-- https://getbootstrap.com/docs/4.0/content/tables/ -->
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
			<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
			<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
			<link rel="stylesheet" type="text/css" href="style/style.css">
		</head>	
	</html>
	<?php

class FundingController extends Controller {
	
	protected $urlms;
	protected $persistence;
	/*
	 * Constructor
	 */
	public function __construct($urlms, $persistence){
		$this->urlms = $urlms;
		$this->persistence = $persistence;
	}
	
	function addAccount($type, $balance){
		if($type == null || strlen($type) == 0 || !$this->isValidStr($type)){
			throw new Exception ("Please enter a valid funding account type.");
		}elseif($type == "Staff Funding" || $type == "Equipment Funding" || $type == "Supply Funding"){
			throw new Exception ("Can't add account with this name!");
		}
		else if($balance == null || strlen($balance) == 0 || !is_numeric($balance)){
			throw new Exception ("Please enter a valid balance.");
		}
		else{
			$urlms = $this->urlms;
			$urlmsLab = $urlms->getLab_index(0);
 			$newFundingAccount = new FundingAccount($type, $balance, $urlmsLab);
 			$urlmsLab->addFundingAccount($newFundingAccount);

 			date_default_timezone_set('America/New_York');
 			$date = date('m/d/Y', time());
 			$newFundingAccount->addExpense(new Expense($balance, $date, "Initial Balance", $newFundingAccount));
 			
 			//$persistence = new Persistence();
 			$this->persistence->writeDataToStore($urlms);
		}
		?>
		<!-- Add back button to page -->
		<HTML>
			<meta http-equiv="refresh" content="0; URL='../view/FundingView.php'" />
			<p>New account successfully added!</p>
			<a href="../view/FundingView.php">Back</a>
		</HTML><?php
	}
	
	function generateFinancialReport($accountType){
		$count = 0;
		$urlms = $this->urlms;
		$fundingAccount = $this->findFundingAccount($accountType);
		
		echo "
		<div class=\"container\">
			<h3>".$accountType."</h3> 
		<table class=\"table table-hover\" style=\"width: 100%;\">
		
		<thread>
		<tr>
		<th>Type</th>
		<th>Amount</th>
		<th>Date</th>
		
		</tr>
		</thread>
		<tbody>";
		
		$expenses = $fundingAccount->getExpenses();
		foreach ($expenses as $e){
			//echo "Type: " . $e->getType() . " | Amount: " . $e->getAmount() . " | Date: ". $e->getDate() ."<br>";
		$count ++;
		echo "<tr>
				<td>" .$e->getType()."</td>
				<td>$". number_format($e->getAmount(), 2, "." , "," ) ."</td>
				<td>" . $e->getDate() . "</td>
			</tr>";}
		echo "</tbody></table>";
		$_SESSION['fundingAccount'] = $fundingAccount;
		$_SESSION['urlms'] = $urlms;
		?>
		<html>
		<div class="container">
			<form action="../controller/InfoUpdater.php" method="get">
			<div class="form-group">
				<br>
				<h3>Edit Expense</h3>
				<input type="hidden" name="action" value="editExpense" />
					<div class="row">
						<div class="col-sm-6">
							<label for="ExpenseType">Expense Type</label> 
							<input type="text" class="form-control" name="expensename" id="expenseName" aria-describedby="nameHelp" placeholder="Enter Expense Type"> 
							<small id="nameHelp" class="form-text text-muted">Enter old type of expense.</small> <br>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6">
							<label for="newExpenseType">New Expense Type</label> 
							<input type="text" class="form-control" name="newexpensename" id="expenseName" aria-describedby="nameHelp" placeholder="Enter New Expense Type"> 
							<small id="nameHelp" class="form-text text-muted">Enter new type of expense.</small> <br>
						</div>
						<div class="col-sm-6">
							<label for="newAmount">New Amount</label> 
							<input type="text" class="form-control" name="newexpenseamount" id="expenseAmount" aria-describedby="nameHelp" placeholder="Enter New Expense Amount"> 
							<small id="nameHelp" class="form-text text-muted">Enter new amount of expense.</small> <br>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6">
							<label for="ExpenseType">New Date</label> 
							<input type="text" class="form-control" name="newexpensedate" id="expenseName" aria-describedby="nameHelp" placeholder="Enter Expense Date"> 
							<small id="nameHelp" class="form-text text-muted">Enter new date of expense.</small> <br>
						</div>
					</div>
				<input class="btn btn-danger" type="submit" value="Edit expense!" />
			</div>
				<br>
			</form>
			
			<div class="row">
				<div class="col-sm-2">
					<a href="../view/FundingView.php" style="color: white; text-decoration: none;">
						<button type="button" class="btn btn-danger" data-toggle="tooltip"
							data-placement="bottom" title="Go back to homepage">Back</button>
					</a>
				</div>
			</div>
		</div>
	</html><?php
		return $count;
	}
	
	function removeAccount($type){
		if($type == "Staff Funding" || $type == "Equipment Funding" || $type == "Supply Funding"){
			throw new Exception ("Can't delete this account!");
		}
		$urlms = $this->urlms;
		$urlmsLab = $urlms->getLab_index(0);
		$fundingAccount = $this->findFundingAccount($type);
		$fundingAccount->delete();
		$this->persistence->writeDataToStore($urlms);
		?>
		<!-- Add back button to page -->
		<HTML>
			<meta http-equiv="refresh" content="0; URL='../view/FundingView.php'" />
			<p>Funding account removed!</p>
			<a href="../view/FundingView.php">Back</a>
		</HTML><?php
	}
	
	function getAccounts(){
		// Get staff members from urlms
		$accounts = $this->urlms->getLab_index(0)->getFundingAccounts();
		$count = 0;
		foreach ($accounts as $a){
			echo $a->getType() . " " . number_format($a->getBalance(), 2, "." , "," ) . "<br>";
			$count ++;
		}?>
		<html>
			<div class="container">
				<div class="row">
					<div class="col-sm-2">
						<a href="../view/FundingView.php" style="color: white; text-decoration: none;">
							<button type="button" class="btn btn-danger" data-toggle="tooltip"
							data-placement="bottom" title="Go back to homepage">Back</button>
						</a>
					</div>
				</div>
			</div>
		</html><?php 
		return $count;
	}
	
	function getNetBalance(){
		$netBalance = 0;
		$accounts = $this->urlms->getLab_index(0)->getFundingAccounts();
		foreach ($accounts as $a){
			$netBalance = $netBalance + $a->getBalance();
		}
		echo "Net Balance of Lab: $" . number_format($netBalance, 2, "." , "," ) . "<br>";
		echo "<a href= \"../view/FundingView.php\">Back</a>" . "<br>";
		
		return $netBalance;
	}
	
	function viewAccount($type){
		if($type == "Staff Funding" || $type == "Equipment Funding" || $type == "Supply Funding"){
			throw new Exception ("Can't view this account!");
		}
		$urlms = $this->urlms;
		$fundingAccount = $this->findFundingAccount($type);
		//session_start();
		$_SESSION['fundingaccount'] = $fundingAccount;
		$_SESSION['urlms'] = $urlms;
		?>
		
		<html>
			<div class="container">
				<h3>Account Summary</h3>
				<br>
				<label for="AccountType">Account Type :</label> <?php echo $fundingAccount->getType();?>
				<br>
				<label for="AccountBalance">Account Balance :</label> <?php echo "$ " . number_format($fundingAccount->getBalance(), 2, "." , "," );?> 
				<br>
			
				<form action="../controller/InfoUpdater.php" method="get">
				<div class="form-group">
					<br>
					<h3>Edit Account</h3>
					<input type="hidden" name="action" value="editAccount" />
						<div class="row">
							<div class="col-sm-6">
								<label for="NewName">New Name</label> 
								<input type="text" class="form-control" name="editedaccountname" id="expenseName" aria-describedby="nameHelp" value="<?php echo $fundingAccount->getType();?>"/> 
								<small id="nameHelp" class="form-text text-muted">Enter new account name.</small> <br>
							</div>
							</div>
							<input class="btn btn-danger" type="submit" value="Edit account!" />
					</div>
						<br>
					</form>
					<div class="row">
						<div class="col-sm-2">
							<a href="../view/FundingView.php" style="color: white; text-decoration: none;">
								<button type="button" class="btn btn-danger" data-toggle="tooltip"
									data-placement="bottom" title="Go back to homepage">Back</button>
							</a>
						</div>
					</div>
			</div>
		</html><?php
		return $fundingAccount;
	}
	
	function addTransaction($account, $expensetype, $amount, $type, $date){
		if($expensetype == null || strlen($expensetype) == 0){
			throw new Exception ("Please enter a valid transaction type.");
		}
		else if ($amount == null || strlen($amount) == 0 || !(is_numeric($amount))){
			throw new Exception ("Please enter a valid amount.");
		} 
		else if ($date == null){
			throw new Exception ("Please enter a date.");
		} else {
			$urlms = $this->urlms;
			$urlmsLab = $urlms->getLab_index(0);
			$fundingAccount = $this->findFundingAccount($account);
			
			$newExpense = new Expense($amount, $date, $expensetype, $fundingAccount);
			
			$fundingAccount->addExpense($newExpense);
			
			if($type == "expense"){
				$fundingAccount->setBalance($fundingAccount->getBalance() - $newExpense->getAmount());
				$newExpense->setAmount(-$amount);
			} 
			else if ($type == "fund"){
				$fundingAccount->setBalance($fundingAccount->getBalance() + $newExpense->getAmount());
			}
			else{
				throw new Exception ("Please choose a valid type of transaction.");
			}
			// Write data
			//$persistence = new Persistence();
			$this->persistence->writeDataToStore($urlms);
			
			?>
		
			<!-- Add back button to page -->
			<HTML>
				<meta http-equiv="refresh" content="0; URL='../view/FundingView.php'" />
				<p>New transation item successfully added!</p>
				<a href="../view/FundingView.php">Back</a>
			</HTML><?php
		}
	}
	
	function findFundingAccount($type){
		if($type == null || strlen($type) == 0 || !$this->isValidStr($type)){
			throw new Exception ("Please enter a valid funding account type.");
		} else{
			$fundingAccount = null;
			//Find the account
			$accounts = $this->urlms->getLab_index(0)->getFundingAccounts();
			for ($i = 0; $i < sizeof($accounts); $i++){
				if($type == $accounts{$i}->getType()){
					$fundingAccount = $accounts{$i};
				}
			}
			if($fundingAccount == null){
				throw new Exception ("Funding account not found.");
			}
		}
		return $fundingAccount;
	}
	
	function payDay(){
		$totalStaffCost = 0;
		foreach($this->urlms->getLab_index(0)->getStaffMembers() as $member){
			$totalStaffCost += $member->getWeeklySalary();
		}
		date_default_timezone_set('America/New_York');
		$date = date('m/d/Y', time());
		$this->addTransaction("Staff Funding", "PAYDAY", $totalStaffCost, "expense", $date);
		return $totalStaffCost;
	}
}
?>