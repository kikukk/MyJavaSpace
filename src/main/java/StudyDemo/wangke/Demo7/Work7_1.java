package StudyDemo.wangke.Demo7;

import java.util.Arrays;
import java.util.Scanner;

public class Work7_1 {
    public static void main(String[] args){
        /**实现注册，要求注册用户名长度不能小于3，
         * 密码长度不能小于6，并且在注册时两次输入的密码必须相同。
         * 如果满足要求，则显示注册成功，
         * 否则提示错误，并重新输入
         * */
        System.out.println("注册系统");
        Scanner input = new Scanner(System.in);
        char[] name = new char[15];
        char[] password = new char[15];
        do{
            System.out.println("输入用户名：");
            name = input.nextLine().toCharArray();
            if(name.length<3||name.length>14){
                System.out.println("用户名过短/过长，重新输入！");
            }else {
                break;
            }
        }while(true);
        do{
            System.out.println("请输入密码：");
            password = input.nextLine().toCharArray();
            if(password.length<6||password.length>14){
                System.out.println("密码长度过长/过短，请重新输入！");
                continue;
            }else{
                System.out.println("请重新输入以确认:");
                char[] temp = input.nextLine().toCharArray();
                if(Arrays.equals(password,temp)){
                    System.out.println("注册成功！");
                    break;
                }else{
                    System.out.println("两次输入不一致！请重新输入密码！");
                    continue;
                }
            }
        }while(true);
    }
}