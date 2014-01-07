package org.wiperdog.consoleservice;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.wiperdog.consoleservice.ConsoleServiceLib;

/**
 * Test method buildCmdList
 * 
 */
public class ConsoleServiceLib_UT_01 {

	ConsoleServiceLib service;
	HashMap<String, Map<String, String>> params;
	List<String> listCmd;
	List<String> tmpListCmd;
	HashMap<String, String> tmpMap;
	String psExec;

	@Before
	public void setUp() throws Exception {
		service = new ConsoleServiceLib();
		// list command
		listCmd = new ArrayList<String>();
		// set params for test (value of params is taken from the conf.params
		// file)
		params = new HashMap<String, Map<String, String>>();
		// set data command psExec
		psExec = "cmd /c echo %PSTOOLS% \\psExec.exe";
		// set value of params wiperdog_path
		tmpMap = new HashMap<String, String>();
		tmpMap.put("os", "win");
		tmpMap.put("host", "10.0.0.184");
		tmpMap.put("user", "thanhmx");
		tmpMap.put("pass", "insight");
		tmpMap.put("path", "D:\\testWiperdog\\1911Wiperdog");
		params.put("wiperdog_path", tmpMap);
		params.put("test", new HashMap<String, String>());
		// set data output need to compare
		tmpListCmd = new ArrayList<String>();
		tmpListCmd.add(psExec);
		tmpListCmd.add("\\\\10.0.0.184");
		tmpListCmd.add("-i");
		tmpListCmd.add("-accepteula");
		tmpListCmd.add("-u");
		tmpListCmd.add("\"thanhmx\"");
		tmpListCmd.add("-p");
		tmpListCmd.add("\"insight\"");
	}

	@After
	public void tearDown() throws Exception {
		listCmd = new ArrayList<String>();
	}

	/**
	 * Check output with isLocalhost: true and isInteractive: true
	 * Expected: value of listCmd is empty
	 */
	@Test
	public void buildCmdListTest01() {
		// get data of function buildCmdList
		listCmd = (ArrayList) service.buildCmdList(true, psExec, listCmd, true,
				params);
		assertTrue(listCmd.size() == 0);
	}

	/**
	 * Check output with isLocalhost: false and isInteractive: true
	 * Expected: value of listCmd containing a variable is -i
	 */
	@Test
	public void buildCmdListTest02() {
		// get data of function buildCmdList
		listCmd = (ArrayList) service.buildCmdList(false, psExec, listCmd,
				true, params);
		assertEquals(tmpListCmd, listCmd);
	}

	/**
	 * Check output with isLocalhost: true and isInteractive: false
	 * Expected: value of listCmd is empty
	 */
	@Test
	public void buildCmdListTest03() {
		// get data of function buildCmdList
		listCmd = (ArrayList) service.buildCmdList(true, psExec, listCmd,
				false, params);
		assertTrue(listCmd.size() == 0);
	}

	/**
	 * Check output with isLocalhost: false and isInteractive: false
	 * Expected: value of listCmd not contain a variable is -i
	 */
	@Test
	public void buildCmdListTest04() {
		// remove -i because tmp output need to compare not contains -i
		tmpListCmd.remove("-i");
		// get data of function buildCmdList
		listCmd = (ArrayList) service.buildCmdList(false, psExec, listCmd,
				false, params);
		assertEquals(tmpListCmd, listCmd);
	}

	/**
	 * Check output with isLocalhost is empty
	 * Expected: value of listCmd is not empty (correct data)
	 */
	@Test
	public void buildCmdListTest05() {
		// get data of function buildCmdList
		listCmd = (ArrayList) service.buildCmdList("", psExec, listCmd, false,
				params);
		assertTrue(listCmd.size() > 0);
	}

	/**
	 * Check output with isLocalhost is null
	 * Expected: value of listCmd is not empty (correct data)
	 */
	@Test
	public void buildCmdListTest06() {
		// get data of function buildCmdList
		listCmd = (ArrayList) service.buildCmdList(null, psExec, listCmd,
				false, params);
		assertTrue(listCmd.size() > 0);
	}

	/**
	 * Check output with psExec is empty
	 * Expected: value of psExec in listCmd is empty
	 */
	@Test
	public void buildCmdListTest07() {
		// replace psExec because tmp output need to compare contains psExec is
		// empty
		tmpListCmd.set(0, "");
		// set value of psExec is empty
		psExec = "";
		// get data of function buildCmdList
		listCmd = (ArrayList) service.buildCmdList(false, psExec, listCmd,
				true, params);
		assertEquals(tmpListCmd, listCmd);
	}

	/**
	 * Check output with psExec is null
	 * Expected: value of psExec in listCmd is null
	 */
	@Test
	public void buildCmdListTest08() {
		// replace psExec because tmp output need to compare contains psExec is
		// null
		tmpListCmd.set(0, null);
		// set value of psExec is null
		psExec = null;
		// get data of function buildCmdList
		listCmd = (ArrayList) service.buildCmdList(false, psExec, listCmd,
				true, params);
		assertEquals(tmpListCmd, listCmd);
	}

	/**
	 * Check output with listCmd is not empty
	 * Expected: value of listCmd containing "abc" and "def"
	 */
	@Test
	public void buildCmdListTest09() {
		// set data for listCmd
		listCmd.add("abc");
		listCmd.add("def");
		// add data for tmp output
		List<String> addListCmd = new ArrayList<String>();
		addListCmd.add("abc");
		addListCmd.add("def");
		addListCmd.addAll(tmpListCmd);
		// get data of function buildCmdList
		listCmd = (ArrayList) service.buildCmdList(false, psExec, listCmd,
				true, params);
		assertEquals(addListCmd, listCmd);
	}

	/**
	 * Check output with isInteractive is empty
	 * Expected: value of listCmd not contain a variable is -i
	 */
	@Test
	public void buildCmdListTest10() {
		// remove -i because tmp output need to compare not contains -i
		tmpListCmd.remove("-i");
		// get data of function buildCmdList
		listCmd = (ArrayList) service.buildCmdList(false, psExec, listCmd, "",
				params);
		assertEquals(tmpListCmd, listCmd);
	}

	/**
	 * Check output with isInteractive is null
	 * Expected: value of listCmd not contain a variable is -i
	 */
	@Test
	public void buildCmdListTest11() {
		// remove -i because tmp output need to compare not contains -i
		tmpListCmd.remove("-i");
		// get data of function buildCmdList
		listCmd = (ArrayList) service.buildCmdList(false, psExec, listCmd,
				null, params);
		assertEquals(tmpListCmd, listCmd);
	}

	/**
	 * Check output with params is empty
	 * Expected: show message about error
	 */
	@Test(expected = AssertionError.class)
	public void buildCmdListTest12() {
		try {
			// create params
			params = new HashMap<String, Map<String, String>>();
			// get data of function buildCmdList
			listCmd = (ArrayList) service.buildCmdList(false, psExec, listCmd,
					true, params);
		} catch (AssertionError ae) {
			assertTrue(ae.getMessage().contains("Params are null or empty!"));
			throw ae;
		}
	}

	/**
	 * Check output with params is null
	 * Expected: show message about error
	 */
	@Test(expected = AssertionError.class)
	public void buildCmdListTest13() {
		try {
			// set value of params is null
			params = null;
			// get data of function buildCmdList
			listCmd = (ArrayList) service.buildCmdList(false, psExec, listCmd,
					true, params);
		} catch (AssertionError ae) {
			assertTrue(ae.getMessage().contains("Params are null or empty!"));
			throw ae;
		}
	}

	/**
	 * Check exception if the listCmd is null
	 * Expected: value of listCmd is not empty (correct data)
	 */
	@Test
	public void buildCmdListTest14() {
		// set value of listCmd is null
		listCmd = null;
		// get data of function buildCmdList
		listCmd = (ArrayList) service.buildCmdList(false, psExec, listCmd,
				true, params);
		assertEquals(tmpListCmd, listCmd);
	}

	/**
	 * Check exception if value of params does not contains wiperdog_path
	 * Expected: value of listCmd containing host, user and pass is empty
	 */
	@Test
	public void buildCmdListTest15() {
		// create params
		params = new HashMap<String, Map<String, String>>();
		params.put("mongoDB", tmpMap);
		// replace host, user, pass because tmp output need to compare contains
		// host, user, pass is empty
		tmpListCmd.set(1, "");
		tmpListCmd.set(5, "");
		tmpListCmd.set(7, "");
		// get data of function buildCmdList
		listCmd = (ArrayList) service.buildCmdList(false, psExec, listCmd,
				true, params);
		assertEquals(tmpListCmd, listCmd);
	}

	/**
	 * Check exception if value of params does not contains host of
	 * wiperdog_path
	 * Expected: value of listCmd containing host is empty
	 */
	@Test
	public void buildCmdListTest16() {
		// create params
		params = new HashMap<String, Map<String, String>>();
		tmpMap = new HashMap<String, String>();
		tmpMap.put("os", "win");
		tmpMap.put("user", "thanhmx");
		tmpMap.put("pass", "insight");
		params.put("wiperdog_path", tmpMap);
		// replace host because tmp output need to compare contains host is
		// empty
		tmpListCmd.set(1, "");
		// get data of function buildCmdList
		listCmd = (ArrayList) service.buildCmdList(false, psExec, listCmd,
				true, params);
		assertEquals(tmpListCmd, listCmd);
	}

	/**
	 * Check exception if value of params does not contains user of
	 * wiperdog_path
	 * Expected: value of listCmd containing user is empty
	 */
	@Test
	public void buildCmdListTest17() {
		// create params
		params = new HashMap<String, Map<String, String>>();
		tmpMap = new HashMap<String, String>();
		tmpMap.put("os", "win");
		tmpMap.put("host", "10.0.0.184");
		tmpMap.put("pass", "insight");
		params.put("wiperdog_path", tmpMap);
		// replace user because tmp output need to compare contains user is
		// empty
		tmpListCmd.set(5, "");
		// get data of function buildCmdList
		listCmd = (ArrayList) service.buildCmdList(false, psExec, listCmd,
				true, params);
		assertEquals(tmpListCmd, listCmd);
	}

	/**
	 * Check exception if value of params does not contains pass of
	 * wiperdog_path
	 * Expected: value of listCmd containing pass is empty
	 */
	@Test
	public void buildCmdListTest18() {
		// create params
		params = new HashMap<String, Map<String, String>>();
		tmpMap = new HashMap<String, String>();
		tmpMap.put("os", "win");
		tmpMap.put("host", "10.0.0.184");
		tmpMap.put("user", "thanhmx");
		params.put("wiperdog_path", tmpMap);
		// replace pass because tmp output need to compare contains pass is
		// empty
		tmpListCmd.set(7, "");
		// get data of function buildCmdList
		listCmd = (ArrayList) service.buildCmdList(false, psExec, listCmd,
				true, params);
		assertEquals(tmpListCmd, listCmd);
	}
}
