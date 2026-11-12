package programmers.graph.p1839;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] ms = {3, 4, 5};
        int[] ns = {3, 6, 5};
        int[] ss = {150, 25, 12};
        int[][][] time_maps = {
                {{0, 2, 99}, {100, 100, 4}, {1, 2, 0}},
                {{0, 1, 1, -1, 2, 4}, {-1, 7, 2, 1, 5, 7}, {-1, 1, -1, 1, 6, 3}, {-1, 1, -1, -1, 7, 0}},
                {{0, 1, 1, 1, 1}, {9, 9, 9, 1, 9}, {1, 1, 1, 1, 9}, {1, 1, 5, 9, 9}, {1, 1, 1, 1, 0}}
        };
        int[][] answers = {{4, 103}, {8, 15}, {12, 11}};
        for (int i = 0; i < ms.length; i++) {
            System.out.println(Arrays.equals(s.solution(ms[i], ns[i], ss[i], time_maps[i]), answers[i]));
        }
    }

    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};

    public int[] solution(int m, int n, int s, int[][] time_map) {
        long[][] cost = new long[m][n];
        for (int r = 0; r < m; r++) {
            Arrays.fill(cost[r], Long.MAX_VALUE);
        }
        cost[0][0] = 0;
        for (int len = 1; ; len++) {
            long[][] next = new long[m][n];
            for (int r = 0; r < m; r++) {
                Arrays.fill(next[r], Long.MAX_VALUE);
            }
            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    if (cost[r][c] == Long.MAX_VALUE) continue;
                    for (int d = 0; d < 4; d++) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];
                        if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                        if (time_map[nr][nc] == -1) continue;
                        long nt = cost[r][c] + time_map[nr][nc];
                        if (nt > s) continue;
                        next[nr][nc] = Math.min(next[nr][nc], nt);
                    }
                }
            }
            if (next[m - 1][n - 1] != Long.MAX_VALUE) return new int[]{len, (int) next[m - 1][n - 1]};
            cost = next;
        }
    }
}
