package programmers.bfs.p169199;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        String[][] inputs = {
            {"...D..R", ".D.G...", "....D.D", "D....D.", "..D...."},
            {".D.R", "....", ".G..", "...D"}
        };
        int[] answers = {7, -1};
        for (int i = 0; i < inputs.length; i++) {
            System.out.println(s.solution(inputs[i]) == answers[i]);
        }
    }

    String[] board;
    int n, m;
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};
    boolean[][] visited;

    public int solution(String[] board) {
        this.board = board;
        n = board.length;
        m = board[0].length();
        Queue<State> q = new ArrayDeque<>();
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i].charAt(j) != 'R') continue;
                visited[i][j] = true;
                for (int dir = 0; dir < 4; dir++) {
                    q.add(new State(i, j, 0, dir));
                }
            }
        }
        while (!q.isEmpty()) {
            State cur = q.poll();
            int nr = cur.r, nc = cur.c;
            while (isAvailable(nr + dr[cur.dir], nc + dc[cur.dir])) {
                nr += dr[cur.dir];
                nc += dc[cur.dir];
            }
            if (board[nr].charAt(nc) == 'G') return cur.d + 1;
            if (visited[nr][nc]) continue;
            visited[nr][nc] = true;
            for (int dir = 0; dir < 4; dir++) {
                if (dir == cur.dir) continue;
                q.add(new State(nr, nc, cur.d + 1, dir));
            }
        }
        return -1;
    }

    boolean isAvailable(int r, int c) {
        if (r < 0 || r >= n || c < 0 || c >= m) return false;
        if (board[r].charAt(c) == 'D') return false;
        return true;
    }
}

class State {
    int r;
    int c;
    int d;
    int dir;

    public State(int r, int c, int d, int dir) {
        this.r = r;
        this.c = c;
        this.d = d;
        this.dir = dir;
    }
}