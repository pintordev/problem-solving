package programmers.search.p12920;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] ns = {6};
        int[][] coress = {{1, 2, 3}};
        int[] answers = {2};
        for (int i = 0; i < ns.length; i++) {
            System.out.println(s.solution(ns[i], coress[i]) == answers[i]);
        }
    }

    public int solution(int n, int[] cores) {
        if (n <= cores.length) return n;
        long target = n - cores.length;
        int maxCore = 0;
        for (int core : cores) {
            maxCore = Math.max(maxCore, core);
        }
        long lo = 0, hi = target * maxCore;
        while (lo + 1 < hi) {
            long mid = (lo + hi) >> 1;
            long cnt = 0;
            for (int core : cores) cnt += mid / core;
            if (cnt < target) lo = mid;
            else hi = mid;
        }
        long done = 0;
        for (int core : cores) {
            done += (hi - 1) / core;
        }
        long remain = target - done;
        for (int i = 0; i < cores.length; i++) {
            if (hi % cores[i] != 0) continue;
            remain--;
            if (remain == 0) return i + 1;
        }
        return -1;
    }
}
