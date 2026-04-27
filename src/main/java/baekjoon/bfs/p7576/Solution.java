package baekjoon.bfs.p7576;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {
    public static int m, n;
    public static int[][] tomato;
    public static int unripe = 0;

    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        m = read(); n = read();
        tomato = new int[n][m];

        // 익은 토마토를 모두 큐에 추가 (다중 출발점 BFS)
        Queue<Node> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int t = read();
                tomato[i][j] = t;
                if (t == 0) unripe++;
                if (t == 1) q.add(new Node(i, j, 0));
            }
        }

        // 모든 익은 토마토에서 동시에 BFS 확산
        int ans = 0;
        while (!q.isEmpty() && unripe > 0) {
            Node cur = q.poll();
            for (int i = 0; i < dr.length; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];

                if (!isAvailable(nr, nc)) continue;

                tomato[nr][nc] = 1;
                unripe--;
                ans = cur.d + 1;
                q.add(new Node(nr, nc, cur.d + 1));
            }
        }

        // unripe > 0: 고립된 토마토 존재 → -1, 아니면 마지막으로 익은 날
        System.out.println(unripe > 0 ? -1 : ans);
    }

    private static boolean isAvailable(int r, int c) {
        if (r < 0 || r >= n || c < 0 || c >= m) return false;
        if (tomato[r][c] != 0) return false;
        return true;
    }

    public static int read() throws IOException {
        int c, n = System.in.read() & 15;
        boolean negative = n == 13;
        if (negative) n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return negative ? ~n + 1 : n;
    }
}

class Node {
    int r;
    int c;
    int d;

    public Node(int r, int c, int d) {
        this.r = r;
        this.c = c;
        this.d = d;
    }
}