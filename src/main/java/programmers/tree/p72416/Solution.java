package programmers.tree.p72416;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] saless = {
                {14, 17, 15, 18, 19, 14, 13, 16, 28, 17},
                {5, 6, 5, 3, 4},
                {5, 6, 5, 1, 4},
                {10, 10, 1, 1}
        };
        int[][][] linkss = {
                {{10, 8}, {1, 9}, {9, 7}, {5, 4}, {1, 5}, {5, 10}, {10, 6}, {1, 3}, {10, 2}},
                {{2, 3}, {1, 4}, {2, 5}, {1, 2}},
                {{2, 3}, {1, 4}, {2, 5}, {1, 2}},
                {{3, 2}, {4, 3}, {1, 4}}
        };
        int[] answers = {44, 6, 5, 2};
        for (int i = 0; i < saless.length; i++) {
            System.out.println(s.solution(saless[i], linkss[i]) == answers[i]);
        }
    }

    int n;
    int[] sales;
    List<Integer>[] children;
    int[][] dp;

    public int solution(int[] sales, int[][] links) {
        n = sales.length;
        this.sales = sales;
        children = new List[n + 1];
        dp = new int[n + 1][2];
        for (int i = 1; i <= n; i++) {
            children[i] = new ArrayList<>();
        }
        for (int[] link : links) {
            children[link[0]].add(link[1]);
        }
        int[] order = bfs();
        for (int i = n - 1; i >= 0; i--) {
            calc(order[i]);
        }
        return Math.min(dp[1][0], dp[1][1]);
    }

    public int[] bfs() {
        int[] order = new int[n];
        int idx = 0;
        Queue<Integer> q = new ArrayDeque<>();
        q.add(1);
        while (!q.isEmpty()) {
            int u = q.poll();
            order[idx++] = u;
            for (int v : children[u]) {
                q.add(v);
            }
        }
        return order;
    }

    public void calc(int v) {
        dp[v][1] = sales[v - 1];
        if (children[v].isEmpty()) return;
        int base = 0;
        int extra = Integer.MAX_VALUE;
        for (int c : children[v]) {
            base += Math.min(dp[c][0], dp[c][1]);
            extra = Math.min(extra, dp[c][1] - dp[c][0]);
        }
        dp[v][1] += base;
        dp[v][0] = base + Math.max(0, extra);
    }
}
