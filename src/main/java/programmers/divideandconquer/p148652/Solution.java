package programmers.divideandconquer.p148652;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] ns = {2};
        long[] ls = {4};
        long[] rs = {17};
        int[] answers = {8};
        for (int i = 0; i < ns.length; i++) {
            System.out.println(s.solution(ns[i], ls[i], rs[i]) == answers[i]);
        }
    }

    long[] pow5;
    long[] pow4;

    public int solution(int n, long l, long r) {
        pow5 = new long[n + 1];
        pow4 = new long[n + 1];
        pow5[0] = 1;
        pow4[0] = 1;
        for (int i = 1; i <= n; i++) {
            pow5[i] = pow5[i - 1] * 5;
            pow4[i] = pow4[i - 1] * 4;
        }
        return (int) (countOnes(n, r) - countOnes(n, l - 1));
    }

    public long countOnes(int level, long x) {
        if (x <= 0) return 0;
        if (level == 0) return 1;
        long bs = pow5[level - 1];
        long remain = x;
        long total = 0;
        for (int b = 0; b < 5 && remain > 0; b++) {
            long covered = Math.min(remain, bs);
            if (b != 2) total += covered == bs ? pow4[level - 1] : countOnes(level - 1, covered);
            remain -= covered;
        }
        return total;
    }
}