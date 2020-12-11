package StudyDemo.wangke.Demo3;

/**
 * 使用循环计算1-100的累加和
 * 需要循环变量
 *需要累加和变量
 * */
public class Demo3_1 {
    public static void main(String[] args){
        //写出循环基本结构
        int sum = 0;        //用来保存累加和
        int i = 1;
        while(i<=100)
        {
            sum += i;
            i++;
        }
        System.out.println(sum);

    }

}
