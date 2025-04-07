public class MinFlips {
    private static int helper(int a, int b, int c) {
        // * basic intuition : we are checking : a | b == c
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int bitC = ((c >> i) & 1);
            int bitA = ((a >> i) & 1);
            int bitB = ((b >> i) & 1);
            if ((bitA | bitB) != bitC) {
                ans += (bitC == 0) ? (bitA == 1 && bitB == 1) ? 2 : 1 : 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int a = 2;
        int b = 6;
        int c = 5;
        System.out.println("Min Flips required to make two numbers equal to third is : " +
                helper(a, b, c));
    }
}
