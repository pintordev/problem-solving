package programmers.dp.p12905;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][][] inputs = {
            {{0,1,1,1},{1,1,1,1},{1,1,1,1},{0,0,1,0}},
            {{0,0,1,1},{1,1,1,1}}
        };
        int[] answers = {9, 4};
        for (int i = 0; i < inputs.length; i++) {
            System.out.println(s.solution(inputs[i]) == answers[i]);
        }
    }

    public int solution(int[][] board) {
        int max = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (i > 0 && j > 0 && board[i][j] == 1) {
                    board[i][j] = Math.min(board[i-1][j], Math.min(board[i][j-1], board[i-1][j-1])) + 1;
                }
                max = Math.max(max, board[i][j]);
            }
        }
        return max * max;
    }
}