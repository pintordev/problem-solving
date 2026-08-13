package programmers.bfs.p49189;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] ns = {6};
        int[][][] vertexs = {
            {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}}
        };
        int[] answers = {3};
        for (int i = 0; i < ns.length; i++) {
            System.out.println(s.solution(ns[i], vertexs[i]) == answers[i]);
        }
    }

    public int solution(int n, int[][] edge) {
        List<Integer>[] graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] e : edge) {
            int a = e[0];
            int b = e[1];
            graph[a].add(b);
            graph[b].add(a);
        }

        int[] dist = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            dist[i] = -1;
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : graph[cur]) {
                if (dist[next] != -1) continue;
                dist[next] = dist[cur] + 1;
                queue.add(next);
            }
        }

        int max = 0;
        for (int i = 2; i <= n; i++) {
            max = Math.max(max, dist[i]);
        }
        int cnt = 0;
        for (int i = 2; i <= n; i++) {
            if (dist[i] == max) cnt++;
        }
        return cnt;
    }
}