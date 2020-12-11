package ACM.TeaMeeting;

import java.util.Arrays;
import java.util.Scanner;

public class TeaMeeting {
    public static void main(String[] args) {
        getNum();
    }

    private static void getNum(){
        Scanner input = new Scanner(System.in);
        int round = input.nextInt();
        int num,V;
        int[] nums;
        for(int i = 0;i < round;i++){
            num = input.nextInt();
            V = input.nextInt();
            nums = new int[num*2];
            for(int j = 0;j < num*2;j++){
                nums[j] = input.nextInt();
            }
            getResult(num,V,nums);
        }
    }

    private static void getResult(int a,int V,int[] cup){
        Arrays.sort(cup);
        double minLar = (double)cup[a]/2;
        double minSma = (double)cup[0];
        double minV = (double)V/4;
        double min = minLar<minSma?minLar:minSma;
        min = min<minV?min:minV;
        min *= 3*a;
        System.out.println(String.format("%.6f", min));
    }
}
