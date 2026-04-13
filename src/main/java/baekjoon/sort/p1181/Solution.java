package baekjoon.sort.p1181;

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

public class Solution {
    public static void main(String[] args) throws IOException {
        int n = read();
        Set<String> set = new TreeSet<>((o1, o2) -> {
            if (o1.length() == o2.length()) return o1.compareTo(o2);
            return o1.length() - o2.length();
        });
        for (int i = 0; i < n; i++) set.add(readWord());

        StringBuilder sb = new StringBuilder();
        for (String s : set) sb.append(s).append('\n');
        System.out.print(sb);
    }

    public static String readWord() throws IOException {
        StringBuilder sb = new StringBuilder();
        int ch;
        while ((ch = System.in.read()) > 32) sb.append((char) ch);
        return sb.toString();
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