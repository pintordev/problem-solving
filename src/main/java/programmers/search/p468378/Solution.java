package programmers.search.p468378;

import java.util.function.Function;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] depths = {
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
                {1, 1, 1, 1, 1},
                {2, 100, 1, 100, 3, 100, 1},
                {2, 100, 1, 100, 3, 100, 1},
                {3, 2, 1, 2, 3, 2, 1, 2},
                {1, 1000, 1, 1, 1, 10, 15, 1}
        };
        int[] moneys = {55, 3, 200, 200, 8, 1002};
        int[] answers = {3, 5, 6, 5, 5, 2};
        for (int i = 0; i < depths.length; i++) {
            s.depth = depths[i];
            s.treasure = answers[i];
            s.spent = 0;
            int result = s.solution(depths[i], moneys[i], s::excavate);
            System.out.println(result == answers[i] && s.spent <= moneys[i]);
        }
    }

    // 프로그래머스에서 제공되는 함수 (로컬 테스트용 구현)
    public int excavate(int col) {
        spent += depth[col - 1];
        return Integer.compare(treasure, col);
    }

    int[] depth;
    int treasure;
    int spent;

    public int solution(int[] depth, int money, Function<Integer, Integer> excavate) {
        int n = depth.length;
        int[][] dp = new int[n + 2][n + 2];
        int[][] pick = new int[n + 2][n + 2];
        for (int len = 1; len <= n; len++) {
            for (int l = 1; l + len - 1 <= n; l++) {
                int r = l + len - 1;
                dp[l][r] = Integer.MAX_VALUE;
                for (int k = l; k <= r; k++) {
                    int cost = depth[k - 1] + Math.max(dp[l][k - 1], dp[k + 1][r]);
                    if (cost >= dp[l][r]) continue;
                    dp[l][r] = cost;
                    pick[l][r] = k;
                }
            }
        }
        int l = 1;
        int r = n;
        while (true) {
            int k = pick[l][r];
            int dir = excavate.apply(k);
            if (dir == 0) return k;
            if (dir < 0) r = k - 1;
            else l = k + 1;
        }
    }
}
