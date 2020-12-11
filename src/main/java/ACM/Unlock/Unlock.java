package ACM.Unlock;

import java.util.Scanner;

public class Unlock {
    public static void main(String[] args) {
        getNum();
    }

    private static void getNum(){
        Scanner input = new Scanner(System.in);
        int num;
        String tmpNum = input.nextLine();
        int[] nums1;
        int[] nums2;
        while(tmpNum!=""){
            try{
                num = Integer.parseInt(tmpNum);
            }catch(NumberFormatException e){
                return;
            }
            nums1 = new int[num];
            nums2 = new int[num];
            tmpNum = input.nextLine();

            for(int i = 0;i < num;i++){
                nums1[i] = tmpNum.charAt(i);
            }

            tmpNum = input.nextLine();

            for(int i = 0;i < num;i++){
                nums2[i] = tmpNum.charAt(i);
            }

            getResult(nums1,nums2);
            tmpNum = input.nextLine();
        }
    }

    private static void getResult(int[] a,int[] b){
        int total = 0;
        for(int i = 0;i < a.length;i++){
            total += compare(a[i],b[i]);
        }
        System.out.println(total);
    }

    private static int compare(int a,int b){
        int tmp1 = (a-b+10)%10;
        int tmp2 = (b-a+10)%10;
        if(tmp1>tmp2)return tmp2;
        return tmp1;
    }
}
