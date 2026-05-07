package programmers.implementation.p131127;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();

        String[][] wants = {
                {"banana", "apple", "rice", "pork", "pot"},
                {"apple"}
        };
        int[][] numbers = {
                {3, 2, 2, 2, 1},
                {10}
        };
        String[][] discounts = {
                {"chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice", "pot", "banana", "apple", "banana"},
                {"banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana"}
        };
        int[] answers = {3, 0};

        for (int i = 0; i < wants.length; i++) {
            System.out.println(s.solution(wants[i], numbers[i], discounts[i]) == answers[i]);
        }
    }

    public int solution(String[] want, int[] number, String[] discount) {
        Map<String, Integer> map = new HashMap<>();
        int d;
        for (d = 0; d < 9; d++) {
            map.put(discount[d], map.getOrDefault(discount[d], 0) + 1);
        }

        int cnt = 0;
        for (; d < discount.length; d++) {
            map.put(discount[d], map.getOrDefault(discount[d], 0) + 1);
            if (isAllDiscounted(want, number, map)) cnt++;
            String out = discount[d - 9];
            if (map.get(out) == 1) map.remove(out);
            else map.put(out, map.get(out) - 1);
        }
        return cnt;
    }

    public boolean isAllDiscounted(String[] want, int[] number, Map<String, Integer> map) {
        for (int i = 0; i < want.length; i++) {
            if (!map.containsKey(want[i])) return false;
            if (map.get(want[i]) < number[i]) return false;
        }
        return true;
    }
}