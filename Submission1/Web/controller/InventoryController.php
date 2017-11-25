<?php
	$my_dir = dirname(__FILE__);
	require_once $my_dir . '/../persistence/persistence.php';
	require_once $my_dir . '/../model/URLMS.php';
	require_once $my_dir . '/../model/Lab.php';
	require_once $my_dir . '/../model/StaffMember.php';
	require_once $my_dir . '/../model/InventoryItem.php';
	require_once $my_dir . '/../model/SupplyType.php';
	require_once $my_dir . '/../model/ResearchRole.php';
	require_once $my_dir . '/../model/ResearchAssociate.php';
	require_once $my_dir . '/../model/ResearchAssistant.php';
	require_once $my_dir . '/../model/Report.php';
	require_once $my_dir . '/../model/ProgressUpdate.php';
	require_once $my_dir . '/../model/Expense.php';
	require_once $my_dir . '/../model/Equipment.php';
	require_once $my_dir . '/../model/FundingAccount.php';
	
	// start session
	session_start();
	
	$persistence = new Persistence();
	$urlms = $persistence->loadDataFromStore();
	
	$c = new InventoryController($urlms);
	
	// Check which button was clicked by user
	// Run appropriate controller method with respect to user request
	switch($_GET['action']){
		case "9/10":
			$c->getInventoryList();
			break;
		case "10/10":
			try {
			$c->addInventory($_GET['newInventoryName'], $_GET['category'], $_GET['type']); 
			} catch (Exception $e){
				echo $e->getMessage() . "<br>";
				echo "<a href= \"../index.php\">Back</a>" . "<br>";
 			}
			break;
		case "11/10":
			try {
				$c->removeInventory($_GET['oldInventoryName']);
			} catch (Exception $e){
				echo $e->getMessage() . "<br>";
				echo "<a href= \"../index.php\">Back</a>" . "<br>";
			}
			break;
		case "12/10":
			try {
				$c->viewInventoryItem($_GET['inventoryName']);
			} catch (Exception $e){
				echo $e->getMessage() . "<br>";
				echo "<a href= \"../index.php\">Back</a>" . "<br>";
			}
			break;
// 		case "8/10":
// 			try {
// 				$c->editInventoryItem($_GET['editInventoryName'], $_GET['editedInventoryName'], $_GET['editedInventoryCost'], $_GET['editedInventoryCat']);
// 			} catch (Exception $e){
// 				echo $e->getMessage() . "<br>";
// 				echo "<a href= \"../index.php\">Back</a>" . "<br>";
// 			}
// 			break;
	}
		
class InventoryController {
	
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
// 		// Load data
// 		$persistence = new Persistence();
// 		$urlms = $persistence->loadDataFromStore();
		
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
			<a href="../View/InventoryView.html">Back</a>
		</HTML><?php
	}
	
	/*
	 * add new inventory item to urlms
	 */
	function addInventory($name, $category, $type){
		if($name == null || strlen($name) == 0){
			throw new Exception ("Please enter a name.");
		} else {
			$urlms = $this->urlms;
			$newInventoryItem;
			
			if($type == "Equipment"){
				$newInventoryItem = new Equipment($name, rand(0,1000), $category,$urlms->getLab_index(0),false);
			} else{
				$newInventoryItem = new SupplyType($name, rand(0,1000), $category,$urlms->getLab_index(0), rand(0,1000));
			}
			//add the new item to the Inventory 
			$urlms->getLab_index(0)->addInventoryItem($newInventoryItem);
			
			// Write data
			$persistence = new Persistence();
			$persistence->writeDataToStore($urlms);
			
			?>
			<!-- Add back button to page -->
			<HTML>
				<p>New inventory item successfully added!</p>
				<a href="../View/InventoryView.html">Back</a>
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
			<p>Inventory item removed succesfully!</p>
			<a href="../View/InventoryView.html">Back</a>
		</HTML><?php
	}		
	
	function viewInventoryItem($name){
		$urlms = $this->urlms;
		$inventoryItem = $this->findInventoryItem($name);
		$_SESSION['inventoryitem'] = $inventoryItem;
		$_SESSION['urlms'] = $urlms;
			
		echo "ID: " . $inventoryItem->getName() . "<br>";
		echo "Cost: $" . $inventoryItem->getCost() . "<br>";
		echo "Category: " . $inventoryItem->getCategory() . "<br>";
		
		if(get_class($inventoryItem) == "Equipment"){
			if($inventoryItem->getIsDamaged()){
				echo $inventoryItem->getName() . "is damaged! <br>";	
			}
		} else{
			echo "Quantity: " . $inventoryItem->getQuantity() . "<br>";
		}
		echo "<br>";
		
		
		?>
		<HTML>
			<form action="InfoUpdater.php" method="get">
			<br>
			<h3>Edit Inventory Item</h3>
			<input type="hidden" name="action" value="editInventoryItem" />
			New Name: <input type="text" name="editedinventoryname" value="<?php echo $inventoryItem->getName();?>"/>
			New Cost: <input type="text" name="editedinventorycost" value="<?php echo $inventoryItem->getCost();?>"/>
			New Category: <input type="text" name="editedinventorycat" value="<?php echo $inventoryItem->getCategory();?>"/>
 			<input type="submit" value="Edit inventory item!" />
 			<br>
		</form>
		</HTML>
		
 
		
<!-- 		if($type == "Equipment"){ -->
<!-- 				<HTML> -->
					
<!-- 				</HTML> -->
<!-- 			} else{ -->
<!-- 				$newInventoryItem = new SupplyType($name, rand(0,1000), $category,$urlms->getLab_index(0), rand(0,1000)); -->
<!-- 			} -->
		
		<?php //TODO ^Add edit for supply quantity or equipment state
		
		echo "<a href= \"../View/InventoryView.html\">Back</a>" . "<br>";
	}
	
	function editInventoryItem($name, $newname, $newcost, $newcat){
		$urlms = $this->urlms;
		$inventoryItem = $this->findInventoryItem($name);
		
		if(strlen($newname) > 0){
			$inventoryItem->setName($newname);
		}else $inventoryItem->setName($inventoryItem->getName());
		if(strlen($newcost) > 0){
			$inventoryItem->setCost($newcost);
		}else $inventoryItem->setCost($inventoryItem->getCost());
		if(strlen($newcat) > 0){
			$inventoryItem->setCategory($newcat);
		}else $inventoryItem->setCategory($inventoryItem->getCategory());
		
		// Write data
		$persistence = new Persistence();
		$persistence->writeDataToStore($urlms);
		
		?>
		<!-- Add back button to page -->
		<HTML>
			<p>Inventory item updated succesfully!</p>
			<a href="../View/InventoryView.html">Back</a>
		</HTML><?php
		
	}
	
	
	function findInventoryItem($name){
		if($name == null || strlen($name) == 0){
			throw new Exception ("Please enter an inventory item name.");
		} else{
			//Find the member
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