package programmers.bfs.p87694;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][][] rectangles = {
            {{1, 1, 7, 4}, {3, 2, 5, 5}, {4, 3, 6, 9}, {2, 6, 8, 8}},
            {{1, 1, 8, 4}, {2, 2, 4, 9}, {3, 6, 9, 8}, {6, 3, 7, 7}},
            {{1, 1, 5, 7}},
            {{2, 1, 7, 5}, {6, 4, 10, 10}},
            {{2, 2, 5, 5}, {1, 3, 6, 4}, {3, 1, 4, 6}}
        };
        int[] characterXs = {1, 9, 1, 3, 1};
        int[] characterYs = {3, 7, 1, 1, 4};
        int[] itemXs = {7, 6, 4, 7, 6};
        int[] itemYs = {8, 1, 7, 10, 3};
        int[] answers = {17, 11, 9, 15, 10};
        for (int i = 0; i < rectangles.length; i++) {
            System.out.println(s.solution(rectangles[i], characterXs[i], characterYs[i], itemXs[i], itemYs[i]) == answers[i]);
        }
    }

    int[][] board;
    boolean[][] visited;
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        board = new int[101][101];
        for (int[] rec : rectangle) {
            merge(rec);
        }
        return bfs(characterX * 2, characterY * 2, itemX * 2, itemY * 2) / 2;
    }

    public void merge(int[] rec) {
        int r1 = rec[0] * 2, c1 = rec[1] * 2, r2 = rec[2] * 2, c2 = rec[3] * 2;
        for (int r = r1; r <= r2; r++) {
            for (int c = c1; c <= c2; c++) {
                if (board[r][c] == 2) continue;
                board[r][c] = (r == r1 || r == r2 || c == c1 || c == c2) ? 1 : 2;
            }
        }
    }

    public int bfs(int sr, int sc, int er, int ec) {
        visited = new boolean[101][101];
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(sr, sc, 0));
        visited[sr][sc] = true;
        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (cur.r == er && cur.c == ec) return cur.dist;
            for (int i = 0; i < dr.length; i++) {
                int nr = cur.r + dr[i], nc = cur.c + dc[i];
                if (!isAvailable(nr, nc)) continue;
                visited[nr][nc] = true;
                q.add(new Node(nr, nc, cur.dist + 1));
            }
        }
        return -1;
    }

    public boolean isAvailable(int r, int c) {
        if (r < 0 || r >= 101 || c < 0 || c >= 101) return false;
        return board[r][c] == 1 && !visited[r][c];
    }
}

class Node {
    int r, c, dist;

    public Node(int r, int c, int dist) {
        this.r = r;
        this.c = c;
        this.dist = dist;
    }
}
