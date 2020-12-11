package StudyDemo.wangke.Demo10;

public class SingleModelDemo {
    /**
     *单例模式示例
     * */
    /**用来保存唯一的本类的实例*/
    private static SingleModelDemo me = null;
    public int count = 0;
    private SingleModelDemo(){
        count++;
    }
    /**
     * 获得本类唯一的一个实例--单例模式的核心方法
     * */
    public static SingleModelDemo getInstance(){
        if(null == me)
            me = new SingleModelDemo();
        return me;


    }


}
