package programmers.implementation.p17676;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();

        String[][] linesArr = {
            {"2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:07.000 2s"},
            {"2016-09-15 01:00:04.002 2.0s", "2016-09-15 01:00:07.000 2s"},
            {
                "2016-09-15 20:59:57.421 0.351s",
                "2016-09-15 20:59:58.233 1.181s",
                "2016-09-15 20:59:58.299 0.8s",
                "2016-09-15 20:59:58.688 1.041s",
                "2016-09-15 20:59:59.591 1.412s",
                "2016-09-15 21:00:00.464 1.466s",
                "2016-09-15 21:00:00.741 1.581s",
                "2016-09-15 21:00:00.748 2.31s",
                "2016-09-15 21:00:00.966 0.381s",
                "2016-09-15 21:00:02.066 2.62s"
            }
        };
        int[] answers = {1, 2, 7};

        for (int i = 0; i < linesArr.length; i++) {
            System.out.println(s.solution(linesArr[i]) == answers[i]);
        }
    }

    int start;
    int end;

    public int solution(String[] lines) {
        int n = lines.length;
        int[] starts = new int[n];
        int[] ends = new int[n];
        for (int i = 0; i < n; i++) {
            parseInterval(lines[i]);
            starts[i] = start - 999;
            ends[i] = end;
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int cnt = 0;
        int max = 0;
        int i = 0;
        int j = 0;
        while (i < n) {
            if (starts[i] <= ends[j]) {
                cnt++;
                max = Math.max(max, cnt);
                i++;
            } else {
                cnt--;
                j++;
            }
        }
        return max;
    }

    public void parseInterval(String line) {
        int hour = Integer.parseInt(line.substring(11, 13));
        int minute = Integer.parseInt(line.substring(14, 16));
        int second = Integer.parseInt(line.substring(17, 19));
        int millis = Integer.parseInt(line.substring(20, 23));
        int duration = (int) (Double.parseDouble(line.substring(24, line.length() - 1)) * 1000);
        end = ((hour * 60 + minute) * 60 + second) * 1000 + millis;
        start = end - duration + 1;
    }
}
