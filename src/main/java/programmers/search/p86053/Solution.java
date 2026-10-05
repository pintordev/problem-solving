package programmers.search.p86053;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] as = {10, 90};
        int[] bs = {10, 500};
        int[][] gs = {{100}, {70, 70, 0}};
        int[][] ss = {{100}, {0, 0, 500}};
        int[][] ws = {{7}, {100, 100, 2}};
        int[][] ts = {{10}, {4, 8, 1}};
        long[] answers = {50, 499};
        for (int i = 0; i < as.length; i++) {
            System.out.println(s.solution(as[i], bs[i], gs[i], ss[i], ws[i], ts[i]) == answers[i]);
        }
    }

    int a;
    int b;
    int[] g;
    int[] s;
    int[] w;
    int[] t;

    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        this.a = a;
        this.b = b;
        this.g = g;
        this.s = s;
        this.w = w;
        this.t = t;
        long lo = 0;
        long hi = (a + b) * 2L * 100_000;
        while (lo + 1 < hi) {
            long mid = (lo + hi) >> 1;
            if (can(mid)) hi = mid;
            else lo = mid;
        }
        return hi;
    }

    public boolean can(long time) {
        long gold = 0;
        long silver = 0;
        long total = 0;
        for (int i = 0; i < g.length; i++) {
            long cap = (time + t[i]) / (2L * t[i]) * w[i];
            gold += Math.min(g[i], cap);
            silver += Math.min(s[i], cap);
            total += Math.min((long) g[i] + s[i], cap);
        }
        return gold >= a && silver >= b && total >= (long) a + b;
    }
}
