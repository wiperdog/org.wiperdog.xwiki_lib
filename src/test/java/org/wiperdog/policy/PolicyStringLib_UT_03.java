package org.wiperdog.policy;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test method getDataConditionsAfterEdit
 * 
 */
public class PolicyStringLib_UT_03 {
	PolicyStringLib policy;
	String stringOfPolicy;
	List<String> dataKey;
	String condition;
	String tmpResultActual;
	String tmpOutput;

	@Before
	public void setUp() throws Exception {
		policy = new PolicyStringLib();
		// set value of dataKey for test
		dataKey = Arrays.asList(new String[] { "TablespaceName",
				"CurrentUsedPct", "MaxFreeSizeKB", "MaxUsedPct" });
		System.out.println(dataKey.getClass());
		// set string of Policy for test
		stringOfPolicy = "(MaxUsedPct >= 95)";
		// set data output need to compare
		tmpOutput = "(data.MaxUsedPct >= 95)";
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * check output with stringOfPolicy and dataKey is not empty, stringOfPolicy
	 * contains a variable.
	 * Expected: the condition will add value is "data."
	 */
	@Test
	public void getDataConditionsAfterEditTest01() {
		// get data of function getDataConditionsAfterEdit
		tmpResultActual = (String) policy.getDataConditionsAfterEdit(
				stringOfPolicy, dataKey);
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * check output with stringOfPolicy and dataKey is not empty, stringOfPolicy
	 * contains many variable.
	 * Expected: the condition will add value is "data."
	 */
	@Test
	public void getDataConditionsAfterEditTest02() {
		// set many variable in stringOfPolicy
		stringOfPolicy = "MaxUsedPct >= 95 && MaxFreeSizeKB == 100";
		// set data output need to compare
		tmpOutput = "data.MaxUsedPct >= 95 && data.MaxFreeSizeKB == 100";
		// get data of function getDataConditionsAfterEdit
		tmpResultActual = (String) policy.getDataConditionsAfterEdit(
				stringOfPolicy, dataKey);
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * check output with stringOfPolicy is empty.
	 * Expected: the condition is empty
	 */
	@Test
	public void getDataConditionsAfterEditTest03() {
		// set value of stringOfPolicy is empty
		stringOfPolicy = "";
		// get data of function getDataConditionsAfterEdit
		tmpResultActual = (String) policy.getDataConditionsAfterEdit(
				stringOfPolicy, dataKey);
		assertTrue(tmpResultActual.length() == 0);
	}

	/**
	 * check output with stringOfPolicy is null.
	 * Expected: the condition is empty
	 */
	@Test
	public void getDataConditionsAfterEditTest04() {
		// set value of stringOfPolicy is null
		stringOfPolicy = null;
		// get data of function getDataConditionsAfterEdit
		tmpResultActual = (String) policy.getDataConditionsAfterEdit(
				stringOfPolicy, dataKey);
		assertTrue(tmpResultActual.length() == 0);
	}

	/**
	 * check output with dataKey is empty.
	 * Expected: the condition will not add value is "data."
	 */
	@Test
	public void getDataConditionsAfterEditTest05() {
		// set value of stringOfPolicy is empty
		dataKey = new ArrayList<String>();
		// set data output need to compare
		tmpOutput = "(MaxUsedPct >= 95)";
		// get data of function getDataConditionsAfterEdit
		tmpResultActual = (String) policy.getDataConditionsAfterEdit(
				stringOfPolicy, dataKey);
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * check output with dataKey is null.
	 * Expected: the condition will not add value is "data."
	 */
	@Test
	public void getDataConditionsAfterEditTest06() {
		// set value of stringOfPolicy is empty
		dataKey = null;
		// set data output need to compare
		tmpOutput = "(MaxUsedPct >= 95)";
		// get data of function getDataConditionsAfterEdit
		tmpResultActual = (String) policy.getDataConditionsAfterEdit(
				stringOfPolicy, dataKey);
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * check output with stringOfPolicy and dataKey is not empty, dataKey not
	 * contains variable of stringOfPolicy.
	 * Expected: the condition will not add value is "data."
	 */
	@Test
	public void getDataConditionsAfterEditTest07() {
		// set value of stringOfPolicy and not exist in dataKey
		stringOfPolicy = "(DataTest >= 95)";
		// set data output need to compare
		tmpOutput = "(DataTest >= 95)";
		// get data of function getDataConditionsAfterEdit
		tmpResultActual = (String) policy.getDataConditionsAfterEdit(
				stringOfPolicy, dataKey);
		assertEquals(tmpOutput, tmpResultActual);
	}

}
