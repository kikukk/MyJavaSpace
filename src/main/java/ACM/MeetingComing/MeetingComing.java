package ACM.MeetingComing;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class MeetingComing {
    private int fun;
    private int pay;
    private double rate;
    MeetingComing(int b,int c){
        fun = b;pay = c;
        rate = (double)b/(double)c;
    }

    private double getRate(){
        return rate;
    }

    public static void main(String[] args) {
        getNum();
    }

    private void show(){
        System.out.println(fun+"\t"+pay+"\t"+rate);
    }

    private static void getNum(){
        Scanner input = new Scanner(System.in);
        int numPeople = input.nextInt();
        int numInside = input.nextInt();
        int numOutside = input.nextInt();
        int inside = 0;
        int outside = 0;
        int money = input.nextInt();
        MeetingComing[] numsInsidePeople = new MeetingComing[numPeople];
        MeetingComing[] insidePeople = new MeetingComing[numInside];
        MeetingComing[] numsOutsidePeople = new MeetingComing[numPeople];
        MeetingComing[] outsidePeople = new MeetingComing[numOutside];

        int tmp2,tmp3;
        char tmp1;
        for(int i = 0;i < numPeople;i++){
            tmp1 = input.next().charAt(0);
            tmp2 = input.nextInt();
            tmp3 = input.nextInt();
            if(tmp1 == 'I')numsInsidePeople[inside++] = new MeetingComing(tmp2,tmp3);
            else numsOutsidePeople[outside++] = new MeetingComing(tmp2,tmp3);
        }

        MeetingComing[] tmp = numsInsidePeople;
        numsInsidePeople = new MeetingComing[inside];
        for(int i = 0;i < inside;i++)
            numsInsidePeople[i] = tmp[i];

        tmp = numsOutsidePeople;
        numsOutsidePeople = new MeetingComing[outside];
        for(int i = 0;i < outside;i++)
            numsOutsidePeople[i] = tmp[i];

        Arrays.sort(numsInsidePeople, (o1, o2) -> {
            if(o1.getRate()<o2.getRate())return 1;
            else if(o1.getRate() == o2.rate)return 0;
            else return -1;
        });
        Arrays.sort(numsOutsidePeople, (o1, o2) -> {
            if(o1.getRate()<o2.getRate())return 1;
            else if(o1.getRate() == o2.rate)return 0;
            else return -1;
        });
    }
}
