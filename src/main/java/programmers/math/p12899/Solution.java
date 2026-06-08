package programmers.math.p12899;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] inputs = {1, 2, 3, 4};
        String[] answers = {"1", "2", "4", "11"};
        for (int i = 0; i < inputs.length; i++) {
            System.out.println(s.solution(inputs[i]).equals(answers[i]));
        }
    }

    public String solution(int n) {
        char[] map = {'1', '2', '4'};
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            sb.append(map[(n - 1) % 3]);
            n = (n - 1) / 3;
        }
        return sb.reverse().toString();
    }
}