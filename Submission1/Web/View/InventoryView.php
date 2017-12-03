<?php $my_dir = dirname(__FILE__);
	$my_dir = dirname(__FILE__);
	require_once $my_dir . '/../persistence/persistence.php';
	require_once $my_dir . '/../model/URLMS.php';
	require_once $my_dir . '/../model/Lab.php';
	require_once $my_dir . '/../model/InventoryItem.php';
	require_once $my_dir . '/../model/SupplyType.php';
	require_once $my_dir . '/../model/Equipment.php';
	
?>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>URLMS - Inventory</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="../style/TableView.css">
</head>
<body>
	<h1 align="center"><a href="../index.php" style="color: black;text-decoration: none;">University Research Lab Management System</a></h1>
	<h2 align="center">Inventory</h2>
		<br><br>
		<form action="InventoryRequest.php" method="get">
			<br>
			<h3>View Inventory List</h3>
			<input type="hidden" name="action" value="9/10" />
 			<input type="submit" value="View Inventory!" />
 			<br>
		</form>
		
		<form action="InventoryRequest.php" method="get">
			<br>
			<h3>Add Inventory Item</h3>
			<input type="hidden" name="action" value="10/10" />
			<input type="text" name="newInventoryName" value=""/>Name<br>
			<input type="text" name="category" value=""/>Category<br>
			<input type="radio" name="type" value="Equipment"/>Equipment <br>
			<input type="radio" name="type" value="Supply"/>Supply <br>
 			<input type="submit" value="Add inventory!" />
 			<br>
		</form>
		
		<form action="InventoryRequest.php" method="get">
			<br>
			<h3>Remove Inventory Item</h3>
			<input type="hidden" name="action" value="11/10" />
			Name: <input type="text" name="oldInventoryName" value=""/>
	<!-- 	<p>ID: </p><input type="text" name="oldstaffid" value=""/> -->
 			<input type="submit" value="Remove inventory!" />
 			<br>
		</form>
		
		<form action="InventoryRequest.php" method="get">
			<br>
			<h3>View and Edit Inventory Item</h3>
			<input type="hidden" name="action" value="12/10" />
			Name: <input type="text" name="inventoryName" value=""/>
	<!-- 	<p>ID: </p><input type="text" name="inventoryId" value=""/> -->
 			<input type="submit" value="View inventory!" />
 			<br>
		</form>
				
		<br>
	<a href="../index.php">Back to homepage</a>

		<br><br>
<div class="container-fluid">
		<table class="table table-hover" style="width: 100%;">
			
			<thread>
			<tr>
				<th>Name</th>
				<th>Category</th>
				<th>Type</th>
			</tr>
			</thread>
			<tbody>
			<?php 
			
			$urlms = (new Persistence())->loadDataFromStore();
			
			
			foreach ($urlms->getLab_index(0)->getInventoryItems() as $item){
// 				$roles = "";
// 				if($member->hasResearchRoles()){
// 					foreach ($member->getResearchRoles() as $r){
// 						$roles = $roles . get_class($r) . "<br>";
// 					}
// 				}else{$roles = "None";}
				
// 				if($member->hasProgressUpdates()){
// 					$progress = $member->getProgressUpdate_index(sizeof($member->getProgressUpdates())-1)->getDescription();
// 					if(strlen($progress)>50){
// 						$progress = substr($progress, 0, 50) . "...";
// 					}
// 				}else{$progress = "None";}
				
				echo "<tr><td><button type=\"button\" class=\"btn btn-outline-primary\">" . $item->getName() . "</button></td>
					<td>". $item->getCategory() ."</td>
					<td>" . get_class($item) . "</td>
					</tr>";
			}?>
			</tbody>
		</table>
		</div>
		<br><br>
		

</body>
</html>