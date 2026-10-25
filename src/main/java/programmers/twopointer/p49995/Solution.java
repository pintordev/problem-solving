package programmers.twopointer.p49995;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] cookies = {{1, 1, 2, 3}, {1, 2, 4, 5}};
        int[] answers = {3, 0};
        for (int i = 0; i < cookies.length; i++) {
            System.out.println(s.solution(cookies[i]) == answers[i]);
        }
    }

    public int solution(int[] cookie) {
        int n = cookie.length, res = 0;
        for (int m = 0; m < n - 1; m++) {
            int l = m, r = m + 1, lsum = cookie[l], rsum = cookie[r];
            while (true) {
                if (lsum == rsum && res < lsum) res = lsum;
                else if (lsum <= rsum && l > 0) lsum += cookie[--l];
                else if (lsum > rsum && r < n - 1) rsum += cookie[++r];
                else break;
            }
        }
        return res;
    }
}
