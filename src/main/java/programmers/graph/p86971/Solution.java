package programmers.graph.p86971;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] ns = {9, 4, 7};
        int[][][] wires = {
            {{1,3},{2,3},{3,4},{4,5},{4,6},{4,7},{7,8},{7,9}},
            {{1,2},{2,3},{3,4}},
            {{1,2},{2,7},{3,7},{3,4},{4,5},{6,7}}
        };
        int[] answers = {3, 0, 1};
        for (int i = 0; i < ns.length; i++) {
            System.out.println(s.solution(ns[i], wires[i]) == answers[i]);
        }
    }

    int n, min;
    List<Integer>[] graph;

    public int solution(int n, int[][] wires) {
        this.n = n;
        this.min = Integer.MAX_VALUE;
        graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] wire : wires) {
            graph[wire[0]].add(wire[1]);
            graph[wire[1]].add(wire[0]);
        }
        dfs(1, 1);
        return min;
    }

    public int dfs(int v, int p) {
        int children = 1;
        for (int u : graph[v]) {
            if (u != p) children += dfs(u, v);
        }
        min = Math.min(min, Math.abs(children - (n - children)));
        return children;
    }
}