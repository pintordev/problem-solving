package programmers.implementation.p86052;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        String[][] grids = {
                {"SL", "LR"},
                {"S"},
                {"R", "R"}
        };
        int[][] answers = {
                {16},
                {1, 1, 1, 1},
                {4, 4}
        };
        for (int i = 0; i < grids.length; i++) {
            System.out.println(Arrays.equals(s.solution(grids[i]), answers[i]));
        }
    }

    public int[] solution(String[] grid) {
        int n = grid.length;
        int m = grid[0].length();
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};
        boolean[][][] visited = new boolean[n][m][4];
        List<Integer> cycles = new ArrayList<>();
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                for (int d = 0; d < 4; d++) {
                    if (visited[r][c][d]) continue;
                    int cr = r, cc = c, cd = d, len = 0;
                    while (!visited[cr][cc][cd]) {
                        visited[cr][cc][cd] = true;
                        len++;
                        char dir = grid[cr].charAt(cc);
                        if (dir == 'L') cd = (cd + 3) % 4;
                        else if (dir == 'R') cd = (cd + 1) % 4;
                        cr = (cr + dr[cd] + n) % n;
                        cc = (cc + dc[cd] + m) % m;
                    }
                    cycles.add(len);
                }
            }
        }
        int[] res = new int[cycles.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = cycles.get(i);
        }
        Arrays.sort(res);
        return res;
    }
}