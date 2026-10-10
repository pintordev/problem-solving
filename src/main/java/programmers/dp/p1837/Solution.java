package programmers.dp.p1837;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] ns = {7, 7};
        int[] ms = {10, 10};
        int[][][] edge_lists = {
                {{1, 2}, {1, 3}, {2, 3}, {2, 4}, {3, 4}, {3, 5}, {4, 6}, {5, 6}, {5, 7}, {6, 7}},
                {{1, 2}, {1, 3}, {2, 3}, {2, 4}, {3, 4}, {3, 5}, {4, 6}, {5, 6}, {5, 7}, {6, 7}}
        };
        int[] ks = {6, 6};
        int[][] gps_logs = {{1, 2, 3, 3, 6, 7}, {1, 2, 4, 6, 5, 7}};
        int[] answers = {1, 0};
        for (int i = 0; i < ns.length; i++) {
            System.out.println(s.solution(ns[i], ms[i], edge_lists[i], ks[i], gps_logs[i]) == answers[i]);
        }
    }

    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        List<Integer>[] graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            graph[i].add(i);
        }
        for (int[] edge : edge_list) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        int[][] dp = new int[k][n + 1];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dp[0][gps_log[0]] = 0;
        for (int t = 1; t < k; t++) {
            for (int u = 1; u <= n; u++) {
                if (dp[t - 1][u] == Integer.MAX_VALUE) continue;
                for (int v : graph[u]) {
                    dp[t][v] = Math.min(dp[t][v], dp[t - 1][u] + (gps_log[t] == v ? 0 : 1));
                }
            }
        }
        int res = dp[k - 1][gps_log[k - 1]];
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
