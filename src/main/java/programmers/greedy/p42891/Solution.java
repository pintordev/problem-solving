package programmers.greedy.p42891;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] food_timess = {{3, 1, 2}};
        long[] ks = {5};
        int[] answers = {1};
        for (int i = 0; i < food_timess.length; i++) {
            System.out.println(s.solution(food_timess[i], ks[i]) == answers[i]);
        }
    }

    public int solution(int[] food_times, long k) {
        int n = food_times.length;
        int[][] foods = new int[n][];
        for (int i = 0; i < n; i++) {
            foods[i] = new int[]{food_times[i], i + 1};
        }
        Arrays.sort(foods, (a, b) -> Integer.compare(a[0], b[0]));
        long prev = 0;
        int i = 0;
        while (i < n) {
            long cost = (foods[i][0] - prev) * (n - i);
            if (k < cost) break;
            k -= cost;
            prev = foods[i][0];
            i++;
        }
        if (i == n) return -1;
        Arrays.sort(foods, i, n, (a, b) -> Integer.compare(a[1], b[1]));
        return foods[i + (int) (k % (n - i))][1];
    }
}
