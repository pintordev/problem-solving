package programmers.math.p12985;

public class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();

        int[] nList = {8};
        int[] aList = {4};
        int[] bList = {7};
        int[] answers = {3};

        for (int i = 0; i < nList.length; i++) {
            System.out.println(s.solution(nList[i], aList[i], bList[i]) == answers[i]);
        }
    }

    public int solution(int n, int a, int b) {
        return Integer.toBinaryString((a - 1) ^ (b - 1)).length();
    }
}