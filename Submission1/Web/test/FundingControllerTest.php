<?php
	$my_dir = dirname(__FILE__);

	require_once $my_dir . '/../controller/FundingController.php';
	require_once $my_dir . '/../model/URLMS.php';
	require_once $my_dir.'/../model/FundingAccount.php';
	
	class FundingControllerTest extends PHPUnit_Framework_TestCase
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
			$this->controller = new FundingController($this->urlms, $this->p);
		}
	
		protected function tearDown()
		{
		}
	}
?>