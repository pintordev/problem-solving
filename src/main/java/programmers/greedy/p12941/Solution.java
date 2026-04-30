package programmers.greedy.p12941;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();

        int[][] As = {{1, 4, 2}, {1, 2}};
        int[][] Bs = {{5, 4, 4}, {3, 4}};
        int[] answers = {29, 10};

        for (int i = 0; i < As.length; i++) {
            System.out.println(s.solution(As[i], Bs[i]) == answers[i]);
        }
    }

    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);
        int sum = 0;
        for (int i = 0, len = A.length; i < len; i++) {
            sum += A[i] * B[len - i - 1];
        }
        return sum;
    }
}