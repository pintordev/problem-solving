package programmers.greedy.p12980;

public class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();

        int[] ns = {5, 6, 5000};
        int[] answers = {2, 2, 5};

        for (int i = 0; i < ns.length; i++) {
            System.out.println(s.solution(ns[i]) == answers[i]);
        }
    }

    public int solution(int n) {
        return Integer.bitCount(n);
    }
}