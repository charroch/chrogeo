package com.novoda.chrogeo.importer;

import java.util.List;


public class DataPoint {
	
	private static final String QUOTE = "\"";
	
	private static final String COMMA = ",";
	
	private static final String SEP = ":";
	
	private static final String QUOTE_COMMA = QUOTE + COMMA;
	
	private static final String QUOTE_SEP = QUOTE + SEP;
	
	private static final String QUOTE_SEP_QUOTE = QUOTE + SEP + QUOTE;

	private Long id;
	
	private String tag;
	
	private String content;
	
	private String lat;
	
	private String lon;
	
	private Long timestamp;
	
	private List<String> geocells;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getTag() {
		return tag;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLat() {
		return lat;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getLon() {
		return lon;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}
	
	public String asJson() {
		StringBuffer buffer = new StringBuffer("[{");
		buffer.append(QUOTE).append("content").append(QUOTE_SEP_QUOTE).append(getContent()).append(QUOTE_COMMA);
		buffer.append(QUOTE).append("tag").append(QUOTE_SEP_QUOTE).append(getTag()).append(QUOTE_COMMA);
		buffer.append(QUOTE).append("timestamp").append(QUOTE_SEP_QUOTE).append(getTimestamp()).append(QUOTE_COMMA);
		buffer.append(QUOTE).append("lat").append(QUOTE_SEP).append(getLat()).append(COMMA);
		buffer.append(QUOTE).append("lon").append(QUOTE_SEP).append(getLon());
		return buffer.append("}]").toString();
	}

	public void setGeocells(List<String> geocells) {
		this.geocells = geocells;
	}

	public List<String> getGeocells() {
		return geocells;
	}
	
}
