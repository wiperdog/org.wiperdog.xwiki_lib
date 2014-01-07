package org.wiperdog.policy;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test method getLevel
 * 
 */
public class PolicyStringLib_UT_01 {
	PolicyStringLib policy;
	String condition;
	HashMap mapConditionLevel;
	HashMap<String, String> mapConditionLevelStore;
	HashMap mapConditionLevelSubtyped;
	HashMap<String, String> tmpMapConditionLevel;
	String group;
	int tmpResultActual;

	@Before
	public void setUp() throws Exception {
		policy = new PolicyStringLib();
		// set value of condition for test
		condition = "(data.MaxUsedPct >= 95)";
		// create mapConditionLevel for test
		mapConditionLevel = new LinkedHashMap();
		// set value of mapConditionLevel of data Store
		mapConditionLevelStore = new LinkedHashMap<String, String>();
		mapConditionLevelStore.put("(data.MaxUsedPct >= 95)", "High");
		mapConditionLevelStore.put(
				"(data.MaxUsedPct >= 90) && (data.MaxUsedPct < 95)", "Medium");
		mapConditionLevelStore.put(
				"(data.MaxUsedPct > 80) && (data.MaxUsedPct < 90)", "Low");

		// set value of mapConditionLevel of data Subtyped
		mapConditionLevelSubtyped = new LinkedHashMap();
		// set data of group D
		tmpMapConditionLevel = new LinkedHashMap<String, String>();
		tmpMapConditionLevel.put("(data.UsedPct >= 95)", "High");
		tmpMapConditionLevel.put("(data.UsedPct >= 90) && (data.UsedPct < 95)",
				"High");
		mapConditionLevelSubtyped.put("D", tmpMapConditionLevel);
		// set data of group M
		tmpMapConditionLevel = new LinkedHashMap<String, String>();
		tmpMapConditionLevel.put("(data.UsedPct >= 90) && (data.UsedPct < 95)",
				"Medium");
		tmpMapConditionLevel.put("(data.UsedPct >= 95)", "Low");
		tmpMapConditionLevel.put("(data.UsedPct == 10)", "High");
		mapConditionLevelSubtyped.put("M", tmpMapConditionLevel);
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Check output with two variable is condition and mapConditionLevel data
	 * type is Store and level of the condition is High value of condition
	 * exists in mapConditionLevel.
	 * Expected: level of condition is High
	 */
	@Test
	public void getLevelTest01() {
		// set data typed is store
		mapConditionLevel = mapConditionLevelStore;
		// get data of function getLevel
		tmpResultActual = (Integer) policy.getLevel(condition,
				mapConditionLevel);
		assertTrue(tmpResultActual == 3);
	}

	/**
	 * Check output with two variable is condition and mapConditionLevel data
	 * type is Store and level of the condition is Medium value of condition
	 * exists in mapConditionLevel.
	 * Expected: level of condition is Medium
	 */
	@Test
	public void getLevelTest02() {
		// set data typed is store
		mapConditionLevel = mapConditionLevelStore;
		// set value of condition for test
		condition = "(data.MaxUsedPct >= 90) && (data.MaxUsedPct < 95)";
		// get data of function getLevel
		tmpResultActual = (Integer) policy.getLevel(condition,
				mapConditionLevel);
		assertTrue(tmpResultActual == 2);
	}

	/**
	 * Check output with two variable is condition and mapConditionLevel data
	 * type is Store and level of the condition is Low value of condition exists
	 * in mapConditionLevel.
	 * Expected: level of condition is Low
	 */
	@Test
	public void getLevelTest03() {
		// set data typed is store
		mapConditionLevel = mapConditionLevelStore;
		// set value of condition for test
		condition = "(data.MaxUsedPct > 80) && (data.MaxUsedPct < 90)";
		// get data of function getLevel
		tmpResultActual = (Integer) policy.getLevel(condition,
				mapConditionLevel);
		assertTrue(tmpResultActual == 1);
	}

	/**
	 * Check output with three variable is condition, mapConditionLevel and
	 * group data type is Subtyped and level of the condition is High value of
	 * condition exists in mapConditionLevel.
	 * Expected: level of condition is High
	 */
	@Test
	public void getLevelTest04() {
		// set data typed is subtyped
		mapConditionLevel = mapConditionLevelSubtyped;
		// set value of condition for test
		condition = "(data.UsedPct >= 95)";
		// set value of group
		group = "D";
		// get data of function getLevel
		tmpResultActual = (Integer) policy.getLevel(condition,
				mapConditionLevel, group);
		assertTrue(tmpResultActual == 3);
	}

	/**
	 * Check output with three variable is condition, mapConditionLevel and
	 * group data type is Subtyped and level of the condition is Medium value of
	 * condition exists in mapConditionLevel.
	 * Expected: level of condition is Medium
	 */
	@Test
	public void getLevelTest05() {
		// set data typed is subtyped
		mapConditionLevel = mapConditionLevelSubtyped;
		// set value of condition for test
		condition = "(data.UsedPct >= 90) && (data.UsedPct < 95)";
		// set value of group
		group = "M";
		// get data of function getLevel
		tmpResultActual = (Integer) policy.getLevel(condition,
				mapConditionLevel, group);
		assertTrue(tmpResultActual == 2);
	}

	/**
	 * Check output with three variable is condition, mapConditionLevel and
	 * group data type is Subtyped and level of the condition is Low value of
	 * condition exists in mapConditionLevel.
	 * Expected: level of condition is Low
	 */
	@Test
	public void getLevelTest06() {
		// set data typed is subtyped
		mapConditionLevel = mapConditionLevelSubtyped;
		// set value of condition for test
		condition = "(data.UsedPct >= 95)";
		// set value of group
		group = "M";
		// get data of function getLevel
		tmpResultActual = (Integer) policy.getLevel(condition,
				mapConditionLevel, group);
		assertTrue(tmpResultActual == 1);
	}

	/**
	 * Check output with value of condition is empty.
	 * Expected: exception occurs, not returning data
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void getLevelTest07() throws Exception {
		// set data typed is store
		mapConditionLevel = mapConditionLevelStore;
		// set value of condition is empty
		condition = "";
		// get data of function getLevel
		tmpResultActual = (Integer) policy.getLevel(condition,
				mapConditionLevel);
	}

	/**
	 * Check output with value of condition is null.
	 * Expected: exception occurs, not returning data
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void getLevelTest08() throws Exception {
		// set data typed is store
		mapConditionLevel = mapConditionLevelStore;
		// set value of condition is null
		condition = null;
		// get data of function getLevel
		tmpResultActual = (Integer) policy.getLevel(condition,
				mapConditionLevel);
	}

	/**
	 * Check output with value of mapConditionLevel is empty.
	 * Expected: exception occurs, not returning data
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void getLevelTest09() throws Exception {
		// get data of function getLevel
		tmpResultActual = (Integer) policy.getLevel(condition,
				mapConditionLevel);
	}

	/**
	 * Check output with value of mapConditionLevel is null.
	 * Expected: exception occurs, not returning data
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void getLevelTest10() throws Exception {
		// set value of mapConditionLevel is null
		mapConditionLevel = null;
		// get data of function getLevel
		tmpResultActual = (Integer) policy.getLevel(condition,
				mapConditionLevel);
	}

	/**
	 * Check output with value of group is empty.
	 * Expected: exception occurs, not returning data
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void getLevelTest11() throws Exception {
		// set data typed is subtyped
		mapConditionLevel = mapConditionLevelSubtyped;
		// set value of condition for test
		condition = "(data.UsedPct >= 95)";
		// set value of group is empty
		group = "";
		// get data of function getLevel
		tmpResultActual = (Integer) policy.getLevel(condition,
				mapConditionLevel, group);
	}

	/**
	 * Check output with value of a group in mapConditionLevel is empty data
	 * type is Subtyped.
	 * Expected: exception occurs, not returning data
	 */
	@Test
	public void getLevelTest12() {
		// set value of group D is empty
		mapConditionLevelSubtyped.put("D", new LinkedHashMap<String, String>());
		// set value of condition for test
		condition = "(data.UsedPct == 10)";
		// set value of group
		group = "M";
		// get data of function getLevel
		tmpResultActual = (Integer) policy.getLevel(condition,
				mapConditionLevelSubtyped, group);
		assertTrue(tmpResultActual == 3);
	}

	/**
	 * Check output with value of group is not D or M
	 * Expected: exception occurs, not returning data
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void getLevelTest13() throws Exception {
		// set data typed is subtyped
		mapConditionLevel = mapConditionLevelSubtyped;
		// set value of condition for test
		condition = "(data.UsedPct >= 95)";
		// set value of group
		group = "E";
		// get data of function getLevel
		tmpResultActual = (Integer) policy.getLevel(condition,
				mapConditionLevel, group);
	}

	/**
	 * Check output with two variable is condition and mapConditionLevel data
	 * type is Store and value of condition not exists in mapConditionLevel.
	 * Expected: exception occurs, not returning data
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void getLevelTest14() throws Exception {
		// set data typed is store
		mapConditionLevel = mapConditionLevelStore;
		// set value of condition for test
		condition = "(data.UsedPct >= 95)";
		// get data of function getLevel
		tmpResultActual = (Integer) policy.getLevel(condition,
				mapConditionLevel);
	}

	/**
	 * Check output with three variable is condition, mapConditionLevel and
	 * group data type is Subtyped and value of condition not exists in
	 * mapConditionLevel.
	 * Expected: exception occurs, not returning data
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void getLevelTest15() throws Exception {
		// set data typed is subtyped
		mapConditionLevel = mapConditionLevelSubtyped;
		// set value of condition for test
		condition = "(data.UsedPct == 10)";
		// set value of group
		group = "D";
		// get data of function getLevel
		tmpResultActual = (Integer) policy.getLevel(condition,
				mapConditionLevel, group);
	}
}
