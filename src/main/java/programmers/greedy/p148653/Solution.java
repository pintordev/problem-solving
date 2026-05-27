package programmers.greedy.p148653;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] storeys = {16, 2554};
        int[] answers = {6, 16};
        for (int i = 0; i < storeys.length; i++) {
            System.out.println(s.solution(storeys[i]) == answers[i]);
        }
    }

    public int solution(int storey) {
        int cost = 0;
        while (storey > 0) {
            int d = storey % 10;
            if (d < 5) cost += d;
            else if (d > 5) {
                cost += 10 - d;
                storey += 10 - d;
            }
            else {
                if (storey / 10 % 10 >= 5) {
                    cost += 5;
                    storey += 5;
                }
                else cost += 5;
            }
            storey /= 10;
        }
        return cost;
    }
}