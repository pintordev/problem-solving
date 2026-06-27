package programmers.bruteforce.p150368;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();

        int[][][] users = {
            {{40, 10000}, {25, 10000}},
            {{40, 2900}, {23, 10000}, {11, 5200}, {5, 5900}, {40, 3100}, {27, 9200}, {32, 6900}}
        };
        int[][] emoticons = {
            {7000, 9000},
            {1300, 1500, 1600, 4900}
        };
        int[][] answers = {
            {1, 5400},
            {4, 13860}
        };

        for (int i = 0; i < users.length; i++) {
            System.out.println(Arrays.equals(s.solution(users[i], emoticons[i]), answers[i]));
        }
    }

    public int[] solution(int[][] users, int[] emoticons) {
        int[] rates = {10, 20, 30, 40};
        int m = emoticons.length;
        int[] best = {0, 0};
        for (int mask = 0; mask < (1 << (2 * m)); mask++) {
            int subscribers = 0;
            int sales = 0;
            for (int[] user : users) {
                int threshold = user[0];
                int limit = user[1];
                int total = 0;
                for (int i = 0; i < m; i++) {
                    int rate = rates[(mask >> (2 * i)) & 3];
                    if (rate >= threshold) total += emoticons[i] * (100 - rate) / 100;
                }
                if (total >= limit) subscribers++;
                else sales += total;
            }
            if (subscribers > best[0] || (subscribers == best[0] && sales > best[1])) {
                best[0] = subscribers;
                best[1] = sales;
            }
        }
        return best;
    }
}