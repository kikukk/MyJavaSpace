package StudyDemo.wangke.Demo6;

import java.util.Scanner;

public class Work6_2 {
    public static void main(String[] args){
        /**将原有积分进行备份作为历史数据，
         * 然后再进行新一年的积分累加。
         * 作为周年庆贺礼，赠送每位会员500积分，
         * 编写程序输出5名会员的积分情况
         * */
        int[] nums = new int[5];
        Scanner input = new Scanner(System.in);
        System.out.println("请输入5位会员的积分：");
        for(int i = 0;i <5;i++){
            System.out.printf("第%s位会员积分：",i+1);
            nums[i] = input.nextInt();
        }
        System.out.println("编号\t历史积分\t新年积分");
        for(int j = 0;j <5 ;j++){
            System.out.println((j+1)+"\t\t"+nums[j]+"\t\t\t"+(nums[j]+500));

        }
    }
}
