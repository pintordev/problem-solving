package programmers.math.p87391;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] ns = {2, 2};
        int[] ms = {2, 5};
        int[] xs = {0, 0};
        int[] ys = {0, 1};
        int[][][] queriess = {
            {{2, 1}, {0, 1}, {1, 1}, {0, 1}, {2, 1}},
            {{3, 1}, {2, 2}, {1, 1}, {2, 3}, {0, 1}, {2, 1}}
        };
        long[] answers = {4, 2};
        for (int i = 0; i < ns.length; i++) {
            System.out.println(s.solution(ns[i], ms[i], xs[i], ys[i], queriess[i]) == answers[i]);
        }
    }

    public long solution(int n, int m, int x, int y, int[][] queries) {
        long r1 = x;
        long r2 = x;
        long c1 = y;
        long c2 = y;
        for (int i = queries.length - 1; i >= 0; i--) {
            int dir = queries[i][0];
            int dist = queries[i][1];
            switch (dir) {
                case 0:
                    if (c1 != 0) c1 += dist;
                    c2 = Math.min(m - 1, c2 + dist);
                    break;
                case 1:
                    if (c2 != m - 1) c2 -= dist;
                    c1 = Math.max(0, c1 - dist);
                    break;
                case 2:
                    if (r1 != 0) r1 += dist;
                    r2 = Math.min(n - 1, r2 + dist);
                    break;
                case 3:
                    if (r2 != n - 1) r2 -= dist;
                    r1 = Math.max(0, r1 - dist);
                    break;
            }
            if (r1 > r2 || c1 > c2) return 0;
        }
        return (r2 - r1 + 1) * (c2 - c1 + 1);
    }
}
