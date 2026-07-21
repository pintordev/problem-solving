package programmers.dp.p389480;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][][] infos = {
                {{1, 2}, {2, 3}, {2, 1}},
                {{1, 2}, {2, 3}, {2, 1}},
                {{3, 3}, {3, 3}},
                {{3, 3}, {3, 3}}
        };
        int[] ns = {4, 1, 7, 6};
        int[] ms = {4, 7, 1, 1};
        int[] answers = {2, 0, 6, -1};
        for (int i = 0; i < infos.length; i++) {
            System.out.println(s.solution(infos[i], ns[i], ms[i]) == answers[i]);
        }
    }

    public int solution(int[][] info, int n, int m) {
        int INF = 121;
        int[] dp = new int[m];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        for (int[] item : info) {
            int a = item[0], b = item[1];
            for (int j = m - 1; j >= 0; j--) {
                int viaA = dp[j] + a;
                int viaB = j - b >= 0 ? dp[j - b] : INF;
                dp[j] = Math.min(viaA, viaB);
            }
        }
        int res = INF;
        for (int j = 0; j < m; j++) {
            if (dp[j] < n) res = Math.min(res, dp[j]);
        }
        return res == INF ? -1 : res;
    }
}