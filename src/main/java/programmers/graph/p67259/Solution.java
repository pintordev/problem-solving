package programmers.graph.p67259;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][][] boards = {
            {{0,0,0},{0,0,0},{0,0,0}},
            {{0,0,0,0,0,0,0,1},{0,0,0,0,0,0,0,0},{0,0,0,0,0,1,0,0},{0,0,0,0,1,0,0,0},{0,0,0,1,0,0,0,1},{0,0,1,0,0,0,1,0},{0,1,0,0,0,1,0,0},{1,0,0,0,0,0,0,0}},
            {{0,0,1,0},{0,0,0,0},{0,1,0,1},{1,0,0,0}},
            {{0,0,0,0,0,0},{0,1,1,1,1,0},{0,0,1,0,0,0},{1,0,0,1,0,1},{0,1,0,0,0,1},{0,0,0,0,0,0}}
        };
        int[] answers = {900, 3800, 2100, 3200};
        for (int i = 0; i < boards.length; i++) {
            System.out.println(s.solution(boards[i]) == answers[i]);
        }
    }

    int n;
    int[][] board;
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};

    public int solution(int[][] board) {
        n = board.length;
        this.board = board;
        int[][][] dist = new int[n][n][4];
        for (int[][] row : dist) {
            for (int[] cell : row) {
                Arrays.fill(cell, Integer.MAX_VALUE);
            }
        }
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, 0, -1, 0));
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.dir != -1 && cur.cost > dist[cur.r][cur.c][cur.dir]) continue;
            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                if(!isAvailable(nr, nc)) continue;
                int newCost = cur.cost + ((cur.dir == -1 || cur.dir == d) ? 100 : 600);
                if (newCost >= dist[nr][nc][d]) continue;
                dist[nr][nc][d] = newCost;
                pq.add(new Node(nr, nc, d, newCost));
            }
        }
        int res = Integer.MAX_VALUE;
        for (int d = 0; d < 4; d++) {
            res = Math.min(res, dist[n - 1][n - 1][d]);
        }
        return res;
    }

    public boolean isAvailable(int r, int c) {
        if (r < 0 || r >= n || c < 0 || c >= n) return false;
        return board[r][c] != 1;
    }
}

class Node implements Comparable<Node> {
    int r, c, dir, cost;

    public Node(int r, int c, int dir, int cost) {
        this.r = r;
        this.c = c;
        this.dir = dir;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        return this.cost - o.cost;
    }
}