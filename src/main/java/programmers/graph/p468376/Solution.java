package programmers.graph.p468376;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][][] citys = {
                {{-1, 3}, {7, 3}, {1, -1}, {-2, 6}},
                {{3, 5}, {3, 3}, {2, 1}, {9, 1}, {7, -1}}
        };
        int[][][] roads = {
                {{-1, 7, 7, 7, 80}, {-3, 3, 9, 3, 45}, {-2, -4, -2, 6, 60}, {1, -4, 1, 8, 50}, {5, 1, 5, 7, 70}},
                {{3, -2, 3, 4, 30}, {5, 1, 9, 1, 29}, {3, 4, 3, 8, 99}, {1, 1, 5, 1, 99}, {7, -3, 7, 5, 99}}
        };
        int[][] answers = {
                {70, 50, 0},
                {0, 30, 29, 29}
        };
        for (int i = 0; i < citys.length; i++) {
            System.out.println(Arrays.equals(s.solution(citys[i], roads[i]), answers[i]));
        }
    }

    int INF = Integer.MAX_VALUE;
    int[] parent;
    int[] cell;
    int[] res;

    public int[] solution(int[][] city, int[][] road) {
        TreeSet<Integer>[] sets = new TreeSet[]{new TreeSet<>(), new TreeSet<>()};
        for (int[] ct : city) {
            sets[0].add(ct[0]);
            sets[1].add(ct[1]);
        }
        for (int[] rd : road) {
            for (int k = 0; k < 2; k++) {
                sets[k].add(rd[k]);
                sets[k].add(rd[k + 2]);
                sets[k].add((rd[k] + rd[k + 2]) / 2);
            }
        }
        int[] xs = sets[0].stream().mapToInt(Integer::intValue).toArray();
        int[] ys = sets[1].stream().mapToInt(Integer::intValue).toArray();
        int cols = ys.length;
        int[] limit = new int[xs.length * cols];
        Arrays.fill(limit, INF);
        for (int[] rd : road) {
            int mid = Arrays.binarySearch(xs, (rd[0] + rd[2]) / 2) * cols + Arrays.binarySearch(ys, (rd[1] + rd[3]) / 2);
            limit[mid] = Math.min(limit[mid], rd[4]);
        }
        parent = new int[limit.length];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        List<int[]> edges = new ArrayList<>();
        for (int[] rd : road) {
            int start = Arrays.binarySearch(xs, rd[0]) * cols + Arrays.binarySearch(ys, rd[1]);
            int end = Arrays.binarySearch(xs, rd[2]) * cols + Arrays.binarySearch(ys, rd[3]);
            int step = rd[0] == rd[2] ? 1 : cols;
            for (int a = start; a < end; a += step) {
                int w = Math.min(limit[a], limit[a + step]);
                if (w == INF) union(a + step, a);
                else edges.add(new int[]{a, a + step, w});
            }
        }
        cell = new int[city.length];
        for (int i = 0; i < city.length; i++) {
            cell[i] = Arrays.binarySearch(xs, city[i][0]) * cols + Arrays.binarySearch(ys, city[i][1]);
        }
        res = new int[city.length - 1];
        Arrays.fill(res, -1);
        check(0);
        edges.sort((a, b) -> b[2] - a[2]);
        for (int[] edge : edges) {
            union(edge[0], edge[1]);
            check(edge[2]);
        }
        return res;
    }

    public void check(int w) {
        for (int i = 1; i < cell.length; i++) {
            if (res[i - 1] != -1 || find(cell[i]) != find(cell[0])) continue;
            res[i - 1] = w;
        }
    }

    public int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    public void union(int a, int b) {
        parent[find(a)] = find(b);
    }
}
