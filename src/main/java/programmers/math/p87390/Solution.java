package programmers.math.p87390;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();

        long[] nList = {3, 4};
        long[] leftList = {2, 7};
        long[] rightList = {5, 14};
        int[][] answers = {{3, 2, 2, 3}, {4, 3, 3, 3, 4, 4, 4, 4}};

        for (int i = 0; i < nList.length; i++) {
            System.out.println(Arrays.equals(s.solution(nList[i], leftList[i], rightList[i]), answers[i]));
        }
    }

    public int[] solution(long n, long left, long right) {
        int[] slice = new int[(int) (right - left + 1)];
        for (long i = left; i <= right; i++) {
            slice[(int) (i - left)] = (int) Math.max(i / n + 1, i % n + 1);
        }
        return slice;
    }
}