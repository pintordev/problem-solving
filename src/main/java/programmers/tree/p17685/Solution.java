package programmers.tree.p17685;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        String[][] wordss = {
                {"go", "gone", "guild"},
                {"abc", "def", "ghi", "jklm"},
                {"word", "war", "warrior", "world"}
        };
        int[] answers = {7, 4, 15};
        for (int i = 0; i < wordss.length; i++) {
            System.out.println(s.solution(wordss[i]) == answers[i]);
        }
    }

    Node root;

    public int solution(String[] words) {
        root = new Node();
        for (String word : words) {
            insert(word);
        }
        int res = 0;
        for (String word : words) {
            Node cur = root;
            for (char ch : word.toCharArray()) {
                cur = cur.next[ch - 'a'];
                res++;
                if (cur.cnt == 1) break;
            }
        }
        return res;
    }

    public void insert(String word) {
        Node cur = root;
        for (char ch : word.toCharArray()) {
            if (cur.next[ch - 'a'] == null) cur.next[ch - 'a'] = new Node();
            cur = cur.next[ch - 'a'];
            cur.cnt++;
        }
    }
}

class Node {
    Node[] next = new Node[26];
    int cnt;
}
