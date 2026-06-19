package programmers.math.p134239;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();

        int[] kList = {5, 3};
        int[][][] rangesList = {
            {{0, 0}, {0, -1}, {2, -3}, {3, -3}},
            {{0, 0}, {1, -2}, {3, -3}}
        };
        double[][] answers = {
            {33.0, 31.5, 0.0, -1.0},
            {47.0, 36.0, 12.0}
        };

        for (int i = 0; i < kList.length; i++) {
            System.out.println(Arrays.equals(s.solution(kList[i], rangesList[i]), answers[i]));
        }
    }

    public double[] solution(int k, int[][] ranges) {
        List<Long> cumArea = new ArrayList<>();
        cumArea.add(0L);
        long a = k, b;
        while (a > 1) {
            if ((a & 1) == 0) b = a >> 1;
            else b = 3 * a + 1;
            cumArea.add(cumArea.get(cumArea.size() - 1) + a + b);
            a = b;
        }
        double[] res = new double[ranges.length];
        for (int i = 0, n = cumArea.size() - 1; i < res.length; i++) {
            if (n + ranges[i][1] < ranges[i][0]) res[i] = -1.0;
            else res[i] = (cumArea.get(n + ranges[i][1]) - cumArea.get(ranges[i][0])) / 2.0;
        }
        return res;
    }
}