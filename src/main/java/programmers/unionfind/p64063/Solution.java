package programmers.unionfind.p64063;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        long[] ks = {10};
        long[][] room_numbers = {{1, 3, 4, 1, 3, 1}};
        long[][] answers = {{1, 3, 4, 2, 5, 6}};
        for (int i = 0; i < ks.length; i++) {
            System.out.println(Arrays.equals(s.solution(ks[i], room_numbers[i]), answers[i]));
        }
    }

    Map<Long, Long> parent;

    public long[] solution(long k, long[] room_number) {
        parent = new HashMap<>();
        long[] res = new long[room_number.length];
        for (int i = 0; i < room_number.length; i++) {
            res[i] = reserve(room_number[i]);
        }
        return res;
    }

    public long reserve(long x) {
        Long next = parent.putIfAbsent(x, x + 1);
        if (next == null) return x;
        long room = next;
        while (true) {
            Long n = parent.putIfAbsent(room, room + 1);
            if (n == null) break;
            room = n;
        }
        while (x != room) {
            long n = parent.get(x);
            parent.put(x, room + 1);
            x = n;
        }
        return room;
    }
}
