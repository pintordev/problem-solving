package programmers.greedy.p42883;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        String[] numbers = {"1924", "1231234", "4177252841"};
        int[] ks = {2, 3, 4};
        String[] answers = {"94", "3234", "775841"};
        for (int i = 0; i < numbers.length; i++) {
            System.out.println(s.solution(numbers[i], ks[i]).equals(answers[i]));
        }
    }

    public String solution(String number, int k) {
        char[] stack = new char[number.length()];
        int top = 0;
        for (char c : number.toCharArray()) {
            while (k > 0 && top > 0 && stack[top - 1] < c) {
                top--;
                k--;
            }
            stack[top++] = c;
        }
        return new String(stack, 0, top - k);
    }
}