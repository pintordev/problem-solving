package programmers.graph.p49191;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] ns = {5};
        int[][][] resultss = {
            {{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}}
        };
        int[] answers = {2};
        for (int i = 0; i < ns.length; i++) {
            System.out.println(s.solution(ns[i], resultss[i]) == answers[i]);
        }
    }

    public int solution(int n, int[][] results) {
        boolean[][] win = new boolean[n + 1][n + 1];
        for (int[] r : results) {
            win[r[0]][r[1]] = true;
        }
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (win[i][k] && win[k][j]) win[i][j] = true;
                }
            }
        }
        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            boolean confirmed = true;
            for (int j = 1; j <= n; j++) {
                if (i == j) continue;
                if (!win[i][j] && !win[j][i]) {
                    confirmed = false;
                    break;
                }
            }
            if (confirmed) cnt++;
        }
        return cnt;
    }
}
