/**
 * copied from CMongoDBConn_UT_06.java
 * for mongodb without auth option
 */
package org.wiperdog.custommongodbconnection;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assume.*;
import org.wiperdog.lib.BaseCMongoDBConnTestcase;
import org.wiperdog.lib.TestUTCommon;

import com.gmongo.GMongo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.MongoException;

/**
 * Testcase for method getConnection(host, port, dbname, userName, password)
 *
 * @author luvina
 *
 */
public class TestCMongoDBConn_06 {
	private boolean mongdbpresent = true;
	private final String [] args = []
	String collection = "MySQL.Database_Area.InnoDBTablespace_Free";
	String istIid = "localhost-@MYSQL-information_schema";
	CMongoDBConn conn;
	public TestCMongoDBConn_06() {
	}

	@Before
	public void startup() {
		assumeTrue(mongdbpresent);
		startmongo.main(args)
		conn = TestUTCommon.createNewConnectionForTest();
	}
	
	@After
	public void shutdown() {
		assumeTrue(mongdbpresent);
		stopmongo.main(args)
	}
	/**
	 * Check getting data with valid params records : 5 limit : 10
	 * Expected: Got 5 records with
	 * ["fetchAt", "TablespaceName", "CurrentUsedSizeKB", "CurrentTotalSizeKB", "CurrentUsedPct"] fields
	 * tmp/datatest/CMongoDBconn/getDataLimitField_3params_001.txt_expected
	 *
	 * @throws FileNotFoundException
	 */
	@Test
	public void getDataLimitField_3params_001() throws FileNotFoundException {
		String [] fields = ["fetchAt", "TablespaceName", "CurrentUsedSizeKB", "CurrentTotalSizeKB", "CurrentUsedPct"]
		try {
			// Get data test and insert into mongodb
			println "11111111111"
			FileReader reader = new FileReader(new File("tmp/datatest/CMongoDBconn/getDataLimitField_3params_001.txt"));
			println "22222222222"
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			ArrayList<Map> datatest = gson.fromJson(reader, ArrayList.class);
			ArrayList<DBObject> listObject = new ArrayList<DBObject>();
			for (Map map : datatest) {
				DBObject obj = new BasicDBObject(map);
				listObject.add(obj);
			}

			DB db = (DB) conn.getDb();
			println "db: " + db
			String realCollectionName = collection + "." + istIid;
			println "realCollectionName: " + realCollectionName
			db.getCollection(realCollectionName).drop();
			db.getCollection(realCollectionName).insert(listObject);
			println "abc"
			// Get data and remove the _id element that auto inserted by mongoDB
			ArrayList<Map> result = (ArrayList<Map>) conn.getDataLimitFields(realCollectionName, fields, 10);
			for (Map res : result) {
				res.remove("_id");
			}

			// Get expected result
			reader = new FileReader(new File("tmp/datatest/CMongoDBconn/getDataLimitField_3params_001.txt_expected"));
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
	 * Expected: Got 10 records with
	 * ["fetchAt", "TablespaceName", "CurrentUsedSizeKB", "CurrentTotalSizeKB", "CurrentUsedPct"] fields
	 * tmp/datatest/CMongoDBconn/getDataLimitField_3params_002.txt_expected
	 *
	 * @throws FileNotFoundException
	 */
	@Test
	public void getDataLimitField_3params_002() throws FileNotFoundException {
		String[] fields = ["fetchAt", "TablespaceName", "CurrentUsedSizeKB", "CurrentTotalSizeKB", "CurrentUsedPct" ];
		try {
			// Get data test and insert into mongodb
			FileReader reader = new FileReader(new File("tmp/datatest/CMongoDBconn/getDataLimitField_3params_002.txt"));

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
			reader = new FileReader(new File("tmp/datatest/CMongoDBconn/getDataLimitField_3params_002.txt_expected"));
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
	 * Expected: Got 0 record
	 */
	@Test
	public void getDataLimitField_3params_003() {
		String[] fields = ["fetchAt", "TablespaceName", "CurrentUsedSizeKB", "CurrentTotalSizeKB", "CurrentUsedPct"];
		String realCollectionName = collection + "." + istIid;
		ArrayList<Map> result = (ArrayList<Map>) conn.getDataLimitFields(realCollectionName, fields, 0);
		assertTrue(result.size() == 0);
	}

	/**
	 * Check getting 0 record when limit < 0
	 * Expected: Got 0 record
	 */
	@Test
	public void getDataLimitField_3params_004() {
		String[] fields = ["fetchAt", "TablespaceName", "CurrentUsedSizeKB", "CurrentTotalSizeKB", "CurrentUsedPct"];
		String realCollectionName = collection + "." + istIid;
		ArrayList<Map> result = (ArrayList<Map>) conn.getDataLimitFields(realCollectionName, fields, -1);
		assertTrue(result.size() == 0);
	}

	/**
	 * Check getting data with 1 field
	 * Expected: Got 5 records with
	 * ["fetchAt", "TablespaceName"] fields
	 * tmp/datatest/CMongoDBconn/getDataLimitField_3params_005.txt_expected
	 *
	 * @throws FileNotFoundException
	 */
	@Test
	public void getDataLimitField_3params_005() throws FileNotFoundException {
		String[] fields = ["TablespaceName"];
		try {
			// Get data test and insert into mongodb
			FileReader reader = new FileReader(new File("tmp/datatest/CMongoDBconn/getDataLimitField_3params_005.txt"));

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
			reader = new FileReader(new File("tmp/datatest/CMongoDBconn/getDataLimitField_3params_005.txt_expected"));
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
	 * Expected: Got 5 records with all fields
	 * "tmp/datatest/CMongoDBconn/getDataLimitField_3params_006.txt_expected"
	 *
	 * @throws FileNotFoundException
	 */
	@Test
	public void getDataLimitField_3params_006() throws FileNotFoundException {
		String[] fields = ["fetchAt", "TablespaceName", "CurrentUsedSizeKB", "CurrentTotalSizeKB", "CurrentFreeSizeKB", "CurrentUsedPct", "MaxTotalSizeKB", "MaxFreeSizeKB", "MaxUsedPct" ];
		try {
			// Get data test and insert into mongodb
			FileReader reader = new FileReader(new File("tmp/datatest/CMongoDBconn/getDataLimitField_3params_006.txt"));

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
			reader = new FileReader(new File("tmp/datatest/CMongoDBconn/getDataLimitField_3params_006.txt_expected"));
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
	 * Expected: AssertionError with message "Can not get data ! Limit is null"
	 */
	@Test(expected = AssertionError.class)
	public void getDataLimitField_3params_007() {
		try {
			String[] fields = ["fetchAt", "TablespaceName", "CurrentUsedSizeKB", "CurrentTotalSizeKB", "CurrentUsedPct"];
			String realCollectionName = collection + "." + istIid;
			ArrayList<Map> result = (ArrayList<Map>) conn.getDataLimitFields(realCollectionName, fields, null);
		} catch (AssertionError ae) {
			assertTrue(ae.getMessage().contains("Can not get data ! Limit is null"));
			throw ae;
		}
	}

	/**
	 * Check getting data with fields that doesn't exist
	 * Expected: Got 5 records with only fetchAt field
	 * tmp/datatest/CMongoDBconn/getDataLimitField_3params_008.txt_expected
	 *
	 * @throws FileNotFoundException
	 */
	@Test
	public void getDataLimitField_3params_008() throws FileNotFoundException {
		String[] fields = ["field1", "field2", "field3", "field4"];
		try {
			// Get data test and insert into mongodb
			FileReader reader = new FileReader(new File("tmp/datatest/CMongoDBconn/getDataLimitField_3params_008.txt"));

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
			reader = new FileReader(new File("tmp/datatest/CMongoDBconn/getDataLimitField_3params_008.txt_expected"));
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
	 * Expected: AssertionError with message "Can not get data ! List fields is null or empty"
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
