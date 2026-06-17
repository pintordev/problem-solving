package programmers.bfs.p81302;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();

        String[][] places = {
            {"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
            {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
            {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
            {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
            {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}
        };
        int[] answer = {1, 0, 1, 1, 1};

        System.out.println(Arrays.equals(s.solution(places), answer));
    }

    public int[] solution(String[][] places) {
        int[] res = new int[5];
        for (int i = 0; i < 5; i++) {
            res[i] = hasKeptDistance(places[i]);
        }
        return res;
    }

    public int hasKeptDistance(String[] place) {
        int[] dr = {0, 0, 1, 1, 1, 2};
        int[] dc = {1, 2, -1, 0, 1, 0};
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (place[i].charAt(j) != 'P') continue;
                for (int d = 0; d < dr.length; d++) {
                    if (!isAvailable(i + dr[d], j + dc[d])) continue;
                    if (!judge(place, i, j, dr[d], dc[d])) return 0;
                }
            }
        }
        return 1;
    }

    public boolean isAvailable(int r, int c) {
        return r >= 0 && r < 5 && c >= 0 && c < 5;
    }

    public boolean judge(String[] place, int r, int c, int dr, int dc) {
        if (place[r + dr].charAt(c + dc) != 'P') return true;
        if (Math.abs(dr) == 1 && Math.abs(dc) == 1) return place[r + dr].charAt(c) == 'X' && place[r].charAt(c + dc) == 'X';
        else if (Math.abs(dr) == 2 || Math.abs(dc) == 2) return place[r + dr / 2].charAt(c + dc / 2) == 'X';
        return false;
    }
}