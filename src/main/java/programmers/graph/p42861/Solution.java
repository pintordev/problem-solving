package programmers.graph.p42861;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] ns = {4};
        int[][][] costss = {
                {{0, 1, 1}, {0, 2, 2}, {1, 2, 5}, {1, 3, 1}, {2, 3, 8}}
        };
        int[] answers = {4};
        for (int i = 0; i < ns.length; i++) {
            System.out.println(s.solution(ns[i], costss[i]) == answers[i]);
        }
    }

    int[] parent;

    public int solution(int n, int[][] costs) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        Arrays.sort(costs, (a, b) -> a[2] - b[2]);
        int res = 0;
        for (int[] cost : costs) {
            int a = cost[0];
            int b = cost[1];
            if (find(a) == find(b)) continue;
            union(a, b);
            res += cost[2];
        }
        return res;
    }

    public int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    public void union(int a, int b) {
        parent[find(a)] = find(b);
    }
}