package org.wiperdog.realtimelib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.google.gson.internal.LinkedTreeMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

/**
 * Testcase for RealtimeDataLib.parseJson parseJson is used only for parsing
 * List
 * 
 * @author luvina
 * 
 */
public class RealtimeDataLib_UT_01 {
	RealtimeDataLib instanceTest = new RealtimeDataLib();

	/**
	 * Test simple List with 3 elements "test1", "test2", "test3"
	 * 
	 * Expected: json string of list. ["test1", "test2", "test3"]
	 */
	@Test
	public void parseJson_001() {
		String[] datatest = new String[] { "test1", "test2", "test3" };
		String expected = "[ \"test1\" , \"test2\" , \"test3\"]";
		String result = (String) instanceTest.parseJson(datatest);
		assertEquals(expected, result);
	}

	/**
	 * Test mix List and Map
	 * 
	 * Expected: json string of object
	 */
	@Test
	public void parseJson_002() {
		List datatest = new ArrayList();
		// Prepare datatest
		for (int i = 0; i < 5; i++) {
			Map obj = new LinkedHashMap();
			for (int j = 1; j < 3; j++) {
				obj.put("key" + j, "value" + j);
			}
			datatest.add(obj);
		}

		String expected = "[ { \"key1\" : \"value1\" , \"key2\" : \"value2\"} , { \"key1\" : \"value1\" , \"key2\" : \"value2\"} , { \"key1\" : \"value1\" , \"key2\" : \"value2\"} , { \"key1\" : \"value1\" , \"key2\" : \"value2\"} , { \"key1\" : \"value1\" , \"key2\" : \"value2\"}]";

		String result = (String) instanceTest.parseJson(datatest);

		assertEquals(expected, result);
	}

	/**
	 * Test with an empty list
	 * Expected: null value as result
	 */
	@Test
	public void parseJson_003() {
		String result = (String) instanceTest.parseJson(new ArrayList());
		
		assertNull(result);
	}

	/**
	 * Test with empty string
	 * Expected: null as result
	 */
	@Test
	public void parseJson_004() {
		String result = (String) instanceTest.parseJson("");

		assertNull(result);
	}

	/**
	 * Test with null value
	 * Expected: null as result
	 */
	@Test
	public void parseJson_005() {
		String result = (String) instanceTest.parseJson(null);

		assertNull(result);
	}

}
