package programmers.greedy.p17678;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] ns = {1, 2, 2, 1, 1, 10};
        int[] ts = {1, 10, 1, 1, 1, 60};
        int[] ms = {5, 2, 2, 5, 1, 45};
        String[][] timetables = {
            {"08:00", "08:01", "08:02", "08:03"},
            {"09:10", "09:09", "08:00"},
            {"09:00", "09:00", "09:00", "09:00"},
            {"00:01", "00:01", "00:01", "00:01", "00:01"},
            {"23:59"},
            {"23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59"}
        };
        String[] answers = {"09:00", "09:09", "08:59", "00:00", "09:00", "18:00"};
        for (int i = 0; i < ns.length; i++) {
            System.out.println(s.solution(ns[i], ts[i], ms[i], timetables[i]).equals(answers[i]));
        }
    }

    public String solution(int n, int t, int m, String[] timetable) {
        int[] arrivals = new int[timetable.length];
        for (int i = 0; i < timetable.length; i++) {
            arrivals[i] = toMinutes(timetable[i]);
        }
        Arrays.sort(arrivals);
        int idx = 0;
        int busTime = 9 * 60;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            busTime = 9 * 60 + i * t;
            cnt = 0;
            while (idx < arrivals.length && arrivals[idx] <= busTime && cnt < m) {
                idx++;
                cnt++;
            }
        }
        int res;
        if (cnt < m) res = busTime;
        else res = arrivals[idx - 1] - 1;
        return toTimeString(res);
    }

    public int toMinutes(String time) {
        int hour = Integer.parseInt(time.substring(0, 2));
        int min = Integer.parseInt(time.substring(3, 5));
        return hour * 60 + min;
    }

    public String toTimeString(int min) {
        return String.format("%02d:%02d", min / 60, min % 60);
    }
}
