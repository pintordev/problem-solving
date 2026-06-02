package programmers.bfs.p159993;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        String[][] inputs = {
                {"SOOOL", "XXXXO", "OOOOO", "OXXXX", "OOOOE"},
                {"LOOXS", "OOOOX", "OOOOO", "OOOOO", "EOOOO"}
        };
        int[] answers = {16, -1};
        for (int i = 0; i < inputs.length; i++) {
            System.out.println(s.solution(inputs[i]) == answers[i]);
        }
    }

    int n, m;
    String[] maps;
    int[] dr = {-1, 0, 1, 0};
    int[] dc = {0, 1, 0, -1};
    boolean[][] visited;

    public int solution(String[] maps) {
        n = maps.length;
        m = maps[0].length();
        this.maps = maps;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (maps[i].charAt(j) != 'S') continue;
                State lever = bfs(new State(i, j, 0), 'L');
                if (lever == null) return -1;
                State end = bfs(lever, 'E');
                if (end == null) return -1;
                return end.d;
            }
        }
        return -1;
    }

    public State bfs(State s, char e) {
        visited = new boolean[n][m];
        Queue<State> q = new ArrayDeque<>();
        q.add(s);
        visited[s.r][s.c] = true;
        while (!q.isEmpty()) {
            State cur = q.poll();
            if (maps[cur.r].charAt(cur.c) == e) return cur;
            for (int i = 0; i < dr.length; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                if (!isAvailable(nr, nc)) continue;
                q.add(new State(nr, nc, cur.d + 1));
                visited[nr][nc] = true;
            }
        }
        return null;
    }

    public boolean isAvailable(int r, int c) {
        if (r < 0 || r >= n || c < 0 || c >= m) return false;
        if (maps[r].charAt(c) == 'X') return false;
        if (visited[r][c]) return false;
        return true;
    }
}

class State {
    int r;
    int c;
    int d;

    public State(int r, int c, int d) {
        this.r = r;
        this.c = c;
        this.d = d;
    }
}