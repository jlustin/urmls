<?php
	$my_dir = dirname(__FILE__); 
require_once $my_dir . '/../model/StaffManager.php';
require_once $my_dir . '/../model/StaffMember.php';
require_once $my_dir . '/../persistence/persistence.php';

class test extends PHPUnit_Framework_TestCase
{
	protected $urlms;

	protected function setUp()
	{
		$this->urlms = URLMS::newInstance(0);
	}

	protected function tearDown()
	{
	}

	public function test()
	{
		// 1. Create test data
		$sm = $this->urlms->getStaffManager();
		$staff = new StaffMember("Frank", "007", $sm);
		$sm->addStaffMember($staff);
		
		// 2. Write all of the data
		$pers = new Persistence("test.txt");
		$pers->writeDataToStore($this->urlms);
		
		// 3. Clear the data from memory		
		$this->urlms->delete();

		$this->assertEquals(0, count($this->urlms->getStaffManager()->getStaffMembers()));

		// 4. Load it back in
		$urlms = $pers->loadDataFromStore();

		// 5. Check that we got it back
		$this->assertEquals(1, count($this->urlms->getStaffManager()->getStaffMembers()));
		$myStaff = $urlms->getStaffManager()->getStaffMember_index(0);
		$this->assertEquals("Frank", $myStaff->getName());
	}

}
?>