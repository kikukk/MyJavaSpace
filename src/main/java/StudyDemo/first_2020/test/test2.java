package StudyDemo.first_2020.test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName test2
 * @Description TODO
 * @date 2020/9/23 9:30
 */
public class test2 {
    public static void main(String[] args) throws UnknownHostException {
        String info = "request\tlogin\taccount\tpassword";
        String order = info.substring(0,info.indexOf('\t')).trim();
        info = info.substring(info.indexOf('\t')).trim();
        String type = info.substring(0,info.indexOf('\t')).trim();
        info = info.substring(info.indexOf('\t')).trim();

        System.out.println("order:"+order);
        System.out.println("type:"+type);
        System.out.println(info);

    }
}
