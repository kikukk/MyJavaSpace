package ACM.Supermarket;

import java.util.Scanner;

public class Supermarket {
    public static void main(String[] args) {
        int[] result = getResult(getNum());
        for(int i = 0;i < result.length;i++){
            System.out.println(result[i]);
        }
    }

    private static int[][] getNum(){
        Scanner input = new Scanner(System.in);
        int numTest = input.nextInt();
        int[][] nums = new int[numTest][2];
        for(int i = 0;i < numTest;i++){
            for(int j = 0;j < 2;j++){
                nums[i][j] = input.nextInt();
            }
        }
        return nums;
    }

    private static int[] getResult(int[][] nums){
        int[] result = new int[nums.length];
        for(int i = 0;i < result.length;i++){
            result[i] = calculate(nums[i][0],nums[i][1]);
        }
        return result;
    }

    private static int calculate(int a,int b){
        int result = 0;
        int times = 0;
        while(a > 0){
            a--;
            result++;
            times++;
            if(times == b){
                a++;
                times = 0;
            }
        }
        return result;
    }
}
