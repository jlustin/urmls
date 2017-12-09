<?php $my_dir = dirname(__FILE__);
require_once $my_dir . '/../controller/InfoUpdater.php';
require_once $my_dir . '/../model/URLMS.php';
require_once $my_dir.'/../model/FundingAccount.php';
session_start();

class InfoUpdaterTest extends PHPUnit_Framework_TestCase
{
	protected $urlms;
	protected $controller;
	protected $p;
	
	protected function setUp()
	{
		
		$this->urlms = new URLMS();
		$lab = new Lab("9/10", $this->urlms);
		$this->urlms->addLab($lab);
		$lab->addFundingAccount(new FundingAccount("Staff Funding", 0, $lab));
		$lab->addFundingAccount(new FundingAccount("Equipment Funding", 0, $lab));
		$lab->addFundingAccount(new FundingAccount("Supply Funding", 0, $lab));
		
		$this->p = new Persistence(dirname(__FILE__)."/../persistence/test.txt");
		$this->p->loadDataFromStore();
		$this->p->writeDataToStore($this->urlms);
		$_SESSION['persistence'] = $this->p;
		$this->controller = new InfoUpdater($this->p);
	}
	
	protected function tearDown()
	{
	}
	
	/**
	 * 	TODO: Finish Update Invnetory Member Tests
	 * 	Update Inventory Tests
	 */
	public function testUpdateEquipment()
	{
		// 1. Create test data
		$newInventoryItem = new Equipment("fpga", 500, "board", $this->urlms->getLab_index(0), false);
		$this->urlms->getLab_index(0)->addInventoryItem($newInventoryItem);
		
		$_SESSION['urlms'] = $this->urlms ;
		$_SESSION['inventoryitem'] = $newInventoryItem; 
		
		$this->assertEquals(1, count($this->urlms->getLab_index(0)->getInventoryItems()));
		
		$this->controller->updateInventory("fpga5", 600, "latest board", "damaged", null);
		
		// 2. Write all of the data
		$pers = $this->p;
		$pers->writeDataToStore($this->urlms);
		
		// 3. Clear the data from memory
		$this->urlms->delete();
		
		$this->assertEquals(0, $this->urlms->numberOfLabs());
		
		// 4. Load it back in
		$this->urlms = $pers->loadDataFromStore();
		
		// 5. Check that we got it back
		$this->assertEquals(1, count($this->urlms->getLab_index(0)->getInventoryItems()));
		$this->assertEquals("fpga5", $newInventoryItem->getName());
		$this->assertEquals(600, $newInventoryItem->getCost());
		$this->assertEquals("latest board", $newInventoryItem->getCategory());
		$this->assertEquals(true, $newInventoryItem->getIsDamaged());
	}
	
	public function testUpdateEquipmentNullName()
	{
		// 1. Create test data
		$newInventoryItem = new Equipment("fpga", 500, "board", $this->urlms->getLab_index(0), false);
		$this->urlms->getLab_index(0)->addInventoryItem($newInventoryItem);
		
		$_SESSION['urlms'] = $this->urlms ;
		$_SESSION['inventoryitem'] = $newInventoryItem;
		
		$this->assertEquals(1, count($this->urlms->getLab_index(0)->getInventoryItems()));
		
		try {
			$this->controller->updateInventory(null, 600, "latest board", "damaged", null);
		} catch (Exception $e) {
			$this->assertEquals("Please enter a valid name.", $e->getMessage()); 
		}
		
		// 2. Write all of the data
		$pers = $this->p;
		$pers->writeDataToStore($this->urlms);
		
		// 3. Clear the data from memory
		$this->urlms->delete();
		
		$this->assertEquals(0, $this->urlms->numberOfLabs());
		
		// 4. Load it back in
		$this->urlms = $pers->loadDataFromStore();
		
		// 5. Check that we got it back
		$this->assertEquals(1, count($this->urlms->getLab_index(0)->getInventoryItems()));
	}
	
	public function testUpdateEquipmentInvalidName()
	{
		// 1. Create test data
		$newInventoryItem = new Equipment("fpga", 500, "board", $this->urlms->getLab_index(0), false);
		$this->urlms->getLab_index(0)->addInventoryItem($newInventoryItem);
		
		$_SESSION['urlms'] = $this->urlms ;
		$_SESSION['inventoryitem'] = $newInventoryItem;
		
		$this->assertEquals(1, count($this->urlms->getLab_index(0)->getInventoryItems()));
		
		try {
			$this->controller->updateInventory("#newinventory", 600, "latest board", "damaged", null);
		} catch (Exception $e) {
			$this->assertEquals("Please enter a valid name.", $e->getMessage());
		}
		
		// 2. Write all of the data
		$pers = $this->p;
		$pers->writeDataToStore($this->urlms);
		
		// 3. Clear the data from memory
		$this->urlms->delete();
		
		$this->assertEquals(0, $this->urlms->numberOfLabs());
		
		// 4. Load it back in
		$this->urlms = $pers->loadDataFromStore();
		
		// 5. Check that we got it back
		$this->assertEquals(1, count($this->urlms->getLab_index(0)->getInventoryItems()));
	}
	
	public function testUpdateEquipmentNullCost()
	{
		// 1. Create test data
		$newInventoryItem = new Equipment("fpga", 500, "board", $this->urlms->getLab_index(0), false);
		$this->urlms->getLab_index(0)->addInventoryItem($newInventoryItem);
		
		$_SESSION['urlms'] = $this->urlms ;
		$_SESSION['inventoryitem'] = $newInventoryItem;
		
		$this->assertEquals(1, count($this->urlms->getLab_index(0)->getInventoryItems()));
		
		try {
			$this->controller->updateInventory("fpga", null, "latest board", "damaged", null);
		} catch (Exception $e) {
			$this->assertEquals("Please enter a valid cost.", $e->getMessage());
		}
		
		// 2. Write all of the data
		$pers = $this->p;
		$pers->writeDataToStore($this->urlms);
		
		// 3. Clear the data from memory
		$this->urlms->delete();
		
		$this->assertEquals(0, $this->urlms->numberOfLabs());
		
		// 4. Load it back in
		$this->urlms = $pers->loadDataFromStore();
		
		// 5. Check that we got it back
		$this->assertEquals(1, count($this->urlms->getLab_index(0)->getInventoryItems()));
	}
	
	public function testUpdateEquipmentInvalidCost()
	{
		// 1. Create test data
		$newInventoryItem = new Equipment("fpga", 500, "board", $this->urlms->getLab_index(0), false);
		$this->urlms->getLab_index(0)->addInventoryItem($newInventoryItem);
		
		$_SESSION['urlms'] = $this->urlms ;
		$_SESSION['inventoryitem'] = $newInventoryItem;
		
		$this->assertEquals(1, count($this->urlms->getLab_index(0)->getInventoryItems()));
		
		try {
			$this->controller->updateInventory("fpga", "forty dollars", "latest board", "damaged", null);
		} catch (Exception $e) {
			$this->assertEquals("Please enter a valid cost.", $e->getMessage());
		}
		
		// 2. Write all of the data
		$pers = $this->p;
		$pers->writeDataToStore($this->urlms);
		
		// 3. Clear the data from memory
		$this->urlms->delete();
		
		$this->assertEquals(0, $this->urlms->numberOfLabs());
		
		// 4. Load it back in
		$this->urlms = $pers->loadDataFromStore();
		
		// 5. Check that we got it back
		$this->assertEquals(1, count($this->urlms->getLab_index(0)->getInventoryItems()));
	}
	
	public function testUpdateEquipmentNullCategory()
	{
		// 1. Create test data
		$newInventoryItem = new Equipment("fpga", 500, "board", $this->urlms->getLab_index(0), false);
		$this->urlms->getLab_index(0)->addInventoryItem($newInventoryItem);
		
		$_SESSION['urlms'] = $this->urlms ;
		$_SESSION['inventoryitem'] = $newInventoryItem;
		
		$this->assertEquals(1, count($this->urlms->getLab_index(0)->getInventoryItems()));
		
		try {
			$this->controller->updateInventory("fpga", 600, null, "damaged", null);
		} catch (Exception $e) {
			$this->assertEquals("Please enter a valid category.", $e->getMessage());
		}
		
		// 2. Write all of the data
		$pers = $this->p;
		$pers->writeDataToStore($this->urlms);
		
		// 3. Clear the data from memory
		$this->urlms->delete();
		
		$this->assertEquals(0, $this->urlms->numberOfLabs());
		
		// 4. Load it back in
		$this->urlms = $pers->loadDataFromStore();
		
		// 5. Check that we got it back
		$this->assertEquals(1, count($this->urlms->getLab_index(0)->getInventoryItems()));
	}
	
	public function testUpdateEquipmentInvalidCategory()
	{
		// 1. Create test data
		$newInventoryItem = new Equipment("fpga", 500, "board", $this->urlms->getLab_index(0), false);
		$this->urlms->getLab_index(0)->addInventoryItem($newInventoryItem);
		
		$_SESSION['urlms'] = $this->urlms ;
		$_SESSION['inventoryitem'] = $newInventoryItem;
		
		$this->assertEquals(1, count($this->urlms->getLab_index(0)->getInventoryItems()));
		
		try {
			$this->controller->updateInventory("fpga", 600, "#latestboard", "damaged", null);
		} catch (Exception $e) {
			$this->assertEquals("Please enter a valid category.", $e->getMessage());
		}
		
		// 2. Write all of the data
		$pers = $this->p;
		$pers->writeDataToStore($this->urlms);
		
		// 3. Clear the data from memory
		$this->urlms->delete();
		
		$this->assertEquals(0, $this->urlms->numberOfLabs());
		
		// 4. Load it back in
		$this->urlms = $pers->loadDataFromStore();
		
		// 5. Check that we got it back
		$this->assertEquals(1, count($this->urlms->getLab_index(0)->getInventoryItems()));
	}
	
	public function testUpdateEquipmentNullIsDamaged()
	{
		// 1. Create test data
		$newInventoryItem = new Equipment("fpga", 500, "board", $this->urlms->getLab_index(0), false);
		$this->urlms->getLab_index(0)->addInventoryItem($newInventoryItem);
		
		$_SESSION['urlms'] = $this->urlms ;
		$_SESSION['inventoryitem'] = $newInventoryItem;
		
		$this->assertEquals(1, count($this->urlms->getLab_index(0)->getInventoryItems()));
		
		try {
			$this->controller->updateInventory("fpga", 600, "latest board", null, null);
		} catch (Exception $e) {
		
		}
		
		// 2. Write all of the data
		$pers = $this->p;
		$pers->writeDataToStore($this->urlms);
		
		// 3. Clear the data from memory
		$this->urlms->delete();
		
		$this->assertEquals(0, $this->urlms->numberOfLabs());
		
		// 4. Load it back in
		$this->urlms = $pers->loadDataFromStore();
		
		// 5. Check that we got it back
		$this->assertEquals(1, count($this->urlms->getLab_index(0)->getInventoryItems()));
	}
	
	public function testUpdateSupply()
	{
		// 1. Create test data
		$newInventoryItem = new SupplyType("pencil", 20, "office", $this->urlms->getLab_index(0), 40);
		$this->urlms->getLab_index(0)->addInventoryItem($newInventoryItem);
		
		$_SESSION['urlms'] = $this->urlms ;
		$_SESSION['inventoryitem'] = $newInventoryItem;
		
		$this->assertEquals(1, count($this->urlms->getLab_index(0)->getInventoryItems()));
		
		$this->controller->updateInventory("pen", 25, "essay tool", null, 20);
		
		// 2. Write all of the data
		$pers = $this->p;
		$pers->writeDataToStore($this->urlms);
		
		// 3. Clear the data from memory
		$this->urlms->delete();
		
		$this->assertEquals(0, $this->urlms->numberOfLabs());
		
		// 4. Load it back in
		$this->urlms = $pers->loadDataFromStore();
		
		// 5. Check that we got it back
		$this->assertEquals(1, count($this->urlms->getLab_index(0)->getInventoryItems()));
		$this->assertEquals("pen", $newInventoryItem->getName());
		$this->assertEquals(25, $newInventoryItem->getCost());
		$this->assertEquals("essay tool", $newInventoryItem->getCategory());
		$this->assertEquals(60, $newInventoryItem->getQuantity());
	}
	
	public function testUpdateSupplyNullName()
	{
		// 1. Create test data
		$newInventoryItem = new SupplyType("pencil", 20, "office", $this->urlms->getLab_index(0), 40);
		$this->urlms->getLab_index(0)->addInventoryItem($newInventoryItem);
		
		$_SESSION['urlms'] = $this->urlms ;
		$_SESSION['inventoryitem'] = $newInventoryItem;
		
		$this->assertEquals(1, count($this->urlms->getLab_index(0)->getInventoryItems()));
		
		try {
			$this->controller->updateInventory(null, 25, "essay tool", null, 20);
		} catch (Exception $e) {
			$this->assertEquals("Please enter a valid name.", $e->getMessage());
		}
		
		// 2. Write all of the data
		$pers = $this->p;
		$pers->writeDataToStore($this->urlms);
		
		// 3. Clear the data from memory
		$this->urlms->delete();
		
		$this->assertEquals(0, $this->urlms->numberOfLabs());
		
		// 4. Load it back in
		$this->urlms = $pers->loadDataFromStore();
		
		// 5. Check that we got it back
		$this->assertEquals(1, count($this->urlms->getLab_index(0)->getInventoryItems()));
	}
	
	public function testUpdateSupplyInvalidName()
	{
		// 1. Create test data
		$newInventoryItem = new SupplyType("pencil", 20, "office", $this->urlms->getLab_index(0), 40);
		$this->urlms->getLab_index(0)->addInventoryItem($newInventoryItem);
		
		$_SESSION['urlms'] = $this->urlms ;
		$_SESSION['inventoryitem'] = $newInventoryItem;
		
		$this->assertEquals(1, count($this->urlms->getLab_index(0)->getInventoryItems()));
		
		try {
			$this->controller->updateInventory("#pencil", 25, "essay tool", null, 20);
		} catch (Exception $e) {
			$this->assertEquals("Please enter a valid name.", $e->getMessage());
		}
		
		// 2. Write all of the data
		$pers = $this->p;
		$pers->writeDataToStore($this->urlms);
		
		// 3. Clear the data from memory
		$this->urlms->delete();
		
		$this->assertEquals(0, $this->urlms->numberOfLabs());
		
		// 4. Load it back in
		$this->urlms = $pers->loadDataFromStore();
		
		// 5. Check that we got it back
		$this->assertEquals(1, count($this->urlms->getLab_index(0)->getInventoryItems()));
	}
	
	public function testUpdateSupplyNullCost()
	{
		// 1. Create test data
		$newInventoryItem = new SupplyType("pencil", 20, "office", $this->urlms->getLab_index(0), 40);
		$this->urlms->getLab_index(0)->addInventoryItem($newInventoryItem);
		
		$_SESSION['urlms'] = $this->urlms ;
		$_SESSION['inventoryitem'] = $newInventoryItem;
		
		$this->assertEquals(1, count($this->urlms->getLab_index(0)->getInventoryItems()));
		
		try {
			$this->controller->updateInventory("pencil", null, "essay tool", null, 20);
		} catch (Exception $e) {
			$this->assertEquals("Please enter a valid cost.", $e->getMessage());
		}
		
		// 2. Write all of the data
		$pers = $this->p;
		$pers->writeDataToStore($this->urlms);
		
		// 3. Clear the data from memory
		$this->urlms->delete();
		
		$this->assertEquals(0, $this->urlms->numberOfLabs());
		
		// 4. Load it back in
		$this->urlms = $pers->loadDataFromStore();
		
		// 5. Check that we got it back
		$this->assertEquals(1, count($this->urlms->getLab_index(0)->getInventoryItems()));
	}
	
	public function testUpdateSupplyInvalidCost()
	{
		// 1. Create test data
		$newInventoryItem = new SupplyType("pencil", 20, "office", $this->urlms->getLab_index(0), 40);
		$this->urlms->getLab_index(0)->addInventoryItem($newInventoryItem);
		
		$_SESSION['urlms'] = $this->urlms ;
		$_SESSION['inventoryitem'] = $newInventoryItem;
		
		$this->assertEquals(1, count($this->urlms->getLab_index(0)->getInventoryItems()));
		
		try {
			$this->controller->updateInventory("pencil", "forty", "essay tool", null, 20);
		} catch (Exception $e) {
			$this->assertEquals("Please enter a valid cost.", $e->getMessage());
		}
		
		// 2. Write all of the data
		$pers = $this->p;
		$pers->writeDataToStore($this->urlms);
		
		// 3. Clear the data from memory
		$this->urlms->delete();
		
		$this->assertEquals(0, $this->urlms->numberOfLabs());
		
		// 4. Load it back in
		$this->urlms = $pers->loadDataFromStore();
		
		// 5. Check that we got it back
		$this->assertEquals(1, count($this->urlms->getLab_index(0)->getInventoryItems()));
	}
	
	public function testUpdateSupplyNullCategory()
	{
		// 1. Create test data
		$newInventoryItem = new SupplyType("pencil", 20, "office", $this->urlms->getLab_index(0), 40);
		$this->urlms->getLab_index(0)->addInventoryItem($newInventoryItem);
		
		$_SESSION['urlms'] = $this->urlms ;
		$_SESSION['inventoryitem'] = $newInventoryItem;
		
		$this->assertEquals(1, count($this->urlms->getLab_index(0)->getInventoryItems()));
		
		try {
			$this->controller->updateInventory("pencil", 25, null, null, 20);
		} catch (Exception $e) {
			$this->assertEquals("Please enter a valid category.", $e->getMessage());
		}
		
		// 2. Write all of the data
		$pers = $this->p;
		$pers->writeDataToStore($this->urlms);
		
		// 3. Clear the data from memory
		$this->urlms->delete();
		
		$this->assertEquals(0, $this->urlms->numberOfLabs());
		
		// 4. Load it back in
		$this->urlms = $pers->loadDataFromStore();
		
		// 5. Check that we got it back
		$this->assertEquals(1, count($this->urlms->getLab_index(0)->getInventoryItems()));
	}
	
	public function testUpdateSupplyInvalidCategory()
	{
		// 1. Create test data
		$newInventoryItem = new SupplyType("pencil", 20, "office", $this->urlms->getLab_index(0), 40);
		$this->urlms->getLab_index(0)->addInventoryItem($newInventoryItem);
		
		$_SESSION['urlms'] = $this->urlms ;
		$_SESSION['inventoryitem'] = $newInventoryItem;
		
		$this->assertEquals(1, count($this->urlms->getLab_index(0)->getInventoryItems()));
		
		try {
			$this->controller->updateInventory("pencil", 40, "#essay tool", null, 20);
		} catch (Exception $e) {
			$this->assertEquals("Please enter a valid category.", $e->getMessage());
		}
		
		// 2. Write all of the data
		$pers = $this->p;
		$pers->writeDataToStore($this->urlms);
		
		// 3. Clear the data from memory
		$this->urlms->delete();
		
		$this->assertEquals(0, $this->urlms->numberOfLabs());
		
		// 4. Load it back in
		$this->urlms = $pers->loadDataFromStore();
		
		// 5. Check that we got it back
		$this->assertEquals(1, count($this->urlms->getLab_index(0)->getInventoryItems()));
	}
	
	public function testUpdateSupplyNullQuantity()
	{
		// 1. Create test data
		$newInventoryItem = new SupplyType("pencil", 20, "office", $this->urlms->getLab_index(0), 40);
		$this->urlms->getLab_index(0)->addInventoryItem($newInventoryItem);
		
		$_SESSION['urlms'] = $this->urlms ;
		$_SESSION['inventoryitem'] = $newInventoryItem;
		
		$this->assertEquals(1, count($this->urlms->getLab_index(0)->getInventoryItems()));
		
		try {
			$this->controller->updateInventory("pencil", 25, "essay tool", null, null);
		} catch (Exception $e) {
			$this->assertEquals("Please enter a valid quantity.", $e->getMessage());
		}
		
		// 2. Write all of the data
		$pers = $this->p;
		$pers->writeDataToStore($this->urlms);
		
		// 3. Clear the data from memory
		$this->urlms->delete();
		
		$this->assertEquals(0, $this->urlms->numberOfLabs());
		
		// 4. Load it back in
		$this->urlms = $pers->loadDataFromStore();
		
		// 5. Check that we got it back
		$this->assertEquals(1, count($this->urlms->getLab_index(0)->getInventoryItems()));
	}
	
	public function testUpdateSupplyInvalidQuantity()
	{
		// 1. Create test data
		$newInventoryItem = new SupplyType("pencil", 20, "office", $this->urlms->getLab_index(0), 40);
		$this->urlms->getLab_index(0)->addInventoryItem($newInventoryItem);
		
		$_SESSION['urlms'] = $this->urlms ;
		$_SESSION['inventoryitem'] = $newInventoryItem;
		
		$this->assertEquals(1, count($this->urlms->getLab_index(0)->getInventoryItems()));
		
		try {
			$this->controller->updateInventory("pencil", 25, "essay tool", null, "#20");
		} catch (Exception $e) {
			$this->assertEquals("Please enter a valid quantity.", $e->getMessage());
		}
		
		// 2. Write all of the data
		$pers = $this->p;
		$pers->writeDataToStore($this->urlms);
		
		// 3. Clear the data from memory
		$this->urlms->delete();
		
		$this->assertEquals(0, $this->urlms->numberOfLabs());
		
		// 4. Load it back in
		$this->urlms = $pers->loadDataFromStore();
		
		// 5. Check that we got it back
		$this->assertEquals(1, count($this->urlms->getLab_index(0)->getInventoryItems()));
	}
	
	/**
	 * 	TODO: Finish Update Staff Member Tests
	 * 	Update Staff Member Tests
	 */
	
	/**
	 * 	TODO: Finish Update Staff Role Tests
	 * 	Update Invnetory Tests
	 */
	
	/**
	 * 	TODO: Finish Update Progress Update Test
	 * 	Update Invnetory Tests
	 */
	
	/**
	 * 	TODO: Finish Update Account Test
	 * 	Update Invnetory Tests
	 */
	
	/**
	 * 	TODO: Finish Update Expense Test
	 * 	Update Invnetory Tests
	 */
	
	/**
	 * 	TODO: Finish Is Valid String Test
	 * 	Update Is Valid String Tests
	 */
	
}?>