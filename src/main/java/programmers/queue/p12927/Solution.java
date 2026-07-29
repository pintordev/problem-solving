package programmers.queue.p12927;

import java.util.PriorityQueue;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] workss = {{4, 3, 3}, {2, 1, 2}, {1, 1}};
        int[] ns = {4, 1, 3};
        long[] answers = {12, 6, 0};
        for (int i = 0; i < workss.length; i++) {
            System.out.println(s.solution(ns[i], workss[i]) == answers[i]);
        }
    }

    public long solution(int n, int[] works) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int w : works) pq.add(w);
        while (n-- > 0 && pq.peek() > 0) pq.add(pq.poll() - 1);
        long fatigue = 0;
        while (!pq.isEmpty()) fatigue += (long) pq.peek() * pq.poll();
        return fatigue;
    }
}