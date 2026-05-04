package programmers.greedy.p138476;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();

        int[] ks = {6, 4, 2};
        int[][] tangerines = {
            {1, 3, 2, 5, 4, 5, 2, 3},
            {1, 3, 2, 5, 4, 5, 2, 3},
            {1, 1, 1, 1, 2, 2, 2, 3}
        };
        int[] answers = {3, 2, 1};

        for (int i = 0; i < ks.length; i++) {
            System.out.println(s.solution(ks[i], tangerines[i]) == answers[i]);
        }
    }

    public int solution(int k, int[] tangerine) {
        Arrays.sort(tangerine);
        int n = tangerine.length;
        int[] bucket = new int[n + 1];
        int r = 1;
        for (int i = 1; i < n; i++) {
            if (tangerine[i] == tangerine[i - 1]) r++;
            else {
                bucket[r]++;
                r = 1;
            }
        }
        bucket[r]++;
        int cnt = 0;
        for (int i = n; i > 0 && k > 0; i--) {
            int take = Math.min(bucket[i], (k + i - 1) / i);
            k -= take * i;
            cnt += take;
        }
        return cnt;
    }
}