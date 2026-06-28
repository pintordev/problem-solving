package programmers.stack.p176962;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        String[][][] inputs = {
            {{"korean", "11:40", "30"}, {"english", "12:10", "20"}, {"math", "12:30", "40"}},
            {{"science", "12:40", "50"}, {"music", "12:20", "40"}, {"history", "14:00", "30"}, {"computer", "12:30", "100"}},
            {{"aaa", "12:00", "20"}, {"bbb", "12:10", "30"}, {"ccc", "12:40", "10"}}
        };
        String[][] answers = {
            {"korean", "english", "math"},
            {"science", "history", "computer", "music"},
            {"bbb", "ccc", "aaa"}
        };
        for (int i = 0; i < inputs.length; i++) {
            System.out.println(Arrays.equals(s.solution(inputs[i]), answers[i]));
        }
    }

    public String[] solution(String[][] plans) {
        Arrays.sort(plans, Comparator.comparing(o -> o[1]));
        Deque<int[]> stack = new ArrayDeque<>();
        String[] res = new String[plans.length];
        int min = toMin(plans[0][1]), idx = 0, rdx = 0;
        for (String[] plan : plans) {
            int temp = toMin(plan[1]);
            int remain = temp - min;
            min = temp;
            while (!stack.isEmpty()) {
                int[] now = stack.pop();
                if (remain >= now[1]) {
                    remain -= now[1];
                    res[rdx++] = plans[now[0]][0];
                } else {
                    now[1] -= remain;
                    stack.push(now);
                    break;
                }
            }
            stack.push(new int[]{idx++, Integer.parseInt(plan[2])});
        }
        while (!stack.isEmpty()) {
            res[rdx++] = plans[stack.pop()[0]][0];
        }
        return res;
    }

    public int toMin(String s) {
        String[] t = s.split(":");
        return Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
    }
}