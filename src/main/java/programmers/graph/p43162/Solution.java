package programmers.graph.p43162;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] ns = {3, 3};
        int[][][] computerss = {
                {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}},
                {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}}
        };
        int[] answers = {2, 1};
        for (int i = 0; i < ns.length; i++) {
            System.out.println(s.solution(ns[i], computerss[i]) == answers[i]);
        }
    }

    int n;
    int[][] computers;
    boolean[] visited;

    public int solution(int n, int[][] computers) {
        this.n = n;
        this.computers = computers;
        visited = new boolean[n];
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            dfs(i);
            cnt++;
        }
        return cnt;
    }

    public void dfs(int cur) {
        visited[cur] = true;
        for (int next = 0; next < n; next++) {
            if (computers[cur][next] != 1 || visited[next]) continue;
            dfs(next);
        }
    }
}