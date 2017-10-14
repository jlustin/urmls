<?php
  include 'controller/controller.php';
  if (!empty($_GET['act'])) {
  	getStaffList();
  }
  else if (!empty($_GET['act2'])){
  	addStaff($_POST["newstaffname"]);
  }
  else {
?>
<html>
	<head>
		<title>URLMS</title>
	</head>
	<body>
		<h1>University Research Lab Management System</h1>
		<br><br>
		<form action="index.php" method="get">
			<br>
			<p>View Staff List</p>
			<input type="hidden" name="act" value="run" />
 			<input type="submit" value="View Staff!" />
 			<br>
		</form>
		<form action="index.php" method="get">
			<br>
			<p>Add Staff Member</p>
			<input type="hidden" name="act2" value="run" />
			<input type="text" name="newstaffname" value=" "/>
 			<input type="submit" value="Add staff!" />
 			<br>
		</form>
	</body>
<html/>
<?php
  }
?>