package programmers.bruteforce.p340210;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        String[][] expressionss = {
                {"14 + 3 = 17", "13 - 6 = X", "51 - 5 = 44"},
                {"1 + 1 = 2", "1 + 3 = 4", "1 + 5 = X", "1 + 2 = X"},
                {"10 - 2 = X", "30 + 31 = 101", "3 + 3 = X", "33 + 33 = X"},
                {"2 - 1 = 1", "2 + 2 = X", "7 + 4 = X", "5 - 5 = X"},
                {"2 - 1 = 1", "2 + 2 = X", "7 + 4 = X", "8 + 4 = X"}
        };
        String[][] answers = {
                {"13 - 6 = 5"},
                {"1 + 5 = ?", "1 + 2 = 3"},
                {"10 - 2 = 4", "3 + 3 = 10", "33 + 33 = 110"},
                {"2 + 2 = 4", "7 + 4 = ?", "5 - 5 = 0"},
                {"2 + 2 = 4", "7 + 4 = 12", "8 + 4 = 13"}
        };
        for (int i = 0; i < expressionss.length; i++) {
            System.out.println(Arrays.equals(s.solution(expressionss[i]), answers[i]));
        }
    }

    String[] expressions;
    boolean[] valid;

    public String[] solution(String[] expressions) {
        this.expressions = expressions;
        valid = new boolean[10];
        for (int base = 2; base <= 9; base++) {
            valid[base] = isValid(base);
        }
        List<String> res = new ArrayList<>();
        for (String expression : expressions) {
            String[] t = expression.split(" ");
            if (!t[4].equals("X")) continue;
            res.add(t[0] + " " + t[1] + " " + t[2] + " = " + restore(t));
        }
        return res.toArray(new String[0]);
    }

    public boolean isValid(int base) {
        for (String expression : expressions) {
            String[] t = expression.split(" ");
            for (int i : new int[]{0, 2, 4}) {
                if (t[i].equals("X")) continue;
                for (char ch : t[i].toCharArray()) {
                    if (ch - '0' >= base) return false;
                }
            }
            if (t[4].equals("X")) continue;
            if (calculate(t, base) != Integer.parseInt(t[4], base)) return false;
        }
        return true;
    }

    public String restore(String[] t) {
        String res = null;
        for (int base = 2; base <= 9; base++) {
            if (!valid[base]) continue;
            String cur = Integer.toString(calculate(t, base), base);
            if (res != null && !res.equals(cur)) return "?";
            res = cur;
        }
        return res;
    }

    public int calculate(String[] t, int base) {
        int a = Integer.parseInt(t[0], base);
        int b = Integer.parseInt(t[2], base);
        if (t[1].equals("+")) return a + b;
        return a - b;
    }
}
