package programmers.dp.p1843;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        String[][] arrs = {
                {"1", "-", "3", "+", "5", "-", "8"},
                {"5", "-", "3", "+", "1", "+", "2", "-", "4"}
        };
        int[] answers = {1, 3};
        for (int i = 0; i < arrs.length; i++) {
            System.out.println(s.solution(arrs[i]) == answers[i]);
        }
    }

    int[][][] dp;
    boolean[] plus;

    public int solution(String[] arr) {
        int n = (arr.length >> 1) + 1;
        int[] nums = new int[n];
        plus = new boolean[n - 1];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(arr[i << 1]);
            if (i < n - 1) plus[i] = arr[(i << 1) + 1].equals("+");
        }
        dp = new int[n][n][2];
        for (int i = 0; i < n; i++) {
            dp[i][i][0] = nums[i];
            dp[i][i][1] = nums[i];
        }
        for (int len = 1; len < n; len++) {
            for (int s = 0; s + len < n; s++) {
                cal(s, s + len);
            }
        }
        return dp[0][n - 1][1];
    }

    public void cal(int s, int e) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = s; i < e; i++) {
            if (plus[i]) {
                min = Math.min(min, dp[s][i][0] + dp[i + 1][e][0]);
                max = Math.max(max, dp[s][i][1] + dp[i + 1][e][1]);
            } else {
                min = Math.min(min, dp[s][i][0] - dp[i + 1][e][1]);
                max = Math.max(max, dp[s][i][1] - dp[i + 1][e][0]);
            }
        }
        dp[s][e][0] = min;
        dp[s][e][1] = max;
    }
}
