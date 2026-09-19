package programmers.tree.p76503;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] as = {
                {-5, 0, 2, 1, 2},
                {0, 1, 0}
        };
        int[][][] edgess = {
                {{0, 1}, {3, 4}, {2, 3}, {0, 3}},
                {{0, 1}, {1, 2}}
        };
        long[] answers = {9L, -1L};
        for (int i = 0; i < as.length; i++) {
            System.out.println(s.solution(as[i], edgess[i]) == answers[i]);
        }
    }

    int n;
    List<Integer>[] graph;
    long[] a;
    int[] parent;

    public long solution(int[] a, int[][] edges) {
        n = a.length;
        this.a = new long[n];
        long sum = 0;
        for (int i = 0; i < n; i++) {
            this.a[i] = a[i];
            sum += a[i];
        }
        if (sum != 0) return -1;
        graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        return countOperations();
    }

    public long countOperations() {
        parent = new int[n];
        int[] order = new int[n];
        int idx = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        while (!stack.isEmpty()) {
            int node = stack.pop();
            order[idx++] = node;
            for (int next : graph[node]) {
                if (next == parent[node]) continue;
                parent[next] = node;
                stack.push(next);
            }
        }
        long cnt = 0;
        for (int i = n - 1; i >= 1; i--) {
            int node = order[i];
            a[parent[node]] += a[node];
            cnt += Math.abs(a[node]);
        }
        return cnt;
    }
}
