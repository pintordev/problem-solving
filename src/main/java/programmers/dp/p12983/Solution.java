package programmers.dp.p12983;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        String[][] strss = {
                {"ba", "na", "n", "a"},
                {"app", "ap", "p", "l", "e", "ple", "pp"},
                {"ba", "an", "nan", "ban", "n"}
        };
        String[] ts = {"banana", "apple", "banana"};
        int[] answers = {3, 2, -1};
        for (int i = 0; i < strss.length; i++) {
            System.out.println(s.solution(strss[i], ts[i]) == answers[i]);
        }
    }

    public int solution(String[] strs, String t) {
        Set<String> pieces = new HashSet<>();
        int maxLen = 0;
        for (String str : strs) {
            pieces.add(str);
            maxLen = Math.max(maxLen, str.length());
        }
        int n = t.length();
        int[] dp = new int[n + 1];
        Arrays.fill(dp, n + 1);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int len = 1; len <= maxLen && len <= i; len++) {
                if (dp[i - len] > n || !pieces.contains(t.substring(i - len, i))) continue;
                dp[i] = Math.min(dp[i], dp[i - len] + 1);
            }
        }
        return dp[n] > n ? -1 : dp[n];
    }
}
