package programmers.dp.p12929;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] ns = {2, 3};
        int[] answers = {2, 5};
        for (int i = 0; i < ns.length; i++) {
            System.out.println(s.solution(ns[i]) == answers[i]);
        }
    }

    public int solution(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - 1 - j];
            }
        }
        return dp[n];
    }
}
