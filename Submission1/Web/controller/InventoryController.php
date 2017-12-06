<?php
	$my_dir = dirname(__FILE__);
	require_once $my_dir . '/../persistence/persistence.php';
	require_once $my_dir . '/../controller/Controller.php';
	require_once $my_dir . '/../model/URLMS.php';
	require_once $my_dir . '/../model/Lab.php';
	require_once $my_dir . '/../model/InventoryItem.php';
	require_once $my_dir . '/../model/SupplyType.php';
	require_once $my_dir . '/../model/Equipment.php';
	session_start();
class InventoryController extends Controller {
	
	protected $urlms;
	/*
	 * Constructor
	 */
	public function __construct($urlms){
		$this->urlms = $urlms;
	}
	
	/*
	 * get list of inventory from urlms
	 */
	function getInventoryList(){
		// Get inventory items from urlms
		$items = $this->urlms->getLab_index(0)->getInventoryItems();
		for ($i = 0; $i < sizeof($items); $i++){
			// display each inventory item represented by their type, name and cost
			echo $items{$i}->getName() . ", " . $items{$i}->getCategory() . ", $" . $items{$i}->getCost();
			if(get_class($items{$i})=="SupplyType")
				echo ", " . $items{$i}->getQuantity();
			echo "<br>";
		} 
		?>
		<html>
			<meta http-equiv="refresh" content="0; URL='../view/InventoryView.php'" />
		</html>
		<!-- Add back button to page -->
		<HTML>
			<a href="../view/InventoryView.php">Back</a>
		</HTML><?php
	}
	
	/*
	 * add new inventory item to urlms
	 */
	function addInventory($name, $category, $type, $cost, $quantity){
		if($name == null || strlen($name) == 0){
			throw new Exception ("Please enter a valid name.");
		}
		elseif ($category == null || strlen($category) == 0 || (!$this->isValidStr($category))){
			throw new Exception ("Please enter a valid category.");
		}
		elseif ($cost == null || strlen($cost) == 0 || (!is_numeric($cost))){
			throw new Exception ("Please enter a valid cost.");
		}
		else {
			$urlms = $this->urlms;
			$newInventoryItem;
			
			if($type == "Equipment"){
				$newInventoryItem = new Equipment($name, $cost, $category,$urlms->getLab_index(0),false);
			} else{
				if ($quantity == null || strlen($quantity) == 0 || (!is_numeric($quantity))){
					throw new Exception ("Please enter a valid quantity.");
				}
				$newInventoryItem = new SupplyType($name, $cost, $category,$urlms->getLab_index(0), $quantity);
			}
			//add the new item to the Inventory 
			$urlms->getLab_index(0)->addInventoryItem($newInventoryItem);
			
			// Write data
			$persistence = new Persistence();
			$persistence->writeDataToStore($urlms);
			
			?>
			<!-- Add back button to page -->
			<HTML>
				<meta http-equiv="refresh" content="0; URL='../view/InventoryView.php'" />
				<p>New inventory item successfully added!</p>
				<a href="../view/InventoryView.php">Back</a>
			</HTML><?php
		}
	}
	/*
	 * remove an inventory item from urlms
	 */
	function removeInventory($name){
		$urlms = $this->urlms;
		$inventoryItem = $this->findInventoryItem($name);
		
		$inventoryItem->delete();
		
		// Write data
		$persistence = new Persistence();
		$persistence->writeDataToStore($urlms);
		
		?>
		<!-- Add back button to page -->
		<HTML>
			<meta http-equiv="refresh" content="0; URL='../view/InventoryView.php'" />
			<p>Inventory item removed succesfully!</p>
			<a href="../view/InventoryView.php">Back</a>
		</HTML><?php
	}
	
	function viewInventoryItem($name){
		$urlms = $this->urlms;
		$inventoryItem = $this->findInventoryItem($name);
		//session_start();
		$_SESSION['inventoryitem'] = $inventoryItem;
		$_SESSION['urlms'] = $urlms;
		?>
		<html>
			<div class="container">
				<h3>Inventory Item Summary</h3>
				<br>
				<label for="ItemName">Item Name :</label> <?php echo $inventoryItem->getName();?>
				<br>
				<label for="ItemCost">Item Cost :</label> <?php echo "$ " . $inventoryItem->getCost();?> 
				<br>
				<label for="ItemCategory">Item Category :</label> <?php echo $inventoryItem->getCategory();?> 
				<br>
				<?php 
				if(get_class($inventoryItem) == "Equipment"){
					if($inventoryItem->getIsDamaged()){?>
						<?php echo $inventoryItem->getName();?> <label for="ItemName"> is damaged!</label> <br> <?php					
					}
				} else{?>
					<label for="SupplyQuantity">Quantity :</label> <?php echo $inventoryItem->getQuantity() ;?> 
					<br>
					<br>
				<?php
				}
				?>
			
				<form action="../controller/InfoUpdater.php" method="get">
				<div class="form-group">
					<br>
					<h3>Edit Inventory Item</h3>
					<input type="hidden" name="action" value="editInventoryItem" />
						<div class="row">
							<div class="col-sm-6">
								<label for="NewName">New Name</label> 
								<input type="text" class="form-control" name="editedinventoryname" id="expenseName" aria-describedby="nameHelp" value="<?php echo $inventoryItem->getName();?>"/>
								<small id="nameHelp" class="form-text text-muted">Enter new inventory item name.</small> <br>
							</div>
							<div class="col-sm-6">
								<label for="NewName">New Cost</label> 
								<input type="text" class="form-control" name="editedinventorycost" id="expenseName" aria-describedby="nameHelp" value="<?php echo $inventoryItem->getCost();?>"/>
								<small id="nameHelp" class="form-text text-muted">Enter new inventory item cost.</small> <br>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<label for="NewName">New Category</label> 
								<input type="text" class="form-control" name="editedinventorycat" id="expenseName" aria-describedby="nameHelp" value="<?php echo $inventoryItem->getCategory();?>"/>
								<small id="nameHelp" class="form-text text-muted">Enter new inventory item category.</small> <br>
							</div>
						</div>
						
						<?php
							if(get_class($inventoryItem) == "Equipment"){
							?>
								<input type="radio" name="isdamaged" value="damaged"/> Damaged<br>
								<input type="radio" name="isdamaged" value="notdamaged"/> Not Damaged <br>
								<input type="hidden" name="editedsupplyquantity" value="" />
							<?php 
							} else{
							?>
								<div class="row">
									<div class="col-sm-6">
										<label for="NewName">Add/Remove Quantity</label> 
										<input type="text" class="form-control" name="editedsupplyquantity" id="expenseName" aria-describedby="nameHelp" value=""/>
										<small id="nameHelp" class="form-text text-muted">Enter number of supply to remove or add.</small> <br>
									</div>
								</div>
								<input type="hidden" name="isdamaged" value="" />
							<?php
							}
						?>
						<br>
						<br>
					<input class="btn btn-danger" type="submit" value="Edit Inventory Item!" />
					</div>
						<br>
					</form>
					<div class="row">
						<div class="col-sm-2">
							<a href="../view/InventoryView.php" style="color: white; text-decoration: none;">
								<button type="button" class="btn btn-danger" data-toggle="tooltip"
									data-placement="bottom" title="Go back to homepage">Back</button>
							</a>
						</div>
					</div>
			</div>
		</html><?php
	}
		
	function findInventoryItem($name){
		if($name == null || strlen($name) == 0){
			throw new Exception ("Please enter a valid name.");
		}else{
			$inventoryItem=null;
			//Find the item
			$items = $this->urlms->getLab_index(0)->getInventoryItems();
			for ($i = 0; $i < sizeof($items); $i++){
				if($name == $items{$i}->getName()){
					$inventoryItem = $items{$i};
				}
			}
			if($inventoryItem == null){
				throw new Exception ("Inventory item not found.");
			}
		}
		return $inventoryItem;
	}
	
}
?>