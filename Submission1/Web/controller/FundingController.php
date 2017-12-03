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
	
	// start session
	session_start();
	
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
		
class FundingController {
	
	protected $urlms;
	/*
	 * Constructor
	 */
	public function __construct($urlms){
		$this->urlms = $urlms;
	}
	
	function addAccount($type, $balance){
		$urlms = $this->urlms;
		$urlmsLab = $urlms->getLab_index(0);
 		$newFundingAccount = new FundingAccount($type, $balance, $urlmsLab);
 		$urlmsLab->addFundingAccount($newFundingAccount);
// 		$this->urlms->getLab_index(0)->addFundingAccount($newFundingAccount);
		//$urlms->getLab_index(0)->addFundingAccount($type, $balance, $urlms->getLab_index(0));
 		$persistence = new Persistence();
 		$persistence->writeDataToStore($urlms);
		?>
		<!-- Add back button to page -->
		<HTML>
			<p>New account successfully added!</p>
			<a href="../View/FundingView.html">Back</a>
		</HTML><?php
	}
	
	function viewNetBalance($type){
		$urlms = $this->urlms;
////	echo $urlms->getLab_index(0)->numberOfFundingAccounts();
		//$fundingAccount = $this->findFundingAccount($type);
		//echo $fundingAccount->getBalance();
////	echo $urlms->getLab_index(0)->getFundingAccount_index($index)->getBalance();
	 
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
			for ($i = 0; $i < sizeof($items); $i++){
				if($type == $accounts{$i}->getType()){
					$fundingAccount = $accounts{$i};
				}
			}
			if($fundingAccoung == null){
				throw new Exception ("Funding account not found.");
			}
		}
		return $fundingAccount;
	}
	
}
?>