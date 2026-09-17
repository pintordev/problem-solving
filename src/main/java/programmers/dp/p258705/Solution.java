package programmers.dp.p258705;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] ns = {4, 2, 10};
        int[][] topss = {
            {1, 1, 0, 1},
            {0, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        int[] answers = {149, 11, 7704};
        for (int i = 0; i < ns.length; i++) {
            System.out.println(s.solution(ns[i], topss[i]) == answers[i]);
        }
    }

    public int solution(int n, int[] tops) {
        int MOD = 10007;
        int dp0 = 1;
        int dp1 = 0;
        for (int i = 0; i < n; i++) {
            int x = tops[i];
            int nextDp0 = (dp1 * (1 + x) + dp0 * (2 + x)) % MOD;
            int nextDp1 = (dp0 + dp1) % MOD;
            dp0 = nextDp0;
            dp1 = nextDp1;
        }
        return (dp0 + dp1) % MOD;
    }
}
