package programmers.greedy.p42885;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();

        int[][] peoples = {{70, 50, 80, 50}, {70, 80, 50}};
        int[] limits = {100, 100};
        int[] answers = {3, 3};

        for (int i = 0; i < peoples.length; i++) {
            System.out.println(s.solution(peoples[i], limits[i]) == answers[i]);
        }
    }

    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        int i = 0;
        int j = people.length - 1;
        while (i < j) {
            if (people[i] + people[j] <= limit) i++;
            j--;
        }
        return people.length - i;
    }
}