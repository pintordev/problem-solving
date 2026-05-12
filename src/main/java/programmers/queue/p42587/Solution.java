package programmers.queue.p42587;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] priorities = {
            {2, 1, 3, 2},
            {1, 1, 9, 1, 1, 1}
        };
        int[] locations = {2, 0};
        int[] answers = {1, 5};

        for (int i = 0; i < priorities.length; i++) {
            System.out.println(s.solution(priorities[i], locations[i]) == answers[i]);
        }
    }

    public int solution(int[] priorities, int location) {
        Queue<Process> q = new ArrayDeque<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < priorities.length; i++) {
            q.add(new Process(i, priorities[i]));
            pq.add(priorities[i]);
        }

        int order = 0;
        while (!q.isEmpty()) {
            Process curr = q.poll();
            if (pq.peek() != curr.priority) q.add(curr);
            else {
                pq.poll();
                order++;
                if (curr.location == location) return order;
            }
        }
        return order;
    }
}

class Process {
    int location;
    int priority;

    public Process(int location, int priority) {
        this.location = location;
        this.priority = priority;
    }
}