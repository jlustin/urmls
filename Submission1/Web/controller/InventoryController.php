<?php
	$my_dir = dirname(__FILE__);
	require_once $my_dir . '/../persistence/persistence.php';
	require_once $my_dir . '/../controller/Controller.php';
	require_once $my_dir . '/../model/URLMS.php';
	require_once $my_dir . '/../model/Lab.php';
	require_once $my_dir . '/../model/InventoryItem.php';
	require_once $my_dir . '/../model/SupplyType.php';
	require_once $my_dir . '/../model/Equipment.php';
	
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
		<!-- Add back button to page -->
		<HTML>
			<a href="../View/InventoryView.php">Back</a>
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
				<meta http-equiv="refresh" content="0; URL='../View/InventoryView.php'" />
				<p>New inventory item successfully added!</p>
				<a href="../View/InventoryView.php">Back</a>
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
			<meta http-equiv="refresh" content="0; URL='../View/InventoryView.php'" />
			<p>Inventory item removed succesfully!</p>
			<a href="../View/InventoryView.php">Back</a>
		</HTML><?php
	}
	
	function viewInventoryItem($name){
		$urlms = $this->urlms;
		$inventoryItem = $this->findInventoryItem($name);
		session_start();
		$_SESSION['inventoryitem'] = $inventoryItem;
		$_SESSION['urlms'] = $urlms;
			
		echo "ID: " . $inventoryItem->getName() . "<br>";
		echo "Cost: $" . $inventoryItem->getCost() . "<br>";
		echo "Category: " . $inventoryItem->getCategory() . "<br>";
		
		if(get_class($inventoryItem) == "Equipment"){
			if($inventoryItem->getIsDamaged()){
				echo $inventoryItem->getName() . " is damaged! <br>";	
			}
		} else{
			echo "Quantity: " . $inventoryItem->getQuantity() . "<br>";
		}
		echo "<br>";
		
		
		?>
		<HTML>
			<form action="../Controller/InfoUpdater.php" method="get">
			<br>
			<h3>Edit Inventory Item</h3>
			<input type="hidden" name="action" value="editInventoryItem" />
			New Name: <input type="text" name="editedinventoryname" value="<?php echo $inventoryItem->getName();?>"/>
			New Cost: <input type="text" name="editedinventorycost" value="<?php echo $inventoryItem->getCost();?>"/>
			New Category: <input type="text" name="editedinventorycat" value="<?php echo $inventoryItem->getCategory();?>"/>
			<?php
			if(get_class($inventoryItem) == "Equipment"){
			?>
				<input type="radio" name="isdamaged" value="damaged"/> Damaged
				<input type="radio" name="isdamaged" value="notdamaged"/> Not Damaged <br>
				<input type="hidden" name="editedsupplyquantity" value="" />
			<?php 
			} else{
			?>
				Add/Remove Quantity: <input type="text" name="editedsupplyquantity" value=""/>
				<input type="hidden" name="isdamaged" value="" />
			<?php
			}
			?>
 			<input type="submit" value="Edit inventory item!" />
 			<br>
		</form>
		</HTML>
	
	<?php	
		echo "<a href= \"../View/InventoryView.php\">Back</a>" . "<br>";
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