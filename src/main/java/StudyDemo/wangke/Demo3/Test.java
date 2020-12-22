package StudyDemo.wangke.Demo3;

import java.util.Random;

public class Test {
    public static void main(String[] args){

        Random random = new Random();
        int temp_1 = 0;
        int i = 0;
        while(i<10){
            temp_1 = random.nextInt(5);
            System.out.print(temp_1+" ");
            i++;
        }
    }

}
