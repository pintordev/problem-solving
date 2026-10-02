package programmers.bruteforce.p72415;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][][] boards = {
                {{1, 0, 0, 3}, {2, 0, 0, 0}, {0, 0, 0, 2}, {3, 0, 1, 0}},
                {{3, 0, 0, 2}, {0, 0, 1, 0}, {0, 1, 0, 0}, {2, 0, 0, 3}}
        };
        int[] rs = {1, 0};
        int[] cs = {0, 1};
        int[] answers = {14, 16};
        for (int i = 0; i < boards.length; i++) {
            System.out.println(s.solution(boards[i], rs[i], cs[i]) == answers[i]);
        }
    }

    int[][] board;
    Node[][] cards;
    boolean[] used;
    int cardTypes;
    int[] dr = {1, -1, 0, 0};
    int[] dc = {0, 0, 1, -1};
    int res;

    public int solution(int[][] board, int r, int c) {
        this.board = board;
        cards = new Node[7][2];
        used = new boolean[7];
        cardTypes = 0;
        res = Integer.MAX_VALUE;
        saveCardInfo();
        dfs(r, c, 0, 0);
        return res;
    }

    public void saveCardInfo() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int v = board[i][j];
                if (v == 0) continue;
                if (cards[v][0] == null) {
                    cards[v][0] = new Node(i, j);
                    cardTypes++;
                } else {
                    cards[v][1] = new Node(i, j);
                }
            }
        }
    }

    public void dfs(int r, int c, int cost, int depth) {
        if (depth == cardTypes) {
            res = Math.min(res, cost);
            return;
        }
        for (int v = 1; v <= 6; v++) {
            if (cards[v][0] == null || used[v]) continue;
            used[v] = true;
            for (int k = 0; k < 2; k++) {
                Node a = cards[v][k];
                Node b = cards[v][1 - k];
                int next = cost + bfs(r, c, a.r, a.c) + bfs(a.r, a.c, b.r, b.c) + 2;
                board[a.r][a.c] = 0;
                board[b.r][b.c] = 0;
                dfs(b.r, b.c, next, depth + 1);
                board[a.r][a.c] = v;
                board[b.r][b.c] = v;
            }
            used[v] = false;
        }
    }

    public int bfs(int sr, int sc, int er, int ec) {
        boolean[][] visited = new boolean[4][4];
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(sr, sc, 0));
        visited[sr][sc] = true;
        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (cur.r == er && cur.c == ec) return cur.mv;
            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                if (!isAvailable(nr, nc) || visited[nr][nc]) continue;
                visited[nr][nc] = true;
                q.add(new Node(nr, nc, cur.mv + 1));
            }
            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                if (!isAvailable(nr, nc)) continue;
                while (board[nr][nc] == 0 && isAvailable(nr + dr[i], nc + dc[i])) {
                    nr += dr[i];
                    nc += dc[i];
                }
                if (visited[nr][nc]) continue;
                visited[nr][nc] = true;
                q.add(new Node(nr, nc, cur.mv + 1));
            }
        }
        return -1;
    }

    public boolean isAvailable(int nr, int nc) {
        if (nr < 0 || nr >= 4 || nc < 0 || nc >= 4) return false;
        return true;
    }
}

class Node {
    int r;
    int c;
    int mv;

    Node(int r, int c) {
        this.r = r;
        this.c = c;
    }

    Node(int r, int c, int mv) {
        this.r = r;
        this.c = c;
        this.mv = mv;
    }
}
