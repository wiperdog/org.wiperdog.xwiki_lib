package org.wiperdog.custommongodbconnection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.wiperdog.lib.BaseCMongoDBConnTestcase;
import org.wiperdog.lib.TestUTCommon;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;

/**
 * Testcase for method getDataLimitField(collection, fields, limit)
 * @author luvina
 *
 */
public class CMongoDBConn_UT_06 extends BaseCMongoDBConnTestcase {

	/**
	 * Check getting data with valid params records : 5 limit : 10
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void getDataLimitField_3params_001() throws FileNotFoundException {
		String[] fields = new String[] { "fetchAt", "TablespaceName", "CurrentUsedSizeKB", "CurrentTotalSizeKB", "CurrentUsedPct" };
		try {
			// Get data test and insert into mongodb
			FileReader reader = new FileReader(new File("tmp\\datatest\\CMongoDBconn\\getDataLimitField_3params_001.txt"));

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
			ArrayList<Map> result = (ArrayList<Map>) conn.getDataLimitFields(realCollectionName, fields, 10);
			for (Map res : result) {
				res.remove("_id");
			}

			// Get expected result
			reader = new FileReader(new File("tmp\\datatest\\CMongoDBconn\\getDataLimitField_3params_001.txt_expected"));
			ArrayList<Map> expectedResult = gson.fromJson(reader, ArrayList.class);

			boolean isEquals = TestUTCommon.compare2object(expectedResult, result);

			assertTrue(isEquals);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Check getting data with valid params records : 20 limit : 10
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void getDataLimitField_3params_002() throws FileNotFoundException {
		String[] fields = new String[] { "fetchAt", "TablespaceName", "CurrentUsedSizeKB", "CurrentTotalSizeKB", "CurrentUsedPct" };
		try {
			// Get data test and insert into mongodb
			FileReader reader = new FileReader(new File("tmp\\datatest\\CMongoDBconn\\getDataLimitField_3params_002.txt"));

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
			ArrayList<Map> result = (ArrayList<Map>) conn.getDataLimitFields(realCollectionName, fields, 10);
			for (Map res : result) {
				res.remove("_id");
			}

			// Get expected result
			reader = new FileReader(new File("tmp\\datatest\\CMongoDBconn\\getDataLimitField_3params_002.txt_expected"));
			ArrayList<Map> expectedResult = gson.fromJson(reader, ArrayList.class);

			boolean isEquals = TestUTCommon.compare2object(expectedResult, result);

			assertTrue(isEquals);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Check getting 0 record when limit = 0
	 */
	@Test
	public void getDataLimitField_3params_003() {
		String[] fields = new String[] { "fetchAt", "TablespaceName", "CurrentUsedSizeKB", "CurrentTotalSizeKB", "CurrentUsedPct" };
		String realCollectionName = collection + "." + istIid;
		ArrayList<Map> result = (ArrayList<Map>) conn.getDataLimitFields(realCollectionName, fields, 0);
		assertTrue(result.size() == 0);
	}

	/**
	 * Check getting 0 record when limit < 0
	 */
	@Test
	public void getDataLimitField_3params_004() {
		String[] fields = new String[] { "fetchAt", "TablespaceName", "CurrentUsedSizeKB", "CurrentTotalSizeKB", "CurrentUsedPct" };
		String realCollectionName = collection + "." + istIid;
		ArrayList<Map> result = (ArrayList<Map>) conn.getDataLimitFields(realCollectionName, fields, -1);
		assertTrue(result.size() == 0);
	}

	/**
	 * Check getting data with 1 field
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void getDataLimitField_3params_005() throws FileNotFoundException {
		String[] fields = new String[] { "TablespaceName" };
		try {
			// Get data test and insert into mongodb
			FileReader reader = new FileReader(new File("tmp\\datatest\\CMongoDBconn\\getDataLimitField_3params_005.txt"));

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
			ArrayList<Map> result = (ArrayList<Map>) conn.getDataLimitFields(realCollectionName, fields, 10);
			for (Map res : result) {
				res.remove("_id");
			}

			// Get expected result
			reader = new FileReader(new File("tmp\\datatest\\CMongoDBconn\\getDataLimitField_3params_005.txt_expected"));
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
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void getDataLimitField_3params_006() throws FileNotFoundException {
		String[] fields = new String[] { "fetchAt", "TablespaceName", "CurrentUsedSizeKB", "CurrentTotalSizeKB", "CurrentFreeSizeKB", "CurrentUsedPct", "MaxTotalSizeKB", "MaxFreeSizeKB", "MaxUsedPct" };
		try {
			// Get data test and insert into mongodb
			FileReader reader = new FileReader(new File("tmp\\datatest\\CMongoDBconn\\getDataLimitField_3params_006.txt"));

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
			ArrayList<Map> result = (ArrayList<Map>) conn.getDataLimitFields(realCollectionName, fields, 10);
			for (Map res : result) {
				res.remove("_id");
			}

			// Get expected result
			reader = new FileReader(new File("tmp\\datatest\\CMongoDBconn\\getDataLimitField_3params_006.txt_expected"));
			ArrayList<Map> expectedResult = gson.fromJson(reader, ArrayList.class);

			boolean isEquals = TestUTCommon.compare2object(expectedResult, result);

			assertTrue(isEquals);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Check exception handle when limit is null
	 */
	@Test(expected = AssertionError.class)
	public void getDataLimitField_3params_007() {
		try {
			String[] fields = new String[] { "fetchAt", "TablespaceName", "CurrentUsedSizeKB", "CurrentTotalSizeKB", "CurrentUsedPct" };
			String realCollectionName = collection + "." + istIid;
			ArrayList<Map> result = (ArrayList<Map>) conn.getDataLimitFields(realCollectionName, fields, null);
		} catch (AssertionError ae) {
			assertTrue(ae.getMessage().contains("Can not get data ! Limit is null"));
			throw ae;
		}
	}

	/**
	 * Check getting data with fields that doesn't exist
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void getDataLimitField_3params_008() throws FileNotFoundException {
		String[] fields = new String[] { "field1", "field2", "field3", "field4" };
		try {
			// Get data test and insert into mongodb
			FileReader reader = new FileReader(new File("tmp\\datatest\\CMongoDBconn\\getDataLimitField_3params_008.txt"));

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
			ArrayList<Map> result = (ArrayList<Map>) conn.getDataLimitFields(realCollectionName, fields, 10);
			for (Map res : result) {
				res.remove("_id");
			}

			// Get expected result
			reader = new FileReader(new File("tmp\\datatest\\CMongoDBconn\\getDataLimitField_3params_008.txt_expected"));
			ArrayList<Map> expectedResult = gson.fromJson(reader, ArrayList.class);

			boolean isEquals = TestUTCommon.compare2object(expectedResult, result);

			assertTrue(isEquals);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Check exception handle when no field is provided
	 */
	@Test(expected = AssertionError.class)
	public void getDataLimitField_3params_009() {
		try {
			String[] fields = new String[0];
			String realCollectionName = collection + "." + istIid;
			ArrayList<Map> result = (ArrayList<Map>) conn.getDataLimitFields(realCollectionName, fields, 10);
		} catch (AssertionError ae) {
			assertTrue(ae.getMessage().contains("Can not get data ! List fields is null or empty"));
			throw ae;
		}
	}
}
