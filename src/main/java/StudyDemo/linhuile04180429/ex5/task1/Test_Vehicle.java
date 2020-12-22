package ex5.task1;

public class Test_Vehicle {
    public static void main(String[] args){
        Vehicle newCar = new Car();
        Vehicle newBike = new Bike();
        System.out.println("启动！");
        newCar.start();
        newBike.start();
        System.out.println("停止！");
        newCar.stop();
        newBike.stop();
    }
}
