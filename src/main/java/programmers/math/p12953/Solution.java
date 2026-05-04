package programmers.math.p12953;

public class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();

        int[][] arrs = {{2, 6, 8, 14}, {1, 2, 3}};
        int[] answers = {168, 6};

        for (int i = 0; i < arrs.length; i++) {
            System.out.println(s.solution(arrs[i]) == answers[i]);
        }
    }

    public int solution(int[] arr) {
        int lcm = 1;
        for (int num : arr) {
            lcm = lcm(lcm, num);
        }
        return lcm;
    }

    public int lcm(int a, int b) {
        return a / gcd(a, b) * b;
    }

    public int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}