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
 * Test method getDataToDrawPie
 */
public class DataToDrawChart_UT_03 {
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
		collection = "SQL_Server.Database_Statistic.TestJobPieStoreUnit";
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
	public void getDataToDrawPieTest01() throws IOException {
		try {
			// get value of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawPie\\getDataToDrawPie_collection_001.txt");
			// get data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawPie\\getDataToDrawPie_tmpOutput_001.txt");
			// get data of function getDataToDrawBar
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawPie(dataCollection));
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
	 * value of chart_columns and hint_columns contains 1 and more than 1 variable.   
	 * Expected: returns data contains all variable is not empty, value of data correspond to the last record in mongodb
	 * @throws IOException
	 */
	@Test
	public void getDataToDrawPieTest02() throws IOException {
		try {
			// get value of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawPie\\getDataToDrawPie_collection_002.txt");
			// get data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawPie\\getDataToDrawPie_tmpOutput_002.txt");
			// get data of function getDataToDrawBar
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawPie(dataCollection));
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
	public void getDataToDrawPieTest03(){
		// set data output need to compare
		tmpOutput = "[]";
		// get data of function getDataToDrawPie
		tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawPie(dataCollection));
		assertEquals(tmpOutput, tmpResultActual);
	}
	
	/**
	 * Check output with value of collection is null.
	 * Expected: return list data is empty
	 */
	@Test
	public void getDataToDrawPieTest04() {

		// set value of collection is null  
		dataCollection = null;
		// set data output need to compare
		tmpOutput = "[]";
		// get data of function getDataToDrawPie
		tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawPie(dataCollection));
		assertEquals(tmpOutput, tmpResultActual);
	}
	
	/**
	 * Check output with value of collection.data is empty.
	 * Expected: return list data contains list empty
	 * @throws FileNotFoundException
	 */
	@Test
	public void getDataToDrawPieTest05() throws FileNotFoundException {
		try {
			// set value of collection.data is null  
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawPie\\getDataToDrawPie_collection_028.txt");
			// get data output need to compare
			tmpOutput = "[[],[]]";
			// get data of function getDataToDrawPie
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawPie(dataCollection));
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
	public void getDataToDrawPieTest06() throws FileNotFoundException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawPie\\getDataToDrawPie_collection_009.txt");
			// set data output need to compare
			tmpOutput = "[]";
			// get data of function getDataToDrawPie
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawPie(dataCollection));
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
	public void getDataToDrawPieTest07() throws FileNotFoundException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawPie\\getDataToDrawPie_collection_010.txt");
			// set data output need to compare
			tmpOutput = "[]";
			// get data of function getDataToDrawPie
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawPie(dataCollection));
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
	public void getDataToDrawPieTest08() throws FileNotFoundException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawPie\\getDataToDrawPie_collection_011.txt");
			// set data output need to compare
			tmpOutput = "[]";
			// get data of function getDataToDrawPie
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawPie(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
	}		
	
	/**
	 * Check output with value of _root is list empty.
	 * Expected: value of the name is (chart_name+number), number=0,1....
	 * @throws IOException 
	 */
	@Test
	public void getDataToDrawPieTest09() throws IOException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawPie\\getDataToDrawPie_collection_024.txt");
			// set data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawPie\\getDataToDrawPie_tmpOutput_008.txt");
			// get data of function getDataToDrawPie
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawPie(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}	
	
	/**
	 * Check output with value of _root is null.
	 * Expected: value of the name is (chart_name+number), number=0,1....
	 * @throws IOException 
	 */
	@Test
	public void getDataToDrawPieTest10() throws IOException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawPie\\getDataToDrawPie_collection_025.txt");
			// set data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawPie\\getDataToDrawPie_tmpOutput_008.txt");
			// get data of function getDataToDrawPie
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawPie(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}	
	
	/**
	 * Check output with value of KEYEXPR does not contains _root.
	 * Expected: value of the name is (chart_name+number), number=0,1....
	 * @throws IOException 
	 */
	@Test
	public void getDataToDrawPieTest11() throws IOException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawPie\\getDataToDrawPie_collection_026.txt");
			// set data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawPie\\getDataToDrawPie_tmpOutput_008.txt");
			// get data of function getDataToDrawPie
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawPie(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}	
	
	/**
	 * Check output with value of _root contains only one variable.
	 * Expected: value of name contain only data of one variable corresponding to the _root
	 * @throws IOException 
	 */
	@Test
	public void getDataToDrawPieTest12() throws IOException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawPie\\getDataToDrawPie_collection_027.txt");
			// set data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawPie\\getDataToDrawPie_tmpOutput_009.txt");
			// get data of function getDataToDrawPie
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawPie(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}	

	/**
	 * Check output with value of KEYEXPR does not contains _unit.
	 * Expected: value of unit is empty
	 * @throws IOException 
	 */
	@Test
	public void getDataToDrawPieTest13() throws IOException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawPie\\getDataToDrawPie_collection_006.txt");
			// get data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawPie\\getDataToDrawPie_tmpOutput_003.txt");
			// get data of function getDataToDrawPie
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawPie(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Check output with value of _unit is map empty.
	 * Expected: value of unit is empty
	 * @throws IOException 
	 */
	@Test
	public void getDataToDrawPieTest14() throws IOException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawPie\\getDataToDrawPie_collection_007.txt");
			// get data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawPie\\getDataToDrawPie_tmpOutput_003.txt");
			// get data of function getDataToDrawPie
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawPie(dataCollection));
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
	public void getDataToDrawPieTest15() throws IOException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawPie\\getDataToDrawPie_collection_008.txt");
			// set data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawPie\\getDataToDrawPie_tmpOutput_003.txt");
			// get data of function getDataToDrawPie
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawPie(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}	

	/**
	 * Check output with value of collection not contains _chart.
	 * Expected: return list data is empty
	 * @throws FileNotFoundException 
	 */
	@Test
	public void getDataToDrawPieTest16() throws FileNotFoundException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawPie\\getDataToDrawPie_collection_003.txt");
			// set data output need to compare
			tmpOutput = "[]";
			// get data of function getDataToDrawPie
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawPie(dataCollection));
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
	public void getDataToDrawPieTest17() throws FileNotFoundException {
		try {	
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawPie\\getDataToDrawPie_collection_004.txt");
			// set data output need to compare
			tmpOutput = "[]";
			// get data of function getDataToDrawPie
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawPie(dataCollection));
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
	public void getDataToDrawPieTest18() throws FileNotFoundException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawPie\\getDataToDrawPie_collection_005.txt");
			// set data output need to compare
			tmpOutput = "[]";
			// get data of function getDataToDrawPie
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawPie(dataCollection));
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
	public void getDataToDrawPieTest19() throws FileNotFoundException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawPie\\getDataToDrawPie_collection_012.txt");
			// set data output need to compare
			tmpOutput = "[]";
			// get data of function getDataToDrawPie
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawPie(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Check output with value of type in KEYEXPR._chart is not "pie".
	 * Expected: return list data is empty
	 * @throws FileNotFoundException 
	 */
	@Test
	public void getDataToDrawPieTest20() throws FileNotFoundException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawPie\\getDataToDrawPie_collection_013.txt");
			// set data output need to compare
			tmpOutput = "[]";
			// get data of function getDataToDrawPie
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawPie(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Check output with value of type in KEYEXPR._chart is pie and other chart.
	 * Expected: return list data contains only data corresponding to pie chart
	 * @throws IOException 
	 */
	@Test
	public void getDataToDrawPieTest21() throws IOException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawPie\\getDataToDrawPie_collection_014.txt");
			// get data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawPie\\getDataToDrawPie_tmpOutput_004.txt");
			// get data of function getDataToDrawPie
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawPie(dataCollection));
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
	public void getDataToDrawPieTest22() throws FileNotFoundException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawPie\\getDataToDrawPie_collection_015.txt");
			// set data output need to compare
			tmpOutput = "[]";
			// get data of function getDataToDrawPie
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawPie(dataCollection));
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
	public void getDataToDrawPieTest23() throws FileNotFoundException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawPie\\getDataToDrawPie_collection_016.txt");
			// set data output need to compare
			tmpOutput = "[]";
			// get data of function getDataToDrawPie
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawPie(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
	}	

	/**
	 * Check output with value of name in KEYEXPR._chart is empty.
	 * Expected: value of chart_name is empty
	 * @throws IOException 
	 */
	@Test
	public void getDataToDrawPieTest24() throws IOException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawPie\\getDataToDrawPie_collection_017.txt");
			// set data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawPie\\getDataToDrawPie_tmpOutput_005.txt");
			// get data of function getDataToDrawPie
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawPie(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Check output with value of name in KEYEXPR._chart is null.
	 * Expected: value of chart_name is null
	 * @throws IOException 
	 */
	@Test
	public void getDataToDrawPieTest25() throws IOException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawPie\\getDataToDrawPie_collection_018.txt");
			// set data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawPie\\getDataToDrawPie_tmpOutput_006.txt");
			// get data of function getDataToDrawPie
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawPie(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Check output with value of KEYEXPR._chart does not contains name.
	 * Expected: value of chart_name is null
	 * @throws IOException 
	 */
	@Test
	public void getDataToDrawPieTest26() throws IOException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawPie\\getDataToDrawPie_collection_019.txt");
			// set data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawPie\\getDataToDrawPie_tmpOutput_006.txt");
			// get data of function getDataToDrawPie
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawPie(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}				

	/**
	 * Check output with value of chart_columns in KEYEXPR._chart is empty
	 * Expected: return data is list of charts with data = []
	 * @throws IOException 
	 */
	@Test
	public void getDataToDrawPieTest27() throws IOException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawPie\\getDataToDrawPie_collection_020.txt");
			// set data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawPie\\getDataToDrawPie_tmpOutput_010.txt");
			// get data of function getDataToDrawPie
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawPie(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Check output with value of chart_columns in KEYEXPR._chart is null.
	 * Expected: return data is list empty
	 * @throws IOException 
	 */
	@Test
	public void getDataToDrawPieTest28() throws IOException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawPie\\getDataToDrawPie_collection_021.txt");
			// set data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawPie\\getDataToDrawPie_tmpOutput_012.txt");
			// get data of function getDataToDrawPie
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawPie(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Check output with value of KEYEXPR._chart does not contains chart_columns.
	 * Expected: return data is list empty
	 * @throws IOException 
	 */
	@Test
	public void getDataToDrawPieTest29() throws IOException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawPie\\getDataToDrawPie_collection_022.txt");
			// set data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawPie\\getDataToDrawPie_tmpOutput_013.txt");
			// get data of function getDataToDrawPie
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawPie(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Check output with value of chart_columns in KEYEXPR._chart contains variable does not exist in data draw chart.
	 * Expected: data of variable in data is null and value of unit is empty
	 * @throws IOException 
	 */
	@Test
	public void getDataToDrawPieTest30() throws IOException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawPie\\getDataToDrawPie_collection_023.txt");
			// set data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawPie\\getDataToDrawPie_tmpOutput_007.txt");
			// get data of function getDataToDrawPie
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawPie(dataCollection));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}		
	
	/**
	 * Check output with value of chart_columns in KEYEXPR._chart is a list of charts
	 * chart1 : no chart_columns
	 * chart2 : has chart_columns
	 * Expected: return data is list of charts with 
	 *  - chart1 has data = []
	 *  - chart2 has normal data
	 * 
	 * @throws IOException 
	 */
	@Test
	public void getDataToDrawPieTest31() throws IOException {
		try {
			// get data of collection, input for test 
			dataCollection = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawPie\\getDataToDrawPie_collection_031.txt");
			// set data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawPie\\getDataToDrawPie_tmpOutput_011.txt");
			// get data of function getDataToDrawPie
			tmpResultActual = new Gson().toJson((ArrayList)chart.getDataToDrawPie(dataCollection));
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
