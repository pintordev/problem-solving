package programmers.queue.p42627;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][][] jobsList = {
            {{0, 3}, {1, 9}, {3, 5}}
        };
        int[] answers = {8};
        for (int i = 0; i < jobsList.length; i++) {
            System.out.println(s.solution(jobsList[i]) == answers[i]);
        }
    }

    public int solution(int[][] jobs) {
        int n = jobs.length;
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] != b[1] ? a[1] - b[1] : a[0] - b[0]);
        int idx = 0;
        int time = 0;
        int total = 0;
        int done = 0;
        while (done < n) {
            while (idx < n && jobs[idx][0] <= time) {
                pq.add(jobs[idx]);
                idx++;
            }
            if (pq.isEmpty()) {
                time = jobs[idx][0];
                continue;
            }
            int[] job = pq.poll();
            time += job[1];
            total += time - job[0];
            done++;
        }
        return total / n;
    }
}