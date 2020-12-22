package StudyDemo.first_2020.B20;

import StudyDemo.first_2020.B20.Utils.NetInfo;
import StudyDemo.first_2020.B20.Utils.User;

/**
 * @author kikukk
 */
public class SendInfo {
    private final String target;
    private final NetInfo netInfo;
    private final byte[] sendInfo;
    private final User fromUser;
    private String fileName;

    public SendInfo(String target,User curUser,byte[] info){
        this.target = target;
        this.netInfo = curUser.getNetInfo();
        this.fromUser = curUser;
        this.sendInfo = info;
    }
    public SendInfo(String target,User curUser,String fileName,byte[] info){
        this.target = target;
        this.netInfo = curUser.getNetInfo();
        this.fromUser = curUser;
        this.sendInfo = info;
        this.fileName = fileName;
    }

    public String getTarget() {
        return target;
    }

    public NetInfo getFromAddress() {
        return netInfo;
    }

    public byte[] getSendInfo(){
        return sendInfo;
    }

    public User getFromUser(){return fromUser;}

    public String getFileName(){return fileName;}
}
