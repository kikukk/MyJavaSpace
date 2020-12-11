package Thread.Demo.threadSingleton;

/**
 * @author 14417
 */
public class SingletonTest {
    public static void main(String[] args) {
        Runnable runnable = ()->{
            Boss.getBoss();
        };

        for(int i = 0;i < 10;i++){
            new Thread(runnable).start();
        }
    }
}

class Boss{
    private  Boss(){
        System.out.println("一个Boss对象被实例化了");
    }
    private static Boss Instance = null;

//    public static Boss getBoss(){
//        synchronized (""){
//            if(Instance == null){
//                Instance = new Boss();
//            }
//            return Instance;
//        }
//    }
    public static synchronized Boss getBoss(){
        if(Instance == null){
            Instance = new Boss();
        }
        return Instance;
    }
}
