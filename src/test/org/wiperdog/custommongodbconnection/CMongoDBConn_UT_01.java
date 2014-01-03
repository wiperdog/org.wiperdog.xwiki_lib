/**
 * 
 */
package org.wiperdog.custommongodbconnection;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;
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
public class CMongoDBConn_UT_01 extends BaseCMongoDBConnTestcase {

	/**
	 * Check connect to localhost, port default
	 */
	@Test
	public void getConnection5params001() {
		try {
			String host = "localhost";
			int port = 27017;
			String dbname = "wiperdog";
			String userName = "";
			String password = "";
			CMongoDBConn conn = new CMongoDBConn();
			Object ret = conn.getConnection(host, port, dbname, userName, password);
			assertTrue(ret instanceof com.gmongo.GMongo);
			assertTrue(conn.getDb() != null && conn.getDb() instanceof com.mongodb.DB);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Check connect to remote host, port default
	 */
	@Test
	public void getConnection5params002() {
		try {
			String host = "10.0.0.184";
			int port = 27017;
			String dbname = "wiperdog";
			String userName = "";
			String password = "";
			CMongoDBConn conn = new CMongoDBConn();
			Object ret = conn.getConnection(host, port, dbname, userName, password);
			assertTrue(ret instanceof com.gmongo.GMongo);
			assertTrue(conn.getDb() != null && conn.getDb() instanceof com.mongodb.DB);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Check connect to localhost, port custom
	 */
	@Test
	public void getConnection5params003() {
		try {
			String host = "localhost";
			int port = 27000;
			String dbname = "wiperdog";
			String userName = "";
			String password = "";
			CMongoDBConn conn = new CMongoDBConn();
			Object ret = conn.getConnection(host, port, dbname, userName, password);
			assertTrue(ret instanceof com.gmongo.GMongo);
			assertTrue(conn.getDb() != null && conn.getDb() instanceof com.mongodb.DB);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Check connect to remote host, port custom
	 */
	@Test
	public void getConnection5params004() {
		try {
			String host = "10.0.0.184";
			int port = 27000;
			String dbname = "wiperdog";
			String userName = "";
			String password = "";
			CMongoDBConn conn = new CMongoDBConn();
			Object ret = conn.getConnection(host, port, dbname, userName, password);
			assertTrue(ret instanceof com.gmongo.GMongo);
			assertTrue(conn.getDb() != null && conn.getDb() instanceof com.mongodb.DB);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Check connect to locahost, with authentication
	 */
	@Test
	public void getConnection5params005() {
		// DB needs authenticate test/test
		try {
			String host = "localhost";
			int port = 27017;
			String dbname = "wiperdog";
			String userName = "test";
			String password = "test";
			CMongoDBConn conn = new CMongoDBConn();
			Object ret = conn.getConnection(host, port, dbname, userName, password);
			assertTrue(ret instanceof com.gmongo.GMongo);
			assertTrue(conn.getDb() != null && conn.getDb() instanceof com.mongodb.DB);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Check connect to remote host, with authentication
	 */
	@Test
	public void getConnection5params006() {
		// DB needs authenticate test/test
		try {
			String host = "10.0.0.184";
			int port = 27017;
			String dbname = "wiperdog";
			String userName = "test";
			String password = "test";
			CMongoDBConn conn = new CMongoDBConn();
			Object ret = conn.getConnection(host, port, dbname, userName, password);
			assertTrue(ret instanceof com.gmongo.GMongo);
			assertTrue(conn.getDb() != null && conn.getDb() instanceof com.mongodb.DB);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Check connect to locahost, custom dbname
	 */
	@Test
	public void getConnection5params007() {
		try {
			String host = "localhost";
			int port = 27017;
			String dbname = "wiperdog_test";
			String userName = "";
			String password = "";
			CMongoDBConn conn = new CMongoDBConn();
			Object ret = conn.getConnection(host, port, dbname, userName, password);
			assertTrue(ret instanceof com.gmongo.GMongo);
			assertTrue(conn.getDb() != null && conn.getDb() instanceof com.mongodb.DB);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Check connect to remote host, custom dbname
	 */
	@Test
	public void getConnection5params008() {
		try {
			String host = "10.0.0.184";
			int port = 27017;
			String dbname = "wiperdog_test";
			String userName = "";
			String password = "";
			CMongoDBConn conn = new CMongoDBConn();
			Object ret = conn.getConnection(host, port, dbname, userName, password);
			assertTrue(ret instanceof com.gmongo.GMongo);
			assertTrue(conn.getDb() != null && conn.getDb() instanceof com.mongodb.DB);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Check connect to authentication host, custom dbname with login infomation
	 */
	@Test
	public void getConnection5params009() {
		try {
			String host = "localhost";
			int port = 27017;
			String dbname = "wiperdog_test";
			String userName = "test";
			String password = "test";
			CMongoDBConn conn = new CMongoDBConn();
			Object ret = conn.getConnection(host, port, dbname, userName, password);
			assertTrue(ret instanceof com.gmongo.GMongo);
			assertTrue(conn.getDb() != null && conn.getDb() instanceof com.mongodb.DB);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Check connect to non-authentication host, custom dbname with login
	 * infomation
	 */
	@Test
	public void getConnection5params010() {
		try {
			String host = "localhost";
			int port = 27017;
			String dbname = "wiperdog_test";
			String userName = "test";
			String password = "test";
			CMongoDBConn conn = new CMongoDBConn();
			Object ret = conn.getConnection(host, port, dbname, userName, password);
			assertTrue(ret instanceof com.gmongo.GMongo);
			assertTrue(conn.getDb() != null && conn.getDb() instanceof com.mongodb.DB);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Connect to port 0
	 * 
	 * @throws Exception
	 */
	@Test(expected = AssertionError.class)
	public void getConnection5params011() throws Exception {
		try {
			String host = "localhost";
			int port = 0;
			String dbname = "wiperdog";
			String userName = "";
			String password = "";
			CMongoDBConn conn = new CMongoDBConn();
			Object ret = conn.getConnection(host, port, dbname, userName, password);
		} catch (AssertionError ae) {
			assertTrue(ae.getMessage().contains("Port is null or invalid!"));
			throw ae;
		}
	}

	/**
	 * Connect to port < 0
	 * 
	 * @throws Exception
	 */
	@Test(expected = AssertionError.class)
	public void getConnection5params012() throws Exception {
		try {
			String host = "localhost";
			int port = -1;
			String dbname = "wiperdog";
			String userName = "";
			String password = "";
			CMongoDBConn conn = new CMongoDBConn();
			Object ret = conn.getConnection(host, port, dbname, userName, password);
		} catch (AssertionError ae) {
			assertTrue(ae.getMessage().contains("Port is null or invalid!"));
			throw ae;
		}
	}

	/**
	 * Connect to port > MAX_PORT
	 * 
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void getConnection5params013() throws Exception {
		String host = "localhost";
		int port = 99999;
		String dbname = "wiperdog";
		String userName = "";
		String password = "";
		CMongoDBConn conn = new CMongoDBConn();
		Object ret = conn.getConnection(host, port, dbname, userName, password);
	}

	/**
	 * Connect to host null
	 * 
	 * @throws Exception
	 * 
	 */
	@Test(expected = AssertionError.class)
	public void getConnection5params014() throws Exception {
		try {
			String host = null;
			int port = 27017;
			String dbname = "wiperdog";
			String userName = "";
			String password = "";
			CMongoDBConn conn = new CMongoDBConn();
			Object ret = conn.getConnection(host, port, dbname, userName, password);
		} catch (AssertionError ae) {
			assertTrue(ae.getMessage().contains("Host is null or empty!"));
			throw ae;
		}
	}

	/**
	 * Connect to host empty
	 * 
	 * @throws Exception
	 * 
	 */
	@Test(expected = AssertionError.class)
	public void getConnection5params015() throws Exception {
		try {
			String host = "";
			int port = 27017;
			String dbname = "wiperdog";
			String userName = "";
			String password = "";
			CMongoDBConn conn = new CMongoDBConn();
			Object ret = conn.getConnection(host, port, dbname, userName, password);
		} catch (AssertionError ae) {
			assertTrue(ae.getMessage().contains("Host is null or empty!"));
			throw ae;
		}
	}

	/**
	 * Connect to wrong port Still have connection but can not query
	 * 
	 * @throws Exception
	 * 
	 */
	@Test
	public void getConnection5params016() throws Exception {
		String host = "localhost";
		int port = 27000;
		String dbname = "wiperdog";
		String userName = "";
		String password = "";
		CMongoDBConn conn = new CMongoDBConn();
		Object ret = conn.getConnection(host, port, dbname, userName, password);
		assertTrue(ret instanceof GMongo);
		assertTrue(conn.getDb() instanceof DB);
	}

	/**
	 * Connect to port null
	 * 
	 * @throws Exception
	 */
	@Test(expected = AssertionError.class)
	public void getConnection5params017() throws Exception {
		try {
			String host = "localhost";
			Integer port = null;
			String dbname = "wiperdog";
			String userName = "";
			String password = "";
			CMongoDBConn conn = new CMongoDBConn();
			Object ret = conn.getConnection(host, port, dbname, userName, password);
		} catch (AssertionError ae) {
			assertTrue(ae.getMessage().contains("Port is null or invalid!"));
			throw ae;
		}
	}

	/**
	 * Connect successfully but dbname is null
	 * 
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void getConnection5params018() throws Exception {
		String host = "localhost";
		int port = 27017;
		String dbname = null;
		String userName = "";
		String password = "";
		CMongoDBConn conn = new CMongoDBConn();
		Object ret = conn.getConnection(host, port, dbname, userName, password);
	}

	/**
	 * Connect successfully but dbname is empty
	 * 
	 * @throws Exception
	 * 
	 */
	@Test
	public void getConnection5params019() throws Exception {
		String host = "localhost";
		int port = 27017;
		String dbname = "";
		String userName = "";
		String password = "";
		CMongoDBConn conn = new CMongoDBConn();
		Object ret = conn.getConnection(host, port, dbname, userName, password);
	}

	/**
	 * Connect to authentication db but give wrong user/password
	 * 
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void getConnection5params020() throws Exception {
		// DB needs authenticate
		String host = "10.0.0.184";
		int port = 27017;
		String dbname = "wiperdog";
		String userName = "wronguser";
		String password = "test";
		CMongoDBConn conn = new CMongoDBConn();
		Object ret = conn.getConnection(host, port, dbname, userName, password);
		assertTrue(ret instanceof GMongo);
		Set<String> lstCollections = ((DB)conn.getDb()).getCollectionNames();
	}

	/**
	 * Connect to authentication db but give null user
	 * 
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void getConnection5params021() throws Exception {
		// DB needs authenticate
		String host = "10.0.0.184";
		int port = 27017;
		String dbname = "wiperdog";
		String userName = null;
		String password = "test";
		CMongoDBConn conn = new CMongoDBConn();
		Object ret = conn.getConnection(host, port, dbname, userName, password);
		((DB)conn.getDb()).getCollectionNames();
	}

	/**
	 * Connect to authentication db but give empty user
	 * 
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void getConnection5params022() throws Exception {
			// DB needs authenticate
			String host = "10.0.0.184";
			int port = 27017;
			String dbname = "wiperdog";
			String userName = "";
			String password = "test";
			CMongoDBConn conn = new CMongoDBConn();
			Object ret = conn.getConnection(host, port, dbname, userName, password);
			((DB)conn.getDb()).getCollectionNames();
	}

	/**
	 * Connect to authentication db but give space character user
	 * 
	 * @throws Exception
	 * 
	 */
	@Test(expected = Exception.class)
	public void getConnection5params023() throws Exception {
		String host = "10.0.0.184";
		int port = 27017;
		String dbname = "wiperdog";
		String userName = " ";
		String password = "test";
		CMongoDBConn conn = new CMongoDBConn();
		Object ret = conn.getConnection(host, port, dbname, userName, password);
		((DB)conn.getDb()).getCollectionNames();
	}

	/**
	 * Connect to authentication db but give wrong password
	 * 
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void getConnection5params024() throws Exception {
		// DB needs authenticate
		String host = "10.0.0.184";
		int port = 27017;
		String dbname = "wiperdog";
		String userName = "test";
		String password = "wrongpassword";
		CMongoDBConn conn = new CMongoDBConn();
		Object ret = conn.getConnection(host, port, dbname, userName, password);
		((DB) conn.getDb()).getCollectionNames();
	}

	/**
	 * Connect to authentication db but give null password
	 * 
	 * @throws Exception
	 */
	@Test(expected = AssertionError.class)
	public void getConnection5params025() throws Exception {
		try {
			// DB needs authenticate
			String host = "10.0.0.184";
			int port = 27017;
			String dbname = "wiperdog";
			String userName = "test";
			String password = null;
			CMongoDBConn conn = new CMongoDBConn();
			Object ret = conn.getConnection(host, port, dbname, userName, password);
		} catch (AssertionError ae) {
			assertTrue(ae.getMessage().contains("Password is null or empty!"));
			throw ae;
		}
	}

	/**
	 * Connect to authentication db but doesn't give login information
	 * 
	 * @throws Exception
	 */
	@Test(expected = MongoException.class)
	public void getConnection5params026() throws Exception {
		// DB needs authenticate
		String host = "10.0.0.184";
		int port = 27017;
		String dbname = "wiperdog";
		String userName = "";
		String password = "";
		CMongoDBConn conn = new CMongoDBConn();
		Object ret = conn.getConnection(host, port, dbname, userName, password);
		assertTrue(ret instanceof com.gmongo.GMongo);
		assertTrue(conn.getDb() != null && conn.getDb() instanceof com.mongodb.DB);
		((DB) conn.getDb()).getCollectionNames();
	}

	/**
	 * Connect to non-authentication db but still give login info
	 * 
	 * @throws Exception
	 */
	@Test
	public void getConnection5params027() throws Exception {
		String host = "localhost";
		int port = 27017;
		String dbname = "wiperdog";
		String userName = "test";
		String password = "test";
		CMongoDBConn conn = new CMongoDBConn();
		Object ret = conn.getConnection(host, port, dbname, userName, password);
		assertTrue(ret instanceof com.gmongo.GMongo);
		assertTrue(conn.getDb() != null && conn.getDb() instanceof com.mongodb.DB);
		((DB) conn.getDb()).getCollectionNames();
	}
}
