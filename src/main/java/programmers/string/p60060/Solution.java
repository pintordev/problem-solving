package programmers.string.p60060;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        String[][] wordss = {
                {"frodo", "front", "frost", "frozen", "frame", "kakao"}
        };
        String[][] queriess = {
                {"fro??", "????o", "fr???", "fro???", "pro?"}
        };
        int[][] answers = {
                {3, 2, 4, 1, 0}
        };
        for (int i = 0; i < wordss.length; i++) {
            System.out.println(Arrays.equals(s.solution(wordss[i], queriess[i]), answers[i]));
        }
    }

    String[][] front = new String[10_001][];
    String[][] back = new String[10_001][];

    public int[] solution(String[] words, String[] queries) {
        int[] cnt = new int[10_001];
        for (String word : words) {
            cnt[word.length()]++;
        }
        for (int len = 1; len <= 10_000; len++) {
            front[len] = new String[cnt[len]];
            back[len] = new String[cnt[len]];
        }
        for (String word : words) {
            int len = word.length();
            front[len][--cnt[len]] = word;
            back[len][cnt[len]] = reverse(word);
        }
        for (int len = 1; len <= 10_000; len++) {
            Arrays.sort(front[len]);
            Arrays.sort(back[len]);
        }
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            String query = queries[i];
            if (query.charAt(0) != '?') res[i] = match(front[query.length()], query);
            else res[i] = match(back[query.length()], reverse(query));
        }
        return res;
    }

    public int match(String[] arr, String query) {
        return bs(arr, query, 'z', true) - bs(arr, query, 'a', false);
    }

    public int bs(String[] arr, String query, char fill, boolean upper) {
        int lo = 0, hi = arr.length - 1;
        while (lo < hi + 1) {
            int mid = (lo + hi) >> 1;
            int diff = compare(arr[mid], query, fill);
            if (upper ? diff <= 0 : diff < 0) lo = mid + 1;
            else hi = mid - 1;
        }
        return lo;
    }

    public int compare(String word, String query, char fill) {
        for (int i = 0; i < word.length(); i++) {
            char c = query.charAt(i) == '?' ? fill : query.charAt(i);
            if (word.charAt(i) != c) return word.charAt(i) - c;
        }
        return 0;
    }

    public String reverse(String str) {
        return new StringBuilder(str).reverse().toString();
    }
}
