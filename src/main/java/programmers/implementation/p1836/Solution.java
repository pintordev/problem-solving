package programmers.implementation.p1836;

import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] ms = {3, 2, 4, 2};
        int[] ns = {3, 4, 4, 2};
        String[][] boards = {
                {"DBA", "C*A", "CDB"},
                {"NRYN", "ARYA"},
                {".ZI.", "M.**", "MZU.", ".IU."},
                {"AB", "BA"}
        };
        String[] answers = {"ABCD", "RYAN", "MUZI", "IMPOSSIBLE"};
        for (int i = 0; i < ms.length; i++) {
            System.out.println(s.solution(ms[i], ns[i], boards[i]).equals(answers[i]));
        }
    }

    Map<Character, int[][]> coords;
    char[][] map;

    public String solution(int m, int n, String[] board) {
        coords = new TreeMap<>();
        map = new char[m][n];
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                map[r][c] = board[r].charAt(c);
                if (map[r][c] < 'A') continue;
                int[][] coord = coords.putIfAbsent(map[r][c], new int[][]{{r, c}, {r, c}});
                if (coord != null) coord[1] = new int[]{r, c};
            }
        }
        StringBuilder res = new StringBuilder();
        L:
        while (!coords.isEmpty()) {
            for (char key : coords.keySet()) {
                if (!canRemove(key)) continue;
                remove(key);
                res.append(key);
                continue L;
            }
            return "IMPOSSIBLE";
        }
        return res.toString();
    }

    public boolean canRemove(char key) {
        int[][] coord = coords.get(key);
        int r1 = coord[0][0];
        int c1 = coord[0][1];
        int r2 = coord[1][0];
        int c2 = coord[1][1];
        int minC = Math.min(c1, c2);
        int maxC = Math.max(c1, c2);
        if (r1 == r2) return checkH(minC, maxC, r1, key);
        if (c1 == c2) return checkV(r1, r2, c1, key);
        boolean u = checkH(minC, maxC, r1, key);
        boolean d = checkH(minC, maxC, r2, key);
        boolean l = checkV(r1, r2, minC, key);
        boolean r = checkV(r1, r2, maxC, key);
        if (c1 < c2) return (u && r) || (d && l);
        return (u && l) || (d && r);
    }

    public boolean checkH(int c1, int c2, int r, char key) {
        for (int c = c1; c <= c2; c++) {
            if (map[r][c] != '.' && map[r][c] != key) return false;
        }
        return true;
    }

    public boolean checkV(int r1, int r2, int c, char key) {
        for (int r = r1; r <= r2; r++) {
            if (map[r][c] != '.' && map[r][c] != key) return false;
        }
        return true;
    }

    public void remove(char key) {
        int[][] coord = coords.remove(key);
        map[coord[0][0]][coord[0][1]] = '.';
        map[coord[1][0]][coord[1][1]] = '.';
    }
}
