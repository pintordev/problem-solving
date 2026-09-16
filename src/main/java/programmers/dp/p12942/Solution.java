package programmers.dp.p12942;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][][] matrixSizess = {
            {{5, 3}, {3, 10}, {10, 6}}
        };
        int[] answers = {270};
        for (int i = 0; i < matrixSizess.length; i++) {
            System.out.println(s.solution(matrixSizess[i]) == answers[i]);
        }
    }

    public int solution(int[][] matrixSizes) {
        int n = matrixSizes.length;
        int[] dims = new int[n + 1];
        dims[0] = matrixSizes[0][0];
        for (int i = 0; i < n; i++) {
            dims[i + 1] = matrixSizes[i][1];
        }
        int[][] dp = new int[n][n];
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k + 1][j] + dims[i] * dims[k + 1] * dims[j + 1];
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }
        return dp[0][n - 1];
    }
}
