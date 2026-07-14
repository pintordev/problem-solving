package programmers.math.p87377;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][][] lines = {
                {{2, -1, 4}, {-2, -1, 4}, {0, -1, 1}, {5, -8, -12}, {5, 8, 12}},
                {{0, 1, -1}, {1, 0, -1}, {1, 0, 1}},
                {{1, -1, 0}, {2, -1, 0}},
                {{1, -1, 0}, {2, -1, 0}, {4, -1, 0}},
        };
        String[][] answers = {
                {"....*....", ".........", ".........", "*.......*", ".........", ".........", ".........", ".........", "*.......*"},
                {"*.*"},
                {"*"},
                {"*"},
        };
        for (int i = 0; i < lines.length; i++) {
            System.out.println(Arrays.equals(s.solution(lines[i]), answers[i]));
        }
    }

    public String[] solution(int[][] line) {
        Set<Point> points = new HashSet<>();
        long minX = Long.MAX_VALUE, maxX = Long.MIN_VALUE;
        long minY = Long.MAX_VALUE, maxY = Long.MIN_VALUE;
        for (int i = 0, n = line.length; i < n; i++) {
            long a1 = line[i][0], b1 = line[i][1], c1 = line[i][2];
            for (int j = i + 1; j < n; j++) {
                long a2 = line[j][0], b2 = line[j][1], c2 = line[j][2];
                long det = a1 * b2 - a2 * b1;
                if (det == 0) continue;
                long xNum = b1 * c2 - b2 * c1;
                long yNum = a2 * c1 - a1 * c2;
                if (xNum % det != 0 || yNum % det != 0) continue;
                long x = xNum / det;
                long y = yNum / det;
                if (!points.add(new Point(x, y))) continue;
                minX = Math.min(minX, x);
                maxX = Math.max(maxX, x);
                minY = Math.min(minY, y);
                maxY = Math.max(maxY, y);
            }
        }
        int w = (int) (maxX - minX + 1);
        int h = (int) (maxY - minY + 1);
        char[][] grid = new char[h][w];
        for (char[] row : grid) {
            Arrays.fill(row, '.');
        }
        for (Point p : points) {
            int row = (int) (maxY - p.y);
            int col = (int) (p.x - minX);
            grid[row][col] = '*';
        }
        String[] res = new String[h];
        for (int i = 0; i < h; i++) {
            res[i] = new String(grid[i]);
        }
        return res;
    }
}

class Point {
    long x, y;

    Point(long x, long y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Point p)) return false;
        return x == p.x && y == p.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}