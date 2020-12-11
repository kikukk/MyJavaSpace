package ACM.MagicalGirl;

import java.util.Scanner;

public class MagicalGirl2 {
    public static void main(String[] args) {
        int len;
        String strlen;
        Scanner input = new Scanner(System.in);
        strlen = input.nextLine();
        while(!strlen.equals("")){
            len = Integer.parseInt(strlen);
            int[][] a = new int[len][len];
            int[][] b = new int[len][len];
            for(int i = 0;i < len;i++){
                for(int j = 0;j < len;j++){
                    a[i][j] = input.nextInt();
                }
            }
            for(int i = 0;i < len;i++){
                for(int j = 0;j < len;j++){
                    b[i][j] = input.nextInt();
                }
            }
            System.out.println(compare(a,b,4));
            strlen = input.nextLine();
            strlen = input.nextLine();
        }
    }
    private static int compare(int[][]a,int[][] b,int time){
        int same = 0;
        int len = a.length;
        for(int i = 0;i < len;i++){
            for(int j = 0;j < len;j++){
                if(a[i][j] == b[i][j])same+=a[i][j];
            }
        }
        if(time == 1)return same;
        return same+compare(reverse(a),b,time-1);
    }

    private static int[][] reverse(int[][] a){
        int len = a.length;
        int[][] b;
        b = new int[len][len];
        for(int i = 0;i < a.length;i++) System.arraycopy(a[i], 0, b[i], 0, a.length);
        for(int i = 0;i < len;i++){
            for(int j = 0;j < len;j++){
                a[i][j] = b[j][len-1-i];

            }
        }
        return a;
    }
}
