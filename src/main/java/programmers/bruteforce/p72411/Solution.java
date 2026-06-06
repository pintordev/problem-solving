package programmers.bruteforce.p72411;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();

        String[][] orders = {
                {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"},
                {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"},
                {"XYZ", "XWY", "WXA"}
        };
        int[][] courses = {
                {2, 3, 4},
                {2, 3, 5},
                {2, 3, 4}
        };
        String[][] answers = {
                {"AC", "ACDE", "BCFG", "CDE"},
                {"ACD", "AD", "ADE", "CD", "XYZ"},
                {"WX", "XY"}
        };

        for (int i = 0; i < orders.length; i++) {
            System.out.println(Arrays.equals(s.solution(orders[i], courses[i]), answers[i]));
        }
    }

    Map<String, Integer> map;
    int[] course;

    public String[] solution(String[] orders, int[] course) {
        map = new HashMap<>();
        this.course = course;
        extractCourse(orders);
        String[] res = getRenewalMenu().toArray(new String[0]);
        Arrays.sort(res);
        return res;
    }

    public void extractCourse(String[] orders) {
        for (String order : orders) {
            char[] c = order.toCharArray();
            Arrays.sort(c);
            for (int k : course) {
                if (c.length < k) continue;
                comb(c, k);
            }
        }
    }

    public void comb(char[] c, int k) {
        for (int mask = 0; mask < (1 << c.length); mask++) {
            if (Integer.bitCount(mask) != k) continue;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < c.length; i++) if ((mask >> i & 1) == 1) sb.append(c[i]);
            String key = sb.toString();
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
    }

    public List<String> getRenewalMenu() {
        List<String> menu = new ArrayList<>();
        for (int k : course) {
            List<String> sub = new ArrayList<>();
            int max = 2;
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (entry.getKey().length() != k) continue;
                int cnt = entry.getValue();
                if (cnt < max) continue;
                if (cnt > max) { sub.clear(); max = cnt; }
                sub.add(entry.getKey());
            }
            menu.addAll(sub);
        }
        return menu;
    }
}