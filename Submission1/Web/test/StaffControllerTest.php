<?php
	$my_dir = dirname(__FILE__);

	require_once $my_dir . '/../controller/StaffController.php';
	require_once $my_dir . '/../model/URLMS.php';
	
	
	class StaffControllerTest extends PHPUnit_Framework_TestCase
	{
		protected $urlms;
		protected $controller;
	
		protected function setUp()
		{
			
			$this->urlms = new URLMS();
			$lab = Lab::newInstance(0, $this->urlms);
			$this->urlms->addLab($lab);
			
			$this->controller = new StaffController($this->urlms);
			(new Persistence("test.txt"))->writeDataToStore($this->urlms);
		}
	
		protected function tearDown()
		{
		}
	
		public function testAddStaffMember()
		{
			// 1. Create test data
			$this->controller->addStaff("bob");
			
			// 2. Write all of the data
			$pers = new Persistence("test.txt");
			$pers->writeDataToStore($this->urlms);
	
			// 3. Clear the data from memory
			//$this->urlms->delete();
	
			$this->assertEquals(0, count($this->urlms->getLab_index(0)->getStaff()->getStaffMembers()));
	
			// 4. Load it back in
			$this->urlms = $pers->loadDataFromStore();
	
			// 5. Check that we got it back
			$this->assertEquals(1, count($this->urlms->getLab_index(0)->getStaffManager()->getStaffMembers()));
			$myStaff = $this->urlms->getLab_index(0)->getStaffMember_index(0);
			$this->assertEquals("bob", $myStaff->getName());
		}
	
	}
?>