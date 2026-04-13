package baekjoon.string.p1259;

import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        String word;
        while (!(word = readWord()).equals("0")) {
            sb.append(isPalindrome(word) ? "yes" : "no").append('\n');
        }
        System.out.print(sb);
    }

    public static boolean isPalindrome(String word) {
        int left = 0;
        int right = word.length() - 1;
        while (left < right) {
            if (word.charAt(left) != word.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    public static String readWord() throws IOException {
        StringBuilder sb = new StringBuilder();
        int ch;
        while ((ch = System.in.read()) > 32) sb.append((char) ch);
        return sb.toString();
    }
}