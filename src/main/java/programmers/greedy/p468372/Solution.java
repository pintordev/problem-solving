package programmers.greedy.p468372;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] dist_limits = {3, 0, 3, 5};
        int[] split_limits = {6, 10, 100, 16};
        int[] answers = {6, 1, 7, 9};
        for (int i = 0; i < dist_limits.length; i++) {
            System.out.println(s.solution(dist_limits[i], split_limits[i]) == answers[i]);
        }
    }

    public int solution(int dist_limit, int split_limit) {
        int res = 1;
        for (int p = 0; ; p++) {
            long pow2 = (long) Math.pow(2, p);
            if (pow2 > split_limit) break;
            for (int q = 0; ; q++) {
                long pow3 = (long) Math.pow(3, q);
                if (split_limit / pow2 < pow3) break;
                long currentSplitValue = pow2 * pow3;
                if (split_limit / currentSplitValue >= 2) res = Math.max(res, calculateMaxLeaves(p, q, 2, dist_limit));
                if (split_limit / currentSplitValue >= 3) res = Math.max(res, calculateMaxLeaves(p, q, 3, dist_limit));
                res = Math.max(res, calculateMaxLeaves(p, q, 0, dist_limit));
            }
        }
        return res;
    }

    public int calculateMaxLeaves(int p, int q, int extraType, int distLimit) {
        int nodes = 1;
        for (int i = 0; i < p; i++) {
            if (distLimit < nodes) return 0;
            distLimit -= nodes;
            nodes *= 2;
        }
        for (int i = 0; i < q; i++) {
            if (distLimit < nodes) return 0;
            distLimit -= nodes;
            nodes *= 3;
        }
        int totalLeaves = nodes;
        if (extraType > 0 && distLimit > 0) totalLeaves += Math.min(nodes, distLimit) * (extraType - 1);
        return totalLeaves;
    }
}