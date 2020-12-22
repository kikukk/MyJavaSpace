package StudyDemo.wangke.Demo3;

import java.util.Scanner;
/**
 * 小型计算器
 * */
public class Demo3_4 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        double num_1,num_2,result;//两个操作数和一个结果变量
        String op = "";
        String choice = null;
        do{
            System.out.println("请输入两个操作数：");
            num_1 = input.nextDouble();
            num_2 = input.nextDouble();
            System.out.println("请输入运算符：");
            op = input.next();
            //根据运算符的判断，进行相应的计算
            switch(op){
                case "+":
                    result = num_1 + num_2;
                    break;
                case "-":
                    result = num_1 - num_2;
                    break;
                case "*":
                    result = num_1 * num_2;
                    break;
                case "/":
                    //需要判断被除数是否为0
                    if(num_2 == 0){
                        System.out.println("除数不能为0!");
                        result = 0;
                    }else{
                        result = num_1/num_2;
                    }
                    break;
                case "%":
                    result = num_1 % num_2;
                    break;
                default:
                    System.out.println("为止运算符，运算失败！");
                    result = 0;
                    break;
            }
            //打印运算结果
            System.out.printf("%.2f %s %.2f = %.2f\n",num_1,op,num_2,result);
            System.out.print("是否继续运算？(y/n)");
            choice = input.next();
        }while ("y".equals(choice));

    }
}
