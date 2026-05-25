package programmers.queue.p118667;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] queue1s = {{3, 2, 7, 2}, {1, 2, 1, 2}, {1, 1}};
        int[][] queue2s = {{4, 6, 5, 1}, {1, 10, 1, 2}, {1, 5}};
        int[] answers = {2, 7, -1};
        for (int i = 0; i < queue1s.length; i++) {
            System.out.println(s.solution(queue1s[i], queue2s[i]) == answers[i]);
        }
    }

    public int solution(int[] queue1, int[] queue2) {
        int[] queue = new int[queue1.length << 1];
        long sum1 = 0, sum2 = 0;
        for (int i = 0; i < queue1.length; i++) {
            queue[i] = queue1[i];
            sum1 += queue1[i];
        }
        for (int i = queue1.length; i < queue.length; i++) {
            queue[i] = queue2[i - queue1.length];
            sum2 += queue2[i - queue1.length];
        }
        if (((sum1 + sum2) & 1) == 1) return -1;
        int l = 0, r = queue1.length;
        long eq = (sum1 + sum2) >> 1;
        while (l < r && r < queue.length) {
            if (sum1 == eq) return (l + r - queue1.length);
            else if (sum1 > eq) sum1 -= queue[l++];
            else sum1 += queue[r++];
        }
        return -1;
    }
}