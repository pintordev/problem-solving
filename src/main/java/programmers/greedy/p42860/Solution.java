package programmers.greedy.p42860;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        String[] names = {"JEROEN", "JAN"};
        int[] answers = {56, 23};
        for (int i = 0; i < names.length; i++) {
            System.out.println(s.solution(names[i]) == answers[i]);
        }
    }

    public int solution(String name) {
        int len = name.length();
        int ud = 0, lr = len - 1;
        for (int i = 0; i < len; i++) {
            char c = name.charAt(i);
            ud += Math.min(c - 'A', 'Z' - c + 1);
            int e = i + 1;
            while (e < len && name.charAt(e) == 'A') e++;
            lr = Math.min(lr, i + len - e + Math.min(i, len - e));
        }
        return ud + lr;
    }
}