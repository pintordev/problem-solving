package programmers.bfs.p132266;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] ns = {3, 5};
        int[][][] roadss = {
            {{1, 2}, {2, 3}},
            {{1, 2}, {1, 4}, {2, 4}, {2, 5}, {4, 5}}
        };
        int[][] sourcess = {
            {2, 3},
            {1, 3, 5}
        };
        int[] destinations = {1, 5};
        int[][] answers = {
            {1, 2},
            {2, -1, 0}
        };
        for (int i = 0; i < ns.length; i++) {
            System.out.println(Arrays.equals(s.solution(ns[i], roadss[i], sourcess[i], destinations[i]), answers[i]));
        }
    }

    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        List<Integer>[] graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] road : roads) {
            int a = road[0];
            int b = road[1];
            graph[a].add(b);
            graph[b].add(a);
        }

        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(destination);
        dist[destination] = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : graph[cur]) {
                if (dist[next] != -1) continue;
                dist[next] = dist[cur] + 1;
                queue.add(next);
            }
        }

        int[] res = new int[sources.length];
        for (int i = 0; i < sources.length; i++) {
            res[i] = dist[sources[i]];
        }
        return res;
    }
}