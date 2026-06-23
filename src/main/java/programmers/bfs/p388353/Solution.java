package programmers.bfs.p388353;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();

        String[][] storages = {
                {"AZWQY", "CAABX", "BBDDA", "ACACA"},
                {"HAH", "HBH", "HHH", "HAH", "HBH"}
        };
        String[][] requests = {
                {"A", "BB", "A"},
                {"C", "B", "B", "B", "B", "H"}
        };
        int[] answers = {11, 4};

        for (int i = 0; i < storages.length; i++) {
            System.out.println(s.solution(storages[i], requests[i]) == answers[i]);
        }
    }

    int n, m;
    char[][] grid;
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};
    boolean[][] visited;
    int cnt;

    public int solution(String[] storage, String[] requests) {
        n = storage.length + 2;
        m = storage[0].length() + 2;
        grid = new char[n][m];
        for (char[] row : grid) Arrays.fill(row, ' ');
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < m - 1; j++) {
                grid[i][j] = storage[i - 1].charAt(j - 1);
            }
        }
        cnt = (n - 2) * (m - 2);
        for (String req : requests) {
            char target = req.charAt(0);
            if (req.length() == 2) crane(target);
            else forklift(target);
        }
        return cnt;
    }

    public void crane(char target) {
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < m - 1; j++) {
                if (grid[i][j] != target) continue;
                grid[i][j] = ' ';
                cnt--;
            }
        }
    }

    public void forklift(char target) {
        visited = new boolean[n][m];
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(0, 0));
        visited[0][0] = true;
        while (!q.isEmpty()) {
            Node cur = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                if (!isAvailable(nr, nc)) continue;
                visited[nr][nc] = true;
                if (grid[nr][nc] == ' ') q.add(new Node(nr, nc));
                else if (grid[nr][nc] == target) {
                    grid[nr][nc] = ' ';
                    cnt--;
                }
            }
        }
    }

    public boolean isAvailable(int nr, int nc) {
        if (nr < 0 || nr >= n || nc < 0 || nc >= m) return false;
        if (visited[nr][nc]) return false;
        return true;
    }
}

class Node {
    int r, c;

    Node(int r, int c) {
        this.r = r;
        this.c = c;
    }
}