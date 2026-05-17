package programmers.stack.p42584;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] inputs = {
            {1, 2, 3, 2, 3}
        };
        int[][] answers = {
            {4, 3, 1, 1, 0}
        };
        for (int i = 0; i < inputs.length; i++) {
            System.out.println(Arrays.equals(s.solution(inputs[i]), answers[i]));
        }
    }

    public int[] solution(int[] prices) {
        int n = prices.length;
        int[] res = new int[n];
        int[] stack = new int[n];
        int top = -1;
        for (int i = 0; i < n; i++) {
            while (top >= 0 && prices[stack[top]] > prices[i]) {
                res[stack[top]] = i - stack[top--];
            }
            stack[++top] = i;
        }
        while (top >= 0) {
            res[stack[top]] = n - stack[top--] - 1;
        }
        return res;
    }
}