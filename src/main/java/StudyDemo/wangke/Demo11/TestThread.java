package StudyDemo.wangke.Demo11;

public class TestThread extends Thread {
    private int threadcount;
    private String threadName;

    TestThread(String threadName){
        this.threadName = threadName;
        threadcount = 0;
        this.start();
    }

    public void run(){
        System.out.println(threadName+"start");
        try{
            while(threadcount < 3){
                Thread.sleep(500);
                System.out.println(threadName+"："+threadcount);
                threadcount++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println(threadName+"中断！");
        }
        System.out.println(threadName+"已经完成！");
    }

    public static void main(String[] args) {
        Thread t = Thread.currentThread();
        System.out.println("主线程是："+t.getName()+","+t.getId()+","+t.getPriority());

        for(int i = 0;i < 3;i++){
            new TestThread("线程"+(i+1));
        }
        System.out.println("主线程已经结束！");
    }

}
