package programmers.implementation.p49994;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        String[] inputs = {"ULURRDLLU", "LULLLLLLU"};
        int[] answers = {7, 7};
        for (int i = 0; i < inputs.length; i++) {
            System.out.println(s.solution(inputs[i]) == answers[i]);
        }
    }

    public int solution(String dirs) {
        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};
        boolean[][][] visited = new boolean[2][11][11];
        int r = 5, c = 5, cnt = 0;
        for (int i = 0; i < dirs.length(); i++) {
            int d = "UDRL".indexOf(dirs.charAt(i));
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (!isAvailable(nr, nc)) continue;
            int minR = Math.min(r, nr), minC = Math.min(c, nc);
            r = nr;
            c = nc;
            if (visited[d >> 1][minR][minC]) continue;
            visited[d >> 1][minR][minC] = true;
            cnt++;
        }
        return cnt;
    }

    public boolean isAvailable(int r, int c) {
        return r >= 0 && r < 11 && c >= 0 && c < 11;
    }
}