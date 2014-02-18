/**
 * copied from CMongoDBConn_UT_03.java
 * for mongodb without auth option
 */
package org.wiperdog.custommongodbconnection;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assume.*;
import org.wiperdog.lib.BaseCMongoDBConnTestcase;

import com.gmongo.GMongo;
import com.mongodb.DB;
import com.mongodb.MongoException;

/**
 * Testcase for method getConnection(host, port, dbname, userName, password)
 *
 * @author luvina
 *
 */
public class TestCMongoDBConn_03 {
	private boolean mongdbpresent = true;
	private final String [] args = []
	String collection = "MySQL.Database_Area.InnoDBTablespace_Free";
	String istIid = "localhost-@MYSQL-information_schema";
	String param_file_path_local = "tmp/conf_Local.params";
	public TestCMongoDBConn_03() {
	}

	@Before
	public void startup() {
		assumeTrue(mongdbpresent);
		startmongo.main(args)
	}
	
	@After
	public void shutdown() {
		assumeTrue(mongdbpresent);
		stopmongo.main(args)
	}
	
	/**
	 * Check local connect with valid param's file path
	 * Expected: Connect successful result is Gmongo, getDb return a com.mongodb.DB instance
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
	 * Expected: Exception because of FileNotFound
	 */
	@Test(expected = Exception.class)
	public void getConnectionStringFilePath003() {
		CMongoDBConn conn = new CMongoDBConn();
		Object ret = conn.getConnection(param_file_path + "wrong");
		conn.closeConnection();
	}

	/**
	 * Check exception handle with empty param's file's path
	 * Expected: Exception when reading file
	 */
	@Test(expected = Exception.class)
	public void getConnectionStringFilePath005() {
		CMongoDBConn conn = new CMongoDBConn();
		Object ret = conn.getConnection("");
		conn.closeConnection();
	}
}
