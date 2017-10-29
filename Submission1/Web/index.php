<html>
	<head>
		<title>URLMS</title>
	</head>
	<body>
		<h1>University Research Lab Management System</h1>
		<br><br>
		<form action="controller/controller.php" method="get">
			<br>
			<h3>View Staff List</h3>
			<input type="hidden" name="action" value="9/10" />
 			<input type="submit" value="View Staff!" />
 			<br>
		</form>
		
		<form action="controller/controller.php" method="get">
			<br>
			<h3>Add Staff Member</h3>
			<input type="hidden" name="action" value="10/10" />
			<input type="text" name="newstaffname" value=""/>
 			<input type="submit" value="Add staff!" />
 			<br>
		</form>
		
		<form action="controller/controller.php" method="get">
			<br>
			<h3>Remove Staff Member</h3>
			<input type="hidden" name="action" value="11/10" />
			<p>Name: </p><input type="text" name="oldstaffname" value=""/><br>
			<p>ID: </p><input type="text" name="oldstaffid" value=""/>
 			<input type="submit" value="Remove staff!" />
 			<br>
		</form>
	</body>
<html/>
