package programmers.implementation.p131701;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();

        int[][] elementsList = {{7, 9, 1, 1, 4}};
        int[] answers = {18};

        for (int i = 0; i < elementsList.length; i++) {
            System.out.println(s.solution(elementsList[i]) == answers[i]);
        }
    }

    public int solution(int[] elements) {
        Set<Integer> set = new HashSet<>();
        int n = elements.length;
        int[] dp = new int[n];
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i < n; i++) {
                dp[i] += elements[(i + len - 1) % n];
                set.add(dp[i]);
            }
        }
        return set.size();
    }
}