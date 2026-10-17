package programmers.backtracking.p1838;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] ns = {3, 2, 2, 4};
        int[] ms = {2, 1, 2, 5};
        int[][][] timetables = {
                {{1170, 1210}, {1200, 1260}},
                {{840, 900}},
                {{600, 630}, {630, 700}},
                {{1140, 1200}, {1150, 1200}, {1100, 1200}, {1210, 1300}, {1220, 1280}}
        };
        int[] answers = {4, 0, 2, 4};
        for (int i = 0; i < ns.length; i++) {
            System.out.println(s.solution(ns[i], ms[i], timetables[i]) == answers[i]);
        }
    }

    int n;
    int d;
    List<Node> nodes;

    public int solution(int n, int m, int[][] timetable) {
        this.n = n;
        int max = calMax(timetable);
        if (max == 1) return 0;
        return calDist(max);
    }

    public int calMax(int[][] timetable) {
        int[] cumulative = new int[722];
        for (int[] time : timetable) {
            cumulative[time[0] - 600]++;
            cumulative[time[1] - 600 + 1]--;
        }
        int max = 1;
        for (int i = 1; i < 722; i++) {
            cumulative[i] += cumulative[i - 1];
            max = Math.max(max, cumulative[i]);
        }
        return max;
    }

    public int calDist(int max) {
        for (d = 2 * (n - 1); d > 0; d--) {
            for (int sc = 0; sc < n; sc++) {
                if (calCnt(sc) >= max) return d;
            }
        }
        return 0;
    }

    public int calCnt(int sc) {
        nodes = new ArrayList<>();
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (r == 0 && c < sc) continue;
                if (!isAvailable(r, c)) continue;
                nodes.add(new Node(r, c));
            }
        }
        return nodes.size();
    }

    public boolean isAvailable(int r, int c) {
        for (Node node : nodes) {
            if (Math.abs(node.r - r) + Math.abs(node.c - c) < d) return false;
        }
        return true;
    }
}

class Node {
    int r;
    int c;

    Node(int r, int c) {
        this.r = r;
        this.c = c;
    }
}
