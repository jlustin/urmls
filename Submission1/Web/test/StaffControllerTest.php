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
	
	}
?>