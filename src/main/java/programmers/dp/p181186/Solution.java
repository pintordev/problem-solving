package programmers.dp.p181186;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] ns = {2, 3};
        int[] answers = {3, 10};
        for (int i = 0; i < ns.length; i++) {
            System.out.println(s.solution(ns[i]) == answers[i]);
        }
    }

    public int solution(int n) {
        int mod = 1_000_000_007;
        long[] dp = new long[Math.max(n + 1, 6)];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 3;
        dp[3] = 10;
        dp[4] = 23;
        dp[5] = 62;
        for (int i = 6; i <= n; i++) {
            dp[i] = (dp[i - 1] + 2 * dp[i - 2] + 6 * dp[i - 3] + dp[i - 4] - dp[i - 6] + mod) % mod;
        }
        return (int) dp[n];
    }
}
