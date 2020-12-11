package StudyDemo.wangke.Demo4;

public class Demo4_3 {
    public static void main(String[] args) {
        int total = 0;
        for(int i = 2;i <= 100;i++){
            if(isPrime(i)){
                System.out.print(i+"\t");
                total+=i;
            }
        }
        System.out.println("\n总和为："+total);
    }
    private static boolean isPrime(int a){
        for(int i = 2;i <= Math.sqrt((double)a);i++){
            if(a%i == 0)
                return false;
        }
        return true;
    }
}

