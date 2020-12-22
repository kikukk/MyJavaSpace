package Application.tableEdit.web;

import java.io.*;

/**
 * @author 14417
 * @ClassName demo1
 * @Description TODO
 * @date 2020/9/21 12:53
 */
public class demo1 {
    public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new FileReader(new File("src\\main\\java\\Application\\tableEdit\\web\\test.txt")));
        String curStr;
        String name;
        String point;
        String type;
        try{
            while(!(curStr = bfr.readLine()).equals(null)){
                name = curStr.substring(0,curStr.indexOf('\t'));
                point = (curStr.substring(curStr.indexOf('\t'),curStr.lastIndexOf('\t'))).trim();
                type = (curStr.substring(curStr.lastIndexOf('\t'))).trim();
//                        <li>
//                            <dt>Web开发技术</dt>
//                            <dd>3学分</dd>
//                            <dd>选修</dd>
//                        </li>
                //System.out.println("<li>"+name+"&nbsp;"+point+"学分&nbsp;"+type+"</li>");
                System.out.println("<li>");
                System.out.println("\t<dt>"+name+"</dt>");
                System.out.println("\t<dd>"+point+"学分</dd>");
                System.out.println("\t<dd>"+type+"</dd>");
                System.out.println("</li>");
            }
        }catch (Exception e){
            System.out.println(e);
        }

    }
}
