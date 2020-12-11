package ACM.LegendaryNine;

import java.util.Scanner;

public class LegendaryNine {
    public static void main(String[] args) {
        getNum();
    }

    private static void getNum(){
        Scanner input = new Scanner(System.in);
        String temp;
        do{
            temp = input.nextLine();
            for(int i = 0;i < temp.length();i++){
                if(temp.charAt(i) == '#')return;
            }
            getResult(temp);
        }while(true);
    }

    private static void getResult(String temp){
        for(int i = 0;i < temp.length();i++){
            if(temp.charAt(i) == '9'){
                if(check(temp,i)){
                    String tmp = new String();
                    tmp += temp.substring(0,i);
                    tmp += "baka";
                    tmp += temp.substring(i+1);
                    temp = tmp;
                }
            }
        }
        System.out.println(temp);

    }

    private static boolean check(String temp,int index){

        int a = index-1,b = index+1;
        if(temp.length() == 1)return true;
        if(index == 0){
            if(temp.charAt(b)>='0'&&temp.charAt(b)<='9')return false;
            return true;
        }
        if(index == temp.length()-1){
            if(temp.charAt(a) >='0'&&temp.charAt(a) <= '9')return false;
            return true;
        }
        if((temp.charAt(a) >='0'&&temp.charAt(a) <= '9')||(temp.charAt(b)>='0'&&temp.charAt(b)<='9'))return false;
        return true;
    }

}
