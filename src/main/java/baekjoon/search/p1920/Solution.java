package baekjoon.search.p1920;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) throws IOException {
        int n = read();

        Set<Integer> set = new HashSet<>();
        while(n-- > 0) set.add(read());

        int m = read();
        StringBuilder sb = new StringBuilder();
        while (m-- > 0) sb.append(set.contains(read()) ? 1 : 0).append('\n');
        System.out.print(sb);
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