package merge;

import static sort.MergeSort.copyArr;
import static sort.MergeSort.randomArr;

/**
 * @Author pjieyi
 * @Description num右边每个数乘2后依然 < num
 * [6,7,1,3,2]
 * 6 右边有1,2满足   1*2 和 2*2 都是<6
 * 7 右边1,3,2满足  1*2 3*2 2*2 都是< 6
 * 1  右边0个
 * 3 右边0个
 * 2 0个
 */
public class BiggerThanRightTwice {


    public  static int bigTwice(int[] arr){
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
        return process(arr,L,mid)+process(arr,mid+1,R)+merge(arr,L,mid,R);
    }


    public static int merge(int[] arr,int L,int mid,int R){
        int ans=0;
        int windowR=mid+1;
        for (int i=L;i<=mid;i++){
            while (windowR<=R && arr[i]>(arr[windowR]*2) ){
                windowR++;
            }
            ans+=windowR-mid-1;
        }


        int N=R-L+1;
        int[] help=new int[N];
        int index=0;
        int p1=L;
        int p2=mid+1;
        while (p1<=L && p2<=R){
            help[index++]= arr[p1]<=arr[p2] ? arr[p1++] : arr[p2++];
        }

        while (p1<=mid){
            help[index++]=arr[p1++];
        }
        while (p2<=R){
            help[index++]=arr[p2++];
        }

        for (int i=0;i< help.length;i++){
            arr[L+i]=help[i];
        }
        return ans;
    }

    //暴力求解
    public static int bigTwice2(int[] arr){
        if (arr==null || arr.length<2){
            return 0;
        }
        int ans=0;
        for (int i=0;i<arr.length;i++){
            for (int j=i+1;j< arr.length;j++){
                ans+= arr[i]> (arr[j]*2) ? 1 : 0;
            }
        }
        return ans;
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
    public static void main(String[] args) {
        //int[] arr=new int[]{6,7,1,3,2};
        //System.out.println(bigTwice2(arr));
        int testTime=10000;
        int len=20;
        int max=200;
        System.out.println("测试开始");
        for (int i=0;i<testTime;i++){
            int[] arr=randomArr(len,max);
            int[] arr2=copyArr(arr);
            int num1 = bigTwice(arr);
            int num2 = bigTwice2(arr2);
            if (num1 != num2){
                System.out.println("测试出错");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
