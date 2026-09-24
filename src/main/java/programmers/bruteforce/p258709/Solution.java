package programmers.bruteforce.p258709;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();

        int[][][] diceList = {
            {{1, 2, 3, 4, 5, 6}, {3, 3, 3, 3, 4, 4}, {1, 3, 3, 4, 4, 4}, {1, 1, 4, 4, 5, 5}},
            {{1, 2, 3, 4, 5, 6}, {2, 2, 4, 4, 6, 6}},
            {{40, 41, 42, 43, 44, 45}, {43, 43, 42, 42, 41, 41}, {1, 1, 80, 80, 80, 80}, {70, 70, 1, 1, 70, 70}}
        };
        int[][] answers = {
            {1, 4},
            {2},
            {1, 3}
        };

        for (int i = 0; i < diceList.length; i++) {
            System.out.println(Arrays.equals(s.solution(diceList[i]), answers[i]));
        }
    }

    int[][] dice;

    public int[] solution(int[][] dice) {
        this.dice = dice;
        int n = dice.length;
        int m = n >> 1;
        int bestMask = 0;
        int bestWins = -1;
        for (int mask = 0; mask < (1 << n); mask++) {
            if (Integer.bitCount(mask) != m) continue;
            List<Integer> indicesA = new ArrayList<>();
            List<Integer> indicesB = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) indicesA.add(i);
                else indicesB.add(i);
            }
            int wins = countWins(possibleSums(indicesA), possibleSums(indicesB));
            if (wins > bestWins) {
                bestWins = wins;
                bestMask = mask;
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if ((bestMask & (1 << i)) != 0) result.add(i + 1);
        }
        int[] res = new int[result.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = result.get(i);
        }
        return res;
    }

    public List<Integer> possibleSums(List<Integer> indices) {
        List<Integer> sums = new ArrayList<>();
        collectSums(indices, 0, 0, sums);
        return sums;
    }

    public void collectSums(List<Integer> indices, int depth, int sum, List<Integer> sums) {
        if (depth == indices.size()) {
            sums.add(sum);
            return;
        }
        int die = indices.get(depth);
        for (int face : dice[die]) {
            collectSums(indices, depth + 1, sum + face, sums);
        }
    }

    public int countWins(List<Integer> sumsA, List<Integer> sumsB) {
        int maxSum = 500;
        int[] freqB = new int[maxSum + 2];
        for (int sum : sumsB) {
            freqB[sum]++;
        }
        int[] cumulative = new int[maxSum + 2];
        for (int v = 1; v <= maxSum + 1; v++) {
            cumulative[v] = cumulative[v - 1] + freqB[v - 1];
        }
        int wins = 0;
        for (int sum : sumsA) {
            wins += cumulative[sum];
        }
        return wins;
    }
}
