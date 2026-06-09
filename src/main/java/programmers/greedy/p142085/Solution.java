package programmers.greedy.p142085;

import java.util.PriorityQueue;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] ns = {7, 2};
        int[] ks = {3, 4};
        int[][] enemies = {{4, 2, 4, 5, 3, 3, 1}, {3, 3, 3, 3}};
        int[] answers = {5, 4};
        for (int i = 0; i < ns.length; i++) {
            System.out.println(s.solution(ns[i], ks[i], enemies[i]) == answers[i]);
        }
    }

    public int solution(int n, int k, int[] enemy) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < enemy.length; i++) {
            if (k-- > 0) pq.add(enemy[i]);
            else if (enemy[i] <= pq.peek()) n -= enemy[i];
            else {
                n -= pq.poll();
                pq.add(enemy[i]);
            }
            if (n == 0) return i + 1;
            else if (n < 0) return i;
        }
        return enemy.length;
    }
}