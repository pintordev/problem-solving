package programmers.bruteforce.p42890;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();

        String[][] relation = {
            {"100", "ryan", "music", "2"},
            {"200", "apeach", "math", "2"},
            {"300", "tube", "computer", "3"},
            {"400", "con", "computer", "4"},
            {"500", "muzi", "music", "3"},
            {"600", "apeach", "music", "2"}
        };
        System.out.println(s.solution(relation) == 2);
    }

    int m;
    String[][] relation;
    List<Integer> candidates;

    public int solution(String[][] relation) {
        m = relation[0].length;
        this.relation = relation;
        candidates = new ArrayList<>();
        for (int mask = 1; mask < (1 << m); mask++) {
            if (!isUnique(mask)) continue;
            if (!isMinimal(mask)) continue;
            candidates.add(mask);
        }
        return candidates.size();
    }

    public boolean isUnique(int mask) {
        Set<String> set = new HashSet<>();
        for (String[] row : relation) {
            StringBuilder sb = new StringBuilder();
            for (int c = 0; c < m; c++) {
                if ((mask & (1 << c)) != 0) sb.append(row[c]).append("|");
            }
            if (!set.add(sb.toString())) return false;
        }
        return true;
    }

    public boolean isMinimal(int mask) {
        for (int k : candidates) {
            if ((mask & k) == k) return false;
        }
        return true;
    }
}