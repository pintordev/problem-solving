package programmers.dp.p1832;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] ms = {3, 3};
        int[] ns = {3, 6};
        int[][][] cityMaps = {
                {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}},
                {{0, 2, 0, 0, 0, 2}, {0, 0, 2, 0, 1, 0}, {1, 0, 0, 2, 2, 0}}
        };
        int[] answers = {6, 2};
        for (int i = 0; i < ms.length; i++) {
            System.out.println(s.solution(ms[i], ns[i], cityMaps[i]) == answers[i]);
        }
    }

    int MOD = 20170805;

    public int solution(int m, int n, int[][] cityMap) {
        int[][][] dp = new int[m][n][2];
        dp[0][0][0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (cityMap[i][j] == 1) continue;
                if (i > 0) dp[i][j][0] = ways(cityMap[i - 1][j], dp[i - 1][j][0], dp[i - 1][j][1]) % MOD;
                if (j > 0) dp[i][j][1] = ways(cityMap[i][j - 1], dp[i][j - 1][1], dp[i][j - 1][0]) % MOD;
            }
        }
        return (dp[m - 1][n - 1][0] + dp[m - 1][n - 1][1]) % MOD;
    }

    public int ways(int cell, int same, int other) {
        return cell == 2 ? same : same + other;
    }
}
