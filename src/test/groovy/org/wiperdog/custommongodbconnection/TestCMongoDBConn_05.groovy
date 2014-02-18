/**
 * copied from CMongoDBConn_UT_05.java
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
public class TestCMongoDBConn_05 {
	private boolean mongdbpresent = true;
	private final String [] args = []
	String collection = "MySQL.Database_Area.InnoDBTablespace_Free";
	String istIid = "localhost-@MYSQL-information_schema";
	CMongoDBConn conn;
	public TestCMongoDBConn_05() {
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
		conn.closeConnection();
	}
	/**
	 * Check getting data with valid param
	 * Expected: Got data exactly in
	 * tmp/datatest/CMongoDBconn/getDataAllFields_collection_001.txt
	 * 5 records with all fields
	 *
	 * @throws FileNotFoundException
	 */
	@Test
	public void getDataAllFields_collection_001() throws FileNotFoundException {
		try {
			FileReader reader = new FileReader(new File("tmp/datatest/CMongoDBconn/getDataAllFields_collection_001.txt"));

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			ArrayList<Map> expectedList = gson.fromJson(reader, ArrayList.class);
			ArrayList<DBObject> listObject = new ArrayList<DBObject>();
			for (Map map : expectedList) {
				DBObject obj = new BasicDBObject(map);
				listObject.add(obj);
			}

			DB db = (DB) conn.getDb();
			db.getCollection(collection + "." + istIid).drop();
			db.getCollection(collection + "." + istIid).insert(listObject);

			// Get data and remove the _id element that auto inserted by mongoDB
			String realCollectionName = collection + "." + istIid;
			ArrayList<Map> result = (ArrayList<Map>) conn.getDataAllFields(realCollectionName);
			for (Map res : result) {
				res.remove("_id");
			}

			// Compare
			boolean isEquals = TestUTCommon.compare2object(expectedList, result);

			assertTrue(isEquals);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Check exception handle when collection is null
	 * Expected: AssertionError with message "Can not get data ! Collection is null or empty string"
	 */
	@Test(expected = AssertionError.class)
	public void getDataAllFields_collection_002() {
		try {
			ArrayList<Map> result = (ArrayList<Map>) conn.getDataAllFields(null);
		} catch (AssertionError ae) {
			assertTrue(ae.getMessage().contains("Can not get data ! Collection is null or empty string"));
			throw ae;
		}
	}

	/**
	 * Check exception handle when collection is empty
	 * Expected: AssertionError with message "Can not get data ! Collection is null or empty string"
	 */
	@Test(expected = AssertionError.class)
	public void getDataAllFields_collection_003() {
		try {
			ArrayList<Map> result = (ArrayList<Map>) conn.getDataAllFields("");
		} catch (AssertionError ae) {
			assertTrue(ae.getMessage().contains("Can not get data ! Collection is null or empty string"));
			throw ae;
		}
	}

	/**
	 * Check exception handle when collection is space character
	 * Expected: AssertionError with message "Can not get data ! Collection is null or empty string"
	 */
	@Test(expected = AssertionError.class)
	public void getDataAllFields_collection_004() {
		try {
			ArrayList<Map> result = (ArrayList<Map>) conn.getDataAllFields(" ");
		} catch (AssertionError ae) {
			assertTrue(ae.getMessage().contains("Can not get data ! Collection is null or empty string"));
			throw ae;
		}
	}

	/**
	 * Check getting data normally when collection has space character at begin
	 * and end
	 * Expected: Got data exactly in
	 * tmp/datatest/CMongoDBconn/getDataAllFields_collection_005.txt
	 * 5 records with all fields
	 *
	 * @throws FileNotFoundException
	 */
	@Test
	public void getDataAllFields_collection_005() throws FileNotFoundException {
		try {
			FileReader reader = new FileReader(new File("tmp/datatest/CMongoDBconn/getDataAllFields_collection_005.txt"));

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			ArrayList<Map> expectedList = gson.fromJson(reader, ArrayList.class);
			ArrayList<DBObject> listObject = new ArrayList<DBObject>();
			for (Map map : expectedList) {
				DBObject obj = new BasicDBObject(map);
				listObject.add(obj);
			}

			DB db = (DB) conn.getDb();
			db.getCollection(collection + "." + istIid).drop();
			db.getCollection(collection + "." + istIid).insert(listObject);

			// Get data and remove the _id element that auto inserted by mongoDB
			String realCollectionName = collection + "." + istIid;
			ArrayList<Map> result = (ArrayList<Map>) conn.getDataAllFields(" " + realCollectionName + " ");
			for (Map res : result) {
				res.remove("_id");
			}

			// Compare
			boolean isEquals = TestUTCommon.compare2object(expectedList, result);

			assertTrue(isEquals);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
	}
}
