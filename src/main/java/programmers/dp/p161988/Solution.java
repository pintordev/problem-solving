package programmers.dp.p161988;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] sequences = {
                {2, 3, -6, 1, 3, -1, 2, 4},
        };
        long[] answers = {10};
        for (int i = 0; i < sequences.length; i++) {
            System.out.println(s.solution(sequences[i]) == answers[i]);
        }
    }

    public long solution(int[] sequence) {
        long max = 0, dp0 = 0, dp1 = 0;
        int f = 1;
        for (int cur : sequence) {
            cur *= f;
            dp0 = Math.max(dp0 + cur, cur);
            dp1 = Math.max(dp1 - cur, -cur);
            max = Math.max(max, Math.max(dp0, dp1));
            f *= -1;
        }
        return max;
    }
}