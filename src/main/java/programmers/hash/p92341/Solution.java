package programmers.hash.p92341;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] fees = {
                {180, 5000, 10, 600},
                {120, 0, 60, 591},
                {1, 461, 1, 10},
        };
        String[][] records = {
                {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"},
                {"16:00 3961 IN", "16:00 0202 IN", "18:00 3961 OUT", "18:00 0202 OUT", "23:58 3961 IN"},
                {"00:00 1234 IN"},
        };
        int[][] answers = {
                {14600, 34400, 5000},
                {0, 591},
                {14841},
        };
        for (int i = 0; i < fees.length; i++) {
            System.out.println(Arrays.equals(s.solution(fees[i], records[i]), answers[i]));
        }
    }

    public int[] solution(int[] fees, String[] records) {
        Map<String, Integer> map = new TreeMap<>();
        for (String record : records) {
            String[] bits = record.split(" ");
            int t = toMin(bits[0]);
            String key = bits[1];
            int val = map.getOrDefault(key, 0);
            if (bits[2].equals("IN")) map.put(key, val - t);
            else map.put(key, val + t);
        }
        int[] res = new int[map.size()];
        int idx = 0;
        for (String key : map.keySet()) {
            int t = map.get(key);
            if (t <= 0) t += 1439; // 23:59
            res[idx++] = calFee(t, fees);
        }
        return res;
    }

    public int toMin(String bit) {
        return Integer.parseInt(bit.substring(0, 2)) * 60 + Integer.parseInt(bit.substring(3));
    }

    public int calFee(int t, int[] fees) {
        return fees[1] + (int) Math.ceil((double) Math.max(0, (t - fees[0])) / fees[2]) * fees[3];
    }
}