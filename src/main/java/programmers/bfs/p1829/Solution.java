package programmers.bfs.p1829;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] ms = {6};
        int[] ns = {4};
        int[][][] pictures = {
            {{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}}
        };
        int[][] answers = {{4, 5}};
        for (int i = 0; i < ms.length; i++) {
            System.out.println(Arrays.equals(s.solution(ms[i], ns[i], pictures[i]), answers[i]));
        }
    }

    public int[] dr = {-1, 1, 0, 0};
    public int[] dc = {0, 0, -1, 1};
    public int m, n;
    public int[][] picture;
    public boolean[][] visited;

    public int[] solution(int m, int n, int[][] picture) {
        this.m = m;
        this.n = n;
        this.picture = picture;
        visited = new boolean[m][n];
        int num = 0;
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] == 0 || visited[i][j]) continue;
                num++;
                max = Math.max(max, bfs(i, j));
            }
        }
        return new int[]{num, max};
    }

    public int bfs(int r, int c) {
        int color = picture[r][c];
        int size = 0;
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(r, c));
        visited[r][c] = true;
        while (!q.isEmpty()) {
            Node cur = q.poll();
            size++;
            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                if (!isAvailable(nr, nc, color)) continue;
                visited[nr][nc] = true;
                q.add(new Node(nr, nc));
            }
        }
        return size;
    }

    public boolean isAvailable(int r, int c, int color) {
        if (r < 0 || r >= m || c < 0 || c >= n) return false;
        if (visited[r][c]) return false;
        return picture[r][c] == color;
    }
}

class Node {
    int r, c;

    Node(int r, int c) {
        this.r = r;
        this.c = c;
    }
}