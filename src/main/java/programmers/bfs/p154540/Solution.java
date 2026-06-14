package programmers.bfs.p154540;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        String[][] inputs = {
            {"X591X", "X1X5X", "X231X", "1XXX1"},
            {"XXX", "XXX", "XXX"}
        };
        int[][] answers = {
            {1, 1, 27},
            {-1}
        };
        for (int i = 0; i < inputs.length; i++) {
            System.out.println(Arrays.equals(s.solution(inputs[i]), answers[i]));
        }
    }

    String[] maps;
    int n, m;
    int[] dr = {-1, 0, 1, 0};
    int[] dc = {0, 1, 0, -1};
    boolean[][] visited;

    public int[] solution(String[] maps) {
        this.maps = maps;
        this.n = maps.length;
        this.m = maps[0].length();
        visited = new boolean[n][m];
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!isAvailable(i, j)) continue;
                list.add(bfs(i, j));
            }
        }
        if (list.isEmpty()) return new int[]{-1};
        Collections.sort(list);
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    public int bfs(int r, int c) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(r, c));
        visited[r][c] = true;
        int sum = maps[r].charAt(c) - '0';
        while (!q.isEmpty()) {
            Node cur = q.poll();
            for (int i = 0; i < dr.length; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                if (!isAvailable(nr, nc)) continue;
                q.add(new Node(nr, nc));
                visited[nr][nc] = true;
                sum += maps[nr].charAt(nc) - '0';
            }
        }
        return sum;
    }

    public boolean isAvailable(int nr, int nc) {
        if (nr < 0 || nr >= n || nc < 0 || nc >= m) return false;
        if (maps[nr].charAt(nc) == 'X') return false;
        if (visited[nr][nc]) return false;
        return true;
    }
}

class Node {
    int r, c;
    public Node(int r, int c) {
        this.r = r;
        this.c = c;
    }
}