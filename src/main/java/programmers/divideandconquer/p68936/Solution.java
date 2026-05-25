package programmers.divideandconquer.p68936;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();

        int[][][] inputs = {
                {{1, 1, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 1}, {1, 1, 1, 1}},
                {{1, 1, 1, 1, 1, 1, 1, 1}, {0, 1, 1, 1, 1, 1, 1, 1}, {0, 0, 0, 0, 1, 1, 1, 1}, {0, 1, 0, 0, 1, 1, 1, 1}, {0, 0, 0, 0, 0, 0, 1, 1}, {0, 0, 0, 0, 0, 0, 0, 1}, {0, 0, 0, 0, 1, 0, 0, 1}, {0, 0, 0, 0, 1, 1, 1, 1}}
        };
        int[][] answers = {
                {4, 9},
                {10, 15}
        };

        for (int i = 0; i < inputs.length; i++) {
            int[] result = s.solution(inputs[i]);
            System.out.println(result[0] == answers[i][0] && result[1] == answers[i][1]);
        }
    }

    int[][] arr;
    int[] counts;

    public int[] solution(int[][] arr) {
        this.arr = arr;
        counts = new int[2];
        compress(0, 0, arr.length);
        return counts;
    }

    public void compress(int row, int col, int size) {
        int val = arr[row][col];
        for (int r = row; r < row + size; r++) {
            for (int c = col; c < col + size; c++) {
                if (arr[r][c] == val) continue;
                int half = size >> 1;
                compress(row, col, half);
                compress(row, col + half, half);
                compress(row + half, col, half);
                compress(row + half, col + half, half);
                return;
            }
        }
        counts[val]++;
    }
}