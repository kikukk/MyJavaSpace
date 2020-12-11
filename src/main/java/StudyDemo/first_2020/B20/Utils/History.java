package StudyDemo.first_2020.B20.Utils;

import StudyDemo.first_2020.B20.Resource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * @author kikukk
 */
public class History {
    private String fromAccount;
    private String time;
    private String type;
    private String content;

    public History(ResultSet rs) throws SQLException {
        this.fromAccount = rs.getString(1);
        this.time = rs.getString(2);
        this.type = rs.getString(3);
        this.content = rs.getString(4);
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public String getTime() {
        return time;
    }

    public String getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    public History(String fromAccount, String type, String content){
        this.fromAccount = fromAccount;
        this.time = getCurTime();
        this.type = type;
        this.content = content;
    }

    private String getCurTime(){
        return Resource.getInstance().getDf().format(new Date());
    }

    public String getHistoryIntoSend(){
        if("image".equals(type)){
            return "message\ttext\t"+JdbcFunction.getNameByAccount(fromAccount)+"\t"+time+"\t发送了图片:"+content;
        }else if("file".equals(type)){
            return "message\ttext\t"+JdbcFunction.getNameByAccount(fromAccount)+"\t"+time+"\t发送了文件:"+content;
        }else{
            return "message\ttext\t"+JdbcFunction.getNameByAccount(fromAccount)+"\t"+time+"\t"+content;
        }
    }
}
