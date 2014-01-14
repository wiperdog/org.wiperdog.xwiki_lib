package org.wiperdog.realtimelib;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.wiperdog.drawchartdatalib.DataToDrawChart;
import org.wiperdog.lib.TestUTCommon;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Testcase for method getDataJson
 * 
 * @author luvina
 * 
 */
public class RealtimeDataLib_UT_02 {

	RealtimeDataLib instanceTest = new RealtimeDataLib();
	Gson gson = new GsonBuilder().setPrettyPrinting().create();

	/**
	 * correct param for job Store
	 * 2 pie declarations, 4 records
	 * 
	 * Expected: List of 2 maps
	 * Each map contains 4 records with format : 
	 * {type : "pie",
	 *  chart_name : $_chartname,
	 *  unit : $_unit,
	 *  data : $_listData,
	 *  name : $_containerName
	 * }
	 * detail in tmp\\datatest\\RealtimeDataLib\\getDataJson_001.txt_expected
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void getDataJson_001() throws FileNotFoundException {
		FileReader fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getDataJson_001.txt");
		List<Map> datatest = gson.fromJson(fr, List.class);

		fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getDataJson_001.txt_expected");
		List expected = gson.fromJson(fr, List.class);
		Object result = instanceTest.getDataJson(datatest, "pie");
		assertTrue(TestUTCommon.compare2object(expected, result));
	}

	/**
	 * Correct param for job Subtyped
	 * group D : 1 pie declaration, 2 records
	 * group M : 1 pie declaration, 2 records
	 * 
	 * Expected: a Map contains D, M key which
	 * D and M each contains list 2 records with format :
	 * {type : "pie",
	 *  chart_name : $_chartname,
	 *  unit : $_unit,
	 *  data : $_listData,
	 *  name : $_containerName
	 * }
	 * detail in tmp\\datatest\\RealtimeDataLib\\getDataJson_002.txt_expected
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void getDataJson_002() throws FileNotFoundException {
		FileReader fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getDataJson_002.txt");
		List<Map> datatest = gson.fromJson(fr, List.class);

		Map dataSubtyped = (Map) DataToDrawChart.getDataToDrawSubtype(datatest);
		Map finalData = new HashMap();
		// Map finalMapData = new LinkedHashMap();
		Set<String> keySet = dataSubtyped.keySet();
		for (String key : keySet) {
			List data = (List) instanceTest.getDataJson(dataSubtyped.get(key), "pie");
			finalData.put(key, data);
		}

		fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getDataJson_002.txt_expected");
		Map expected = gson.fromJson(fr, Map.class);
		assertTrue(TestUTCommon.compare2object(expected, finalData));
	}

	/**
	 * Incorrect param should return an empty list
	 * 
	 * Expected: An empty list
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void getDataJson_003() throws FileNotFoundException {
		FileReader fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getDataJson_001.txt");
		List<Map> datatest = gson.fromJson(fr, List.class);

		Object result = instanceTest.getDataJson(datatest, "incorrect");
		assertTrue(TestUTCommon.compare2object(new ArrayList(), result));
	}

	/**
	 * Data doesn't contain pie should return empty list
	 * 
	 * Expected: An empty list
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void getDataJson_004() throws FileNotFoundException {
		FileReader fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getDataJson_004.txt");
		List<Map> datatest = gson.fromJson(fr, List.class);

		Object result = instanceTest.getDataJson(datatest, "pie");
		assertTrue(TestUTCommon.compare2object(new ArrayList(), result));
	}

	/**
	 * Method should work normally when param has space character at begin and
	 * end
	 * Expected: Get data normally as case 001
	 * 
	 * @throws FileNotFoundException
	 * 
	 */
	@Test
	public void getDataJson_005() throws FileNotFoundException {
		FileReader fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getDataJson_001.txt");
		List<Map> datatest = gson.fromJson(fr, List.class);

		fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getDataJson_001.txt_expected");
		List expected = gson.fromJson(fr, List.class);
		Object result = instanceTest.getDataJson(datatest, " pie ");
		assertTrue(TestUTCommon.compare2object(expected, result));
	}

	/**
	 * When type is null
	 * Expected: An empty list
	 * 
	 * @throws FileNotFoundException
	 * 
	 */
	@Test
	public void getDataJson_006() throws FileNotFoundException {
		FileReader fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getDataJson_001.txt");
		List<Map> datatest = gson.fromJson(fr, List.class);

		fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getDataJson_001.txt_expected");
		List expected = gson.fromJson(fr, List.class);
		Object result = instanceTest.getDataJson(datatest, null);
		assertTrue(TestUTCommon.compare2object(new ArrayList(), result));
	}

	/**
	 * When data is null
	 * Expected: AssertionError with message "data is null or empty!"
	 */
	@Test(expected = AssertionError.class)
	public void getDataJson_007() {
		try {
			Object result = instanceTest.getDataJson(null, "pie");
		} catch (AssertionError ae) {
			assertTrue(ae.getMessage().contains("data is null or empty!"));
			throw ae;
		}
	}

}
