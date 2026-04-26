package baekjoon.greedy.p1931;

import java.io.IOException;
import java.util.Arrays;

public class Solution {
    public static void main(String[] args) throws IOException {
        int n = (int) read();
        long[][] meetings = new long[n][2];
        for (int i = 0; i < n; i++) {
            meetings[i][0] = read();
            meetings[i][1] = read();
        }

        Arrays.sort(meetings, (a, b) -> {
            if (a[1] != b[1]) return Long.compare(a[1], b[1]);
            return Long.compare(a[0], b[0]);
        });

        int cnt = 0;
        long et = 0;
        for (long[] meeting : meetings) {
            if (meeting[0] >= et) {
                cnt++;
                et = meeting[1];
            }
        }

        System.out.println(cnt);
    }

    public static long read() throws IOException {
        int c;
        long n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}