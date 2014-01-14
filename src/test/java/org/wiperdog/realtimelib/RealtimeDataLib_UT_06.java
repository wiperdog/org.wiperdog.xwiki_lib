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
 * Testcase for getAdditionDataArea
 * Only test with Store job because Subtyped data will be processed at Xwiki layer
 * to transform into many Store data
 * 
 * @author luvina
 *
 */
public class RealtimeDataLib_UT_06 {
	RealtimeDataLib instanceTest = new RealtimeDataLib();
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	/**
	 * Full and valid data of 2 database and 2 area declared
	 * 
	 * Expected: 2 * 2 = 4 records with format
	 * {
	 *  type : "area",
	 *  chartName : $_chartName,
	 *  nameOfChart : $_ReadableNameForTitleOfChart,
	 *  categories : $_fetchAt,
	 *  data : $_data,
	 *  detailData : $_detailData
	 *  }
	 *  Detail in tmp\\datatest\\RealtimeDataLib\\getAdditionDataArea_001.txt_expected
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void getAdditionDataArea_001() throws FileNotFoundException {
		List result = new ArrayList();
		FileReader fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getAdditionDataArea_001.txt");
		List<Map> datatest = gson.fromJson(fr, List.class);
		Map keyExprDeclaration = (Map) datatest.get(0).get("KEYEXPR");
		List key_root = (List) keyExprDeclaration.get("_root");
		Map key_unit = (Map) keyExprDeclaration.get("_unit");
		List chartDeclaration = ((List) keyExprDeclaration.get("_chart"));
		int chartNum = 0;
		for (Map chartDec : (List<Map>) chartDeclaration) {
			List chartColumns = (List) chartDec.get("chart_columns");
			List hintColumns = (List) chartDec.get("hint_columns");
			String chartName = (String)chartDec.get("name");
			List ret = (List) instanceTest.getAdditionDataArea(datatest, chartColumns, hintColumns, key_root, key_unit, chartNum, chartName);
			chartNum++;
			result.addAll(ret);
		}

		fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getAdditionDataArea_001.txt_expected");
		List<Map> expected = gson.fromJson(fr, List.class);
		assertTrue(result.size() == 4);
		assertTrue(TestUTCommon.compare2object(expected, result));
	}

	/**
	 * Full and valid data of 1 database with 2 area declared
	 * 
	 * Expected: 1 * 2 = 2 records with format
	 * {
	 *  type : "area",
	 *  chartName : $_chartName,
	 *  nameOfChart : $_ReadableNameForTitleOfChart,
	 *  categories : $_fetchAt,
	 *  data : $_data,
	 *  detailData : $_detailData
	 *  } 
	 *  
	 * Detail in tmp\\datatest\\RealtimeDataLib\\getAdditionDataArea_002.txt_expected
	 * @throws FileNotFoundException 
	 * 
	 */
	@Test
	public void getAdditionDataArea_002() throws FileNotFoundException {
		List result = new ArrayList();
		FileReader fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getAdditionDataArea_002.txt");
		List<Map> datatest = gson.fromJson(fr, List.class);
		Map keyExprDeclaration = (Map) datatest.get(0).get("KEYEXPR");
		List key_root = (List) keyExprDeclaration.get("_root");
		Map key_unit = (Map) keyExprDeclaration.get("_unit");
		List chartDeclaration = ((List) keyExprDeclaration.get("_chart"));
		int chartNum = 0;
		for (Map chartDec : (List<Map>) chartDeclaration) {
			List chartColumns = (List) chartDec.get("chart_columns");
			List hintColumns = (List) chartDec.get("hint_columns");
			String chartName = (String)chartDec.get("name");
			List ret = (List) instanceTest.getAdditionDataArea(datatest, chartColumns, hintColumns, key_root, key_unit, chartNum, chartName);
			chartNum++;
			result.addAll(ret);
		}

		fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getAdditionDataArea_002.txt_expected");
		List<Map> expected = gson.fromJson(fr, List.class);
		assertTrue(result.size() == 2);
		assertTrue(TestUTCommon.compare2object(expected, result));
	}

	/**
	 * Full and valid data of 2 database with 1 area declared
	 * 
	 * Expected: 2 * 1 = 2 records with format
	 * {
	 *  type : "area",
	 *  chartName : $_chartName,
	 *  nameOfChart : $_ReadableNameForTitleOfChart,
	 *  categories : $_fetchAt,
	 *  data : $_data,
	 *  detailData : $_detailData
	 *  } 
	 *  
	 * Detail in tmp\\datatest\\RealtimeDataLib\\getAdditionDataArea_003.txt_expected
	 * 
	 * @throws FileNotFoundException 
	 */
	@Test
	public void getAdditionDataArea_003() throws FileNotFoundException {
		List result = new ArrayList();
		FileReader fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getAdditionDataArea_003.txt");
		List<Map> datatest = gson.fromJson(fr, List.class);
		Map keyExprDeclaration = (Map) datatest.get(0).get("KEYEXPR");
		List key_root = (List) keyExprDeclaration.get("_root");
		Map key_unit = (Map) keyExprDeclaration.get("_unit");
		List chartDeclaration = ((List) keyExprDeclaration.get("_chart"));
		int chartNum = 0;
		for (Map chartDec : (List<Map>) chartDeclaration) {
			List chartColumns = (List) chartDec.get("chart_columns");
			List hintColumns = (List) chartDec.get("hint_columns");
			String chartName = (String)chartDec.get("name");
			List ret = (List) instanceTest.getAdditionDataArea(datatest, chartColumns, hintColumns, key_root, key_unit, chartNum, chartName);
			chartNum++;
			result.addAll(ret);
		}

		fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getAdditionDataArea_003.txt_expected");
		List<Map> expected = gson.fromJson(fr, List.class);
		assertTrue(result.size() == 2);
		assertTrue(TestUTCommon.compare2object(expected, result));
	}

	/**
	 * Full and valid data of 1 database with 1 area declared
	 * 
	 * Expected: 1 records with format
	 * {
	 *  type : "area",
	 *  chartName : $_chartName,
	 *  nameOfChart : $_ReadableNameForTitleOfChart,
	 *  categories : $_fetchAt,
	 *  data : $_data,
	 *  detailData : $_detailData
	 *  } 
	 *  
	 * Detail in tmp\\datatest\\RealtimeDataLib\\getAdditionDataArea_004.txt_expected
	 * @throws FileNotFoundException 
	 */
	@Test
	public void getAdditionDataArea_004() throws FileNotFoundException {
		List result = new ArrayList();
		FileReader fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getAdditionDataArea_004.txt");
		List<Map> datatest = gson.fromJson(fr, List.class);
		Map keyExprDeclaration = (Map) datatest.get(0).get("KEYEXPR");
		List key_root = (List) keyExprDeclaration.get("_root");
		Map key_unit = (Map) keyExprDeclaration.get("_unit");
		List chartDeclaration = ((List) keyExprDeclaration.get("_chart"));
		int chartNum = 0;
		for (Map chartDec : (List<Map>) chartDeclaration) {
			List chartColumns = (List) chartDec.get("chart_columns");
			List hintColumns = (List) chartDec.get("hint_columns");
			String chartName = (String)chartDec.get("name");
			List ret = (List) instanceTest.getAdditionDataArea(datatest, chartColumns, hintColumns, key_root, key_unit, chartNum, chartName);
			chartNum++;
			result.addAll(ret);
		}

		fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getAdditionDataArea_004.txt_expected");
		List<Map> expected = gson.fromJson(fr, List.class);
		assertTrue(result.size() == 1);
		assertTrue(TestUTCommon.compare2object(expected, result));
	}

	/**
	 * Data is null
	 * 
	 * Expected:
	 * AssertionError with message : "Collection is null or empty!"
	 * @throws FileNotFoundException 
	 * 
	 */
	@Test(expected=AssertionError.class)
	public void getAdditionDataArea_005() throws FileNotFoundException {
		try{
			List result = new ArrayList();
			FileReader fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getAdditionDataArea_001.txt");
			List<Map> datatest = gson.fromJson(fr, List.class);
			Map keyExprDeclaration = (Map) datatest.get(0).get("KEYEXPR");
			List key_root = (List) keyExprDeclaration.get("_root");
			Map key_unit = (Map) keyExprDeclaration.get("_unit");
			List chartDeclaration = ((List) keyExprDeclaration.get("_chart"));
			int chartNum = 0;
			for (Map chartDec : (List<Map>) chartDeclaration) {
				List chartColumns = (List) chartDec.get("chart_columns");
				List hintColumns = (List) chartDec.get("hint_columns");
				String chartName = (String)chartDec.get("name");
				List ret = (List) instanceTest.getAdditionDataArea(null, chartColumns, hintColumns, key_root, key_unit, chartNum, chartName);
				chartNum++;
				result.addAll(ret);
			}
		}catch(AssertionError ae){
			assertTrue(ae.getMessage().contains("Collection is null or empty!"));
			throw ae;
		}
	}

	/**
	 * Data is an empty List
	 * 
	 * Expected:
	 * AssertionError with message : "Collection is null or empty"
	 * @throws FileNotFoundException 
	 */
	@Test(expected=AssertionError.class)
	public void getAdditionDataArea_006() throws FileNotFoundException {
		try{
			List result = new ArrayList();
			FileReader fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getAdditionDataArea_001.txt");
			List<Map> datatest = gson.fromJson(fr, List.class);
			Map keyExprDeclaration = (Map) datatest.get(0).get("KEYEXPR");
			List key_root = (List) keyExprDeclaration.get("_root");
			Map key_unit = (Map) keyExprDeclaration.get("_unit");
			List chartDeclaration = ((List) keyExprDeclaration.get("_chart"));
			int chartNum = 0;
			for (Map chartDec : (List<Map>) chartDeclaration) {
				List chartColumns = (List) chartDec.get("chart_columns");
				List hintColumns = (List) chartDec.get("hint_columns");
				String chartName = (String)chartDec.get("name");
				List ret = (List) instanceTest.getAdditionDataArea(new ArrayList(), chartColumns, hintColumns, key_root, key_unit, chartNum, chartName);
				chartNum++;
				result.addAll(ret);
			}
		}catch(AssertionError ae){
			assertTrue(ae.getMessage().contains("Collection is null or empty"));
			throw ae;
		}
	}

	/**
	 * Data with no area declared
	 * 
	 * Expected:
	 * An empty List as result
	 * @throws FileNotFoundException 
	 */
	@Test
	public void getAdditionDataArea_007() throws FileNotFoundException {
		List result = new ArrayList();
		FileReader fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getAdditionDataArea_007.txt");
		List<Map> datatest = gson.fromJson(fr, List.class);
		Map keyExprDeclaration = (Map) datatest.get(0).get("KEYEXPR");
		List key_root = (List) keyExprDeclaration.get("_root");
		Map key_unit = (Map) keyExprDeclaration.get("_unit");
		List chartDeclaration = ((List) keyExprDeclaration.get("_chart"));
		int chartNum = 0;
		List ret = (List) instanceTest.getAdditionDataArea(datatest, null, null, key_root, key_unit, chartNum, null);
		chartNum++;
		result.addAll(ret);

		assertTrue(TestUTCommon.compare2object(new ArrayList(), result));
	}

	/**
	 * Valid data but no hintColumns
	 * Expected:
	 * Normal data like case 001 but detail data only contains fetchAt
	 * 
	 * Detail in tmp\\datatest\\RealtimeDataLib\\getAdditionDataArea_008.txt_expected
	 * 
	 * @throws FileNotFoundException 
	 * 
	 */
	@Test
	public void getAdditionDataArea_008() throws FileNotFoundException {
		List result = new ArrayList();
		FileReader fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getAdditionDataArea_008.txt");
		List<Map> datatest = gson.fromJson(fr, List.class);
		Map keyExprDeclaration = (Map) datatest.get(0).get("KEYEXPR");
		List key_root = (List) keyExprDeclaration.get("_root");
		Map key_unit = (Map) keyExprDeclaration.get("_unit");
		List chartDeclaration = ((List) keyExprDeclaration.get("_chart"));
		int chartNum = 0;
		for (Map chartDec : (List<Map>) chartDeclaration) {
			List chartColumns = (List) chartDec.get("chart_columns");
			List hintColumns = (List) chartDec.get("hint_columns");
			String chartName = (String)chartDec.get("name");
			List ret = (List) instanceTest.getAdditionDataArea(datatest, chartColumns, hintColumns, key_root, key_unit, chartNum, chartName);
			chartNum++;
			result.addAll(ret);
		}

		fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getAdditionDataArea_008.txt_expected");
		List<Map> expected = gson.fromJson(fr, List.class);
		assertTrue(result.size() == 4);
		assertTrue(TestUTCommon.compare2object(expected, result));
	}

	/**
	 * Valid data but no unit
	 * Expected:
	 * Normal data like case 001 but detail data doesn't contains unit string
	 * 
	 * Detail in tmp\\datatest\\RealtimeDataLib\\getAdditionDataArea_009.txt_expected
	 * 
	 * @throws FileNotFoundException 
	 * 
	 */
	@Test
	public void getAdditionDataArea_009() throws FileNotFoundException {
		List result = new ArrayList();
		FileReader fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getAdditionDataArea_009.txt");
		List<Map> datatest = gson.fromJson(fr, List.class);
		Map keyExprDeclaration = (Map) datatest.get(0).get("KEYEXPR");
		List key_root = (List) keyExprDeclaration.get("_root");
		Map key_unit = (Map) keyExprDeclaration.get("_unit");
		List chartDeclaration = ((List) keyExprDeclaration.get("_chart"));
		int chartNum = 0;
		for (Map chartDec : (List<Map>) chartDeclaration) {
			List chartColumns = (List) chartDec.get("chart_columns");
			List hintColumns = (List) chartDec.get("hint_columns");
			String chartName = (String)chartDec.get("name");
			List ret = (List) instanceTest.getAdditionDataArea(datatest, chartColumns, hintColumns, key_root, key_unit, chartNum, chartName);
			chartNum++;
			result.addAll(ret);
		}

		fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getAdditionDataArea_009.txt_expected");
		List<Map> expected = gson.fromJson(fr, List.class);
		assertTrue(result.size() == 4);
		assertTrue(TestUTCommon.compare2object(expected, result));
	}
	
	/**
	 * Collection != null but collection.data = [] (Empty list)
	 * 
	 * Expected:
	 * An empty list as result
	 * 
	 * @throws FileNotFoundException 
	 * 
	 */
	@Test
	public void getAdditionDataArea_010() throws FileNotFoundException{
		List result = new ArrayList();
		FileReader fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getAdditionDataArea_010.txt");
		List<Map> datatest = gson.fromJson(fr, List.class);
		Map keyExprDeclaration = (Map) datatest.get(0).get("KEYEXPR");
		List key_root = (List) keyExprDeclaration.get("_root");
		Map key_unit = (Map) keyExprDeclaration.get("_unit");
		List chartDeclaration = ((List) keyExprDeclaration.get("_chart"));
		int chartNum = 0;
		for (Map chartDec : (List<Map>) chartDeclaration) {
			List chartColumns = (List) chartDec.get("chart_columns");
			List hintColumns = (List) chartDec.get("hint_columns");
			String chartName = (String)chartDec.get("name");
			List ret = (List) instanceTest.getAdditionDataArea(datatest, chartColumns, hintColumns, key_root, key_unit, chartNum, chartName);
			chartNum++;
			assertEquals(new ArrayList(), ret);
		}
	}

	/**
	 * data Subtyped with 1 record, data of 2 databases
	 * 2 groups : "D" and "M"
	 * D has 1 area charts declared
	 *  - chart has 1 chartColumn -> 1 * 2 db = 2 records
	 * M has 2 area charts declared
	 *  - 1st chart has 1 chartColumn -> 1 * 2 db = 2 records
	 *  - 2nd chart has 1 chartColumn -> 1 * 2 db = 2 records
	 *  
	 * Expected: List of 6 records(2 "D" records + 4 "M" records)
	 * "D" record has chartName with prefix D_
	 * "M" record has chartName with prefix M_
	 * 
	 * Check further data in 
	 * tmp\\datatest\\RealtimeDataLib\\getAdditionDataLine_008.txt_expected
	 *  
	 * @throws FileNotFoundException 
	 */
	@Test
	public void getAdditionDataArea_011() throws FileNotFoundException{
		List result = new ArrayList();
		FileReader fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getAdditionDataArea_011.txt");
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
				String chartName = (String)chartDec.get("name");
				List ret = (List) instanceTest.getAdditionDataArea(collection, chartColumns, hintColumns, key_root, key_unit, chartNum, chartName, key);
				chartNum++;
				result.addAll(ret);
			}
		}

		fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getAdditionDataArea_011.txt_expected");
		List<Map> expected = gson.fromJson(fr, List.class);
		assertTrue(result.size() == 6);
		assertTrue(TestUTCommon.compare2object(expected, result));
	}
	
	/**
	 * No _root
	 * 2 charts declared
	 * 
	 * Normal data like case 001 but 
	 * in data field, check chartName doesn't contains record identifier but xx_root (xx is number of chart declared)
	 * Because no _root = data of 1 database -> result size is 1 db * 2 charts = 2 records
	 * 
	 * @throws FileNotFoundException 
	 */
	@Test
	public void getAdditionDataArea_012() throws FileNotFoundException{
		List result = new ArrayList();
		FileReader fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getAdditionDataArea_012.txt");
		List<Map> datatest = gson.fromJson(fr, List.class);
		Map keyExprDeclaration = (Map) datatest.get(0).get("KEYEXPR");
		List key_root = (List) keyExprDeclaration.get("_root");
		Map key_unit = (Map) keyExprDeclaration.get("_unit");
		List chartDeclaration = ((List) keyExprDeclaration.get("_chart"));
		int chartNum = 0;
		for (Map chartDec : (List<Map>) chartDeclaration) {
			List chartColumns = (List) chartDec.get("chart_columns");
			List hintColumns = (List) chartDec.get("hint_columns");
			String chartName = (String)chartDec.get("name");
			List ret = (List) instanceTest.getAdditionDataArea(datatest, chartColumns, hintColumns, key_root, key_unit, chartNum, chartName);
			chartNum++;
			result.addAll(ret);
		}

		fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getAdditionDataArea_012.txt_expected");
		List<Map> expected = gson.fromJson(fr, List.class);
		assertTrue(result.size() == 2);
		assertTrue(TestUTCommon.compare2object(expected, result));
	}
}
