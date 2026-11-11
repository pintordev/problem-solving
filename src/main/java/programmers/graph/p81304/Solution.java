package programmers.graph.p81304;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] ns = {3, 4};
        int[] starts = {1, 1};
        int[] ends = {3, 4};
        int[][][] roadss = {
                {{1, 2, 2}, {3, 2, 3}},
                {{1, 2, 1}, {3, 2, 1}, {2, 4, 1}}
        };
        int[][] trapss = {{2}, {2, 3}};
        int[] answers = {5, 4};
        for (int i = 0; i < ns.length; i++) {
            System.out.println(s.solution(ns[i], starts[i], ends[i], roadss[i], trapss[i]) == answers[i]);
        }
    }

    int n;
    List<Edge>[] graph;
    int[] trap;

    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        this.n = n;
        graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] road : roads) {
            graph[road[0]].add(new Edge(road[1], road[2], false));
            graph[road[1]].add(new Edge(road[0], road[2], true));
        }
        trap = new int[n + 1];
        Arrays.fill(trap, -1);
        for (int i = 0; i < traps.length; i++) {
            trap[traps[i]] = i;
        }
        return dijkstra(start, end, traps.length);
    }

    public int dijkstra(int start, int end, int k) {
        int[][] dist = new int[n + 1][1 << k];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[start][0] = 0;
        pq.add(new Node(start, 0, 0));
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.time > dist[cur.to][cur.mask]) continue;
            if (cur.to == end) return cur.time;
            for (Edge next : graph[cur.to]) {
                boolean same = isActive(cur.to, cur.mask) == isActive(next.to, cur.mask);
                if (same == next.reversed) continue;
                int nm = trap[next.to] == -1 ? cur.mask : cur.mask ^ (1 << trap[next.to]);
                int nd = cur.time + next.time;
                if (dist[next.to][nm] <= nd) continue;
                dist[next.to][nm] = nd;
                pq.add(new Node(next.to, nm, nd));
            }
        }
        return -1;
    }

    public boolean isActive(int v, int mask) {
        if (trap[v] == -1) return false;
        return (mask >> trap[v] & 1) == 1;
    }
}

class Edge {
    int to;
    int time;
    boolean reversed;

    public Edge(int to, int time, boolean reversed) {
        this.to = to;
        this.time = time;
        this.reversed = reversed;
    }
}

class Node implements Comparable<Node> {
    int to;
    int mask;
    int time;

    public Node(int to, int mask, int time) {
        this.to = to;
        this.mask = mask;
        this.time = time;
    }

    @Override
    public int compareTo(Node o) {
        return this.time - o.time;
    }
}
