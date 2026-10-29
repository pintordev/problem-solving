package programmers.search.p43236;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] distances = {25};
        int[][] rockss = {{2, 14, 11, 21, 17}};
        int[] ns = {2};
        int[] answers = {4};
        for (int i = 0; i < distances.length; i++) {
            System.out.println(s.solution(distances[i], rockss[i], ns[i]) == answers[i]);
        }
    }

    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);
        int lo = 0, hi = distance + 1;
        while (lo + 1 < hi) {
            int mid = (lo + hi) >>> 1;
            int cnt = 0, prev = 0;
            for (int rock : rocks) {
                if (rock - prev < mid) cnt++;
                else prev = rock;
            }
            if (distance - prev < mid) cnt++;
            if (cnt <= n) lo = mid;
            else hi = mid;
        }
        return lo;
    }
}
