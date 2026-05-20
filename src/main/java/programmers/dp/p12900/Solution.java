package programmers.dp.p12900;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] inputs = {4};
        int[] answers = {5};
        for (int i = 0; i < inputs.length; i++) {
            System.out.println(s.solution(inputs[i]) == answers[i]);
        }
    }

    public int solution(int n) {
        int a = 1, b = 2;
        for (int i = 1; i < n; i++) {
            int c = (a + b) % 1_000_000_007;
            a = b;
            b = c;
        }
        return a;
    }
}