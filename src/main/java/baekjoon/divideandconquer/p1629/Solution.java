package baekjoon.divideandconquer.p1629;

import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException {
        long a = read();
        long b = read();
        long c = read();

        System.out.println(pow(a, b, c));
    }

    private static long pow(long a, long b, long c) {
        if (b == 0) return 1;

        long p = pow(a, b / 2, c);
        long pp = p * p % c;
        if (b % 2 == 1) pp = pp * (a % c) % c;
        return pp;
    }

    private static int read() throws IOException {
        int c, n = System.in.read() & 15;
        boolean negative = n == 13;
        if (negative) n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return negative ? ~n + 1 : n;
    }
}