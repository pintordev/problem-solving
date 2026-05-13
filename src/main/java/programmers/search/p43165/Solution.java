package programmers.search.p43165;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] numbers = {{1, 1, 1, 1, 1}, {4, 1, 2, 1}};
        int[] targets = {3, 4};
        int[] answers = {5, 2};

        for (int i = 0; i < numbers.length; i++) {
            System.out.println(s.solution(numbers[i], targets[i]) == answers[i]);
        }
    }

    public int[] numbers;
    public int target;
    public int cnt = 0;

    public int solution(int[] numbers, int target) {
        this.numbers = numbers;
        this.target = target;
        cnt = 0;

        cal(0, 0);
        return cnt;
    }

    public void cal(int i, int s) {
        if (i == numbers.length) {
            if (s == target) cnt++;
            return;
        }
        cal(i + 1, s + numbers[i]);
        cal(i + 1, s - numbers[i]);
    }
}