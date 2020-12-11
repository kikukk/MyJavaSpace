package ACM.KillMonster;

import java.util.Scanner;



public class KillMonster2 {
    public static void main(String[] args) {
        getNum();

    }

    private static int getResult(int[][] num){
        int result = 0;
        for(int i = 0;i < num.length;i++){
            result += ((num[i][2]-num[i][0]+1)*(num[i][3]-num[i][1]+1));
        }
        return result;
    }

    private static void getNum() {
        Scanner input = new Scanner(System.in);
        String strAmount = input.nextLine();
        int amount = 0;
        int[] result = new int[10];
        int curLen = 0;
        int[][] num;
        while (!strAmount.equals("")){
            amount = Integer.parseInt(strAmount);
            num = new int[amount][4];
            for (int i = 0; i < amount; i++) {
                for (int j = 0; j < 4; j++) {
                    num[i][j] = input.nextInt();
                }
            }
            System.out.println(getResult(num));
            strAmount = input.nextLine();
            strAmount = input.nextLine();
        }


    }


}
