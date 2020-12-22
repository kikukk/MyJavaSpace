package Thread.Demo.thread;

/**
 * @author 14417
 */
public class ThreadMethod {
    public static void main(String[] args) {
        threadYield();
//        threadPriority();
    }
    //线程的礼让
    private static void threadYield(){
        //线程礼让，指的是让当前的运行状态的线程释放自己的CPU资源，由运行状态，回到就绪状态
        Runnable r = new Runnable() {
            @Override
            public void run() {
                for(int i = 0;i < 10;i++){
                    System.out.println(Thread.currentThread().getName()+':'+i);
                    if(i == 3){
                        Thread.yield();
                    }
                }
            }
        };

        Thread thread1 = new Thread(r,"thread-1");
        Thread thread2 = new Thread(r,"thread-2");

        thread1.start();
        thread2.start();
    }


    //设置线程的优先级
    private static void threadPriority(){
        //设置线程的优先级，只是修改这个线程可以去抢到CPU时间片的概率
        //并不是优先级高的线程一定能抢到CPU时间片
        //优先级的设置，是一个整数[0,10]的整数，默认是5
        Runnable r = ()->{
          for(int i = 0;i < 100;i++) {
              System.out.println(Thread.currentThread().getName()+":"+i);
          }
        };
        Thread t1 = new Thread(r,"Thread-1");
        Thread t2 = new Thread(r,"Thread-2");

        //设置优先级
        //设置优先级必须要放到这个线程开始执行（start）之前

        t1.setPriority(10);
        t2.setPriority(1);

        t1.start();
        t2.start();

    }

    //线程休眠
    private static void threadSleep(){
        //1.实例化一个线程对象
        MyThread2 t = new MyThread2();
        t.start();
    }

    //线程的命名
    private static void threadName(){
        //1.实例化一个线程对象
        //Thread t = new Thread();
        //t.setName("custom");

        //2.实例化一个线程对象同时，通过构造方法对线程进行命令
        //Thread(Runnable r,String name);
        //Thread t = new Thread(()->{},"custom");


        //3.使用自定义的线程类，在实例化线程对象的同时，进行名称的赋值
        //  需要给线程类添加对应的构造方法
        MyThread2 t = new MyThread2("custom");

        System.out.println(t.getName());


    }
}

class MyThread2 extends Thread{
    public MyThread2(){}
    public MyThread2(String name){
        //super(name);
        this.setName(name);
    }

    @Override
    public void run() {
        for(int i = 0;i < 10;i++){
            System.out.println(i);
            //线程休眠
            //1.参数：毫秒为单位的时间差
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

