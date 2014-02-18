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
import org.wiperdog.custommongodbconnection.CMongoDBConn;
import org.wiperdog.drawchartdatalib.DataToDrawChart;
import org.wiperdog.lib.TestUTCommon;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Testcase for method RealtimeDataLib.drawChart
 * 
 * @author luvina
 * 
 */
public class RealtimeDataLib_UT_03 {
	RealtimeDataLib instanceTest = new RealtimeDataLib();
	Gson gson = new GsonBuilder().setPrettyPrinting().create();

	/**
	 * Test for Store case with correct dataType
	 * Expected: a map of 
	 * {value : $_datatest, 
	 *  typeChart : "Store", 
	 *  jsonPie : $_jsonPie, 
	 *  jsonContainerPie : $_jsonContainerPie, 
	 *  key : ""} 
	 * in tmp\\datatest\\RealtimeDataLib\\drawChart_001.txt_expected
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void drawChart_001() throws FileNotFoundException {
		FileReader fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\drawChart_001.txt");
		List<Map> datatest = gson.fromJson(fr, List.class);

		fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\drawChart_001.txt_expected");
		Map expected = gson.fromJson(fr, Map.class);
		Object actual = instanceTest.drawChart("", datatest, "Store");
		assertTrue(actual instanceof Map);
		assertTrue(TestUTCommon.compare2object(expected, actual));

	}

	/**
	 * Test for Subtyped case with correct dataType
	 * Expected: a list of 2 maps 
	 * {value : $_datatest, 
	 *  typeChart : "Subtyped", 
	 *  jsonPie : $_jsonPie, 
	 *  jsonContainerPie : $_jsonContainerPie, 
	 *  key : $_key //In this case keys are "M" and "D"} 
	 * in tmp\\datatest\\RealtimeDataLib\\drawChart_002.txt_expected
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void drawChart_002() throws FileNotFoundException {
		FileReader fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\drawChart_002.txt");
		List<Map> datatest = gson.fromJson(fr, List.class);

		Map dataSubtyped = (Map) DataToDrawChart.getDataToDrawSubtype(datatest);
		List<Map> finalData = new ArrayList<Map>();
		Map finalMapData = new LinkedHashMap();
		Set<String> keySet = dataSubtyped.keySet();
		for (String key : keySet) {
			finalMapData = (Map) instanceTest.drawChart(key, dataSubtyped.get(key), "Subtyped");
			finalData.add(finalMapData);
			finalMapData = new LinkedHashMap();
		}

		fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\drawChart_002.txt_expected");
		List expected = gson.fromJson(fr, List.class);
		assertTrue(TestUTCommon.compare2object(expected, finalData));
	}

	/**
	 * Test for Store case with incorrect dataType
	 * Expected: result is different with case 001 because of dataType
	 * assertFalse instead of assertTrue
	 * 
	 * @throws FileNotFoundException
	 * 
	 */
	@Test
	public void drawChart_003() throws FileNotFoundException {
		FileReader fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\drawChart_001.txt");
		List<Map> datatest = gson.fromJson(fr, List.class);

		fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\drawChart_001.txt_expected");
		Map expected = gson.fromJson(fr, Map.class);
		Object actual = instanceTest.drawChart("", datatest, "Subtyped");
		assertFalse(TestUTCommon.compare2object(expected, actual));
	}

	/**
	 * Test for Subtyped case with incorrect dataType
	 * Expected: result is different with case 002 because of dataType
	 * assertFalse instead of assertTrue
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void drawChart_004() throws FileNotFoundException {
		FileReader fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\drawChart_002.txt");
		List<Map> datatest = gson.fromJson(fr, List.class);

		Map dataSubtyped = (Map) DataToDrawChart.getDataToDrawSubtype(datatest);
		List<Map> finalData = new ArrayList<Map>();
		Map finalMapData = new LinkedHashMap();
		Set<String> keySet = dataSubtyped.keySet();
		for (String key : keySet) {
			finalMapData = (Map) instanceTest.drawChart(key, dataSubtyped.get(key), "Store");
			finalData.add(finalMapData);
			finalMapData = new LinkedHashMap();
		}

		fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\drawChart_002.txt_expected");
		List expected = gson.fromJson(fr, List.class);
		assertFalse(TestUTCommon.compare2object(expected, finalData));
	}

	/**
	 * Testcase with datatype is null
	 * Expected: AssertionError with message "data type is null or empty!"
	 * 
	 * @throws FileNotFoundException
	 */
	@Test(expected = AssertionError.class)
	public void drawChart_005() throws FileNotFoundException {
		try {
			FileReader fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\drawChart_001.txt");
			List<Map> datatest = gson.fromJson(fr, List.class);

			fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\drawChart_001.txt_expected");
			Map expected = gson.fromJson(fr, Map.class);
			Object actual = instanceTest.drawChart("", datatest, null);
		} catch (AssertionError ae) {
			assertTrue(ae.getMessage().contains("data type is null or empty!"));
			throw ae;
		}
	}

	/**
	 * Testcase with datatype is empty
	 * Expected: AssertionError with message "data type is null or empty!"
	 * 
	 * @throws FileNotFoundException
	 */
	@Test(expected=AssertionError.class)
	public void drawChart_006() throws FileNotFoundException {
		try {
			FileReader fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\drawChart_001.txt");
			List<Map> datatest = gson.fromJson(fr, List.class);

			fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\drawChart_001.txt_expected");
			Map expected = gson.fromJson(fr, Map.class);
			Object actual = instanceTest.drawChart("", datatest, "");
		} catch (AssertionError ae) {
			assertTrue(ae.getMessage().contains("data type is null or empty!"));
			throw ae;
		}
	}

	/**
	 * Method should be done successfully with data contains space character
	 * Expected: Get data normally as case 001
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void drawChart_007() throws FileNotFoundException {
		FileReader fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\drawChart_001.txt");
		List<Map> datatest = gson.fromJson(fr, List.class);

		fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\drawChart_001.txt_expected");
		Map expected = gson.fromJson(fr, Map.class);
		Object actual = instanceTest.drawChart("", datatest, " Store ");
		assertTrue(TestUTCommon.compare2object(expected, actual));
	}

	/**
	 * Data doesn't contain pie declaration should return empty
	 * Expected: an empty Map as result
	 * 
	 * @throws FileNotFoundException 
	 * 
	 */
	@Test
	public void drawChart_008() throws FileNotFoundException {
		FileReader fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\drawChart_008.txt");
		List<Map> datatest = gson.fromJson(fr, List.class);
		Object actual = instanceTest.drawChart("", datatest, "Store");
		assertTrue(TestUTCommon.compare2object(new HashMap(), actual));
	}

}
