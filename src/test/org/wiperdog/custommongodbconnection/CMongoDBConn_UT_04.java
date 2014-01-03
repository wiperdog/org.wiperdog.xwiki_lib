package org.wiperdog.custommongodbconnection;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Map;

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
 * Testcase for method getDataAllFields(collection, limit, istIid)
 * @author luvina
 *
 */
public class CMongoDBConn_UT_04 extends BaseCMongoDBConnTestcase {

	/**
	 * Check getting data with valid params 5 records limit 10
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void getDataAllFields3_params_001() throws FileNotFoundException {
		try {
			FileReader reader = new FileReader(new File("tmp\\datatest\\CMongoDBconn\\getDataAllFields3_params_001.txt"));

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
			ArrayList<Map> result = (ArrayList<Map>) conn.getDataAllFields(collection, 10, istIid);
			for (Map res : result) {
				res.remove("_id");
			}
			boolean isEquals = TestUTCommon.compare2object(expectedList, result);

			assertTrue(isEquals);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Check getting data with valid params 20 records limit 10
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void getDataAllFields3_params_002() throws FileNotFoundException {
		try {
			FileReader reader = new FileReader(new File("tmp\\datatest\\CMongoDBconn\\getDataAllFields3_params_002.txt"));

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			ArrayList<Map> datatest = gson.fromJson(reader, ArrayList.class);
			ArrayList<DBObject> listObject = new ArrayList<DBObject>();
			for (Map map : datatest) {
				DBObject obj = new BasicDBObject(map);
				listObject.add(obj);
			}

			DB db = (DB) conn.getDb();
			db.getCollection(collection + "." + istIid).drop();
			db.getCollection(collection + "." + istIid).insert(listObject);

			// Get 10 last records as the expected result
			ArrayList<Map> expectedList = new ArrayList<Map>();
			for (int i = 10; i < datatest.size(); i++) {
				expectedList.add(datatest.get(i));
			}

			// Get data and remove the _id element that auto inserted by mongoDB
			ArrayList<Map> result = (ArrayList<Map>) conn.getDataAllFields(collection, 10, istIid);
			for (Map res : result) {
				res.remove("_id");
			}
			boolean isEquals = TestUTCommon.compare2object(expectedList, result);

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
	public void getDataAllFields3_params_003() {
		DB db = (DB) conn.getDb();
		ArrayList<Map> result = (ArrayList<Map>) conn.getDataAllFields(collection, 0, istIid);
		assertTrue(result.size() == 0);
	}

	/**
	 * Check getting 0 record when limit < 0
	 */
	@Test
	public void getDataAllFields3_params_004() {
		DB db = (DB) conn.getDb();
		ArrayList<Map> result = (ArrayList<Map>) conn.getDataAllFields(collection, -1, istIid);
		assertTrue(result.size() == 0);
	}

	/**
	 * Check exception handle when collection is null
	 */
	@Test(expected = AssertionError.class)
	public void getDataAllFields3_params_005() {
		DB db = (DB) conn.getDb();
		try {
			ArrayList<Map> result = (ArrayList<Map>) conn.getDataAllFields(null, 10, istIid);
		} catch (AssertionError ae) {
			assertTrue(ae.getMessage().contains("Can not get data ! Collection is null or empty string"));
			throw ae;
		}
	}

	/**
	 * Check exception handle when collection is empty
	 */
	@Test(expected = AssertionError.class)
	public void getDataAllFields3_params_006() {
		DB db = (DB) conn.getDb();
		try {
			ArrayList<Map> result = (ArrayList<Map>) conn.getDataAllFields("", 10, istIid);
		} catch (AssertionError ae) {
			assertTrue(ae.getMessage().contains("Can not get data ! Collection is null or empty string"));
			throw ae;
		}
	}

	/**
	 * Check exception handle when collection is space character
	 */
	@Test(expected = AssertionError.class)
	public void getDataAllFields3_params_007() {
		DB db = (DB) conn.getDb();
		try {
			ArrayList<Map> result = (ArrayList<Map>) conn.getDataAllFields(" ", 10, istIid);
		} catch (AssertionError ae) {
			assertTrue(ae.getMessage().contains("Can not get data ! Collection is null or empty string"));
			throw ae;
		}
	}

	/**
	 * Check exception handle when limit is null
	 */
	@Test(expected = AssertionError.class)
	public void getDataAllFields3_params_008() {
		DB db = (DB) conn.getDb();
		try {
			ArrayList<Map> result = (ArrayList<Map>) conn.getDataAllFields(collection, null, istIid);
		} catch (AssertionError ae) {
			assertTrue(ae.getMessage().contains("Can not get data ! Limit is null"));
			throw ae;
		}
	}

	/**
	 * Check exception handle when IstIid is null
	 */
	@Test(expected = AssertionError.class)
	public void getDataAllFields3_params_009() {
		DB db = (DB) conn.getDb();
		try {
			ArrayList<Map> result = (ArrayList<Map>) conn.getDataAllFields(collection, 10, null);
		} catch (AssertionError ae) {
			assertTrue(ae.getMessage().contains("Can not get data ! IstIid is null or empty string"));
			throw ae;
		}
	}

	/**
	 * Check exception handle when IstIid is empty
	 */
	@Test(expected = AssertionError.class)
	public void getDataAllFields3_params_010() {
		DB db = (DB) conn.getDb();
		try {
			ArrayList<Map> result = (ArrayList<Map>) conn.getDataAllFields(collection, 10, "");
		} catch (AssertionError ae) {
			assertTrue(ae.getMessage().contains("Can not get data ! IstIid is null or empty string"));
			throw ae;
		}
	}

	/**
	 * Check exception handle when IstIid is space character
	 */
	@Test(expected = AssertionError.class)
	public void getDataAllFields3_params_011() {
		DB db = (DB) conn.getDb();
		try {
			ArrayList<Map> result = (ArrayList<Map>) conn.getDataAllFields(collection, 10, " ");
		} catch (AssertionError ae) {
			assertTrue(ae.getMessage().contains("Can not get data ! IstIid is null or empty string"));
			throw ae;
		}
	}

	/**
	 * Check getting 0 record when collection doesn't exist
	 */
	@Test
	public void getDataAllFields3_params_012() {
		DB db = (DB) conn.getDb();
		ArrayList<Map> result = (ArrayList<Map>) conn.getDataAllFields("MySQL.Database_Area.NotInDB", 10, istIid);
		assertTrue(result.size() == 0);
	}

	/**
	 * Check getting 0 record when istIid doesn't exist
	 */
	@Test
	public void getDataAllFields3_params_013() {
		DB db = (DB) conn.getDb();
		ArrayList<Map> result = (ArrayList<Map>) conn.getDataAllFields(collection, 10, "NotInDB");
		assertTrue(result.size() == 0);
	}

	/**
	 * Check getting data normally with collection and istIid has space
	 * character at begin and end
	 */
	@Test
	public void getDataAllFields3_params_014() {
		try {
			FileReader reader = new FileReader(new File("tmp\\datatest\\CMongoDBconn\\getDataAllFields3_params_014.txt"));

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
			ArrayList<Map> result = (ArrayList<Map>) conn.getDataAllFields(" " + collection + " ", 10, " " + istIid + " ");
			for (Map res : result) {
				res.remove("_id");
			}
			boolean isEquals = TestUTCommon.compare2object(expectedList, result);

			assertTrue(isEquals);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
