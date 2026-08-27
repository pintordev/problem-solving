package programmers.graph.p72413;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] ns = {6, 7, 6};
        int[] ss = {4, 3, 4};
        int[] as = {6, 4, 5};
        int[] bs = {2, 1, 6};
        int[][][] faress = {
                {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}},
                {{5, 7, 9}, {4, 6, 4}, {3, 6, 1}, {3, 2, 3}, {2, 1, 6}},
                {{2, 6, 6}, {6, 3, 7}, {4, 6, 7}, {6, 5, 11}, {2, 5, 12}, {5, 3, 20}, {2, 4, 8}, {4, 3, 9}}
        };
        int[] answers = {82, 14, 18};
        for (int i = 0; i < ns.length; i++) {
            System.out.println(s.solution(ns[i], ss[i], as[i], bs[i], faress[i]) == answers[i]);
        }
    }

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int INF = Integer.MAX_VALUE >> 2;
        int[][] dist = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }
        for (int[] fare : fares) {
            int from = fare[0], to = fare[1], cost = fare[2];
            dist[from][to] = cost;
            dist[to][from] = cost;
        }
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (dist[i][k] + dist[k][j] >= dist[i][j]) continue;
                    dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }
        int min = INF;
        for (int k = 1; k <= n; k++) {
            int cost = dist[s][k] + dist[k][a] + dist[k][b];
            if (cost < min) min = cost;
        }
        return min;
    }
}
