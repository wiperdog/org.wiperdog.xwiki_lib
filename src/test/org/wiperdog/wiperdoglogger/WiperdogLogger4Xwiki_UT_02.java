package org.wiperdog.wiperdoglogger;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test method getLevel
 */
public class WiperdogLogger4Xwiki_UT_02 {
	WiperdogLogger4Xwiki logger;
	String tmpResultActual;
	String tmpOutput;
	int level;

	@Before
	public void setUp() throws Exception {
		logger = new WiperdogLogger4Xwiki("Wiperdog");
		// set value of level for test
		level = 1;
		// set data output need to compare
		tmpOutput = "WARN";
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Check output with level is not empty. Value of level is 1. 
	 * Expected: return level of log is WARN
	 */
	@Test
	public void getLevelTest01() {
		// get data of function getLevel
		tmpResultActual = (logger.getLevel(level)).toString();
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * Check output with level is not empty. Value of level is 2. 
	 * Expected: return level of log is INFO
	 */
	@Test
	public void getLevelTest02() {
		// set value of level for test
		level = 2;
		// set data output need to compare
		tmpOutput = "INFO";
		// get data of function getLevel
		tmpResultActual = (logger.getLevel(level)).toString();
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * Check output with level is not empty. Value of level is 3. 
	 * Expected: return level of log is TRACE
	 */
	@Test
	public void getLevelTest03() {
		// set value of level for test
		level = 3;
		// set data output need to compare
		tmpOutput = "TRACE";
		// get data of function getLevel
		tmpResultActual = (logger.getLevel(level)).toString();
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * Check output with level is not empty. Value of level is 4. 
	 * Expected: return level of log is DEBUG
	 */
	@Test
	public void getLevelTest04() {
		// set value of level for test
		level = 4;
		// set data output need to compare
		tmpOutput = "DEBUG";
		// get data of function getLevel
		tmpResultActual = (logger.getLevel(level)).toString();
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * Check output with level is not empty. Value of level is 0. 
	 * Expected: exception occur occurs with other levels 1,2,3,4
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void getLevelTest05() throws Exception {
		// set value of level for test
		level = 0;
		// get data of function getLevel
		tmpResultActual = (logger.getLevel(level)).toString();
	}
}
