package programmers.sort.p42747;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();

        int[][] citationsList = {
                {3, 0, 6, 1, 5}
        };
        int[] answers = {3};

        for (int i = 0; i < citationsList.length; i++) {
            System.out.println(s.solution(citationsList[i]) == answers[i]);
        }
    }

    public int solution(int[] citations) {
        Arrays.sort(citations);
        int h = 0;
        for (int i = citations.length - 1; i >= 0; i--) {
            if (citations[i] > h) h++;
        }
        return h;
    }
}