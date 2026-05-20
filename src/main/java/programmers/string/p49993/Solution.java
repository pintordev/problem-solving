package programmers.string.p49993;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        String[] skill_trees = {"BACDE", "CBADF", "AECB", "BDA"};
        System.out.println(s.solution("CBD", skill_trees) == 2);
    }

    public int solution(String skill, String[] skill_trees) {
        int cnt = 0;
        for (String skill_tree : skill_trees) {
            if (isValid(skill, skill_tree)) cnt++;
        }
        return cnt;
    }

    public boolean isValid(String skill, String skillTree) {
        for (int i = 0, p = 0; i < skillTree.length(); i++) {
            char c = skillTree.charAt(i);
            if (skill.indexOf(c) == -1) continue;
            if (skill.charAt(p) == c) p++;
            else return false;
        }
        return true;
    }
}