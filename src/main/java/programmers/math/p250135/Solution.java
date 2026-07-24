package programmers.math.p250135;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] h1s = {0, 12, 0, 11, 11, 1, 0};
        int[] m1s = {5, 0, 6, 59, 58, 5, 0};
        int[] s1s = {30, 0, 1, 30, 59, 5, 0};
        int[] h2s = {0, 12, 0, 12, 11, 1, 23};
        int[] m2s = {7, 0, 6, 0, 59, 5, 59};
        int[] s2s = {0, 30, 6, 0, 0, 6, 59};
        int[] answers = {2, 1, 0, 1, 1, 2, 2852};
        for (int i = 0; i < h1s.length; i++) {
            System.out.println(s.solution(h1s[i], m1s[i], s1s[i], h2s[i], m2s[i], s2s[i]) == answers[i]);
        }
    }

    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        long t1 = h1 * 3600L + m1 * 60L + s1;
        long t2 = h2 * 3600L + m2 * 60L + s2;
        long ms = cntCoincide(t1, t2, 3600, 59);
        long hs = cntCoincide(t1, t2, 43200, 719);
        long both = cntCoincide(t1, t2, 43200, 1);
        return (int) (ms + hs - both);
    }

    public long cntCoincide(long t1, long t2, long num, long den) {
        long lo = (den * t1 + num - 1) / num;
        long hi = (den * t2) / num;
        long cnt = hi - lo + 1;
        return cnt > 0 ? cnt : 0;
    }
}