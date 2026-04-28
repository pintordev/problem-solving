package baekjoon.dp.p1149;

import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException {
        int n = read();
        int[][] dp = new int[n][3];
        for (int i = 0; i < 3; i++) {
            dp[0][i] = read();
        }

        for (int i = 1; i < n; i++) {
            dp[i][0] = read() + Math.min(dp[i - 1][1], dp[i - 1][2]);
            dp[i][1] = read() + Math.min(dp[i - 1][0], dp[i - 1][2]);
            dp[i][2] = read() + Math.min(dp[i - 1][0], dp[i - 1][1]);
        }

        System.out.println(Math.min(dp[n - 1][0], Math.min(dp[n - 1][1], dp[n - 1][2])));
    }

    private static int read() throws IOException {
        int c, n = System.in.read() & 15;
        boolean negative = n == 13;
        if (negative) n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return negative ? ~n + 1 : n;
    }
}