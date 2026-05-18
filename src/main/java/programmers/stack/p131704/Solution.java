package programmers.stack.p131704;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] inputs = {
            {4, 3, 1, 2, 5},
            {5, 4, 3, 2, 1}
        };
        int[] answers = {2, 5};
        for (int i = 0; i < inputs.length; i++) {
            System.out.println(s.solution(inputs[i]) == answers[i]);
        }
    }

    public int solution(int[] order) {
        int n = order.length;
        int[] stack = new int[n];
        int cnt = 0, top = -1, next = 1;
        for (int i = 0; i < n; i++) {
            while (top < 0 || stack[top] != order[i]) {
                if (next > n) return cnt;
                stack[++top] = next++;
            }
            top--;
            cnt++;
        }
        return cnt;
    }
}