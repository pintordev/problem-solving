package programmers.dp.p468375;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] hs = {3, 1, 4};
        String[][] grids = {
                {".#.##..", ".#..##.", ".......", "##.###.", ".@.#...", "...#..."},
                {"@......", ".######", ".......", "######.", ".......", ".####..", "....#.."},
                {"........#", "........#", "@.......#", ".#.#....#", "........#", "#........", "#.#..####", "..#..####", ".....####"}
        };
        int[][][] panelss = {
                {{2, 3, 4}, {2, 5, 6}, {1, 1, 1}, {3, 6, 3}},
                {{1, 7, 4}, {1, 3, 5}, {1, 1, 3}},
                {{2, 9, 1}, {2, 1, 8}, {1, 1, 3}, {3, 3, 2}, {1, 2, 8}}
        };
        int[][][] seqss = {
                {{3, 2}, {1, 2}, {4, 1}, {4, 3}},
                {{1, 3}, {3, 2}},
                {{1, 2}, {2, 3}, {3, 4}, {4, 5}}
        };
        int[] answers = {36, 31, 47};
        for (int i = 0; i < hs.length; i++) {
            System.out.println(s.solution(hs[i], grids[i], panelss[i], seqss[i]) == answers[i]);
        }
    }

    int h;
    int n;
    int m;
    String[] grid;
    int[] dr = {1, -1, 0, 0};
    int[] dc = {0, 0, 1, -1};

    public int solution(int h, String[] grid, int[][] panels, int[][] seqs) {
        this.h = h;
        this.grid = grid;
        n = grid.length;
        m = grid[0].length();
        int k = panels.length;
        int[][] dist = new int[k][k];
        for (int i = 0; i < k; i++) {
            int[][][] d = bfs(panels[i]);
            for (int j = 0; j < k; j++) {
                dist[i][j] = d[panels[j][0] - 1][panels[j][1] - 1][panels[j][2] - 1];
            }
        }
        int[] pre = new int[k];
        for (int[] seq : seqs) {
            pre[seq[1] - 1] |= 1 << (seq[0] - 1);
        }
        int[][] dp = new int[1 << k][k];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        for (int i = 0; i < k; i++) {
            if (pre[i] != 0) continue;
            dp[1 << i][i] = dist[0][i];
        }
        for (int mask = 1; mask < (1 << k); mask++) {
            for (int last = 0; last < k; last++) {
                if (dp[mask][last] == Integer.MAX_VALUE) continue;
                for (int next = 0; next < k; next++) {
                    if ((mask & (1 << next)) != 0 || (pre[next] & mask) != pre[next]) continue;
                    int nmask = mask | (1 << next);
                    dp[nmask][next] = Math.min(dp[nmask][next], dp[mask][last] + dist[last][next]);
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int last = 0; last < k; last++) {
            res = Math.min(res, dp[(1 << k) - 1][last]);
        }
        return res;
    }

    public int[][][] bfs(int[] start) {
        int[][][] d = new int[h][n][m];
        for (int[][] floor : d) {
            for (int[] row : floor) {
                Arrays.fill(row, Integer.MAX_VALUE);
            }
        }
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(start[0] - 1, start[1] - 1, start[2] - 1));
        d[start[0] - 1][start[1] - 1][start[2] - 1] = 0;
        while (!q.isEmpty()) {
            Node cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                if (!isAvailable(nr, nc) || d[cur.f][nr][nc] != Integer.MAX_VALUE) continue;
                d[cur.f][nr][nc] = d[cur.f][cur.r][cur.c] + 1;
                q.add(new Node(cur.f, nr, nc));
            }
            if (grid[cur.r].charAt(cur.c) != '@') continue;
            for (int nf = cur.f - 1; nf <= cur.f + 1; nf += 2) {
                if (nf < 0 || nf >= h || d[nf][cur.r][cur.c] != Integer.MAX_VALUE) continue;
                d[nf][cur.r][cur.c] = d[cur.f][cur.r][cur.c] + 1;
                q.add(new Node(nf, cur.r, cur.c));
            }
        }
        return d;
    }

    public boolean isAvailable(int nr, int nc) {
        if (nr < 0 || nr >= n || nc < 0 || nc >= m) return false;
        if (grid[nr].charAt(nc) == '#') return false;
        return true;
    }
}

class Node {
    int f;
    int r;
    int c;

    Node(int f, int r, int c) {
        this.f = f;
        this.r = r;
        this.c = c;
    }
}
