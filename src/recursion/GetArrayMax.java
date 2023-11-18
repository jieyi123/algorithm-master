package recursion;

/**
 * @Author pjieyi
 * @Description 获取数组中的最大值 递归实现
 */
public class GetArrayMax {
    public static int getMax(int[] array){
        return process(array,0,array.length-1);
    }

    //递归过程
    //arr[L..R]范围上求最大值  L ... R   N
    public static int process(int[] arr,int L,int R){
        if (L==R){
            return arr[L];
        }
        int mid=L + ((R-L)>>1);
        int leftMax=process(arr,L,mid);
        int rightMax=process(arr,mid+1,R);
        return Math.max(leftMax,rightMax);
    }

    public static void main(String[] args) {
        int[] arr=new int[]{2,5,8,10,5,9,100};
        System.out.println(getMax(arr));
    }
}
