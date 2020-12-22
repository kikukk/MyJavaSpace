package StudyDemo.wangke.Demo11;

public class Ring extends Thread{
    int threadcount = 0;
    double douTime = 0;
    Ring(double douTime){
        this.douTime = douTime;
        this.threadcount = 0;
        this.start();
    }

    public void run(){
        while(threadcount < 3){
            try {
                sleep((long)(douTime*100));
                System.out.println('\007');
                threadcount++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("\007");
        for(int i = 0;i < 3;i++){
            new Ring(10);
        }

    }
}
