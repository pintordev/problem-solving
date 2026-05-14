package programmers.string.p84512;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        String[] inputs = {"AAAAE", "AAAE", "I", "EIO"};
        int[] answers = {6, 10, 1563, 1189};
        for (int i = 0; i < inputs.length; i++) {
            System.out.println(s.solution(inputs[i]) == answers[i]);
        }
    }

    public int solution(String word) {
        int[] f = {781, 156, 31, 6, 1};
        String vowels = "AEIOU";
        int order = 0;
        for (int i = 0; i < word.length(); i++) {
            order += vowels.indexOf(word.charAt(i)) * f[i] + 1;
        }
        return order;
    }
}