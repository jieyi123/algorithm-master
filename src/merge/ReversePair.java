package merge;

import java.awt.*;

import static sort.MergeSort.copyArr;
import static sort.MergeSort.randomArr;

/**
 * @Author pjieyi
 * @Description 归并排序求逆序对有多少个
 * 右边每个数比当前数小构成逆序对
 * [3,1,0,4,3,1]
 * 3 (3,1) (3,0) (3,1)
 * 1 (1,0)
 * 0 没有
 * 4 (4,3) (4,1)
 * 3 (3,1)
 */
public class ReversePair {

    public static int reversePair(int[] arr){
        if (arr==null || arr.length<2){
            return 0;
        }
       return process(arr,0,arr.length-1);
    }


    public static int process(int[] arr,int L,int R){
        if (L==R){
            return 0;
        }
        int mid=L+((R-L)>>1);
        return process(arr,L,mid)+process(arr,mid+1,R)+merge2(arr,L,mid,R);
    }



    //降序排序  相等的时候拷贝右边的
    public static int merge(int[] arr,int L,int mid,int R){
        int N=R-L+1;
        int[] help=new int[N];
        int result=0;
        int index=0;
        int p1=L;
        int p2=mid+1;

        while (p1<=mid && p2<=R){
            result += arr[p1]>arr[p2] ? R-p2+1 : 0;
            help[index++]= arr[p1]>arr[p2] ? arr[p1++] : arr[p2++];
        }

        while (p1<=mid){
            help[index++]=arr[p1++];
        }

        while (p2<=R){
            help[index++]=arr[p2++];
        }

        for (int i=0;i<N;i++){
            arr[L+i]= help[i];
        }
        return result;
    }

    //升序排序 从又往左拷贝  相等的时候拷贝右边的
    public static int merge2(int[] arr,int L,int mid,int R){
        int N=R-L+1;
        int[] help=new int[N];
        int result=0;
        int index= help.length-1;
        int p1=mid;
        int p2=R;
        while (p1>=L && p2>mid){
            result += arr[p1]>arr[p2] ?  p2-mid : 0;
            help[index--]= arr[p1]>arr[p2] ? arr[p1--] : arr[p2--];
        }

        while (p1>=L){
            help[index--]=arr[p1--];
        }

        while (p2>mid){
            help[index--]=arr[p2--];
        }

        for (int i=0;i<N;i++){
            arr[L+i]= help[i];
        }
        return result;
    }

    //暴力解法
    public static int reversePair2(int[] arr){
        if (arr==null || arr.length<2){
            return 0;
        }
        int result=0;
        for (int i=0;i<arr.length;i++){
            for (int j=i;j<arr.length;j++){
                result += arr[i]>arr[j] ? 1 : 0;
            }
        }
        return result;

    }

    public static void printArr(int[] arr){
        for (int i: arr){
            System.out.print(i+" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        //int[] arr={3,1,0,4,3,1};
        //System.out.println(reversePair2(arr));
        //printArr(arr);
        int testTime=10000;
        int len=20;
        int max=200;
        System.out.println("测试开始");
        for (int i=0;i<testTime;i++){
            int[] arr=randomArr(len,max);
            int[] arr2=copyArr(arr);
            int sm1 = reversePair(arr);
            int sm2 = reversePair2(arr2);
            if (sm1!=sm2){
                System.out.println("测试出错");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
