package programmers.dp.p42898;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] ms = {4};
        int[] ns = {3};
        int[][][] puddless = {
                {{2, 2}}
        };
        int[] answers = {4};
        for (int i = 0; i < ms.length; i++) {
            System.out.println(s.solution(ms[i], ns[i], puddless[i]) == answers[i]);
        }
    }

    public int solution(int m, int n, int[][] puddles) {
        int[][] map = new int[m + 1][n + 1];
        for (int[] puddle : puddles) map[puddle[0]][puddle[1]] = -1;
        map[1][1] = 1;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (map[i][j] == -1) continue;
                map[i][j] += map[i - 1][j] != -1 ? map[i - 1][j] : 0;
                map[i][j] += map[i][j - 1] != -1 ? map[i][j - 1] : 0;
                map[i][j] %= 1000000007;
            }
        }
        return map[m][n];
    }
}