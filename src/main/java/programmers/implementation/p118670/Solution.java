package programmers.implementation.p118670;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][][] rcs = {
                {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
                {{8, 6, 3}, {3, 3, 7}, {8, 4, 9}},
                {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}}
        };
        String[][] operationss = {
                {"Rotate", "ShiftRow"},
                {"Rotate", "ShiftRow", "ShiftRow"},
                {"ShiftRow", "Rotate", "ShiftRow", "Rotate"}
        };
        int[][][] answers = {
                {{8, 9, 6}, {4, 1, 2}, {7, 5, 3}},
                {{8, 3, 3}, {4, 9, 7}, {3, 8, 6}},
                {{1, 6, 7, 8}, {5, 9, 10, 4}, {2, 3, 12, 11}}
        };
        for (int i = 0; i < rcs.length; i++) {
            System.out.println(Arrays.deepEquals(s.solution(rcs[i], operationss[i]), answers[i]));
        }
    }

    public int[][] solution(int[][] rc, String[] operations) {
        int n = rc.length;
        int m = rc[0].length;
        Deque<Integer> left = new ArrayDeque<>();
        Deque<Deque<Integer>> center = new ArrayDeque<>();
        Deque<Integer> right = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            left.add(rc[i][0]);
            Deque<Integer> row = new ArrayDeque<>();
            for (int j = 1; j < m - 1; j++) {
                row.add(rc[i][j]);
            }
            center.add(row);
            right.add(rc[i][m - 1]);
        }

        for (String op : operations) {
            if (op.equals("ShiftRow")) {
                left.addFirst(left.pollLast());
                center.addFirst(center.pollLast());
                right.addFirst(right.pollLast());
                continue;
            }
            center.peekFirst().addFirst(left.pollFirst());
            right.addFirst(center.peekFirst().pollLast());
            center.peekLast().addLast(right.pollLast());
            left.addLast(center.peekLast().pollFirst());
        }

        int[][] res = new int[n][m];
        for (int i = 0; i < n; i++) {
            res[i][0] = left.poll();
            Deque<Integer> row = center.poll();
            for (int j = 1; j < m - 1; j++) {
                res[i][j] = row.poll();
            }
            res[i][m - 1] = right.poll();
        }
        return res;
    }
}
