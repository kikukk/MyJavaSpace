package StudyDemo.first_2020.A09.MessageTool;

import java.util.EventObject;

public class MessageEvent extends EventObject {
        private Boolean state = false;// 表示是否收到信息的状态
        public MessageEvent(Object source, Boolean State) {
            super(source);
            this.state = State;
        }
        public Boolean getState() {
            return this.state;
        }
}

