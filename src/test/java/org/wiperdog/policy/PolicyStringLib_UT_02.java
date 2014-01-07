package org.wiperdog.policy;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test method generatePolicyString
 * 
 */
public class PolicyStringLib_UT_02 {
	PolicyStringLib policy;
	HashMap data;
	HashMap dataStore;
	HashMap dataSubtyped;
	List<String> listKey;
	List<String> listKeyStore;
	List<String> listKeySubtyped;
	String type;
	HashMap mapConditionLevel;
	HashMap<String, String> mapConditionLevelStore;
	HashMap mapConditionLevelSubtyped;
	HashMap<String, String> tmpMapConditionLevel;
	HashMap<String, String> tmpPolicy;
	HashMap tmpMap;
	String tmpOutput;
	String tmpOutputStore;
	String tmpOutputSubtyped;
	String tmpResultActual;
	BufferedReader br;
	String line;

	@Before
	public void setUp() throws Exception {
		policy = new PolicyStringLib();
		// create data for test
		data = new LinkedHashMap();
		// set value of data Store
		dataStore = new LinkedHashMap();
		dataStore.put("jobName", "MySQL.Database_Area.InnoDBTablespace_Free");
		dataStore.put("instanceName", "localhost-@MYSQL-information_schema");
		tmpPolicy = new LinkedHashMap<String, String>();
		tmpPolicy
				.put("(data.MaxUsedPct >= 95)",
						"Dangerous: The size used of table space ${data.TablespaceName} is too high!!!");
		dataStore.put("mappolicy", tmpPolicy);

		// set value of data Subtyped
		dataSubtyped = new LinkedHashMap();
		dataSubtyped.put("jobName", "MySQL.Database_Area.Top30Database");
		dataSubtyped.put("instanceName", "localhost-@MYSQL-information_schema");
		// set data policy for group D
		tmpMap = new LinkedHashMap();
		tmpPolicy = new LinkedHashMap<String, String>();
		tmpPolicy
				.put("(data.UsedPct >= 95)",
						"Dangerous: Database size used of database ${data.DatabaseNm} is too high!!!");
		tmpMap.put("D", tmpPolicy);
		// set data policy for group M
		tmpPolicy = new LinkedHashMap<String, String>();
		tmpPolicy
				.put("(data.UsedPct >= 90) && (data.UsedPct < 95)",
						"Warning: Current value of database size used of database ${data.DatabaseNm} is ${data.UsedSize} byte!!!");
		tmpMap.put("M", tmpPolicy);
		dataSubtyped.put("mappolicy", tmpMap);

		// create listKey for test
		listKey = new ArrayList<String>();
		// set value of listKey for test data Store
		listKeyStore = Arrays.asList(new String[] { "TablespaceName",
				"CurrentUsedPct", "MaxFreeSizeKB", "MaxUsedPct" });
		// set value of listKey for test data Subtyped
		listKeySubtyped = Arrays.asList(new String[] { "DatabaseNm",
				"UsedSize", "UsedDataPct", "TotalSize", "UsedPct" });

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

		// get data output of data Store
		tmpOutputStore = readFile("tmp\\datatest\\PolicyStringLib\\policyDataStore.txt");
		// get data output of data Subtyped
		tmpOutputSubtyped = readFile("tmp\\datatest\\PolicyStringLib\\policyDataSubtyped.txt");
	}

	@After
	public void tearDown() throws Exception {
		br.close();
	}

	/**
	 * Check output with data type is store.
	 * Expected: generate the Policy correctly
	 */
	@Test
	public void generatePolicyStringTest01() {
		// set value of type
		type = "store";
		// set value of data store
		data = dataStore;
		// set listKey for data store
		listKey = listKeyStore;
		// set value of mapConditionLevel
		mapConditionLevel = mapConditionLevelStore;
		// set data output need to compare
		tmpOutput = tmpOutputStore;
		// get data of function generatePolicyString
		tmpResultActual = (String) policy.generatePolicyString(data, listKey,
				type, mapConditionLevel);
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * Check output with data type is subtyped.
	 * Expected: generate the Policy correctly
	 */
	@Test
	public void generatePolicyStringTest02() {
		// set value of type
		type = "subtyped";
		// set value of data store
		data = dataSubtyped;
		// set listKey for data store
		listKey = listKeySubtyped;
		// set value of mapConditionLevel
		mapConditionLevel = mapConditionLevelSubtyped;
		// set data output need to compare
		tmpOutput = tmpOutputSubtyped;
		// get data of function generatePolicyString
		tmpResultActual = (String) policy.generatePolicyString(data, listKey,
				type, mapConditionLevel);
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * Check output with data type is store value of data is empty.
	 * Expected: generate the Policy is empty
	 */
	@Test
	public void generatePolicyStringTest03() {
		// set value of type
		type = "store";
		// set listKey for data store
		listKey = listKeyStore;
		// set value of mapConditionLevel
		mapConditionLevel = mapConditionLevelStore;
		// get data of function generatePolicyString
		tmpResultActual = (String) policy.generatePolicyString(data, listKey,
				type, mapConditionLevel);
		assertTrue(tmpResultActual.length() == 0);
	}

	/**
	 * Check output with data type is store value of data is null.
	 * Expected: generate the Policy is empty
	 */
	@Test
	public void generatePolicyStringTest04() {
		// set value of type
		type = "store";
		// set value of data store
		data = null;
		// set listKey for data store
		listKey = listKeyStore;
		// set value of mapConditionLevel
		mapConditionLevel = mapConditionLevelStore;
		// get data of function generatePolicyString
		tmpResultActual = (String) policy.generatePolicyString(data, listKey,
				type, mapConditionLevel);
		assertTrue(tmpResultActual.length() == 0);
	}

	/**
	 * Check output with data type is store value of listKey is empty.
	 * Expected: generate the Policy correctly
	 */
	@Test
	public void generatePolicyStringTest05() {
		// set value of type
		type = "store";
		// set value of data store
		data = dataStore;
		// set listKey for data store
		listKey = new ArrayList<String>();
		// set value of mapConditionLevel
		mapConditionLevel = mapConditionLevelStore;
		// set data output need to compare
		tmpOutput = tmpOutputStore;
		// get data of function generatePolicyString
		tmpResultActual = (String) policy.generatePolicyString(data, listKey,
				type, mapConditionLevel);
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * Check output with data type is store value of listKey is null.
	 * Expected: generate the Policy correctly
	 */
	@Test
	public void generatePolicyStringTest06() {
		// set value of type
		type = "store";
		// set value of data store
		data = dataStore;
		// set listKey for data store
		listKey = null;
		// set value of mapConditionLevel
		mapConditionLevel = mapConditionLevelStore;
		// set data output need to compare
		tmpOutput = tmpOutputStore;
		// get data of function generatePolicyString
		tmpResultActual = (String) policy.generatePolicyString(data, listKey,
				type, mapConditionLevel);
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * Check output with data type is store value of type is empty.
	 * Expected: generate the Policy is empty
	 */
	@Test
	public void generatePolicyStringTest07() {
		// set value of type
		type = "";
		// set value of data store
		data = dataStore;
		// set listKey for data store
		listKey = listKeyStore;
		// set value of mapConditionLevel
		mapConditionLevel = mapConditionLevelStore;
		// get data of function generatePolicyString
		tmpResultActual = (String) policy.generatePolicyString(data, listKey,
				type, mapConditionLevel);
		assertTrue(tmpResultActual.length() == 0);
	}

	/**
	 * Check output with data type is store value of type is null.
	 * Expected: generate the Policy is empty
	 */
	@Test
	public void generatePolicyStringTest08() {
		// set value of type
		type = null;
		// set value of data store
		data = dataStore;
		// set listKey for data store
		listKey = listKeyStore;
		// set value of mapConditionLevel
		mapConditionLevel = mapConditionLevelStore;
		// get data of function generatePolicyString
		tmpResultActual = (String) policy.generatePolicyString(data, listKey,
				type, mapConditionLevel);
		assertTrue(tmpResultActual.length() == 0);
	}

	/**
	 * Check output with data type is store value of mapConditionLevel is empty.
	 * Expected: value of level in policy is null
	 */
	@Test
	public void generatePolicyStringTest09() {
		// set value of type
		type = "store";
		// set value of data store
		data = dataStore;
		// set listKey for data store
		listKey = listKeyStore;
		// set data output need to compare
		tmpOutput = readFile("tmp\\datatest\\PolicyStringLib\\generatePolicyStringTest09.txt");
		// get data of function generatePolicyString
		tmpResultActual = (String) policy.generatePolicyString(data, listKey,
				type, mapConditionLevel);
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * Check output with data type is store value of mapConditionLevel is null.
	 * Expected: value of level in policy is null
	 */
	@Test
	public void generatePolicyStringTest10() {
		// set value of type
		type = "store";
		// set value of data store
		data = dataStore;
		// set listKey for data store
		listKey = listKeyStore;
		// set value of mapConditionLevel
		mapConditionLevel = null;
		// set data output need to compare
		tmpOutput = readFile("tmp\\datatest\\PolicyStringLib\\generatePolicyStringTest09.txt");
		// get data of function generatePolicyString
		tmpResultActual = (String) policy.generatePolicyString(data, listKey,
				type, mapConditionLevel);
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * Check output with data type is store value of jobName in data is empty.
	 * Expected: value of jobName in policy is empty
	 */
	@Test
	public void generatePolicyStringTest11() {
		// set value of type
		type = "store";
		// set value of jobName is empty
		dataStore.put("jobName", "");
		// set value of data store
		data = dataStore;
		// set listKey for data store
		listKey = listKeyStore;
		// set value of mapConditionLevel
		mapConditionLevel = mapConditionLevelStore;
		// set data output need to compare
		tmpOutput = readFile("tmp\\datatest\\PolicyStringLib\\generatePolicyStringTest11.txt");
		// get data of function generatePolicyString
		tmpResultActual = (String) policy.generatePolicyString(data, listKey,
				type, mapConditionLevel);
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * Check output with data type is store value of jobName in data is null.
	 * Expected: value of jobName in policy is null
	 */
	@Test
	public void generatePolicyStringTest12() {
		// set value of type
		type = "store";
		// set value of jobName is empty
		dataStore.put("jobName", null);
		// set value of data store
		data = dataStore;
		// set listKey for data store
		listKey = listKeyStore;
		// set value of mapConditionLevel
		mapConditionLevel = mapConditionLevelStore;
		// set data output need to compare
		tmpOutput = readFile("tmp\\datatest\\PolicyStringLib\\generatePolicyStringTest12.txt");
		// get data of function generatePolicyString
		tmpResultActual = (String) policy.generatePolicyString(data, listKey,
				type, mapConditionLevel);
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * Check output with data type is store value of data not contains jobName.
	 * Expected: value of jobName in policy is null
	 */
	@Test
	public void generatePolicyStringTest13() {
		// set value of type
		type = "store";
		// remove jobName from map data test
		dataStore.remove("jobName");
		// set value of data store
		data = dataStore;
		// set listKey for data store
		listKey = listKeyStore;
		// set value of mapConditionLevel
		mapConditionLevel = mapConditionLevelStore;
		// set data output need to compare
		tmpOutput = readFile("tmp\\datatest\\PolicyStringLib\\generatePolicyStringTest12.txt");
		// get data of function generatePolicyString
		tmpResultActual = (String) policy.generatePolicyString(data, listKey,
				type, mapConditionLevel);
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * Check output with data type is store value of instanceName in data is
	 * empty.
	 * Expected: value of istIid in policy is empty
	 */
	@Test
	public void generatePolicyStringTest14() {
		// set value of type
		type = "store";
		// set value of jobName is empty
		dataStore.put("instanceName", "");
		// set value of data store
		data = dataStore;
		// set listKey for data store
		listKey = listKeyStore;
		// set value of mapConditionLevel
		mapConditionLevel = mapConditionLevelStore;
		// set data output need to compare
		tmpOutput = readFile("tmp\\datatest\\PolicyStringLib\\generatePolicyStringTest14.txt");
		// get data of function generatePolicyString
		tmpResultActual = (String) policy.generatePolicyString(data, listKey,
				type, mapConditionLevel);
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * Check output with data type is store value of instanceName in data is
	 * null.
	 * Expected: value of istIid in policy is null
	 */
	@Test
	public void generatePolicyStringTest15() {
		// set value of type
		type = "store";
		// set value of jobName is empty
		dataStore.put("instanceName", null);
		// set value of data store
		data = dataStore;
		// set listKey for data store
		listKey = listKeyStore;
		// set value of mapConditionLevel
		mapConditionLevel = mapConditionLevelStore;
		// set data output need to compare
		tmpOutput = readFile("tmp\\datatest\\PolicyStringLib\\generatePolicyStringTest15.txt");
		// get data of function generatePolicyString
		tmpResultActual = (String) policy.generatePolicyString(data, listKey,
				type, mapConditionLevel);
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * Check output with data type is store value of data not contains
	 * instanceName
	 * Expected: value of istIid in policy is null
	 */
	@Test
	public void generatePolicyStringTest16() {
		// set value of type
		type = "store";
		// remove jobName from map data test
		dataStore.remove("instanceName");
		// set value of data store
		data = dataStore;
		// set listKey for data store
		listKey = listKeyStore;
		// set value of mapConditionLevel
		mapConditionLevel = mapConditionLevelStore;
		// set data output need to compare
		tmpOutput = readFile("tmp\\datatest\\PolicyStringLib\\generatePolicyStringTest15.txt");
		// get data of function generatePolicyString
		tmpResultActual = (String) policy.generatePolicyString(data, listKey,
				type, mapConditionLevel);
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * Check output with data type is store value of mappolicy in data is empty.
	 * Expected: generate the policy is empty
	 */
	@Test
	public void generatePolicyStringTest17() {
		// set value of type
		type = "store";
		// set value of jobName is empty
		dataStore.put("mappolicy", new LinkedHashMap<String, String>());
		// set value of data store
		data = dataStore;
		// set listKey for data store
		listKey = listKeyStore;
		// set value of mapConditionLevel
		mapConditionLevel = mapConditionLevelStore;
		// get data of function generatePolicyString
		tmpResultActual = (String) policy.generatePolicyString(data, listKey,
				type, mapConditionLevel);
		assertTrue(tmpResultActual.length() == 0);
	}

	/**
	 * Check output with data type is store value of mappolicy in data is null.
	 * Expected: generate the policy is empty
	 */
	@Test
	public void generatePolicyStringTest18() {
		// set value of type
		type = "store";
		// set value of jobName is empty
		dataStore.put("mappolicy", null);
		// set value of data store
		data = dataStore;
		// set listKey for data store
		listKey = listKeyStore;
		// set value of mapConditionLevel
		mapConditionLevel = mapConditionLevelStore;
		// get data of function generatePolicyString
		tmpResultActual = (String) policy.generatePolicyString(data, listKey,
				type, mapConditionLevel);
		assertTrue(tmpResultActual.length() == 0);
	}

	/**
	 * Check output with data type is store value of data not contains mappolicy.
	 * Expected: generate the policy is empty
	 */
	@Test
	public void generatePolicyStringTest19() {
		// set value of type
		type = "store";
		// remove jobName from map data test
		dataStore.remove("mappolicy");
		// set value of data store
		data = dataStore;
		// set listKey for data store
		listKey = listKeyStore;
		// set value of mapConditionLevel
		mapConditionLevel = mapConditionLevelStore;
		// get data of function generatePolicyString
		tmpResultActual = (String) policy.generatePolicyString(data, listKey,
				type, mapConditionLevel);
		assertTrue(tmpResultActual.length() == 0);
	}

	/**
	 * Check output with data type is subtyped value of data is empty.
	 * Expected: generate the policy is empty
	 */
	@Test
	public void generatePolicyStringTest20() {
		// set value of type
		type = "subtyped";
		// set listKey for data subtyped
		listKey = listKeySubtyped;
		// set value of mapConditionLevel
		mapConditionLevel = mapConditionLevelSubtyped;
		// get data of function generatePolicyString
		tmpResultActual = (String) policy.generatePolicyString(data, listKey,
				type, mapConditionLevel);
		assertTrue(tmpResultActual.length() == 0);
	}

	/**
	 * Check output with data type is subtyped value of data is null.
	 * Expected: generate the policy is empty
	 */
	@Test
	public void generatePolicyStringTest21() {
		// set value of type
		type = "subtyped";
		// set value of data subtyped
		data = null;
		// set listKey for data subtyped
		listKey = listKeySubtyped;
		// set value of mapConditionLevel
		mapConditionLevel = mapConditionLevelSubtyped;
		// get data of function generatePolicyString
		tmpResultActual = (String) policy.generatePolicyString(data, listKey,
				type, mapConditionLevel);
		assertTrue(tmpResultActual.length() == 0);
	}

	/**
	 * Check output with data type is subtyped value of listKey is empty.
	 * Expected: generate the policy correctly
	 */
	@Test
	public void generatePolicyStringTest22() {
		// set value of type
		type = "subtyped";
		// set value of data subtyped
		data = dataSubtyped;
		// set listKey for data subtyped
		listKey = new ArrayList<String>();
		// set value of mapConditionLevel
		mapConditionLevel = mapConditionLevelSubtyped;
		// set data output need to compare
		tmpOutput = tmpOutputSubtyped;
		// get data of function generatePolicyString
		tmpResultActual = (String) policy.generatePolicyString(data, listKey,
				type, mapConditionLevel);
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * Check output with data type is subtyped value of listKey is null.
	 * Expected: generate the policy correctly
	 */
	@Test
	public void generatePolicyStringTest23() {
		// set value of type
		type = "subtyped";
		// set value of data subtyped
		data = dataSubtyped;
		// set listKey for data subtyped
		listKey = null;
		// set value of mapConditionLevel
		mapConditionLevel = mapConditionLevelSubtyped;
		// set data output need to compare
		tmpOutput = tmpOutputSubtyped;
		// get data of function generatePolicyString
		tmpResultActual = (String) policy.generatePolicyString(data, listKey,
				type, mapConditionLevel);
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * Check output with data type is subtyped value of type is empty
	 * Expected: generate the policy is empty
	 */
	@Test
	public void generatePolicyStringTest24() {
		// set value of type
		type = "";
		// set value of data subtyped
		data = dataSubtyped;
		// set listKey for data subtyped
		listKey = listKeySubtyped;
		// set value of mapConditionLevel
		mapConditionLevel = mapConditionLevelSubtyped;
		// get data of function generatePolicyString
		tmpResultActual = (String) policy.generatePolicyString(data, listKey,
				type, mapConditionLevel);
		assertTrue(tmpResultActual.length() == 0);
	}

	/**
	 * Check output with data type is subtyped value of type is null.
	 * Expected: generate the policy is empty
	 */
	@Test
	public void generatePolicyStringTest25() {
		// set value of type
		type = null;
		// set value of data subtyped
		data = dataSubtyped;
		// set listKey for data subtyped
		listKey = listKeySubtyped;
		// set value of mapConditionLevel
		mapConditionLevel = mapConditionLevelSubtyped;
		// get data of function generatePolicyString
		tmpResultActual = (String) policy.generatePolicyString(data, listKey,
				type, mapConditionLevel);
		assertTrue(tmpResultActual.length() == 0);
	}

	/**
	 * Check output with data type is subtyped value of mapConditionLevel is
	 * empty.
	 * Expected: value of level in policy is null
	 */
	@Test
	public void generatePolicyStringTest26() {
		// set value of type
		type = "subtyped";
		// set value of data subtyped
		data = dataSubtyped;
		// set listKey for data subtyped
		listKey = listKeySubtyped;
		// set data output need to compare
		tmpOutput = readFile("tmp\\datatest\\PolicyStringLib\\generatePolicyStringTest26.txt");
		// get data of function generatePolicyString
		tmpResultActual = (String) policy.generatePolicyString(data, listKey,
				type, mapConditionLevel);
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * Check output with data type is subtyped value of mapConditionLevel is
	 * null.
	 * Expected: value of level in policy is null
	 */
	@Test
	public void generatePolicyStringTest27() {
		// set value of type
		type = "subtyped";
		// set value of data subtyped
		data = dataSubtyped;
		// set listKey for data subtyped
		listKey = listKeySubtyped;
		// set value of mapConditionLevel
		mapConditionLevel = null;
		// set data output need to compare
		tmpOutput = readFile("tmp\\datatest\\PolicyStringLib\\generatePolicyStringTest26.txt");
		// get data of function generatePolicyString
		tmpResultActual = (String) policy.generatePolicyString(data, listKey,
				type, mapConditionLevel);
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * Check output with data type is subtyped value of jobName in data is empty.
	 * Expected: value of jobName in policy is empty
	 */
	@Test
	public void generatePolicyStringTest28() {
		// set value of type
		type = "subtyped";
		// set value of jobName is empty
		dataSubtyped.put("jobName", "");
		// set value of data subtyped
		data = dataSubtyped;
		// set listKey for data subtyped
		listKey = listKeySubtyped;
		// set value of mapConditionLevel
		mapConditionLevel = mapConditionLevelSubtyped;
		// set data output need to compare
		tmpOutput = readFile("tmp\\datatest\\PolicyStringLib\\generatePolicyStringTest28.txt");
		// get data of function generatePolicyString
		tmpResultActual = (String) policy.generatePolicyString(data, listKey,
				type, mapConditionLevel);
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * Check output with data type is subtyped value of jobName in data is null.
	 * Expected: value of jobName in policy is null
	 */
	@Test
	public void generatePolicyStringTest29() {
		// set value of type
		type = "subtyped";
		// set value of jobName is empty
		dataSubtyped.put("jobName", null);
		// set value of data subtyped
		data = dataSubtyped;
		// set listKey for data subtyped
		listKey = listKeySubtyped;
		// set value of mapConditionLevel
		mapConditionLevel = mapConditionLevelSubtyped;
		// set data output need to compare
		tmpOutput = readFile("tmp\\datatest\\PolicyStringLib\\generatePolicyStringTest29.txt");
		// get data of function generatePolicyString
		tmpResultActual = (String) policy.generatePolicyString(data, listKey,
				type, mapConditionLevel);
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * Check output with data type is subtyped value of data not contains
	 * jobName.
	 * Expected: value of jobName in policy is null
	 */
	@Test
	public void generatePolicyStringTest30() {
		// set value of type
		type = "subtyped";
		// remove jobName from map data test
		dataSubtyped.remove("jobName");
		// set value of data subtyped
		data = dataSubtyped;
		// set listKey for data subtyped
		listKey = listKeySubtyped;
		// set value of mapConditionLevel
		mapConditionLevel = mapConditionLevelSubtyped;
		// set data output need to compare
		tmpOutput = readFile("tmp\\datatest\\PolicyStringLib\\generatePolicyStringTest29.txt");
		// get data of function generatePolicyString
		tmpResultActual = (String) policy.generatePolicyString(data, listKey,
				type, mapConditionLevel);
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * Check output with data type is subtyped value of instanceName in data is
	 * empty.
	 * Expected: value of istIid in policy is empty
	 */
	@Test
	public void generatePolicyStringTest31() {
		// set value of type
		type = "subtyped";
		// set value of jobName is empty
		dataSubtyped.put("instanceName", "");
		// set value of data subtyped
		data = dataSubtyped;
		// set listKey for data subtyped
		listKey = listKeySubtyped;
		// set value of mapConditionLevel
		mapConditionLevel = mapConditionLevelSubtyped;
		// set data output need to compare
		tmpOutput = readFile("tmp\\datatest\\PolicyStringLib\\generatePolicyStringTest31.txt");
		// get data of function generatePolicyString
		tmpResultActual = (String) policy.generatePolicyString(data, listKey,
				type, mapConditionLevel);
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * Check output with data type is subtyped value of instanceName in data is
	 * null.
	 * Expected: value of istIid in policy is null
	 */
	@Test
	public void generatePolicyStringTest32() {
		// set value of type
		type = "subtyped";
		// set value of jobName is empty
		dataSubtyped.put("instanceName", null);
		// set value of data subtyped
		data = dataSubtyped;
		// set listKey for data subtyped
		listKey = listKeySubtyped;
		// set value of mapConditionLevel
		mapConditionLevel = mapConditionLevelSubtyped;
		// set data output need to compare
		tmpOutput = readFile("tmp\\datatest\\PolicyStringLib\\generatePolicyStringTest32.txt");
		// get data of function generatePolicyString
		tmpResultActual = (String) policy.generatePolicyString(data, listKey,
				type, mapConditionLevel);
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * Check output with data type is subtyped value of data not contains
	 * instanceName.
	 * Expected: value of istIid in policy is null
	 */
	@Test
	public void generatePolicyStringTest33() {
		// set value of type
		type = "subtyped";
		// remove jobName from map data test
		dataSubtyped.remove("instanceName");
		// set value of data subtyped
		data = dataSubtyped;
		// set listKey for data subtyped
		listKey = listKeySubtyped;
		// set value of mapConditionLevel
		mapConditionLevel = mapConditionLevelSubtyped;
		// set data output need to compare
		tmpOutput = readFile("tmp\\datatest\\PolicyStringLib\\generatePolicyStringTest32.txt");
		// get data of function generatePolicyString
		tmpResultActual = (String) policy.generatePolicyString(data, listKey,
				type, mapConditionLevel);
		assertEquals(tmpOutput, tmpResultActual);
	}

	/**
	 * Check output with data type is subtyped value of mappolicy in data is
	 * empty.
	 * Expected: generate the policy is empty
	 */
	@Test
	public void generatePolicyStringTest34() {
		// set value of type
		type = "subtyped";
		// set value of jobName is empty
		dataSubtyped.put("mappolicy", new LinkedHashMap<String, String>());
		// set value of data subtyped
		data = dataSubtyped;
		// set listKey for data subtyped
		listKey = listKeySubtyped;
		// set value of mapConditionLevel
		mapConditionLevel = mapConditionLevelSubtyped;
		// get data of function generatePolicyString
		tmpResultActual = (String) policy.generatePolicyString(data, listKey,
				type, mapConditionLevel);
		assertTrue(tmpResultActual.length() == 0);
	}

	/**
	 * Check output with data type is subtyped value of mappolicy in data is
	 * null.
	 * Expected: generate the policy is empty
	 */
	@Test
	public void generatePolicyStringTest35() {
		// set value of type
		type = "subtyped";
		// set value of jobName is empty
		dataSubtyped.put("mappolicy", null);
		// set value of data subtyped
		data = dataSubtyped;
		// set listKey for data subtyped
		listKey = listKeySubtyped;
		// set value of mapConditionLevel
		mapConditionLevel = mapConditionLevelSubtyped;
		// get data of function generatePolicyString
		tmpResultActual = (String) policy.generatePolicyString(data, listKey,
				type, mapConditionLevel);
		assertTrue(tmpResultActual.length() == 0);
	}

	/**
	 * Check output with data type is subtyped value of data not contains
	 * mappolicy.
	 * Expected: generate the policy is empty
	 */
	@Test
	public void generatePolicyStringTest36() {
		// set value of type
		type = "subtyped";
		// remove jobName from map data test
		dataSubtyped.remove("mappolicy");
		// set value of data subtyped
		data = dataSubtyped;
		// set listKey for data subtyped
		listKey = listKeySubtyped;
		// set value of mapConditionLevel
		mapConditionLevel = mapConditionLevelSubtyped;
		// get data of function generatePolicyString
		tmpResultActual = (String) policy.generatePolicyString(data, listKey,
				type, mapConditionLevel);
		assertTrue(tmpResultActual.length() == 0);
	}

	/**
	 * @param filePath
	 *            path to file contains data for test
	 * @return output need to compare
	 */
	public String readFile(String filePath) {
		// get data output need to compare, read from file
		String output = "";
		try {
			br = new BufferedReader(new FileReader(filePath));
			while ((line = br.readLine()) != null) {
				if (output != "") {
					output += "\n" + line;
				} else {
					output += line;
				}
			}
			return output;
		} catch (Exception ex) {
			return ex.getMessage();
		}
	}
}
