package programmers.tree.p68937;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] ns = {4, 5};
        int[][][] edgess = {
                {{1, 2}, {2, 3}, {3, 4}},
                {{1, 5}, {2, 5}, {3, 5}, {4, 5}}
        };
        int[] answers = {2, 2};
        for (int i = 0; i < ns.length; i++) {
            System.out.println(s.solution(ns[i], edgess[i]) == answers[i]);
        }
    }

    int n;
    List<Integer>[] graph;
    boolean[] visited;
    int max;
    int mdx;
    int cnt;

    public int solution(int n, int[][] edges) {
        this.n = n;
        graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        bfs(1);

        bfs(mdx);
        if (cnt > 1) return max;

        bfs(mdx);
        return cnt > 1 ? max : max - 1;
    }

    public void bfs(int s) {
        visited = new boolean[n + 1];
        visited[s] = true;
        Queue<Integer> q = new ArrayDeque<>();
        q.add(s);
        max = -1;
        while (!q.isEmpty()) {
            max++;
            cnt = q.size();
            mdx = q.peek();
            for (int i = 0; i < cnt; i++) {
                int cur = q.poll();
                for (int next : graph[cur]) {
                    if (visited[next]) continue;
                    visited[next] = true;
                    q.add(next);
                }
            }
        }
    }
}
