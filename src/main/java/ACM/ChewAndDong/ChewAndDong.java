package ACM.ChewAndDong;

import java.util.Scanner;

public class ChewAndDong {
    public static void main(String[] args) {
        getNum();
    }

    private static void getNum(){
        Scanner input = new Scanner(System.in);
        String tmp = input.nextLine();
        int[] nums;
        int times = 0;
        while(!tmp .equals("")){
            int num = Integer.parseInt(tmp);
            nums = new int[num];
            for(int i = 0;i < num;i++){
                nums[i] = input.nextInt();
                if(i >= 1&&nums[i] < nums[i-1])times++;
            }
            System.out.println(times);
            times = 0;
            tmp = input.nextLine();
            tmp = input.nextLine();
        }
    }
}
