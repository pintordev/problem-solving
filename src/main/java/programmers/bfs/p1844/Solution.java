package programmers.bfs.p1844;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][][] maps = {
            {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}},
            {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,0},{0,0,0,0,1}}
        };
        int[] answers = {11, -1};
        for (int i = 0; i < maps.length; i++) {
            System.out.println(s.solution(maps[i]) == answers[i]);
        }
    }

    public int[][] map;
    public int[] dr = {1, -1, 0, 0};
    public int[] dc = {0, 0, 1, -1};
    public int er;
    public int ec;

    public int solution(int[][] maps) {
        map = maps;
        er = maps.length;
        ec = maps[0].length;

        Queue<Node> q= new ArrayDeque<>();
        q.add(new Node(0, 0));
        map[0][0]++;
        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (cur.r == er - 1 && cur.c == ec - 1) return map[cur.r][cur.c] - 1;
            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                if (!isAvailable(nr, nc)) continue;
                q.add(new Node(nr, nc));
                map[nr][nc] += map[cur.r][cur.c];
            }
        }
        return -1;
    }

    public boolean isAvailable(int nr, int nc) {
        if (nr < 0 || nr >= er || nc < 0 || nc >= ec) return false;
        if (map[nr][nc] != 1) return false;
        return true;
    }
}

class Node {
    int r;
    int c;

    Node(int r, int c) {
        this.r = r;
        this.c = c;
    }
}