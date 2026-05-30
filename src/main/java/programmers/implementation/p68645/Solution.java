package programmers.implementation.p68645;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] inputs = {{4}, {5}, {6}};
        int[][] answers = {
            {1, 2, 9, 3, 10, 8, 4, 5, 6, 7},
            {1, 2, 12, 3, 13, 11, 4, 14, 15, 10, 5, 6, 7, 8, 9},
            {1, 2, 15, 3, 16, 14, 4, 17, 21, 13, 5, 18, 19, 20, 12, 6, 7, 8, 9, 10, 11}
        };
        for (int i = 0; i < inputs.length; i++) {
            System.out.println(Arrays.equals(s.solution(inputs[i][0]), answers[i]));
        }
    }

    public int[] solution(int n) {
        int[] flat = new int[(n * (n + 1)) >> 1];
        int[][] mat = new int[n][n];
        int r = -1, c = 0, num = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (i % 3 == 0) ++r;
                else if (i % 3 == 1) ++c;
                else {--r; --c;}
                mat[r][c] = ++num;
            }
        }
        int idx = 0;
        for (int[] m : mat) {
            for (int a : m) {
                if (a == 0) break;
                flat[idx++] = a;
            }
        }
        return flat;
    }
}