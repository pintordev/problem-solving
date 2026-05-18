package programmers.implementation.p17687;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] inputs = {{2, 4, 2, 1}, {16, 16, 2, 1}, {16, 16, 2, 2}};
        String[] answers = {"0111", "02468ACE11111111", "13579BDF01234567"};
        for (int i = 0; i < inputs.length; i++) {
            System.out.println(s.solution(inputs[i][0], inputs[i][1], inputs[i][2], inputs[i][3]).equals(answers[i]));
        }
    }

    public String solution(int n, int t, int m, int p) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (sb.length() < m * t) {
            sb.append(Integer.toString(i++, n).toUpperCase());
        }
        char[] res = new char[t];
        for (int j = 0; j < t; j++) {
            res[j] = sb.charAt(j * m + p - 1);
        }
        return new String(res);
    }
}