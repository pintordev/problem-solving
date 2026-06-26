package programmers.math.p62048;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        long[] answers = {80};
        int[][] inputs = {{8, 12}};
        for (int i = 0; i < inputs.length; i++) {
            System.out.println(s.solution(inputs[i][0], inputs[i][1]) == answers[i]);
        }
    }

    public long solution(int w, int h) {
        return (long) w * h - (w + h - gcd(w, h));
    }

    public long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}