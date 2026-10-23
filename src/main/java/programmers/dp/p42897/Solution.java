package programmers.dp.p42897;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] moneys = {
                {1, 2, 3, 1}
        };
        int[] answers = {4};
        for (int i = 0; i < moneys.length; i++) {
            System.out.println(s.solution(moneys[i]) == answers[i]);
        }
    }

    int[] money;

    public int solution(int[] money) {
        this.money = money;
        int n = money.length;
        return Math.max(rob(0, n - 2), rob(1, n - 1));
    }

    public int rob(int from, int to) {
        int prev = 0;
        int cur = 0;
        for (int i = from; i <= to; i++) {
            int next = Math.max(cur, prev + money[i]);
            prev = cur;
            cur = next;
        }
        return cur;
    }
}
