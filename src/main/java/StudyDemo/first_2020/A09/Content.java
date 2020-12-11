package StudyDemo.first_2020.A09;

import StudyDemo.first_2020.A09.utils.MyBytes;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @ClassName Content
 * @Description TODO
 * @date 2020/10/10 8:47
 */
public class Content {
    private InetAddress fromAddress;
    private int fromPort;
    //private String target;
    private String type;
    private MyBytes content;
    private String time;

//    }

    public Content(){
        type = "nextline";
        content = null;
    }

    public Content(User user,String type,MyBytes info){
        this.fromAddress = user.getAddress();
        this.fromPort = user.getPort();
        this.type = type;
        this.content = info;
        this.time = getTime();
    }

    public String getContent(){
//        if(target == "Single"){
//            return "对方\t"+time+"\n"+content+"\n";
//        }else{
//            return fromAddress+":"+fromPort+"\t"+time+"\n"+content+"\n";
//        }
        switch (type) {
            case "picture":
                return fromAddress.getHostAddress() + ":" + fromPort + "\t" + time;
            case "text":
                return fromAddress.getHostAddress() + ":" + fromPort + "\t" + time + "\n" +content.getMyBytesToString() + "\n";
            case "nextline":
                return "\n";
            default:
                return "";
        }
    }

    static String getTime(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public String getType(){return type;}

    @Override
    public String toString() {
        return "Content{" +
                "fromAddress=" + fromAddress +
                ", fromPort=" + fromPort +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public static void main(String[] args) {
        try {
            Content content = new Content(new User(InetAddress.getByName("127.0.0.1"),8811),"text",new MyBytes("hahahaa".getBytes()));
            System.out.println(content.getContent());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
