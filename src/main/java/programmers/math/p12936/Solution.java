package programmers.math.p12936;

import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] ns = {3};
        long[] ks = {5};
        int[][] answers = {{3, 1, 2}};
        for (int i = 0; i < ns.length; i++) {
            System.out.println(Arrays.equals(s.solution(ns[i], ks[i]), answers[i]));
        }
    }

    public int[] solution(int n, long k) {
        long[] dp = new long[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) dp[i] = dp[i - 1] * i;

        ArrayList<Integer> candidates = new ArrayList<>();
        for (int i = 1; i <= n; i++) candidates.add(i);

        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int idx = (int) ((k - 1) / dp[n - 1 - i]);
            res[i] = candidates.remove(idx);
            k = (k - 1) % dp[n - 1 - i] + 1;
        }
        return res;
    }
}