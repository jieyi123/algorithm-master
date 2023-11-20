package sort;


import java.util.Arrays;

/**
 * @Author pjieyi
 * @Description  归并排序
 */
public class MergeSort {

    //递归实现
    public static void mergeSort(int[] arr){
        if (arr==null || arr.length<2){
            return;
        }
        process(arr,0,arr.length-1);
    }


    // 请把arr[L..R]排有序
    // l...r N
    // T(N) = 2 * T(N / 2) + O(N)
    // O(N * logN)
    public static void process(int[] arr,int L,int R){
        if (L==R){
            return;
        }
        int mid=L+((R-L)>>1);
        process(arr,L,mid);
        process(arr,mid+1,R);
        merge(arr,L,mid,R);
    }


    public static void merge(int[] arr,int L,int M,int R){
        int[] copy=new int[R-L+1];
        int index=0;
        int p1=L;
        int p2=M+1;
        //谁小拷贝谁
        while (p1<=M && p2<=R){
            copy[index++]=arr[p1]<=arr[p2] ? arr[p1++] : arr[p2++];
        }
        //以下两种情况只会执行一种 要么p1越界了，要么p2越界了
        while (p1<=M){
            copy[index++]=arr[p1++];
        }
        while (p2<=R){
            copy[index++]=arr[p2++];
        }
        //将copy数组赋值给原数组
        for (int i=0;i<copy.length;i++){
            arr[L+i]=copy[i];
        }
    }


    //非递归实现
    public static void mergeSort2(int[] arr){
        if (arr==null || arr.length<2){
            return;
        }
        int mergeSize=1;
        int N=arr.length;
        while (mergeSize<N){
            //当前左组的第一个位置
            int L=0;
            //merge一组
            while (L < N){
                //L...M左组
                int M=L+mergeSize-1;
                if (M>=N){
                    break;
                }
                //M+1...R 右组
                //不够直接取N-1
                int R=Math.min(M+mergeSize,N-1);
                merge(arr,L,M,R);
                L=R+1;
            }
            //防止溢出
            if (mergeSize > N/2 ){
                break;
            }
            //每次乘以2的倍数
            mergeSize<<=1;
        }
    }

    //数组打印
    public static void printArr(int[] arr){
        for (int i:arr){
            System.out.print(i+" ");
        }
        System.out.println();
    }
    //对数器测试
    //返回一个数组arr 数组长度是[0,len-1]，arr中的每个值[-max+1,max-1]
    public static int[] randomArr(int len,int max){
        int size=(int)(Math.random()*len);
        int[] arr=new int[size];
        for (int i=0;i<size;i++){
            int num=((int)(Math.random()*max)-(int) (max * Math.random()));
            arr[i]=num;
        }
        return arr;
    }

    //复制数值
    public static int[] copyArr(int[] arr){
        int size= arr.length;
        int[] newArr=new int[size];
        for (int i=0;i< arr.length;i++){
            newArr[i]=arr[i];
        }
        return newArr;
    }

    //比较两个数组是否排好序
    public static boolean isEqual(int[] arr1,int[] arr2){
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1==null&&arr2==null){
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i=0;i<arr1.length;i++){
            if (arr1[i]!=arr2[i]){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int testTime=10000;
        int len=20;
        int max=200;
        System.out.println("测试开始");
        for (int i=0;i<testTime;i++){
            int[] arr=randomArr(len,max);
            int[] arr2=copyArr(arr);
            mergeSort(arr);
            mergeSort2(arr2);
            if(!isEqual(arr,arr2 )){
                System.out.println("排序出错");
                printArr(arr);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
