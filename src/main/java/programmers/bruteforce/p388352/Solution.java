package programmers.bruteforce.p388352;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] ns = {10, 15};
        int[][][] qs = {
                {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {3, 7, 8, 9, 10}, {2, 5, 7, 9, 10}, {3, 4, 5, 6, 7}},
                {{2, 3, 9, 12, 13}, {1, 4, 6, 7, 9}, {1, 2, 8, 10, 12}, {6, 7, 11, 13, 15}, {1, 4, 10, 11, 14}}
        };
        int[][] anss = {{2, 3, 4, 3, 3}, {2, 1, 3, 0, 1}};
        int[] answers = {3, 5};
        for (int i = 0; i < ns.length; i++) {
            System.out.println(s.solution(ns[i], qs[i], anss[i]) == answers[i]);
        }
    }

    int[] qBits;
    int[] ans;
    int cnt;

    public int solution(int n, int[][] q, int[] ans) {
        qBits = new int[q.length];
        for (int i = 0; i < q.length; i++) {
            for (int num : q[i]) {
                qBits[i] |= (1 << (num - 1));
            }
        }
        this.ans = ans;
        cnt = 0;
        dfs(1, n, 0, 0);
        return cnt;
    }

    public void dfs(int s, int n, int d, int mask) {
        if (d == 5) {
            for (int i = 0; i < qBits.length; i++) {
                if (Integer.bitCount(mask & qBits[i]) != ans[i]) return;
            }
            cnt++;
            return;
        }
        for (int i = s; i <= n; i++) {
            dfs(i + 1, n, d + 1, mask | (1 << (i - 1)));
        }
    }
}