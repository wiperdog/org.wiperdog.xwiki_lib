package org.wiperdog.policy;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test method convertListToString
 * 
 */
public class PolicyStringLib_UT_02 {
	PolicyStringLib policy;
	List<String> listData;
	String tmpOutput;
	String tmpResultActual;
	String concatStr;

	@Before
	public void setUp() throws Exception {
		policy = new PolicyStringLib();
		// set data input for test ["MySQL","SQL_Server"]
		listData = new ArrayList<String>();
		listData.add("MySQL");
		listData.add("SQL_Server");
		// set data output need to compare
		tmpOutput = "MySQL|SQL_Server";
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Check output with listData is not empty.
	 * Expected: return string has character '|'
	 */
	@Test
	public void convertListToStringTest01() {
		// get data of function convertListToString
		tmpResultActual = (String) policy.convertListToString(listData);
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * Check output with two variable is listData and concatStr, value of
	 * listData and concatStr is not empty, value of concatStr is "/".
	 * Expected: return string has character '/'
	 */
	@Test
	public void convertListToStringTest02() {
		// set data concatStr
		concatStr = "/";
		// set data output need to compare
		tmpOutput = "MySQL/SQL_Server";
		// get data of function convertListToString
		tmpResultActual = (String) policy.convertListToString(listData,
				concatStr);
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * Check output is string empty, with value of listData is empty.
	 * Expected: return string is empty
	 */
	@Test
	public void convertListToStringTest03() {
		// create listData
		listData = new ArrayList<String>();
		// get data of function convertListToString
		tmpResultActual = (String) policy.convertListToString(listData);
		assertTrue(tmpResultActual.length() == 0);
	}

	/**
	 * Check output is string empty, with value of listData is null.
	 * Expected: return string is empty
	 */
	@Test
	public void convertListToStringTest04() {
		// set listData is null
		listData = null;
		// get data of function convertListToString
		tmpResultActual = (String) policy.convertListToString(listData);
		assertTrue(tmpResultActual.length() == 0);
	}

	/**
	 * Check output with value of concatStr is empty.
	 * Expected: return string is MySQLSQL_Server 
	 */
	@Test
	public void convertListToStringTest05() {
		// set data concatStr is null
		concatStr = "";
		// get data of function convertListToString
		tmpResultActual = (String) policy.convertListToString(listData,
				concatStr);
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * Check output is string empty, with value of concatStr is null.
	 * Expected: return string is empty
	 */
	@Test
	public void convertListToStringTest06() {
		// set data concatStr is null
		concatStr = null;
		// get data of function convertListToString
		tmpResultActual = (String) policy.convertListToString(listData, concatStr);
		assertEquals(tmpOutput, tmpResultActual);
	}
}