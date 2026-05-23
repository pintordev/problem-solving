package programmers.sort.p42746;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] inputs = {
            {6, 10, 2},
            {3, 30, 34, 5, 9}
        };
        String[] answers = {"6210", "9534330"};
        for (int i = 0; i < inputs.length; i++) {
            System.out.println(s.solution(inputs[i]).equals(answers[i]));
        }
    }

    public String solution(int[] numbers) {
        String[] ns = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) ns[i] = String.valueOf(numbers[i]);
        Arrays.sort(ns, (a, b) -> (b + a).compareTo(a + b));
        if (ns[0].equals("0")) return "0";
        return String.join("", ns);
    }
}