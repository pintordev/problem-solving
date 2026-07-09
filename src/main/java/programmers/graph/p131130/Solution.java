package programmers.graph.p131130;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] cardss = {{8, 6, 3, 7, 2, 5, 1, 4}};
        int[] answers = {12};
        for (int i = 0; i < cardss.length; i++) {
            System.out.println(s.solution(cardss[i]) == answers[i]);
        }
    }

    public int solution(int[] cards) {
        PriorityQueue<Integer> q = new PriorityQueue<>(Comparator.reverseOrder());
        boolean[] visited = new boolean[cards.length];
        int idx = 0, cnt = 0;
        while (idx < cards.length) {
            if (!visited[idx]) {
                visited[idx] = true;
                cnt++;
                idx = cards[idx] - 1;
            } else {
                if (cnt != 0) q.add(cnt);
                cnt = 0;
                idx++;
            }
        }
        return q.size() == 1 ? 0 : q.poll() * q.poll();
    }
}