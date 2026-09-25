package programmers.greedy.p258707;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] coins = {4, 3, 2, 10};
        int[][] cardss = {
                {3, 6, 7, 2, 1, 10, 5, 9, 8, 12, 11, 4},
                {1, 2, 3, 4, 5, 8, 6, 7, 9, 10, 11, 12},
                {5, 8, 1, 2, 9, 4, 12, 11, 3, 10, 6, 7},
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18}
        };
        int[] answers = {5, 2, 4, 1};
        for (int i = 0; i < coins.length; i++) {
            System.out.println(s.solution(coins[i], cardss[i]) == answers[i]);
        }
    }

    int initialCount;

    public int solution(int coin, int[] cards) {
        int n = cards.length;
        initialCount = n / 3;
        int rounds = n / 3;
        int[] pos = new int[n + 1];
        for (int i = 0; i < n; i++) {
            pos[cards[i]] = i;
        }
        List<Integer>[] bucket = new List[rounds + 1];
        for (int r = 0; r <= rounds; r++) {
            bucket[r] = new ArrayList<>();
        }
        for (int v = 1; v * 2 <= n; v++) {
            int partner = n + 1 - v;
            int cost = 0;
            if (pos[v] >= initialCount) cost++;
            if (pos[partner] >= initialCount) cost++;
            int readyRound = Math.max(roundOf(pos[v]), roundOf(pos[partner]));
            bucket[readyRound].add(cost);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int cost : bucket[0]) {
            pq.add(cost);
        }
        int activated = 0;
        int remainingCoin = coin;
        for (int r = 1; r <= rounds; r++) {
            for (int cost : bucket[r]) {
                pq.add(cost);
            }
            while (activated < r) {
                if (pq.isEmpty()) return r;
                int cost = pq.peek();
                if (cost > remainingCoin) return r;
                pq.poll();
                remainingCoin -= cost;
                activated++;
            }
        }
        return rounds + 1;
    }

    public int roundOf(int idx) {
        if (idx < initialCount) return 0;
        return ((idx - initialCount) >> 1) + 1;
    }
}
