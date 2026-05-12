package programmers.hash.p132265;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] toppings = {
            {1, 2, 1, 3, 1, 4, 1, 2},
            {1, 2, 3, 1, 4}
        };
        int[] answers = {2, 0};

        for (int i = 0; i < toppings.length; i++) {
            System.out.println(s.solution(toppings[i]) == answers[i]);
        }
    }

    public int solution(int[] topping) {
        int[] right = new int[10001];
        int[] left = new int[10001];
        int ru = 0, lu = 0;
        for (int t : topping) {
            if (right[t]++ == 0) ru++;
        }

        int cnt = 0;
        for (int t : topping) {
            if (left[t]++ == 0) lu++;
            if (--right[t] == 0) ru--;
            if (lu == ru) cnt++;
        }
        return cnt;
    }
}