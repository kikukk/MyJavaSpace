package Thread.Demo.thread;

public class ThreadCreate {
    public static void main(String[] args) {
        //线程实例化
        //1.继承Thread类，做一个线程子类（自定义的线程类）
        MyThread mt = new MyThread();

        //注意：
        //需要调用start方法来启动类中的run方法
        mt.start();

        System.out.println("主线程中的逻辑执行结束了");

        //2.通过Runnable接口
        Runnable r1 = ()->{
          for(int i = 0;i < 10;i++) {
              System.out.println("线程2中的逻辑：" + i);
          }
        };
        Thread t2 = new Thread(r1);
        t2.start();


    }
}

class MyThread extends Thread{
    @Override
    public void run() {
        for(int i = 0;i < 10;i++){
            System.out.println("子线程中的逻辑：" + i);

        }
    }
}

