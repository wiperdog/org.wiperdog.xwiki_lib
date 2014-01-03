package org.wiperdog.custommongodbconnection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.wiperdog.lib.BaseCMongoDBConnTestcase;
import org.wiperdog.lib.TestUTCommon;

/**
 * Testcase for method getConnection(File param_file)
 * @author luvina
 *
 */
public class CMongoDBConn_UT_02 extends BaseCMongoDBConnTestcase {
	String collection = "MySQL.Database_Area.InnoDBTablespace_Free";
	String istIid = "localhost-@MYSQL-information_schema";
	String param_file_path = "tmp\\conf_Remote.params";
	String param_file_path_local = "tmp\\conf_Local.params";
	CMongoDBConn conn;

	/**
	 * Check remote connect with valid param's file
	 * 
	 * @throws Exception
	 * 
	 */
	@Test
	public void getConnectionFileParam_file001() throws Exception {
		File param_file = new File(this.param_file_path);
		CMongoDBConn conn = new CMongoDBConn();
		Object ret = conn.getConnection(param_file);
		assertTrue(ret instanceof com.gmongo.GMongo);
		assertTrue(conn.getDb() != null && conn.getDb() instanceof com.mongodb.DB);
	}

	/**
	 * Check local connect with valid param's file
	 * 
	 * @throws Exception
	 */
	@Test
	public void getConnectionFileParam_file002() throws Exception {
		File param_file = new File(this.param_file_path_local);
		CMongoDBConn conn = new CMongoDBConn();
		Object ret = conn.getConnection(param_file);
		assertTrue(ret instanceof com.gmongo.GMongo);
		assertTrue(conn.getDb() != null && conn.getDb() instanceof com.mongodb.DB);
	}

	/**
	 * Check exception handle when param's file doesn't exist
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
	 * 
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void getConnectionFileParam_file005() throws Exception {
		try {
			File param_file = new File("tmp\\conf_empty.params");
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
	 * 
	 * @throws Exception
	 * 
	 */
	@Test(expected = Exception.class)
	public void getConnectionFileParam_file006() throws Exception {
		try {
			File param_file = new File("tmp\\conf_wrongformat.params");
			CMongoDBConn conn = new CMongoDBConn();
			Object ret = conn.getConnection(param_file);
		} catch (Exception e) {
			assertEquals("Params file doesn\'t contain mongoDB", e.getMessage());
			throw e;
		}
	}
}
