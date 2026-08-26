package programmers.prefixsum.p92344;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][][] boards = {
            {{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5}},
            {{1,2,3},{4,5,6},{7,8,9}}
        };
        int[][][] skills = {
            {{1,0,0,3,4,4},{1,2,0,2,3,2},{2,1,0,3,1,2},{1,0,1,3,3,1}},
            {{1,1,1,2,2,4},{1,0,0,1,1,2},{2,2,0,2,0,100}}
        };
        int[] answers = {10, 6};
        for (int i = 0; i < boards.length; i++) {
            System.out.println(s.solution(boards[i], skills[i]) == answers[i]);
        }
    }

    public int solution(int[][] board, int[][] skill) {
        int n = board.length;
        int m = board[0].length;
        int[][] diff = new int[n + 2][m + 2];
        for (int[] sk : skill) {
            int val = sk[0] == 1 ? -sk[5] : sk[5];
            diff[sk[1] + 1][sk[2] + 1] += val;
            diff[sk[1] + 1][sk[4] + 2] -= val;
            diff[sk[3] + 2][sk[2] + 1] -= val;
            diff[sk[3] + 2][sk[4] + 2] += val;
        }

        int cnt = 0;
        for (int r = 1; r <= n; r++) {
            for (int c = 1; c <= m; c++) {
                diff[r][c] += diff[r - 1][c] + diff[r][c - 1] - diff[r - 1][c - 1];
                if (board[r - 1][c - 1] + diff[r][c] > 0) cnt++;
            }
        }
        return cnt;
    }
}
