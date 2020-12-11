package ex6;

import java.util.Scanner;

public class ex6_2 {
    private static Scanner input;

    public static void main(String[] args) {
        input = new Scanner(System.in);
        double[] nums = new double[2];				//存放数据
        String tempStrs[] = new String[2];
        int j = 0;
        boolean isRight = false;
        do{
            isRight = true;
            tempStrs[0] = "";
            tempStrs[1] = "";
            System.out.println("输入参数：");
            String tempStr = input.nextLine();
            for(int i = 0;i < tempStr.length();i++){
                while(tempStr.charAt(i)!=' '){
                    tempStrs[j]+=tempStr.charAt(i++);
                }
                j++;
            }
            try{
                for(int i = 0;i < 2;i++){
                    nums[i] = Integer.parseInt(tempStrs[i]);
                }
            }catch(NumberFormatException e){
                System.out.println("请输入数字！");
                isRight = false;
            }
        }while(!isRight);

        for(int i = 0;i < nums.length;i++){
            System.out.println(nums[i]);
        }


    }
}
