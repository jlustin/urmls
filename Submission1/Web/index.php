<html>
	<head>
		<title>URLMS</title>
	</head>
	<body>
		<h1>University Research Lab Management System</h1>
		<br><br>
		<form action="controller/controller.php" method="get">
			<br>
			<p>View Staff List</p>
			<input type="hidden" name="action" value="9/10" />
 			<input type="submit" value="View Staff!" />
 			<br>
		</form>
		
		<form action="controller/controller.php" method="get">
			<br>
			<p>Add Staff Member</p>
			<input type="hidden" name="action" value="10/10" />
			<input type="text" name="newstaffname" value=""/>
 			<input type="submit" value="Add staff!" />
 			<br>
		</form>
	</body>
<html/>
