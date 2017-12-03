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
// 		$this->urlms->getLab_index(0)->addFundingAccount($newFundingAccount);
		//$urlms->getLab_index(0)->addFundingAccount($type, $balance, $urlms->getLab_index(0));
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
		// Get staff members from urlms
		$netBalance = 0;
		$accounts = $this->urlms->getLab_index(0)->getFundingAccounts();
		foreach ($accounts as $a){
			$a->getBalance();
			$netBalance = $netBalance + $a;
		}
		echo $netBalance;
		echo "<a href= \"../View/FundingView.html\">Back</a>" . "<br>";
	}
	
	function viewAccount($type){
		$urlms = $this->urlms;
		//echo $urlms->getLab_index(0)->numberOfFundingAccounts();
		$fundingAccount = $this->findFundingAccount($type);
		echo $fundingAccount->getType();
		echo "<br>";
		echo $fundingAccount->getBalance();
		echo "<br>";
		?>
		<!-- Add back button to page -->
		<HTML>
			<a href="../View/FundingView.html">Back</a>
		</HTML><?php
	}
	
	function addTransaction($amount, $type){
		if($amount == null || strlen($amount) == 0){
			throw new Exception ("Please enter a amount.");
		} else {
			$urlms = $this->urlms;
			
			$currentBalance = $urlms->getLab_index(0)->getFundingAccount_index(0)->getBalance();
			
			if($type == "expense"){
				$urlms->getLab_index(0)->getFundingAccount_index(0)->setBalance($currentBalance - $amount);
			} else{
				$urlms->getLab_index(0)->getFundingAccount_index(0)->setBalance($currentBalance + $amount);
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