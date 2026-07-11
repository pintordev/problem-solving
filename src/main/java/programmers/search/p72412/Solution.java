package programmers.search.p72412;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        String[][] infos = {
            {"java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"}
        };
        String[][] querys = {
            {"java and backend and junior and pizza 100", "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150"}
        };
        int[][] answers = {
            {1, 1, 1, 1, 2, 4}
        };

        for (int i = 0; i < infos.length; i++) {
            System.out.println(Arrays.equals(s.solution(infos[i], querys[i]), answers[i]));
        }
    }

    public int[] solution(String[] info, String[] query) {
        Map<String, List<Integer>> map = new HashMap<>();
        for (String line : info) {
            String[] tokens = line.split(" ");
            String[] fields = {tokens[0], tokens[1], tokens[2], tokens[3]};
            int score = Integer.parseInt(tokens[4]);
            for (int mask = 0; mask < 16; mask++) {
                map.computeIfAbsent(genKey(fields, mask), k -> new ArrayList<>()).add(score);
            }
        }
        for (List<Integer> scores : map.values()) {
            Collections.sort(scores);
        }
        int[] answer = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            String[] tokens = query[i].split(" ");
            String key = tokens[0] + "/" + tokens[2] + "/" + tokens[4] + "/" + tokens[6] + "/";
            int x = Integer.parseInt(tokens[7]);
            List<Integer> scores = map.get(key);
            answer[i] = scores == null ? 0 : scores.size() - bs(scores, x);
        }
        return answer;
    }

    public String genKey(String[] fields, int mask) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            sb.append((mask & (1 << i)) != 0 ? "-" : fields[i]);
            sb.append("/");
        }
        return sb.toString();
    }

    public int bs(List<Integer> scores, int x) {
        int lo = 0, hi = scores.size() - 1;
        while (lo < hi + 1) {
            int mid = (lo + hi) >> 1;
            if (scores.get(mid) < x) lo = mid + 1;
            else hi = mid - 1;
        }
        return lo;
    }
}