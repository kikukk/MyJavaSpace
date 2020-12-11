package ACM.MusicCompare;

import java.util.Scanner;

public class MusicCompare {
    public static void main(String[] args) {
        getNum();
    }

    private static void getNum(){
        Scanner input = new Scanner(System.in);
        String curLen = input.nextLine();
        int len = 0;
        String [] result = new String[10];
        int amount = 0;
        while(!curLen.equals("")){
            len = Integer.parseInt(curLen);
            int[] a = new int[len];
            int[] b = new int[len];
            for(int i = 0;i < len;i++){
                a[i] = input.nextInt();
            }
            for(int i = 0;i < len;i++){
                b[i] = input.nextInt();
            }
            result[amount++] = compare(a,b);
            curLen = input.nextLine();
            curLen = input.nextLine();
        }
        for(int i = 0;i < amount;i++){
            System.out.print(result[i]);
            if(i < amount-1){
                System.out.println("");
            }
        }
    }

    private static String compare(int[] a,int[] b){
        int len = a.length;
        int[] max = new int[len];
        int[] cur = new int[len];
        int maxLen = 0;
        int curMax = 0;
        for(int i = 0;i < len;i++){
            for(int j = 0;j < len;j++){
                if(a[i] == b[j]){
                    int temp = i;
                    while(i < len&&j < len&&a[i] == b[j]){
                        cur[curMax++] = a[i];
                        i++;j++;
                    }
                    if(curMax>maxLen){
                        max = cur;
                        maxLen = curMax;

                    }
                    cur = new int[len];
                    curMax = 0;
                    i = temp;
                }
            }
        }
        return showResult(max,maxLen);
    }

    private static String showResult(int[] max,int maxLen){
        if(maxLen == max.length){
            return new String("^_^");
        }else if(maxLen > 0&&maxLen < max.length){
            String temp = new String();
            for(int i = 0;i < maxLen;i++){
                temp+=max[i];
                if(i < maxLen-1){
                    temp += " ";
                }
            }
            return temp;
        }else {
            return new String("T_T");
        }
    }
}
