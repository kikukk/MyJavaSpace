package ex4.asia.china.guangdong.zhuhai;

import ex4.asia.china.guangdong.Teacher;

public class TestTeacher {
    public static void main(String[] args){
        Teacher jilinTeacher = new Teacher("zhangsan",20,1,"jiangshi");
        jilinTeacher.showInfo();
        System.out.println("修改职称！");
        jilinTeacher.setJobTitle("jiaoshou");
        jilinTeacher.showInfo();
    }
}
