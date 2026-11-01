package programmers.sort.p12984;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][][] lands = {
                {{1, 2}, {2, 3}},
                {{4, 4, 3}, {3, 2, 2}, {2, 1, 0}}
        };
        int[] Ps = {3, 5};
        int[] Qs = {2, 3};
        long[] answers = {5, 33};
        for (int i = 0; i < lands.length; i++) {
            System.out.println(s.solution(lands[i], Ps[i], Qs[i]) == answers[i]);
        }
    }

    public long solution(int[][] land, int P, int Q) {
        int n = land.length;
        int[] heights = new int[n * n];
        long total = 0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                heights[r * n + c] = land[r][c];
                total += land[r][c];
            }
        }
        Arrays.sort(heights);
        long res = Long.MAX_VALUE;
        long sum = 0;
        for (int i = 0; i < heights.length; i++) {
            sum += heights[i];
            long add = (long) heights[i] * (i + 1) - sum;
            long del = total - sum - (long) heights[i] * (heights.length - i - 1);
            res = Math.min(res, add * P + del * Q);
        }
        return res;
    }
}
