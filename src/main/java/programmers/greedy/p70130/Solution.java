package programmers.greedy.p70130;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] as = {
                {0},
                {5, 2, 3, 3, 5, 3},
                {0, 3, 3, 0, 7, 2, 0, 2, 2, 0},
        };
        int[] answers = {0, 4, 8};
        for (int i = 0; i < as.length; i++) {
            System.out.println(s.solution(as[i]) == answers[i]);
        }
    }

    public int solution(int[] a) {
        int n = a.length;
        int[] freq = new int[n];
        for (int x : a) {
            freq[x]++;
        }
        int best = 0;
        for (int v = 0; v < n; v++) {
            if (freq[v] << 1 <= best) continue;
            int pairs = 0;
            for (int i = 0; i + 1 < n; i++) {
                if ((a[i] == v) == (a[i + 1] == v)) continue;
                pairs++;
                i++;
            }
            best = Math.max(best, pairs << 1);
        }
        return best;
    }
}
