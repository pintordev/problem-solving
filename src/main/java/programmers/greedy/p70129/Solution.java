package programmers.greedy.p70129;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();

        String[] ss = {"110010101001", "01110", "1111111"};
        int[][] answers = {{3, 8}, {3, 3}, {4, 1}};

        for (int i = 0; i < ss.length; i++) {
            System.out.println(Arrays.equals(s.solution(ss[i]), answers[i]));
        }
    }

    public int[] solution(String s) {
        int iter = 0, zc = 0, len;
        while ((len = s.length()) > 1) {
            int oc = 0;
            for (int i = 0; i < len; i++) {
                if (s.charAt(i) == '1') oc++;
            }
            zc += len - oc;
            iter++;
            s = Integer.toBinaryString(oc);
        }
        return new int[]{iter, zc};
    }
}