package StudyDemo.wangke.Demo9;

import java.util.Scanner;

public class Work9_1 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        Count count = new Count();
        int choice_1 = 0;
        do {
            System.out.println("选择要输入的数据类型:");
            System.out.println("1:整形int");
            System.out.println("2：单精度浮点型float");
            System.out.println("3：双精度浮点型double");
            choice_1 = input.nextInt();
            if(choice_1<1||choice_1>3){
                System.out.println("无此选项,重新选择!");
            }
        }while(choice_1<1||choice_1>3);
        switch (choice_1){
            case 1:
                System.out.println("输入一个整形数字：");
                int num_1 = input.nextInt();
                num_1 = count.mySqrt(num_1);
                System.out.println("调用mySqrt()方法的结果为："+num_1);
                break;
            case 2:
                System.out.println("输入一个单精度浮点型数字：");
                float num_2 = input.nextFloat();
                num_2 = count.mySqrt(num_2);
                System.out.println("调用mySqrt()方法的结果为："+num_2);
                break;
            case 3:
                System.out.println("输入一个双精度浮点型数字：");
                double num_3 = input.nextDouble();
                num_3 = count.mySqrt(num_3);
                System.out.println("调用mySqrt()方法的结果为："+num_3);
                break;

        }


    }
}
