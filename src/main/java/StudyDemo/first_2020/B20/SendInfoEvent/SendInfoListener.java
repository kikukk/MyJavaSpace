package StudyDemo.first_2020.B20.SendInfoEvent;

import StudyDemo.first_2020.B20.Utils.TcpUtils;

import java.util.EventListener;

/**
 * @author kikukk
 */
public class SendInfoListener implements EventListener {
    public void messageEvent(SendInfoEvent event){
        if (event.getState() != null && event.getState()) {
            TcpUtils.actionAddContent();
        }
    }
}
