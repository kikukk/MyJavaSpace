package StudyDemo.wangke.Demo3;

import java.util.Scanner;
/**
 * 输入5位数，计算各位数之和
 * */
public class Work3_1 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int num = 0;
        int total = 0;
        String reward = new String();
        char choice = 'n';
        do {
            System.out.print("请输入一个五位数：");
            num = input.nextInt();
            while (num > 99999 || num < 10000) {
                System.out.print("输入数字范围错误！重新输入：");
                num = input.nextInt();
            }
            while (num > 0) {
                total += num % 10;
                num /= 10;
            }
            System.out.println("输入数字各位相加得：" + total);
            System.out.print("是否继续（Y/N):");
            choice = input.next().charAt(0);
        }while(choice == 'Y'||choice == 'y');
        if(total<15){
            /* 我们给满足条件的用户自行选择其他更多的礼品：
            “U盘”、“笔记本”、“纪念勋章”
            使用switch结构完成这个新增的功能
            */
            System.out.println("请选择奖品：");
            System.out.println("1：U盘");
            System.out.println("2：笔记本");
            System.out.println("3：纪念勋章");
            choice = input.next().charAt(0);
            switch(choice){
                case '1':
                    reward = "U盘";
                    break;
                case '2':
                    reward = "笔记本";
                    break;
                case '3':
                    reward = "纪念勋章";
                    break;
            }
            System.out.printf("恭喜获得%S一个",reward);
        }
    }
}
