package programmers.hash.p152996;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] inputs = {
            {100, 180, 360, 100, 270}
        };
        long[] answers = {4};
        for (int i = 0; i < inputs.length; i++) {
            System.out.println(s.solution(inputs[i]) == answers[i]);
        }
    }

    public long solution(int[] weights) {
        Arrays.sort(weights);
        int[] ws = new int[1001];
        long count = 0;
        for (int w : weights) {
            count += ws[w];
            if (w % 2 == 0) count += ws[w / 2];
            if (2 * w % 3 == 0) count += ws[2 * w / 3];
            if (3 * w % 4 == 0) count += ws[3 * w / 4];
            ws[w]++;
        }
        return count;
    }
}