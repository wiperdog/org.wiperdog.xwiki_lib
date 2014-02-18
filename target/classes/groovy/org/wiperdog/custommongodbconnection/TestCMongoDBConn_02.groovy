/**
 * copied from CMongoDBConn_UT_02.java
 * for mongodb without auth option
 */
package org.wiperdog.custommongodbconnection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
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
public class TestCMongoDBConn_02 {
	private boolean mongdbpresent = true;
	private final String [] args = [];
	String collection = "MySQL.Database_Area.InnoDBTablespace_Free";
	String istIid = "localhost-@MYSQL-information_schema";
	String param_file_path = "tmp/conf_Remote.params";
	String param_file_path_local = "tmp/conf_Local.params";
	CMongoDBConn conn;
	
	public TestCMongoDBConn_02() {
	}

	@Before
	public void startup() {
		assumeTrue(mongdbpresent);
		startmongo.main(args);
	}
	
	@After
	public void shutdown() {
		assumeTrue(mongdbpresent);
		stopmongo.main(args);
	}

	/**
	 * Check local connect with valid param's file
	 * Expected: Connect successful result is Gmongo, getDb return a com.mongodb.DB instance
	 *
	 * @throws Exception
	 */
	@Test
	public void getConnectionFileParam_file002() throws Exception {
		System.out.println("===========getConnectionFileParam_file002================");
		File param_file = new File(this.param_file_path_local);
		CMongoDBConn conn = new CMongoDBConn();
		System.out.println(param_file);
		Object ret = conn.getConnection(param_file);
		assertTrue(ret instanceof com.gmongo.GMongo);
		assertTrue(conn.getDb() != null && conn.getDb() instanceof com.mongodb.DB);
	}
	
	/**
	 * Check exception handle when param's file doesn't exist
	 * Expected: Exception with message "File params doesn\'t existed!"
	 *
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void getConnectionFileParam_file003() throws Exception {
		try {
			File param_file = new File(this.param_file_path + "wrong");
			CMongoDBConn conn = new CMongoDBConn();
			Object ret = conn.getConnection(param_file);
		} catch (Exception e) {
			assertEquals("File params doesn\'t existed!", e.getMessage());
			throw e;
		}
	}

	/**
	 * Check exception handle when param's file is empty
	 * Expected: Exception with message "Params file doesn\'t contain mongoDB"
	 *
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void getConnectionFileParam_file005() throws Exception {
		try {
			File param_file = new File("tmp/conf_empty.params");
			CMongoDBConn conn = new CMongoDBConn();
			Object ret = conn.getConnection(param_file);
		} catch (Exception e) {
			assertEquals("Params file doesn\'t contain mongoDB", e.getMessage());
			throw e;
		}
	}

	/**
	 * Check exception handle when param's file doesn't contains mongodb
	 * declaration
	 * Expected: Exception with message "Params file doesn\'t contain mongoDB"
	 *
	 * @throws Exception
	 *
	 */
	@Test(expected = Exception.class)
	public void getConnectionFileParam_file006() throws Exception {
		try {
			File param_file = new File("tmp/conf_wrongformat.params");
			CMongoDBConn conn = new CMongoDBConn();
			Object ret = conn.getConnection(param_file);
		} catch (Exception e) {
			assertEquals("Params file doesn\'t contain mongoDB", e.getMessage());
			throw e;
		}
	}
}
