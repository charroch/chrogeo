package rewired.state.service;

import java.io.IOException;
import java.net.URI;

import novoda.lib.se.idores.edk.SourceManager;
import novoda.lib.se.idores.model.Event;
import novoda.lib.se.idores.model.Inserter;
import novoda.lib.se.idores.model.InserterFactory;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Service;
import android.content.Intent;
import android.net.http.AndroidHttpClient;
import android.os.IBinder;
import android.util.Log;

public class ColoredDataFetcherService extends Service{

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}

	
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.v("XXXXXX","OnStartCommand!");
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				startWorking();
			}
		}).start();
		
		this.stopSelf();
		return super.onStartCommand(intent, flags, startId);
	}
	
	private void startWorking() {
		HttpResponse response = getResponse("http://coloreddata.appspot.com/politics?lat=51.527543&lon=0.12085");
		String result = getResult(response);
		String colour = getColour(result);
		insertEvent(colour);
	}
	
	private void insertEvent(String colour) {
    	Event.Builder builder = new Event.Builder(ColoredDataFetcherService.this);
    	builder.withSourceId(SourceManager.getSourceId(ColoredDataFetcherService.this))
    		   .setPublishedTime(System.currentTimeMillis())
    		   .withImage("content://rewired.state/" + colour)
    		   .setMessage("This area's political colour...");
    	
    	Inserter insert = InserterFactory.getInserter(Event.class, ColoredDataFetcherService.this);
    	insert.add(builder.build());
    	insert.insert();		
	}

	private HttpResponse getResponse(String path){
		HttpResponse response = null;
		AndroidHttpClient client = AndroidHttpClient.newInstance("chrogeo");
		URI uri = URI.create(path);
		HttpGet httpGet = new HttpGet(uri);
		
		try {
			response = client.execute(httpGet);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	private String getResult(HttpResponse response){
		try {
			return EntityUtils.toString(response.getEntity());
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private String getColour(String result) {
		try {
			JSONObject json = new JSONObject(result);
			return (String) json.get("colour");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	
	
}
