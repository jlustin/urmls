package ca.mcgill.ecse321.urlms.controller;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.urlms.persistence.PersistenceXStream;

public class PersistenceTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		File file = new File("urlmsTest.xml");
		file.delete();
		PersistenceXStream.setFilename("urlmsTest.xml");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		File file = new File("urlmsTest.xml");
		file.delete();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInitializeModelManager() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveToXMLwithXStream() {
		fail("Not yet implemented");
	}

	@Test
	public void testLoadFromXMLwithXStream() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetFilename() {
		fail("Not yet implemented");
	}

}
