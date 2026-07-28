package programmers.dp.p43105;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][][] triangles = {
            {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}}
        };
        int[] answers = {30};
        for (int i = 0; i < triangles.length; i++) {
            System.out.println(s.solution(triangles[i]) == answers[i]);
        }
    }

    public int solution(int[][] triangle) {
        for (int i = triangle.length - 2; i >= 0; i--) {
            for (int j = 0; j < triangle[i].length; j++) {
                triangle[i][j] += Math.max(triangle[i + 1][j], triangle[i + 1][j + 1]);
            }
        }
        return triangle[0][0];
    }
}