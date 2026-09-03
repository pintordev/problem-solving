package programmers.implementation.p60061;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();

        int[] ns = {5, 5};
        int[][][] build_frames = {
                {{1, 0, 0, 1}, {1, 1, 1, 1}, {2, 1, 0, 1}, {2, 2, 1, 1}, {5, 0, 0, 1}, {5, 1, 0, 1}, {4, 2, 1, 1}, {3, 2, 1, 1}},
                {{0, 0, 0, 1}, {2, 0, 0, 1}, {4, 0, 0, 1}, {0, 1, 1, 1}, {1, 1, 1, 1}, {2, 1, 1, 1}, {3, 1, 1, 1}, {2, 0, 0, 0}, {1, 1, 1, 0}, {2, 2, 0, 1}}
        };
        int[][][] answers = {
                {{1, 0, 0}, {1, 1, 1}, {2, 1, 0}, {2, 2, 1}, {3, 2, 1}, {4, 2, 1}, {5, 0, 0}, {5, 1, 0}},
                {{0, 0, 0}, {0, 1, 1}, {1, 1, 1}, {2, 1, 1}, {3, 1, 1}, {4, 0, 0}}
        };

        for (int i = 0; i < ns.length; i++) {
            System.out.println(Arrays.deepEquals(s.solution(ns[i], build_frames[i]), answers[i]));
        }
    }

    int n;
    boolean[][][] built;

    public int[][] solution(int n, int[][] build_frame) {
        this.n = n;
        built = new boolean[n + 1][n + 1][2];
        for (int[] cmd : build_frame) {
            int x = cmd[0], y = cmd[1], a = cmd[2], b = cmd[3];
            if (b == 1) {
                built[x][y][a] = true;
                if (!canBuild(x, y, a)) built[x][y][a] = false;
            } else {
                built[x][y][a] = false;
                if (!isValid()) built[x][y][a] = true;
            }
        }
        List<int[]> res = new ArrayList<>();
        for (int x = 0; x <= n; x++) {
            for (int y = 0; y <= n; y++) {
                for (int a = 0; a < 2; a++) {
                    if (built[x][y][a]) res.add(new int[]{x, y, a});
                }
            }
        }
        return res.toArray(new int[0][]);
    }

    public boolean isValid() {
        for (int x = 0; x <= n; x++) {
            for (int y = 0; y <= n; y++) {
                for (int a = 0; a < 2; a++) {
                    if (built[x][y][a] && !canBuild(x, y, a)) return false;
                }
            }
        }
        return true;
    }

    public boolean canBuild(int x, int y, int a) {
        if (a == 0) return y == 0 || isBuilt(x, y - 1, 0) || isBuilt(x - 1, y, 1) || isBuilt(x, y, 1);
        return isBuilt(x, y - 1, 0) || isBuilt(x + 1, y - 1, 0) || (isBuilt(x - 1, y, 1) && isBuilt(x + 1, y, 1));
    }

    public boolean isBuilt(int x, int y, int a) {
        if (x < 0 || y < 0 || x > n || y > n) return false;
        return built[x][y][a];
    }
}
