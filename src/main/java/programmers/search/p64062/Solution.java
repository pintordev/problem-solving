package programmers.search.p64062;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] stoness = {
            {2, 4, 5, 3, 2, 1, 4, 2, 5, 1}
        };
        int[] ks = {3};
        int[] answers = {3};

        for (int i = 0; i < stoness.length; i++) {
            System.out.println(s.solution(stoness[i], ks[i]) == answers[i]);
        }
    }

    public int solution(int[] stones, int k) {
        int lo = 1, hi = 0;
        for (int stone : stones) hi = Math.max(hi, stone);
        while (lo < hi + 1) {
            int mid = (lo + hi) / 2;
            if (canCross(stones, k, mid)) lo = mid + 1;
            else hi = mid - 1;
        }
        return hi;
    }

    public boolean canCross(int[] stones, int k, int people) {
        int cnt = 0;
        for (int stone : stones) {
            if (stone < people) cnt++;
            else cnt = 0;
            if (cnt >= k) return false;
        }
        return true;
    }
}