package StudyDemo.first_2020.A09.MessageTool;

import StudyDemo.first_2020.A09.Client;
import java.util.EventListener;

public class MessageListener implements EventListener {
        public void messageEvent(MessageEvent event) {
            // TODO Auto-generated method stub
            if (event.getState() != null && event.getState() == true) {
                Client.actionAddContent();
            }
        }
}

