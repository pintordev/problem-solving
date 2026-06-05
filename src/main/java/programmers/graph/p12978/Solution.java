package programmers.graph.p12978;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] Ns = {5, 6};
        int[][][] roads = {
            {{1,2,1},{2,3,3},{5,2,2},{1,4,2},{5,3,1},{5,4,2}},
            {{1,2,1},{1,3,2},{2,3,2},{3,4,3},{3,5,2},{3,5,3},{5,6,1}}
        };
        int[] Ks = {3, 4};
        int[] answers = {4, 4};
        for (int i = 0; i < Ns.length; i++) {
            System.out.println(s.solution(Ns[i], roads[i], Ks[i]) == answers[i]);
        }
    }

    int n;
    List<Node>[] graph;

    public int solution(int N, int[][] road, int K) {
        n = N;
        graph = new List[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
        for (int[] r : road) {
            graph[r[0]].add(new Node(r[1], r[2]));
            graph[r[1]].add(new Node(r[0], r[2]));
        }
        return dijkstra(1, K);
    }

    public int dijkstra(int s, int k) {
        int[] dist = new int[n + 1];
        for (int i = 1; i <= n; i++) dist[i] = Integer.MAX_VALUE;
        dist[s] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(s, 0));
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.d > dist[cur.t]) continue;
            for (Node next : graph[cur.t]) {
                int nd = dist[cur.t] + next.d;
                if (dist[next.t] <= nd) continue;
                dist[next.t] = nd;
                pq.add(new Node(next.t, nd));
            }
        }
        int cnt = 0;
        for (int i = 1; i <= n; i++) if (dist[i] <= k) cnt++;
        return cnt;
    }
}

class Node implements Comparable<Node> {
    int t;
    int d;

    public Node(int t, int d) {
        this.t = t;
        this.d = d;
    }

    @Override
    public int compareTo(Node o) {
        return this.d - o.d;
    }
}