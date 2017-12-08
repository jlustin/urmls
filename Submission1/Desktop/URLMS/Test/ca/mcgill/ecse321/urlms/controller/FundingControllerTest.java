package ca.mcgill.ecse321.urlms.controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.urlms.application.URLMSApplication;
import ca.mcgill.ecse321.urlms.model.URLMS;
import ca.mcgill.ecse321.urlms.persistence.PersistenceXStream;

public class FundingControllerTest {

	private static URLMS urlms;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PersistenceXStream.initializeModelManager("urlms.xml");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		urlms = URLMSApplication.getURLMS();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFundingController() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddFundingAccount() {
		fail("Not yet implemented");
	}

	@Test
	public void testViewFundingAccounts() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddTransactionStringDoubleStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddTransactionStringDoubleStringInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFundingAccount() {
		fail("Not yet implemented");
	}

	@Test
	public void testInitiateFundingAccounts() {
		fail("Not yet implemented");
	}

	@Test
	public void testViewNetBalance() {
		fail("Not yet implemented");
	}

	@Test
	public void testViewBalanceForSpecificAccount() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddFunding() {
		fail("Not yet implemented");
	}

	@Test
	public void testEditFinancialAccount() {
		fail("Not yet implemented");
	}

	@Test
	public void testViewFundingAccountType() {
		fail("Not yet implemented");
	}

	@Test
	public void testViewFundingAccountBalance() {
		fail("Not yet implemented");
	}

	@Test
	public void testViewFundingAccountExpensesString() {
		fail("Not yet implemented");
	}

	@Test
	public void testViewFundingAccountExpensesInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetExpense() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveFundingAccountInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveFundingAccountString() {
		fail("Not yet implemented");
	}

}
