<?php
	
	class Persistence{
		
		private $filename;
		
		/*
		 * Constructor
		 */
		function __construct($filename = 'data.txt') {
			$this->filename = $filename;
		}
		
		/*
		 * Load data from file
		 */
		function loadDataFromStore() {
			// check if file exists, if yes, unserialize data file
			if (file_exists($this->filename)) {
				$str = file_get_contents($this->filename);
				$urlms = unserialize($str);
			} else {
				// if doesn't exist, create new instances of URLMS and Lab and add lab to URLMS
				$urlms = new URLMS();
				$lab = Lab::newInstance(0, $urlms);
				$urlms->addLab($lab);
			}
			// return urlms
			return $urlms;
		}
		
		/*
		 * Write data to file
		 */
		function writeDataToStore($rm) {
			// serialize data and put content to data file
			$str = serialize($rm);
			file_put_contents($this->filename, $str);
		}
	}
?>