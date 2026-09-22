package programmers.graph.p118668;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] alps = {10, 0};
        int[] cops = {10, 0};
        int[][][] problemss = {
                {{10, 15, 2, 1, 2}, {20, 20, 3, 3, 4}},
                {{0, 0, 2, 1, 2}, {4, 5, 3, 1, 2}, {4, 11, 4, 0, 2}, {10, 4, 0, 4, 2}}
        };
        int[] answers = {15, 13};
        for (int i = 0; i < alps.length; i++) {
            System.out.println(s.solution(alps[i], cops[i], problemss[i]) == answers[i]);
        }
    }

    int[][] problems;
    int maxAlp;
    int maxCop;
    int[][] dist;

    public int solution(int alp, int cop, int[][] problems) {
        this.problems = problems;
        maxAlp = alp;
        maxCop = cop;
        for (int[] p : problems) {
            maxAlp = Math.max(maxAlp, p[0]);
            maxCop = Math.max(maxCop, p[1]);
        }
        return gridDp(alp, cop);
    }

    public int gridDp(int alp, int cop) {
        dist = new int[maxAlp + 1][maxCop + 1];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dist[alp][cop] = 0;
        for (int i = alp; i <= maxAlp; i++) {
            for (int j = cop; j <= maxCop; j++) {
                if (i + 1 <= maxAlp && dist[i][j] + 1 < dist[i + 1][j]) dist[i + 1][j] = dist[i][j] + 1;
                if (j + 1 <= maxCop && dist[i][j] + 1 < dist[i][j + 1]) dist[i][j + 1] = dist[i][j] + 1;
                for (int[] p : problems) {
                    if (i < p[0] || j < p[1]) continue;
                    int na = Math.min(maxAlp, i + p[2]);
                    int nc = Math.min(maxCop, j + p[3]);
                    if (dist[i][j] + p[4] < dist[na][nc]) dist[na][nc] = dist[i][j] + p[4];
                }
            }
        }
        return dist[maxAlp][maxCop];
    }
}
