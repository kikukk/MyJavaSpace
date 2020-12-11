package StudyDemo.wangke.Demo6;

public class Work6 {
    public static void main(String[] args){
        String[] nums_1 = { "自学宝典", "大礼包","文化衫","会员"};
        System.out.println("本次活动的特价产品有：");
        for(int i = 1;i <5;i++){
            System.out.printf("%s.%S\n",i,nums_1[i-1]);
        }
    }
}
