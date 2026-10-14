package programmers.dp.p214289;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] temperatures = {28, -10, 11, 11};
        int[] t1s = {18, -5, 8, 8};
        int[] t2s = {26, 5, 10, 10};
        int[] as = {10, 5, 10, 10};
        int[] bs = {8, 1, 1, 100};
        int[][] onboards = {
                {0, 0, 1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1},
                {0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1}
        };
        int[] answers = {40, 25, 20, 60};
        for (int i = 0; i < temperatures.length; i++) {
            System.out.println(s.solution(temperatures[i], t1s[i], t2s[i], as[i], bs[i], onboards[i]) == answers[i]);
        }
    }

    int INF = Integer.MAX_VALUE;

    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        int n = onboard.length;
        int min = Math.min(temperature, Math.min(t1, t2));
        int max = Math.max(temperature, Math.max(t1, t2)) - min;
        temperature -= min;
        t1 -= min;
        t2 -= min;
        int[] dp = new int[max + 1];
        Arrays.fill(dp, INF);
        dp[temperature] = 0;
        for (int i = 1; i < n; i++) {
            int[] next = new int[max + 1];
            Arrays.fill(next, INF);
            for (int t = 0; t <= max; t++) {
                if (dp[t] == INF) continue;
                int[] dts = {Integer.compare(temperature, t), -1, 1, 0};
                int[] costs = {0, a, a, b};
                for (int k = 0; k < 4; k++) {
                    int nt = t + dts[k];
                    if (nt < 0 || nt > max) continue;
                    if (onboard[i] == 1 && (nt < t1 || nt > t2)) continue;
                    next[nt] = Math.min(next[nt], dp[t] + costs[k]);
                }
            }
            dp = next;
        }
        int res = INF;
        for (int cost : dp) {
            res = Math.min(res, cost);
        }
        return res;
    }
}
