package StudyDemo.wangke.Demo3;

import java.util.Scanner;
/**
 * 计算一个整形数字各位数之和
 * */
public class Demo3_2 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("输入一个整数");
        int num = input.nextInt();
        int sum = 0,temp = 0;
        while(num>0)
        {
            temp = num%10;
            sum += temp;
            num /= 10;
        }
        System.out.println("各位数相加的和为："+sum);
    }
}
