package programmers.implementation.p77485;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();

        int[] rows = {6, 3, 100};
        int[] cols = {6, 3, 97};
        int[][][] queries = {
                {{2, 2, 5, 4}, {3, 3, 6, 6}, {5, 1, 6, 3}},
                {{1, 1, 2, 2}, {1, 2, 2, 3}, {2, 1, 3, 2}, {2, 2, 3, 3}},
                {{1, 1, 100, 97}}
        };
        int[][] answers = {{8, 10, 25}, {1, 1, 5, 3}, {1}};

        for (int i = 0; i < rows.length; i++) {
            System.out.println(Arrays.equals(s.solution(rows[i], cols[i], queries[i]), answers[i]));
        }
    }

    int[][] map;

    public int[] solution(int rows, int columns, int[][] queries) {
        map = new int[rows + 1][columns + 1];
        int n = 1;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                map[i][j] = n++;
            }
        }
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            res[i] = rotate(queries[i][0], queries[i][1], queries[i][2], queries[i][3]);
        }
        return res;
    }

    public int rotate(int x1, int y1, int x2, int y2) {
        int min = Integer.MAX_VALUE;
        int prev = map[x1][y1];
        for (int i = y1 + 1; i <= y2; i++) {
            int tmp = map[x1][i];
            min = Math.min(min, map[x1][i] = prev);
            prev = tmp;
        }
        for (int i = x1 + 1; i <= x2; i++) {
            int tmp = map[i][y2];
            min = Math.min(min, map[i][y2] = prev);
            prev = tmp;
        }
        for (int i = y2 - 1; i >= y1; i--) {
            int tmp = map[x2][i];
            min = Math.min(min, map[x2][i] = prev);
            prev = tmp;
        }
        for (int i = x2 - 1; i >= x1; i--) {
            int tmp = map[i][y1];
            min = Math.min(min, map[i][y1] = prev);
            prev = tmp;
        }
        return min;
    }
}