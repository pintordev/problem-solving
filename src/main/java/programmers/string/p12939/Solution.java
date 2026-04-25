package programmers.string.p12939;

public class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();

        String[] inputs = {"1 2 3 4", "-1 -2 -3 -4", "-1 -1"};
        String[] answers = {"1 4", "-4 -1", "-1 -1"};

        for (int i = 0; i < inputs.length; i++) {
            System.out.println(s.solution(inputs[i]).equals(answers[i]));
        }
    }

    public String solution(String s) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int i = -1;
        int len = s.length();
        while (++i < len) {
            int sign = 1;
            if (s.charAt(i) == '-') {
                sign = -1;
                i++;
            }
            int num = 0;
            while (i < len && s.charAt(i) != ' ') {
                num = (num << 3) + (num << 1) + (s.charAt(i) - '0');
                i++;
            }
            num *= sign;
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        return new StringBuilder()
                .append(min)
                .append(' ')
                .append(max)
                .toString();
    }
}