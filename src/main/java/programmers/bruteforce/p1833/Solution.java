package programmers.bruteforce.p1833;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] ns = {4};
        int[][][] datas = {{{0, 0}, {1, 1}, {0, 2}, {2, 0}}};
        int[] answers = {3};
        for (int i = 0; i < ns.length; i++) {
            System.out.println(s.solution(ns[i], datas[i]) == answers[i]);
        }
    }

    public int solution(int n, int[][] data) {
        Arrays.sort(data, (a, b) -> a[0] != b[0] ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1]));
        int res = countDown(n, data);
        for (int[] d : data) {
            d[1] = Integer.MAX_VALUE - d[1];
        }
        Arrays.sort(data, (a, b) -> a[0] != b[0] ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1]));
        return res + countDown(n, data);
    }

    public int countDown(int n, int[][] data) {
        int res = 0;
        for (int i = 0; i < n; i++) {
            int maxDown = -1;
            for (int j = i + 1; j < n; j++) {
                int y = data[j][1];
                if (y >= data[i][1]) continue;
                if (y >= maxDown) res++;
                maxDown = Math.max(maxDown, y);
            }
        }
        return res;
    }
}
