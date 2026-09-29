package programmers.string.p42893;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();

        String[] words = {"blind", "Muzi"};
        String[][] pagesArr = {
            {
                "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>",
                "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>",
                "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>"
            },
            {
                "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>",
                "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>"
            }
        };
        int[] answers = {0, 1};

        for (int i = 0; i < words.length; i++) {
            System.out.println(s.solution(words[i], pagesArr[i]) == answers[i]);
        }
    }

    static Pattern urlPattern = Pattern.compile("<meta property=\"og:url\" content=\"https://(\\S*)\"/>");
    static Pattern hrefPattern = Pattern.compile("<a href=\"https://(\\S*)\"");
    Pattern wordPattern;

    public int solution(String word, String[] pages) {
        wordPattern = Pattern.compile("\\b(?i)" + word + "\\b");
        int n = pages.length;
        int[] baseScores = new int[n];
        int[] outDegrees = new int[n];
        List<String>[] links = new List[n];
        Map<String, Integer> urlIndex = new HashMap<>();
        for (int i = 0; i < n; i++) {
            urlIndex.put(extractUrl(pages[i]), i);
            baseScores[i] = countWord(pages[i]);
            links[i] = extractLinks(pages[i]);
            outDegrees[i] = links[i].size();
        }
        double[] linkScores = new double[n];
        for (int i = 0; i < n; i++) {
            for (String link : new HashSet<>(links[i])) {
                Integer target = urlIndex.get(link);
                if (target != null) linkScores[target] += (double) baseScores[i] / outDegrees[i];
            }
        }
        int res = 0;
        double maxScore = -1;
        for (int i = 0; i < n; i++) {
            double score = baseScores[i] + linkScores[i];
            if (score > maxScore) {
                maxScore = score;
                res = i;
            }
        }
        return res;
    }

    public String extractUrl(String page) {
        Matcher m = urlPattern.matcher(page);
        if (m.find()) return m.group(1);
        return null;
    }

    public List<String> extractLinks(String page) {
        List<String> links = new ArrayList<>();
        Matcher m = hrefPattern.matcher(page);
        while (m.find()) {
            links.add(m.group(1));
        }
        return links;
    }

    public int countWord(String page) {
        Matcher m = wordPattern.matcher(page.replaceAll("[0-9]", " "));
        int cnt = 0;
        while (m.find()) {
            cnt++;
        }
        return cnt;
    }
}
