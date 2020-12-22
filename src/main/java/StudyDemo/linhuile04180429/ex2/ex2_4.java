package ex2;

import java.util.Scanner;

public class ex2_4 {
    private static Scanner input;

    public static void main(String[] args){
        int num_1,num_2;
        char ch_1;
        input = new Scanner(System.in);
        System.out.println("输入第一个整数：");
        num_1 = input.nextInt();
        System.out.println("输入第二个整数：");
        num_2 = input.nextInt();
        System.out.println("输入要运行的运算：");
        ch_1 = input.next().charAt(0);
        switch(ch_1){
            case '+':
                num_1 = num_1+num_2;
                break;
            case '-':
                num_1 = num_1-num_2;
                break;
            case '*':
                num_1 = num_1*num_2;
                break;
            case '/':
                num_1 = num_1/num_2;
                break;
            case '%':
                num_1 = num_1%num_2;
                break;
            default:
                System.out.println("运算符错误！");
                return;
        }
        System.out.println("运行的结果为:"+num_1);
    }
}
