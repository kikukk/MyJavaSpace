package ex3;

public class Circle {
    private double radius;
    public double getPerimeter(){
        return 2*Math.PI*radius;
    }
    public double getArea(){
        return Math.PI*Math.pow(radius, 2);
    }

    public void getAllInfo(){
        System.out.println("面积："+getArea());
        System.out.println("周长："+getPerimeter());
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }
}
