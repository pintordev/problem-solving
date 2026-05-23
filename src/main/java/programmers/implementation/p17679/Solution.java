package programmers.implementation.p17679;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] answers = {14, 15};
        String[][] boards = {
            {"CCBDE", "AAADE", "AAABF", "CCBBF"},
            {"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"}
        };
        int[] ms = {4, 6};
        int[] ns = {5, 6};
        for (int i = 0; i < answers.length; i++) {
            System.out.println(s.solution(ms[i], ns[i], boards[i]) == answers[i]);
        }
    }

    public int solution(int m, int n, String[] board) {
        char[][] grid = new char[m][n];
        for (int i = 0; i < m; i++) grid[i] = board[i].toCharArray();
        int total = 0;
        while (true) {
            boolean[][] toRemove = new boolean[m][n];
            for (int i = 0; i < m - 1; i++) {
                for (int j = 0; j < n - 1; j++) {
                    char c = grid[i][j];
                    if (c != ' ' && c == grid[i][j + 1] && c == grid[i + 1][j] && c == grid[i + 1][j + 1]) {
                        toRemove[i][j] = toRemove[i][j + 1] = toRemove[i + 1][j] = toRemove[i + 1][j + 1] = true;
                    }
                }
            }
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                int bottom = m - 1;
                for (int i = m - 1; i >= 0; i--) {
                    if (toRemove[i][j]) cnt++;
                    else grid[bottom--][j] = grid[i][j];
                }
                while (bottom >= 0) grid[bottom--][j] = ' ';
            }
            if (cnt == 0) break;
            total += cnt;
        }
        return total;
    }
}