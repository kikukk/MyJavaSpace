package ex2;

public class ex2_1 {
    public static void main(String[] args){
        int num = 76;
        switch(num/10){
            case 9:
                System.out.println("优");
                break;
            case 8:
                System.out.println("良");
                break;
            case 7:
                System.out.println("中");
                break;
            case 6:
                System.out.println("及格");
                break;
            default:
                System.out.println("不及格");
        }
    }
}
