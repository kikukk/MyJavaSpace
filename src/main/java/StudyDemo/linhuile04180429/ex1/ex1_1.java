package ex1;

public class ex1_1 {
    public static void main(String[] args){
        for(int i = 50;i < 100;i++){
            for(int j = 2;j <= Math.sqrt(i);j++){
                if(i % j == 0)break;
                if(j >= Math.sqrt(i)-1){
                    System.out.println(i);
                    break;
                }
            }
        }
    }
}
