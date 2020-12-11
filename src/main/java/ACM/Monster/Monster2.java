package ACM.Monster;

import java.util.Scanner;

public class Monster2 {
    public static void main(String[] args) {
        long[] a = new long[58];
        a[1] = 1;
        a[2] = 2;
        a[3] = 3;
        for(int i = 4;i <= 55;i++){
            a[i] = a[i-1]+a[i-3];
        }
        Scanner input = new  Scanner(System.in);
        int b = input.nextInt();
        while(b != 0){
            System.out.println(a[b]);
            b = input.nextInt();
        }
    }
}
