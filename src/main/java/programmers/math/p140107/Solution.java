package programmers.math.p140107;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] inputs = {{2, 4}, {1, 5}};
        long[] answers = {6, 26};
        for (int i = 0; i < inputs.length; i++) {
            System.out.println(s.solution(inputs[i][0], inputs[i][1]) == answers[i]);
        }
    }

    public long solution(int k, int d) {
        long cnt = 0;
        long ds = (long) d * d;
        for (long i = 0; i <= d; i += k) {
            cnt += (long) Math.sqrt(ds - i * i) / k + 1;
        }
        return cnt;
    }
}
