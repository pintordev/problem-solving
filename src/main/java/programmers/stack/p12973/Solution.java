package programmers.stack.p12973;

public class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();

        String[] ss = {"baabaa", "cdcd"};
        int[] answers = {1, 0};

        for (int i = 0; i < ss.length; i++) {
            System.out.println(s.solution(ss[i]) == answers[i]);
        }
    }

    public int solution(String s) {
        int n = s.length();
        char[] c = new char[n];
        int t = 0;
        for (int i = 0; i < n; i++) {
            if (t > 0 && c[t - 1] == s.charAt(i)) t--;
            else c[t++] = s.charAt(i);
        }
        return t == 0 ? 1 : 0;
    }
}