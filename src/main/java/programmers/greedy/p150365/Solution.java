package programmers.greedy.p150365;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] ns = {3, 2, 3};
        int[] ms = {4, 2, 3};
        int[] xs = {2, 1, 1};
        int[] ys = {3, 1, 2};
        int[] rs = {3, 2, 3};
        int[] cs = {1, 2, 3};
        int[] ks = {5, 2, 4};
        String[] answers = {"dllrl", "dr", "impossible"};
        for (int i = 0; i < ns.length; i++) {
            System.out.println(
                s.solution(ns[i], ms[i], xs[i], ys[i], rs[i], cs[i], ks[i]).equals(answers[i])
            );
        }
    }

    char[] dir = {'d', 'l', 'r', 'u'};
    int[] dr = {1, 0, 0, -1};
    int[] dc = {0, -1, 1, 0};
    int n;
    int m;

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        this.n = n;
        this.m = m;
        if (!isAvailable(x, y, r, c, k)) return "impossible";
        StringBuilder sb = new StringBuilder();
        int cr = x;
        int cc = y;
        for (int step = 0; step < k; step++) {
            int remain = k - step - 1;
            for (int d = 0; d < 4; d++) {
                int nr = cr + dr[d];
                int nc = cc + dc[d];
                if (!isAvailable(nr, nc, r, c, remain)) continue;
                sb.append(dir[d]);
                cr = nr;
                cc = nc;
                break;
            }
        }
        return sb.toString();
    }

    public boolean isAvailable(int cr, int cc, int r, int c, int remain) {
        if (cr < 1 || cr > n || cc < 1 || cc > m) return false;
        int need = Math.abs(cr - r) + Math.abs(cc - c);
        return need <= remain && (remain - need) % 2 == 0;
    }
}
