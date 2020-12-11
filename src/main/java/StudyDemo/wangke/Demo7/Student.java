package StudyDemo.wangke.Demo7;

public class Student {
    //学号
    private int number;
    //姓名
    private String name;
    public Student(int a,String b){
        number = a;
        name = b;
    }
    public void show(){
        System.out.println("学号："+ this.number);
        System.out.println("姓名："+ this.name);
    }
}
