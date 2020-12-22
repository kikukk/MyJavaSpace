package StudyDemo.first_2020.B20.SendInfoEvent;

import java.util.Collection;
import java.util.HashSet;

/**
 * @author kikukk
 */
public class SendInfoManager {
    private Collection listeners;
    private static SendInfoManager instance = new SendInfoManager();
    public SendInfoManager(){}
    public static SendInfoManager getInstance(){
        return instance;
    }

    public void addMessageListener(SendInfoListener listener) {
        if (listeners == null){listeners = new HashSet();}
        listeners.add(listener);
    }

    public void fireWorkspaceOpened(){
        if (listeners == null){return;}
        SendInfoEvent event = new SendInfoEvent(this, true);
        notifyListeners(event);
    }

    public void fireWorkspaceClosed(){
        if (listeners == null){return;}
        SendInfoEvent event = new SendInfoEvent(this, false);
        notifyListeners(event);
    }

    private void notifyListeners(SendInfoEvent event){
        for (Object o : listeners) {
            SendInfoListener listener = (SendInfoListener) o;
            listener.messageEvent(event);
        }
    }
}
