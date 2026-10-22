package programmers.string.p1830;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        String[] sentences = {"HaEaLaLaObWORLDb", "SpIpGpOpNpGJqOqA", "AxAxAxAoBoBoB"};
        String[] answers = {"HELLO WORLD", "SIGONG JOA", "invalid"};
        for (int i = 0; i < sentences.length; i++) {
            System.out.println(s.solution(sentences[i]).equals(answers[i]));
        }
    }

    String s;
    int n;
    int[] cnt;

    public String solution(String sentence) {
        s = sentence;
        n = s.length();
        cnt = new int[26];
        for (int i = 0; i < n; i++) {
            if (Character.isLowerCase(s.charAt(i))) {
                cnt[s.charAt(i) - 'a']++;
            }
        }

        boolean[] ok = new boolean[n + 1];
        int[] nxt = new int[n + 1];
        ok[n] = true;
        for (int i = n - 1; i >= 0; i--) {
            int e = rule1(i);
            if (e == -1 || !ok[e]) e = rule2(i);
            if (e == -1 || !ok[e]) e = Character.isUpperCase(s.charAt(i)) ? i + 1 : -1;
            if (e == -1 || !ok[e]) continue;
            ok[i] = true;
            nxt[i] = e;
        }
        if (!ok[0]) return "invalid";

        StringBuilder sb = new StringBuilder();
        boolean prevPlain = false;
        for (int i = 0; i < n; i = nxt[i]) {
            boolean plain = nxt[i] - i == 1;
            if (sb.length() > 0 && !(plain && prevPlain)) sb.append(' ');
            for (int k = i; k < nxt[i]; k++) {
                if (Character.isUpperCase(s.charAt(k))) sb.append(s.charAt(k));
            }
            prevPlain = plain;
        }
        return sb.toString();
    }

    public int rule1(int i) {
        if (i + 1 >= n || !Character.isUpperCase(s.charAt(i)) || !Character.isLowerCase(s.charAt(i + 1))) return -1;
        char c = s.charAt(i + 1);
        int end = i + 2 * cnt[c - 'a'] + 1;
        if (end > n) return -1;
        for (int k = i; k < end; k++) {
            boolean ok = (k - i) % 2 == 0 ? Character.isUpperCase(s.charAt(k)) : s.charAt(k) == c;
            if (!ok) return -1;
        }
        return end;
    }

    public int rule2(int i) {
        char c = s.charAt(i);
        if (!Character.isLowerCase(c) || cnt[c - 'a'] != 2) return -1;
        int j = s.indexOf(c, i + 1);
        if (j == i + 1) return -1;
        for (int k = i + 1; k < j; k++) {
            if (Character.isLowerCase(s.charAt(k))) return rule1(i + 1) == j ? j + 1 : -1;
        }
        return j + 1;
    }
}
