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
 * Testcase for method getAdditionDataLine
 * Only test with Store job because Subtyped data will be processed at Xwiki layer
 * to transform into many Store data
 *  
 * @author luvina
 * 
 */
public class RealtimeDataLib_UT_04 {
	RealtimeDataLib instanceTest = new RealtimeDataLib();
	Gson gson = new GsonBuilder().setPrettyPrinting().create();

	/**
	 * Correct and full param with 2 records and 2 line charts declared
	 * 1st line declared has 2 chartColumns
	 * 2nd line declared has 1 chartColumn
	 * (ChartColumns doesn't affect result size but data and detailData of it)
	 * result size is 2 * 2 = 4
	 * 
	 * Expected: A list of 4 records with format :
	 * {type : "line_"
	 *  chartName : $_chartname,
	 *  categories : $_fetchAt,
	 *  data : $_data,
	 *  detailData : $_detailData} 
	 * Detail in tmp\\datatest\\RealtimeDataLib\\getAdditionDataLine_001.txt_expected
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void getAdditionDataLine_001() throws FileNotFoundException {
		List result = new ArrayList();
		FileReader fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getAdditionDataLine_001.txt");
		List<Map> datatest = gson.fromJson(fr, List.class);
		Map keyExprDeclaration = (Map) datatest.get(0).get("KEYEXPR");
		List key_root = (List) keyExprDeclaration.get("_root");
		Map key_unit = (Map) keyExprDeclaration.get("_unit");
		List chartDeclaration = ((List) keyExprDeclaration.get("_chart"));
		for (Map chartDec : (List<Map>) chartDeclaration) {
			List chartColumns = (List) chartDec.get("chart_columns");
			List hintColumns = (List) chartDec.get("hint_columns");
			List ret = (List) instanceTest.getAdditionDataLine(datatest, "", chartColumns, hintColumns, key_root, key_unit);
			result.addAll(ret);
		}
		
		fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getAdditionDataLine_001.txt_expected");
		List<Map> expected = gson.fromJson(fr, List.class);
		assertTrue(result.size() == 4);
		assertTrue(TestUTCommon.compare2object(expected, result));

	}

	/**
	 * Correct and full param with just 1 record and 1 line chart declaration
	 * line declaration has 2 chartColumns
	 * 
	 * Expected: A list of 1 record with format like case 001
	 * tmp\\datatest\\RealtimeDataLib\\getAdditionDataLine_002.txt_expected
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void getAdditionDataLine_002() throws FileNotFoundException {
		List result = new ArrayList();
		FileReader fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getAdditionDataLine_002.txt");
		List<Map> datatest = gson.fromJson(fr, List.class);
		Map keyExprDeclaration = (Map) datatest.get(0).get("KEYEXPR");
		List key_root = (List) keyExprDeclaration.get("_root");
		Map key_unit = (Map) keyExprDeclaration.get("_unit");
		List chartDeclaration = ((List) keyExprDeclaration.get("_chart"));
		for (Map chartDec : (List<Map>) chartDeclaration) {
			List chartColumns = (List) chartDec.get("chart_columns");
			List hintColumns = (List) chartDec.get("hint_columns");
			List ret = (List) instanceTest.getAdditionDataLine(datatest, "", chartColumns, hintColumns, key_root, key_unit);
			result.addAll(ret);
		}

		fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getAdditionDataLine_002.txt_expected");
		List<Map> expected = gson.fromJson(fr, List.class);
		assertTrue(datatest.size() == result.size());
		assertTrue(TestUTCommon.compare2object(expected, result));
	}

	/**
	 * Collection is null -> return an empty List
	 * Expected: An empty List
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void getAdditionDataLine_003() throws FileNotFoundException {
		List result = new ArrayList();
		FileReader fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getAdditionDataLine_002.txt");
		List<Map> datatest = gson.fromJson(fr, List.class);
		// Get chartColumns
		Map keyExprDeclaration = (Map) datatest.get(0).get("KEYEXPR");
		List key_root = (List) keyExprDeclaration.get("_root");
		Map key_unit = (Map) keyExprDeclaration.get("_unit");
		List chartDeclaration = ((List) keyExprDeclaration.get("_chart"));
		for (Map chartDec : (List<Map>) chartDeclaration) {
			List chartColumns = (List) chartDec.get("chart_columns");
			List hintColumns = (List) chartDec.get("hint_columns");
			List ret = (List) instanceTest.getAdditionDataLine(null, "", chartColumns, hintColumns, key_root, key_unit);
			result.addAll(ret);
		}

		assertTrue(TestUTCommon.compare2object(new ArrayList(), result));
	}

	/**
	 * no _chart declared -> chartColumns is null, hintColumns is null 
	 * Expected: An empty list
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void getAdditionDataLine_004() throws FileNotFoundException {
		FileReader fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getAdditionDataLine_004.txt");
		List result = new ArrayList();
		
		List<Map> datatest = gson.fromJson(fr, List.class);
		Map keyExprDeclaration = (Map) datatest.get(0).get("KEYEXPR");
		List key_root = (List) keyExprDeclaration.get("_root");
		Map key_unit = (Map) keyExprDeclaration.get("_unit");
		List ret = (List) instanceTest.getAdditionDataLine(datatest, "", null, null, key_root, key_unit);
		result.addAll(ret);

		assertTrue(TestUTCommon.compare2object(new ArrayList(), result));
	}

	/**
	 * No unit declaration -> detail data won't have unit
	 * Expected: A List like case 001 but detailData won't have unit string after it
	 * @throws FileNotFoundException 
	 */
	@Test
	public void getAdditionDataLine_005() throws FileNotFoundException {
		List result = new ArrayList();
		FileReader fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getAdditionDataLine_005.txt");
		List<Map> datatest = gson.fromJson(fr, List.class);
		// Get chartColumns
		Map keyExprDeclaration = (Map) datatest.get(0).get("KEYEXPR");
		List key_root = (List) keyExprDeclaration.get("_root");
		Map key_unit = (Map) keyExprDeclaration.get("_unit");
		List chartDeclaration = ((List) keyExprDeclaration.get("_chart"));
		for (Map chartDec : (List<Map>) chartDeclaration) {
			List chartColumns = (List) chartDec.get("chart_columns");
			List hintColumns = (List) chartDec.get("hint_columns");
			List ret = (List) instanceTest.getAdditionDataLine(datatest, "", chartColumns, hintColumns, key_root, key_unit);
			result.addAll(ret);
		}
		fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getAdditionDataLine_005.txt_expected");
		List<Map> expected = gson.fromJson(fr, List.class);
		assertTrue(TestUTCommon.compare2object(expected, result));
	}

	/**
	 * no hint_columns -> detail data only contains fetchAt
	 * Expected: A List like case 001 but detailData will only contains fetchAt
	 * @throws FileNotFoundException
	 */
	@Test
	public void getAdditionDataLine_006() throws FileNotFoundException {
		List result = new ArrayList();
		FileReader fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getAdditionDataLine_006.txt");
		List<Map> datatest = gson.fromJson(fr, List.class);
		// Get chartColumns
		Map keyExprDeclaration = (Map) datatest.get(0).get("KEYEXPR");
		List key_root = (List) keyExprDeclaration.get("_root");
		Map key_unit = (Map) keyExprDeclaration.get("_unit");
		List chartDeclaration = ((List) keyExprDeclaration.get("_chart"));
		for (Map chartDec : (List<Map>) chartDeclaration) {
			List chartColumns = (List) chartDec.get("chart_columns");
			List hintColumns = (List) chartDec.get("hint_columns");
			List ret = (List) instanceTest.getAdditionDataLine(datatest, "", chartColumns, hintColumns, key_root, key_unit);
			result.addAll(ret);
		}
		fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getAdditionDataLine_006.txt_expected");
		List<Map> expected = gson.fromJson(fr, List.class);
		assertTrue(TestUTCommon.compare2object(expected, result));
	}
	
	/**
	 * Collection != null but data is an empty List (collection.data = [])
	 * 
	 * Expected: Empty List as result
	 * 
	 * @throws FileNotFoundException 
	 * 
	 */
	@Test
	public void getAdditionDataLine_007() throws FileNotFoundException{
		List result = new ArrayList();
		FileReader fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getAdditionDataLine_007.txt");
		List<Map> datatest = gson.fromJson(fr, List.class);
		// Get chartColumns
		Map keyExprDeclaration = (Map) datatest.get(0).get("KEYEXPR");
		List key_root = (List) keyExprDeclaration.get("_root");
		Map key_unit = (Map) keyExprDeclaration.get("_unit");
		List chartDeclaration = ((List) keyExprDeclaration.get("_chart"));
		for (Map chartDec : (List<Map>) chartDeclaration) {
			List chartColumns = (List) chartDec.get("chart_columns");
			List hintColumns = (List) chartDec.get("hint_columns");
			List ret = (List) instanceTest.getAdditionDataLine(datatest, "", chartColumns, hintColumns, key_root, key_unit);
			assertEquals(new ArrayList(), ret);
		}
	}
	
	
	/**
	 * Test for data Subtyped with 1 record
	 * 2 groups : "D" and "M"
	 * D has 2 line charts declared
	 *  - 1st chart has 1 chartColumn
	 *  - 2nd chart has 1 chartColumn
	 * M has 2 line charts declared
	 *  - 1st chart has 1 chartColumn
	 *  - 2nd chart has 2 chartColumns
	 * (ChartColumns doesn't affect result size but data and detailData of it)
	 * 
	 * Expected : 
	 * List of 2 ("D" records) + 2 ("M" records) = 4 records
	 * "D" record has chartName with prefix D_
	 * "M" record has chartName with prefix M_
	 * 
	 * Check further data in 
	 * tmp\\datatest\\RealtimeDataLib\\getAdditionDataLine_008.txt_expected
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void getAdditionDataLine_008() throws FileNotFoundException{
		List result = new ArrayList();
		FileReader fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getAdditionDataLine_008.txt");
		List<Map> datatest = gson.fromJson(fr, List.class);
		
		Map dataSubtyped = (Map)DataToDrawChart.getDataToDrawSubtype(datatest);
		Set<String> keySet = dataSubtyped.keySet();
		for (String key : keySet) {
			List collection = (List)dataSubtyped.get(key);
			Map keyExprDeclaration = (Map)((Map)collection.get(0)).get("KEYEXPR");
			List key_root = (List) keyExprDeclaration.get("_root");
			Map key_unit = (Map) keyExprDeclaration.get("_unit");
			List chartDeclaration = ((List) keyExprDeclaration.get("_chart"));
			for (Map chartDec : (List<Map>) chartDeclaration) {
				List chartColumns = (List) chartDec.get("chart_columns");
				List hintColumns = (List) chartDec.get("hint_columns");
				List ret = (List) instanceTest.getAdditionDataLine(collection, key, chartColumns, hintColumns, key_root, key_unit);
				result.addAll(ret);
			}
		}
		
		assertEquals(4, result.size());
		
		fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getAdditionDataLine_008.txt_expected");
		List<Map> expected = gson.fromJson(fr, List.class);
		assertTrue(TestUTCommon.compare2object(expected, result));
	}
	
	
	/**
	 * Collection with 2 records and 2 line charts declared
	 * No _root
	 * 
	 * Expected:
	 * Normal data like case 001 but 
	 * in data field, check seriesName doesn't contains record identifier 
	 * 
	 * Detail in tmp\\datatest\\RealtimeDataLib\\getAdditionDataLine_009.txt_expected
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void getAdditionDataLine_009() throws FileNotFoundException {
		List result = new ArrayList();
		FileReader fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getAdditionDataLine_009.txt");
		List<Map> datatest = gson.fromJson(fr, List.class);
		Map keyExprDeclaration = (Map) datatest.get(0).get("KEYEXPR");
		List key_root = (List) keyExprDeclaration.get("_root");
		Map key_unit = (Map) keyExprDeclaration.get("_unit");
		List chartDeclaration = ((List) keyExprDeclaration.get("_chart"));
		for (Map chartDec : (List<Map>) chartDeclaration) {
			List chartColumns = (List) chartDec.get("chart_columns");
			List hintColumns = (List) chartDec.get("hint_columns");
			List ret = (List) instanceTest.getAdditionDataLine(datatest, "", chartColumns, hintColumns, key_root, key_unit);
			result.addAll(ret);
		}
		fr = new FileReader("tmp\\datatest\\RealtimeDataLib\\getAdditionDataLine_009.txt_expected");
		List<Map> expected = gson.fromJson(fr, List.class);
		assertTrue(result.size() == 4);
		assertTrue(TestUTCommon.compare2object(expected, result));

	}
}
