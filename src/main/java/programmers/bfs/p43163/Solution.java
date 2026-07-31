package programmers.bfs.p43163;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {
    String[] words;
    boolean[] visited;

    public static void main(String[] args) {
        Solution s = new Solution();
        String[] begins = {"hit", "hit"};
        String[] targets = {"cog", "cog"};
        String[][] wordss = {
                {"hot", "dot", "dog", "lot", "log", "cog"},
                {"hot", "dot", "dog", "lot", "log"}
        };
        int[] answers = {4, 0};
        for (int i = 0; i < begins.length; i++) {
            System.out.println(s.solution(begins[i], targets[i], wordss[i]) == answers[i]);
        }
    }

    public int solution(String begin, String target, String[] words) {
        this.words = words;
        visited = new boolean[words.length];
        Queue<String> queue = new ArrayDeque<>();
        queue.add(begin);
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                if (cur.equals(target)) return step;
                for (int j = 0; j < words.length; j++) {
                    if (!isAvailable(cur, j)) continue;
                    visited[j] = true;
                    queue.add(words[j]);
                }
            }
            step++;
        }
        return 0;
    }

    public boolean isAvailable(String a, int j) {
        if (visited[j]) return false;
        String b = words[j];
        int cnt = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) cnt++;
        }
        return cnt == 1;
    }
}