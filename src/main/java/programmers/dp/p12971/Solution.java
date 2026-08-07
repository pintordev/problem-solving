package programmers.dp.p12971;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] stickers = {
                {14, 6, 5, 11, 3, 9, 2, 10},
                {1, 3, 2, 5, 4}
        };
        int[] answers = {36, 8};
        for (int i = 0; i < stickers.length; i++) {
            System.out.println(s.solution(stickers[i]) == answers[i]);
        }
    }

    public int solution(int[] sticker) {
        int n = sticker.length;
        if (n == 1) return sticker[0];
        return Math.max(maxSum(sticker, 0, n - 2), maxSum(sticker, 1, n - 1));
    }

    public int maxSum(int[] sticker, int start, int end) {
        int prev2 = 0, prev1 = 0;
        for (int i = start; i <= end; i++) {
            int cur = Math.max(prev1, prev2 + sticker[i]);
            prev2 = prev1;
            prev1 = cur;
        }
        return prev1;
    }
}