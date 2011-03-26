package com.novoda.chrogeo.importer;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

public class DataPointTest {

	@Ignore
	@Test
	public void shouldGetString() {
		DataPoint point = new DataPoint();
		point.setContent("bad");
		point.setTimestamp(System.currentTimeMillis());
		point.setLat("120000");
		point.setLon("123121l");
		point.setTag("crime");
		
		assertEquals("[{\"content\":\"Anti-social behaviour\"," +
				"\"tag\":\"crime\"," +
				"\"timestamp\":\"1301147695314\"" +
				",\"lat\":" +
				",\"lon\":}]" 
				, point.asJson());
	}
	
}
