package programmers.math.p389481;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        long[] ns = {30, 7388};
        String[][] banss = {
                {"d", "e", "bb", "aa", "ae"},
                {"gqk", "kdn", "jxj", "jxi", "fug", "jxg", "ewq", "len", "bhc"}
        };
        String[] answers = {"ah", "jxk"};
        for (int i = 0; i < ns.length; i++) {
            System.out.println(s.solution(ns[i], banss[i]).equals(answers[i]));
        }
    }

    public String solution(long n, String[] bans) {
        long[] banRanks = new long[bans.length];
        for (int i = 0; i < bans.length; i++) {
            String ban = bans[i];
            long rank = 0;
            for (int j = 0; j < ban.length(); j++) {
                rank = rank * 26 + (ban.charAt(j) - 'a' + 1);
            }
            banRanks[i] = rank;
        }
        Arrays.sort(banRanks);
        for (long banRank : banRanks) {
            if (banRank > n) break;
            n++;
        }
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            n--;
            sb.append((char) ('a' + n % 26));
            n /= 26;
        }
        return sb.reverse().toString();
    }
}
