package programmers.bfs.p250136;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][][] inputs = {
                {{0, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 1, 1, 0, 0}, {1, 1, 0, 0, 0, 1, 1, 0}, {1, 1, 1, 0, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 0, 1, 1}},
                {{1, 0, 1, 0, 1, 1}, {1, 0, 1, 0, 0, 0}, {1, 0, 1, 0, 0, 1}, {1, 0, 0, 1, 0, 0}, {1, 0, 0, 1, 0, 1}, {1, 0, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 1}}
        };
        int[] answers = {9, 16};
        for (int i = 0; i < inputs.length; i++) {
            System.out.println(s.solution(inputs[i]) == answers[i]);
        }
    }

    int n, m;
    int[][] land;
    boolean[][] visited;
    int[] dr = {-1, 0, 1, 0};
    int[] dc = {0, -1, 0, 1};
    int[] flat;

    public int solution(int[][] land) {
        n = land.length;
        m = land[0].length;
        this.land = land;
        visited = new boolean[n][m];
        flat = new int[m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (land[i][j] == 0 || visited[i][j]) continue;
                bfs(i, j);
            }
        }
        int max = 0;
        for (int f : flat) {
            max = Math.max(max, f);
        }
        return max;
    }

    public void bfs(int i, int j) {
        Set<Integer> set = new HashSet<>();
        int amt = 0;
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(i, j));
        visited[i][j] = true;
        amt++;
        set.add(j);
        while (!q.isEmpty()) {
            Node cur = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                if (!isAvailable(nr, nc)) continue;
                q.add(new Node(nr, nc));
                visited[nr][nc] = true;
                amt++;
                set.add(nc);
            }
        }
        for (int c : set) {
            flat[c] += amt;
        }
    }

    public boolean isAvailable(int r, int c) {
        if (r < 0 || r >= n || c < 0 || c >= m) return false;
        if (land[r][c] == 0) return false;
        return !visited[r][c];
    }
}

class Node {
    int r, c;
    Node (int r, int c) {
        this.r = r;
        this.c = c;
    }
}