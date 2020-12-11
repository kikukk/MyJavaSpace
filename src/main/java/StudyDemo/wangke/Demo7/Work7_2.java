package StudyDemo.wangke.Demo7;

import java.util.Scanner;

public class Work7_2 {
    public static void main(String[] args){
        /**
         * 实现查找一句话中的字母
         * */
        System.out.println("输入一个字符串：");
        Scanner input = new Scanner(System.in);
        String sentence = input.nextLine().toString();
        System.out.println("输入要查找的字符：");
        char target = input.next().charAt(0);
        int num = find(target,sentence);
        System.out.println(sentence+"中包含了"+num+"个"+target);
    }

    public static int find(char target,String sentence){
        int num = 0;
        for(int i = 0;i < sentence.length();i++){
            if(sentence.charAt(i) == target){
                num++;
            }
        }
        return num;
    }
}
