package org.wiperdog.realtimelib;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.wiperdog.drawchartdatalib.DataToDrawChart;
import org.wiperdog.lib.TestUTCommon;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Testcase for method getAdditionDataBar
 * Only test with Store job because Subtyped data will be processed at Xwiki layer
 * to transform into many Store data
 * 
 * @author luvina
 * 
 */
public class RealtimeDataLib_UT_05 {

	RealtimeDataLib instanceTest = new RealtimeDataLib();
	Gson gson = new GsonBuilder().setPrettyPrinting().create();

	/**
	 * Correct and full param with 2 records, 2 bar declarations each
	 * declaration has 2 chart_columns 
	 * 
	 * Expected : 2 * 2 * 2 = 8 records with
	 * format: 
	 * {
	 *  type : "bar", 
	 *  chartName : $_chartName, 
	 *  categories : $_fetchAt,
	 * data : $_data, 
	 * detailData : $_detailData } 
	 * Detail in tmp\\datatest\\RealtimeDataLib\\getAdditionDataBar_001.txt_expected
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void getAdditionDataBar_001() throws FileNotFoundException {
		List result = new ArrayList();
		FileReader fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getAdditionDataBar_001.txt");
		List<Map> datatest = gson.fromJson(fr, List.class);
		Map keyExprDeclaration = (Map) datatest.get(0).get("KEYEXPR");
		List key_root = (List) keyExprDeclaration.get("_root");
		Map key_unit = (Map) keyExprDeclaration.get("_unit");
		List chartDeclaration = ((List) keyExprDeclaration.get("_chart"));
		int chartNum = 0;
		for (Map chartDec : (List<Map>) chartDeclaration) {
			List chartColumns = (List) chartDec.get("chart_columns");
			List hintColumns = (List) chartDec.get("hint_columns");
			List ret = (List) instanceTest.getAdditionDataBar(datatest, chartColumns, hintColumns, key_root, key_unit, chartNum);
			chartNum++;
			result.addAll(ret);
		}

		fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getAdditionDataBar_001.txt_expected");
		List<Map> expected = gson.fromJson(fr, List.class);
		assertTrue(result.size() == 8);
		assertTrue(TestUTCommon.compare2object(expected, result));

	}

	/**
	 * Correct and full param with 1 record, 2 bar declarations each declaration
	 * has 2 chart_columns 
	 * 
	 * Expected : 1 * 2 * 2 = 4 records with format: 
	 * { 
	 *  type : "bar", 
	 *  chartName : $_chartName, 
	 *  categories : $_fetchAt, 
	 *  data : $_data,
	 *  detailData : $_detailData } 
	 *  Detail in tmp\\datatest\\RealtimeDataLib\\getAdditionDataBar_002.txt_expected
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void getAdditionDataBar_002() throws FileNotFoundException {
		List result = new ArrayList();
		FileReader fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getAdditionDataBar_002.txt");
		List<Map> datatest = gson.fromJson(fr, List.class);
		Map keyExprDeclaration = (Map) datatest.get(0).get("KEYEXPR");
		List key_root = (List) keyExprDeclaration.get("_root");
		Map key_unit = (Map) keyExprDeclaration.get("_unit");
		List chartDeclaration = ((List) keyExprDeclaration.get("_chart"));
		int chartNum = 0;
		for (Map chartDec : (List<Map>) chartDeclaration) {
			List chartColumns = (List) chartDec.get("chart_columns");
			List hintColumns = (List) chartDec.get("hint_columns");
			List ret = (List) instanceTest.getAdditionDataBar(datatest, chartColumns, hintColumns, key_root, key_unit, chartNum);
			chartNum++;
			result.addAll(ret);
		}

		fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getAdditionDataBar_002.txt_expected");
		List<Map> expected = gson.fromJson(fr, List.class);
		assertTrue(result.size() == 4);
		assertTrue(TestUTCommon.compare2object(expected, result));
	}

	/**
	 * Correct and full param with 1 record, 1 bar declaration each declaration
	 * has 2 chart_columns 
	 * 
	 * Expected : 1 * 1 * 2 = 2 records with format: 
	 * {
	 *  type : "bar", 
	 *  chartName : $_chartName, 
	 *  categories : $_fetchAt, 
	 *  data : $_data,
	 *  detailData : $_detailData } 
	 *  Detail in tmp\\datatest\\RealtimeDataLib\\getAdditionDataBar_002.txt_expected
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void getAdditionDataBar_003() throws FileNotFoundException {
		List result = new ArrayList();
		FileReader fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getAdditionDataBar_003.txt");
		List<Map> datatest = gson.fromJson(fr, List.class);
		Map keyExprDeclaration = (Map) datatest.get(0).get("KEYEXPR");
		List key_root = (List) keyExprDeclaration.get("_root");
		Map key_unit = (Map) keyExprDeclaration.get("_unit");
		List chartDeclaration = ((List) keyExprDeclaration.get("_chart"));
		int chartNum = 0;
		for (Map chartDec : (List<Map>) chartDeclaration) {
			List chartColumns = (List) chartDec.get("chart_columns");
			List hintColumns = (List) chartDec.get("hint_columns");
			List ret = (List) instanceTest.getAdditionDataBar(datatest, chartColumns, hintColumns, key_root, key_unit, chartNum);
			chartNum++;
			result.addAll(ret);
		}

		fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getAdditionDataBar_003.txt_expected");
		List<Map> expected = gson.fromJson(fr, List.class);
		assertTrue(result.size() == 2);
		assertTrue(TestUTCommon.compare2object(expected, result));
	}

	/**
	 * Correct and full param with 1 record, 1 bar declaration has 1
	 * chart_column 
	 * Expected : 1 * 1 * 1 = 1 records with format: 
	 * {
	 *  type : "bar", 
	 *  chartName : $_chartName, 
	 *  categories : $_fetchAt, 
	 *  data : $_data,
	 *  detailData : $_detailData } 
	 *  Detail in tmp\\datatest\\RealtimeDataLib\\getAdditionDataBar_002.txt_expected
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void getAdditionDataBar_004() throws FileNotFoundException {
		List result = new ArrayList();
		FileReader fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getAdditionDataBar_004.txt");
		List<Map> datatest = gson.fromJson(fr, List.class);
		Map keyExprDeclaration = (Map) datatest.get(0).get("KEYEXPR");
		List key_root = (List) keyExprDeclaration.get("_root");
		Map key_unit = (Map) keyExprDeclaration.get("_unit");
		List chartDeclaration = ((List) keyExprDeclaration.get("_chart"));
		int chartNum = 0;
		for (Map chartDec : (List<Map>) chartDeclaration) {
			List chartColumns = (List) chartDec.get("chart_columns");
			List hintColumns = (List) chartDec.get("hint_columns");
			List ret = (List) instanceTest.getAdditionDataBar(datatest, chartColumns, hintColumns, key_root, key_unit, chartNum);
			chartNum++;
			result.addAll(ret);
		}

		fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getAdditionDataBar_004.txt_expected");
		List<Map> expected = gson.fromJson(fr, List.class);
		assertTrue(result.size() == 1);
		assertTrue(TestUTCommon.compare2object(expected, result));
	}

	/**
	 * Data with no bar chart declaration should return an empty List 
	 * Expected:
	 * An empty list
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void getAdditionDataBar_005() throws FileNotFoundException {
		FileReader fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getAdditionDataBar_005.txt");
		List<Map> datatest = gson.fromJson(fr, List.class);
		Map keyExprDeclaration = (Map) datatest.get(0).get("KEYEXPR");
		List key_root = (List) keyExprDeclaration.get("_root");
		Map key_unit = (Map) keyExprDeclaration.get("_unit");

		List ret = (List) instanceTest.getAdditionDataBar(datatest, null, null, key_root, key_unit, 0);

		assertTrue(TestUTCommon.compare2object(new ArrayList(), ret));
	}

	/**
	 * Data input is empty List 
	 * Expected: AssertionError with message
	 * "Collection is null or empty!"
	 * 
	 * @throws FileNotFoundException
	 */
	@Test(expected = AssertionError.class)
	public void getAdditionDataBar_006() throws FileNotFoundException {
		try {
			List result = new ArrayList();
			FileReader fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getAdditionDataBar_001.txt");
			List<Map> datatest = gson.fromJson(fr, List.class);
			Map keyExprDeclaration = (Map) datatest.get(0).get("KEYEXPR");
			List key_root = (List) keyExprDeclaration.get("_root");
			Map key_unit = (Map) keyExprDeclaration.get("_unit");
			List chartDeclaration = ((List) keyExprDeclaration.get("_chart"));
			int chartNum = 0;
			for (Map chartDec : (List<Map>) chartDeclaration) {
				List chartColumns = (List) chartDec.get("chart_columns");
				List hintColumns = (List) chartDec.get("hint_columns");
				List ret = (List) instanceTest.getAdditionDataBar(new ArrayList(), chartColumns, hintColumns, key_root, key_unit, chartNum);
				chartNum++;
				result.addAll(ret);
			}
		} catch (AssertionError ae) {
			assertTrue(ae.getMessage().contains("Collection is null or empty!"));
			throw ae;
		}
	}

	/**
	 * Data input is null
	 * 
	 * Expected: AssertionError with message
	 * "Collection is null or empty!"
	 * @throws FileNotFoundException
	 */
	@Test(expected=AssertionError.class)
	public void getAdditionDataBar_007() throws FileNotFoundException {
		try {
			List result = new ArrayList();
			FileReader fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getAdditionDataBar_001.txt");
			List<Map> datatest = gson.fromJson(fr, List.class);
			Map keyExprDeclaration = (Map) datatest.get(0).get("KEYEXPR");
			List key_root = (List) keyExprDeclaration.get("_root");
			Map key_unit = (Map) keyExprDeclaration.get("_unit");
			List chartDeclaration = ((List) keyExprDeclaration.get("_chart"));
			int chartNum = 0;
			for (Map chartDec : (List<Map>) chartDeclaration) {
				List chartColumns = (List) chartDec.get("chart_columns");
				List hintColumns = (List) chartDec.get("hint_columns");
				List ret = (List) instanceTest.getAdditionDataBar(null, chartColumns, hintColumns, key_root, key_unit, chartNum);
				chartNum++;
				result.addAll(ret);
			}
		} catch (AssertionError ae) {
			assertTrue(ae.getMessage().contains("Collection is null or empty!"));
			throw ae;
		}
	}

	/**
	 * Data doesn't have unit declaration
	 * 
	 * Expected: Normal data like case 001 but detailData doesn't have unit string
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void getAdditionDataBar_008() throws FileNotFoundException {
		List result = new ArrayList();
		FileReader fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getAdditionDataBar_008.txt");
		List<Map> datatest = gson.fromJson(fr, List.class);
		Map keyExprDeclaration = (Map) datatest.get(0).get("KEYEXPR");
		List key_root = (List) keyExprDeclaration.get("_root");
		Map key_unit = (Map) keyExprDeclaration.get("_unit");
		List chartDeclaration = ((List) keyExprDeclaration.get("_chart"));
		int chartNum = 0;
		for (Map chartDec : (List<Map>) chartDeclaration) {
			List chartColumns = (List) chartDec.get("chart_columns");
			List hintColumns = (List) chartDec.get("hint_columns");
			List ret = (List) instanceTest.getAdditionDataBar(datatest, chartColumns, hintColumns, key_root, key_unit, chartNum);
			chartNum++;
			result.addAll(ret);
		}

		fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getAdditionDataBar_008.txt_expected");
		List<Map> expected = gson.fromJson(fr, List.class);
		assertTrue(result.size() == 8);
		assertTrue(TestUTCommon.compare2object(expected, result));

	}

	/**
	 * Chart declaration with no hintColumns
	 * 
	 * Expected: Normal data as case 001 but detailData only contains fetchAt field
	 * @throws FileNotFoundException 
	 * 
	 */
	@Test
	public void getAdditionDataBar_009() throws FileNotFoundException{
		List result = new ArrayList();
		FileReader fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getAdditionDataBar_009.txt");
		List<Map> datatest = gson.fromJson(fr, List.class);
		Map keyExprDeclaration = (Map) datatest.get(0).get("KEYEXPR");
		List key_root = (List) keyExprDeclaration.get("_root");
		Map key_unit = (Map) keyExprDeclaration.get("_unit");
		List chartDeclaration = ((List) keyExprDeclaration.get("_chart"));
		int chartNum = 0;
		for (Map chartDec : (List<Map>) chartDeclaration) {
			List chartColumns = (List) chartDec.get("chart_columns");
			List hintColumns = (List) chartDec.get("hint_columns");
			List ret = (List) instanceTest.getAdditionDataBar(datatest, chartColumns, hintColumns, key_root, key_unit, chartNum);
			chartNum++;
			result.addAll(ret);
		}

		fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getAdditionDataBar_009.txt_expected");
		List<Map> expected = gson.fromJson(fr, List.class);
		assertTrue(result.size() == 8);
		assertTrue(TestUTCommon.compare2object(expected, result));
		
	}
	
	/**
	 * Collection != null but collection.data = [] (empty List)
	 * 
	 * Expected : An empty List as result
	 * @throws FileNotFoundException 
	 * 
	 */
	@Test
	public void getAdditionDataBar_010() throws FileNotFoundException{
		List result = new ArrayList();
		FileReader fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getAdditionDataBar_010.txt");
		List<Map> datatest = gson.fromJson(fr, List.class);
		Map keyExprDeclaration = (Map) datatest.get(0).get("KEYEXPR");
		List key_root = (List) keyExprDeclaration.get("_root");
		Map key_unit = (Map) keyExprDeclaration.get("_unit");
		List chartDeclaration = ((List) keyExprDeclaration.get("_chart"));
		int chartNum = 0;
		for (Map chartDec : (List<Map>) chartDeclaration) {
			List chartColumns = (List) chartDec.get("chart_columns");
			List hintColumns = (List) chartDec.get("hint_columns");
			List ret = (List) instanceTest.getAdditionDataBar(datatest, chartColumns, hintColumns, key_root, key_unit, chartNum);
			chartNum++;
			assertEquals(new ArrayList(), ret);
		}
	}
	
	/**
	 * Data Subtyped with 1 record
	 * 2 groups : "D" and "M"
	 * D has 2 bar charts declared
	 *  - 1st chart has 1 chartColumn -> 1 record
	 *  - 2nd chart has 2 chartColumns -> 2 records
	 * M has 2 bar charts declared
	 *  - 1st chart has 1 chartColumn -> 1 record
	 *  - 2nd chart has 2 chartColumns -> 2 records
	 * 
	 * Expected : 
	 * List of 3 ("D" records) + 3 ("M" records) = 6 records
	 * "D" record has chartName with prefix D_
	 * "M" record has chartName with prefix M_
	 * 
	 * Further check in 
	 * tmp\\datatest\\RealtimeDataLib\\getAdditionDataBar_011.txt_expected
	 * 
	 * @throws FileNotFoundException 
	 */
	@Test
	public void getAdditionDataBar_011() throws FileNotFoundException{
		List result = new ArrayList();
		FileReader fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getAdditionDataBar_011.txt");
		List<Map> datatest = gson.fromJson(fr, List.class);
		
		Map dataSubtyped = (Map)DataToDrawChart.getDataToDrawSubtype(datatest);
		Set<String> keySet = dataSubtyped.keySet();
		for (String key : keySet) {
			List collection = (List)dataSubtyped.get(key);
			Map keyExprDeclaration = (Map)((Map) collection.get(0)).get("KEYEXPR");
			List key_root = (List) keyExprDeclaration.get("_root");
			Map key_unit = (Map) keyExprDeclaration.get("_unit");
			List chartDeclaration = ((List) keyExprDeclaration.get("_chart"));
			int chartNum = 0;
			for (Map chartDec : (List<Map>) chartDeclaration) {
				List chartColumns = (List) chartDec.get("chart_columns");
				List hintColumns = (List) chartDec.get("hint_columns");
				List ret = (List) instanceTest.getAdditionDataBar(collection, chartColumns, hintColumns, key_root, key_unit, chartNum, key);
				chartNum++;
				result.addAll(ret);
			}
		}
		fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getAdditionDataBar_011.txt_expected");
		List<Map> expected = gson.fromJson(fr, List.class);
		assertTrue(result.size() == 6);
		assertTrue(TestUTCommon.compare2object(expected, result));
	}
	
	/**
	 * No _root
	 * Expected:
	 * Normal data like case 001 but 
	 * in data field, check seriesName doesn't contains record identifier
	 *  
	 * @throws FileNotFoundException 
	 */
	@Test
	public void getAdditionDataBar_012() throws FileNotFoundException{
		List result = new ArrayList();
		FileReader fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getAdditionDataBar_012.txt");
		List<Map> datatest = gson.fromJson(fr, List.class);
		Map keyExprDeclaration = (Map) datatest.get(0).get("KEYEXPR");
		List key_root = (List) keyExprDeclaration.get("_root");
		Map key_unit = (Map) keyExprDeclaration.get("_unit");
		List chartDeclaration = ((List) keyExprDeclaration.get("_chart"));
		int chartNum = 0;
		for (Map chartDec : (List<Map>) chartDeclaration) {
			List chartColumns = (List) chartDec.get("chart_columns");
			List hintColumns = (List) chartDec.get("hint_columns");
			List ret = (List) instanceTest.getAdditionDataBar(datatest, chartColumns, hintColumns, key_root, key_unit, chartNum);
			chartNum++;
			result.addAll(ret);
		}

		fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getAdditionDataBar_012.txt_expected");
		List<Map> expected = gson.fromJson(fr, List.class);
		assertTrue(result.size() == 8);
		assertTrue(TestUTCommon.compare2object(expected, result));
	}
}
