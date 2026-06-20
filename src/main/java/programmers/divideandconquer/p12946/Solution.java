package programmers.divideandconquer.p12946;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] inputs = {2};
        int[][][] answers = {{{1, 2}, {1, 3}, {2, 3}}};
        for (int i = 0; i < inputs.length; i++) {
            System.out.println(Arrays.deepEquals(s.solution(inputs[i]), answers[i]));
        }
    }

    public int[][] solution(int n) {
        int[][] dp = {{1, 3}};
        for (int i = 2; i <= n; i++) {
            int[][] next = new int[(1 << i) - 1][2];
            for (int j = 0; j < dp.length; j++) {
                next[j][0] = swap(dp[j][0], 2, 3);
                next[j][1] = swap(dp[j][1], 2, 3);
            }
            next[dp.length] = new int[]{1, 3};
            for (int j = 0; j < dp.length; j++) {
                next[dp.length + 1 + j][0] = swap(dp[j][0], 1, 2);
                next[dp.length + 1 + j][1] = swap(dp[j][1], 1, 2);
            }
            dp = next;
        }
        return dp;
    }

    public int swap(int peg, int a, int b) {
        if (peg == a) return b;
        if (peg == b) return a;
        return peg;
    }
}