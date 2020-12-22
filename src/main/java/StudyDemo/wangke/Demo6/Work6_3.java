package StudyDemo.wangke.Demo6;

public class Work6_3 {
    public static void main(String[] args){
        /**有一列乱序的字符：
         *‘a’,'c','u','b','e','p','f','z'，
         *用程序实现按照英文字母表的升序和逆序输出。
         **/
        char[] nums = {'a','c','u','b','e','p','f','z'};
        System.out.print("原字符序列：");
        for(int i = 0;i < nums.length;i++){
            System.out.print(nums[i]+",");
        }
        for(int i = 0;i < nums.length;i++){
            for(int j = i+1;j < nums.length;j++){
                if(nums[i]>nums[j]){
                    char k = nums[i];
                    nums[i] = nums[j];
                    nums[j] = k;
                }
            }
        }
        System.out.print("\n升序排列后：");
        for(int i = 0;i < nums.length;i++){
            System.out.print(nums[i]+",");
        }
        System.out.print("\n降序排列后：");
        for(int i = nums.length-1;i>=0;i--){
            System.out.print(nums[i]+",");
        }

    }
}
