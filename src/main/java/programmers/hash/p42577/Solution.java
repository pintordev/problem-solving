package programmers.hash.p42577;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();

        String[][] inputs = {
            {"119", "97674223", "1195524421"},
            {"123", "456", "789"},
            {"12", "123", "1235", "567", "88"}
        };
        boolean[] answers = {false, true, false};

        for (int i = 0; i < inputs.length; i++) {
            System.out.println(s.solution(inputs[i]) == answers[i]);
        }
    }

    public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book);
        for (int i = 1; i < phone_book.length; i++) {
            if (phone_book[i].startsWith(phone_book[i - 1])) return false;
        }
        return true;
    }
}