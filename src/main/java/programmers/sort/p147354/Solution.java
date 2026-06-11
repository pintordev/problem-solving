package programmers.sort.p147354;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();

        int[][][] inputs = {
            {{2, 2, 6}, {1, 5, 10}, {4, 2, 9}, {3, 8, 3}}
        };
        int[] cols = {2};
        int[] rowBegins = {2};
        int[] rowEnds = {3};
        int[] answers = {4};

        for (int i = 0; i < inputs.length; i++) {
            System.out.println(s.solution(inputs[i], cols[i], rowBegins[i], rowEnds[i]) == answers[i]);
        }
    }

    public int solution(int[][] data, int col, int row_begin, int row_end) {
        Arrays.sort(data, (o1, o2) -> {
            if (o1[col - 1] == o2[col - 1]) return o2[0] - o1[0];
            return o1[col - 1] - o2[col - 1];
        });
        int res = 0;
        for (int i = row_begin; i <= row_end; i++) {
            int si = 0;
            for (int d : data[i - 1]) si += d % i;
            res ^= si;
        }
        return res;
    }
}