package programmers.string.p17683;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();

        String[] ms = {"ABCDEFG", "CC#BCC#BCC#BCC#B", "ABC"};
        String[][] musicinfos = {
            {"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"},
            {"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"},
            {"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"}
        };
        String[] answers = {"HELLO", "FOO", "WORLD"};

        for (int i = 0; i < ms.length; i++) {
            System.out.println(s.solution(ms[i], musicinfos[i]).equals(answers[i]));
        }
    }

    public String solution(String m, String[] musicinfos) {
        String title = "(None)";
        int maxTime = 0;
        m = convert(m);
        for (String musicinfo : musicinfos) {
            String[] music = musicinfo.split(",");
            int playTime = toTime(music[1]) - toTime(music[0]);
            if (!toNotes(convert(music[3]), playTime).contains(m) || playTime <= maxTime) continue;
            title = music[2];
            maxTime = playTime;
        }
        return title;
    }

    public String convert(String s) {
        return s
            .replace("C#", "V")
            .replace("D#", "W")
            .replace("F#", "X")
            .replace("G#", "Y")
            .replace("A#", "Z");
    }

    public int toTime(String time) {
        String[] t = time.split(":");
        return Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
    }

    public String toNotes(String notes, int playTime) {
        return (notes.repeat(playTime / notes.length()) + notes)
            .substring(0, playTime);
    }
}