package baekjoon.bruteforce.p1018;

import java.io.IOException;
import java.util.Arrays;

public class Solution {
    public static void main(String[] args) throws IOException {
        int n = read(), m = read();
        char[][] board = new char[n][m];
        for (int i = 0; i < n; i++) {
            board[i] = readChars();
        }
        char[] w = "WBWBWBWB".toCharArray(), b = "BWBWBWBW".toCharArray();
        char[][] wBoard = new char[8][8];
        char[][] bBoard = new char[8][8];
        for (int i = 0; i < 8; i++) {
            if (i % 2 == 0) {
                wBoard[i] = w;
                bBoard[i] = b;
            } else {
                wBoard[i] = b;
                bBoard[i] = w;
            }
        }

        int min = 64;
        for (int i = 0; i <= n - 8; i++) {
            for (int j = 0; j <= m - 8; j++) {
                min = Math.min(min, change(board, i, j, wBoard, bBoard));
            }
        }
        System.out.println(min);
    }

    public static int change(char[][] board, int si, int sj, char[][] wBoard, char[][] bBoard) {
        int wChange = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i + si][j + sj] != wBoard[i][j]) wChange++;
            }
        }
        return Math.min(wChange, 64 - wChange);
    }

    public static char[] readChars() throws IOException {
        char[] c = new char[50];
        int idx = 0, ch;
        while ((ch = System.in.read()) > 32) c[idx++] = (char) ch;
        return Arrays.copyOf(c, idx);
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
