package programmers.dp.p12904;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        String[] ss = {"abcdcba", "abacde"};
        int[] answers = {7, 3};
        for (int i = 0; i < ss.length; i++) {
            System.out.println(s.solution(ss[i]) == answers[i]);
        }
    }

    public int solution(String s) {
        int res = 1;
        for (int c = 0; c < s.length(); c++) {
            res = Math.max(res, expand(s, c, c));
            res = Math.max(res, expand(s, c, c + 1));
        }
        return res;
    }

    public int expand(String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        return r - l - 1;
    }
}