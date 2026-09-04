package programmers.prefixsum.p72414;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        String[] play_times = {"02:03:55", "99:59:59", "50:00:00"};
        String[] adv_times = {"00:14:15", "25:00:00", "50:00:00"};
        String[][] logss = {
            {"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"},
            {"69:59:59-89:59:59", "01:00:00-21:00:00", "79:59:59-99:59:59", "11:00:00-31:00:00"},
            {"15:36:51-38:21:49", "10:14:18-15:36:51", "38:21:49-42:51:45"}
        };
        String[] answers = {"01:30:59", "01:00:00", "00:00:00"};
        for (int i = 0; i < play_times.length; i++) {
            System.out.println(s.solution(play_times[i], adv_times[i], logss[i]).equals(answers[i]));
        }
    }

    public String solution(String play_time, String adv_time, String[] logs) {
        int playSec = timeToSec(play_time);
        int advSec = timeToSec(adv_time);
        int[] watch = new int[playSec + 1];
        for (String log : logs) {
            watch[timeToSec(log.substring(0, 8))]++;
            watch[timeToSec(log.substring(9))]--;
        }
        for (int i = 1; i < playSec; i++) {
            watch[i] += watch[i - 1];
        }
        long windowView = 0;
        for (int i = 0; i < advSec; i++) {
            windowView += watch[i];
        }
        long maxView = windowView;
        int maxStart = 0;
        for (int t = 1; t <= playSec - advSec; t++) {
            windowView += watch[t + advSec - 1] - watch[t - 1];
            if (windowView > maxView) {
                maxView = windowView;
                maxStart = t;
            }
        }
        return secToTime(maxStart);
    }

    public int timeToSec(String time) {
        int hour = Integer.parseInt(time.substring(0, 2));
        int min = Integer.parseInt(time.substring(3, 5));
        int sec = Integer.parseInt(time.substring(6, 8));
        return hour * 3600 + min * 60 + sec;
    }

    public String secToTime(int total) {
        int hour = total / 3600;
        int min = total % 3600 / 60;
        int sec = total % 60;
        return String.format("%02d:%02d:%02d", hour, min, sec);
    }
}
