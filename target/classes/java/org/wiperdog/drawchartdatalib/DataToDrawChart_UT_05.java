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
 * Test method getDataToDrawSubtype
 */
public class DataToDrawChart_UT_05 {
	DataToDrawChart chart;
	String collection;
	String istIid;
	BufferedReader br;
	String line;
	String tmpResultActual;
	String tmpOutput;
	ArrayList<Map> dataSubtype;
	
	@Before
	public void setUp() throws Exception {
		chart = new DataToDrawChart();
		// set collection name for insert mongodb
		collection = "SQL_Server.Database_Statistic.TestDrawSubtype";
		// set istIid name for insert mongodb
		istIid = "localhost-@MSSQL-master";
		// create data of collection, input for test 
		dataSubtype = new ArrayList<Map>();
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Check output with value of collection is correctly and data of group D and M is not empty.
	 * Exist parameter KEYEXPR with: key root of each group contains 1 or more than 1 variable, value of _unit used for all group.
	 * value of _chart is a map grouped by key of each group.
	 * value of _chart contains two bar chart with parameter is type, name, chart_columns, hint_columns not empty.
	 * Expected: return data contains all variable of two group is not empty 
	 * @throws IOException
	 */
	@Test
	public void getDataToDrawSubtypeTest01() throws IOException {
		try {
			// get data of collection, input for test 
			dataSubtype = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawSubtype\\getDataToDrawSubtype_collection_001.txt");
			// get data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawSubtype\\getDataToDrawSubtype_tmpOutput_001.txt");
			// get data of function getDataToDrawBar
			tmpResultActual = new Gson().toJson((Map)chart.getDataToDrawSubtype(dataSubtype));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Check output with value of collection is correctly, data of group D is not empty and group M is empty.
	 * Exist parameter KEYEXPR with: key root of each group contains 1 or more than 1 variable, value of _unit used for all group.
	 * value of _chart is a map grouped by key of each group.
	 * value of _chart contains two bar chart with parameter is type, name, chart_columns, hint_columns not empty.
	 * Expected: data of group D is not empty and data of group M is empty
	 * @throws IOException
	 */
	@Test
	public void getDataToDrawSubtypeTest02() throws IOException {
		try {
			// get data of collection, input for test 
			dataSubtype = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawSubtype\\getDataToDrawSubtype_collection_002.txt");
			// get data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawSubtype\\getDataToDrawSubtype_tmpOutput_002.txt");
			// get data of function getDataToDrawBar
			tmpResultActual = new Gson().toJson((Map)chart.getDataToDrawSubtype(dataSubtype));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Check output with value of collection is correctly, data of group D is empty and group M is not empty.
	 * Exist parameter KEYEXPR with: key root of each group contains 1 or more than 1 variable, value of _unit used for all group.
	 * value of _chart is a map grouped by key of each group.
	 * value of _chart contains two bar chart with parameter is type, name, chart_columns, hint_columns not empty.
	 * Expected: data of group D is empty and data of group M is not empty
	 * @throws IOException
	 */
	@Test
	public void getDataToDrawSubtypeTest03() throws IOException {
		try {
			// get data of collection, input for test 
			dataSubtype = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawSubtype\\getDataToDrawSubtype_collection_003.txt");
			// get data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawSubtype\\getDataToDrawSubtype_tmpOutput_003.txt");
			// get data of function getDataToDrawBar
			tmpResultActual = new Gson().toJson((Map)chart.getDataToDrawSubtype(dataSubtype));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Check output with value of collection is empty.
	 * Expected: return data is map empty
	 * @throws IOException
	 */
	@Test
	public void getDataToDrawSubtypeTest04() {
		// get data output need to compare
		tmpOutput = "{}";
		// get data of function getDataToDrawBar
		tmpResultActual = new Gson().toJson((Map)chart.getDataToDrawSubtype(dataSubtype));
		assertEquals(tmpOutput, tmpResultActual);
	}
	
	/**
	 * Check output with value of collection is null.
	 * Expected: return data is map empty
	 */
	@Test
	public void getDataToDrawSubtypeTest05() {
		// get data of collection, input for test 
		dataSubtype = null;
		// get data output need to compare
		tmpOutput = "{}";
		// get data of function getDataToDrawBar
		tmpResultActual = new Gson().toJson((Map)chart.getDataToDrawSubtype(dataSubtype));
		assertEquals(tmpOutput, tmpResultActual);
	}
	
	/**
	 * Check output with value of collection.data contain only one group.
	 * Expected: returns data of one group
	 * @throws IOException
	 */
	@Test
	public void getDataToDrawSubtypeTest06() throws IOException {
		try {
			// get data of collection, input for test 
			dataSubtype = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawSubtype\\getDataToDrawSubtype_collection_004.txt");
			// get data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawSubtype\\getDataToDrawSubtype_tmpOutput_004.txt");
			// get data of function getDataToDrawBar
			tmpResultActual = new Gson().toJson((Map)chart.getDataToDrawSubtype(dataSubtype));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Check output with value of collection.data is empty.
	 * Expected: return data is map empty
	 * @throws IOException
	 */
	@Test
	public void getDataToDrawSubtypeTest07() throws IOException {
		try {
			// get data of collection, input for test 
			dataSubtype = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawSubtype\\getDataToDrawSubtype_collection_005.txt");
			// get data output need to compare
			tmpOutput = "{}";
			// get data of function getDataToDrawBar
			tmpResultActual = new Gson().toJson((Map)chart.getDataToDrawSubtype(dataSubtype));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Check output with value of collection.data is null.
	 * Expected: return data is map empty
	 * @throws IOException
	 */
	@Test
	public void getDataToDrawSubtypeTest08() throws IOException {
		try {
			// get data of collection, input for test 
			dataSubtype = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawSubtype\\getDataToDrawSubtype_collection_006.txt");
			// get data output need to compare
			tmpOutput = "{}";
			// get data of function getDataToDrawBar
			tmpResultActual = new Gson().toJson((Map)chart.getDataToDrawSubtype(dataSubtype));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Check output with value of KEYEXPR is empty.
	 * Expected: value of KEYEXPR in data of two group is empty  
	 * @throws IOException
	 */
	@Test
	public void getDataToDrawSubtypeTest09() throws IOException {
		try {
			// get data of collection, input for test 
			dataSubtype = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawSubtype\\getDataToDrawSubtype_collection_007.txt");
			// get data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawSubtype\\getDataToDrawSubtype_tmpOutput_005.txt");
			// get data of function getDataToDrawBar
			tmpResultActual = new Gson().toJson((Map)chart.getDataToDrawSubtype(dataSubtype));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Check output with value of KEYEXPR is null.
	 * Expected: value of KEYEXPR in data of two group is empty  
	 * @throws IOException
	 */
	@Test
	public void getDataToDrawSubtypeTest10() throws IOException {
		try {
			// get data of collection, input for test 
			dataSubtype = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawSubtype\\getDataToDrawSubtype_collection_008.txt");
			// get data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawSubtype\\getDataToDrawSubtype_tmpOutput_005.txt");
			// get data of function getDataToDrawBar
			tmpResultActual = new Gson().toJson((Map)chart.getDataToDrawSubtype(dataSubtype));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Check output with value of collection does not contain KEYEXPR.
	 * Expected: data of two group does not contain KEYEXPR 
	 * @throws IOException
	 */
	@Test
	public void getDataToDrawSubtypeTest11() throws IOException {
		try {
			// get data of collection, input for test 
			dataSubtype = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawSubtype\\getDataToDrawSubtype_collection_009.txt");
			// get data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawSubtype\\getDataToDrawSubtype_tmpOutput_006.txt");
			// get data of function getDataToDrawBar
			tmpResultActual = new Gson().toJson((Map)chart.getDataToDrawSubtype(dataSubtype));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Check output with value of KEYEXPR contain only key root of one group.
	 * Expected: value of _root only exist one group correspond to KEYEXPR
	 * @throws IOException
	 */
	@Test
	public void getDataToDrawSubtypeTest12() throws IOException {
		try {
			// get data of collection, input for test 
			dataSubtype = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawSubtype\\getDataToDrawSubtype_collection_010.txt");
			// get data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawSubtype\\getDataToDrawSubtype_tmpOutput_007.txt");
			// get data of function getDataToDrawBar
			tmpResultActual = new Gson().toJson((Map)chart.getDataToDrawSubtype(dataSubtype));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Check output with value of KEYEXPR does not contain key root of group.
	 * Expected: _root does not exist in data of group
	 * @throws IOException
	 */
	@Test
	public void getDataToDrawSubtypeTest13() throws IOException {
		try {
			// get data of collection, input for test 
			dataSubtype = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawSubtype\\getDataToDrawSubtype_collection_011.txt");
			// get data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawSubtype\\getDataToDrawSubtype_tmpOutput_008.txt");
			// get data of function getDataToDrawBar
			tmpResultActual = new Gson().toJson((Map)chart.getDataToDrawSubtype(dataSubtype));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Check output with value of key root of group in KEYEXPR is list empty.
	 * Expected: value of _root in each group is list empty
	 * @throws IOException
	 */
	@Test
	public void getDataToDrawSubtypeTest14() throws IOException {
		try {
			// get data of collection, input for test 
			dataSubtype = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawSubtype\\getDataToDrawSubtype_collection_012.txt");
			// get data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawSubtype\\getDataToDrawSubtype_tmpOutput_009.txt");
			// get data of function getDataToDrawBar
			tmpResultActual = new Gson().toJson((Map)chart.getDataToDrawSubtype(dataSubtype));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Check output with value of key root of group in KEYEXPR is null.
	 * Expected: value of _root in each group is list empty
	 * @throws IOException
	 */
	@Test
	public void getDataToDrawSubtypeTest15() throws IOException {
		try {
			// get data of collection, input for test 
			dataSubtype = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawSubtype\\getDataToDrawSubtype_collection_013.txt");
			// get data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawSubtype\\getDataToDrawSubtype_tmpOutput_008.txt");
			// get data of function getDataToDrawBar
			tmpResultActual = new Gson().toJson((Map)chart.getDataToDrawSubtype(dataSubtype));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Check output with value of _unit is map empty.
	 * Expected: value of _unit in each group is map empty
	 * @throws IOException 
	 */
	@Test
	public void getDataToDrawSubtypeTest16() throws IOException {
		try {
			// get data of collection, input for test 
			dataSubtype = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawSubtype\\getDataToDrawSubtype_collection_019.txt");
			// set data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawSubtype\\getDataToDrawSubtype_tmpOutput_013.txt");
			// get data of function getDataToDrawSubtype
			tmpResultActual = new Gson().toJson((Map)chart.getDataToDrawSubtype(dataSubtype));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Check output with value of _unit is null.
	 * Expected: _unit does not exist in data of each group
	 * @throws IOException 
	 */
	@Test
	public void getDataToDrawSubtypeTest17() throws IOException {
		try {
			// get data of collection, input for test 
			dataSubtype = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawSubtype\\getDataToDrawSubtype_collection_020.txt");
			// set data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawSubtype\\getDataToDrawSubtype_tmpOutput_014.txt");
			// get data of function getDataToDrawSubtype
			tmpResultActual = new Gson().toJson((Map)chart.getDataToDrawSubtype(dataSubtype));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Check output with value of KEYEXPR does not contain _unit.
	 * Expected: _unit does not exist in data of each group
	 * @throws IOException 
	 */
	@Test
	public void getDataToDrawSubtypeTest18() throws IOException {
		try {
			// get data of collection, input for test 
			dataSubtype = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawSubtype\\getDataToDrawSubtype_collection_021.txt");
			// set data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawSubtype\\getDataToDrawSubtype_tmpOutput_014.txt");
			// get data of function getDataToDrawSubtype
			tmpResultActual = new Gson().toJson((Map)chart.getDataToDrawSubtype(dataSubtype));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Check output with value of _unit is map contains unit of each group.
	 * Expected: value of _unit in each group correspond to data input
	 * @throws IOException 
	 */
	@Test
	public void getDataToDrawSubtypeTest19() throws IOException {
		try {
			// get data of collection, input for test 
			dataSubtype = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawSubtype\\getDataToDrawSubtype_collection_022.txt");
			// set data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawSubtype\\getDataToDrawSubtype_tmpOutput_015.txt");
			// get data of function getDataToDrawSubtype
			tmpResultActual = new Gson().toJson((Map)chart.getDataToDrawSubtype(dataSubtype));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Check output with value of _unit contain key not exist in data of the collection.
	 * Expected: value of _unit in group D is not empty and value of _unit in group M is empty.
	 * @throws IOException 
	 */
	@Test
	public void getDataToDrawSubtypeTest20() throws IOException {
		try {
			// get data of collection, input for test 
			dataSubtype = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawSubtype\\getDataToDrawSubtype_collection_023.txt");
			// set data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawSubtype\\getDataToDrawSubtype_tmpOutput_016.txt");
			// get data of function getDataToDrawSubtype
			tmpResultActual = new Gson().toJson((Map)chart.getDataToDrawSubtype(dataSubtype));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Check output with value of _description is empty.
	 * Expected: value of _description in each group is empty
	 * @throws IOException 
	 */
	@Test
	public void getDataToDrawSubtypeTest21() throws IOException {
		try {
			// get data of collection, input for test 
			dataSubtype = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawSubtype\\getDataToDrawSubtype_collection_024.txt");
			// set data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawSubtype\\getDataToDrawSubtype_tmpOutput_017.txt");
			// get data of function getDataToDrawSubtype
			tmpResultActual = new Gson().toJson((Map)chart.getDataToDrawSubtype(dataSubtype));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	* Check output with value of _description is null.
	* Expected: _description does not exist in each group
	* @throws IOException 
	*/
	@Test
	public void getDataToDrawSubtypeTest22() throws IOException {
		try {
			// get data of collection, input for test 
			dataSubtype = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawSubtype\\getDataToDrawSubtype_collection_025.txt");
			// set data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawSubtype\\getDataToDrawSubtype_tmpOutput_018.txt");
			// get data of function getDataToDrawSubtype
			tmpResultActual = new Gson().toJson((Map)chart.getDataToDrawSubtype(dataSubtype));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	* Check output with value of KEYEXPR does not contain _description.
	* Expected: _description does not exist in each group
	* @throws IOException 
	*/
	@Test
	public void getDataToDrawSubtypeTest23() throws IOException {
		try {
			// get data of collection, input for test 
			dataSubtype = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawSubtype\\getDataToDrawSubtype_collection_026.txt");
			// set data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawSubtype\\getDataToDrawSubtype_tmpOutput_018.txt");
			// get data of function getDataToDrawSubtype
			tmpResultActual = new Gson().toJson((Map)chart.getDataToDrawSubtype(dataSubtype));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}				
	
	/**
	 * Check output with value of collection does not contains _chart.
	 * Expected: _chart does not exist in data of each group
	 * @throws IOException 
	 */
	@Test
	public void getDataToDrawSubtypeTest24() throws IOException {
		try {
			// get data of collection, input for test 
			dataSubtype = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawSubtype\\getDataToDrawSubtype_collection_014.txt");
			// set data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawSubtype\\getDataToDrawSubtype_tmpOutput_010.txt");
			// get data of function getDataToDrawSubtype
			tmpResultActual = new Gson().toJson((Map)chart.getDataToDrawSubtype(dataSubtype));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}	

	/**
	 * Check output with value of _chart is map empty.
	 * Expected: _chart does not exist in data of each group
	 * @throws IOException 
	 */
	@Test
	public void getDataToDrawSubtypeTest25() throws IOException {
		try {	
			// get data of collection, input for test 
			dataSubtype = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawSubtype\\getDataToDrawSubtype_collection_015.txt");
			// set data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawSubtype\\getDataToDrawSubtype_tmpOutput_010.txt");
			// get data of function getDataToDrawSubtype
			tmpResultActual = new Gson().toJson((Map)chart.getDataToDrawSubtype(dataSubtype));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}	
	
	/**
	 * Check output with value of _chart is null.
	 * Expected: _chart does not exist in data of each group
	 * @throws IOException 
	 */
	@Test
	public void getDataToDrawSubtypeTest26() throws IOException {
		try {
			// get data of collection, input for test 
			dataSubtype = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawSubtype\\getDataToDrawSubtype_collection_016.txt");
			// set data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawSubtype\\getDataToDrawSubtype_tmpOutput_010.txt");
			// get data of function getDataToDrawSubtype
			tmpResultActual = new Gson().toJson((Map)chart.getDataToDrawSubtype(dataSubtype));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Check output with value of key in _chart does not exist in value of data.
	 * Expected: _chart does not exist in data of each group
	 * @throws IOException 
	 */
	@Test
	public void getDataToDrawSubtypeTest27() throws IOException {
		try {
			// get data of collection, input for test 
			dataSubtype = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawSubtype\\getDataToDrawSubtype_collection_017.txt");
			// set data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawSubtype\\getDataToDrawSubtype_tmpOutput_011.txt");
			// get data of function getDataToDrawSubtype
			tmpResultActual = new Gson().toJson((Map)chart.getDataToDrawSubtype(dataSubtype));
			assertEquals(tmpOutput, tmpResultActual);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}			

	/**
	 * Check output with value of _chart is list data.
	 * Expected: value of _chart in each group is the same
	 * @throws IOException 
	 */
	@Test
	public void getDataToDrawSubtypeTest28() throws IOException {
		try {
			// get data of collection, input for test 
			dataSubtype = readFileInput("tmp\\datatest\\DataToDrawChart\\getDataToDrawSubtype\\getDataToDrawSubtype_collection_018.txt");
			// set data output need to compare
			tmpOutput = readFileOutput("tmp\\datatest\\DataToDrawChart\\getDataToDrawSubtype\\getDataToDrawSubtype_tmpOutput_012.txt");
			// get data of function getDataToDrawSubtype
			tmpResultActual = new Gson().toJson((Map)chart.getDataToDrawSubtype(dataSubtype));
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
