package org.wiperdog.realtimelib;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.wiperdog.lib.TestUTCommon;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Testcase for method getAdditionData
 * Only test with Store job because Subtyped data will be processed at Xwiki layer
 * to transform into many Store data
 * 
 * @author luvina
 *
 */
public class RealtimeDataLib_UT_07 {
	RealtimeDataLib instanceTest = new RealtimeDataLib();
	Gson gson = new GsonBuilder().setPrettyPrinting().create();

	/**
	 * Valid and full data with just line chart declared
	 * 
	 * Expected: List records with format:
	 * {type : "line_"
	 *  chartName : $_chartname,
	 *  categories : $_fetchAt,
	 *  data : $_data,
	 *  detailData : $_detailData} 
	 * Detail in tmp\\datatest\\RealtimeDataLib\\getAdditionData_001.txt_expected
	 * 
	 * @throws IOException 
	 */
	@Test
	public void getAdditionData_001() throws IOException {
		List result;
		List datatest = (List)TestUTCommon.getObjectFromFile("tmp\\datatest\\RealtimeDataLib\\getAdditionData_001.txt");
		result = (List) instanceTest.getAdditionData(datatest, "");

		List expected = (List)TestUTCommon.getObjectFromFile("tmp\\datatest\\RealtimeDataLib\\getAdditionData_001.txt_expected");
		
		for (Map map : (List<Map>)result) {
			// Check type = line_
			assertEquals("line_", map.get("type"));
		}
		assertTrue(TestUTCommon.compare2object(expected, result));
	}

	/**
	 * Valid and full data with just bar chart declared
	 * 
	 * Expected: List records with format:
	 * {type : "bar"
	 *  chartName : $_chartname,
	 *  categories : $_fetchAt,
	 *  data : $_data,
	 *  detailData : $_detailData} 
	 * Detail in tmp\\datatest\\RealtimeDataLib\\getAdditionData_002.txt_expected
	 * 
	 * @throws IOException 
	 */
	@Test
	public void getAdditionData_002() throws IOException {
		List result;
		List datatest = (List)TestUTCommon.getObjectFromFile("tmp\\datatest\\RealtimeDataLib\\getAdditionData_002.txt");
		result = (List) instanceTest.getAdditionData(datatest, "");
		
		List expected = (List)TestUTCommon.getObjectFromFile("tmp\\datatest\\RealtimeDataLib\\getAdditionData_002.txt_expected");
		
		for (Map map : (List<Map>)result) {
			// Check type = bar
			assertEquals("bar", map.get("type"));
		}
		assertTrue(TestUTCommon.compare2object(expected, result));
	}

	/**
	 * Valid and full data with just area chart declared
	 * Expected: List records with format:
	 *  {
	 *  type : "area",
	 *  chartName : $_chartName,
	 *  nameOfChart : $_ReadableNameForTitleOfChart,
	 *  categories : $_fetchAt,
	 *  data : $_data,
	 *  detailData : $_detailData
	 *  } 
	 * @throws IOException 
	 */
	@Test
	public void getAdditionData_003() throws IOException {
		List result;
		List datatest = (List)TestUTCommon.getObjectFromFile("tmp\\datatest\\RealtimeDataLib\\getAdditionData_003.txt");
		result = (List) instanceTest.getAdditionData(datatest, "");
		
		List expected = (List)TestUTCommon.getObjectFromFile("tmp\\datatest\\RealtimeDataLib\\getAdditionData_003.txt_expected");
		
		for (Map map : (List<Map>)result) {
			// Check type = area
			assertEquals("area", map.get("type"));
		}
		assertTrue(TestUTCommon.compare2object(expected, result));
	}

	/**
	 * Valid and full data with mixed charts declared
	 * Expected: List record of line, bar, area with format:
	 * {
	 *  type : $_type,
	 *  chartName : $_chartName,
	 *  (nameOfChart : $_ReadableNameForTitleOfChart,) // This field only exists in area record
	 *  categories : $_fetchAt,
	 *  data : $_data,
	 *  detailData : $_detailData
	 *  } 
	 * @throws IOException 
	 */
	@Test
	public void getAdditionData_004() throws IOException {
		List result;
		List datatest = (List)TestUTCommon.getObjectFromFile("tmp\\datatest\\RealtimeDataLib\\getAdditionData_004.txt");
		result = (List) instanceTest.getAdditionData(datatest, "");

		List expected = (List)TestUTCommon.getObjectFromFile("tmp\\datatest\\RealtimeDataLib\\getAdditionData_004.txt_expected");
		
		for (Map map : (List<Map>)result) {
			// Check type could be 3 options line_, bar, area
			Object type = map.get("type");
			if(!"line_".equals(type) && !"bar".equals(type) && !"area".equals(type)){
				fail();
			}
		}
		assertTrue(TestUTCommon.compare2object(expected, result));
	}

	/**
	 * Valid data but no KEYEXPR
	 * 
	 * Expected: an empty List
	 * 
	 * @throws IOException 
	 * 
	 */
	@Test
	public void getAdditionData_005() throws IOException {
		List result;
		List datatest = (List)TestUTCommon.getObjectFromFile("tmp\\datatest\\RealtimeDataLib\\getAdditionData_005.txt");
		result = (List) instanceTest.getAdditionData(datatest, "");
		
		assertTrue(TestUTCommon.compare2object(new ArrayList(), result));
	}

	/**
	 * Data is an empty List
	 * 
	 * Expected: 
	 * AssertionError with message "Collection is null or empty!"
	 * 
	 */
	@Test(expected=AssertionError.class)
	public void getAdditionData_006() {
		try{
			List result;
			List datatest = new ArrayList();
			result = (List) instanceTest.getAdditionData(datatest, "");
		}catch(AssertionError ae){
			assertTrue(ae.getMessage().contains("Collection is null or empty!"));
			throw ae;
		}
	}

	/**
	 * Data is null
	 * 
	 * Expected: 
	 * AssertionError with message "Collection is null or empty!"
	 * 
	 */
	@Test(expected=AssertionError.class)
	public void getAdditionData_007() {
		try{
			List result;
			result = (List) instanceTest.getAdditionData(null, "");
		}catch(AssertionError ae){
			assertTrue(ae.getMessage().contains("Collection is null or empty!"));
			throw ae;
		}
	}

	/**
	 * Data with chart declared but no unit
	 * 
	 * Expected:
	 * Normal data as case 004 but DetailData doesn't have unit
	 * @throws IOException 
	 */
	@Test
	public void getAdditionData_008() throws IOException {
		List result;
		List datatest = (List)TestUTCommon.getObjectFromFile("tmp\\datatest\\RealtimeDataLib\\getAdditionData_008.txt");
		result = (List) instanceTest.getAdditionData(datatest, "");

		List expected = (List)TestUTCommon.getObjectFromFile("tmp\\datatest\\RealtimeDataLib\\getAdditionData_008.txt_expected");
		
		for (Map map : (List<Map>)result) {
			// Check type could be 3 options line_, bar, area
			Object type = map.get("type");
			if(!"line_".equals(type) && !"bar".equals(type) && !"area".equals(type)){
				fail();
			}
		}
		assertTrue(TestUTCommon.compare2object(expected, result));
	}

	/**
	 * Data with chart declared but no hintColumns
	 * Expected: 
	 * Normal data like case 004 but DetailData contains only chartColumns and fetchAt
	 * (not include hintColumns)
	 * 
	 * Detail in 
	 * tmp\\datatest\\RealtimeDataLib\\getAdditionData_009.txt_expected
	 * @throws IOException 
	 * 
	 */
	@Test
	public void getAdditionData_009() throws IOException {
		List result;
		List datatest = (List)TestUTCommon.getObjectFromFile("tmp\\datatest\\RealtimeDataLib\\getAdditionData_009.txt");
		result = (List) instanceTest.getAdditionData(datatest, "");
		
		List expected = (List)TestUTCommon.getObjectFromFile("tmp\\datatest\\RealtimeDataLib\\getAdditionData_009.txt_expected");
		
		for (Map map : (List<Map>)result) {
			// Check type could be 3 options line_, bar, area
			Object type = map.get("type");
			if(!"line_".equals(type) && !"bar".equals(type) && !"area".equals(type)){
				fail();
			}
		}
		
		assertTrue(TestUTCommon.compare2object(expected, result));
	}

	/**
	 * Data with no key_root -> data has only 1 record for no identifier
	 * Expected:
	 * Check seriesName for changes from other cases
	 * Detail in 
	 * tmp\\datatest\\RealtimeDataLib\\getAdditionData_010.txt_expected
	 * 
	 * @throws IOException 
	 */
	@Test
	public void getAdditionData_010() throws IOException {
		List result;
		List datatest = (List)TestUTCommon.getObjectFromFile("tmp\\datatest\\RealtimeDataLib\\getAdditionData_010.txt");
		result = (List) instanceTest.getAdditionData(datatest, "");
		
		List expected = (List)TestUTCommon.getObjectFromFile("tmp\\datatest\\RealtimeDataLib\\getAdditionData_010.txt_expected");
		
		for (Map map : (List<Map>)result) {
			// Check type could be 3 options line_, bar, area
			Object type = map.get("type");
			if(!"line_".equals(type) && !"bar".equals(type) && !"area".equals(type)){
				fail();
			}
		}
		
		assertTrue(TestUTCommon.compare2object(expected, result));
	}

	/**
	 * Test mix with data contains: 
	 * - 2 records, each record present 3 database
	 * - 2 line charts
	 * - 2 bar charts (each chart has 2 chartColumns) // It matters in result 
	 * - 2 area charts
	 * - 2 pie charts (Won't return any record with pie type)
	 * 
	 * Expected:
	 * Normal data like case 004 but check the number of record in result
	 * {type=line_} : 2 charts * 2 records = 4 
	 * {type=bar} : (2 chartCol(of chart1) * 2 records) + (2 chartCol(of chart2) * 2 records) = 8
	 * {type=area} : 2 charts * 3 databases * 2 records = 12
	 * Further check in detail 
	 * 
	 * @throws IOException
	 */
	@Test
	public void getAdditionData_011() throws IOException{
		List result;
		List datatest = (List)TestUTCommon.getObjectFromFile("tmp\\datatest\\RealtimeDataLib\\getAdditionData_011.txt");
		result = (List) instanceTest.getAdditionData(datatest, "");
		
		int lineCount = 0;
		int barCount = 0;
		int areaCount = 0;
		for (Map map : (List<Map>)result) {
			if(map.get("type") == "line_"){
				lineCount++;
			}
			if(map.get("type") == "bar"){
				barCount++;
			}
			if(map.get("type") == "area"){
				areaCount++;
			}
		}
		// Confirm result only contains line_, bar, area
		assertEquals(result.size(), lineCount + barCount + areaCount);
		
		// Confirm count of each chart's type
		assertEquals(4, lineCount);
		assertEquals(8, barCount);
		assertEquals(12, areaCount);

		// Confirm in detail
		List expected = (List)TestUTCommon.getObjectFromFile("tmp\\datatest\\RealtimeDataLib\\getAdditionData_011.txt_expected");
		
		assertTrue(TestUTCommon.compare2object(expected, result));
	}
}
