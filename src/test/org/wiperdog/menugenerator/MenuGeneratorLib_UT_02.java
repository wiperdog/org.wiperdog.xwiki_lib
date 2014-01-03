package org.wiperdog.menugenerator;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test method getListJobGroup
 * 
 */
public class MenuGeneratorLib_UT_02 {
	MenuGeneratorLib menuLib;
	List<String> groupItemsList;
	HashMap groupItemsMap;
	List parentList;
	HashMap<String, List> tmpMap;
	List<String> tmpOutput;
	List<String> tmpResultActual;

	@Before
	public void setUp() throws Exception {
		menuLib = new MenuGeneratorLib();
		// list data input for test:
		// [SQL_Server.Performance, SQL_Server.Database_Statistic, OS,
		// MySQL.Performance, MySQL.Database_Area]
		groupItemsList = new ArrayList<String>();
		groupItemsList.add("SQL_Server.Performance");
		groupItemsList.add("SQL_Server.Database_Area");
		groupItemsList.add("OS");
		groupItemsList.add("MySQL.Performance");
		groupItemsList.add("MySQL.Database_Area");
		// tmp data input for test
		tmpMap = new LinkedHashMap<String, List>();
		tmpMap.put("Database_Area", new ArrayList());
		tmpMap.put("Performance", new ArrayList());
		// map data input for test:
		// ["MySQL":["Database_Area":[], "Performance":[]],
		// "SQL_Server":["Database_Area":[], "Performance":[]], "OS":[]]
		groupItemsMap = new LinkedHashMap();
		groupItemsMap.put("MySQL", tmpMap);
		groupItemsMap.put("SQL_Server", tmpMap);
		groupItemsMap.put("OS", new ArrayList());
		// used for recursively to canculate parent key if data is the last node
		// before leaf
		// set value ["MySQL","Database_Area"]
		parentList = new ArrayList();
		parentList.add("MySQL");
		parentList.add("Database_Area");
		// set value of output need to compare
		// [SQL_Server.Database_Statistic, SQL_Server.Performance, OS,
		// MySQL.Database_Area, MySQL.Performance]
		tmpOutput = Arrays.asList(new String[] { "MySQL.Database_Area",
				"MySQL.Performance", "SQL_Server.Database_Area",
				"SQL_Server.Performance", "OS" });
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Check output with value of groupItemsMap is not a map empty (correct
	 * input data)
	 */
	@Test
	public void getListJobGroupTest01() {
		// get data of function getListJobGroup
		tmpResultActual = (ArrayList<String>) menuLib
				.getListJobGroup(groupItemsMap);
		assertEquals(tmpOutput, tmpResultActual);

	}

	/**
	 * Check output with two variable is groupItemsMap and parentList. Value of
	 * groupItemsMap is not empty and parentList is not empty (correct input
	 * data)
	 */
	@Test
	public void getListJobGroupTest02() {
		// set data output need to compare
		tmpOutput = Arrays.asList(new String[] {
				"MySQL.Database_Area.MySQL.Database_Area",
				"MySQL.Database_Area.MySQL.Performance",
				"MySQL.Database_Area.SQL_Server.Database_Area",
				"MySQL.Database_Area.SQL_Server.Performance",
				"MySQL.Database_Area.OS" });
		// get data of function getListJobGroup
		tmpResultActual = (List<String>) menuLib.getListJobGroup(groupItemsMap,
				parentList);
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * Check output with value of groupItemsList is not a list empty (correct
	 * input data)
	 */
	@Test
	public void getListJobGroupTest03() {
		// set data output need to compare
		tmpOutput = new ArrayList<String>();
		// get data of function getListJobGroup
		tmpResultActual = (List<String>) menuLib
				.getListJobGroup(groupItemsList);
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * Check output with two variable is groupItemsList and parentList. Value of
	 * groupItemsList is not a list empty and parentList is not empty (correct
	 * input data)
	 */
	@Test
	public void getListJobGroupTest04() {
		// set data output need to compare
		tmpOutput = Arrays.asList(new String[] { "MySQL.Database_Area" });
		// get data of function getListJobGroup
		tmpResultActual = (List<String>) menuLib.getListJobGroup(
				groupItemsList, parentList);
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * Check output with value of groupItemsMap is a map empty
	 */
	@Test
	public void getListJobGroupTest05() {
		// create groupItemsMap
		groupItemsMap = new HashMap();
		// get data of function getListJobGroup
		tmpResultActual = (List<String>) menuLib.getListJobGroup(groupItemsMap);
		assertTrue(tmpResultActual.size() == 0);
	}

	/**
	 * Check output with value of groupItemsMap is null
	 */
	@Test
	public void getListJobGroupTest06() {
		// set groupItemsMap null
		groupItemsMap = null;
		// get data of function getListJobGroup
		tmpResultActual = (List<String>) menuLib.getListJobGroup(groupItemsMap);
		assertTrue(tmpResultActual.size() == 0);
	}

	/**
	 * Check output with value of groupItemsList is a list empty
	 */
	@Test
	public void getListJobGroupTest07() {
		// create groupItemsList
		groupItemsList = new ArrayList<String>();
		// get data of function getListJobGroup
		tmpResultActual = (List<String>) menuLib
				.getListJobGroup(groupItemsList);
		assertTrue(tmpResultActual.size() == 0);
	}

	/**
	 * Check output with two variable is groupItemsMap and parentList. Value of
	 * groupItemsMap is not a map empty and parentList is null
	 */
	@Test
	public void getListJobGroupTest08() {
		// set parentList null
		parentList = null;
		// get data of function getListJobGroup
		tmpResultActual = (List<String>) menuLib.getListJobGroup(groupItemsMap,
				parentList);
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * Check output with two variable is groupItemsList and parentList. Value of
	 * groupItemsList is not a list empty and parentList is null
	 */
	@Test
	public void getListJobGroupTest09() {
		// set value of parentList is null
		parentList = null;
		// get data of function getListJobGroup
		tmpResultActual = (List<String>) menuLib.getListJobGroup(
				groupItemsList, parentList);
		assertTrue(tmpResultActual.size() == 0);
	}
}
