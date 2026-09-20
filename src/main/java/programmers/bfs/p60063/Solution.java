package programmers.bfs.p60063;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][][] boards = {
            {{0, 0, 0, 1, 1}, {0, 0, 0, 1, 0}, {0, 1, 0, 1, 1}, {1, 1, 0, 0, 1}, {0, 0, 0, 0, 0}}
        };
        int[] answers = {7};
        for (int i = 0; i < boards.length; i++) {
            System.out.println(s.solution(boards[i]) == answers[i]);
        }
    }

    public int[][] board;
    public int n;
    public boolean[][][] visited;
    public int[] dr = {-1, 1, 0, 0};
    public int[] dc = {0, 0, -1, 1};
    public int[][] rotateR = {{-1, 0, -1, 0}, {0, 0, 1, 1}};
    public int[][] rotateC = {{0, 0, 1, 1}, {-1, 0, -1, 0}};
    public int[][] checkR = {{0, 1, 0, 1}, {1, 1, -1, -1}};
    public int[][] checkC = {{1, 1, -1, -1}, {0, 1, 0, 1}};

    public int solution(int[][] board) {
        this.board = board;
        n = board.length;
        visited = new boolean[n][n][2];
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(0, 0, 0, 0));
        visited[0][0][0] = true;
        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (isArrived(cur)) return cur.time;
            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                if (!isAvailable(nr, nc, cur.dir)) continue;
                visited[nr][nc][cur.dir] = true;
                q.add(new Node(nr, nc, cur.dir, cur.time + 1));
            }
            for (int i = 0; i < 4; i++) {
                int nr = cur.r + rotateR[cur.dir][i];
                int nc = cur.c + rotateC[cur.dir][i];
                int ndir = 1 - cur.dir;
                if (!isAvailable(nr, nc, ndir, cur.dir, i)) continue;
                visited[nr][nc][ndir] = true;
                q.add(new Node(nr, nc, ndir, cur.time + 1));
            }
        }
        return -1;
    }

    public boolean isArrived(Node cur) {
        if (cur.dir == 0 && cur.r == n - 1 && cur.c + 1 == n - 1) return true;
        if (cur.dir == 1 && cur.r + 1 == n - 1 && cur.c == n - 1) return true;
        return false;
    }

    public boolean isAvailable(int nr, int nc, int dir) {
        if (nr < 0 || nr >= n || nc < 0 || nc >= n) return false;
        if (board[nr][nc] == 1) return false;
        if (dir == 0 && (nc + 1 >= n || board[nr][nc + 1] == 1)) return false;
        if (dir == 1 && (nr + 1 >= n || board[nr + 1][nc] == 1)) return false;
        if (visited[nr][nc][dir]) return false;
        return true;
    }

    public boolean isAvailable(int nr, int nc, int dir, int fromDir, int i) {
        if (!isAvailable(nr, nc, dir)) return false;
        return board[nr + checkR[fromDir][i]][nc + checkC[fromDir][i]] == 0;
    }
}

class Node {
    int r;
    int c;
    int dir;
    int time;

    Node(int r, int c, int dir, int time) {
        this.r = r;
        this.c = c;
        this.dir = dir;
        this.time = time;
    }
}
