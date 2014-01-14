package org.wiperdog.drawchartdatalib;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Test method getDataToDrawLine
 */
public class DataToDrawChart_UT_02 {
	DataToDrawChart chart;
	String collection;
	String istIid;
	BufferedReader br;
	String line;
	String tmpResultActual;
	String tmpOutput;
	ArrayList<Map> dataCollection;
	
	@Before
	public void setUp() throws Exception {
		chart = new DataToDrawChart();
		// set collection name for insert mongodb
		collection = "SQL_Server.Database_Statistic.TestJobLineStoreUnit";
		// set istIid name for insert mongodb
		istIid = "localhost-@MSSQL-master";
		// create data of collection, input for test 
		dataCollection = new ArrayList<Map>();
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Check output with value of collection is correctly and data size of collections is 1.
	 * Exist parameter KEYEXPR with: _root contains more than 1 variable, _unit has data, 
	 * _chart contains two bar chart with parameter is type, name, chart_columns, hint_columns not empty.
	 * value of chart_columns more than 1 variable, value of hint_columns contains 1 and more than 1 variable. 
	 * Expected: returns data contains all variable is not empty 
	 * @throws IOException
	 */
	@Test
	public void getDataToDrawLineTest01() throws IOException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawLine\\getDataToDrawLine_collection_001.txt");
			// get data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawLine\\getDataToDrawLine_tmpOutput_001.txt");
			// get data of function getDataToDrawBar
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawLine(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Check output with value of collection is correctly and data size of collections more than 1.
	 * Exist parameter KEYEXPR with: _root contains more than 1 variable, _unit has data, 
	 * _chart contains two bar chart with parameter is type, name, chart_columns, hint_columns not empty.
	 * value of chart_columns more than 1 variable, value of hint_columns contains 1 and more than 1 variable. 
	 * Expected: returns data contains all variable is not empty 
	 * @throws IOException
	 */
	@Test
	public void getDataToDrawLineTest02() throws IOException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawLine\\getDataToDrawLine_collection_002.txt");
			// get data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawLine\\getDataToDrawLine_tmpOutput_002.txt");
			// get data of function getDataToDrawBar
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawLine(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Check output with value of collection contains a collection.data is empty and a collection.data is not empty. 
	 * Expected: value of series.data contain null, correspond to fetchAt of the collection.data is empty 
	 * @throws IOException
	 */
	@Test
	public void getDataToDrawLineTest03() throws IOException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawLine\\getDataToDrawLine_collection_013.txt");
			// get data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawLine\\getDataToDrawLine_tmpOutput_005.txt");
			// get data of function getDataToDrawBar
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawLine(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}	
	
	/**
	 * Check output with value of collection is empty.
	 * Expected: return list data is empty
	 */
	@Test
	public void getDataToDrawLineTest04(){
		// set data output need to compare
		tmpOutput = "[]";
		// get data of function getDataToDrawLine
		tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawLine(dataCollection));
		assertEquals(tmpOutput, tmpResultActual);
	}
	
	/**
	 * Check output with value of collection is null.
	 * Expected: return list data is empty
	 */
	@Test
	public void getDataToDrawLineTest05(){
		// set value of collection is null 
		dataCollection = null;
		// set data output need to compare
		tmpOutput = "[]";
		// get data of function getDataToDrawLine
		tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawLine(dataCollection));
		assertEquals(tmpOutput, tmpResultActual);
	}
	
	/**
	 * Check output with value of collection.data is empty.
	 * Expected: value of series and detail_data is list empty
	 * @throws IOException
	 */
	@Test
	public void getDataToDrawLineTest06() throws IOException {
		try {
			// set data of collection is null  
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawLine\\getDataToDrawLine_collection_012.txt");
			// get data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawLine\\getDataToDrawLine_tmpOutput_004.txt");
			// get data of function getDataToDrawLine
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawLine(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Check output with value of collection does not contains KEYEXPR.
	 * Expected: return list data is empty
	 * @throws FileNotFoundException 
	 */
	@Test
	public void getDataToDrawLineTest07() throws FileNotFoundException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawLine\\getDataToDrawLine_collection_009.txt");
			// set data output need to compare
			tmpOutput = "[]";
			// get data of function getDataToDrawLine
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawLine(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
	}	

	/**
	 * Check output with value of KEYEXPR is map empty.
	 * Expected: return list data is empty
	 * @throws FileNotFoundException 
	 */
	@Test
	public void getDataToDrawLineTest08() throws FileNotFoundException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawLine\\getDataToDrawLine_collection_010.txt");
			// set data output need to compare
			tmpOutput = "[]";
			// get data of function getDataToDrawLine
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawLine(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Check output with value of KEYEXPR is null.
	 * Expected: return list data is empty
	 * @throws FileNotFoundException 
	 */
	@Test
	public void getDataToDrawLineTest09() throws FileNotFoundException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawLine\\getDataToDrawLine_collection_011.txt");
			// set data output need to compare
			tmpOutput = "[]";
			// get data of function getDataToDrawLine
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawLine(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
	}	
	
	/**
	 * Check output with value of _root is list empty.
	 * Expected: value of series.name correspond to value of chart_columns
	 * @throws IOException 
	 */
	@Test
	public void getDataToDrawLineTest10() throws IOException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawLine\\getDataToDrawLine_collection_015.txt");
			// set data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawLine\\getDataToDrawLine_tmpOutput_006.txt");
			// get data of function getDataToDrawLine
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawLine(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}	
	
	/**
	 * Check output with value of _root is null.
	 * Expected: value of series.name correspond to value of chart_columns
	 * @throws IOException 
	 */
	@Test
	public void getDataToDrawLineTest11() throws IOException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawLine\\getDataToDrawLine_collection_016.txt");
			// set data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawLine\\getDataToDrawLine_tmpOutput_006.txt");
			// get data of function getDataToDrawLine
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawLine(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}	
	
	/**
	 * Check output with value of KEYEXPR does not contains _root.
	 * Expected: value of series.name correspond to value of chart_columns
	 * @throws IOException 
	 */
	@Test
	public void getDataToDrawLineTest12() throws IOException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawLine\\getDataToDrawLine_collection_017.txt");
			// set data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawLine\\getDataToDrawLine_tmpOutput_006.txt");
			// get data of function getDataToDrawLine
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawLine(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}	
	
	/**
	 * Check output with value of _root contains only one variable.
	 * Expected: value of series.name contain only data of one variable corresponding to the root
	 * @throws IOException 
	 */
	@Test
	public void getDataToDrawLineTest13() throws IOException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawLine\\getDataToDrawLine_collection_018.txt");
			// set data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawLine\\getDataToDrawLine_tmpOutput_007.txt");
			// get data of function getDataToDrawLine
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawLine(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}	
	
	/**
	 * Check output with value of _unit is map empty.
	 * Expected: return data does not contains unit
	 * @throws IOException 
	 */
	@Test
	public void getDataToDrawLineTest14() throws IOException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawLine\\getDataToDrawLine_collection_007.txt");
			// get data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawLine\\getDataToDrawLine_tmpOutput_003.txt");
			// get data of function getDataToDrawLine
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawLine(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}	
	
	/**
	 * Check output with value of _unit is null.
	 * Expected: return data does not contains unit
	 * @throws IOException 
	 */
	@Test
	public void getDataToDrawLineTest15() throws IOException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawLine\\getDataToDrawLine_collection_008.txt");
			// set data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawLine\\getDataToDrawLine_tmpOutput_003.txt");
			// get data of function getDataToDrawLine
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawLine(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}			

	/**
	 * Check output with value of KEYEXPR does not contains _unit.
	 * Expected: return data does not contains unit
	 * @throws IOException 
	 */
	@Test
	public void getDataToDrawLineTest16() throws IOException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawLine\\getDataToDrawLine_collection_006.txt");
			// get data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawLine\\getDataToDrawLine_tmpOutput_003.txt");
			// get data of function getDataToDrawLine
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawLine(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}	

	/**
	 * Check output with value of _chart is list empty.
	 * Expected: return list data is empty
	 * @throws FileNotFoundException 
	 */
	@Test
	public void getDataToDrawLineTest17() throws FileNotFoundException {
		try {	
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawLine\\getDataToDrawLine_collection_004.txt");
			// set data output need to compare
			tmpOutput = "[]";
			// get data of function getDataToDrawLine
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawLine(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
	}	
	
	/**
	 * Check output with value of _chart is null.
	 * Expected: return list data is empty
	 * @throws FileNotFoundException 
	 */
	@Test
	public void getDataToDrawLineTest18() throws FileNotFoundException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawLine\\getDataToDrawLine_collection_005.txt");
			// set data output need to compare
			tmpOutput = "[]";
			// get data of function getDataToDrawLine
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawLine(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Check output with value of collection does not contains KEYEXPR._chart.
	 * Expected: return list data is empty
	 * @throws FileNotFoundException 
	 */
	@Test
	public void getDataToDrawLineTest19() throws FileNotFoundException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawLine\\getDataToDrawLine_collection_003.txt");
			// set data output need to compare
			tmpOutput = "[]";
			// get data of function getDataToDrawLine
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawLine(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Check output with value of type in KEYEXPR._chart is empty.
	 * Expected: return list data is empty
	 * @throws FileNotFoundException 
	 */
	@Test
	public void getDataToDrawLineTest20() throws FileNotFoundException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawLine\\getDataToDrawLine_collection_019.txt");
			// set data output need to compare
			tmpOutput = "[]";
			// get data of function getDataToDrawLine
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawLine(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Check output with value of type in KEYEXPR._chart is not "line".
	 * Expected: return list data is empty
	 * @throws FileNotFoundException 
	 */
	@Test
	public void getDataToDrawLineTest21() throws FileNotFoundException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawLine\\getDataToDrawLine_collection_020.txt");
			// set data output need to compare
			tmpOutput = "[]";
			// get data of function getDataToDrawLine
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawLine(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Check output with value of KEYEXPR._chart contains type of chart is line and other chart.
	 * Expected: return list data contains only data corresponding to line chart
	 * @throws IOException 
	 */
	@Test
	public void getDataToDrawLineTest22() throws IOException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawLine\\getDataToDrawLine_collection_021.txt");
			// get data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawLine\\getDataToDrawLine_tmpOutput_008.txt");
			// get data of function getDataToDrawLine
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawLine(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Check output with value of type in KEYEXPR._chart is null.
	 * Expected: return list data is empty
	 * @throws FileNotFoundException 
	 */
	@Test
	public void getDataToDrawLineTest23() throws FileNotFoundException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawLine\\getDataToDrawLine_collection_022.txt");
			// set data output need to compare
			tmpOutput = "[]";
			// get data of function getDataToDrawLine
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawLine(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Check output with KEYEXPR._chart does not contains type.
	 * Expected: return list data is empty
	 * @throws FileNotFoundException 
	 */
	@Test
	public void getDataToDrawLineTest24() throws FileNotFoundException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawLine\\getDataToDrawLine_collection_023.txt");
			// set data output need to compare
			tmpOutput = "[]";
			// get data of function getDataToDrawLine
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawLine(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
	}	

	/**
	 * Check output with value of name in KEYEXPR._chart is empty.
	 * Expected: value of title is empty
	 * @throws IOException 
	 */
	@Test
	public void getDataToDrawLineTest25() throws IOException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawLine\\getDataToDrawLine_collection_032.txt");
			// set data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawLine\\getDataToDrawLine_tmpOutput_012.txt");
			// get data of function getDataToDrawLine
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawLine(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Check output with value of name in KEYEXPR._chart is null.
	 * Expected: value of title is empty
	 * @throws IOException 
	 */
	@Test
	public void getDataToDrawLineTest26() throws IOException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawLine\\getDataToDrawLine_collection_033.txt");
			// set data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawLine\\getDataToDrawLine_tmpOutput_013.txt");
			// get data of function getDataToDrawLine
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawLine(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Check output with KEYEXPR._chart does not contains name.
	 * Expected: value of chart_name is null
	 * @throws IOException 
	 */
	@Test
	public void getDataToDrawLineTest27() throws IOException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawLine\\getDataToDrawLine_collection_034.txt");
			// set data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawLine\\getDataToDrawLine_tmpOutput_013.txt");
			// get data of function getDataToDrawLine
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawLine(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}	
	
	/**
	 * Check output with value of chart_columns in KEYEXPR._chart is list empty.
	 * Expected: return data is list empty
	 * @throws IOException 
	 */
	@Test
	public void getDataToDrawLineTest28() throws IOException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawLine\\getDataToDrawLine_collection_024.txt");
			// set data output need to compare
			tmpOutput = "[]";
			// get data of function getDataToDrawLine
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawLine(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Check output with value of chart_columns in KEYEXPR._chart is null.
	 * Expected: return data is list empty
	 * @throws FileNotFoundException 
	 */
	@Test
	public void getDataToDrawLineTest29() throws FileNotFoundException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawLine\\getDataToDrawLine_collection_025.txt");
			// set data output need to compare
			tmpOutput = "[]";
			// get data of function getDataToDrawLine
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawLine(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Check output with value of KEYEXPR._chart does not contains chart_columns.
	 * Expected: return data is list empty
	 * @throws FileNotFoundException 
	 */
	@Test
	public void getDataToDrawLineTest30() throws FileNotFoundException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawLine\\getDataToDrawLine_collection_026.txt");
			// set data output need to compare
			tmpOutput = "[]";
			// get data of function getDataToDrawLine
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawLine(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Check output with value of chart_columns contains variable does not exist in data draw chart.
	 * Expected: data of series to draw point is null
	 * @throws IOException 
	 */
	@Test
	public void getDataToDrawLineTest31() throws IOException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawLine\\getDataToDrawLine_collection_027.txt");
			// set data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawLine\\getDataToDrawLine_tmpOutput_009.txt");
			// get data of function getDataToDrawLine
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawLine(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Check output with value of hint_columns in KEYEXPR._chart is list empty.
	 * Expected: return list data contains value of hint_columns contains only value of chart_columns
	 * @throws IOException 
	 */
	@Test
	public void getDataToDrawLineTest32() throws IOException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawLine\\getDataToDrawLine_collection_028.txt");
			// set data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawLine\\getDataToDrawLine_tmpOutput_010.txt");
			// get data of function getDataToDrawLine
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawLine(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Check output with value of hint_columns in KEYEXPR._chart is null.
	 * Expected: return list data contains value of hint_columns contains only value of chart_columns
	 * @throws IOException 
	 */
	@Test
	public void getDataToDrawLineTest33() throws IOException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawLine\\getDataToDrawLine_collection_029.txt");
			// set data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawLine\\getDataToDrawLine_tmpOutput_010.txt");
			// get data of function getDataToDrawLine
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawLine(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Check output with value of KEYEXPR._chart does not contains hint_columns.
	 * Expected: return list data contains value of hint_columns contains only value of chart_columns
	 * @throws IOException 
	 */
	@Test
	public void getDataToDrawLineTest34() throws IOException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawLine\\getDataToDrawLine_collection_030.txt");
			// set data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawLine\\getDataToDrawLine_tmpOutput_010.txt");
			// get data of function getDataToDrawLine
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawLine(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Check output with value of hint_columns contains variable not exist in data draw chart.
	 * Expected: data of detail_data and hint_columns does not contains the data of the variable does not exist
	 * @throws IOException 
	 */
	@Test
	public void getDataToDrawLineTest35() throws IOException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawLine\\getDataToDrawLine_collection_031.txt");
			// set data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawLine\\getDataToDrawLine_tmpOutput_011.txt");
			// get data of function getDataToDrawLine
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawLine(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}	
	
	/**
	 * @param filePath
	 *            path to file contains data output for test
	 * @return data output need to compare
	 * @throws IOException 
	 */
	public String readFileOutput(String filePath) throws IOException{
		// get data output need to compare, read from file
		try {
			String output = "";
			br = new BufferedReader(new FileReader(filePath));
			while ((line = br.readLine()) != null) {
				if (output != "") {
					output += "\n" + line;
				} else {
					output += line;
				}
			}
			return output;
		} catch (IOException ex) {
			ex.printStackTrace();
			throw ex;
		}
	}
	
	/**
	 * @param filePath
	 *            path to file contains data input for test
	 * @return data input for test
	 * @throws FileNotFoundException
	 */
	public ArrayList<Map> readFileInput(String filePath) throws FileNotFoundException{
		try {
			FileReader reader = new FileReader(new File(filePath));
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			ArrayList<Map> expectedList = gson.fromJson(reader, ArrayList.class);
			return expectedList;
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
			throw ex;
		}
	}
}
