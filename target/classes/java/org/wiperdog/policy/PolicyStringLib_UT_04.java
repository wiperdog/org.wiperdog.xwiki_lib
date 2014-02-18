package org.wiperdog.policy;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test method getDataMessageAfterEdit
 * 
 */
public class PolicyStringLib_UT_04 {
	PolicyStringLib policy;
	String stringOfMessage;
	List<String> dataKey;
	List<String> dataKeyStore;
	String condition;
	String tmpResultActual;
	String tmpOutput;

	@Before
	public void setUp() throws Exception {
		policy = new PolicyStringLib();
		// set string of Policy for test
		stringOfMessage = "Dangerous: The size used of table space TablespaceName is too high!!!";
		// create listKey for test
		dataKey = Arrays.asList(new String[] { "TablespaceName",
				"CurrentUsedPct", "MaxFreeSizeKB", "MaxUsedPct" });
		// set data output need to compare
		tmpOutput = "Dangerous: The size used of table space ${data.TablespaceName} is too high!!!";
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Check output with stringOfMessage and dataKey is not empty,
	 * stringOfMessage contains a variable.
	 * Expected: each variable in the message will add value is "data."
	 */
	@Test
	public void getDataMessageAfterEditTest01() {
		// get data of function getDataMessageAfterEdit
		tmpResultActual = (String) policy.getDataMessageAfterEdit(
				stringOfMessage, dataKey);
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * Check output with stringOfMessage and dataKey is not empty,
	 * stringOfMessage contains many variable.
	 * Expected: each variable in the message will add value is "data."
	 */
	@Test
	public void getDataMessageAfterEditTest02() {
		// set many variable in stringOfMessage
		stringOfMessage = "Dangerous: The size used of table space TablespaceName is too high. Current size is MaxFreeSizeKB";
		// set data output need to compare
		tmpOutput = "Dangerous: The size used of table space ${data.TablespaceName} is too high. Current size is ${data.MaxFreeSizeKB}";
		// get data of function getDataMessageAfterEdit
		tmpResultActual = (String) policy.getDataMessageAfterEdit(
				stringOfMessage, dataKey);
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * Check output with stringOfMessage is empty.
	 * Expected: message is empty
	 */
	@Test
	public void getDataMessageAfterEditTest03() {
		// set value of stringOfMessage is empty
		stringOfMessage = "";
		// get data of function getDataMessageAfterEdit
		tmpResultActual = (String) policy.getDataMessageAfterEdit(
				stringOfMessage, dataKey);
		assertTrue(tmpResultActual.length() == 0);
	}

	/**
	 * Check output with stringOfMessage is null.
	 * Expected: message is empty
	 */
	@Test
	public void getDataMessageAfterEditTest04() {
		// set value of stringOfMessage is null
		stringOfMessage = null;
		// get data of function getDataMessageAfterEdit
		tmpResultActual = (String) policy.getDataMessageAfterEdit(
				stringOfMessage, dataKey);
		assertTrue(tmpResultActual.length() == 0);
	}

	/**
	 * Check output with dataKey is empty
	 * Expected: each variable in the message will not add value is "data."
	 */
	@Test
	public void getDataMessageAfterEditTest05() {
		// set value of stringOfMessage is empty
		dataKey = new ArrayList<String>();
		// set data output need to compare
		tmpOutput = "Dangerous: The size used of table space TablespaceName is too high!!!";
		// get data of function getDataMessageAfterEdit
		tmpResultActual = (String) policy.getDataMessageAfterEdit(
				stringOfMessage, dataKey);
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * Check output with dataKey is null.
	 * Expected: each variable in the message will not add value is "data."
	 */
	@Test
	public void getDataMessageAfterEditTest06() {
		// set value of stringOfMessage is empty
		dataKey = null;
		// set data output need to compare
		tmpOutput = "Dangerous: The size used of table space TablespaceName is too high!!!";
		// get data of function getDataMessageAfterEdit
		tmpResultActual = (String) policy.getDataMessageAfterEdit(
				stringOfMessage, dataKey);
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * Check output with stringOfMessage and dataKey is not empty, dataKey not
	 * contains variable of stringOfMessage.
	 * Expected: each variable in the message will not add value is "data."
	 */
	@Test
	public void getDataMessageAfterEditTest07() {
		// set value of stringOfMessage and not exist in dataKey
		stringOfMessage = "Dangerous: The size used of table space DataTestName is too high.";
		// set data output need to compare
		tmpOutput = "Dangerous: The size used of table space DataTestName is too high.";
		// get data of function getDataMessageAfterEdit
		tmpResultActual = (String) policy.getDataMessageAfterEdit(
				stringOfMessage, dataKey);
		assertEquals(tmpOutput, tmpResultActual);
	}

}
