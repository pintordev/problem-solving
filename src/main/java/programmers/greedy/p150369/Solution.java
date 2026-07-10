package programmers.greedy.p150369;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] caps = {4, 2};
        int[] ns = {5, 7};
        int[][] deliveriess = {
                {1, 0, 3, 1, 2},
                {1, 0, 2, 0, 1, 0, 2}
        };
        int[][] pickupss = {
                {0, 3, 0, 4, 0},
                {0, 2, 0, 1, 0, 2, 0}
        };
        long[] answers = {16, 30};
        for (int i = 0; i < caps.length; i++) {
            System.out.println(s.solution(caps[i], ns[i], deliveriess[i], pickupss[i]) == answers[i]);
        }
    }

    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long res = 0;
        int ds = 0;
        int ps = 0;
        for (int i = n - 1; i >= 0; i--) {
            ds += deliveries[i];
            ps += pickups[i];
            while (ds > 0 || ps > 0) {
                res += (i + 1) << 1;
                ds -= cap;
                ps -= cap;
            }
        }
        return res;
    }
}