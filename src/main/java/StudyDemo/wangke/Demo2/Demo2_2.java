package StudyDemo.wangke.Demo2;

import java.util.Scanner;

public class Demo2_2 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.print("请输入金额：");
        double money = input.nextDouble();
        //处理元部分
        int yuan = (int)money;
        int num_10 = yuan/10;
        int num_5 = yuan%10/5;
        int num_1 = yuan%5;
        //处理角部分
        int jiao = ((int)(money*10))%10;
        int num_0_5 = jiao/5;
        int num_0_1 = jiao-5;
        if(jiao ==0)
            num_0_5 = num_0_1 = 0;


        System.out.println("十元纸币的数量："+num_10);
        System.out.println("五元纸币的数量："+num_5);
        System.out.println("一元纸币的数量："+num_1);
        System.out.println("五角纸币的数量："+num_0_5);
        System.out.println("一角纸币的数量："+num_0_1);
    }
}
