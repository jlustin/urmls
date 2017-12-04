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
 			<input class="btn btn-secondary" type="submit" value="View Inventory!" />
 			<br>
 			
		</form>
		
		<form action="InventoryRequest.php" method="get">
			<div class="form-group">
			<br>
			<input type="hidden" name="action" value="10/10" />
			<h3>Add Inventory Item</h3>
			
			<div class="form-row">	
			<div class="col">	
			<label for="newInventoryName">Name</label>
    		<input type="text" class="form-control" name="newInventoryName" id="newInventoryName" aria-describedby="nameHelp" placeholder="Enter item name">
    		<small id="nameHelp" class="form-text text-muted">Enter the model or name of your item.</small>
			<br>
			</div><div class="col">
			<label for="newInventoryCategory">Category</label>
    		<input type="text" class="form-control" name="category" id="newInventoryCategory" aria-describedby="categoryHelp" placeholder="Enter item category">
    		<small id="categoryHelp" class="form-text text-muted">Enter the category of your item (eg. Computer, chair, ...).</small>
			<br>
			</div>
			</div>
			<div class="form-row">	
			<div class="col">	
			<label for="newInventoryCost">Cost</label>
    		<input type="text" class="form-control" name="cost" id="newInventoryCost" aria-describedby="costHelp" placeholder="Enter item cost">
    		<small id="costHelp" class="form-text text-muted">Enter the cost of your item (eg. $50.00).</small>
			<br>
			</div><div class="col">
			<label for="newInventoryQuantity">Quantity</label>
    		<input type="text" class="form-control" name="quantity" id="newInventoryQuantity" aria-describedby="quantityHelp" placeholder="Enter supply quantity">
    		<small id="quantityHelp" class="form-text text-muted">Enter the quantiy of your item (eg. 100).</small>
			<br>
			</div>
			</div>
				
			
			<div class="form-row">		
			Type:<input type="radio" name="type" value="Equipment"/>Equipment <br>
			<input type="radio" name="type" value="Supply"/>Supply <br>
			<input class="btn btn-primary" type="submit" value="Add inventory!" />
 			<br></div>
 			</div>
		</form>
		
		<form action="InventoryRequest.php" method="get">
			<br>
			<h3>Remove Inventory Item</h3>
			<input type="hidden" name="action" value="11/10" />
			Name:<input type="text" name="oldInventoryName" value=""/> 			
 			<input class="btn btn-primary" type="submit" value="Remove inventory!" />
 			<br> 			
		</form>
		
		<form action="InventoryRequest.php" method="get">
			<br>
			<h3>View and Edit Inventory Item</h3>
			<input type="hidden" name="action" value="12/10" />
			Name: <input type="text" name="inventoryName" value=""/>
 			<input class="btn btn-primary" type="submit" value="View inventory!" />
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
				<th>Cost</th>
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
					<td>$" . $item->getCost() . "</td>
					</tr>";
			}?>
			</tbody>
		</table>
		</div>
		<br><br>
		

</body>
</html>