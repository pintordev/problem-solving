package baekjoon.math.p1546;

import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException {
        int n = read();

        double sum = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            int score = read();
            sum += score;
            max = Math.max(max, score);
        }
        System.out.println(sum / max * 100 / n);
    }

    public static int read() throws IOException {
        int c, n = System.in.read() & 15;
        boolean negative = n == 13;
        if (negative) n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return negative ? ~n + 1 : n;
    }
}