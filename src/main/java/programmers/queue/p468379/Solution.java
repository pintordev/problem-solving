package programmers.queue.p468379;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] ms = {4, 3, 4, 4, 2, 4};
        int[] ns = {5, 3, 6, 6, 2, 4};
        int[] hs = {2, 1, 3, 1, 2, 3};
        int[] ws = {2, 1, 4, 2, 2, 1};
        int[][][] dropss = {
                {{0, 0}, {3, 1}, {1, 3}, {2, 4}, {1, 1}, {2, 2}, {2, 3}, {0, 4}},
                {{0, 0}, {0, 1}, {0, 2}, {1, 0}},
                {{1, 2}},
                {{0, 1}, {0, 3}, {0, 5}, {1, 1}, {1, 3}, {1, 5}, {2, 1}, {2, 3}, {2, 5}, {3, 1}, {3, 3}, {3, 5}},
                {{0, 0}, {0, 1}, {1, 1}, {1, 0}},
                {{2, 0}, {1, 3}, {3, 2}, {0, 1}}
        };
        int[][] answers = {
                {2, 2},
                {1, 1},
                {0, 0},
                {3, 4},
                {0, 0},
                {0, 2}
        };
        for (int i = 0; i < ms.length; i++) {
            System.out.println(Arrays.equals(s.solution(ms[i], ns[i], hs[i], ws[i], dropss[i]), answers[i]));
        }
    }

    public int[] solution(int m, int n, int h, int w, int[][] drops) {
        int INF = drops.length;
        int[][] hitTime = new int[m][n];
        for (int[] row : hitTime) Arrays.fill(row, INF);
        for (int t = 0; t < drops.length; t++) {
            hitTime[drops[t][0]][drops[t][1]] = t;
        }

        int[][] rowMin = new int[m][n - w + 1];
        int[] colDeque = new int[n];
        for (int r = 0; r < m; r++) {
            int head = 0, tail = 0;
            for (int c = 0; c < n; c++) {
                while (tail > head && hitTime[r][colDeque[tail - 1]] >= hitTime[r][c]) tail--;
                colDeque[tail++] = c;
                if (colDeque[head] <= c - w) head++;
                if (c >= w - 1) rowMin[r][c - w + 1] = hitTime[r][colDeque[head]];
            }
        }

        int windowRows = m - h + 1;
        int windowCols = n - w + 1;
        int[][] windowMin = new int[windowRows][windowCols];
        int[] rowDeque = new int[m];
        for (int c = 0; c < windowCols; c++) {
            int head = 0, tail = 0;
            for (int r = 0; r < m; r++) {
                while (tail > head && rowMin[rowDeque[tail - 1]][c] >= rowMin[r][c]) tail--;
                rowDeque[tail++] = r;
                if (rowDeque[head] <= r - h) head++;
                if (r >= h - 1) windowMin[r - h + 1][c] = rowMin[rowDeque[head]][c];
            }
        }

        int max = -1, bestR = 0, bestC = 0;
        for (int r = 0; r < windowRows; r++) {
            for (int c = 0; c < windowCols; c++) {
                if (windowMin[r][c] <= max) continue;
                max = windowMin[r][c];
                bestR = r;
                bestC = c;
            }
        }
        return new int[]{bestR, bestC};
    }
}