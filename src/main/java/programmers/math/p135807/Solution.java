package programmers.math.p135807;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][][] inputs = {
            {{10, 17}, {5, 20}},
            {{10, 20}, {5, 17}},
            {{14, 35, 119}, {18, 30, 102}}
        };
        int[] answers = {0, 10, 7};
        for (int i = 0; i < inputs.length; i++) {
            System.out.println(s.solution(inputs[i][0], inputs[i][1]) == answers[i]);
        }
    }

    public int solution(int[] arrayA, int[] arrayB) {
        return Math.max(findOrZero(arrayA, arrayB), findOrZero(arrayB, arrayA));
    }

    public int findOrZero(int[] arrayA, int[] arrayB) {
        int gcd = arrayA[0];
        for (int a : arrayA) gcd = gcd(gcd, a);
        for (int b : arrayB) if (b % gcd == 0) return 0;
        return gcd;
    }

    public int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}