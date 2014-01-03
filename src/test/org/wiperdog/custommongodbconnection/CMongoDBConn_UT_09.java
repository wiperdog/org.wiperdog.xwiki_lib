package org.wiperdog.custommongodbconnection;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
 * Testcase for method getDataInPeriod(collection, fromDate, toDate, limit, istIid)
 * @author luvina
 *
 */
public class CMongoDBConn_UT_09 extends BaseCMongoDBConnTestcase {

	/**
	 * Check getting data with valid params
	 * Return 10 record from fromDate to toDate
	 * limit = 10
	 * records = 20
	 * @throws FileNotFoundException
	 * @throws ParseException
	 */
	@Test
	public void getDataInPeriod_001() throws FileNotFoundException, ParseException {
		// Get data test and insert into mongodb
		FileReader reader = new FileReader(new File("tmp\\datatest\\CmongoDBconn\\getDataInPeriod_001-006.txt"));

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

		// Get expected result as first 10 records
		ArrayList<Map> expected = new ArrayList<Map>();
		for (int i = 0; i < 10; i++) {
			expected.add(datatest.get(i));
		}

		// Prepare limit, fromDate, toDate
		int limit = 10;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat sdfout = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// fromDateStr is before first record's time 1 second
		Date fromDate = sdf.parse((String) datatest.get(0).get("fetchAt"));
		fromDate.setTime(fromDate.getTime() - 1000);
		String fromDateStr = sdfout.format(fromDate);

		// toDateStr is after last record's time 1 second
		Date toDate = sdf.parse((String) datatest.get(9).get("fetchAt"));
		toDate.setTime(toDate.getTime() + 1000);
		String toDateStr = sdfout.format(toDate);

		// Get result and remove _id element that automatically add by mongodb
		// driver
		List<Map> result = (List<Map>) conn.getDataInPeriod(collection, fromDateStr, toDateStr, limit, istIid);
		for (Map map : result) {
			map.remove("_id");
		}

		// Max size is variable limit
		assertTrue(result.size() <= limit);

		// check order by fetchAt
		assertTrue(TestUTCommon.compare2object(expected, result));
	}

	/**
	 * Check getting 10 newest record from beginning to toDate
	 * limit : 10
	 * records : 20
	 * @throws FileNotFoundException
	 * @throws ParseException
	 */
	@Test
	public void getDataInPeriod_002() throws FileNotFoundException, ParseException {
		// Get data test and insert into mongodb
		FileReader reader = new FileReader(new File("tmp\\datatest\\CmongoDBconn\\getDataInPeriod_001-006.txt"));

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

		// Get expected result as newest 10 records
		ArrayList<Map> expected = new ArrayList<Map>();
		for (int i = 10; i < 20; i++) {
			expected.add(datatest.get(i));
		}

		// Prepare limit, fromDate, toDate
		int limit = 10;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat sdfout = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// fromDateStr is empty
		String fromDateStr = "";

		// toDateStr is after 10th record's time 1 second
		Date toDate = sdf.parse((String) expected.get(9).get("fetchAt"));
		toDate.setTime(toDate.getTime() + 1000);
		String toDateStr = sdfout.format(toDate);

		// Get result and remove _id element that automatically add by mongodb
		// driver
		List<Map> result = (List<Map>) conn.getDataInPeriod(collection, fromDateStr, toDateStr, limit, istIid);
		for (Map map : result) {
			map.remove("_id");
		}

		// Max size is variable limit
		assertTrue(result.size() <= limit);

		// check order by fetchAt
		assertTrue(TestUTCommon.compare2object(expected, result));
	}

	/**
	 * Check getting newest data from fromDate to end of table
	 * limit : 10
	 * records : 20
	 * @throws ParseException
	 * @throws FileNotFoundException
	 */
	@Test
	public void getDataInPeriod_003() throws ParseException, FileNotFoundException {
		// Get data test and insert into mongodb
		FileReader reader = new FileReader(new File("tmp\\datatest\\CmongoDBconn\\getDataInPeriod_001-006.txt"));

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

		// Get expected result as first 10 records
		ArrayList<Map> expected = new ArrayList<Map>();
		for (int i = 10; i < 20; i++) {
			expected.add(datatest.get(i));
		}

		// Prepare limit, fromDate, toDate
		int limit = 10;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat sdfout = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// fromDateStr is before first record's time 1 second
		Date fromDate = sdf.parse((String) expected.get(0).get("fetchAt"));
		fromDate.setTime(fromDate.getTime() - 1000);
		String fromDateStr = sdfout.format(fromDate);

		// toDateStr is empty
		String toDateStr = "";

		// Get result and remove _id element that automatically add by mongodb
		// driver
		List<Map> result = (List<Map>) conn.getDataInPeriod(collection, fromDateStr, toDateStr, limit, istIid);
		for (Map map : result) {
			map.remove("_id");
		}

		// Max size is variable limit
		assertTrue(result.size() <= limit);

		// check order by fetchAt
		assertTrue(TestUTCommon.compare2object(expected, result));
	}

	/**
	 * Check getting data newest data with records < limit
	 * limit : 30
	 * records : 20
	 * @throws ParseException
	 * @throws FileNotFoundException
	 */
	@Test
	public void getDataInPeriod_004() throws ParseException, FileNotFoundException {
		// Get data test and insert into mongodb
		FileReader reader = new FileReader(new File("tmp\\datatest\\CmongoDBconn\\getDataInPeriod_001-006.txt"));

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

		// Get expected result as first 10 records
		ArrayList<Map> expected = new ArrayList<Map>();
		expected.addAll(datatest);

		// Prepare limit, fromDate, toDate
		int limit = 30;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat sdfout = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// fromDateStr is before first record's time 1 second
		Date fromDate = sdf.parse((String) expected.get(0).get("fetchAt"));
		fromDate.setTime(fromDate.getTime() - 1000);
		String fromDateStr = sdfout.format(fromDate);

		// toDateStr is empty
		String toDateStr = "";

		// Get result and remove _id element that automatically add by mongodb
		// driver
		List<Map> result = (List<Map>) conn.getDataInPeriod(collection, fromDateStr, toDateStr, limit, istIid);
		for (Map map : result) {
			map.remove("_id");
		}

		// Max size is variable limit
		assertTrue(result.size() <= limit);

		// check order by fetchAt
		assertTrue(TestUTCommon.compare2object(expected, result));
	}

	/**
	 * Check getting 0 record when limit = 0
	 * @throws FileNotFoundException
	 * @throws ParseException
	 */
	@Test
	public void getDataInPeriod_005() throws FileNotFoundException, ParseException {
		// Get data test and insert into mongodb
		FileReader reader = new FileReader(new File("tmp\\datatest\\CmongoDBconn\\getDataInPeriod_001-006.txt"));

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

		// Prepare limit, fromDate, toDate
		int limit = 0;

		// fromDateStr is empty
		String fromDateStr = "";

		// toDateStr is empty
		String toDateStr = "";

		// Get result and remove _id element that automatically add by mongodb
		// driver
		List<Map> result = (List<Map>) conn.getDataInPeriod(collection, fromDateStr, toDateStr, limit, istIid);

		// Size is 0
		assertTrue(result.size() == limit);
	}

	/**
	 * Check getting 0 record when limit < 0
	 * @throws FileNotFoundException
	 */
	@Test
	public void getDataInPeriod_006() throws FileNotFoundException {
		// Get data test and insert into mongodb
		FileReader reader = new FileReader(new File("tmp\\datatest\\CmongoDBconn\\getDataInPeriod_001-006.txt"));

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

		// Prepare limit, fromDate, toDate
		int limit = -1;

		// fromDateStr is empty
		String fromDateStr = "";

		// toDateStr is empty
		String toDateStr = "";

		// Get result and remove _id element that automatically add by mongodb
		// driver
		List<Map> result = (List<Map>) conn.getDataInPeriod(collection, fromDateStr, toDateStr, limit, istIid);

		// Size is 0
		assertTrue(result.size() == 0);
	}

	/**
	 * Check get data at border point fromDate < fetchAt < toDate
	 * return 8 records except first and last record
	 * fromDate = firstRecord's time
	 * toDate = lastRecord's time
	 * @throws FileNotFoundException
	 * @throws ParseException
	 */
	@Test
	public void getDataInPeriod_007() throws FileNotFoundException, ParseException {
		// Get data test and insert into mongodb
		FileReader reader = new FileReader(new File("tmp\\datatest\\CmongoDBconn\\getDataInPeriod_007-027.txt"));

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

		// Get expected result as 8 records except first and last record
		ArrayList<Map> expected = new ArrayList<Map>();
		for (int i = 1; i < 9; i++) {
			expected.add(datatest.get(i));
		}

		// Prepare limit, fromDate, toDate
		int limit = 10;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat sdfout = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// fromDateStr is first record's time
		Date fromDate = sdf.parse((String) datatest.get(0).get("fetchAt"));
		String fromDateStr = sdfout.format(fromDate);

		// toDateStr is last record's time
		Date toDate = sdf.parse((String) datatest.get(9).get("fetchAt"));
		String toDateStr = sdfout.format(toDate);

		// Get result and remove _id element that automatically add by mongodb
		// driver
		List<Map> result = (List<Map>) conn.getDataInPeriod(collection, fromDateStr, toDateStr, limit, istIid);
		for (Map map : result) {
			map.remove("_id");
		}

		// Size is 8 records
		assertTrue(result.size() == 8);

		// check order by fetchAt
		assertTrue(TestUTCommon.compare2object(expected, result));
	}

	/**
	 * Check getting data at border point
	 * Return 9 records except lastRecord
	 * fromDate = firstRecord's time - 1 (Include first record in result)
	 * toDate = lastRecord's time
	 * @throws ParseException
	 * @throws FileNotFoundException
	 */
	@Test
	public void getDataInPeriod_008() throws ParseException, FileNotFoundException {
		// Get data test and insert into mongodb
		FileReader reader = new FileReader(new File("tmp\\datatest\\CmongoDBconn\\getDataInPeriod_007-027.txt"));

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

		// Get expected result 9 records except last record
		ArrayList<Map> expected = new ArrayList<Map>();
		for (int i = 0; i < 9; i++) {
			expected.add(datatest.get(i));
		}

		// Prepare limit, fromDate, toDate
		int limit = 10;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat sdfout = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// fromDateStr is before first record's time 1 second
		Date fromDate = sdf.parse((String) datatest.get(0).get("fetchAt"));
		fromDate.setTime(fromDate.getTime() - 1000);
		String fromDateStr = sdfout.format(fromDate);

		// toDateStr is last record's time
		Date toDate = sdf.parse((String) datatest.get(9).get("fetchAt"));
		String toDateStr = sdfout.format(toDate);

		// Get result and remove _id element that automatically add by mongodb
		// driver
		List<Map> result = (List<Map>) conn.getDataInPeriod(collection, fromDateStr, toDateStr, limit, istIid);
		for (Map map : result) {
			map.remove("_id");
		}

		// Size is 9
		assertTrue(result.size() == 9);

		// check order by fetchAt
		assertTrue(TestUTCommon.compare2object(expected, result));
	}

	/**
	 * Check getting data at border point
	 * Return 8 records except firstRecord and lastRecord
	 * fromDate = firstRecord's time + 1 (Exclude first record in result)
	 * toDate = lastRecord's time
	 * @throws ParseException
	 * @throws FileNotFoundException
	 */
	@Test
	public void getDataInPeriod_009() throws ParseException, FileNotFoundException {
		// Get data test and insert into mongodb
		FileReader reader = new FileReader(new File("tmp\\datatest\\CmongoDBconn\\getDataInPeriod_007-027.txt"));

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

		// Get expected result 8 records except first and last record
		ArrayList<Map> expected = new ArrayList<Map>();
		for (int i = 1; i < 9; i++) {
			expected.add(datatest.get(i));
		}

		// Prepare limit, fromDate, toDate
		int limit = 10;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat sdfout = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// fromDateStr is after first record's time 1 second
		Date fromDate = sdf.parse((String) datatest.get(0).get("fetchAt"));
		fromDate.setTime(fromDate.getTime() + 1000);
		String fromDateStr = sdfout.format(fromDate);

		// toDateStr is last record's time
		Date toDate = sdf.parse((String) datatest.get(9).get("fetchAt"));
		String toDateStr = sdfout.format(toDate);

		// Get result and remove _id element that automatically add by mongodb
		// driver
		List<Map> result = (List<Map>) conn.getDataInPeriod(collection, fromDateStr, toDateStr, limit, istIid);
		for (Map map : result) {
			map.remove("_id");
		}

		// Size is 8
		assertTrue(result.size() == 8);

		// check order by fetchAt
		assertTrue(TestUTCommon.compare2object(expected, result));
	}

	/**
	 * Check getting data at border point
	 * Return 8 records except firstRecord and lastRecord
	 * fromDate = firstRecord's time (Exclude first record in result)
	 * toDate = lastRecord's time - 1 (Exclude last record in result)
	 * @throws ParseException
	 * @throws FileNotFoundException
	 */
	@Test
	public void getDataInPeriod_010() throws ParseException, FileNotFoundException {
		// Get data test and insert into mongodb
		FileReader reader = new FileReader(new File("tmp\\datatest\\CmongoDBconn\\getDataInPeriod_007-027.txt"));

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

		// Get expected result 8 records except first and last record
		ArrayList<Map> expected = new ArrayList<Map>();
		for (int i = 1; i < 9; i++) {
			expected.add(datatest.get(i));
		}

		// Prepare limit, fromDate, toDate
		int limit = 10;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat sdfout = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// fromDateStr is first record's time
		Date fromDate = sdf.parse((String) datatest.get(0).get("fetchAt"));
		String fromDateStr = sdfout.format(fromDate);

		// toDateStr is before last record's time 1 second
		Date toDate = sdf.parse((String) datatest.get(9).get("fetchAt"));
		toDate.setTime(toDate.getTime() - 1000);
		String toDateStr = sdfout.format(toDate);

		// Get result and remove _id element that automatically add by mongodb
		// driver
		List<Map> result = (List<Map>) conn.getDataInPeriod(collection, fromDateStr, toDateStr, limit, istIid);
		for (Map map : result) {
			map.remove("_id");
		}

		// Size is 8
		assertTrue(result.size() == 8);

		// check order by fetchAt
		assertTrue(TestUTCommon.compare2object(expected, result));
	}

	/**
	 * Check getting data at border point
	 * Return 9 records except firstRecord
	 * fromDate = firstRecord's time (Exclude first record in result)
	 * toDate = lastRecord's time + 1 (Include last record in result)
	 * @throws ParseException
	 * @throws FileNotFoundException
	 */
	@Test
	public void getDataInPeriod_011() throws ParseException, FileNotFoundException {
		// Get data test and insert into mongodb
		FileReader reader = new FileReader(new File("tmp\\datatest\\CmongoDBconn\\getDataInPeriod_007-027.txt"));

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

		// Get expected result 9 records except first record
		ArrayList<Map> expected = new ArrayList<Map>();
		for (int i = 1; i < 10; i++) {
			expected.add(datatest.get(i));
		}

		// Prepare limit, fromDate, toDate
		int limit = 10;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat sdfout = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// fromDateStr is first record's time
		Date fromDate = sdf.parse((String) datatest.get(0).get("fetchAt"));
		String fromDateStr = sdfout.format(fromDate);

		// toDateStr is before last record's time 1 second
		Date toDate = sdf.parse((String) datatest.get(9).get("fetchAt"));
		toDate.setTime(toDate.getTime() + 1000);
		String toDateStr = sdfout.format(toDate);

		// Get result and remove _id element that automatically add by mongodb
		// driver
		List<Map> result = (List<Map>) conn.getDataInPeriod(collection, fromDateStr, toDateStr, limit, istIid);
		for (Map map : result) {
			map.remove("_id");
		}

		// Size is 9
		assertTrue(result.size() == 9);

		// check order by fetchAt
		assertTrue(TestUTCommon.compare2object(expected, result));
	}

	/**
	 * Check getting data at border point
	 * Return 8 records except firstRecord and lastRecord
	 * fromDate = firstRecord's time + 1 (Exclude first record in result)
	 * toDate = lastRecord's time - 1 (Exclude last record in result)
	 * @throws ParseException
	 * @throws FileNotFoundException
	 */
	@Test
	public void getDataInPeriod_012() throws ParseException, FileNotFoundException {
		// Get data test and insert into mongodb
		FileReader reader = new FileReader(new File("tmp\\datatest\\CmongoDBconn\\getDataInPeriod_007-027.txt"));

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

		// Get expected result 8 records except first and last record
		ArrayList<Map> expected = new ArrayList<Map>();
		for (int i = 1; i < 9; i++) {
			expected.add(datatest.get(i));
		}

		// Prepare limit, fromDate, toDate
		int limit = 10;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat sdfout = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// fromDateStr is first record's time
		Date fromDate = sdf.parse((String) datatest.get(0).get("fetchAt"));
		fromDate.setTime(fromDate.getTime() + 1000);
		String fromDateStr = sdfout.format(fromDate);

		// toDateStr is before last record's time 1 second
		Date toDate = sdf.parse((String) datatest.get(9).get("fetchAt"));
		toDate.setTime(toDate.getTime() - 1000);
		String toDateStr = sdfout.format(toDate);

		// Get result and remove _id element that automatically add by mongodb
		// driver
		List<Map> result = (List<Map>) conn.getDataInPeriod(collection, fromDateStr, toDateStr, limit, istIid);
		for (Map map : result) {
			map.remove("_id");
		}

		// Size is 8
		assertTrue(result.size() == 8);

		// check order by fetchAt
		assertTrue(TestUTCommon.compare2object(expected, result));
	}

	/**
	 * Check getting 0 record when collection doesn't exist
	 */
	@Test
	public void getDataInPeriod_013() {
		List<Map> result = (List<Map>) conn.getDataInPeriod("NotInDB", "", "", 10, istIid);
		assertTrue(result.size() == 0);
	}

	/**
	 * Check exception handle when collection is null
	 */
	@Test(expected = AssertionError.class)
	public void getDataInPeriod_014() {
		try {
			List<Map> result = (List<Map>) conn.getDataInPeriod(null, "", "", 10, istIid);
		} catch (AssertionError ae) {
			assertTrue(ae.getMessage().contains("Can not get data ! Collection is null or empty string"));
			throw ae;
		}
	}

	/**
	 * Check exception handle when collection is empty
	 */
	@Test(expected = AssertionError.class)
	public void getDataInPeriod_015() {
		try {
			List<Map> result = (List<Map>) conn.getDataInPeriod("", "", "", 10, istIid);
		} catch (AssertionError ae) {
			assertTrue(ae.getMessage().contains("Can not get data ! Collection is null or empty string"));
			throw ae;
		}
	}

	/**
	 * Check exception handle when collection is space character
	 */
	@Test(expected = AssertionError.class)
	public void getDataInPeriod_016() {
		try {
			List<Map> result = (List<Map>) conn.getDataInPeriod(" ", "", "", 10, istIid);
		} catch (AssertionError ae) {
			assertTrue(ae.getMessage().contains("Can not get data ! Collection is null or empty string"));
			throw ae;
		}
	}

	/**
	 * Check getting data normally when collection has space character at begin and end
	 * @throws FileNotFoundException
	 * @throws ParseException
	 */
	@Test
	public void getDataInPeriod_017() throws FileNotFoundException, ParseException {
		// Get data test and insert into mongodb
		FileReader reader = new FileReader(new File("tmp\\datatest\\CmongoDBconn\\getDataInPeriod_007-027.txt"));

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

		// Get expected result as all 10 records
		ArrayList<Map> expected = new ArrayList<Map>();
		expected.addAll(datatest);

		// Prepare limit, fromDate, toDate
		int limit = 10;

		String fromDateStr = "";

		String toDateStr = "";

		// Get result and remove _id element that automatically add by mongodb
		// driver
		List<Map> result = (List<Map>) conn.getDataInPeriod(" " + collection + " ", fromDateStr, toDateStr, limit, istIid);
		for (Map map : result) {
			map.remove("_id");
		}

		// Max size is variable limit
		assertTrue(result.size() <= limit);

		// check order by fetchAt
		assertTrue(TestUTCommon.compare2object(expected, result));
	}

	/**
	 * Check getting 0 record when istIid doesn't exist
	 */
	@Test
	public void getDataInPeriod_018() {
		List<Map> result = (List<Map>) conn.getDataInPeriod(collection, "", "", 10, "NotInDB");
		assertTrue(result.size() == 0);
	}

	/**
	 * Check exception handle when istIid is null
	 */
	@Test(expected = AssertionError.class)
	public void getDataInPeriod_019() {
		try {
			List<Map> result = (List<Map>) conn.getDataInPeriod(collection, "", "", 10, null);
		} catch (AssertionError ae) {
			assertTrue(ae.getMessage().contains("Can not get data ! Collection is null or empty string"));
			throw ae;
		}
	}

	/**
	 * Check exception handle when istIid is empty
	 */
	@Test(expected = AssertionError.class)
	public void getDataInPeriod_020() {
		try {
			List<Map> result = (List<Map>) conn.getDataInPeriod(collection, "", "", 10, "");
		} catch (AssertionError ae) {
			assertTrue(ae.getMessage().contains("Can not get data ! Collection is null or empty string"));
			throw ae;
		}
	}

	/**
	 * Check exception handle when istIid is space character
	 */
	@Test(expected = AssertionError.class)
	public void getDataInPeriod_021() {
		try {
			List<Map> result = (List<Map>) conn.getDataInPeriod(collection, "", "", 10, " ");
		} catch (AssertionError ae) {
			assertTrue(ae.getMessage().contains("Can not get data ! Collection is null or empty string"));
			throw ae;
		}
	}

	/**
	 * Check exception handle when fromDate is null
	 */
	@Test(expected = AssertionError.class)
	public void getDataInPeriod_022() {
		try {
			List<Map> result = (List<Map>) conn.getDataInPeriod(collection, null, "", 10, istIid);
		} catch (AssertionError ae) {
			assertTrue(ae.getMessage().contains("Can not get data ! 'From Date' or 'To date' is null !"));
			throw ae;
		}
	}

	/**
	 * Check getting data normally when fromDate is space character
	 * @throws FileNotFoundException
	 */
	@Test
	public void getDataInPeriod_023() throws FileNotFoundException {
		// Get data test and insert into mongodb
		FileReader reader = new FileReader(new File("tmp\\datatest\\CmongoDBconn\\getDataInPeriod_007-027.txt"));

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

		// Get expected result as all 10 records
		ArrayList<Map> expected = new ArrayList<Map>();
		expected.addAll(datatest);

		// Prepare limit, fromDate, toDate
		int limit = 10;

		String fromDateStr = " ";

		String toDateStr = "";

		// Get result and remove _id element that automatically add by mongodb
		// driver
		List<Map> result = (List<Map>) conn.getDataInPeriod(collection, fromDateStr, toDateStr, limit, istIid);
		for (Map map : result) {
			map.remove("_id");
		}

		// Max size is variable limit
		assertTrue(result.size() <= limit);

		// check order by fetchAt
		assertTrue(TestUTCommon.compare2object(expected, result));
	}

	/**
	 * Check exception handle when toDate is null
	 */
	@Test(expected = AssertionError.class)
	public void getDataInPeriod_024() {
		try {
			List<Map> result = (List<Map>) conn.getDataInPeriod(collection, "", null, 10, istIid);
		} catch (AssertionError ae) {
			assertTrue(ae.getMessage().contains("Can not get data ! 'From Date' or 'To date' is null !"));
			throw ae;
		}
	}

	/**
	 * Check getting data normally when toDate is space character
	 * @throws FileNotFoundException
	 */
	@Test
	public void getDataInPeriod_025() throws FileNotFoundException {
		// Get data test and insert into mongodb
		FileReader reader = new FileReader(new File("tmp\\datatest\\CmongoDBconn\\getDataInPeriod_007-027.txt"));

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

		// Get expected result as all 10 records
		ArrayList<Map> expected = new ArrayList<Map>();
		expected.addAll(datatest);

		// Prepare limit, fromDate, toDate
		int limit = 10;

		String fromDateStr = "";

		String toDateStr = " ";

		// Get result and remove _id element that automatically add by mongodb
		// driver
		List<Map> result = (List<Map>) conn.getDataInPeriod(collection, fromDateStr, toDateStr, limit, istIid);
		for (Map map : result) {
			map.remove("_id");
		}

		// Max size is variable limit
		assertTrue(result.size() <= limit);

		// check order by fetchAt
		assertTrue(TestUTCommon.compare2object(expected, result));
	}

	/**
	 * Check exception handle when fromDate and toDate are null
	 */
	@Test(expected=AssertionError.class)
	public void getDataInPeriod_026() {
		try {
			List<Map> result = (List<Map>) conn.getDataInPeriod(collection, null, null, 10, istIid);
		} catch (AssertionError ae) {
			assertTrue(ae.getMessage().contains("Can not get data ! 'From Date' or 'To date' is null !"));
			throw ae;
		}
	}

	/**
	 * Check getting data normally when fromDate and toDate are space character
	 * @throws FileNotFoundException
	 */
	@Test
	public void getDataInPeriod_027() throws FileNotFoundException {
		// Get data test and insert into mongodb
		FileReader reader = new FileReader(new File("tmp\\datatest\\CmongoDBconn\\getDataInPeriod_007-027.txt"));

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

		// Get expected result as all 10 records
		ArrayList<Map> expected = new ArrayList<Map>();
		expected.addAll(datatest);

		// Prepare limit, fromDate, toDate
		int limit = 10;

		String fromDateStr = " ";

		String toDateStr = " ";

		// Get result and remove _id element that automatically add by mongodb
		// driver
		List<Map> result = (List<Map>) conn.getDataInPeriod(collection, fromDateStr, toDateStr, limit, istIid);
		for (Map map : result) {
			map.remove("_id");
		}

		// Max size is variable limit
		assertTrue(result.size() <= limit);

		// check order by fetchAt
		assertTrue(TestUTCommon.compare2object(expected, result));
	}

}
