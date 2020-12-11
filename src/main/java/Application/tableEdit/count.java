package Application.tableEdit;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @ClassName count
 * @Description TODO
 * @date 2020/8/27 18:01
 */
public class count {
    public static void main(String[] args) throws InterruptedException {
        Scanner in = new Scanner(System.in);
        ArrayList<String> arrayList = new ArrayList<>();
        String tmp;
        while(true){
            tmp = in.nextLine();
            arrayList.add(tmp);
            //20-11-2006
            System.out.println(tmp.substring(6)+"/"+tmp.substring(3,5)+"/"+tmp.substring(0,2));
            Thread.sleep(10);

        }



    }
}
