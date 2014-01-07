package org.wiperdog.wiperdoglogger;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test method log
 */
public class WiperdogLogger4Xwiki_UT_01 {
	WiperdogLogger4Xwiki logger;
	String message;
	String tmpResultActual;
	String tmpOutput;
	int level;

	@Before
	public void setUp() throws Exception {
		logger = new WiperdogLogger4Xwiki("Wiperdog");
		// set value of message for test
		message = "This is message for test!";
		// set data output need to compare
		tmpOutput = "WARN [Wiperdog] This is message for test!";
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Check output with level and message is not empty. Value of level is 1.
	 * Expected: return string with level of log is WARN and message is
	 * "This is message for test!"
	 */
	@Test
	public void logTest01() {
		// set value of level for test
		level = 1;
		// get data of function log
		tmpResultActual = (logger.log(level, message)).toString();
		tmpResultActual = tmpResultActual.replaceAll("[0-9.:-]", "").trim();
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * Check output with level and message is not empty. Value of level is 2.
	 * Expected: return level of log is INFO and message is
	 * "This is message for test!"
	 */
	@Test
	public void logTest02() {
		// set value of level for test
		level = 2;
		// set data output need to compare
		tmpOutput = "INFO [Wiperdog] This is message for test!";
		// get data of function log
		tmpResultActual = (logger.log(level, message)).toString();
		tmpResultActual = tmpResultActual.replaceAll("[0-9.:-]", "").trim();
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * Check output with level and message is not empty. Value of level is 3.
	 * Expected: return level of log is TRACE and message is
	 * "This is message for test!"
	 */
	@Test
	public void logTest03() {
		// set value of level for test
		level = 3;
		// set data output need to compare
		tmpOutput = "TRACE [Wiperdog] This is message for test!";
		// get data of function log
		tmpResultActual = (logger.log(level, message)).toString();
		tmpResultActual = tmpResultActual.replaceAll("[0-9.:-]", "").trim();
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * Check output with level and message is not empty. Value of level is 4.
	 * Expected: return level of log is DEBUG and message is
	 * "This is message for test!"
	 */
	@Test
	public void logTest04() {
		// set value of level for test
		level = 4;
		// set data output need to compare
		tmpOutput = "DEBUG [Wiperdog] This is message for test!";
		// get data of function log
		tmpResultActual = (logger.log(level, message)).toString();
		tmpResultActual = tmpResultActual.replaceAll("[0-9.:-]", "").trim();
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * Check output with level and message is not empty. Value of level is 0.
	 * Expected: return level of log is null and message is
	 * "This is message for test!"
	 */
	@Test
	public void logTest05() {
		// set value of level for test
		level = 0;
		// set data output need to compare
		tmpOutput = "null [Wiperdog] This is message for test!";
		// get data of function log
		tmpResultActual = (logger.log(level, message)).toString();
		tmpResultActual = tmpResultActual.replaceAll("[0-9.:-]", "").trim();
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * Check output with level and message is not empty. Value of message is
	 * empty. 
	 * Expected: return level of log is WARN and message is empty
	 */
	@Test
	public void logTest06() {
		// set value of level for test
		level = 1;
		// set value of message is empty
		message = "";
		// set data output need to compare
		tmpOutput = "WARN [Wiperdog]";
		// get data of function log
		tmpResultActual = (logger.log(level, message)).toString();
		tmpResultActual = tmpResultActual.replaceAll("[0-9.:-]", "").trim();
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * Check output with level and message is not empty. Value of message is
	 * null. 
	 * Expected: return level of log is WARN and message is null
	 */
	@Test
	public void logTest07() {
		// set value of level for test
		level = 1;
		// set value of message is null
		message = null;
		// set data output need to compare
		tmpOutput = "WARN [Wiperdog] null";
		// get data of function log
		tmpResultActual = (logger.log(level, message)).toString();
		tmpResultActual = tmpResultActual.replaceAll("[0-9.:-]", "").trim();
		assertEquals(tmpOutput, tmpResultActual);
	}
}
