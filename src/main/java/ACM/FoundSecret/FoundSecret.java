package ACM.FoundSecret;

import java.util.Scanner;

public class FoundSecret {
    public static void main(String[] args) {
        getNum();
    }

    private static void getNum(){
        Scanner input = new Scanner(System.in);
        String tmp = input.nextLine();
        while(!tmp.equals("")){
            int[] nums = new int[4];
            for(int i = 0;i < tmp.length();i++){
                if(tmp.charAt(i) == 'j')nums[0]++;
                if(tmp.charAt(i) == 'a')nums[1]++;
                if(tmp.charAt(i) == 'c')nums[2]++;
                if(tmp.charAt(i) == 'k')nums[3]++;
            }
            getResult(nums);
            tmp = input.nextLine();
        }

    }

    private static void getResult(int[] nums){
        int result = 0,tmp1,tmp2;
        tmp1 = nums[0]<nums[1]?nums[0]:nums[1];
        tmp2 = nums[2]<nums[3]?nums[2]:nums[3];
        result = tmp1<tmp2?tmp1:tmp2;
        if(result == 0) System.out.println("LOL");
        else System.out.println(result);
    }
}
