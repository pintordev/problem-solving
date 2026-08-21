package programmers.dp.p12907;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] ns = {5};
        int[][] moneys = {{1, 2, 5}};
        int[] answers = {4};
        for (int i = 0; i < ns.length; i++) {
            System.out.println(s.solution(ns[i], moneys[i]) == answers[i]);
        }
    }

    public int solution(int n, int[] money) {
        int MOD = 1_000_000_007;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int coin : money) {
            for (int j = coin; j <= n; j++) {
                dp[j] = (dp[j] + dp[j - coin]) % MOD;
            }
        }
        return dp[n];
    }
}