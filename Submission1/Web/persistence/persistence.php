<?php
	/*
	 * Load XML file
	 */
	function loadData($string){
		$xml=simplexml_load_file($string) or die ("Error: Cannot create object");
		return $xml;
	}
	
	/*
	 * Save to XML file
	 */
	function saveData($xml, $string){
		$xml->asXML($string);
	}
?>