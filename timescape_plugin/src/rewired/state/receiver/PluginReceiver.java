package rewired.state.receiver;

import novoda.lib.se.idores.provider.PluginContentValues;
import novoda.lib.se.idores.provider.SourceContentValues;
import novoda.lib.se.idores.receiver.EventStreamReceiver;

public class PluginReceiver extends EventStreamReceiver {

    @Override
    public PluginContentValues getRegisterValues() {
        return null;
    }

    @Override
    public SourceContentValues[] getSources() {
        return null;
    }

}
