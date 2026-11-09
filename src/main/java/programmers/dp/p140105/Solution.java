package programmers.dp.p140105;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] ns = {3, 3, 3};
        int[] counts = {1, 2, 3};
        int[] answers = {8, 6, 1};
        for (int i = 0; i < ns.length; i++) {
            System.out.println(s.solution(ns[i], counts[i]) == answers[i]);
        }
    }

    public int solution(int n, int count) {
        int mod = 1_000_000_007;
        long[][] dp = new long[n + 1][count + 1];
        dp[1][1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= count; j++) {
                dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j] * 2 * (i - 1)) % mod;
            }
        }
        return (int) dp[n][count];
    }
}
