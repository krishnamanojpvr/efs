import java.util.*;
public class BuyAndSellStocksUpdated{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int w = sc.nextInt();
        int arr[][] = new int[n][2];
        for(int i=0;i<n;i++){
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
        }
        
        int memo[][] = new int[n][w+1];
        for(int i[] : memo){
            Arrays.fill(i,-1);
        }
        System.out.println(helper(arr,memo,n,w,w,0,0));
        sc.close();
        
    }
    public static int helper(int arr[][],int memo[][], int n,int maxw,int remw,int maxh,int i){
        if(i==arr.length) return maxh;
        if(memo[i][remw]!=-1) return memo[i][remw];
        
        int nextrow = maxh + helper(arr,memo,n,maxw,maxw-arr[i][0],arr[i][1],i+1);
        int currrow = Integer.MAX_VALUE;
        if(remw>=arr[i][0]) currrow = helper(arr,memo,n,maxw,remw-arr[i][0],Math.max(maxh,arr[i][1]),i+1);
        return memo[i][remw] = Math.min(nextrow,currrow);
    }
}