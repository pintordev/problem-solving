package programmers.math.p12949;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();

        int[][][] arr1s = {
                {{1, 4}, {3, 2}, {4, 1}},
                {{2, 3, 2}, {4, 2, 4}, {3, 1, 4}}
        };
        int[][][] arr2s = {
                {{3, 3}, {3, 3}},
                {{5, 4, 3}, {2, 4, 1}, {3, 1, 1}}
        };
        int[][][] answers = {
                {{15, 15}, {15, 15}, {15, 15}},
                {{22, 22, 11}, {36, 28, 18}, {29, 20, 14}}
        };

        for (int i = 0; i < arr1s.length; i++) {
            System.out.println(Arrays.deepEquals(s.solution(arr1s[i], arr2s[i]), answers[i]));
        }
    }

    public int[][] solution(int[][] arr1, int[][] arr2) {
        int[][] result = new int[arr1.length][arr2[0].length];
        int idx = 0, kdx = 0;
        while (true) {
            int val = arr1[idx][kdx];
            for (int j = 0; j < arr2[0].length; j++) {
                result[idx][j] += val * arr2[kdx][j];
            }
            kdx++;

            if (kdx == arr2.length) {
                kdx = 0;
                idx++;
            }

            if (idx == arr1.length) break;
        }
        return result;
    }
}