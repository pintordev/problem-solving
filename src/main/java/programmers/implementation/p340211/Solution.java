package programmers.implementation.p340211;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][][] pointss = {
            {{3, 2}, {6, 4}, {4, 7}, {1, 4}},
            {{3, 2}, {6, 4}, {4, 7}, {1, 4}},
            {{2, 2}, {2, 3}, {2, 7}, {6, 6}, {5, 2}}
        };
        int[][][] routess = {
            {{4, 2}, {1, 3}, {2, 4}},
            {{4, 2}, {1, 3}, {4, 2}, {4, 3}},
            {{2, 3, 4, 5}, {1, 3, 4, 5}}
        };
        int[] answers = {1, 9, 0};
        for (int i = 0; i < pointss.length; i++) {
            System.out.println(s.solution(pointss[i], routess[i]) == answers[i]);
        }
    }

    Map<Integer, Integer> cnts;

    public int solution(int[][] points, int[][] routes) {
        cnts = new HashMap<>();
        for (int[] route : routes) {
            int[] p = points[route[0] - 1].clone();
            int t = 0;
            mark(t, p[0], p[1]);
            for (int i = 1; i < route.length; i++) {
                int[] to = points[route[i] - 1];
                t = move(t, p, 0, to[0]);
                t = move(t, p, 1, to[1]);
            }
        }
        int risk = 0;
        for (int cnt : cnts.values()) {
            if (cnt >= 2) risk++;
        }
        return risk;
    }

    public int move(int t, int[] p, int d, int to) {
        int ds = Integer.compare(to, p[d]);
        while (p[d] != to) {
            p[d] += ds;
            t++;
            mark(t, p[0], p[1]);
        }
        return t;
    }

    public void mark(int t, int r, int c) {
        int key = (t << 16) | (r << 8) | c;
        cnts.merge(key, 1, Integer::sum);
    }
}