package programmers.greedy.p155651;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        String[][][] inputs = {
                {{"15:00", "17:00"}, {"16:40", "18:20"}, {"14:20", "15:20"}, {"14:10", "19:20"}, {"18:20", "21:20"}},
                {{"09:10", "10:10"}, {"10:20", "12:20"}},
                {{"10:20", "12:30"}, {"10:20", "12:30"}, {"10:20", "12:30"}}
        };
        int[] answers = {3, 1, 3};
        for (int i = 0; i < inputs.length; i++) {
            System.out.println(s.solution(inputs[i]) == answers[i]);
        }
    }

    public int solution(String[][] book_time) {
        int[][] bt = new int[book_time.length][];
        for (int i = 0; i < book_time.length; i++) {
            bt[i] = parse(book_time[i]);
        }
        Arrays.sort(bt, (o1, o2) -> o1[0] - o2[0]);
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        int max = 0;
        for (int[] cur : bt) {
            while (!pq.isEmpty() && pq.peek()[1] <= cur[0]) {
                pq.poll();
            }
            pq.add(cur);
            max = Math.max(pq.size(), max);
        }
        return max;
    }

    public int[] parse(String[] bts) {
        return new int[]{parseTime(bts[0]), parseTime(bts[1]) + 10};
    }

    public int parseTime(String bt) {
        return Integer.parseInt(bt.substring(0, 2)) * 60 + Integer.parseInt(bt.substring(3));
    }
}