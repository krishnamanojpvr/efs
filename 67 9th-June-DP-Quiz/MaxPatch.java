import java.util.*;
public class MaxPatch{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];
        int max = 0;
        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();
            max = Math.max(arr[i],max);
        }
        int LIMIT = max*max;
        boolean bool[] = new boolean[LIMIT];
        bool[0] = true;
        for(int i=0;i<LIMIT;i++){
            if(bool[i]){
                for(int p : arr){
                    if(i+p<LIMIT){
                        bool[i+p] = true;
                    }
                }
            }
        }
        for(int i=LIMIT-1;i>=0;i--){
            if(!bool[i]){
                System.out.println(i);
                break;
            }
        }
        System.out.printn(-1);
        sc.close();
    }
}