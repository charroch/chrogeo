
package rewired.state.receiver;

import novoda.lib.se.idores.provider.PluginContentValues;
import novoda.lib.se.idores.provider.SourceContentValues;
import novoda.lib.se.idores.receiver.EventStreamReceiver;
import rewired.state.R;
import rewired.state.activity.Settings;

import android.content.ComponentName;

public class PluginReceiver extends EventStreamReceiver {

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
