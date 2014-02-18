/**
 * copied from CMongoDBConn_UT_08.java
 * for mongodb without auth option
 */
package org.wiperdog.custommongodbconnection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assume.*;
import org.wiperdog.lib.BaseCMongoDBConnTestcase;
import org.wiperdog.lib.TestUTCommon;

import com.gmongo.GMongo;
import com.mongodb.DB;
import com.mongodb.MongoException;

/**
 * Testcase for method getConnection(host, port, dbname, userName, password)
 *
 * @author luvina
 *
 */
public class TestCMongoDBConn_08 {
	private boolean mongdbpresent = true;
	private final String [] args = []
	String collection = "MySQL.Database_Area.InnoDBTablespace_Free";
	String istIid = "localhost-@MYSQL-information_schema";
	String param_file_path_local = "tmp/conf_Local.params";
	CMongoDBConn conn;
	public TestCMongoDBConn_08() {
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
	 * Check getting type of Store job
	 * Expected: Got string "Store"
	 */
	@Test
	public void getDataType_001() {
		String result = (String) conn.getDataType("MySQL.Database_Area.InnoDBTablespace_Free.localhost-@MYSQL-information_schema");
		assertEquals("Store", result);
	}

	/**
	 * Check getting type of Subtyped job
	 * Expected: Got string "Subtyped"
	 */
	@Test
	public void getDataType_002() {
		String result = (String) conn.getDataType("MySQL.Database_Area.Top30Database.localhost-@MYSQL-information_schema");
		assertEquals("Subtyped", result);
	}

	/**
	 * Check exception handle when collection is null
	 * Expected: AssertionError with message "Can not get data ! Collection is null or empty string"
	 */
	@Test(expected = AssertionError.class)
	public void getDataType_003() {
		try {
			conn.getDataType(null);
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
	public void getDataType_004() {
		try {
			conn.getDataType("");
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
	public void getDataType_005() {
		try {
			conn.getDataType(" ");
		} catch (AssertionError ae) {
			assertTrue(ae.getMessage().contains("Can not get data ! Collection is null or empty string"));
			throw ae;
		}
	}

	/**
	 * Check getting type normally when collection has space character at begin
	 * and end
	 * Expected: Got string "Store"
	 */
	@Test
	public void getDataType_006() {
		String result = (String) conn.getDataType(" MySQL.Database_Area.InnoDBTablespace_Free.localhost-@MYSQL-information_schema ");
		assertEquals("Store", result);
	}

}
