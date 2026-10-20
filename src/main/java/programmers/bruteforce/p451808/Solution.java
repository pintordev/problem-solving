package programmers.bruteforce.p451808;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] ns = {3024, 3024, 33};
        int[] answers = {1357, 3986, 7685};
        for (int i = 0; i < ns.length; i++) {
            int password = answers[i];
            int[] count = {0};
            Function<Integer, String> submit = num -> {
                count[0]++;
                return clue(password, num);
            };
            int result = s.solution(ns[i], submit);
            System.out.println(result == answers[i] && count[0] <= ns[i]);
        }
    }

    public static String clue(int password, int num) {
        String p = String.valueOf(password);
        String q = String.valueOf(num);
        int strike = 0;
        int ball = 0;
        for (int i = 0; i < 4; i++) {
            if (q.charAt(i) == p.charAt(i)) strike++;
            else if (p.indexOf(q.charAt(i)) >= 0) ball++;
        }
        return strike + "S " + ball + "B";
    }

    public int solution(int n, Function<Integer, String> submit) {
        List<Integer> candidates = new ArrayList<>();
        for (int num = 1000; num <= 9999; num++) {
            if (valid(num)) candidates.add(num);
        }
        int q = 1123;
        while (true) {
            String clue = submit.apply(q);
            int code = (clue.charAt(0) - '0') * 5 + (clue.charAt(3) - '0');
            List<Integer> next = new ArrayList<>();
            for (int p : candidates) {
                if (score(p, q) == code) next.add(p);
            }
            candidates = next;
            if (candidates.size() == 1) return candidates.get(0);
            q = choose(candidates);
        }
    }

    public boolean valid(int num) {
        int mask = 0;
        for (int t = num; t > 0; t /= 10) {
            int d = t % 10;
            if (d == 0 || (mask >> d & 1) == 1) return false;
            mask |= 1 << d;
        }
        return true;
    }

    public int choose(List<Integer> candidates) {
        int res = 0;
        int minMax = Integer.MAX_VALUE;
        long minSum = Long.MAX_VALUE;
        for (int q = 1000; q <= 9999; q++) {
            int[] cnt = new int[25];
            for (int p : candidates) {
                cnt[score(p, q)]++;
            }
            int max = 0;
            long sum = 0;
            for (int c : cnt) {
                max = Math.max(max, c);
                sum += (long) c * c;
            }
            if (max > minMax || (max == minMax && sum >= minSum)) continue;
            minMax = max;
            minSum = sum;
            res = q;
        }
        return res;
    }

    public int score(int p, int q) {
        int mask = 0;
        for (int t = p; t > 0; t /= 10) {
            mask |= 1 << (t % 10);
        }
        int strike = 0;
        int ball = 0;
        for (int i = 0; i < 4; i++) {
            int pd = p % 10;
            int qd = q % 10;
            if (pd == qd) strike++;
            else if ((mask >> qd & 1) == 1) ball++;
            p /= 10;
            q /= 10;
        }
        return strike * 5 + ball;
    }
}
