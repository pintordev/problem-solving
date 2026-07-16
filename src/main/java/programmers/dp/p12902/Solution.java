package programmers.dp.p12902;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] ns = {4};
        int[] answers = {11};
        for (int i = 0; i < ns.length; i++) {
            System.out.println(s.solution(ns[i]) == answers[i]);
        }
    }

    public int solution(int n) {
        int MOD = 1_000_000_007;
        if (n % 2 != 0) return 0;
        long a = 1; // f(0)
        long b = 3; // f(2)
        for (int i = 4; i <= n; i += 2) {
            long tmp = (4 * b - a + MOD) % MOD;
            a = b;
            b = tmp;
        }
        return (int) b;
    }
}