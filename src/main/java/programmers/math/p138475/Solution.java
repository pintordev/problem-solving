package programmers.math.p138475;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] es = {8};
        int[][] startss = {{1, 3, 7}};
        int[][] answers = {{6, 6, 8}};
        for (int i = 0; i < es.length; i++) {
            System.out.println(Arrays.equals(s.solution(es[i], startss[i]), answers[i]));
        }
    }

    public int[] solution(int e, int[] starts) {
        int[] cnt = new int[e + 1];
        for (int d = 1; d <= e; d++) {
            for (int multiple = d; multiple <= e; multiple += d) {
                cnt[multiple]++;
            }
        }
        int[] best = new int[e + 1];
        int max = 0;
        for (int k = e; k >= 1; k--) {
            if (cnt[k] < max) best[k] = best[k + 1];
            else {
                max = cnt[k];
                best[k] = k;
            }
        }
        for (int i = 0; i < starts.length; i++) {
            starts[i] = best[starts[i]];
        }
        return starts;
    }
}
