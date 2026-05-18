package programmers.dp.p12913;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][][] inputs = {
                {{1, 2, 3, 5}, {5, 6, 7, 8}, {4, 3, 2, 1}}
        };
        int[] answers = {16};
        for (int i = 0; i < inputs.length; i++) {
            System.out.println(s.solution(inputs[i]) == answers[i]);
        }
    }

    public int solution(int[][] land) {
        int n = land.length;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 4; j++) {
                int max = 0;
                for (int k = 0; k < 4; k++) {
                    if (k != j) max = Math.max(max, land[i - 1][k]);
                }
                land[i][j] += max;
            }
        }
        return Math.max(Math.max(land[n - 1][0], land[n - 1][1]), Math.max(land[n - 1][2], land[n - 1][3]));
    }
}