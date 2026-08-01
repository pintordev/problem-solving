package programmers.greedy.p12987;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] as = {{5, 1, 3, 7}, {2, 2, 2, 2}};
        int[][] bs = {{2, 2, 6, 8}, {1, 1, 1, 1}};
        int[] answers = {3, 0};
        for (int i = 0; i < as.length; i++) {
            System.out.println(s.solution(as[i], bs[i]) == answers[i]);
        }
    }

    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);
        int score = 0;
        int i = 0;
        for (int b : B) {
            if (b > A[i]) {
                score++;
                i++;
            }
        }
        return score;
    }
}