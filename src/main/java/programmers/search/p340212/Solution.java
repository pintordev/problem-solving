package programmers.search.p340212;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] diffs = {
            {1, 5, 3},
            {1, 4, 4, 2},
            {1, 328, 467, 209, 54},
            {1, 99999, 100000, 99995}
        };
        int[][] times = {
            {2, 4, 7},
            {6, 3, 8, 2},
            {2, 7, 1, 4, 3},
            {9999, 9001, 9999, 9001}
        };
        long[] limits = {30, 59, 1723, 3456789012L};
        int[] answers = {3, 2, 294, 39354};
        for (int i = 0; i < answers.length; i++) {
            System.out.println(s.solution(diffs[i], times[i], limits[i]) == answers[i]);
        }
    }

    public int solution(int[] diffs, int[] times, long limit) {
        int lo = 0, hi = 100001;
        while (lo + 1 < hi) {
            int mid = (lo + hi) >> 1;
            if (canSolve(diffs, times, limit, mid)) hi = mid;
            else lo = mid;
        }
        return hi;
    }

    public boolean canSolve(int[] diffs, int[] times, long limit, int level) {
        long total = 0;
        for (int i = 0; i < diffs.length; i++) {
            if (diffs[i] <= level) total += times[i];
            else total += (long) (diffs[i] - level) * (times[i] + times[i - 1]) + times[i];
            if (total > limit) return false;
        }
        return true;
    }
}