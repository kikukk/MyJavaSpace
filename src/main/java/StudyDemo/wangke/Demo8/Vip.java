package StudyDemo.wangke.Demo8;

import java.util.Scanner;

public class Vip {
    /**
     * 定义一个会员类
     * 包含三个属性（编号，姓名，积分）均为私有的
     * 分别给这三个属性定义两个方法
     * 一个设置它的值，另一个获得它的值
     * 然后在一个测试类里试着调用
     * */
    private String ident = "111111";      //编号
    private String name = "默认会员";       //姓名
    private long score = 0;         //积分
    Scanner input = new Scanner(System.in);

    public Vip() {
    }

    public Vip(String ident, String name, long score) {
        this.ident = ident;
        this.name = name;
        this.score = score;
    }

    public void setIdent(String ident) {
        this.ident = ident;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setScore(long score) {
        this.score = score;
    }

    public String getIdent() {
        return ident;
    }
    public String getName() {
        return name;
    }
    public long getScore() {
        return score;
    }
    public void show(){
        System.out.print("编号："+this.getIdent()+"\t\t");
        System.out.print("姓名："+this.getName()+"\t\t");
        System.out.print("积分："+this.getScore()+"\n");

    }
}
