public class FirstSetBitPosition {
    // * Left Shift
    private static int helper(int n) {
        if (n == 0) {
            return 0;
        }
        int k = 1;
        while (true) {
            if ((n & (1 << (k - 1))) == 0) {
                k++;
            } else {
                return k;
            }
        }
    }

    // * Right Shift
    private static int anotherWay(int n) {
        if (n == 0)
            return 0;
        int position = 1;
        while ((n & 1) == 0) {
            n >>= 1;
            position++;
        }
        return position;
    }

    // * Left Shift
    private static int anotherAnotherWay(int n) {
        int position = 1;
        int mask = 1;

        if (n == 0) {
            return 0;
        }

        while ((n & mask) == 0) {
            mask <<= 1;
            position++;
        }
        return position;
    }

}