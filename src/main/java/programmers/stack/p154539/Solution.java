package programmers.stack.p154539;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] inputs = {
                {2, 3, 3, 5},
                {9, 1, 5, 3, 6, 2},
        };
        int[][] answers = {
                {3, 5, 5, -1},
                {-1, 5, 6, 6, -1, -1},
        };
        for (int i = 0; i < inputs.length; i++) {
            System.out.println(java.util.Arrays.equals(s.solution(inputs[i]), answers[i]));
        }
    }

    public int[] solution(int[] numbers) {
        int n = numbers.length;
        int[] res = new int[n];
        int[] stack = new int[n];
        int top = -1;
        for (int i = 0; i < n; i++) {
            while (top >= 0 && numbers[stack[top]] < numbers[i]) {
                res[stack[top--]] = numbers[i];
            }
            stack[++top] = i;
        }
        while (top >= 0) {
            res[stack[top--]] = -1;
        }
        return res;
    }
}