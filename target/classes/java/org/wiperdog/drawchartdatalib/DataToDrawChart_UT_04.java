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
import org.wiperdog.custommongodbconnection.CMongoDBConn;
import org.wiperdog.lib.TestUTCommon;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;

/**
 * Test method getDataToDrawArea
 */
public class DataToDrawChart_UT_04 {
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
		collection = "SQL_Server.Database_Statistic.TestJobAreaStoreUnit";
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
	public void getDataToDrawAreaTest01() throws IOException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawArea\\getDataToDrawArea_collection_001.txt");
			// get data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawArea\\getDataToDrawArea_tmpOutput_001.txt");
			// get data of function getDataToDrawBar
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawArea(dataCollection));
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
	public void getDataToDrawAreaTest02() throws IOException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawArea\\getDataToDrawArea_collection_002.txt");
			// get data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawArea\\getDataToDrawArea_tmpOutput_002.txt");
			// get data of function getDataToDrawBar
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawArea(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Check output with monitoring two time, has _root, 
	 * the first time monitoring has two data A, B and the second time has three data B, C, D. 
	 * Expected: value of series.data ​​of A contain null corresponding to the second time monitoring.
	 * value of series.data ​​of C, D contain null corresponding to the first time monitoring.
	 * @throws IOException
	 */
	@Test
	public void getDataToDrawAreaTest03() throws IOException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawArea\\getDataToDrawArea_collection_026.txt");
			// get data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawArea\\getDataToDrawArea_tmpOutput_008.txt");
			// get data of function getDataToDrawBar
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawArea(dataCollection));
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
	public void getDataToDrawAreaTest04() {
		// set data output need to compare
		tmpOutput = "[]";
		// get data of function getDataToDrawArea
		tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawArea(dataCollection));
		assertEquals(tmpOutput, tmpResultActual);
	}
	
	/**
	 * Check output with value of collection is null.
	 * Expected: return list data is empty
	 */
	@Test
	public void getDataToDrawAreaTest05() {
		// set data of collection is null  
		dataCollection = null;
		// set data output need to compare
		tmpOutput = "[]";
		// get data of function getDataToDrawArea
		tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawArea(dataCollection));
		assertEquals(tmpOutput, tmpResultActual);
	}
	
	/**
	 * Check output with value of collection.data is empty.
	 * Expected: return list data contains list empty 
	 * @throws FileNotFoundException
	 */
	@Test
	public void getDataToDrawAreaTest06() throws FileNotFoundException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawArea\\getDataToDrawArea_collection_025.txt");
			// set data output need to compare
			tmpOutput = "[[],[]]";
			// get data of function getDataToDrawBar
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawArea(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Check output with value of collection not contains KEYEXPR.
	 * Expected: return list data is empty
	 * @throws FileNotFoundException 
	 */
	@Test
	public void getDataToDrawAreaTest07() throws FileNotFoundException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawArea\\getDataToDrawArea_collection_009.txt");
			// set data output need to compare
			tmpOutput = "[]";
			// get data of function getDataToDrawArea
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawArea(dataCollection));
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
	public void getDataToDrawAreaTest08() throws FileNotFoundException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawArea\\getDataToDrawArea_collection_010.txt");
			// set data output need to compare
			tmpOutput = "[]";
			// get data of function getDataToDrawArea
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawArea(dataCollection));
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
	public void getDataToDrawAreaTest09() throws FileNotFoundException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawArea\\getDataToDrawArea_collection_011.txt");
			// set data output need to compare
			tmpOutput = "[]";
			// get data of function getDataToDrawArea
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawArea(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
	}	
	
	/**
	 * Check output with value of KEYEXPR does not contains _root.
	 * Expected: value of chart_name contain only value of name in _chart. 
	 * Value of series.data correspond to the first record.
	 * @throws IOException 
	 */
	@Test
	public void getDataToDrawAreaTest10() throws IOException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawArea\\getDataToDrawArea_collection_023.txt");
			// set data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawArea\\getDataToDrawArea_tmpOutput_006.txt");
			// get data of function getDataToDrawArea
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawArea(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Check output with value of _root is list empty.
	 * Expected: value of chart_name contain only value of name in _chart. 
	 * Value of series.data correspond to the first record.
	 * @throws IOException 
	 */
	@Test
	public void getDataToDrawAreaTest11() throws IOException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawArea\\getDataToDrawArea_collection_021.txt");
			// set data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawArea\\getDataToDrawArea_tmpOutput_006.txt");
			// get data of function getDataToDrawArea
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawArea(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}	
	
	/**
	 * Check output with value of _root is null.
	 * Expected: value of chart_name contain only value of name in _chart. 
	 * Value of series.data correspond to the first record.
	 * @throws IOException 
	 */
	@Test
	public void getDataToDrawAreaTest12() throws IOException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawArea\\getDataToDrawArea_collection_022.txt");
			// set data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawArea\\getDataToDrawArea_tmpOutput_006.txt");
			// get data of function getDataToDrawArea
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawArea(dataCollection));
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
	public void getDataToDrawAreaTest13() throws IOException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawArea\\getDataToDrawArea_collection_024.txt");
			// set data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawArea\\getDataToDrawArea_tmpOutput_007.txt");
			// get data of function getDataToDrawArea
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawArea(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}	
	
	/**
	 * Check output with variable in _root of collection.data contain the character '.'
	 * Expected: value of chartItemName not replace character '.' with the data of _root
	 * @throws IOException
	 */
	@Test
	public void getDataToDrawAreaTest14() throws IOException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawArea\\getDataToDrawArea_collection_031.txt");
			// get data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawArea\\getDataToDrawArea_tmpOutput_011.txt");
			// get data of function getDataToDrawBar
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawArea(dataCollection));
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
	public void getDataToDrawAreaTest15() throws IOException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawArea\\getDataToDrawArea_collection_006.txt");
			// get data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawArea\\getDataToDrawArea_tmpOutput_003.txt");
			// get data of function getDataToDrawArea
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawArea(dataCollection));
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
	public void getDataToDrawAreaTest16() throws IOException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawArea\\getDataToDrawArea_collection_007.txt");
			// get data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawArea\\getDataToDrawArea_tmpOutput_003.txt");
			// get data of function getDataToDrawArea
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawArea(dataCollection));
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
	public void getDataToDrawAreaTest17() throws IOException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawArea\\getDataToDrawArea_collection_008.txt");
			// set data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawArea\\getDataToDrawArea_tmpOutput_003.txt");
			// get data of function getDataToDrawArea
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawArea(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}	
	
	/**
	 * Check output with value of KEYEXPR not contains _chart.
	 * Expected: return list data is empty
	 * @throws FileNotFoundException 
	 */
	@Test
	public void getDataToDrawAreaTest18() throws FileNotFoundException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawArea\\getDataToDrawArea_collection_003.txt");
			// set data output need to compare
			tmpOutput = "[]";
			// get data of function getDataToDrawArea
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawArea(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (FileNotFoundException e) {
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
	public void getDataToDrawAreaTest19() throws FileNotFoundException {
		try {	
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawArea\\getDataToDrawArea_collection_004.txt");
			// set data output need to compare
			tmpOutput = "[]";
			// get data of function getDataToDrawArea
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawArea(dataCollection));
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
	public void getDataToDrawAreaTest20() throws FileNotFoundException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawArea\\getDataToDrawArea_collection_005.txt");
			// set data output need to compare
			tmpOutput = "[]";
			// get data of function getDataToDrawArea
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawArea(dataCollection));
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
	public void getDataToDrawAreaTest21() throws FileNotFoundException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawArea\\getDataToDrawArea_collection_012.txt");
			// set data output need to compare
			tmpOutput = "[]";
			// get data of function getDataToDrawArea
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawArea(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Check output with value of type in KEYEXPR._chart is not "area".
	 * Expected: return list data is empty
	 * @throws FileNotFoundException 
	 */
	@Test
	public void getDataToDrawAreaTest22() throws FileNotFoundException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawArea\\getDataToDrawArea_collection_013.txt");
			// set data output need to compare
			tmpOutput = "[]";
			// get data of function getDataToDrawArea
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawArea(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Check output with value of type in KEYEXPR._chart is area and other chart.
	 * Expected: return list data contains only data corresponding to area chart
	 * @throws IOException 
	 */
	@Test
	public void getDataToDrawAreaTest23() throws IOException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawArea\\getDataToDrawArea_collection_014.txt");
			// get data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawArea\\getDataToDrawArea_tmpOutput_004.txt");
			// get data of function getDataToDrawArea
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawArea(dataCollection));
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
	public void getDataToDrawAreaTest24() throws FileNotFoundException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawArea\\getDataToDrawArea_collection_015.txt");
			// set data output need to compare
			tmpOutput = "[]";
			// get data of function getDataToDrawArea
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawArea(dataCollection));
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
	public void getDataToDrawAreaTest25() throws FileNotFoundException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawArea\\getDataToDrawArea_collection_016.txt");
			// set data output need to compare
			tmpOutput = "[]";
			// get data of function getDataToDrawArea
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawArea(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
	}	

	/**
	 * Check output with value of name in KEYEXPR._chart is empty.
	 * Expected: value of chart_name contains only value of _root
	 * @throws IOException 
	 */
	@Test
	public void getDataToDrawAreaTest26() throws IOException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawArea\\getDataToDrawArea_collection_028.txt");
			// set data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawArea\\getDataToDrawArea_tmpOutput_010.txt");
			// get data of function getDataToDrawArea
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawArea(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Check output with value of name in KEYEXPR._chart is null.
	 * Expected: value of chart_name contains only value of _root
	 * @throws IOException 
	 */
	@Test
	public void getDataToDrawAreaTest27() throws IOException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawArea\\getDataToDrawArea_collection_029.txt");
			// set data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawArea\\getDataToDrawArea_tmpOutput_010.txt");
			// get data of function getDataToDrawArea
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawArea(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Check output with value of KEYEXPR._chart does not contains name.
	 * Expected: value of chart_name contains only value of _root
	 * @throws IOException 
	 */
	@Test
	public void getDataToDrawAreaTest28() throws IOException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawArea\\getDataToDrawArea_collection_030.txt");
			// set data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawArea\\getDataToDrawArea_tmpOutput_010.txt");
			// get data of function getDataToDrawArea
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawArea(dataCollection));
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
	public void getDataToDrawAreaTest29() throws IOException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawArea\\getDataToDrawArea_collection_017.txt");
			// set data output need to compare
			tmpOutput = "[]";
			// get data of function getDataToDrawArea
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawArea(dataCollection));
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
	public void getDataToDrawAreaTest30() throws FileNotFoundException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawArea\\getDataToDrawArea_collection_018.txt");
			// set data output need to compare
			tmpOutput = "[]";
			// get data of function getDataToDrawArea
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawArea(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Check output with value of KEYEXPR._chart not contains chart_columns.
	 * Expected: return data is list empty
	 * @throws FileNotFoundException 
	 */
	@Test
	public void getDataToDrawAreaTest31() throws FileNotFoundException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawArea\\getDataToDrawArea_collection_019.txt");
			// set data output need to compare
			tmpOutput = "[]";
			// get data of function getDataToDrawArea
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawArea(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Check output with value of chart_columns in KEYEXPR._chart contains variable does not exist in data draw chart.
	 * Expected: value of series.data to draw point is list contains null
	 * @throws IOException 
	 */
	@Test
	public void getDataToDrawAreaTest32() throws IOException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawArea\\getDataToDrawArea_collection_020.txt");
			// set data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawArea\\getDataToDrawArea_tmpOutput_005.txt");
			// get data of function getDataToDrawArea
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawArea(dataCollection));
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
