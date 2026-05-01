package programmers.math.p12911;

public class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();

        int[] ns = {78, 15};
        int[] answers = {83, 23};

        for (int i = 0; i < ns.length; i++) {
            System.out.println(s.solution(ns[i]) == answers[i]);
        }
    }

    public int solution(int n) {
        int c = n;
        int c0 = 0, c1 = 0;
        while ((c & 1) == 0 && c != 0) {c0++; c >>= 1;}
        while ((c & 1) == 1) {c1++; c >>= 1;}
        int p = c0 + c1;
        n |= (1 << p);
        n &= ~((1 << p) - 1);
        n |= (1 << (c1 - 1)) - 1;
        return n;
    }
}