package programmers.implementation.p468374;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][][] boards = {
                {{0, 2, 2, 0, 0, 0, 0, 0}, {0, 2, 2, 0, 0, 4, 4, 0}, {0, 3, 3, 3, 1, 4, 4, 0}, {0, 3, 3, 3, 0, 0, 0, 0}, {0, 3, 3, 3, 5, 5, 6, 0}, {0, 0, 0, 0, 5, 5, 0, 0}},
                {{0, 9, 1, 1, 6, 0, 0, 0}, {2, 2, 1, 1, 0, 0, 0, 0}, {2, 2, 3, 4, 4, 4, 0, 0}, {5, 0, 0, 4, 4, 4, 7, 0}, {0, 0, 0, 4, 4, 4, 8, 8}, {0, 0, 0, 0, 0, 0, 8, 8}},
                {{1, 1, 0}, {1, 1, 0}}
        };
        int[][][] commandss = {
                {{3, 1}, {3, 1}},
                {{2, 1}, {3, 1}, {9, 2}, {4, 1}},
                {{1, 4}, {1, 3}, {1, 2}}
        };
        int[][][] answers = {
                {{0, 0, 2, 2, 0, 0, 0, 0}, {4, 4, 2, 2, 0, 0, 0, 0}, {4, 4, 0, 3, 3, 3, 1, 0}, {0, 0, 0, 3, 3, 3, 0, 0}, {6, 0, 0, 3, 3, 3, 5, 5}, {0, 0, 0, 0, 0, 0, 5, 5}},
                {{8, 8, 0, 1, 1, 6, 0, 0}, {8, 8, 0, 1, 1, 0, 0, 0}, {4, 4, 4, 9, 3, 0, 0, 0}, {4, 4, 4, 7, 2, 2, 0, 0}, {4, 4, 4, 0, 2, 2, 0, 0}, {0, 5, 0, 0, 0, 0, 0, 0}},
                {{0, 1, 1}, {0, 1, 1}}
        };
        for (int i = 0; i < boards.length; i++) {
            System.out.println(Arrays.deepEquals(s.solution(boards[i], commandss[i]), answers[i]));
        }
    }

    int n, m;
    int[][] board;
    App[] apps;
    int[] dr = {0, 1, 0, -1};
    int[] dc = {1, 0, -1, 0};

    public int[][] solution(int[][] board, int[][] commands) {
        n = board.length;
        m = board[0].length;
        this.board = board;
        apps = new App[101];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                int id = board[r][c];
                if (id == 0) continue;
                if (apps[id] == null) apps[id] = new App(r, c);
                if (apps[id].r == r) apps[id].size++;
            }
        }
        for (int[] cmd : commands) {
            push(cmd[0], cmd[1] - 1);
        }
        return board;
    }

    public void push(int id, int d) {
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] queued = new boolean[apps.length];
        q.add(id);
        queued[id] = true;
        while (!q.isEmpty()) {
            int cur = q.poll();
            queued[cur] = false;
            App app = apps[cur];
            int pr = Math.abs(dc[d]);
            int pc = Math.abs(dr[d]);
            int sr = app.r + (d == 3 ? app.size - 1 : 0);
            int sc = app.c + (d == 2 ? app.size - 1 : 0);
            for (int i = 0; i < app.size; i++) {
                int r = (sr + i * pr) % n;
                int c = (sc + i * pc) % m;
                if (board[r][c] == cur) board[r][c] = 0;
            }
            for (int i = 0; i < app.size; i++) {
                int nr = (sr + dr[d] * app.size + i * pr + n) % n;
                int nc = (sc + dc[d] * app.size + i * pc + m) % m;
                int next = board[nr][nc];
                if (next != 0 && !queued[next]) {
                    q.add(next);
                    queued[next] = true;
                }
                board[nr][nc] = cur;
            }
            app.r = (app.r + dr[d] + n) % n;
            app.c = (app.c + dc[d] + m) % m;
            if (app.r + app.size > n || app.c + app.size > m) {
                q.add(cur);
                queued[cur] = true;
            }
        }
    }
}

class App {
    int r;
    int c;
    int size;

    App(int r, int c) {
        this.r = r;
        this.c = c;
    }
}
