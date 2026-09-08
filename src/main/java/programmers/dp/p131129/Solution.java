package programmers.dp.p131129;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] targets = {21, 58};
        int[][] answers = {{1, 0}, {2, 2}};
        for (int i = 0; i < targets.length; i++) {
            System.out.println(Arrays.equals(s.solution(targets[i]), answers[i]));
        }
    }

    int[] minDarts;
    int[] maxHits;

    public int[] solution(int target) {
        minDarts = new int[target + 1];
        maxHits = new int[target + 1];
        for (int t = 1; t <= target; t++) {
            minDarts[t] = Integer.MAX_VALUE;
            relax(t, 50, 1);
            for (int n = 1; n <= 20; n++) {
                relax(t, n, 1);
                relax(t, 2 * n, 0);
                relax(t, 3 * n, 0);
            }
        }
        return new int[]{minDarts[target], maxHits[target]};
    }

    public void relax(int t, int score, int gain) {
        if (t < score) return;
        if (minDarts[t] > minDarts[t - score] + 1) {
            minDarts[t] = minDarts[t - score] + 1;
            maxHits[t] = maxHits[t - score] + gain;
        } else if (minDarts[t] == minDarts[t - score] + 1) {
            maxHits[t] = Math.max(maxHits[t], maxHits[t - score] + gain);
        }
    }
}
