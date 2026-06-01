package programmers.greedy.p155651;

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
        int[] timeline = new int[1450];
        for (String[] bt : book_time) {
            timeline[parseTime(bt[0])]++;
            timeline[parseTime(bt[1]) + 10]--;
        }
        int max = 0, cur = 0;
        for (int t : timeline) {
            max = Math.max(max, cur += t);
        }
        return max;
    }

    public int parseTime(String bt) {
        return Integer.parseInt(bt.substring(0, 2)) * 60 + Integer.parseInt(bt.substring(3));
    }
}