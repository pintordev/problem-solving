package programmers.greedy.p68646;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] as = {
            {9, -1, -5},
            {-16, 27, 65, -2, 58, -92, -71, -68, -61, -33}
        };
        int[] answers = {3, 6};
        for (int i = 0; i < as.length; i++) {
            System.out.println(s.solution(as[i]) == answers[i]);
        }
    }

    public int solution(int[] a) {
        int n = a.length;
        int[] prefixMin = new int[n];
        prefixMin[0] = a[0];
        for (int i = 1; i < n; i++) {
            prefixMin[i] = Math.min(prefixMin[i - 1], a[i]);
        }
        int[] suffixMin = new int[n];
        suffixMin[n - 1] = a[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(suffixMin[i + 1], a[i]);
        }
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] == prefixMin[i] || a[i] == suffixMin[i]) cnt++;
        }
        return cnt;
    }
}