package programmers.math.p12924;

public class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();

        int n = 15;
        int result = 4;

        System.out.println(s.solution(n) == result);
    }

    public int solution(int n) {
        // n = i * m + i * (i + 1) / 2
        // m = (n - i * (i + 1) / 2) / i
        // m = n / i - (i + 1) / 2
        // i == n의 홀수인 약수
        int cnt = 0;
        for (int i = 1; i * i <= n; i++) {
            if (n % i != 0) continue;
            if (i % 2 == 1) cnt++;
            if (i != n / i && (n / i) % 2 == 1) cnt++;
        }
        return cnt;
    }
}
