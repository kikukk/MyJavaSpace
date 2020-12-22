package StudyDemo.first_2020.B20.SendInfoEvent;

import java.util.EventObject;

/**
 * @author kikukk
 */
public class SendInfoEvent extends EventObject {
    private Boolean state;
    public SendInfoEvent(Object source, Boolean state) {
        super(source);
        this.state = state;
    }
    public Boolean getState() {
        return this.state;
    }
}
