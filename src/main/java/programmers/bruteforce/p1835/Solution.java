package programmers.bruteforce.p1835;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] ns = {2, 2};
        String[][] datas = {
                {"N~F=0", "R~T>2"},
                {"M~C<2", "C~M>1"}
        };
        int[] answers = {3648, 0};
        for (int i = 0; i < ns.length; i++) {
            System.out.println(s.solution(ns[i], datas[i]) == answers[i]);
        }
    }

    char[] NAMES = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};

    String[] data;
    boolean[] used;
    int[] pos;
    int cnt;

    public int solution(int n, String[] data) {
        this.data = data;
        used = new boolean[8];
        pos = new int[8];
        cnt = 0;
        dfs(0);
        return cnt;
    }

    public void dfs(int depth) {
        if (depth == 8) {
            if (isValid()) cnt++;
            return;
        }
        for (int i = 0; i < 8; i++) {
            if (used[i]) continue;
            used[i] = true;
            pos[i] = depth;
            dfs(depth + 1);
            used[i] = false;
        }
    }

    public boolean isValid() {
        for (String d : data) {
            if (!check(d)) return false;
        }
        return true;
    }

    public boolean check(String d) {
        int i = idx(d.charAt(0));
        int j = idx(d.charAt(2));
        char op = d.charAt(3);
        int k = d.charAt(4) - '0';
        int gap = Math.abs(pos[i] - pos[j]) - 1;
        if (op == '=') return gap == k;
        if (op == '>') return gap > k;
        return gap < k;
    }

    public int idx(char c) {
        for (int i = 0; i < 8; i++) {
            if (NAMES[i] == c) return i;
        }
        return -1;
    }
}