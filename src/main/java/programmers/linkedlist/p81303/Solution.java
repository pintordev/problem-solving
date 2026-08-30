package programmers.linkedlist.p81303;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] ns = {8, 8};
        int[] ks = {2, 2};
        String[][] cmds = {
                {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"},
                {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C"}
        };
        String[] answers = {"OOOOXOOO", "OOXOXOOO"};
        for (int i = 0; i < ns.length; i++) {
            System.out.println(s.solution(ns[i], ks[i], cmds[i]).equals(answers[i]));
        }
    }

    public String solution(int n, int k, String[] cmd) {
        int[] prev = new int[n];
        int[] next = new int[n];
        boolean[] deleted = new boolean[n];
        for (int i = 0; i < n; i++) {
            prev[i] = i - 1;
            next[i] = i + 1;
        }
        Deque<Integer> deletedStack = new ArrayDeque<>();
        int cur = k;
        for (String c : cmd) {
            char op = c.charAt(0);
            switch (op) {
                case 'U': {
                    int x = Integer.parseInt(c.substring(2));
                    for (int i = 0; i < x; i++) cur = prev[cur];
                    break;
                }
                case 'D': {
                    int x = Integer.parseInt(c.substring(2));
                    for (int i = 0; i < x; i++) cur = next[cur];
                    break;
                }
                case 'C': {
                    deletedStack.push(cur);
                    deleted[cur] = true;
                    int p = prev[cur];
                    int nx = next[cur];
                    if (p != -1) next[p] = nx;
                    if (nx != n) prev[nx] = p;
                    cur = (nx != n) ? nx : p;
                    break;
                }
                case 'Z': {
                    int r = deletedStack.pop();
                    deleted[r] = false;
                    int p = prev[r];
                    int nx = next[r];
                    if (p != -1) next[p] = r;
                    if (nx != n) prev[nx] = r;
                    break;
                }
            }
        }
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) sb.append(deleted[i] ? 'X' : 'O');
        return sb.toString();
    }
}
