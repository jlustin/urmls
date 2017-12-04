<?php

$my_dir = dirname(__FILE__);
require_once $my_dir . '/../persistence/persistence.php';
require_once $my_dir . '/../controller/FundingController.php';
require_once $my_dir . '/../model/URLMS.php';
require_once $my_dir . '/../model/Lab.php';
require_once $my_dir . '/../model/Report.php';
require_once $my_dir . '/../model/Expense.php';
require_once $my_dir . '/../model/FundingAccount.php';


?>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>URLMS - Funding</title>
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
	<link rel="stylesheet" type="text/css" href="../style/TableView.css">
	
</head>
<body>
	<h1 align="center"><a href="../index.php" style="color: black;text-decoration: none;">University Research Lab Management System</a></h1>
	
	<form action="FundingRequest.php" method="get">
		<br>
		<h3>View Net Balance</h3>
		<input type="hidden" name="action" value="10/10" />
 		<input type="submit" value="View Net Balance!" />
		<br>
	</form>
	
	<br><br>
	<h2>Funding Accounts Manager</h2>
	
	<form action="FundingRequest.php" method="get">
		<br>
		<h3>View Funding Accounts</h3>
		<input type="hidden" name="action" value="9/10" />
 		<input type="submit" value="View Accounts!" />
		<br>
	</form>
	
	<form action="FundingRequest.php" method="get">
		<br>
		<h3>Add Funding Account</h3>
		<input type="hidden" name="action" value="1/10" />
		Type<input type="text" name="addtype" value=""/><br>
		Balance<input type="text" name="addbalance" value=""/><br>
		<input type="submit" value="Add Funding Account!"/>
		<br>
	</form>
	
	<form action="FundingRequest.php" method="get">
		<br>
		<h3>Remove Funding Account</h3>
		<input type="hidden" name="action" value="4/10" />
		Type<input type="text" name="removetype" value=""/><br>
		<input type="submit" value="Remove Funding Account!"/>
		<br>
	</form>
	
	<form action="FundingRequest.php" method="get">
		<br>
		<h3>Generate Financial Report of an Account</h3>
		Account Type<input type="text" name="accounttype" value=""/><br>
		<input type="hidden" name="action" value="11/10" />
 		<input type="submit" value="View Accounts!" />
		<br>
	</form>
	
	<form action="FundingRequest.php" method="get">
		<br>
		<h3>View/Edit Account and Account Net Balance</h3>
		Type<input type="text" name="viewtype" value=""/><br>
		<input type="hidden" name="action" value="2/10" />
		<input type="submit" value="View Net Balance!"/>
		<br>
	</form>
	

<!-- 	<form action="FundingRequest.php" method="get">
		<br>
		<h3>Edit Funding Account</h3>
		<input type="hidden" name="action" value="12/10" />
		Type<input type="text" name="edittype" value=""/><br>
		<input type="submit" value="Remove Funding Account!"/>
		<br>
	</form>
-->
	<br>
	<hr>
	
	<form action="FundingRequest.php" method="get">
		<br>
		<h3>Add Transaction</h3>
		<input type="hidden" name="action" value="3/10" />
		Account Type<input type="text" name="labtype" value=""/><br>
		Expense Type<input type="text" name="expensetype" value=""/><br>
		Amount<input type="text" name="amount" value=""/><br>
		<input type="radio" name="type" value="fund"/>Fund<br>
		<input type="radio" name="type" value="expense"/>Expense<br>
		Date<input type="text" name="date" value=""/><br>
		<input type="submit" value="Add Transation!!!"/>
		<br>
	</form>
	<a href="../index.php">Back to homepage</a>

	<br><br>
<div class="container-fluid">
		<table class="table table-hover" style="width: 100%;">
			
			<thread>
			<tr>
				<th>Account</th>
				<th>Net balance</th>
				<th>Latest Expense</th>
			</tr>
			</thread>
			<tbody>
			<?php 
			
			$urlms = (new Persistence())->loadDataFromStore();
			
			
			foreach ($urlms->getLab_index(0)->getFundingAccounts() as $account){
				$expense = "";
				if($account->hasExpenses()){
					$expense = $account->getExpense_index(sizeof($account->getExpenses())-1);
					$lastestExpense = $expense->getType() ." ". $expense->getAmount();
				}else{$latestExpense = "None";}
				
				
// 				if($member->hasProgressUpdates()){
// 					$progress = $member->getProgressUpdate_index(sizeof($member->getProgressUpdates())-1)->getDescription();
// 					if(strlen($progress)>50){
// 						$progress = substr($progress, 0, 50) . "...";
// 					}
// 				}else{$progress = "None";}
				
				echo "<tr><td><button type=\"button\" class=\"btn btn-outline-primary\">" . $account->getType() . "</button></td>
					<td>". $account->getBalance() ."</td>
					<td>" . $latestExpense . "</td>
					</tr>";
			}?>
			</tbody>
		</table>
		</div>
		<br><br>
		
			

</body>
</html>