package programmers.queue.p42626;

import java.util.PriorityQueue;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] inputs = {
            {1, 2, 3, 9, 10, 12}
        };
        int[] ks = {7};
        int[] answers = {2};
        for (int i = 0; i < inputs.length; i++) {
            System.out.println(s.solution(inputs[i], ks[i]) == answers[i]);
        }
    }

    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int s : scoville) pq.add(s);
        int cnt = 0;
        while (pq.size() > 1 && pq.peek() < K) {
            int a = pq.poll();
            int b = pq.poll();
            pq.add(a + (b << 1));
            cnt++;
        }
        return pq.peek() < K ? -1 : cnt;
    }
}