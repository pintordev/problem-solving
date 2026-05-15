package programmers.string.p17677;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        String[] str1s = {"FRANCE", "handshake", "aa1+aa2"};
        String[] str2s = {"french", "shake hands", "AAAA12"};
        int[] answers = {16384, 65536, 43690};
        for (int i = 0; i < str1s.length; i++) {
            System.out.println(s.solution(str1s[i], str2s[i]) == answers[i]);
        }
    }

    public int solution(String str1, String str2) {
        int[] freq1 = bigrams(str1);
        int[] freq2 = bigrams(str2);
        int intersect = 0, union = 0;
        for (int i = 0; i < freq1.length; i++) {
            intersect += Math.min(freq1[i], freq2[i]);
            union += Math.max(freq1[i], freq2[i]);
        }
        if (union == 0) return 65536;
        return 65536 * intersect / union;
    }

    public int[] bigrams(String s) {
        s = s.toLowerCase();
        int[] freq = new int[676];
        for (int i = 1; i < s.length(); i++) {
            char c1 = s.charAt(i - 1);
            char c2 = s.charAt(i);
            if (!Character.isAlphabetic(c1) || !Character.isAlphabetic(c2)) continue;
            freq[(c1 - 'a') * 26 + (c2 - 'a')]++;
        }
        return freq;
    }
}