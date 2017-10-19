<?php
	
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
	}
?>