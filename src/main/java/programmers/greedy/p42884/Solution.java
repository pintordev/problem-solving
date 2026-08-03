package programmers.greedy.p42884;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][][] routess = {
                {{-20, -15}, {-14, -5}, {-18, -13}, {-5, -3}}
        };
        int[] answers = {2};
        for (int i = 0; i < routess.length; i++) {
            System.out.println(s.solution(routess[i]) == answers[i]);
        }
    }

    public int solution(int[][] routes) {
        Arrays.sort(routes, (a, b) -> Integer.compare(a[1], b[1]));
        int cnt = 0;
        int pos = Integer.MIN_VALUE;
        for (int[] route : routes) {
            if (route[0] <= pos) continue;
            pos = route[1];
            cnt++;
        }
        return cnt;
    }
}