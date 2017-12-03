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
	require_once $my_dir . '/../model/Report.php';
	require_once $my_dir . '/../model/ProgressUpdate.php';
	require_once $my_dir . '/../model/Expense.php';
	require_once $my_dir . '/../model/Equipment.php';
	require_once $my_dir . '/../model/FundingAccount.php';
		
class FundingController {
	
	protected $urlms;
	/*
	 * Constructor
	 */
	public function __construct($urlms){
		$this->urlms = $urlms;
	}
	
	function addAccount($type, $balance){
		if($type == null || strlen($type) == 0){
			throw new Exception ("Please enter an funding account type.");
		}
		else if($balance == null || strlen($balance) == 0){
			throw new Exception ("Please enter balance.");
		}
		else{
			$urlms = $this->urlms;
			$urlmsLab = $urlms->getLab_index(0);
 			$newFundingAccount = new FundingAccount($type, $balance, $urlmsLab);
 			$urlmsLab->addFundingAccount($newFundingAccount);

 			$newFundingAccount->addExpense(new Expense($balance, "Initial Balance", $newFundingAccount));
 			
 			$persistence = new Persistence();
 			$persistence->writeDataToStore($urlms);
		}
		?>
		<!-- Add back button to page -->
		<HTML>
			<p>New account successfully added!</p>
			<a href="../View/FundingView.html">Back</a>
		</HTML><?php
	}
	
	function generateFinancialReport($accountType){
		$urlms = $this->urlms;
		$fundingAccount = $this->findFundingAccount($accountType);

		$expenses = $fundingAccount->getExpenses();
		foreach ($expenses as $e){
			echo "Type: " . $e->getType() . " | Amount: " . $e->getAmount() . "<br>";
		}
		
		session_start();
		$_SESSION['fundingAccount'] = $fundingAccount;
		$_SESSION['urlms'] = $urlms;
		?>
		<HTML>
			<form action="../Controller/InfoUpdater.php" method="get">
			<br>
			<h3>Edit Expense</h3>
			<input type="hidden" name="action" value="editExpense" />
			Expense Type: <input type="text" name="expensename" value=""/>
			New Expense Type: <input type="text" name="newexpensename" value=""/>
			New Amount: <input type="text" name="newexpenseamount" value=""/><br>
			New Date: <input type="text" name="newexpensedate" value=""/><br>
			<input type="submit" value="Edit expense!" />
 			<br>
		</form>
		</HTML>
		<!-- Add back button to page -->
		<HTML>
			<a href="../View/FundingView.html">Back</a>
		</HTML><?php
	}
	
	function removeAccount($type){
		$urlms = $this->urlms;
		$urlmsLab = $urlms->getLab_index(0);
		$fundingAccount = $this->findFundingAccount($type);
		$fundingAccount->delete();
		$persistence = new Persistence();
		$persistence->writeDataToStore($urlms);
		?>
		<!-- Add back button to page -->
		<HTML>
			<p>Funding account removed!</p>
			<a href="../View/FundingView.html">Back</a>
		</HTML><?php
	}
	
	function getAccounts(){
		// Get staff members from urlms
		$accounts = $this->urlms->getLab_index(0)->getFundingAccounts();
		foreach ($accounts as $a){
			echo $a->getType() . " " . $a->getBalance() . "<br>";
		}
		echo "<a href= \"../View/FundingView.html\">Back</a>" . "<br>";
	}
	
	function getNetBalance(){
		$netBalance = 0;
		$accounts = $this->urlms->getLab_index(0)->getFundingAccounts();
		foreach ($accounts as $a){
			$netBalance = $netBalance + $a->getBalance();
		}
		echo "Net Balance of Lab: " . $netBalance . "<br>";
		echo "<a href= \"../View/FundingView.html\">Back</a>" . "<br>";
	}
	
	function viewAccount($type){
		$urlms = $this->urlms;
		//echo $urlms->getLab_index(0)->numberOfFundingAccounts();
		$fundingAccount = $this->findFundingAccount($type);
		session_start();
		$_SESSION['fundingaccount'] = $fundingAccount;
		$_SESSION['urlms'] = $urlms;
		echo "Type: " . $fundingAccount->getType();
		echo "<br>";
		echo "Balance: " . $fundingAccount->getBalance();
		echo "<br>";
		?>
		<HTML>
			<form action="../Controller/InfoUpdater.php" method="get">
			<br>
			<h3>Edit Account</h3>
			<input type="hidden" name="action" value="editAccount" />
			New Name: <input type="text" name="editedaccountname" value="<?php echo $fundingAccount->getType();?>"/>
			<input type="submit" value="Edit account!" />
 			<br>
		</form>
		</HTML>

		<!-- Add back button to page -->
		<HTML>
			<a href="../View/FundingView.html">Back</a>
		</HTML><?php
	}
	
	function addTransaction($labtype, $expensetype, $amount, $type, $date){
		if($amount == null || strlen($amount) == 0){
			throw new Exception ("Please enter a amount.");
		} else {
			$urlms = $this->urlms;
			$urlmsLab = $urlms->getLab_index(0);
			$fundingAccount = $this->findFundingAccount($labtype);
			
			$newExpense = new Expense($amount, $expensetype, $fundingAccount);
			
			$fundingAccount->addExpense($newExpense);
			
			if($type == "expense"){
				$fundingAccount->setBalance($fundingAccount->getBalance() - $newExpense->getAmount());
				$newExpense->setAmount(-$amount);
			} else{
				$fundingAccount->setBalance($fundingAccount->getBalance() + $newExpense->getAmount());
			}
			// Write data
			$persistence = new Persistence();
			$persistence->writeDataToStore($urlms);
			
			?>
			<!-- Add back button to page -->
			<HTML>
				<p>New transation item successfully added!</p>
				<a href="../View/FundingView.html">Back</a>
			</HTML><?php
		}
	}
	
	function findFundingAccount($type){
		if($type == null || strlen($type) == 0){
			throw new Exception ("Please enter an funding account type.");
		} else{
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
	
}
?>