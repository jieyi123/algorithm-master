package sort;

import java.util.Stack;

import static sort.MergeSort.*;

/**
 * @Author pjieyi
 * @Description 快速排序
 */
public class QuickSort {
    public static void swap(int[] arr,int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }

    // arr[L..R]上，以arr[R]位置的数做划分值
    // <= X 放左边  >X 放右边
    public static int partition(int[] arr,int L,int R){
        if (L>R){
            return -1;
        }
        if (L==R){
            return L;
        }
        int lessEqual=L-1;
        int index=L;
        while (index<R){
            if (arr[index]<=arr[R]){
                swap(arr,index,++lessEqual);
            }
            index++;
        }
        swap(arr,++lessEqual,R);
        return lessEqual;
    }

    public static void quickSort1(int[] arr){
        if (arr==null || arr.length<2){
            return;
        }
        process(arr,0, arr.length-1);
    }

    public static void process(int[] arr,int L,int R){
        if (L>=R){
            return ;
        }
        int m=partition(arr,L,R);
        process(arr,L,m-1);
        process(arr,m+1,R);
    }

    // arr[L...R] 玩荷兰国旗问题的划分，以arr[R]做划分值
    // <arr[R]左 ==arr[R]中  > arr[R]右
    //返回值return[0] arr中第一个==arr[R]的索引
    //return[1] arr中最后一个==arr[R]的索引
    public static int[] netherlandsFlag(int[] arr,int l,int r){
        if (l>r){
            return new int[]{-1,-1};
        }
        if (l==r){
            return new int[]{l,r};
        }
        int less=l-1; //<区
        int more=r; // >区
        int index=l;
        while (index<more){ // 当前位置，不能和 >区的左边界撞上
            if (arr[index]<arr[r]){
                swap(arr,index++,++less);
            } else if (arr[index]>arr[r]) {
                swap(arr,index,--more);
            }else{
                index++;
            }
        }
        swap(arr,more,r);
        return new int[]{less+1,more};
    }


    //快速排序2.0
    public static void quickSort2(int[] arr){
        if (arr==null || arr.length<2){
            return;
        }
        process2(arr,0,arr.length-1);
    }

    private static void process2(int[] arr, int L, int R) {
        if (L>=R){
            return;
        }
       int[] equalArea= netherlandsFlag(arr,L,R);
       process2(arr,L,equalArea[0]-1);
       process(arr,equalArea[1]+1,R);
    }

    //快速排序3.0  随机快速排序
    public static void quickSort3(int[] arr){
        if (arr==null || arr.length<2){
            return;
        }
        process3(arr,0,arr.length-1);
    }

    private static void process3(int[] arr, int L, int R) {
        if (L>=R){
            return;
        }
        swap(arr,(int)(Math.random()*(R-L+1)),R);
        int[] equalArea= netherlandsFlag(arr,L,R);
        process2(arr,L,equalArea[0]-1);
        process(arr,equalArea[1]+1,R);
    }

    public static class Job{
        private int L;
        private int R;
        public Job(int left,int right){
            L=left;
            R=right;
        }
    }

    //快速排序非递归实现
    public static void quickSort4(int[] arr){
        if (arr==null || arr.length<2){
            return;
        }
        Stack<Job>  stack=new Stack<>();
        stack.push(new Job(0,arr.length-1));
        while (! stack.isEmpty()){
            Job job=stack.pop();
            int[] areaEqual = netherlandsFlag(arr, job.L, job.R);
            if (areaEqual[0]> job.L){// 有 < 区域
                stack.push(new Job( job.L,areaEqual[0]-1));
            }
            if (areaEqual[1]< job.R){ // 有 > 区域
                stack.push(new Job(areaEqual[1]+1,job.R));
            }
        }

    }


    public static void main(String[] args) {
        int testTime=1000;
        int max=100;
        int len=10;
        System.out.println("测试开始");
        for (int i=0;i<testTime;i++){
            int[] arr=randomArr(len,max);
            int[] arr2=copyArr(arr);
            quickSort4(arr);
            quickSort3(arr2);
            if(!isEqual(arr,arr2 )){
                System.out.println("排序出错");
                printArr(arr2);
                System.out.println();
                printArr(arr);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
