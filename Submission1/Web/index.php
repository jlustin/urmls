<?php
  if (!empty($_GET['act'])) {
  	$xml=simplexml_load_file("staff.xml") or die ("Error: Cannot create object");
  	foreach($xml->children() as $staffs){
  		echo $staffs['id'] . "    " . $staffs->name . "<br>";
  	}
  } else {
?>
<html>
	<head>
		<title>Staff Manager</title>
	</head>
	<body>
		<h1>Click to View Staff List!!!</h1>
		<br><br><br>
		<form action="index.php" method="get">
			<p>First way: php script in a different file</p>
			<input type="button" value="View Staff1!" onclick="location='displayStaff.php'" />
			<br>
			<p>Second way: php script within index.php</p>
			<input type="hidden" name="act" value="run" />
 			<input type="submit" value="View Staff2!" />
 			<br>
		</form>
	</body>
<html/>
<?php
  }
?>






