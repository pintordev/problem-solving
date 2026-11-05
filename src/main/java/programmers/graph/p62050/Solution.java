package programmers.graph.p62050;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][][] lands = {
                {{1, 4, 8, 10}, {5, 5, 5, 5}, {10, 10, 10, 10}, {10, 10, 10, 20}},
                {{10, 11, 10, 11}, {2, 21, 20, 10}, {1, 20, 21, 11}, {2, 1, 2, 1}}
        };
        int[] heights = {3, 1};
        int[] answers = {15, 18};
        for (int i = 0; i < lands.length; i++) {
            System.out.println(s.solution(lands[i], heights[i]) == answers[i]);
        }
    }

    int n;
    int[][] land;
    int height;
    int[] parent;
    int[] dr = {1, 0};
    int[] dc = {0, 1};

    public int solution(int[][] land, int height) {
        this.land = land;
        this.height = height;
        n = land.length;
        parent = new int[n * n];
        for (int i = 0; i < n * n; i++) {
            parent[i] = i;
        }
        List<Edge> edges = new ArrayList<>();
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                for (int d = 0; d < 2; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if (!isAvailable(nr, nc)) continue;
                    int diff = Math.abs(land[r][c] - land[nr][nc]);
                    edges.add(new Edge(r * n + c, nr * n + nc, diff <= height ? 0 : diff));
                }
            }
        }
        Collections.sort(edges);
        int res = 0;
        for (Edge e : edges) {
            if (find(e.a) == find(e.b)) continue;
            union(e.a, e.b);
            res += e.cost;
        }
        return res;
    }

    public boolean isAvailable(int r, int c) {
        if (r >= n || c >= n) return false;
        return true;
    }

    public int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    public void union(int a, int b) {
        parent[find(a)] = find(b);
    }
}

class Edge implements Comparable<Edge> {
    int a;
    int b;
    int cost;

    Edge(int a, int b, int cost) {
        this.a = a;
        this.b = b;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return cost - o.cost;
    }
}
