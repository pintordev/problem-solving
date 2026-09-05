package programmers.stack.p77886;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        String[][] ss = {
                {"1110", "100111100", "0111111010"},
        };
        String[][] answers = {
                {"1101", "100110110", "0110110111"},
        };
        for (int i = 0; i < ss.length; i++) {
            System.out.println(Arrays.equals(s.solution(ss[i]), answers[i]));
        }
    }

    public String[] solution(String[] s) {
        for (int i = 0; i < s.length; i++) {
            s[i] = transform(s[i]);
        }
        return s;
    }

    public String transform(String x) {
        StringBuilder sb = new StringBuilder(x.length());
        int cnt = 0;
        for (int i = 0; i < x.length(); i++) {
            char c = x.charAt(i);
            int len = sb.length();
            if (c == '0' && len >= 2 && sb.charAt(len - 1) == '1' && sb.charAt(len - 2) == '1') {
                sb.setLength(len - 2);
                cnt++;
                continue;
            }
            sb.append(c);
        }
        int cut = sb.lastIndexOf("0") + 1;
        sb.insert(cut, "110".repeat(cnt));
        return sb.toString();
    }
}
