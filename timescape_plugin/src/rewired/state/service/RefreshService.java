
package rewired.state.service;

import novoda.lib.se.idores.edk.SourceManager;
import novoda.lib.se.idores.model.Event;
import novoda.lib.se.idores.model.Inserter;
import novoda.lib.se.idores.model.InserterFactory;
import rewired.state.R;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import java.util.Random;

public class RefreshService extends Service {
    private Handler refresher = new Handler();

    public static int randomNumber(int min, int max) {
        return min + (new Random()).nextInt(max - min);
    }

    Runnable refreshRunnable = new Runnable() {
        @Override
        public void run() {
            int color = 0;
            Log.i("TEST", "running");
            switch (randomNumber(0, 3)) {
                case 0:
                    color = R.drawable.blue;
                    break;
                case 1:
                    color = R.drawable.red;
                    break;
                case 2:
                    color = R.drawable.yellow;
                    break;
            }

            Event.Builder builder = new Event.Builder(getBaseContext());
            builder.withSourceId(SourceManager.getSourceId(getBaseContext()))
                    .setPublishedTime(System.currentTimeMillis())
                    .withImage("android.resource://rewired.state/" + color).setMessage("");

            Inserter insert = InserterFactory.getInserter(Event.class, getBaseContext());
            insert.add(builder.build());
            insert.insert();
            refresher.postDelayed(this, 5000);
            //refresher.postAtTime(refreshRunnable, 5000);
        }

    };

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        refresher.postAtTime(refreshRunnable, 5000);
        return super.onStartCommand(intent, flags, startId);
    }
}
