package StudyDemo.first_2020.A09.MessageTool;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class MessageManager {
    private Collection listeners;
    private static MessageManager instance = new MessageManager();
    private MessageManager(){}
    public static MessageManager getInstance(){
        return instance;
    }

    public void addMessageListener(MessageListener listener) {
        if (listeners == null)
            listeners = new HashSet();
        listeners.add(listener);
    }

    public void fireWorkspaceOpened() {
        if (listeners == null)
            return;
        MessageEvent event = new MessageEvent(this, true);
        notifyListeners(event);
    }

    public void fireWorkspaceClosed() {
        if (listeners == null)
            return;
        MessageEvent event = new MessageEvent(this, false);
        notifyListeners(event);
    }

    private void notifyListeners(MessageEvent event) {
        Iterator iter = listeners.iterator();
        while (iter.hasNext()) {
            MessageListener listener = (MessageListener) iter.next();
            listener.messageEvent(event);
        }
    }
}
