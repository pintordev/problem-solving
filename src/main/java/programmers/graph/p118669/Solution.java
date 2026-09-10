package programmers.graph.p118669;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] ns = {6, 7, 7, 5};
        int[][][] pathss = {
                {{1, 2, 3}, {2, 3, 5}, {2, 4, 2}, {2, 5, 4}, {3, 4, 4}, {4, 5, 3}, {4, 6, 1}, {5, 6, 1}},
                {{1, 4, 4}, {1, 6, 1}, {1, 7, 3}, {2, 5, 2}, {3, 7, 4}, {5, 6, 6}},
                {{1, 2, 5}, {1, 4, 1}, {2, 3, 1}, {2, 6, 7}, {4, 5, 1}, {5, 6, 1}, {6, 7, 1}},
                {{1, 3, 10}, {1, 4, 20}, {2, 3, 4}, {2, 4, 6}, {3, 5, 20}, {4, 5, 6}}
        };
        int[][] gatess = {{1, 3}, {1}, {3, 7}, {1, 2}};
        int[][] summitss = {{5}, {2, 3, 4}, {1, 5}, {5}};
        int[][] answers = {{5, 3}, {3, 4}, {5, 1}, {5, 6}};
        for (int i = 0; i < ns.length; i++) {
            System.out.println(Arrays.equals(s.solution(ns[i], pathss[i], gatess[i], summitss[i]), answers[i]));
        }
    }

    int n;
    List<Node>[] graph;
    boolean[] summit;

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        this.n = n;
        graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] path : paths) {
            graph[path[0]].add(new Node(path[1], path[2]));
            graph[path[1]].add(new Node(path[0], path[2]));
        }
        summit = new boolean[n + 1];
        for (int s : summits) summit[s] = true;
        return dijkstra(gates);
    }

    public int[] dijkstra(int[] gates) {
        int[] dist = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int gate : gates) {
            dist[gate] = 0;
            pq.add(new Node(gate, 0));
        }
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.time > dist[cur.to]) continue;
            for (Node next : graph[cur.to]) {
                int nd = Math.max(dist[cur.to], next.time);
                if (dist[next.to] <= nd) continue;
                dist[next.to] = nd;
                if (summit[next.to]) continue;
                pq.add(new Node(next.to, nd));
            }
        }
        int minSummit = 0;
        int minIntensity = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            if (!summit[i] || dist[i] >= minIntensity) continue;
            minSummit = i;
            minIntensity = dist[i];
        }
        return new int[]{minSummit, minIntensity};
    }
}

class Node implements Comparable<Node> {
    int to;
    int time;

    public Node(int to, int time) {
        this.to = to;
        this.time = time;
    }

    @Override
    public int compareTo(Node o) {
        return this.time - o.time;
    }
}
