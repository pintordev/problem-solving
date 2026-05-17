package programmers.math.p92335;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] ns = {437674, 110011};
        int[] ks = {3, 10};
        int[] answers = {3, 2};
        for (int i = 0; i < ns.length; i++) {
            System.out.println(s.solution(ns[i], ks[i]) == answers[i]);
        }
    }

    public int solution(int n, int k) {
        int cnt = 0;
        for (String token : Integer.toString(n, k).split("0+")) {
            if (isPrime(token)) cnt++;
        }
        return cnt;
    }

    public boolean isPrime(String s) {
        if (s.isBlank() || s.equals("1")) return false;
        long n = Long.parseLong(s);
        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}