package programmers.bruteforce.p64064;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        String[][] user_ids = {
            {"frodo", "fradi", "crodo", "abc123", "frodoc"},
            {"frodo", "fradi", "crodo", "abc123", "frodoc"},
            {"frodo", "fradi", "crodo", "abc123", "frodoc"}
        };
        String[][] banned_ids = {
            {"fr*d*", "abc1**"},
            {"*rodo", "*rodo", "******"},
            {"fr*d*", "*rodo", "******", "******"}
        };
        int[] answers = {2, 2, 3};
        for (int i = 0; i < user_ids.length; i++) {
            System.out.println(s.solution(user_ids[i], banned_ids[i]) == answers[i]);
        }
    }
    
    int n, m;
    boolean[][] matched;
    Set<Integer> combinations;

    public int solution(String[] user_id, String[] banned_id) {
        n = user_id.length;
        m = banned_id.length;
        matched = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matched[i][j] = isMatched(banned_id[i], user_id[j]);
            }
        }
        combinations = new HashSet<>();
        dfs(0, 0);
        return combinations.size();
    }
    
    public boolean isMatched(String banned, String user) {
        if (banned.length() != user.length()) return false;
        for (int i = 0; i < banned.length(); i++) {
            if (banned.charAt(i) != '*' && banned.charAt(i) != user.charAt(i)) return false;
        }
        return true;
    }

    public void dfs(int idx, int mask) {
        if (idx == m) {
            combinations.add(mask);
            return;
        }
        for (int j = 0; j < n; j++) {
            if ((mask & (1 << j)) != 0 || !matched[idx][j]) continue;
            dfs(idx + 1, mask | (1 << j));
        }
    }
}