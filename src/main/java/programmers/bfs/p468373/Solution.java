package programmers.bfs.p468373;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] ns = {10, 7};
        int[] infections = {1, 6};
        int[][][] edgess = {
                {{1, 2, 1}, {1, 3, 1}, {1, 4, 3}, {1, 5, 2}, {5, 6, 1}, {5, 7, 1}, {2, 8, 3}, {2, 9, 2}, {9, 10, 1}},
                {{1, 2, 3}, {1, 4, 3}, {4, 5, 1}, {5, 6, 1}, {3, 6, 2}, {3, 7, 2}}
        };
        int[] ks = {2, 3};
        int[] answers = {6, 7};
        for (int i = 0; i < ns.length; i++) {
            System.out.println(s.solution(ns[i], infections[i], edgess[i], ks[i]) == answers[i]);
        }
    }

    int n, max;
    List<Node>[] graph;

    public int solution(int n, int infection, int[][] edges, int k) {
        this.n = n;
        graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1], no = edge[2];
            graph[x].add(new Node(y, no));
            graph[y].add(new Node(x, no));
        }
        long[] mask = new long[2];
        visit(mask, infection);
        dfs(0, k, 0, mask);
        return max;
    }

    public void dfs(int d, int k, int prev, long[] mask) {
        if (d == k) {
            max = Math.max(max, Long.bitCount(mask[0]) + Long.bitCount(mask[1]));
            return;
        }
        for (int no = 1; no <= 3; no++) {
            if (no == prev) continue;
            dfs(d + 1, k, no, spread(mask, no));
        }
    }

    public long[] spread(long[] mask, int no) {
        long[] next = mask.clone();
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if (isVisited(mask, i)) queue.add(i);
        }
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (Node nd : graph[cur]) {
                if (nd.no != no || isVisited(next, nd.to)) continue;
                visit(next, nd.to);
                queue.add(nd.to);
            }
        }
        return next;
    }

    public boolean isVisited(long[] mask, int i) {
        return (mask[(i - 1) >> 6] & (1L << ((i - 1) & 63))) != 0;
    }

    public void visit(long[] mask, int i) {
        mask[(i - 1) >> 6] |= 1L << ((i - 1) & 63);
    }
}

class Node {
    int to, no;

    Node(int to, int no) {
        this.to = to;
        this.no = no;
    }
}