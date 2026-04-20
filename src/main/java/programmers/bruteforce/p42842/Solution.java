package programmers.bruteforce.p42842;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();

        int[] browns = {10, 8, 24};
        int[] yellows = {2, 1, 24};
        int[][] answers = {{4, 3}, {3, 3}, {8, 6}};

        for (int i = 0; i < browns.length; i++) {
            System.out.println(Arrays.equals(s.solution(browns[i], yellows[i]), answers[i]));
        }
    }

    public int[] solution(int brown, int yellow) {
        // b + y == wh;
        // (w - 2)(h - 2) == y;
        // (b + 4) >> 1 == w + h;
        // sum & times -> 2nd order equation
        int t = brown + yellow;
        int s = (brown + 4) >> 1;
        int d = (int) Math.sqrt(s * s - 4 * t);
        return new int[]{(s + d) >> 1, (s - d) >> 1};
    }
}