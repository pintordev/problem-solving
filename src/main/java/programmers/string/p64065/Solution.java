package programmers.string.p64065;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        String[] inputs = {
            "{{2},{2,1},{2,1,3},{2,1,3,4}}",
            "{{1,2,3},{2,1},{1,2,4,3},{2}}",
            "{{20,111},{111}}"
        };
        int[][] answers = {
            {2, 1, 3, 4},
            {2, 1, 3, 4},
            {111, 20}
        };
        for (int i = 0; i < inputs.length; i++) {
            System.out.println(Arrays.equals(s.solution(inputs[i]), answers[i]));
        }
    }

    public int[] solution(String s) {
        int[] parsed = parse(s);
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : parsed) {
            map.merge(n, 1, Integer::sum);
        }
        return sortByValueDesc(map);
    }

    public int[] parse(String s) {
        String[] split = s.split("[{},]+");
        int[] parsed = new int[split.length - 1];
        for (int i = 0; i < split.length - 1; i++) {
            parsed[i] = Integer.parseInt(split[i + 1]);
        }
        return parsed;
    }

    public int[] sortByValueDesc(Map<Integer, Integer> map) {
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i).getKey();
        }
        return res;
    }
}