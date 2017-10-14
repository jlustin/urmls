<?php
	include 'model/*.php';
	
	
	/*
	 * Load XML file
	 */
	function loadData($string){
		$xml=simplexml_load_file($string) or die ("Error: Cannot create object");
		
		$urlms = newInstance(0);
		foreach($xml->children() as $staffs){ //TO BE MODIFIED
			$member = new StaffMember($urlms->StaffManager);
		}
		
		return $urlms;
	}
	
	/*
	 * Save to XML file
	 */
	function saveData($xml, $string){
		file_put_contents($string, $xml->asXML());
	}
?>