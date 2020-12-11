package Test;

import java.io.*;
import java.net.InetAddress;

/**
 * @ClassName test
 * @Description TODO
 * @date 2020/9/18 16:43
 */
public class test {
    public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new FileReader(new File("D:\\kikukk\\WorkSpace\\MyJavaSpace\\src\\main\\java\\Test\\test")));
        String curStr = "";
        String str1;
        String str2;
        String str3;
        while((curStr = bfr.readLine())!=""){
            str1 = curStr.substring(0,curStr.indexOf("\t"));
            str2 = curStr.substring(curStr.indexOf('\t')+1,curStr.lastIndexOf('\t'));
            str3 = curStr.substring(curStr.lastIndexOf('\t')+1);
//            <tr align="center">
//                <td>社会心理学</td>
//                <td>2学分</td>
//                <td>院公选课</td>
//            </tr>
            System.out.println("<tr align=\"center\">");
            System.out.println("\t<td>"+str1+"</td>");
            System.out.println("\t<td>"+str2+"</td>");
            System.out.println("\t<td>"+str3+"</td>");
            System.out.println("</tr>");


        }

        bfr.close();


    }
}
