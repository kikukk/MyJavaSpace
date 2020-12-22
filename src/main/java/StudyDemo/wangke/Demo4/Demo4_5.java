package StudyDemo.wangke.Demo4;

public class Demo4_5 {
    public static void main(String[] args) {
        int tmpInt;
        for(int i = 0;i < 10000;i++){
            tmpInt = (int)(Math.random()*20+0.5);
            System.out.println(tmpInt);
        }
    }
}
