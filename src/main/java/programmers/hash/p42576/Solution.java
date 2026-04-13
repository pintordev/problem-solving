package programmers.hash.p42576;

public class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();

        String[][] participants = {{"leo", "kiki", "eden"}, {"marina", "josipa", "nikola", "vinko", "filipa"}};
        String[][] completions = {{"eden", "kiki"}, {"josipa", "filipa", "marina", "nikola"}};
        String[] answers = {"leo", "vinko"};

        for (int i = 0; i < participants.length; i++) {
            System.out.println(s.solution(participants[i], completions[i]).equals(answers[i]));
        }
    }

    public String solution(String[] participant, String[] completion) {
        // TODO
        return "";
    }
}
