package programmers.implementation.p17684;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();

        String[] inputs = {"KAKAO", "TOBEORNOTTOBEORTOBEORNOT", "ABABABABABABABAB"};
        int[][] answers = {
                {11, 1, 27, 15},
                {20, 15, 2, 5, 15, 18, 14, 15, 20, 27, 29, 31, 36, 30, 32, 34},
                {1, 2, 27, 29, 28, 31, 30}
        };

        for (int i = 0; i < inputs.length; i++) {
            System.out.println(Arrays.equals(s.solution(inputs[i]), answers[i]));
        }
    }

    public int[] solution(String msg) {
        Map<String, Integer> dict = new HashMap<>();
        for (char c = 'A'; c <= 'Z'; c++) {
            dict.put(String.valueOf(c), c - 'A' + 1);
        }
        int idx = 27, s = 0;
        List<Integer> resList = new ArrayList<>();
        for (int e = 1; e <= msg.length(); e++) {
            if (e != msg.length() && dict.containsKey(msg.substring(s, e + 1))) continue;
            resList.add(dict.get(msg.substring(s, e)));
            if (e < msg.length()) dict.put(msg.substring(s, e + 1), idx++);
            s = e;
        }
        int[] res = new int[resList.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = resList.get(i);
        }
        return res;
    }
}