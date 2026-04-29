package programmers.string.p12951;

public class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();

        String[] inputs = {"3people unFollowed me", "for the last week"};
        String[] answers = {"3people Unfollowed Me", "For The Last Week"};

        for (int i = 0; i < inputs.length; i++) {
            System.out.println(s.solution(inputs[i]).equals(answers[i]));
        }
    }

    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append(Character.toUpperCase(s.charAt(0)));
        for (int i = 1, len = s.length(); i < len; i++) {
            if (s.charAt(i - 1) == ' ') sb.append(Character.toUpperCase(s.charAt(i)));
            else sb.append(Character.toLowerCase(s.charAt(i)));
        }
        return sb.toString();
    }
}