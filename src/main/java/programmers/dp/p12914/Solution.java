package programmers.dp.p12914;

public class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();

        int[] ns = {4, 3};
        long[] answers = {5, 3};

        for (int i = 0; i < ns.length; i++) {
            System.out.println(s.solution(ns[i]) == answers[i]);
        }
    }

    public long solution(int n) {
        if (n <= 3) return n;
        int mod = 1234567;
        long da = 2;
        long db = 3;
        for (int i = 4; i <= n; i++) {
            long dc = (da + db) % mod;
            da = db;
            db = dc;
        }
        return db;
    }
}