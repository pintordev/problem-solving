package programmers.bruteforce.p131702;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][][] clockHandss = {
                {{0, 3, 3, 0}, {3, 2, 2, 3}, {0, 3, 2, 0}, {0, 3, 3, 3}}
        };
        int[] answers = {3};
        for (int i = 0; i < clockHandss.length; i++) {
            System.out.println(s.solution(clockHandss[i]) == answers[i]);
        }
    }

    int n;
    int[][] board;
    int[] dr = {0, -1, 1, 0, 0};
    int[] dc = {0, 0, 0, -1, 1};

    public int solution(int[][] clockHands) {
        n = clockHands.length;
        int res = Integer.MAX_VALUE;
        for (int mask = 0; mask < (1 << (2 * n)); mask++) {
            board = new int[n][];
            for (int r = 0; r < n; r++) {
                board[r] = clockHands[r].clone();
            }
            int cnt = 0;
            for (int c = 0; c < n; c++) {
                int rot = (mask >> (2 * c)) & 3;
                rotate(0, c, rot);
                cnt += rot;
            }
            for (int r = 1; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    int rot = (4 - board[r - 1][c]) % 4;
                    rotate(r, c, rot);
                    cnt += rot;
                }
            }
            if (!isAligned()) continue;
            res = Math.min(res, cnt);
        }
        return res;
    }

    public void rotate(int r, int c, int rot) {
        for (int d = 0; d < 5; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (!isAvailable(nr, nc)) continue;
            board[nr][nc] = (board[nr][nc] + rot) % 4;
        }
    }

    public boolean isAvailable(int r, int c) {
        if (r < 0 || r >= n) return false;
        if (c < 0 || c >= n) return false;
        return true;
    }

    public boolean isAligned() {
        for (int c = 0; c < n; c++) {
            if (board[n - 1][c] != 0) return false;
        }
        return true;
    }
}
