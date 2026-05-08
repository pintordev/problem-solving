package programmers.hash.p42578;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();

        String[][][] clothesList = {
            {{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}},
            {{"crow_mask", "face"}, {"blue_sunglasses", "face"}, {"smoky_makeup", "face"}}
        };
        int[] answers = {5, 3};

        for (int i = 0; i < clothesList.length; i++) {
            System.out.println(s.solution(clothesList[i]) == answers[i]);
        }
    }

    public int solution(String[][] clothes) {
        Map<String, Integer> map = new HashMap<>();
        for (String[] clothe : clothes) {
            map.put(clothe[1], map.getOrDefault(clothe[1], 0) + 1);
        }

        int comb = 1;
        for (var value : map.values()) {
            comb *= (1 + value);
        }
        return comb - 1;
    }
}