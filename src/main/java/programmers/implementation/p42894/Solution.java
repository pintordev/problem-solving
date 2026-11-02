package programmers.implementation.p42894;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][][] boards = {
                {{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,4,0,0,0},{0,0,0,0,0,4,4,0,0,0},{0,0,0,0,3,0,4,0,0,0},{0,0,0,2,3,0,0,0,5,5},{1,2,2,2,3,3,0,0,0,5},{1,1,1,0,0,0,0,0,0,5}}
        };
        int[] answers = {2};
        for (int i = 0; i < boards.length; i++) {
            System.out.println(s.solution(boards[i]) == answers[i]);
        }
    }

    int[][] board;
    int n;
    int[] sr = new int[201];
    int[] er = new int[201];
    int[] sc = new int[201];
    int[] ec = new int[201];

    public int solution(int[][] board) {
        this.board = board;
        n = board.length;
        Arrays.fill(sr, n);
        Arrays.fill(sc, n);
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                int v = board[r][c];
                if (v == 0) continue;
                sr[v] = Math.min(sr[v], r);
                er[v] = Math.max(er[v], r);
                sc[v] = Math.min(sc[v], c);
                ec[v] = Math.max(ec[v], c);
            }
        }
        int res = 0;
        boolean changed = true;
        while (changed) {
            changed = false;
            for (int v = 1; v <= 200; v++) {
                if (!removable(v)) continue;
                remove(v);
                res++;
                changed = true;
            }
        }
        return res;
    }

    public boolean removable(int v) {
        if (sr[v] == n) return false;
        if ((er[v] - sr[v] + 1) * (ec[v] - sc[v] + 1) != 6) return false;
        for (int r = sr[v]; r <= er[v]; r++) {
            for (int c = sc[v]; c <= ec[v]; c++) {
                if (board[r][c] == v) continue;
                if (board[r][c] != 0) return false;
                if (!open(r, c)) return false;
            }
        }
        return true;
    }

    public boolean open(int r, int c) {
        for (int i = 0; i < r; i++) {
            if (board[i][c] != 0) return false;
        }
        return true;
    }

    public void remove(int v) {
        for (int r = sr[v]; r <= er[v]; r++) {
            for (int c = sc[v]; c <= ec[v]; c++) {
                if (board[r][c] == v) board[r][c] = 0;
            }
        }
        sr[v] = n;
    }
}
