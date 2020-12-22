package StudyDemo.wangke.Demo6;

public class Work6_4 {
    public static void main(String[] args){
        /**假设有一个长度为5的数组：
         * int []array = new int[]{1, 3, -1, 5, -2};
         * 现在要创建一个新数组newArray[]，
         * 要求新数组中的元素与原数组中的元素逆序，
         * 并且如果原数组中的元素值小于0，
         * 在新数组中按0存储。
         * 编程输出新数组中的元素
         * */
        int[] array = new int[]{1,3,-1,5,-2};
        int[] nums = new int[array.length];
        for(int i = array.length-1,j = 0;i>=0;i--,j++){
            if(array[i]<0){
                nums[j] = 0;
            }
            else{
                nums[j] = array[i];
            }
        }
        System.out.print("新数组中的元素为：");
        for(int i = 0;i < nums.length;i++){
            System.out.print(nums[i]+",");
        }
    }
}
