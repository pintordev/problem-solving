package programmers.stack.p76502;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();

        String[] inputs = {"[](){}", "}]()[{", "[)(]", "}}}"};
        int[] answers = {3, 2, 0, 0};

        for (int i = 0; i < inputs.length; i++) {
            System.out.println(s.solution(inputs[i]) == answers[i]);
        }
    }

    public int solution(String s) {
        int cnt = 0;
        for (int i = 0, len = s.length(); i < len; i++) {
            if (isValid(s, i)) cnt++;
        }
        return cnt;
    }

    public boolean isValid(String s, int bias) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0, len = s.length(); i < len; i++) {
            char c = s.charAt((i + bias) % len);
            if (c == '(') stack.push(')');
            else if (c == '[') stack.push(']');
            else if (c == '{') stack.push('}');
            else if (stack.isEmpty() || stack.pop() != c) return false;
        }
        return stack.isEmpty();
    }
}