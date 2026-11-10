package programmers.backtracking.p1831;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] ns = {15, 24, 41, 2147483647};
        int[] answers = {1, 0, 2, 1735};
        for (int i = 0; i < ns.length; i++) {
            System.out.println(s.solution(ns[i]) == answers[i]);
        }
    }

    int cnt;

    public int solution(int n) {
        cnt = 0;
        dfs(n, 0);
        return cnt;
    }

    public void dfs(int cur, int plus) {
        if (Math.pow(3, plus >> 1) > cur) return;
        if (cur == 1 && plus == 0) {
            cnt++;
            return;
        }
        if (cur % 3 == 0 && plus >= 2) dfs(cur / 3, plus - 2);
        dfs(cur - 1, plus + 1);
    }
}
