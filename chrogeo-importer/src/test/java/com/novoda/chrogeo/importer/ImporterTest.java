package com.novoda.chrogeo.importer;

import org.junit.Ignore;
import org.junit.Test;

public class ImporterTest {

	@Ignore
	@Test
	public void importSingleData() {
		DataPoint point = new DataPoint();
		point.setContent("bad");
		point.setTimestamp(System.currentTimeMillis());
		point.setLat("51.527543");
		point.setLon("-0.12085");
		point.setTag("crime");
		point.setGeocells(null);
	
		Importer importer = new Importer("localhost:8888");
		
		importer.importer(point);
		
	}
	
	@Test
	public void importPoliticsSingleData() {
		DataPoint point = new DataPoint();
		point.setContent("Anti-social behaviour");
		point.setTimestamp(System.currentTimeMillis());
		point.setLat("51.527543");
		point.setLon("-0.12085");
		point.setTag("crime");
		point.setGeocells(null);
	
		Importer importer = new Importer();
		
		importer.importer(point);
	}
	
}
