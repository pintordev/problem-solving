package programmers.backtracking.p250134;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][][] mazes = {
                {{1, 4}, {0, 0}, {2, 3}},
                {{1, 0, 2}, {0, 0, 0}, {5, 0, 5}, {4, 0, 3}},
                {{1, 5}, {2, 5}, {4, 5}, {3, 5}},
                {{4, 1, 2, 3}}
        };
        int[] answers = {3, 7, 0, 0};
        for (int i = 0; i < mazes.length; i++) {
            System.out.println(s.solution(mazes[i]) == answers[i]);
        }
    }

    int[][] maze;
    int n;
    int m;
    int rer;
    int rec;
    int ber;
    int bec;
    int[][] visited;
    int res;
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};

    public int solution(int[][] maze) {
        this.maze = maze;
        n = maze.length;
        m = maze[0].length;
        visited = new int[n][m];
        res = Integer.MAX_VALUE;
        int rsr = 0, rsc = 0, bsr = 0, bsc = 0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (maze[r][c] == 1) {
                    rsr = r;
                    rsc = c;
                } else if (maze[r][c] == 2) {
                    bsr = r;
                    bsc = c;
                } else if (maze[r][c] == 3) {
                    rer = r;
                    rec = c;
                } else if (maze[r][c] == 4) {
                    ber = r;
                    bec = c;
                }
            }
        }
        visited[rsr][rsc] = 1;
        visited[bsr][bsc] = 2;
        dfs(rsr, rsc, bsr, bsc, 0);
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    public void dfs(int rr, int rc, int br, int bc, int turn) {
        boolean rDone = rr == rer && rc == rec;
        boolean bDone = br == ber && bc == bec;
        if (rDone && bDone) {
            res = Math.min(res, turn);
            return;
        }
        if (turn + 1 >= res) return;
        for (int i = 0; i < (rDone ? 1 : 4); i++) {
            int nrr = rDone ? rr : rr + dr[i];
            int nrc = rDone ? rc : rc + dc[i];
            if (!rDone && !isAvailable(nrr, nrc, 1)) continue;
            for (int j = 0; j < (bDone ? 1 : 4); j++) {
                int nbr = bDone ? br : br + dr[j];
                int nbc = bDone ? bc : bc + dc[j];
                if (!bDone && !isAvailable(nbr, nbc, 2)) continue;
                if (nrr == nbr && nrc == nbc) continue;
                if (nrr == br && nrc == bc && nbr == rr && nbc == rc) continue;
                if (!rDone) visited[nrr][nrc] |= 1;
                if (!bDone) visited[nbr][nbc] |= 2;
                dfs(nrr, nrc, nbr, nbc, turn + 1);
                if (!rDone) visited[nrr][nrc] ^= 1;
                if (!bDone) visited[nbr][nbc] ^= 2;
            }
        }
    }

    public boolean isAvailable(int r, int c, int bit) {
        if (r < 0 || r >= n || c < 0 || c >= m) return false;
        if (maze[r][c] == 5) return false;
        return (visited[r][c] & bit) == 0;
    }
}
