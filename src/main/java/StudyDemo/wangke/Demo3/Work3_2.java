package StudyDemo.wangke.Demo3;

public class Work3_2 {
    public static void main(String[] args){
        /**
         * 编程计算在1000~9999中，
         * 个位数和百位数之和大于16,
         * 十位数和千位数之和小于3
         * 打印出最终的个数
         * */
        int total = 0;
        for(int i=1000;i < 9999;i++){
            if(((i%10+i%1000/100)>16)&&(((i%100/10+i/1000)<3))) {
                System.out.print(i+"\t");
                total++;
                continue;
            }
        }
        System.out.printf("\n满足条件的数共有%s个",total);
    }
}
