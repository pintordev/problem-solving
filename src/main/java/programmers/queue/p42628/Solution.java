package programmers.queue.p42628;

import java.util.Arrays;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        String[][] operationss = {
            {"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"},
            {"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"}
        };
        int[][] answers = {
            {0, 0},
            {333, -45}
        };
        for (int i = 0; i < operationss.length; i++) {
            System.out.println(Arrays.equals(s.solution(operationss[i]), answers[i]));
        }
    }

    public int[] solution(String[] operations) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (String op : operations) {
            String[] tokens = op.split(" ");
            String cmd = tokens[0];
            int num = Integer.parseInt(tokens[1]);
            if (cmd.equals("I")) {
                map.merge(num, 1, Integer::sum);
                continue;
            }
            if (map.isEmpty()) continue;
            int key = num == 1 ? map.lastKey() : map.firstKey();
            int cnt = map.get(key) - 1;
            map.put(key, cnt);
            if (cnt == 0) {
                map.remove(key);
            }
        }
        if (map.isEmpty()) return new int[]{0, 0};
        return new int[]{map.lastKey(), map.firstKey()};
    }
}
