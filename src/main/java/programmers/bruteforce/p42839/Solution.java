package programmers.bruteforce.p42839;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        String[] inputs = {"17", "011"};
        int[] answers = {3, 2};
        for (int i = 0; i < inputs.length; i++) {
            System.out.println(s.solution(inputs[i]) == answers[i]);
        }
    }

    char[] chars;
    Set<Integer> candidates;
    StringBuilder sb;

    public int solution(String numbers) {
        chars = numbers.toCharArray();
        candidates = new HashSet<>();
        sb = new StringBuilder();
        permute(0);
        int cnt = 0;
        for (int n : candidates) {
            if (isPrime(n)) cnt++;
        }
        return cnt;
    }

    public void permute(int visited) {
        if (sb.length() > 0) candidates.add(Integer.parseInt(sb.toString()));
        for (int i = 0; i < chars.length; i++) {
            if ((visited & (1 << i)) != 0) continue;
            sb.append(chars[i]);
            permute(visited | (1 << i));
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}