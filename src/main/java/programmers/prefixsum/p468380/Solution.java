package programmers.prefixsum.p468380;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] arrs = {
            {3, 2, 3, 1, 1},
            {2, 2, 2},
            {8, 8, 6, 5, 2, 9, 8, 4, 3, 10},
            {70195, 25471, 7389, 58187, 18454, 90532, 97667, 17148, 91636, 2810},
            {16952, 70276, 16771, 37992, 87549, 54906, 36718, 20478, 57088, 27916, 51509, 83422, 51707, 18807, 80859, 2673, 37734, 93380},
            {49134, 86806, 94548, 88849, 95022, 28334, 16637, 79487, 23773, 7314, 47370, 50269, 36573, 9415, 44674, 28096}
        };
        long[] ls = {5, 2, 25, 126058, 149845, 61242};
        long[] rs = {7, 2, 27, 462933, 228204, 88535};
        long[][] answers = {
            {8, 2},
            {2, 6},
            {15, 3},
            {27554327568L, 1},
            {6860339640L, 9190},
            {2369282964L, 59513}
        };
        for (int i = 0; i < arrs.length; i++) {
            System.out.println(Arrays.equals(s.solution(arrs[i], ls[i], rs[i]), answers[i]));
        }
    }

    int[] arr;
    long[] len;
    long[] sq;

    public long[] solution(int[] arr, long l, long r) {
        this.arr = arr;
        int n = arr.length;
        len = new long[n + 1];
        sq = new long[n + 1];
        for (int i = 0; i < n; i++) {
            len[i + 1] = len[i] + arr[i];
            sq[i + 1] = sq[i] + (long) arr[i] * arr[i];
        }
        long k = prefix(r) - prefix(l - 1);
        long size = r - l + 1;
        long last = len[n] - size + 1;
        int i = 0;
        int j = block(size);
        long count = 0;
        long s = 1;
        while (s <= last) {
            long end = Math.min(len[i + 1], Math.min(len[j + 1] - size + 1, last));
            long diff = k - (sum(j, s + size - 1) - sum(i, s - 1));
            long d = arr[j] - arr[i];
            if (d == 0) {
                if (diff == 0) count += end - s + 1;
            } else if (diff % d == 0 && diff / d >= 0 && diff / d <= end - s) {
                count++;
            }
            s = end + 1;
            if (s > len[i + 1]) i++;
            if (s + size - 1 > len[j + 1]) j++;
        }
        return new long[]{k, count};
    }

    public long prefix(long x) {
        if (x == 0) return 0;
        return sum(block(x), x);
    }

    public long sum(int b, long x) {
        return sq[b] + (long) arr[b] * (x - len[b]);
    }

    public int block(long x) {
        int lo = 0;
        int hi = arr.length - 1;
        while (lo < hi) {
            int mid = (lo + hi) >> 1;
            if (len[mid + 1] >= x) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }
}
