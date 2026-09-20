package programmers.tree.p133500;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] ns = {8, 10};
        int[][][] lighthouses = {
                {{1, 2}, {1, 3}, {1, 4}, {1, 5}, {5, 6}, {5, 7}, {5, 8}},
                {{4, 1}, {5, 1}, {5, 6}, {7, 6}, {1, 2}, {1, 3}, {6, 8}, {2, 9}, {9, 10}}
        };
        int[] answers = {2, 3};
        for (int i = 0; i < ns.length; i++) {
            System.out.println(s.solution(ns[i], lighthouses[i]) == answers[i]);
        }
    }

    int n;
    List<Integer>[] graph;
    int[] degree;
    boolean[] lit;
    boolean[] removed;

    public int solution(int n, int[][] lighthouse) {
        this.n = n;
        graph = new List[n + 1];
        degree = new int[n + 1];
        lit = new boolean[n + 1];
        removed = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : lighthouse) {
            int a = edge[0];
            int b = edge[1];
            graph[a].add(b);
            graph[b].add(a);
            degree[a]++;
            degree[b]++;
        }
        return countLitLighthouses();
    }

    public int countLitLighthouses() {
        Deque<Integer> leaves = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if (degree[i] == 1) leaves.add(i);
        }
        int cnt = 0;
        while (!leaves.isEmpty()) {
            int u = leaves.poll();
            if (removed[u]) continue;
            removed[u] = true;
            for (int v : graph[u]) {
                if (removed[v]) continue;
                if (!lit[v]) {
                    lit[v] = true;
                    cnt++;
                }
                removed[v] = true;
                for (int w : graph[v]) {
                    if (removed[w]) continue;
                    degree[w]--;
                    if (degree[w] == 1) leaves.add(w);
                }
                break;
            }
        }
        return cnt;
    }
}
