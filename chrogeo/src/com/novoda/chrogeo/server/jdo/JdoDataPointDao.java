package com.novoda.chrogeo.server.jdo;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.novoda.chrogeo.shared.DataPoint;

public class JdoDataPointDao extends BaseDaoImpl<DataPoint> implements BaseDao<DataPoint> {
	
	public JdoDataPointDao() {
		super(DataPoint.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<DataPoint> proximitySearch(String cell, String tag) {
		PersistenceManager pm = getPM();
        Query q = pm.newQuery(DataPoint.class);
        q.setFilter("tag == '" + tag + "' && " + " geocells == '" + cell + "'");
        return (List<DataPoint>) q.execute(tag, cell);
	}
	
}
