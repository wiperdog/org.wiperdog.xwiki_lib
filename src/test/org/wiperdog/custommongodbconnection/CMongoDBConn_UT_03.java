package org.wiperdog.custommongodbconnection;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.wiperdog.lib.BaseCMongoDBConnTestcase;
import org.wiperdog.lib.TestUTCommon;

/**
 * Testcase for method getConnection(String param_file_path)
 * @author luvina
 *
 */
public class CMongoDBConn_UT_03 extends BaseCMongoDBConnTestcase {

	/**
	 * Check remote connect with valid param's file path
	 */
	@Test
	public void getConnectionStringFilePath001() {
		try {
			CMongoDBConn conn = new CMongoDBConn();
			Object ret = conn.getConnection(param_file_path);
			assertTrue(ret instanceof com.gmongo.GMongo);
			assertTrue(conn.getDb() != null && conn.getDb() instanceof com.mongodb.DB);
			conn.closeConnection();
		} catch (Exception e) {
			fail("Cannot create connection to Mongodb:\n" + e);
		}
	}

	/**
	 * Check local connect with valid param's file path
	 */
	@Test
	public void getConnectionStringFilePath002() {
		// Need start mongoDB at local host port 27000 first
		CMongoDBConn conn = new CMongoDBConn();
		Object ret = conn.getConnection(param_file_path_local);
		assertTrue(ret instanceof com.gmongo.GMongo);
		assertTrue(conn.getDb() != null && conn.getDb() instanceof com.mongodb.DB);
		conn.closeConnection();
	}

	/**
	 * Check exception handle with invalid param's file's path
	 */
	@Test(expected = Exception.class)
	public void getConnectionStringFilePath003() {
		CMongoDBConn conn = new CMongoDBConn();
		Object ret = conn.getConnection(param_file_path + "wrong");
		conn.closeConnection();
	}

	/**
	 * Check exception handle with empty param's file's path
	 */
	@Test(expected = Exception.class)
	public void getConnectionStringFilePath005() {
		CMongoDBConn conn = new CMongoDBConn();
		Object ret = conn.getConnection("");
		conn.closeConnection();
	}
}
