package programmers.math.p12938;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] ns = {2, 2, 2};
        int[] ss = {9, 1, 8};
        int[][] answers = {{4, 5}, {-1}, {4, 4}};
        for (int i = 0; i < ns.length; i++) {
            System.out.println(Arrays.equals(sol.solution(ns[i], ss[i]), answers[i]));
        }
    }

    public int[] solution(int n, int s) {
        if (s < n) return new int[]{-1};
        int q = s / n;
        int r = s % n;
        int[] set = new int[n];
        for (int i = 0; i < n - r; i++) {
            set[i] = q;
        }
        for (int i = n - r; i < n; i++) {
            set[i] = q + 1;
        }
        return set;
    }
}