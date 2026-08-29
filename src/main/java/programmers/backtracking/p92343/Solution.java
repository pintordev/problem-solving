package programmers.backtracking.p92343;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] infos = {
            {0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1},
            {0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0}
        };
        int[][][] edgesList = {
            {{0, 1}, {1, 2}, {1, 4}, {0, 8}, {8, 7}, {9, 10}, {9, 11}, {4, 3}, {6, 5}, {4, 6}, {8, 9}},
            {{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 5}, {2, 6}, {3, 7}, {4, 8}, {6, 9}, {9, 10}}
        };
        int[] answers = {5, 5};
        for (int i = 0; i < infos.length; i++) {
            System.out.println(s.solution(infos[i], edgesList[i]) == answers[i]);
        }
    }

    int n;
    List<Integer>[] graph;
    int sheepMask;
    int wolfMask;
    int[] memo;

    public int solution(int[] info, int[][] edges) {
        n = info.length;
        graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        sheepMask = 0;
        wolfMask = 0;
        for (int i = 0; i < n; i++) {
            if (info[i] == 0) sheepMask |= (1 << i);
            else wolfMask |= (1 << i);
        }
        memo = new int[1 << n];
        Arrays.fill(memo, -1);
        return dfs(1);
    }

    public int dfs(int mask) {
        if (memo[mask] != -1) return memo[mask];
        int best = Integer.bitCount(mask & sheepMask);
        int frontier = frontierMask(mask);
        for (int f = frontier; f != 0; f &= f - 1) {
            int v = Integer.numberOfTrailingZeros(f);
            int newMask = mask | (1 << v);
            int sheep = Integer.bitCount(newMask & sheepMask);
            int wolf = Integer.bitCount(newMask & wolfMask);
            if (wolf < sheep) best = Math.max(best, dfs(newMask));
        }
        return memo[mask] = best;
    }

    public int frontierMask(int mask) {
        int res = 0;
        for (int u = 0; u < n; u++) {
            if ((mask & (1 << u)) == 0) continue;
            for (int v : graph[u]) {
                if ((mask & (1 << v)) == 0) res |= (1 << v);
            }
        }
        return res;
    }
}
