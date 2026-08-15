package programmers.search.p43238;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] ns = {6};
        int[][] timess = {{7, 10}};
        long[] answers = {28};
        for (int i = 0; i < ns.length; i++) {
            System.out.println(s.solution(ns[i], timess[i]) == answers[i]);
        }
    }

    public long solution(int n, int[] times) {
        int tLen = times.length;
        long lo = 0, hi = (long) n * times[tLen - 1];
        while (lo + 1 < hi) {
            long mid = (lo + hi) >> 1;
            long cnt = 0;
            for (int i = 0; i < tLen && cnt < n; i++) {
                cnt += mid / times[i];
            }
            if (cnt < n) lo = mid;
            else hi = mid;
        }
        return hi;
    }
}