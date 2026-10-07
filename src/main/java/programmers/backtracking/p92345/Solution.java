package programmers.backtracking.p92345;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][][] boards = {
                {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}},
                {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}},
                {{1, 1, 1, 1, 1}},
                {{1}}
        };
        int[][] alocs = {{1, 0}, {1, 0}, {0, 0}, {0, 0}};
        int[][] blocs = {{1, 2}, {1, 2}, {0, 4}, {0, 0}};
        int[] answers = {5, 4, 4, 0};
        for (int i = 0; i < boards.length; i++) {
            System.out.println(s.solution(boards[i], alocs[i], blocs[i]) == answers[i]);
        }
    }

    int[][] board;
    int n;
    int m;
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        this.board = board;
        n = board.length;
        m = board[0].length;
        return dfs(aloc[0], aloc[1], bloc[0], bloc[1]);
    }

    public int dfs(int ar, int ac, int br, int bc) {
        int win = Integer.MAX_VALUE;
        int lose = 0;
        for (int d = 0; d < 4; d++) {
            int nr = ar + dr[d];
            int nc = ac + dc[d];
            if (nr < 0 || nr >= n || nc < 0 || nc >= m || board[nr][nc] == 0) continue;
            if (ar == br && ac == bc) return 1;
            board[ar][ac] = 0;
            int res = dfs(br, bc, nr, nc) + 1;
            board[ar][ac] = 1;
            if (res % 2 == 1) win = Math.min(win, res);
            else lose = Math.max(lose, res);
        }
        return win == Integer.MAX_VALUE ? lose : win;
    }
}
