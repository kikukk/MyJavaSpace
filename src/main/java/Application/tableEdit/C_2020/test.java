package Application.tableEdit.C_2020;

import java.io.*;
import java.text.ParseException;

class test{
    public static void main(String[] args) throws ParseException, IOException {

        //=R2/SUM(R2:R1462)
//        for(int i = 2;i < 1463;i++){
//            System.out.println("=(R"+i+"*10000)/SUM(R2:R1462)");
//        }
        BufferedReader bfr = new BufferedReader(new FileReader(new File("src\\main\\java\\Test\\test.txt")));
        String str = "";
        while((str = bfr.readLine())!=""){
            str = str.substring(5);
            System.out.println(str);

        }
        bfr.close();
    }
}