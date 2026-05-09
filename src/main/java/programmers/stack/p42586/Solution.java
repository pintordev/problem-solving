package programmers.stack.p42586;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();

        int[][] progressesList = {{93, 30, 55}, {95, 90, 99, 99, 80, 99}};
        int[][] speedsList = {{1, 30, 5}, {1, 1, 1, 1, 1, 1}};
        int[][] answers = {{2, 1}, {1, 3, 2}};

        for (int i = 0; i < progressesList.length; i++) {
            System.out.println(Arrays.equals(s.solution(progressesList[i], speedsList[i]), answers[i]));
        }
    }

    public int[] solution(int[] progresses, int[] speeds) {
        int[] deploy = new int[100];
        int day = -1, cnt = 0;
        for (int i = 0; i < progresses.length; i++) {
            while (progresses[i] + speeds[i] * day < 100) day++;
            if (deploy[day]++ == 0) cnt++;
        }

        int[] log = new int[cnt];
        for (int d = 0, l = 0; d < 100; d++) {
            if (deploy[d] == 0) continue;
            log[l++] = deploy[d];
        }
        return log;
    }
}