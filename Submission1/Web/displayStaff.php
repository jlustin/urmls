<?php
	$xml=simplexml_load_file("staff.xml") or die ("Error: Cannot create object");
	foreach($xml->children() as $staffs){
		echo $staffs['id'] . "    " . $staffs->name . "<br>";
	}
?>