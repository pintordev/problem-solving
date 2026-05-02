package programmers.dp.p12945;

public class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();

        int[] ns = {3, 5};
        int[] answers = {2, 5};

        for (int i = 0; i < ns.length; i++) {
            System.out.println(s.solution(ns[i]) == answers[i]);
        }
    }

    public int solution(int n) {
        int mod = 1234567;
        int fa = 0, fb = 1;
        for (int i = 2; i <= n; i++) {
            int fn = (fa + fb) % mod;
            fa = fb;
            fb = fn;
        }
        return fb;
    }
}