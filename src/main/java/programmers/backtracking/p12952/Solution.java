package programmers.backtracking.p12952;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] inputs = {4};
        int[] answers = {2};
        for (int i = 0; i < inputs.length; i++) {
            System.out.println(s.solution(inputs[i]) == answers[i]);
        }
    }

    int n;
    int cnt;
    int full;

    public int solution(int n) {
        this.n = n;
        this.cnt = 0;
        full = (1 << n) - 1;
        dfs(0, 0, 0, 0);
        return cnt;
    }

    public void dfs(int r, int c, int ld, int rd) {
        if (r == n) {
            cnt++;
            return;
        }
        int available = full & ~(c | ld | rd);
        while (available != 0) {
            int pick = available & -available;
            available -= pick;
            dfs(r + 1, c | pick, (ld | pick) << 1, (rd | pick) >> 1);
        }
    }
}