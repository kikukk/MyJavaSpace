package StudyDemo.wangke.Demo9;

public class Count {
    /**编写一个 Application，
     * 其中包含三个同名方法 mySqrt()
     * 它们都只有一个参数
     * 参数的类型分别为 int 型、float 型和 double 型
     * 它们的功能均为返回参数的平方根
     * 返回值的类型与参数的类型相同
     * 在方法 main( ) 中调用上面的三个方法并输出结果。
     * */
    public int mySqrt(int a){
        return (int)Math.sqrt(a);
    }
    public float mySqrt(float a){
        return (float)Math.sqrt(a);
    }
    public double mySqrt(double a){
        return (double)Math.sqrt(a);
    }
}
