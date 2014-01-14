package org.wiperdog.realtimelib;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

/**
 * Testcase for method convertFetchAtBin
 * 
 * @author luvina
 * 
 */
public class RealtimeDataLib_UT_08 {
	RealtimeDataLib instanceTest = new RealtimeDataLib();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss z");
	
	/**
	 * convert fetchAt_bin with valid param
	 * Expected: result can be revert into begin date
	 * @throws ParseException
	 */
	@Test
	public void convertFetchAtBin_001() throws ParseException {
		Date datatest = new Date();
		Long datatestLong = datatest.getTime() / 1000;

		String result = (String) instanceTest.convertFetchAtBin(datatestLong);

		Date resultDate = sdf.parse(result);

		assertEquals(resultDate.toString(), datatest.toString());
	}

	/**
	 * When param is null
	 * Expected: AssertionError with message : "fetchAt is null!"
	 */
	@Test(expected = AssertionError.class)
	public void convertFetchAtBin_002() {
		try{
			String result = (String) instanceTest.convertFetchAtBin(null);
		}catch(AssertionError ae){
			assertTrue(ae.getMessage().contains("fetchAt is null!"));
			throw ae;
		}
	}

	/**
	 * When param is 0
	 * Expected: Got the first day valid new Date(0)
	 */
	@Test
	public void convertFetchAtBin_003() {
		String result = (String) instanceTest.convertFetchAtBin(0);
		assertEquals(sdf.format(new Date(0)), result);
	}

	/**
	 * When param is -1
	 * Expected: Got the same date as new Date(-1)
	 */
	@Test
	public void convertFetchAtBin_004() {
		String result = (String) instanceTest.convertFetchAtBin(-1);
		assertEquals(sdf.format(new Date(-1)), result);
	}

}
