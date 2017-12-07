<?php
	$my_dir = dirname(__FILE__);

	require_once $my_dir . '/../controller/StaffController.php';
	require_once $my_dir . '/../model/URLMS.php';
	require_once $my_dir.'/../model/FundingAccount.php';
	
	
	class StaffControllerTest extends PHPUnit_Framework_TestCase
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
			$this->controller = new StaffController($this->urlms, $this->p);
		}
	
		protected function tearDown()
		{
		}
	
		/*
		 * Add Staff Tests - Done
		 */
		public function testAddStaffMember()
		{
			// 1. Create test data
			$this->controller->addStaff("bob", 100);
			
			// 2. Write all of the data
			$pers = $this->p;
			$pers->writeDataToStore($this->urlms);
	
			// 3. Clear the data from memory
			$this->urlms->delete();
	
			$this->assertEquals(0, $this->urlms->numberOfLabs());
	
			// 4. Load it back in
			$this->urlms = $pers->loadDataFromStore();
	
			// 5. Check that we got it back
			$this->assertEquals(1, count($this->urlms->getLab_index(0)->getStaffMembers()));
			$myStaff = $this->urlms->getLab_index(0)->getStaffMember_index(0);
			$this->assertEquals("bob", $myStaff->getName());
		}
		
		public function testAddStaffMemberNullName()
		{
			// 1. Create test data
			try{
				$this->controller->addStaff(null, 90);
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
			$this->assertEquals(0, count($this->urlms->getLab_index(0)->getStaffMembers()));
// 			$myStaff = $this->urlms->getLab_index(0)->getStaffMember_index(0);
// 			$this->assertEquals("bob", $myStaff->getName());
		}
		
		public function testAddStaffMemberNullSalary()
		{
			// 1. Create test data
			try{
				$this->controller->addStaff("jasmine", null);
			}catch (Exception $e) {
				$this->assertEquals("Please enter a valid number for the salary.", $e->getMessage());
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
			$this->assertEquals(0, count($this->urlms->getLab_index(0)->getStaffMembers()));
			// 			$myStaff = $this->urlms->getLab_index(0)->getStaffMember_index(0);
			// 			$this->assertEquals("bob", $myStaff->getName());
		}
		
		/*
		 *Remove Staff 
		 */
		public function testRemoveStaff()
		{
			// 1. Create test data
			$newStaffMember = new StaffMember("jasmine", rand(0,1000), 100, $this->urlms->getLab_index(0));
			$this->urlms->getLab_index(0)->addStaffMember($newStaffMember);
			
			$this->controller->removeStaff("jasmine", $newStaffMember->getId());
			
			// 2. Write all of the data
			$pers = $this->p;
			$pers->writeDataToStore($this->urlms);
			
			// 3. Clear the data from memory
			$this->urlms->delete();
			
			$this->assertEquals(0, $this->urlms->numberOfLabs());
			
			// 4. Load it back in
			$this->urlms = $pers->loadDataFromStore();
			
			// 5. Check that we got it back
			$this->assertEquals(0, count($this->urlms->getLab_index(0)->getStaffMembers()));
			// 			$myStaff = $this->urlms->getLab_index(0)->getStaffMember_index(0);
			// 			$this->assertEquals("bob", $myStaff->getName());
		}
	
	}
?>