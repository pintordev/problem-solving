package programmers.graph.p67260;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] ns = {9, 9, 9};
        int[][][] paths = {
                {{0, 1}, {0, 3}, {0, 7}, {8, 1}, {3, 6}, {1, 2}, {4, 7}, {7, 5}},
                {{8, 1}, {0, 1}, {1, 2}, {0, 7}, {4, 7}, {0, 3}, {7, 5}, {3, 6}},
                {{0, 1}, {0, 3}, {0, 7}, {8, 1}, {3, 6}, {1, 2}, {4, 7}, {7, 5}}
        };
        int[][][] orders = {
                {{8, 5}, {6, 7}, {4, 1}},
                {{4, 1}, {5, 2}},
                {{4, 1}, {8, 7}, {6, 5}}
        };
        boolean[] answers = {true, true, false};
        for (int i = 0; i < ns.length; i++) {
            System.out.println(s.solution(ns[i], paths[i], orders[i]) == answers[i]);
        }
    }

    List<Integer>[] graph;
    int[] before;
    int[] after;
    boolean[] visited;
    boolean[] held;

    public boolean solution(int n, int[][] path, int[][] order) {
        graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] p : path) {
            graph[p[0]].add(p[1]);
            graph[p[1]].add(p[0]);
        }
        before = new int[n];
        after = new int[n];
        for (int[] o : order) {
            after[o[0]] = o[1];
            before[o[1]] = o[0];
        }
        if (before[0] != 0) return false;
        visited = new boolean[n];
        held = new boolean[n];
        Queue<Integer> q = new ArrayDeque<>();
        q.add(0);
        visited[0] = true;
        int cnt = 0;
        while (!q.isEmpty()) {
            int cur = q.poll();
            cnt++;
            for (int next : graph[cur]) {
                if (visited[next]) continue;
                if (!visited[before[next]]) {
                    held[next] = true;
                    continue;
                }
                q.add(next);
                visited[next] = true;
            }
            if (held[after[cur]]) {
                q.add(after[cur]);
                visited[after[cur]] = true;
            }
        }
        return cnt == n;
    }
}
