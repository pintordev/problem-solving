package programmers.dp.p42895;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] Ns = {5, 2};
        int[] numbers = {12, 11};
        int[] answers = {4, 3};
        for (int i = 0; i < Ns.length; i++) {
            System.out.println(s.solution(Ns[i], numbers[i]) == answers[i]);
        }
    }

    Set<Integer>[] dp;

    public int solution(int N, int number) {
        dp = new Set[9];
        int concat = 0;
        for (int cnt = 1; cnt <= 8; cnt++) {
            concat = concat * 10 + N;
            Set<Integer> cur = new HashSet<>();
            cur.add(concat);
            dp[cnt] = cur;
            for (int j = 1; j <= cnt >> 1; j++) {
                combine(cnt, j);
            }
            if (cur.contains(number)) return cnt;
        }
        return -1;
    }

    public void combine(int cnt, int j) {
        Set<Integer> cur = dp[cnt];
        for (int a : dp[j]) {
            for (int b : dp[cnt - j]) {
                cur.add(a + b);
                cur.add(a - b);
                cur.add(b - a);
                cur.add(a * b);
                if (b != 0) cur.add(a / b);
                if (a != 0) cur.add(b / a);
            }
        }
    }
}
