
package rewired.state.receiver;

import novoda.lib.se.idores.edk.SourceManager;
import novoda.lib.se.idores.model.Event;
import novoda.lib.se.idores.model.Inserter;
import novoda.lib.se.idores.model.InserterFactory;
import novoda.lib.se.idores.provider.PluginContentValues;
import novoda.lib.se.idores.provider.SourceContentValues;
import novoda.lib.se.idores.receiver.EventStreamReceiver;
import rewired.state.R;
import rewired.state.activity.Settings;
import rewired.state.service.RefreshService;

import android.app.IntentService;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.sax.StartElementListener;
import android.util.Log;

import java.util.Random;

public class PluginReceiver extends EventStreamReceiver {

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

            Event.Builder builder = new Event.Builder(getContext());
            builder.withSourceId(SourceManager.getSourceId(getContext()))
                    .setPublishedTime(System.currentTimeMillis())
                    .withImage("android.resource://rewired.state/" + color).setMessage("");

            Inserter insert = InserterFactory.getInserter(Event.class, getContext());
            insert.add(builder.build());
            insert.insert();
        }

    };

    @Override
    public void refresh() {
        refresher.postAtTime(refreshRunnable, 5000);
        Intent intent = new Intent(getContext(), RefreshService.class);
        getContext().startService(intent);
        super.refresh();
    }

    @Override
    public PluginContentValues getRegisterValues() {
        boolean isloggedin = true;
        PluginContentValues values = new PluginContentValues(mContext);
        values.setApiVersion(1);
        values.setName(getContext().getString(R.string.app_name));
        values.setPluginKey("testing");
        values.setIcon(R.drawable.icon);
        values.setStatusSupport(1);
        values.setStatusMaxLength(256);
        values.setState((isloggedin) ? 0 : 1);
        values.setConfigurationStatus("test");
        // values.setConfigurationStatus((isloggedin) ?
        // mContext.getString(R.string.logged_in_as)
        // + " " + PreferenceUtil.getUsername(getContext()) : mContext
        // .getString(R.string.not_logged_in));

        ComponentName activity = new ComponentName(mContext, Settings.class);
        values.setConfigurationActivity(activity);
        return values;
    }

    @Override
    public SourceContentValues[] getSources() {
        SourceContentValues[] values = new SourceContentValues[1];
        SourceContentValues.Builder builder = new SourceContentValues.Builder(mContext);
        builder.withName(getContext().getString(R.string.app_name));
        builder.withIcon(R.drawable.icon);
        builder.isEnabled(true);
        values[0] = builder.build();
        return values;
    }
}
