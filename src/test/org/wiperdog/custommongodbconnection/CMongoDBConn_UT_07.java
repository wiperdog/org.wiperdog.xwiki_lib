package org.wiperdog.custommongodbconnection;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Map;

import org.junit.Test;
import org.wiperdog.lib.BaseCMongoDBConnTestcase;
import org.wiperdog.lib.TestUTCommon;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;

/**
 * Testcase for method getDataLimitFields(collection, fields)
 * @author luvina
 *
 */
public class CMongoDBConn_UT_07 extends BaseCMongoDBConnTestcase {

	/**
	 * Check getting data with valid params
	 * field contains fetchAt
	 * @throws FileNotFoundException 
	 * 
	 */
	@Test
	public void getDataLimitField_2params_001() throws FileNotFoundException {
		String[] fields = new String[] { "fetchAt", "TablespaceName", "CurrentUsedSizeKB", "CurrentTotalSizeKB", "CurrentUsedPct" };
		try {
			// Get data test and insert into mongodb
			FileReader reader = new FileReader(new File("tmp\\datatest\\CMongoDBconn\\getDataLimitField_2params_001.txt"));

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			ArrayList<Map> datatest = gson.fromJson(reader, ArrayList.class);
			ArrayList<DBObject> listObject = new ArrayList<DBObject>();
			for (Map map : datatest) {
				DBObject obj = new BasicDBObject(map);
				listObject.add(obj);
			}

			DB db = (DB) conn.getDb();
			String realCollectionName = collection + "." + istIid;
			db.getCollection(realCollectionName).drop();
			db.getCollection(realCollectionName).insert(listObject);

			// Get data and remove the _id element that auto inserted by mongoDB
			ArrayList<Map> result = (ArrayList<Map>) conn.getDataLimitFields(realCollectionName, fields);
			for (Map res : result) {
				res.remove("_id");
			}

			// Get expected result
			reader = new FileReader(new File("tmp\\datatest\\CMongoDBconn\\getDataLimitField_2params_001.txt_expected"));
			ArrayList<Map> expectedResult = gson.fromJson(reader, ArrayList.class);

			boolean isEquals = TestUTCommon.compare2object(expectedResult, result);

			assertTrue(isEquals);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Check getting data with valid param
	 * field doesn't contains fetchAt
	 * @throws FileNotFoundException 
	 * 
	 */
	@Test
	public void getDataLimitField_2params_002() throws FileNotFoundException {
		String[] fields = new String[] { "TablespaceName", "CurrentUsedSizeKB", "CurrentTotalSizeKB", "CurrentUsedPct" };
		try {
			// Get data test and insert into mongodb
			FileReader reader = new FileReader(new File("tmp\\datatest\\CMongoDBconn\\getDataLimitField_2params_002.txt"));

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			ArrayList<Map> datatest = gson.fromJson(reader, ArrayList.class);
			ArrayList<DBObject> listObject = new ArrayList<DBObject>();
			for (Map map : datatest) {
				DBObject obj = new BasicDBObject(map);
				listObject.add(obj);
			}

			DB db = (DB) conn.getDb();
			String realCollectionName = collection + "." + istIid;
			db.getCollection(realCollectionName).drop();
			db.getCollection(realCollectionName).insert(listObject);

			// Get data and remove the _id element that auto inserted by mongoDB
			ArrayList<Map> result = (ArrayList<Map>) conn.getDataLimitFields(realCollectionName, fields);
			for (Map res : result) {
				res.remove("_id");
			}

			// Get expected result
			reader = new FileReader(new File("tmp\\datatest\\CMongoDBconn\\getDataLimitField_2params_002.txt_expected"));
			ArrayList<Map> expectedResult = gson.fromJson(reader, ArrayList.class);

			boolean isEquals = TestUTCommon.compare2object(expectedResult, result);

			assertTrue(isEquals);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Check getting data with 1 field
	 * @throws FileNotFoundException 
	 */
	@Test
	public void getDataLimitField_2params_003() throws FileNotFoundException {
		String[] fields = new String[] { "TablespaceName" };
		try {
			// Get data test and insert into mongodb
			FileReader reader = new FileReader(new File("tmp\\datatest\\CMongoDBconn\\getDataLimitField_2params_003.txt"));

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			ArrayList<Map> datatest = gson.fromJson(reader, ArrayList.class);
			ArrayList<DBObject> listObject = new ArrayList<DBObject>();
			for (Map map : datatest) {
				DBObject obj = new BasicDBObject(map);
				listObject.add(obj);
			}

			DB db = (DB) conn.getDb();
			String realCollectionName = collection + "." + istIid;
			db.getCollection(realCollectionName).drop();
			db.getCollection(realCollectionName).insert(listObject);

			// Get data and remove the _id element that auto inserted by mongoDB
			ArrayList<Map> result = (ArrayList<Map>) conn.getDataLimitFields(realCollectionName, fields);
			for (Map res : result) {
				res.remove("_id");
			}

			// Get expected result
			reader = new FileReader(new File("tmp\\datatest\\CMongoDBconn\\getDataLimitField_2params_003.txt_expected"));
			ArrayList<Map> expectedResult = gson.fromJson(reader, ArrayList.class);

			boolean isEquals = TestUTCommon.compare2object(expectedResult, result);

			assertTrue(isEquals);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Check getting data with all fields
	 * @throws FileNotFoundException 
	 */
	@Test
	public void getDataLimitField_2params_004() throws FileNotFoundException {
		String[] fields = new String[] { "fetchAt", "TablespaceName", "CurrentUsedSizeKB", "CurrentTotalSizeKB", "CurrentFreeSizeKB", "CurrentUsedPct", "MaxTotalSizeKB", "MaxFreeSizeKB", "MaxUsedPct" };
		try {
			// Get data test and insert into mongodb
			FileReader reader = new FileReader(new File("tmp\\datatest\\CMongoDBconn\\getDataLimitField_2params_004.txt"));

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			ArrayList<Map> datatest = gson.fromJson(reader, ArrayList.class);
			ArrayList<DBObject> listObject = new ArrayList<DBObject>();
			for (Map map : datatest) {
				DBObject obj = new BasicDBObject(map);
				listObject.add(obj);
			}

			DB db = (DB) conn.getDb();
			String realCollectionName = collection + "." + istIid;
			db.getCollection(realCollectionName).drop();
			db.getCollection(realCollectionName).insert(listObject);

			// Get data and remove the _id element that auto inserted by mongoDB
			ArrayList<Map> result = (ArrayList<Map>) conn.getDataLimitFields(realCollectionName, fields);
			for (Map res : result) {
				res.remove("_id");
			}

			// Get expected result
			reader = new FileReader(new File("tmp\\datatest\\CMongoDBconn\\getDataLimitField_2params_004.txt_expected"));
			ArrayList<Map> expectedResult = gson.fromJson(reader, ArrayList.class);

			boolean isEquals = TestUTCommon.compare2object(expectedResult, result);

			assertTrue(isEquals);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Check exception handle when collection is null
	 */
	@Test(expected = AssertionError.class)
	public void getDataLimitField_2params_005() {
		try {
			String[] fields = new String[] { "TablespaceName", "CurrentUsedSizeKB", "CurrentTotalSizeKB", "CurrentUsedPct" };
			ArrayList<Map> result = (ArrayList<Map>) conn.getDataLimitFields(null, fields);
		} catch (AssertionError ae) {
			assertTrue(ae.getMessage().contains("Can not get data ! Collection is null or empty string"));
			throw ae;
		}
	}

	/**
	 * Check exception handle when collection is empty
	 */
	@Test(expected = AssertionError.class)
	public void getDataLimitField_2params_006() {
		try {
			String[] fields = new String[] { "TablespaceName", "CurrentUsedSizeKB", "CurrentTotalSizeKB", "CurrentUsedPct" };
			ArrayList<Map> result = (ArrayList<Map>) conn.getDataLimitFields("", fields);
		} catch (AssertionError ae) {
			assertTrue(ae.getMessage().contains("Can not get data ! Collection is null or empty string"));
			throw ae;
		}
	}

	/**
	 * Check exception handle when collection is space character
	 */
	@Test(expected = AssertionError.class)
	public void getDataLimitField_2params_007() {
		try {
			String[] fields = new String[] { "TablespaceName", "CurrentUsedSizeKB", "CurrentTotalSizeKB", "CurrentUsedPct" };
			ArrayList<Map> result = (ArrayList<Map>) conn.getDataLimitFields(" ", fields);
		} catch (AssertionError ae) {
			assertTrue(ae.getMessage().contains("Can not get data ! Collection is null or empty string"));
			throw ae;
		}
	}

	/**
	 * Check getting 0 record when collection doesn't exist
	 */
	@Test
	public void getDataLimitField_2params_008() {
		String[] fields = new String[] { "TablespaceName", "CurrentUsedSizeKB", "CurrentTotalSizeKB", "CurrentUsedPct" };
		ArrayList<Map> result = (ArrayList<Map>) conn.getDataLimitFields("NotInDB", fields);
		assertTrue(result.size() == 0);
	}

	/**
	 * Check exception handle when field is null
	 */
	@Test(expected=AssertionError.class)
	public void getDataLimitField_2params_009() {
		try {
			String realCollectionName = collection + "." + istIid;
			ArrayList<Map> result = (ArrayList<Map>) conn.getDataLimitFields(realCollectionName, null);
		} catch (AssertionError ae) {
			assertTrue(ae.getMessage().contains("Can not get data ! List fields is null or empty"));
			throw ae;
		}
	}

	/**
	 * Check exception handle when field is empty
	 */
	@Test(expected=AssertionError.class)
	public void getDataLimitField_2params_010() {
		try {
			String[] fields = new String[0];
			String realCollectionName = collection + "." + istIid;
			ArrayList<Map> result = (ArrayList<Map>) conn.getDataLimitFields(realCollectionName, fields);
		} catch (AssertionError ae) {
			assertTrue(ae.getMessage().contains("Can not get data ! List fields is null or empty"));
			throw ae;
		}
	}

	/**
	 * Check getting data with fields that doesn't exist
	 */
	@Test
	public void getDataLimitField_2params_011() {
		String[] fields = new String[] { "field1", "field2", "field3", "field4" };
		try {
			// Get data test and insert into mongodb
			FileReader reader = new FileReader(new File("tmp\\datatest\\CMongoDBconn\\getDataLimitField_2params_011.txt"));

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			ArrayList<Map> datatest = gson.fromJson(reader, ArrayList.class);
			ArrayList<DBObject> listObject = new ArrayList<DBObject>();
			for (Map map : datatest) {
				DBObject obj = new BasicDBObject(map);
				listObject.add(obj);
			}

			DB db = (DB) conn.getDb();
			String realCollectionName = collection + "." + istIid;
			db.getCollection(realCollectionName).drop();
			db.getCollection(realCollectionName).insert(listObject);

			// Get data and remove the _id element that auto inserted by mongoDB
			ArrayList<Map> result = (ArrayList<Map>) conn.getDataLimitFields(realCollectionName, fields);
			for (Map res : result) {
				res.remove("_id");
			}

			// Get expected result
			reader = new FileReader(new File("tmp\\datatest\\CMongoDBconn\\getDataLimitField_2params_011.txt_expected"));
			ArrayList<Map> expectedResult = gson.fromJson(reader, ArrayList.class);

			boolean isEquals = TestUTCommon.compare2object(expectedResult, result);

			assertTrue(isEquals);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
