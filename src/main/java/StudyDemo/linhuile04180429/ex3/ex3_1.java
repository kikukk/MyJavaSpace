package ex3;

public class ex3_1 {
    public static void main(String[] args){
        System.out.println("测试矩形!");
        Rect rect1 = new Rect();
        double col = 3,row = 5;
        rect1.setCol(col);
        rect1.setRow(row);
        rect1.getAllInfo();
        System.out.println("测试圆形！");
        Circle circle1 = new Circle();
        circle1.setRadius(5);
        circle1.getAllInfo();
    }
}
