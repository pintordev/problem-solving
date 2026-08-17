package programmers.hash.p77486;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();

        String[][] enrolls = {
            {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"},
            {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"}
        };
        String[][] referrals = {
            {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"},
            {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"}
        };
        String[][] sellers = {
            {"young", "john", "tod", "emily", "mary"},
            {"sam", "emily", "jaimie", "edward"}
        };
        int[][] amounts = {
            {12, 4, 2, 5, 10},
            {2, 3, 5, 4}
        };
        int[][] answers = {
            {360, 958, 108, 0, 450, 18, 180, 1080},
            {0, 110, 378, 180, 270, 450, 0, 0}
        };

        for (int i = 0; i < enrolls.length; i++) {
            System.out.println(Arrays.equals(s.solution(enrolls[i], referrals[i], sellers[i], amounts[i]), answers[i]));
        }
    }

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        Map<String, String> referrerOf = new HashMap<>();
        Map<String, Integer> profitOf = new HashMap<>();
        for (int i = 0; i < enroll.length; i++) {
            referrerOf.put(enroll[i], referral[i]);
            profitOf.put(enroll[i], 0);
        }
        for (int i = 0; i < seller.length; i++) {
            int profit = amount[i] * 100;
            String cur = seller[i];
            while (cur != null && !cur.equals("-")) {
                int share = profit / 10;
                if (share < 1) {
                    profitOf.put(cur, profitOf.get(cur) + profit);
                    break;
                }
                profitOf.put(cur, profitOf.get(cur) + profit - share);
                profit = share;
                cur = referrerOf.get(cur);
            }
        }
        int[] res = new int[enroll.length];
        for (int i = 0; i < enroll.length; i++) {
            res[i] = profitOf.get(enroll[i]);
        }
        return res;
    }
}