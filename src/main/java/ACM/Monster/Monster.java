package ACM.Monster;

import java.util.Scanner;

public class Monster {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[] listInt = new int[10];
        long [] result;
        //1209982081
        int max = 0;
        int cur = 0;
        int curInt;
        do{
            if(cur >= listInt.length-1)resize(listInt);
            curInt = input.nextInt();
            listInt[cur++] = curInt;
            if(max < curInt)max = curInt;
        }while(curInt!=0);

        result = getResult2(max);
        for(int i = 0;i < cur-1;i++){
            System.out.println(result[listInt[i]-1]);
        }
    }

    private static long[] getResult2(int max){
        long num4 = 1;
        long num1 = 0,num2 = 0,num3 = 0;
        long result[] = new long[max];
        result[0] = 1;
        for(int i = 1;i < max;i++){
            num4 += num3;
            num3 = num2;
            num2 = num1;
            num1 = num4;

            result[i] = num1+num2+num3+num4;
        }
        return result;
    }

    private static void resize(int[] num){
        int[] tmp = new int[num.length];
        System.arraycopy(num, 0, tmp, 0, num.length);
        num = new int[num.length*2];
        System.arraycopy(tmp, 0, num, 0, tmp.length);
    }
}
