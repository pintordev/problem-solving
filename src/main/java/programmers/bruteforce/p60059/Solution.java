package programmers.bruteforce.p60059;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        // 파라미터마다 별도 배열 (파라미터명 그대로 사용, inputs 같은 통합 이름 금지)
        // 테스트 케이스가 1개여도 배열 + 루프 사용
        int[][][] keys = {
                {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}}
        };
        int[][][] locks = {
                {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}}
        };
        boolean[] answers = {true};
        for (int i = 0; i < keys.length; i++) {
            System.out.println(s.solution(keys[i], locks[i]) == answers[i]);
        }
    }

    int[][] lock;
    int[][] key;
    int n;
    int m;
    int offset;
    int hole;

    public boolean solution(int[][] key, int[][] lock) {
        this.lock = lock;
        this.key = key;
        n = lock.length;
        m = key.length;
        offset = m - 1;
        hole = 0;
        for (int[] row : lock) {
            for (int val : row) {
                if (val == 0) hole++;
            }
        }
        int size = n + 2 * offset;
        for (int rot = 0; rot < 4; rot++) {
            for (int r = 0; r <= size - m; r++) {
                for (int c = 0; c <= size - m; c++) {
                    if (canUnlock(r, c)) return true;
                }
            }
            rotate();
        }
        return false;
    }

    public boolean canUnlock(int r, int c) {
        int filled = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                int lr = r + i - offset;
                int lc = c + j - offset;
                if (lr < 0 || lr >= n || lc < 0 || lc >= n) continue;
                if (lock[lr][lc] == 1 && key[i][j] == 1) return false;
                if (lock[lr][lc] == 0 && key[i][j] == 1) filled++;
            }
        }
        return filled == hole;
    }

    public void rotate() {
        int[][] rotated = new int[m][m];
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < m; c++) {
                rotated[c][m - 1 - r] = key[r][c];
            }
        }
        key = rotated;
    }
}
