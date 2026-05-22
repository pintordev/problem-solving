package programmers.sort.p17686;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();

        String[][] inputs = {
                {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"},
                {"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"}
        };
        String[][] answers = {
                {"img1.png", "IMG01.GIF", "img02.png", "img2.JPG", "img10.png", "img12.png"},
                {"A-10 Thunderbolt II", "B-50 Superfortress", "F-5 Freedom Fighter", "F-14 Tomcat"}
        };

        for (int i = 0; i < inputs.length; i++) {
            System.out.println(Arrays.equals(s.solution(inputs[i]), answers[i]));
        }
    }

    public String[] solution(String[] files) {
        File[] parsed = new File[files.length];
        for (int i = 0; i < files.length; i++) {
            parsed[i] = new File(files[i]);
        }
        Arrays.sort(parsed);
        String[] res = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            res[i] = parsed[i].name;
        }
        return res;
    }
}

class File implements Comparable<File> {
    static Pattern p = Pattern.compile("^([a-zA-Z\\-. ]+?)(\\d{1,5})");
    String name;
    String head;
    int number;

    File(String name) {
        this.name = name;
        Matcher m = p.matcher(name);
        m.find();
        this.head = m.group(1).toLowerCase();
        this.number = Integer.parseInt(m.group(2));
    }

    @Override
    public int compareTo(File o) {
        if (!this.head.equals(o.head)) return this.head.compareTo(o.head);
        return this.number - o.number;
    }
}