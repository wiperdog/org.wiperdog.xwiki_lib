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
 * Testcase for method getDataAllFields(String collection)
 * @author luvina
 *
 */
public class CMongoDBConn_UT_05 extends BaseCMongoDBConnTestcase {

	/**
	 * Check getting data with valid param
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void getDataAllFields_collection_001() throws FileNotFoundException {
		try {
			FileReader reader = new FileReader(new File("tmp\\datatest\\CMongoDBconn\\getDataAllFields_collection_001.txt"));

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			ArrayList<Map> expectedList = gson.fromJson(reader, ArrayList.class);
			ArrayList<DBObject> listObject = new ArrayList<DBObject>();
			for (Map map : expectedList) {
				DBObject obj = new BasicDBObject(map);
				listObject.add(obj);
			}

			conn = TestUTCommon.createNewConnectionForTest();
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
	 */
	@Test(expected = AssertionError.class)
	public void getDataAllFields_collection_002() {
		conn = TestUTCommon.createNewConnectionForTest();
		try {
			ArrayList<Map> result = (ArrayList<Map>) conn.getDataAllFields(null);
		} catch (AssertionError ae) {
			assertTrue(ae.getMessage().contains("Can not get data ! Collection is null or empty string"));
			throw ae;
		}
	}

	/**
	 * Check exception handle when collection is empty
	 */
	@Test(expected = AssertionError.class)
	public void getDataAllFields_collection_003() {
		conn = TestUTCommon.createNewConnectionForTest();
		try {
			ArrayList<Map> result = (ArrayList<Map>) conn.getDataAllFields("");
		} catch (AssertionError ae) {
			assertTrue(ae.getMessage().contains("Can not get data ! Collection is null or empty string"));
			throw ae;
		}
	}

	/**
	 * Check exception handle when collection is space character
	 */
	@Test(expected = AssertionError.class)
	public void getDataAllFields_collection_004() {
		conn = TestUTCommon.createNewConnectionForTest();
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
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void getDataAllFields_collection_005() throws FileNotFoundException {
		try {
			FileReader reader = new FileReader(new File("tmp\\datatest\\CMongoDBconn\\getDataAllFields_collection_005.txt"));

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			ArrayList<Map> expectedList = gson.fromJson(reader, ArrayList.class);
			ArrayList<DBObject> listObject = new ArrayList<DBObject>();
			for (Map map : expectedList) {
				DBObject obj = new BasicDBObject(map);
				listObject.add(obj);
			}

			conn = TestUTCommon.createNewConnectionForTest();
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
