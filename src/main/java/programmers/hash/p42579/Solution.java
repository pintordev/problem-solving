package programmers.hash.p42579;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();

        String[][] genress = {
            {"classic", "pop", "classic", "classic", "pop"}
        };
        int[][] playss = {
            {500, 600, 150, 800, 2500}
        };
        int[][] answers = {
            {4, 1, 3, 0}
        };

        for (int i = 0; i < genress.length; i++) {
            System.out.println(Arrays.equals(s.solution(genress[i], playss[i]), answers[i]));
        }
    }

    public int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> genMap = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            genMap.put(genres[i], genMap.getOrDefault(genres[i], 0) + plays[i]);
        }

        int[][] info = new int[genres.length][3];
        for (int i = 0; i < genres.length; i++) {
            info[i] = new int[] {genMap.get(genres[i]), i, plays[i]};
        }

        Arrays.sort(info, (a, b) -> {
            if (a[0] == b[0] && a[2] == b[2]) return a[1] - b[1];
            if (a[0] == b[0]) return b[2] - a[2];
            return b[0] - a[0];
        });

        int[] res = new int[genres.length];
        int idx = 0, now = 0, cnt = 0;
        for (int[] song : info) {
            cnt++;
            if (song[0] != now) {
                now = song[0];
                cnt = 1;
            }
            if (cnt <= 2) res[idx++] = song[1];
        }
        return Arrays.copyOf(res, idx);
    }
}