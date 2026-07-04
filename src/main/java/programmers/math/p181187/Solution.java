package programmers.math.p181187;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] inputs = {{2, 3}};
        long[] answers = {20};
        for (int i = 0; i < inputs.length; i++) {
            System.out.println(s.solution(inputs[i][0], inputs[i][1]) == answers[i]);
        }
    }

    public long solution(int r1, int r2) {
        long r1sq = (long) r1 * r1;
        long r2sq = (long) r2 * r2;
        long cnt = 0;
        for (long i = 1; i <= r2; i++) {
            long iSq = i * i;
            cnt += (long) Math.sqrt(r2sq - iSq)
                    - (i < r1 ? (long) Math.ceil(Math.sqrt(r1sq - iSq)) : 0)
                    + 1;
        }
        return cnt * 4;
    }
}