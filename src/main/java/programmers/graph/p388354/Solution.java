package programmers.graph.p388354;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] nodess = {
                {11, 9, 3, 2, 4, 6},
                {9, 15, 14, 7, 6, 1, 2, 4, 5, 11, 8, 10}
        };
        int[][][] edgess = {
                {{9, 11}, {2, 3}, {6, 3}, {3, 4}},
                {{5, 14}, {1, 4}, {9, 11}, {2, 15}, {2, 5}, {9, 7}, {8, 1}, {6, 4}}
        };
        int[][] answers = {
                {1, 0},
                {2, 1}
        };
        for (int i = 0; i < nodess.length; i++) {
            System.out.println(Arrays.equals(s.solution(nodess[i], edgess[i]), answers[i]));
        }
    }

    int[] parent = new int[1000001];

    public int[] solution(int[] nodes, int[][] edges) {
        int[] degree = new int[1000001];
        for (int node : nodes) {
            parent[node] = node;
        }
        for (int[] edge : edges) {
            degree[edge[0]]++;
            degree[edge[1]]++;
            union(edge[0], edge[1]);
        }
        int[] same = new int[1000001];
        int[] diff = new int[1000001];
        for (int node : nodes) {
            int root = find(node);
            if (node % 2 == degree[node] % 2) same[root]++;
            else diff[root]++;
        }
        int[] res = new int[2];
        for (int node : nodes) {
            if (parent[node] != node) continue;
            if (same[node] == 1) res[0]++;
            if (diff[node] == 1) res[1]++;
        }
        return res;
    }

    public void union(int a, int b) {
        parent[find(a)] = find(b);
    }

    public int find(int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }
}
