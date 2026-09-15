package programmers.bruteforce.p131703;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();

        int[][][] beginnings = {
            {{0, 1, 0, 0, 0}, {1, 0, 1, 0, 1}, {0, 1, 1, 1, 0}, {1, 0, 1, 1, 0}, {0, 1, 0, 1, 0}},
            {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}
        };
        int[][][] targets = {
            {{0, 0, 0, 1, 1}, {0, 0, 0, 0, 1}, {0, 0, 1, 0, 1}, {0, 0, 0, 1, 0}, {0, 0, 0, 0, 1}},
            {{1, 0, 1}, {0, 0, 0}, {0, 0, 0}}
        };
        int[] answers = {5, -1};

        for (int i = 0; i < beginnings.length; i++) {
            System.out.println(s.solution(beginnings[i], targets[i]) == answers[i]);
        }
    }

    int n;
    int m;
    int[][] diff;

    public int solution(int[][] beginning, int[][] target) {
        n = beginning.length;
        m = beginning[0].length;
        diff = new int[n][m];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                diff[r][c] = beginning[r][c] ^ target[r][c];
            }
        }
        int res = -1;
        for (int mask = 0; mask < (1 << n); mask++) {
            int[] cols = columns(mask);
            if (cols == null) continue;
            int flips = Integer.bitCount(mask);
            for (int col : cols) {
                flips += col;
            }
            if (res == -1 || flips < res) res = flips;
        }
        return res;
    }

    public int[] columns(int mask) {
        int[] cols = new int[m];
        for (int c = 0; c < m; c++) {
            cols[c] = diff[0][c] ^ (mask & 1);
        }
        for (int r = 0; r < n; r++) {
            int rowFlip = (mask >> r) & 1;
            for (int c = 0; c < m; c++) {
                if ((rowFlip ^ cols[c]) != diff[r][c]) return null;
            }
        }
        return cols;
    }
}
