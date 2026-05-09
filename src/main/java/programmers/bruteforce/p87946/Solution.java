package programmers.bruteforce.p87946;

public class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();

        int[] ks = {80};
        int[][][] dungeonsList = {
            {{80, 20}, {50, 40}, {30, 10}}
        };
        int[] answers = {3};

        for (int i = 0; i < ks.length; i++) {
            System.out.println(s.solution(ks[i], dungeonsList[i]) == answers[i]);
        }
    }

    public int solution(int k, int[][] dungeons) {
        return dfs(k, dungeons, 0);
    }

    public int dfs(int k, int[][] dungeons, int visited) {
        int max = 0;
        for (int i = 0; i < dungeons.length; i++) {
            if ((visited & (1 << i)) != 0 || k < dungeons[i][0]) continue;
            max = Math.max(max, 1 + dfs(k - dungeons[i][1], dungeons, visited | (1 << i)));
        }
        return max;
    }
}