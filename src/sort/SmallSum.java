package sort;

import static sort.MergeSort.copyArr;
import static sort.MergeSort.randomArr;

/**
 * @Author pjieyi
 * @Description 最小和
 */
public class SmallSum {

    //arr[i]左边的数加起来比arr[i]还要小

    //a[3 6 2 1 6     7]
    //  0 3 0 0 3+2+1 3+2+1+6  =27
    public static int smallSum(int[] arr){
        if (arr==null || arr.length<2){
            return 0;
        }
        return process(arr,0,arr.length-1);
    }

    private static int process(int[] arr, int L, int R) {
        if (L==R){
            return 0;
        }
        int mid=L+((R-L)>>1);

        return process(arr,L,mid)+process(arr,mid+1,R)+merge(arr,L,mid,R);
    }

    //暴力解法
    public static int smallSum2(int[] arr){
        int result=0;
        for (int i=1;i<arr.length;i++){
            for (int j=0;j<i;j++){
                result+=arr[j]<arr[i]?arr[j]: 0;
            }
        }
        return result;
    }

    public static int merge(int[] arr,int L,int mid,int R){
        //拷贝右边的时候不产生小和
        //左组拷贝产生小和时，  右组有多少个数比这个数大R-p2+1
        int index=0;
        int N= R-L+1;
        int[] copy=new int[N];
        int result=0;
        int p1=L;
        int p2=mid+1;
        while (p1<=mid && p2<=R){
            //谁小拷贝谁，相等的时候拷贝右组的
            result+=arr[p1]<arr[p2] ? arr[p1]*(R-p2+1) : 0;
            copy[index++]=arr[p1]<arr[p2] ? arr[p1++]: arr[p2++];
        }
        while (p1<=mid){
            copy[index++]=arr[p1++];
        }
        while (p2<=R){
            copy[index++]=arr[p2++];
        }
        for (int i=0;i< N;i++){
            arr[L+i]=copy[i];
        }

        return result;
    }

    public static void main(String[] args) {
        int testTime=10000;
        int len=20;
        int max=200;
        System.out.println("测试开始");
        for (int i=0;i<testTime;i++){
            int[] arr=randomArr(len,max);
            int[] arr2=copyArr(arr);
            int sm1 = smallSum(arr);
            int sm2 = smallSum2(arr2);
            if (sm1!=sm2){
                System.out.println("测试出错");
                break;
            }
        }
        System.out.println("测试结束");
    }

}
