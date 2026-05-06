package programmers.string.p12981;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();

        int[] nList = {3, 5, 2};
        String[][] wordsList = {
                {"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"},
                {"hello", "observe", "effect", "take", "either", "recognize", "encourage", "ensure", "establish", "hang", "gather", "refer", "reference", "estimate", "executive"},
                {"hello", "one", "even", "never", "now", "world", "draw"}
        };
        int[][] answers = {{3, 3}, {0, 0}, {1, 3}};

        for (int i = 0; i < nList.length; i++) {
            System.out.println(Arrays.equals(s.solution(nList[i], wordsList[i]), answers[i]));
        }
    }

    public int[] solution(int n, String[] words) {
        Set<String> set = new HashSet<>();
        char c = ' ';
        int i = 0;
        for (String word : words) {
            i++;
            if (c != ' ' && c != word.charAt(0)) break;
            if (!set.add(word)) break;
            c = word.charAt(word.length() - 1);
        }
        if (set.size() == words.length) return new int[]{0, 0};
        return new int[]{(i - 1) % n + 1, (i - 1) / n + 1};
    }
}