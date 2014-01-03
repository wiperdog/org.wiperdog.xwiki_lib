package org.wiperdog.custommongodbconnection;

import org.junit.Test;
import org.wiperdog.lib.BaseCMongoDBConnTestcase;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Testcase for method getDataType(collection)
 * @author luvina
 *
 */
public class CMongoDBConn_UT_08 extends BaseCMongoDBConnTestcase {

	/**
	 * Check getting type of Store job
	 */
	@Test
	public void getDataType_001() {
		String result = (String) conn.getDataType("MySQL.Database_Area.InnoDBTablespace_Free.localhost-@MYSQL-information_schema");
		assertEquals("Store", result);
	}

	/**
	 * Check getting type of Subtyped job
	 */
	@Test
	public void getDataType_002() {
		String result = (String) conn.getDataType("MySQL.Database_Area.Top30Database.localhost-@MYSQL-information_schema");
		assertEquals("Subtyped", result);
	}

	/**
	 * Check exception handle when collection is null
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
	 */
	@Test
	public void getDataType_006() {
		String result = (String) conn.getDataType(" MySQL.Database_Area.InnoDBTablespace_Free.localhost-@MYSQL-information_schema ");
		assertEquals("Store", result);
	}

}
