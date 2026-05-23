package programmers.math.p77885;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        long[][] inputs = {
            {2, 7}
        };
        long[][] answers = {
            {3, 11}
        };
        for (int i = 0; i < inputs.length; i++) {
            System.out.println(Arrays.equals(s.solution(inputs[i]), answers[i]));
        }
    }

    public long[] solution(long[] numbers) {
        long[] result = new long[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            long x = numbers[i];
            result[i] = x + ((Long.lowestOneBit(~x) + 1) >> 1);
        }
        return result;
    }
}