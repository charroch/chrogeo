package com.novoda.chrogeo.server;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beoui.geocell.GeocellManager;
import com.beoui.geocell.model.Point;
import com.novoda.chrogeo.server.jdo.JdoDataPointDao;
import com.novoda.chrogeo.shared.DataPoint;

public class Chrogeo extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String lat = req.getParameter("lat");
		String lon = req.getParameter("lon");
		
		double latd = Double.valueOf(lat);
		double lond = Double.valueOf(lon);
		
		Point point = new Point(latd, lond);
		
		URI uri = URI.create(req.getRequestURI());
		String tag = uri.getPath().replace("/", "");
		
		returnColor(resp, lat, lon, proximitySearch(point, tag), tag);
	}
	
	public String proximitySearch(Point center, String tag) {
		List<String> cells = GeocellManager.generateGeoCell(center);
		String cell = cells.get(11);
		JdoDataPointDao dao = new JdoDataPointDao();
		List<DataPoint> points = dao.proximitySearch(cell, tag);
		
		if("crime".equals(tag)) {
			int size = 0;
			if(points != null) {
				size = points.size();
			}
			if(size == 0) {
				return "FF00FF00";
			} else if (0 < size && size < 3) {
				return "FFFFFF00";
			} else {
				return "FFFF0000";
			}
		} else { //politics
			if(points != null && points.size() > 0) {
				String content = points.get(0).getContent();
				if("conservative".equals(content)) {
					return "FFFF0000";
				} else {
					return "FF0000FF";
				}
			} else {
				return "FF000000";
			}
		}
    }
	
	private void returnColor(HttpServletResponse resp, String lat, String lon, String color, String tag) throws IOException {
		resp.getContentType();
		resp.getWriter().append("{\"colour\":" + "\"" + color + "\"" + ",\"lon\":" + lon + 
				",\"lat\":" + lat + ",\"tag\":\"" + tag + "\" }");
		resp.flushBuffer();
	}

}
