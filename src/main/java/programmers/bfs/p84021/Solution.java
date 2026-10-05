package programmers.bfs.p84021;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][][] game_boards = {
                {{1, 1, 0, 0, 1, 0}, {0, 0, 1, 0, 1, 0}, {0, 1, 1, 0, 0, 1}, {1, 1, 0, 1, 1, 1}, {1, 0, 0, 0, 1, 0}, {0, 1, 1, 1, 0, 0}},
                {{0, 0, 0}, {1, 1, 0}, {1, 1, 1}}
        };
        int[][][] tables = {
                {{1, 0, 0, 1, 1, 0}, {1, 0, 1, 0, 1, 0}, {0, 1, 1, 0, 1, 1}, {0, 0, 1, 0, 0, 0}, {1, 1, 0, 1, 1, 0}, {0, 1, 0, 0, 0, 0}},
                {{1, 1, 1}, {1, 0, 0}, {0, 0, 0}}
        };
        int[] answers = {14, 0};
        for (int i = 0; i < game_boards.length; i++) {
            System.out.println(s.solution(game_boards[i], tables[i]) == answers[i]);
        }
    }

    int n;
    int[][] grid;
    boolean[][] visited;
    int[] dr = {-1, 0, 1, 0};
    int[] dc = {0, 1, 0, -1};

    public int solution(int[][] game_board, int[][] table) {
        n = game_board.length;
        List<Block> blanks = blocks(game_board, 0);
        List<Block> pieces = blocks(table, 1);
        boolean[] used = new boolean[pieces.size()];
        int res = 0;
        for (Block blank : blanks) {
            for (int i = 0; i < pieces.size(); i++) {
                if (used[i] || pieces.get(i).size() != blank.size()) continue;
                if (!blank.matched(pieces.get(i))) continue;
                used[i] = true;
                res += blank.size();
                break;
            }
        }
        return res;
    }

    public List<Block> blocks(int[][] grid, int target) {
        this.grid = grid;
        visited = new boolean[n][n];
        List<Block> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!isAvailable(i, j, target)) continue;
                list.add(bfs(i, j, target));
            }
        }
        return list;
    }

    public Block bfs(int sr, int sc, int target) {
        List<Node> nodes = new ArrayList<>();
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(sr, sc));
        visited[sr][sc] = true;
        nodes.add(new Node(0, 0));
        while (!q.isEmpty()) {
            Node cur = q.poll();
            for (int i = 0; i < dr.length; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                if (!isAvailable(nr, nc, target)) continue;
                q.add(new Node(nr, nc));
                visited[nr][nc] = true;
                nodes.add(new Node(nr - sr, nc - sc));
            }
        }
        return new Block(nodes);
    }

    public boolean isAvailable(int r, int c, int target) {
        if (r < 0 || r >= n || c < 0 || c >= n) return false;
        if (visited[r][c]) return false;
        return grid[r][c] == target;
    }
}

class Block {
    List<Node> nodes;

    Block(List<Node> nodes) {
        Collections.sort(nodes);
        this.nodes = nodes;
    }

    public int size() {
        return nodes.size();
    }

    public boolean matched(Block o) {
        if (equals(o)) return true;
        for (int i = 0; i < 3; i++) {
            rotate();
            if (equals(o)) return true;
        }
        return false;
    }

    public void rotate() {
        for (Node node : nodes) {
            int temp = node.r;
            node.r = node.c;
            node.c = -temp;
        }
        Collections.sort(nodes);
        int sr = nodes.get(0).r;
        int sc = nodes.get(0).c;
        for (Node node : nodes) {
            node.r -= sr;
            node.c -= sc;
        }
    }

    public boolean equals(Block o) {
        for (int i = 0; i < nodes.size(); i++) {
            if (!nodes.get(i).equals(o.nodes.get(i))) return false;
        }
        return true;
    }
}

class Node implements Comparable<Node> {
    int r, c;

    Node(int r, int c) {
        this.r = r;
        this.c = c;
    }

    @Override
    public int compareTo(Node o) {
        if (r == o.r) return c - o.c;
        return r - o.r;
    }

    public boolean equals(Node o) {
        return r == o.r && c == o.c;
    }
}
