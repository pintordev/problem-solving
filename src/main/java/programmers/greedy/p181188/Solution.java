package programmers.greedy.p181188;

import java.util.Arrays;
import java.util.Comparator;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][][] inputs = {
            {{4,5},{4,8},{10,14},{11,13},{5,12},{3,7},{1,4}}
        };
        int[] answers = {3};
        for (int i = 0; i < inputs.length; i++) {
            System.out.println(s.solution(inputs[i]) == answers[i]);
        }
    }

    public int solution(int[][] targets) {
        Arrays.sort(targets, Comparator.comparingInt(o -> o[1]));
        int cnt = 1;
        int le = targets[0][1];
        for (int i = 1; i < targets.length; i++) {
            if (targets[i][0] < le) continue;
            cnt++;
            le = targets[i][1];
        }
        return cnt;
    }
}