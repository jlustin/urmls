<?php $my_dir = dirname(__FILE__);
	require_once $my_dir . '/../persistence/persistence.php';
	require_once $my_dir . '/../model/URLMS.php';
	require_once $my_dir . '/../model/Lab.php';
	require_once $my_dir . '/../model/StaffMember.php';
	require_once $my_dir . '/../model/ResearchRole.php';
	require_once $my_dir . '/../model/ResearchAssociate.php';
	require_once $my_dir . '/../model/ResearchAssistant.php';
	require_once $my_dir . '/../model/ProgressUpdate.php';
?>

<html>
	<head>
		<title>URLMS - Staff</title>
		
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
		<link rel="stylesheet" type="text/css" href="TableView.css">
		
	</head>
	<body>
		<h1 align="center"><a href="../index.php" style="color: black;text-decoration: none;">University Research Lab Management System</a></h1>
		<h2 align="center">Staff</h2>
		<br><br>
		
		<form action="../controller/StaffController.php" method="get">
			<br>
			<h3>View Staff List</h3>
			<input type="hidden" name="action" value="9/10" />
 			<input type="submit" value="View Staff!" />
 			<br>
		</form>
		
		<form action="../controller/StaffController.php" method="get">
			<br>
			<h3>Add Staff Member</h3>
			<input type="hidden" name="action" value="10/10" />
			<input type="text" name="newstaffname" value=""/>
 			<input type="submit" value="Add staff!" />
 			<br>
		</form>
		
		<form action="../controller/StaffController.php" method="get">
			<br>
			<h3>Remove Staff Member</h3>
			<input type="hidden" name="action" value="11/10" />
			Name: <input type="text" name="oldstaffname" value=""/>
			ID: <input type="text" name="oldstaffid" value=""/>
 			<input type="submit" value="Remove staff!" />
 			<br>
		</form>
		
		<form action="../controller/StaffController.php" method="get">
			<br>
			<h3>View and Edit Staff Member Record</h3>
			<input type="hidden" name="action" value="12/10" />
			Name: <input type="text" name="staffname" value=""/>
			ID: <input type="text" name="staffid" value=""/>
 			<input type="submit" value="View record!" />
 			<br>
		</form>
		
		<br>
		<a href="../index.php">Back to homepage</a>

		<br><br>

		<table class="table" style="width: 100%;">
			
			<thread>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Role(s)</th>
				<th>Progress Updates</th>
			</tr>
			</thread>
			<?php 
			
			$urlms = (new Persistence("../controller/data.txt"))->loadDataFromStore();
			
			
			foreach ($urlms->getLab_index(0)->getStaffMembers() as $member){
				echo "<tr><td>" . $member->getId() . "</td><td>" . $member->getName() . "</td></tr>";
			}?>
			
		</table>
		
		<br><br>
		
		<div class="btn-group" role="group" aria-label="Basic example">
	  <button type="button" class="btn btn-secondary">Left</button>
	  <button type="button" class="btn btn-secondary">Middle</button>
	  <button type="button" class="btn btn-secondary">Right</button>
		</div>
			
</body>
</html>

