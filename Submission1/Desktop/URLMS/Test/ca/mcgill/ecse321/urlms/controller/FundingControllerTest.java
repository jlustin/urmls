package ca.mcgill.ecse321.urlms.controller;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.urlms.application.URLMSApplication;
import ca.mcgill.ecse321.urlms.model.FundingAccount;
import ca.mcgill.ecse321.urlms.model.InventoryItem;
import ca.mcgill.ecse321.urlms.model.Lab;
import ca.mcgill.ecse321.urlms.model.URLMS;
import ca.mcgill.ecse321.urlms.persistence.PersistenceXStream;

public class FundingControllerTest {

	private static URLMS urlms;
	private static FundingController controller;
	private static Lab aLab;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PersistenceXStream.setFilename("urlmsTest.xml");
		URLMSApplication.setFilename("urlmsTest.xml");
		PersistenceXStream.initializeModelManager("urlmsTest.xml");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		urlms = URLMSApplication.load();
		URLMSApplication.setURLMS(urlms);
		controller = new FundingController();
		aLab = urlms.getLab(0);
		
	}

	@After
	public void tearDown() throws Exception {
		urlms.delete();
		File file = new File("urlmsTest.xml");
		file.delete();
	}

	@Test
	public void testAddFundingAccount() {
		String err = "";
		String name = "test add";
		double balance = 123;
		List<FundingAccount> testList = null;
		try {
			controller.addFundingAccount(name, balance);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			err = e.getMessage();
		}
		
		try {
			List<FundingAccount> accounts = controller.viewFundingAccounts();
			testList = accounts;
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals("", err);
		assertEquals("test add", aLab.getFundingAccount(0).getType());
		assertEquals(1, testList.size());
	}

	@Test
	public void testViewFundingAccounts() {
		String err = "";
		String name = "test view";
		double balance = 123;
		List<FundingAccount> testList = null;
		try {
			controller.addFundingAccount(name, balance);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			err = e.getMessage();
		}
		
		try {
			List<FundingAccount> accounts = controller.viewFundingAccounts();
			testList = accounts;
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals("", err);
		assertEquals("test view", aLab.getFundingAccount(0).getType());
		assertEquals(1, testList.size());
	}

	@Test
	public void testAddTransaction() {
		String err = "";
		String name = "test transaction";
		double balance = 123;
		List<FundingAccount> testList = null;
		try {
			controller.addFundingAccount(name, balance);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			err = e.getMessage();
		}
		
		controller.addTransaction("1/2/3", 111, "test type", 0);
		
		try {
			assertEquals("1/2/3", controller.getFundingAccount("test transaction").getExpense(0).getDate());
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			assertEquals("test type", controller.getFundingAccount("test transaction").getExpense(0).getType());
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			assertEquals(1, controller.getFundingAccount("test transaction").getExpenses().size());
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals("", err);
		
	}

	@Test
	public void testAddTransactionStringDoubleStringInt() {
		String err = "";
		String name = "test transaction";
		double balance = 123;
		List<FundingAccount> testList = null;
		try {
			controller.addFundingAccount(name, balance);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			err = e.getMessage();
		}
		
		controller.addTransaction("1/2/3", 111, "test type", 0);
		
		try {
			assertEquals("1/2/3", controller.getFundingAccount("test transaction").getExpense(0).getDate());
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			assertEquals("test type", controller.getFundingAccount("test transaction").getExpense(0).getType());
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			assertEquals(1, controller.getFundingAccount("test transaction").getExpenses().size());
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals("", err);
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
