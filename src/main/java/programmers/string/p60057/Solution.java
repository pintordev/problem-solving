package programmers.string.p60057;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        String[] inputs = {"aabbaccc", "ababcdcdababcdcd", "abcabcdede", "abcabcabcabcdededededede", "xababcdcdababcdcd"};
        int[] answers = {7, 9, 8, 14, 17};
        for (int i = 0; i < inputs.length; i++) {
            System.out.println(s.solution(inputs[i]) == answers[i]);
        }
    }

    public int solution(String s) {
        int min = s.length();
        for (int k = 1; k <= (s.length() >> 1); k++) {
            int len = compress(s, k);
            min = Math.min(min, len);
        }
        return min;
    }

    public int compress(String s, int k) {
        int len = 0;
        String prev = s.substring(0, k);
        int cnt = 1;
        for (int i = k; i < s.length(); i += k) {
            String chunk = s.substring(i, Math.min(i + k, s.length()));
            if (chunk.equals(prev)) cnt++;
            else {
                len += (cnt >= 2 ? String.valueOf(cnt).length() : 0) + prev.length();
                prev = chunk;
                cnt = 1;
            }
        }
        len += (cnt >= 2 ? String.valueOf(cnt).length() : 0) + prev.length();
        return len;
    }
}