package programmers.stack.p12909;

public class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();

        String[] inputs = {"()()", "(())()", ")()(", "(()("};
        boolean[] answers = {true, true, false, false};

        for (int i = 0; i < inputs.length; i++) {
            System.out.println(s.solution(inputs[i]) == answers[i]);
        }
    }

    public boolean solution(String s) {
        int flag = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') flag++;
            else flag--;
            if (flag < 0) return false;
        }
        return flag == 0;
    }
}