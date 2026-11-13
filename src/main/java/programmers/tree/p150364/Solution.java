package programmers.tree.p150364;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][][] edgess = {
                {{2, 4}, {1, 2}, {6, 8}, {1, 3}, {5, 7}, {2, 5}, {3, 6}, {6, 10}, {6, 9}},
                {{1, 2}, {1, 3}},
                {{1, 3}, {1, 2}}
        };
        int[][] targets = {
                {0, 0, 0, 3, 0, 0, 5, 1, 2, 3},
                {0, 7, 3},
                {0, 7, 1}
        };
        int[][] answers = {
                {1, 1, 2, 2, 2, 3, 3},
                {1, 1, 3, 2, 3},
                {-1}
        };
        for (int i = 0; i < edgess.length; i++) {
            System.out.println(Arrays.equals(s.solution(edgess[i], targets[i]), answers[i]));
        }
    }

    int n;
    List<Integer>[] children;
    int[] cnt;

    public int[] solution(int[][] edges, int[] target) {
        n = target.length;
        children = new List[n + 1];
        cnt = new int[n + 1];
        int[] min = new int[n + 1];
        boolean[] made = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            children[i] = new ArrayList<>();
            min[i] = (target[i - 1] + 2) / 3;
        }
        for (int[] edge : edges) {
            children[edge[0]].add(edge[1]);
        }
        int leafCnt = 0;
        for (int i = 1; i <= n; i++) {
            Collections.sort(children[i]);
            if (children[i].isEmpty() && target[i - 1] > 0) leafCnt++;
        }
        List<Integer> order = new ArrayList<>();
        while (leafCnt > 0) {
            int leaf = drop();
            if (cnt[leaf] > target[leaf - 1]) return new int[]{-1};
            if (!made[leaf] && cnt[leaf] >= min[leaf]) {
                made[leaf] = true;
                leafCnt--;
            }
            order.add(leaf);
        }
        int[] res = new int[order.size()];
        for (int i = res.length - 1; i >= 0; i--) {
            int leaf = order.get(i);
            for (int j = 3; j >= 1; j--) {
                if (target[leaf - 1] - j < cnt[leaf] - 1) continue;
                target[leaf - 1] -= j;
                cnt[leaf]--;
                res[i] = j;
                break;
            }
        }
        return res;
    }

    public int drop() {
        int u = 1;
        cnt[u]++;
        while (!children[u].isEmpty()) {
            u = children[u].get((cnt[u] - 1) % children[u].size());
            cnt[u]++;
        }
        return u;
    }
}
