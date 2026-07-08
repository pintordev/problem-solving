package programmers.backtracking.p92342;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] ns = {5, 1, 9, 10};
        int[][] infos = {
            {2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3}
        };
        int[][] answers = {
            {0, 2, 2, 0, 1, 0, 0, 0, 0, 0, 0},
            {-1},
            {1, 1, 2, 0, 1, 2, 2, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 2}
        };
        for (int i = 0; i < ns.length; i++) {
            System.out.println(Arrays.equals(s.solution(ns[i], infos[i]), answers[i]));
        }
    }

    int[] info;
    int[] best;
    int bestDiff;

    public int[] solution(int n, int[] info) {
        this.info = info;
        best = null;
        bestDiff = 0;
        dfs(0, n, new int[11]);
        return best == null ? new int[]{-1} : best;
    }

    public void dfs(int d, int remain, int[] ryan) {
        if (d == 10) {
            ryan[10] = remain;
            int diff = calDiff(ryan);
            if (diff > 0 && (diff > bestDiff || (diff == bestDiff && isBetter(ryan)))) {
                best = ryan.clone();
                bestDiff = diff;
            }
            ryan[10] = 0;
            return;
        }
        int need = info[d] + 1;
        if (remain >= need) {
            ryan[d] = need;
            dfs(d + 1, remain - need, ryan);
            ryan[d] = 0;
        }
        dfs(d + 1, remain, ryan);
    }

    public int calDiff(int[] ryan) {
        int rs = 0, as = 0;
        for (int i = 0; i < 11; i++) {
            if (ryan[i] == 0 && info[i] == 0) continue;
            int s = 10 - i;
            if (ryan[i] > info[i]) rs += s;
            else as += s;
        }
        return rs - as;
    }

    public boolean isBetter(int[] ryan) {
        for (int i = 10; i >= 0; i--) {
            if (ryan[i] != best[i]) return ryan[i] > best[i];
        }
        return false;
    }
}