package programmers.divideandconquer.p150367;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        long[][] numberss = {
                {7, 42, 5},
                {63, 111, 95},
        };
        int[][] answers = {
                {1, 1, 0},
                {1, 1, 0},
        };
        for (int i = 0; i < numberss.length; i++) {
            System.out.println(Arrays.equals(s.solution(numberss[i]), answers[i]));
        }
    }

    public int[] solution(long[] numbers) {
        int[] res = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            res[i] = isRepresentable(numbers[i]) ? 1 : 0;
        }
        return res;
    }

    public boolean isRepresentable(long number) {
        String binary = Long.toBinaryString(number);
        int size = 1;
        while (size < binary.length()) {
            size = (size << 1) + 1;
        }
        char[] tree = new char[size];
        Arrays.fill(tree, '0');
        for (int i = 0; i < binary.length(); i++) {
            tree[size - binary.length() + i] = binary.charAt(i);
        }
        return isValid(tree, 0, size - 1);
    }

    public boolean isValid(char[] tree, int lo, int hi) {
        if (lo > hi) return true;
        int mid = (lo + hi) >> 1;
        if (tree[mid] == '1') return isValid(tree, lo, mid - 1) && isValid(tree, mid + 1, hi);
        for (int i = lo; i <= hi; i++) {
            if (tree[i] == '1') return false;
        }
        return true;
    }
}
