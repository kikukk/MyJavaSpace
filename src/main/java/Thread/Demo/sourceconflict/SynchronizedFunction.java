package Thread.Demo.sourceconflict;

public class SynchronizedFunction {
    public static void main(String[] args){
        //实例化四个售票员，用4个线程模拟4个售票员
        Runnable r = ()->{
            while(TicketCenter.restCount > 0){
                soldTicket();

            }
        };

        //四个线程，模拟四个售票员，用线程名字模拟售票员
        Thread t1 = new Thread(r,"thread-1");
        Thread t2 = new Thread(r,"thread-2");
        Thread t3 = new Thread(r,"thread-3");
        Thread t4 = new Thread(r,"thread-4");

        //开启线程
        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }

    /**
     * 同步的方法
     * 静态方法：同步锁就是 类锁 当前类.class
     * 非静态方法：同步锁 是 this
     * */
    private synchronized static void soldTicket(){
        //对象锁
        if (TicketCenter.restCount <= 0) {
            return;
        }
        System.out.println(Thread.currentThread().getName() + "卖出了一张票，剩余" + --TicketCenter.restCount + "张");
    }
}
