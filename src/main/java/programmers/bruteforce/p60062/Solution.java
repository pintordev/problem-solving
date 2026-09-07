package programmers.bruteforce.p60062;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] ns = {12, 12};
        int[][] weaks = {{1, 5, 6, 10}, {1, 3, 4, 9, 10}};
        int[][] dists = {{1, 2, 3, 4}, {3, 5, 7}};
        int[] answers = {2, 1};
        for (int i = 0; i < ns.length; i++) {
            System.out.println(s.solution(ns[i], weaks[i], dists[i]) == answers[i]);
        }
    }

    int[] weak;
    int[] dist;
    int[] extended;
    int minFriends;

    public int solution(int n, int[] weak, int[] dist) {
        this.weak = weak;
        this.dist = dist;
        extended = new int[weak.length * 2];
        for (int i = 0; i < weak.length; i++) {
            extended[i] = weak[i];
            extended[i + weak.length] = weak[i] + n;
        }
        minFriends = dist.length + 1;
        permute(0);
        return minFriends > dist.length ? -1 : minFriends;
    }

    public void permute(int depth) {
        if (depth == dist.length) {
            for (int start = 0; start < weak.length; start++) {
                minFriends = Math.min(minFriends, countFrom(start));
            }
            return;
        }
        for (int i = depth; i < dist.length; i++) {
            swap(depth, i);
            permute(depth + 1);
            swap(depth, i);
        }
    }

    public int countFrom(int start) {
        int used = 1;
        int covered = extended[start] + dist[0];
        for (int i = start + 1; i < start + weak.length; i++) {
            if (extended[i] <= covered) continue;
            if (used == dist.length) return dist.length + 1;
            covered = extended[i] + dist[used];
            used++;
        }
        return used;
    }

    public void swap(int a, int b) {
        int tmp = dist[a];
        dist[a] = dist[b];
        dist[b] = tmp;
    }
}
