package programmers.sort.p152995;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][][] scoress = {
            {{2, 2}, {1, 4}, {3, 2}, {3, 2}, {2, 1}}
        };
        int[] answers = {4};
        for (int i = 0; i < scoress.length; i++) {
            System.out.println(s.solution(scoress[i]) == answers[i]);
        }
    }

    public int solution(int[][] scores) {
        int[] me = scores[0];
        Arrays.sort(scores, (a, b) -> a[0] != b[0] ? b[0] - a[0] : a[1] - b[1]);
        int res = 1;
        int prev = scores[0][1];
        for (int[] score : scores) {
            if (prev > score[1]) {
                if (score == me) return -1;
                continue;
            }
            prev = score[1];
            if (score[0] + score[1] > me[0] + me[1]) res++;
        }
        return res;
    }
}
