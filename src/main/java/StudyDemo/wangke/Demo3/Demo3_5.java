package StudyDemo.wangke.Demo3;

public class Demo3_5 {
    public static void main(String[] args){
        System.out.println("星期一\t星期二\t星期三\t星期四\t星期五\t星期六\t星期日");
        System.out.print("\t\t");
        int dayOfMonth = 31;
        for(int i = 1;i < dayOfMonth;i++){
            System.out.print(i);
            //判断何时\t，何时\n（周日那天）
            //已知:1号是星期二
            if((i+1)%7==0){
                System.out.print("\n");
            }else{
                System.out.print("\t\t");
            }
        }


    }
}
