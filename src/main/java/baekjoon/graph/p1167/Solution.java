package baekjoon.graph.p1167;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Solution {
    public static List<Edge>[] graph;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(dfs(dfs(1)[0])[1]);
    }

    private static void init() throws IOException {
        int v = read();

        graph = new ArrayList[v + 1];
        while (v-- > 0) {
            int i = read();
            graph[i] = new ArrayList<>();
            int r;
            while ((r = read()) != -1) {
                graph[i].add(new Edge(r, read()));
            }
        }
    }

    private static int[] dfs(int start) {
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(new Node(start, -1, 0));

        int farest = start;
        int maxDist = 0;
        while (!stack.isEmpty()) {
            Node cur = stack.pop();

            if (cur.dist > maxDist) {
                maxDist = cur.dist;
                farest = cur.me;
            }

            for (Edge edge : graph[cur.me]) {
                if (edge.to == cur.parent) continue;
                stack.push(new Node(edge.to, cur.me, cur.dist + edge.cost));
            }
        }

        return new int[]{farest, maxDist};
    }

    private static int read() throws IOException {
        int c, n = System.in.read() & 15;
        boolean negative = n == 13;
        if (negative) n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return negative ? ~n + 1 : n;
    }
}

class Edge {
    int to;
    int cost;

    Edge(int to, int cost) {
        this.to = to;
        this.cost = cost;
    }
}

class Node {
    int me;
    int parent;
    int dist;

    public Node(int me, int parent, int dist) {
        this.me = me;
        this.parent = parent;
        this.dist = dist;
    }
}