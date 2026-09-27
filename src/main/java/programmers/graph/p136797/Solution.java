package programmers.graph.p136797;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        String[] numberss = {"1756", "5123"};
        int[] answers = {10, 8};
        for (int i = 0; i < numberss.length; i++) {
            System.out.println(s.solution(numberss[i]) == answers[i]);
        }
    }

    int[] r = {3, 0, 0, 0, 1, 1, 1, 2, 2, 2};
    int[] c = {1, 0, 1, 2, 0, 1, 2, 0, 1, 2};

    public int solution(String numbers) {
        int inf = Integer.MAX_VALUE >> 1;
        int[] dp = new int[10];
        Arrays.fill(dp, inf);
        int first = numbers.charAt(0) - '0';
        dp[6] = dist(4, first);
        dp[4] = dist(6, first);
        int prevActive = first;
        for (int i = 1; i < numbers.length(); i++) {
            int digit = numbers.charAt(i) - '0';
            int[] next = new int[10];
            Arrays.fill(next, inf);
            for (int p = 0; p < 10; p++) {
                if (dp[p] == inf) continue;
                if (p != digit) {
                    int moved = dp[p] + dist(prevActive, digit);
                    if (moved < next[p]) next[p] = moved;
                }
                if (prevActive != digit) {
                    int moved = dp[p] + dist(p, digit);
                    if (moved < next[prevActive]) next[prevActive] = moved;
                }
            }
            dp = next;
            prevActive = digit;
        }
        int min = Integer.MAX_VALUE;
        for (int cost : dp) {
            if (cost < min) min = cost;
        }
        return min;
    }

    public int dist(int a, int b) {
        if (a == b) return 1;
        int dr = Math.abs(r[a] - r[b]);
        int dc = Math.abs(c[a] - c[b]);
        return 3 * Math.min(dr, dc) + 2 * Math.abs(dr - dc);
    }
}
