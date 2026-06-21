package programmers.greedy.p172927;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] picks = {{1, 3, 2}, {0, 1, 1}};
        String[][] minerals = {
            {"diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone"},
            {"diamond", "diamond", "diamond", "diamond", "diamond", "iron", "iron", "iron", "iron", "iron", "diamond"}
        };
        int[] answers = {12, 50};
        for (int i = 0; i < picks.length; i++) {
            System.out.println(s.solution(picks[i], minerals[i]) == answers[i]);
        }
    }

    public int solution(int[] picks, String[] minerals) {
        int[][] fatigue = {{1, 1, 1}, {5, 1, 1}, {25, 5, 1}};
        int len = Math.min(picks[0] + picks[1] + picks[2], (minerals.length + 4) / 5);

        int[][] groups = new int[len][3];
        for (int g = 0; g < len; g++) {
            for (int k = g * 5; k < Math.min((g + 1) * 5, minerals.length); k++) {
                int m = minerals[k].equals("diamond") ?
                        0 : minerals[k].equals("iron") ?
                            1 : 2;
                for (int p = 0; p < 3; p++) groups[g][p] += fatigue[p][m];
            }
        }

        Arrays.sort(groups, (o1, o2) -> o2[2] - o1[2]);

        int res = 0;
        for (int g = 0; g < len; g++) {
            if (picks[0]-- > 0) res += groups[g][0];
            else if (picks[1]-- > 0) res += groups[g][1];
            else if (picks[2]-- > 0) res += groups[g][2];
        }
        return res;
    }
}