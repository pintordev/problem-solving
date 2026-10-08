package programmers.dp.p214288;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] ks = {3, 2};
        int[] ns = {5, 3};
        int[][][] reqss = {
                {{10, 60, 1}, {15, 100, 3}, {20, 30, 1}, {30, 50, 3}, {50, 40, 1}, {60, 30, 2}, {65, 30, 1}, {70, 100, 2}},
                {{5, 55, 2}, {10, 90, 2}, {20, 40, 2}, {50, 45, 2}, {100, 50, 2}}
        };
        int[] answers = {25, 90};
        for (int i = 0; i < ks.length; i++) {
            System.out.println(s.solution(ks[i], ns[i], reqss[i]) == answers[i]);
        }
    }

    List<int[]>[] types;

    public int solution(int k, int n, int[][] reqs) {
        types = new List[k + 1];
        for (int i = 1; i <= k; i++) {
            types[i] = new ArrayList<>();
        }
        for (int[] req : reqs) {
            types[req[2]].add(req);
        }
        int[][] waiting = new int[k + 1][n - k + 2];
        for (int i = 1; i <= k; i++) {
            for (int j = 1; j <= n - k + 1; j++) {
                waiting[i][j] = simulate(i, j);
            }
        }
        int[][] dp = new int[k + 1][n + 1];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dp[0][0] = 0;
        for (int i = 1; i <= k; i++) {
            for (int j = i; j <= n; j++) {
                for (int m = 1; m <= n - k + 1 && m <= j - i + 1; m++) {
                    if (dp[i - 1][j - m] == Integer.MAX_VALUE) continue;
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - m] + waiting[i][m]);
                }
            }
        }
        return dp[k][n];
    }

    public int simulate(int type, int mentors) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < mentors; i++) {
            pq.add(0);
        }
        int res = 0;
        for (int[] req : types[type]) {
            int end = pq.poll();
            res += Math.max(0, end - req[0]);
            pq.add(Math.max(end, req[0]) + req[1]);
        }
        return res;
    }
}
