package programmers.graph.p258711;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][][] edgess = {
            {{2, 3}, {4, 3}, {1, 1}, {2, 1}},
            {{4, 11}, {1, 12}, {8, 3}, {12, 7}, {4, 2}, {7, 11}, {4, 8}, {9, 6}, {10, 11}, {6, 10}, {3, 5}, {11, 1}, {5, 3}, {11, 9}, {3, 8}}
        };
        int[][] answers = {
            {2, 1, 1, 0},
            {4, 0, 1, 2}
        };
        for (int i = 0; i < edgess.length; i++) {
            System.out.println(Arrays.equals(s.solution(edgess[i]), answers[i]));
        }
    }

    public int[] solution(int[][] edges) {
        int[] in = new int[1000001];
        int[] out = new int[1000001];
        for (int[] edge : edges) {
            out[edge[0]]++;
            in[edge[1]]++;
        }
        int[] res = new int[4];
        for (int i = 0; i < in.length; i++) {
            if (in[i] == 0 && out[i] == 0) continue;
            if (in[i] == 0 && out[i] >= 2) res[0] = i;
            else if (out[i] == 0) res[2]++;
            else if (in[i] >= 2 && out[i] == 2) res[3]++;
        }
        res[1] = out[res[0]] - res[2] - res[3];
        return res;
    }
}