package merge;

/**
 * @Author pjieyi
 * @Description  327. 区间和的个数
 */
public class CountRangeSum {



    public static int countRangeSum(int[] arr,int lower,int upper){
        if (arr==null || arr.length==0){
            return 0;
        }
        int N=arr.length;
        //前缀数组的和
        long[] sumArr=new long[N];
        sumArr[0]=arr[0];
        for (int i=1;i<N;i++){
            sumArr[i]=sumArr[i-1]+arr[i];
        }
        return process(sumArr,0,N-1,lower,upper);
    }

    public static int process(long[] sum,int L,int R,int lower,int upper){
        if (L==R){
            return sum[L]>=lower && sum[L]<=upper ? 1 : 0;
        }

        int M=L+((R-L)>>1);
        return process(sum,L,M,lower,upper)+process(sum,M+1,R,lower,upper)+merge(sum,L,M,R,lower,upper);
    }


    public static int merge(long[] sum,int L,int M,int R,int lower,int upper){
        int ans=0;
        int windowL=L;
        int windowR=L;
        for (int i=M+1;i<=R;i++){
            long min=sum[i]-upper;
            long max=sum[i]-lower;
            while (windowR<=M && sum[windowR]<=max  ){
                windowR++;
            }
            while ( windowL<=M && sum[windowL]<min ){
                windowL++;
            }
            ans+=windowR-windowL;
        }

        int p1=L;
        int p2=M+1;
        int N=R-L+1;
        long[] help=new long[N];
        int index=0;
        while (p1<=M && p2<=R){
            help[index++]=sum[p1]<=sum[p2] ? sum[p1++]: sum[p2++];
        }
        while (p1<=M){
            help[index++]=sum[p1++];
        }
        while (p2<=R){
            help[index++]=sum[p2++];
        }
        for (int i=0;i<N;i++){
            sum[L+i]=help[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr={-2,5,-1};
        System.out.println(countRangeSum(arr,-2,2));
    }


}
