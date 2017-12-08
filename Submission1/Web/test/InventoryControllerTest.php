<?php
	$my_dir = dirname(__FILE__);

	require_once $my_dir . '/../controller/InventoryController.php';
	require_once $my_dir . '/../model/URLMS.php';
	require_once $my_dir . '/../model/FundingAccount.php';
	
	
	class InventoryControllerTest extends PHPUnit_Framework_TestCase
	{
		protected $urlms;
		protected $controller;
		protected $p;
	
		protected function setUp()
		{
			
			$this->urlms = new URLMS();
			$lab = new Lab("9/10", $this->urlms);
			$this->urlms->addLab($lab);
			
			$this->p = new Persistence(dirname(__FILE__)."/../persistence/test.txt");
			$this->p->loadDataFromStore();
			$this->p->writeDataToStore($this->urlms);
			$this->controller = new InventoryController($this->urlms, $this->p);
		}
	
		protected function tearDown()
		{
		}
		
		/**
		 * 	TODO: Do get inventory list test, if necessary
		 * 	Get Inventory List Tests
		 */
		
		/**
		 *	TODO: Finish Add Inventory Tests
		 * 	Add Inventory Tests
		 */
		public function testAddEquipement()
		{
			// 1. Create test data
			$this->controller->addInventory("fpga", "board", "Equipment", 500, 1);
			
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
			$myInventory = $this->urlms->getLab_index(0)->getInventoryItem_index(0);
			$this->assertEquals("fpga", $myInventory->getName());
			$this->assertEquals("board", $myInventory->getCategory());
			$this->assertEquals("Equipment", get_class($myInventory));
			$this->assertEquals(500, $myInventory->getCost());
		}
		
		public function testAddEquipementNullName()
		{
			// 1. Create test data
			try{
				$this->controller->addInventory(null, "board", "Equipment", 500, 1);
			}catch (Exception $e) {
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
			$this->assertEquals(0, count($this->urlms->getLab_index(0)->getInventoryItems()));
// 			$myInventory = $this->urlms->getLab_index(0)->getInventoryItem_index(0);
// 			$this->assertEquals("fpga", $myInventory->getName());
// 			$this->assertEquals("board", $myInventory->getCategory());
// 			$this->assertEquals("Equipment", get_class($myInventory));
// 			$this->assertEquals(500, $myInventory->getCost());
		}
		
		public function testAddSupplyType()
		{
			// 1. Create test data
			$this->controller->addInventory("fpga", "board", "SupplyType", 500, 20);
			
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
			$myInventory = $this->urlms->getLab_index(0)->getInventoryItem_index(0);
			$this->assertEquals("fpga", $myInventory->getName());
			$this->assertEquals("board", $myInventory->getCategory());
			$this->assertEquals("SupplyType", get_class($myInventory));
			$this->assertEquals(20, $myInventory->getQuantity());
		}
		
		
		
		/**
		 *	TODO: Finish Remove Inventory Tests
		 * 	Remove Inventory Tests
		 */
		
		/**
		 *	TODO: Finish View Inventory Tests
		 * 	View Inventory Tests
		 */
		
		/**
		 *	TODO: Finish Find Inventory Tests
		 * 	Find Inventory Tests
		 */
		
	}
?>
