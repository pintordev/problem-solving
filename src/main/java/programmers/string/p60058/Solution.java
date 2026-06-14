package programmers.string.p60058;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        String[] inputs = {"(()())()", ")(", "()))((()"};
        String[] answers = {"(()())()", "()", "()(())()"};
        for (int i = 0; i < inputs.length; i++) {
            System.out.println(s.solution(inputs[i]).equals(answers[i]));
        }
    }

    public String solution(String p) {
        return convert(p);
    }

    public String convert(String p) {
        if (p.isEmpty()) return p;

        int m = sdx(p);
        String u = p.substring(0, m), v = p.substring(m);

        if (isRight(u)) return u + convert(v);

        StringBuilder sb = new StringBuilder()
                .append('(')
                .append(convert(v))
                .append(')');

        for (char c : u.substring(1, u.length() - 1).toCharArray()) {
            sb.append(c == '(' ? ')' : '(');
        }
        return sb.toString();
    }

    public int sdx(String p) {
        char[] ps = p.toCharArray();
        int c = 0;
        for (int i = 0; i < ps.length; i++) {
            c = ps[i] == '(' ? c - 1 : c + 1;
            if (c == 0) return i + 1;
        }
        return 0;
    }

    public boolean isRight(String u) {
        int cnt = 0;
        for (char c : u.toCharArray()) {
            cnt += c == '(' ? 1 : -1;
            if (cnt < 0) return false;
        }
        return true;
    }
}