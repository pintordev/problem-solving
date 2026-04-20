package baekjoon.divideandconquer.p1074;

import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException {
        int n = read();
        int r = read();
        int c = read();

        System.out.println(solve(n, r, c));
    }

    // Divide and conquer: split the grid into 4 quadrants recursively.
    // Determine which quadrant (r, c) belongs to, add the quadrant offset, and recurse with adjusted coordinates.
    private static long solve(int n, int r, int c) {
        if (n == 0) return 0;
        int half = 1 << (n - 1);
        long subSquared = (long) half * half;
        if (r < half && c < half) return solve(n - 1, r, c);
        if (r < half) return subSquared + solve(n - 1, r, c - half);
        if (c < half) return 2 * subSquared + solve(n - 1, r - half, c);
        return 3 * subSquared + solve(n - 1, r - half, c - half);
    }

    // Bit manipulation: at each bit level i, the quadrant number is (r_bit << 1) | c_bit,
    // contributing quadrant_number * 4^i to the result. This is equivalent to interleaving bits of r and c.
    private static long solve(int n, int r, int c) {
        long result = 0;
        for (int i = n - 1; i >= 0; i--) {
            int rb = (r >> i) & 1;
            int cb = (c >> i) & 1;
            result += (long)(rb * 2 + cb) << (2 * i);
        }
        return result;
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