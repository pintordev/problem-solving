package programmers.twopointer.p67258;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        String[][] gemss = {
                {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"},
                {"AA", "AB", "AC", "AA", "AC"},
                {"XYZ", "XYZ", "XYZ"},
                {"ZZZ", "YYY", "NNNN", "YYY", "BBB"}
        };
        int[][] answers = {
                {3, 7},
                {1, 3},
                {1, 1},
                {1, 5}
        };
        for (int i = 0; i < gemss.length; i++) {
            System.out.println(Arrays.equals(s.solution(gemss[i]), answers[i]));
        }
    }

    public int[] solution(String[] gems) {
        int k = new HashSet<>(Arrays.asList(gems)).size();
        Map<String, Integer> window = new HashMap<>();
        int distinct = 0;
        int left = 0;
        int bestLen = Integer.MAX_VALUE;
        int bestStart = 0, bestEnd = 0;
        for (int right = 0; right < gems.length; right++) {
            if (window.merge(gems[right], 1, Integer::sum) == 1) distinct++;
            while (distinct == k) {
                int len = right - left + 1;
                if (len < bestLen) {
                    bestLen = len;
                    bestStart = left;
                    bestEnd = right;
                }
                if (window.merge(gems[left], -1, Integer::sum) == 0) distinct--;
                left++;
            }
        }
        return new int[]{bestStart + 1, bestEnd + 1};
    }
}