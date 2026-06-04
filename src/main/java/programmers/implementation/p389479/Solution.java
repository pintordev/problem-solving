package programmers.implementation.p389479;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] playersList = {
            {0, 2, 3, 3, 1, 2, 0, 0, 0, 0, 4, 2, 0, 6, 0, 4, 2, 13, 3, 5, 10, 0, 1, 5},
            {0, 0, 0, 10, 0, 12, 0, 15, 0, 1, 0, 1, 0, 0, 0, 5, 0, 0, 11, 0, 8, 0, 0, 0},
            {0, 0, 0, 0, 0, 2, 0, 0, 0, 1, 0, 5, 0, 2, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1}
        };
        int[] ms = {3, 5, 1};
        int[] ks = {5, 1, 1};
        int[] answers = {7, 11, 12};
        for (int i = 0; i < playersList.length; i++) {
            System.out.println(s.solution(playersList[i], ms[i], ks[i]) == answers[i]);
        }
    }

    public int solution(int[] players, int m, int k) {
        int[] expansion = new int[24 + k];
        int total = 0;
        for (int i = 0, cur = 0; i < players.length; i++) {
            cur += expansion[i];
            int required = players[i] / m;
            if (cur >= required) continue;
            int needed = required - cur;
            cur += needed;
            total += needed;
            expansion[i + k] -= needed;
        }
        return total;
    }
}