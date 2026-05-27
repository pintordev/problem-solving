package programmers.twopointer.p178870;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();

        int[][] sequences = {
            {1, 2, 3, 4, 5},
            {1, 1, 1, 2, 3, 4, 5},
            {2, 2, 2, 2, 2}
        };
        int[] ks = {7, 5, 6};
        int[][] answers = {{2, 3}, {6, 6}, {0, 2}};

        for (int i = 0; i < sequences.length; i++) {
            int[] result = s.solution(sequences[i], ks[i]);
            System.out.println(result[0] == answers[i][0] && result[1] == answers[i][1]);
        }
    }

    public int[] solution(int[] sequence, int k) {
        int l = 0, r= 0, sum = sequence[0], al = 0, ar = sequence.length - 1;
        while (r < sequence.length) {
            if (sum < k && ++r < sequence.length) sum += sequence[r];
            else if (sum > k) sum -= sequence[l++];
            else if (sum == k) {
                if (r - l < ar - al) {
                    al = l;
                    ar = r;
                }
                sum -= sequence[l++];
            }
        }
        return new int[]{al, ar};
    }
}