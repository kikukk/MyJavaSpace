#多线程笔记
同步线程的三种方法：
    同步代码段
    同步方法
    锁（注意死锁）

线程的创建
    Thread/Demo/thread/ThreadCreate
         //线程实例化
            //1.继承Thread类，做一个线程子类（自定义的线程类）
                MyThread mt = new MyThread()
                //注意：
                //需要调用start方法来启动类中的run方法
                mt.start()
             //2.通过Runnable接口
                Runnable r1 = ()->{
                  for(int i = 0;i < 10;i++) {
                      System.out.println("线程2中的逻辑：" + i);
                  }
                };
                Thread t2 = new Thread(r1);
                t2.start();
线程的方法
    Thread/Demo/thread/ThreadMethod
    //线程礼让，指的是让当前的运行状态的线程释放自己的CPU资源，由运行状态，回到就绪状态
        Thread.yield();
    //设置线程的优先级，只是修改这个线程可以去抢到CPU时间片的概率
    //并不是优先级高的线程一定能抢到CPU时间片
    //优先级的设置，是一个整数[0,10]的整数，默认是5
        thread.setPriority(10);
    //线程休眠
        thread.sleep(1000);
简单线程三窗口卖票程序：
    Thread/Demo/RunApp.java
简单多线程1000开始减，按键减50的程序
    Thread/Demo/window/Start
单例线程
    Thread/Demo/threadSingleton/SingletonTest.java
售票临界资源问题
    Thread/Demo/productorAndConsumer
    问题演示
        Thread/Demo/sourceconflict
    死锁演示
    DeadLock互相死锁，DeadLock2死锁后释放资源后释放锁
    使用同步锁
        //实例化四个售票员，用4个线程模拟4个售票员
        Runnable r = ()->{
            while(TicketCenter.restCount > 0){
                //对象锁
                synchronized (""){
                    if(TicketCenter.restCount <= 0){
                        return;
                    }
                    System.out.println(Thread.currentThread().getName()+"卖出了一张票，剩余"+ -- TicketCenter.restCount+"张");
                }
            }
        };
    使用同步方法
        Runnable r = ()->{
            while(TicketCenter.restCount > 0){
                soldTicket();

            }
        };
        private synchronized static void soldTicket(){
                //对象锁
                if (TicketCenter.restCount <= 0) {
                    return;
                }
                System.out.println(Thread.currentThread().getName() + "卖出了一张票，剩余" + --TicketCenter.restCount + "张");
            }
    给代码段上锁
        //实例化了一个锁对象
        ReentrantLock lock = new ReentrantLock();
        //实例化四个售票员，用4个线程模拟4个售票员
        Runnable r = ()->{
            //对临界资源上锁
            lock.lock();
            while(TicketCenter.restCount > 0){
                //对象锁
                synchronized (""){
                    if(TicketCenter.restCount <= 0){
                        return;
                    }
                    System.out.println(Thread.currentThread().getName()+"卖出了一张票，剩余"+ -- TicketCenter.restCount+"张");
                }
            }
            //对临界资源解锁
            lock.unlock();
        };
    生产者消费者问题
        Thread/Demo/productorAndConsumer