<?php
// 	include '/../model/URLMS.php';
// 	include '/../model/StaffManager.php';
// 	include '/../model/StaffMember.php';
// 	include '/../model/InventoryManager.php';
// 	include '/../model/FundingManager.php';
// 	include '/../model/InventoryItem.php';
// 	include '/../model/SupplyType.php';
// 	include '/../model/ResearchRole.php';
// 	include '/../model/ResearchAssociate.php';
// 	include '/../model/ResearchAssistant.php';
// 	include '/../model/Report.php';
// 	include '/../model/ProgressUpdate.php';
// 	include '/../model/Expense.php';
// 	include '/../model/Equipment.php';
// 	include '/../model/FundingAccount.php';
	
	class Persistence{
		
		private $filename;
		
		function __construct($filename = 'data.txt') {
			$this->filename = $filename;
		}
		
		function loadDataFromStore() {
			if (file_exists($this->filename)) {
				$str = file_get_contents($this->filename);
				$urlms = unserialize($str);
			} else {
				$urlms = URLMS::newInstance(0);
			}
			return $urlms;
		}
		
		function writeDataToStore($rm) {
			$str = serialize($rm);
			file_put_contents($this->filename, $str);
		}
		
		
// 		public function __construct(){}
		
// 		/*
// 		 * Load XML file
// 		 */
// 		function loadData($string){
// 			$xml = simplexml_load_file($string) or die ("Error: Cannot create object");
			
// 			$urlms = URLMS::newInstance(0);
// 			foreach($xml->children() as $staffs){ //TODO MODIFY TO INCLUDE ALL
// 				$member = new StaffMember($staffs->name, $staffs['id'], $urlms->getStaffManager());
// 				$urlms->getStaffManager()->addStaffMember($member);
// 			}
			
// 			return $urlms;
// 		}
		
// 		/*
// 		 * Save to XML file
// 		 */
// 		function saveData($xml, $string){
// 			file_put_contents($string, $xml->asXML());
// 		}
	}
?>