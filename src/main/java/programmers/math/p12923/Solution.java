package programmers.math.p12923;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        long[] begins = {1};
        long[] ends = {10};
        int[][] answers = {{0, 1, 1, 2, 1, 3, 1, 4, 3, 5}};
        for (int i = 0; i < begins.length; i++) {
            System.out.println(Arrays.equals(s.solution(begins[i], ends[i]), answers[i]));
        }
    }

    public int[] solution(long begin, long end) {
        int[] res = new int[(int)(end - begin + 1)];
        for (long p = begin; p <= end; p++) {
            res[(int)(p - begin)] = block(p);
        }
        return res;
    }

    public int block(long p) {
        if (p == 1) return 0;
        long r = 1;
        for (long i = 2; i * i <= p; i++) {
            if (p % i == 0) {
                if (p / i <= 10_000_000) return (int)(p / i);
                r = i;
            }
        }
        return (int) r;
    }
}