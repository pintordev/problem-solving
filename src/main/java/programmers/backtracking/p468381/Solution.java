package programmers.backtracking.p468381;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][][] grids = {
                {{1, 0, -1}, {0, 0, 7}, {0, 0, 2}},
                {{1, 0, 0, 0, 0, -1, -1}, {-1, 0, 0, 1, 0, 0, 1}},
                {{1, 0, 0, 0, 0}, {0, 0, 3, 0, 2}, {0, 0, 0, 0, 2}},
                {{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 1}},
                {{1, 7}, {0, 2}},
                {{1, -1, 0, 0}, {-1, 0, 0, 0}, {0, 0, 0, -1}, {0, 0, -1, 1}}
        };
        int[] answers = {2, 2, 4, 644, 1, 0};
        for (int i = 0; i < grids.length; i++) {
            System.out.println(s.solution(grids[i]) == answers[i]);
        }
    }

    int[] dr = {-1, 0, 1, 0};
    int[] dc = {0, 1, 0, -1};
    int[] conn = {0, 10, 5, 15, 9, 3, 6, 12};
    int[][] grid;
    int[][] seen;
    int n;
    int m;
    int need;

    public int solution(int[][] grid) {
        this.grid = grid;
        n = grid.length;
        m = grid[0].length;
        seen = new int[n][m];
        need = 0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (grid[r][c] > 0) need++;
            }
        }
        return go(0, 0, 1);
    }

    public int go(int r, int c, int d) {
        if (r < 0 || r >= n || c < 0 || c >= m || grid[r][c] < 0) return 0;
        if (grid[r][c] > 0) return step(r, c, d, grid[r][c]);
        int res = 0;
        need++;
        for (int k = 1; k <= 7; k++) {
            grid[r][c] = k;
            res += step(r, c, d, k);
        }
        grid[r][c] = 0;
        need--;
        return res;
    }

    public int step(int r, int c, int d, int t) {
        if ((conn[t] >> (d + 2) % 4 & 1) == 0) return 0;
        int prior = seen[r][c];
        int axis = t == 3 && d % 2 == 0 ? 2 : 1;
        if ((prior & axis) != 0) return 0;
        int e = t == 3 ? d : Integer.numberOfTrailingZeros(conn[t] & ~(1 << (d + 2) % 4));
        int delta = t == 3 && prior == 0 ? 0 : 1;
        seen[r][c] = prior | axis;
        need -= delta;
        int res = r == n - 1 && c == m - 1 ? (need == 0 ? 1 : 0) : go(r + dr[e], c + dc[e], e);
        need += delta;
        seen[r][c] = prior;
        return res;
    }
}
