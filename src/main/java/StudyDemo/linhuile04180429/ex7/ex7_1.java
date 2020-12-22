package ex7;

public class ex7_1 {
    public static void main(String[] args) {
        String tempStr = "www.google.com";
        System.out.println("数组长度："+tempStr.length());
        int num_1 = 0,num_2 = 0;

        for(char i : tempStr.toCharArray()){
            if(i == 'o'){
                num_1++;
            }
            if(i == 'g'){
                num_2++;
            }
        }
        System.out.println("o的个数："+ num_1);
        System.out.println("g的个数："+ num_2);
        System.out.println(tempStr.subSequence(4,10));

    }
}
/*
 * 输出字符串“www.google.com”的长度，
 * 并分别计算并显示出‘o’ 与‘g’的个数，
 * 截取其中“google”进行输出显示。
 * */