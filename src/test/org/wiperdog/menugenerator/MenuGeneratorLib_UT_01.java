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
 * Test method getMenuItemsStr
 * 
 */
public class MenuGeneratorLib_UT_01 {
	MenuGeneratorLib menuLib;
	List<String> treeItemList;
	HashMap treeItemMap;
	HashMap<String, List> tmpMap;
	HashMap<String, List> mapCollection;
	List parentList;
	String tmpOutput;
	String tmpResultActual;

	@Before
	public void setUp() throws Exception {
		menuLib = new MenuGeneratorLib();
		/*
		 * set data collections for test:
		 * [MySQL.Database_Area:[MySQL.Database_Area.InnoDBTablespace_Free,
		 * MySQL.Database_Area.Top30Database],
		 * MySQL.Performance:[MySQL.Performance.InnoDBBufferPool],
		 * OS:[OS.CPU_Windows, OS.Process_Windows],
		 * SQL_Server.Database_Area:[SQL_Server.Database_Area.Database_free],
		 * SQL_Server.Performance:[SQL_Server.Performance.Plan_Cache_Hit_Ratio,
		 * SQL_Server.Performance.Wait_Status]]
		 */
		mapCollection = new HashMap<String, List>();
		mapCollection.put(
				"MySQL.Database_Area",
				Arrays.asList(new String[] {
						"MySQL.Database_Area.InnoDBTablespace_Free",
						"MySQL.Database_Area.Top30Database" }));
		mapCollection.put("MySQL.Performance", Arrays
				.asList(new String[] { "MySQL.Performance.InnoDBBufferPool" }));
		mapCollection.put(
				"OS",
				Arrays.asList(new String[] { "OS.CPU_Windows",
						"OS.Process_Windows" }));
		mapCollection
				.put("SQL_Server.Database_Area",
						Arrays.asList(new String[] { "SQL_Server.Database_Area.Database_free" }));
		mapCollection.put(
				"SQL_Server.Performance",
				Arrays.asList(new String[] {
						"SQL_Server.Performance.Plan_Cache_Hit_Ratio",
						"SQL_Server.Performance.Wait_Status" }));
		// list data input for test:
		// [SQL_Server.Performance, SQL_Server.Database_Statistic, OS,
		// MySQL.Performance, MySQL.Database_Area]
		treeItemList = new ArrayList<String>();
		treeItemList.add("MySQL.Database_Area");
		treeItemList.add("MySQL.Performance");
		// tmp data input for test
		tmpMap = new LinkedHashMap<String, List>();
		tmpMap.put("Database_Area", new ArrayList());
		tmpMap.put("Performance", new ArrayList());
		// map data input for test:
		// ["MySQL":["Database_Area":[], "Performance":[]],
		// "SQL_Server":["Database_Area":[], "Performance":[]], "OS":[]]
		treeItemMap = new LinkedHashMap();
		treeItemMap.put("MySQL", tmpMap);
		treeItemMap.put("SQL_Server", tmpMap);
		treeItemMap.put("OS", new ArrayList());
		// set data parentList
		parentList = new ArrayList();
		parentList.add("MySQL");
		parentList.add("Database_Area");
		// set data output need to compare
		tmpOutput = "<ul id='treemenu2' class='treeview'><li>MySQL<ul id='treemenu2' class='treeview'><li>Database_Area<ul><li><a>MySQL.Database_Area.InnoDBTablespace_Free</a></li><li><a>MySQL.Database_Area.Top30Database</a></li></ul></li><li>Performance<ul><li><a>MySQL.Performance.InnoDBBufferPool</a></li></ul></li></ul></li><li>SQL_Server<ul id='treemenu2' class='treeview'><li>Database_Area<ul><li><a>SQL_Server.Database_Area.Database_free</a></li></ul></li><li>Performance<ul><li><a>SQL_Server.Performance.Plan_Cache_Hit_Ratio</a></li><li><a>SQL_Server.Performance.Wait_Status</a></li></ul></li></ul></li><li>OS<ul><li><a>OS.CPU_Windows</a></li><li><a>OS.Process_Windows</a></li></ul></li></ul>";
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Check output with two variable is treeItemMap and mapCollection. Value of
	 * treeItemMap is not a map empty and mapCollection is not empty (correct
	 * input data)
	 */
	@Test
	public void getMenuItemsStrTest01() {
		// get data of function getMenuItemsStr
		tmpResultActual = (String) menuLib.getMenuItemsStr(treeItemMap,
				mapCollection);
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * Check output with three variable is treeItemMap, mapCollection and
	 * parentList. Value of treeItemMap is not a map empty, mapCollection and
	 * parentList is not empty (correct input data)
	 */
	@Test
	public void getMenuItemsStrTest02() {
		// set data output need to compare
		tmpOutput = "<ul id='treemenu2' class='treeview'><li>MySQL<ul id='treemenu2' class='treeview'><li>Database_Area<ul></ul></li><li>Performance<ul></ul></li></ul></li><li>SQL_Server<ul id='treemenu2' class='treeview'><li>Database_Area<ul></ul></li><li>Performance<ul></ul></li></ul></li><li>OS<ul></ul></li></ul>";
		// get data of function getMenuItemsStr
		tmpResultActual = (String) menuLib.getMenuItemsStr(treeItemMap,
				mapCollection, parentList);
		System.out.println(tmpOutput);
		System.out.println(tmpResultActual);
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * Check output with two variable is treeItemList and mapCollection. Value
	 * of treeItemList is not a list empty and mapCollection is not empty
	 * (correct input data)
	 */
	@Test
	public void getMenuItemsStrTest03() {
		// set data output need to compare
		tmpOutput = "<ul></ul>";
		// get data of function getMenuItemsStr
		tmpResultActual = (String) menuLib.getMenuItemsStr(treeItemList,
				mapCollection);
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * Check output with three variable is treeItemList, mapCollection and
	 * parentList. Value of treeItemList is not a list empty, mapCollection and
	 * parentList is not empty (correct input data)
	 */
	@Test
	public void getMenuItemsStrTest04() {
		// set data output need to compare
		tmpOutput = "<ul><li><a>MySQL.Database_Area.InnoDBTablespace_Free</a></li><li><a>MySQL.Database_Area.Top30Database</a></li></ul>";
		// get data of function getMenuItemsStr
		tmpResultActual = (String) menuLib.getMenuItemsStr(treeItemList,
				mapCollection, parentList);
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * Check output with value of treeItemMap is a map empty
	 */
	@Test
	public void getMenuItemsStrTest05() {
		// create treeItemMap
		treeItemMap = new HashMap();
		// set data output need compare
		tmpOutput = "<ul id='treemenu2' class='treeview'></ul>";
		// get data of function getMenuItemsStr
		tmpResultActual = (String) menuLib.getMenuItemsStr(treeItemMap,
				mapCollection);
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * Check output with value of treeItemMap is null
	 */
	@Test
	public void getMenuItemsStrTest06() {
		// set treeItemMap null
		treeItemMap = null;
		// set data output need compare
		tmpOutput = "";
		// get data of function getMenuItemsStr
		tmpResultActual = (String) menuLib.getMenuItemsStr(treeItemMap,
				mapCollection);
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * Check output with value of mapCollection is a map empty and treeItemMap
	 * is not a map empty
	 */
	@Test
	public void getMenuItemsStrTest07() {
		// create mapCollection
		mapCollection = new HashMap();
		// set data output need compare
		tmpOutput = "<ul id='treemenu2' class='treeview'><li>MySQL<ul id='treemenu2' class='treeview'><li>Database_Area<ul></ul></li><li>Performance<ul></ul></li></ul></li><li>SQL_Server<ul id='treemenu2' class='treeview'><li>Database_Area<ul></ul></li><li>Performance<ul></ul></li></ul></li><li>OS<ul></ul></li></ul>";
		// get data of function getMenuItemsStr
		tmpResultActual = (String) menuLib.getMenuItemsStr(treeItemMap,
				mapCollection);
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * Check output with value of mapCollection is null and treeItemMap is not a
	 * map empty
	 */
	@Test
	public void getMenuItemsStrTest08() {
		// set mapCollection null
		mapCollection = null;
		// set data output need compare
		tmpOutput = "<ul id='treemenu2' class='treeview'><li>MySQL<ul id='treemenu2' class='treeview'><li>Database_Area<ul></ul></li><li>Performance<ul></ul></li></ul></li><li>SQL_Server<ul id='treemenu2' class='treeview'><li>Database_Area<ul></ul></li><li>Performance<ul></ul></li></ul></li><li>OS<ul></ul></li></ul>";
		// get data of function getMenuItemsStr
		tmpResultActual = (String) menuLib.getMenuItemsStr(treeItemMap,
				mapCollection);
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * Check output with value of treeItemList is a list empty and mapCollection
	 * is not empty
	 */
	@Test
	public void getMenuItemsStrTest09() {
		// create treeItemList
		treeItemList = new ArrayList<String>();
		// set data output need compare
		tmpOutput = "<ul></ul>";
		// get data of function getMenuItemsStr
		tmpResultActual = (String) menuLib.getMenuItemsStr(treeItemList,
				mapCollection);
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * Check output with value of mapCollection is a map empty and treeItemList
	 * is not a list empty
	 */
	@Test
	public void getMenuItemsStrTest10() {
		// create mapCollection
		mapCollection = new HashMap();
		// set data output need compare
		tmpOutput = "<ul></ul>";
		// get data of function getMenuItemsStr
		tmpResultActual = (String) menuLib.getMenuItemsStr(treeItemList,
				mapCollection);
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * Check output with value of mapCollection is null and treeItemList is not
	 * a list empty
	 */
	@Test
	public void getMenuItemsStrTest11() {
		// set mapCollection null
		mapCollection = null;
		// set data output need compare
		tmpOutput = "<ul></ul>";
		// get data of function getMenuItemsStr
		tmpResultActual = (String) menuLib.getMenuItemsStr(treeItemList,
				mapCollection);
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * Check output with value of parentList is null and treeItemMap is not a
	 * map empty
	 */
	@Test
	public void getMenuItemsStrTest12() {
		// set parentList null
		parentList = null;
		// set data output need to compare
		tmpOutput = "<ul id='treemenu2' class='treeview'><li>MySQL<ul id='treemenu2' class='treeview'><li>Database_Area<ul><li><a>MySQL.Database_Area.InnoDBTablespace_Free</a></li><li><a>MySQL.Database_Area.Top30Database</a></li></ul></li><li>Performance<ul><li><a>MySQL.Performance.InnoDBBufferPool</a></li></ul></li></ul></li><li>SQL_Server<ul id='treemenu2' class='treeview'><li>Database_Area<ul><li><a>SQL_Server.Database_Area.Database_free</a></li></ul></li><li>Performance<ul><li><a>SQL_Server.Performance.Plan_Cache_Hit_Ratio</a></li><li><a>SQL_Server.Performance.Wait_Status</a></li></ul></li></ul></li><li>OS<ul><li><a>OS.CPU_Windows</a></li><li><a>OS.Process_Windows</a></li></ul></li></ul>";
		// get data of function getMenuItemsStr
		tmpResultActual = (String) menuLib.getMenuItemsStr(treeItemMap,
				mapCollection, parentList);
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * Check output with value of parentList is null and treeItemList is not a
	 * list empty
	 */
	@Test
	public void getMenuItemsStrTest13() {
		// set parentList null
		parentList = null;
		// set data output need to compare
		tmpOutput = "<ul></ul>";
		// get data of function getMenuItemsStr
		tmpResultActual = (String) menuLib.getMenuItemsStr(treeItemList,
				mapCollection, parentList);
		assertEquals(tmpOutput, tmpResultActual);
	}
}
