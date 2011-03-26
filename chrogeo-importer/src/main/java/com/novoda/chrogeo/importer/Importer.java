package com.novoda.chrogeo.importer;

import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

public class Importer	 {

	private DefaultHttpClient httpclient = new DefaultHttpClient();
	
	private static final String HOST = "coloreddata.appspot.com";
	
	private String defaultHost = HOST;
	
	public Importer() {
		
	}
	
	public Importer(String host) {
		defaultHost = host;
	}

	public void importer(List<DataPoint> data) {
		for (DataPoint point : data) {
			importer(point);
		}
	}

	public void importer(DataPoint data) {
        try {
			HttpPost httpost = new HttpPost("http://" + defaultHost + "/data/DataPoint");
	        httpost.setEntity(new StringEntity(data.asJson()));
	        HttpResponse response = httpclient.execute(httpost);
	
	        System.out.println("Status : " + response.getStatusLine());
	        
	        httpclient.getConnectionManager().shutdown();
        } catch (Exception e) {
        	throw new RuntimeException("runtime exception : " + e.getStackTrace());
		}
        
	}

}
