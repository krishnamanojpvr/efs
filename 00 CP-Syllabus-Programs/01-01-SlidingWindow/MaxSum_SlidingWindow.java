/* Using Slidingwindow Approach-O(n) Time Complexity

Suppose the problem gives us an array of length ‘n’ and a number ‘k’. The problem asks us to 
find the maximum sum of ‘k’ consecutive elements inside the array


case=1
input=
6
5 2 4 6 3 1
3
output=
13


case=2
input=
9
1 2 3 4 5 6 7 8 9
4
output=
30


case=3
input=
8
4 -1 2 1 6 -5 3 2
3
output=
9

case=4
input=
5
1 2 3 4 5
1
output=
5

case=5
input=
4
-2 -3 -1 -4
2
output=
-4


*/
import java.util.*;

class Test2
 {
    public int maxSum(int a[],int k)
     {
        int n=a.length;
        
        int sum=0;
        
        for (int i=0; i<k; i++)
        {
            sum=sum+a[i];
        }
        
        answer=sum;
            
         for (int i=k;i<n;i++)
               {
                    sum =sum+a[i]-a[i-k];
               
           answer=Math.max(answer,sum);
        }
            
             return answer;
    }
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);

        int n=sc.nextInt();
         int a[]=new int[n];
          for(int i=0;i<n;i++)
          {
            a[i]=sc.nextInt();
          }
            int k=sc.nextInt();
           
            System.out.println(new Test().maxSum(a,k));
    }
 }
