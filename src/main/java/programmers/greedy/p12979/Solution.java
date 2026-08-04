package programmers.greedy.p12979;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] Ns = {11, 16};
        int[][] stationss = {{4, 11}, {9}};
        int[] Ws = {1, 2};
        int[] answers = {3, 3};
        for (int i = 0; i < Ns.length; i++) {
            System.out.println(s.solution(Ns[i], stationss[i], Ws[i]) == answers[i]);
        }
    }

    public int solution(int n, int[] stations, int w) {
        int res = 0;
        int covered = 0;
        int range = 2 * w + 1;
        for (int station : stations) {
            int start = station - w;
            if (start > covered + 1) res += (start - covered - 2) / range + 1;
            covered = station + w;
        }
        if (n > covered) res += (n - covered - 1) / range + 1;
        return res;
    }
}