package programmers.math.p169198;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] ms = {10};
        int[] ns = {10};
        int[] startXs = {3};
        int[] startYs = {7};
        int[][][] ballss = {{{7, 7}, {2, 7}, {7, 3}}};
        int[][] answers = {{52, 37, 116}};
        for (int i = 0; i < ms.length; i++) {
            System.out.println(Arrays.equals(s.solution(ms[i], ns[i], startXs[i], startYs[i], ballss[i]), answers[i]));
        }
    }

    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] res = new int[balls.length];
        for (int i = 0; i < balls.length; i++) {
            int a = balls[i][0], b = balls[i][1];
            int min = Integer.MAX_VALUE;
            if (b != startY || a >= startX) min = Math.min(min, sq(startX + a) + sq(startY - b));
            if (b != startY || a <= startX) min = Math.min(min, sq(2 * m - startX - a) + sq(startY - b));
            if (a != startX || b >= startY) min = Math.min(min, sq(startX - a) + sq(startY + b));
            if (a != startX || b <= startY) min = Math.min(min, sq(startX - a) + sq(2 * n - startY - b));
            res[i] = min;
        }
        return res;
    }

    public int sq(int x) {
        return x * x;
    }
}